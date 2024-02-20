package com.example.common

enum class BizCode(val code: Int, val msg: String) {
    SUCCESS(200, "成功"),
    USER_NOT_FOUND(40101, "用户不存在"),
    PWD_WRONG(40102, "密码错误"),
    USER_EXIST(40103, "用户已存在"),
    NO_AUTH(40104, "未登录"),
    SYSTEM_ERROR(50000, "系统错误"),
}