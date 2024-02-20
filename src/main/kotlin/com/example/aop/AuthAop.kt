package com.example.aop

import com.example.common.*
import com.example.dao.sys.SysUserDAO
import com.example.model.sys.SysUser
import com.example.model.sys.toVO
import com.example.util.JwtUtil
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.ktorm.dsl.eq
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

@Aspect
@Component
class AuthAop {
    @Autowired
    private lateinit var jwtUtil: JwtUtil

    @Autowired
    private lateinit var sysUserDAO: SysUserDAO;
    @Pointcut("@annotation(com.example.common.Need)")
    fun permissionCheck() {
    }
    @Around("permissionCheck()")
    fun around(joinPoint: ProceedingJoinPoint): Any? {
        // 获取当前请求的用户id
        val userId = (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).request
            .let { jwtUtil.parseToken(it) }
            .let { it?.get("id")?.asLong()!! }

        // 获取当前请求的用户信息
        val user = sysUserDAO.findOne { it.id eq userId }.let {
            it ?: justThrow(BizCode.USER_NOT_FOUND)
            it as SysUser
        }

        // 获得当前方法上的 @Need 注解
        val need = joinPoint.target.javaClass.getMethod(joinPoint.signature.name).getAnnotation(Need::class.java)

        // 获取当前请求的用户权限
        val roleVOs = sysUserDAO.getRoleVOsByUserIds(listOf(user.id))
        val userVo = user.toVO(roleVOs)

        userVo.hasPermission(need).failThenThrow(BizCode.ACCESS_DENIED)

        try {
            AuthContextHolder.setUser(userVo)
            return joinPoint.proceed()
        } finally {
            // 清除当前请求的用户信息
            AuthContextHolder.clear()
        }
    }


}