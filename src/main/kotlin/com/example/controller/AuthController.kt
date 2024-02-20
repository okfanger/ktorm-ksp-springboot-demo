package com.example.controller

import com.example.common.*
import com.example.dao.sys.SysUserDAO
import com.example.model.sys.TokenDTO
import com.example.model.sys.UserLoginRequest
import com.example.model.sys.UserVO
import com.example.util.JwtUtil
import org.ktorm.dsl.eq
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RequestMapping("/auth")
@RestController
class AuthController {

    @Autowired
    private lateinit var sysUserDAO: SysUserDAO

    @Autowired
    private lateinit var jwtUtil: JwtUtil

    @PostMapping(value = ["/login"])
    fun login(@RequestBody body: UserLoginRequest): ApiRes<TokenDTO> {
        return sysUserDAO.findOne { it.uid eq body.uid }
            .let {
                // 如果用户不存在则抛出用户不存在异常
                it ?: justThrow(BizCode.USER_NOT_FOUND)
                // 如果密码不匹配则抛出密码错误异常
                (it!!.password != body.password).thenThrow(BizCode.PWD_WRONG)
                // 创建token并返回
                jwtUtil.createToken(buildMap {
                    put("uid", it.uid)
                    put("id", it.id)
                }).let { token -> TokenDTO(token).okk() }
            }
    }

    @GetMapping("/info")
    @Need(allOf = ["auth:info"])
    fun info(): ApiRes<UserVO> {
        return AuthContextHolder.getUser().okk()
    }
}