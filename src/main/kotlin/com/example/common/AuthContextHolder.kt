package com.example.common

import com.example.model.sys.UserVO

object AuthContextHolder {
    private val authLocalThread = ThreadLocal<UserVO>()

    fun getUser(): UserVO {
        return authLocalThread.get()
    }

    fun setUser(sysUserInfo: UserVO) {
        authLocalThread.set(sysUserInfo)
    }

    fun clear() {
        authLocalThread.remove()
    }
}
