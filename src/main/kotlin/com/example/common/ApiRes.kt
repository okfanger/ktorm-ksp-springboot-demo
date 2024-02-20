package com.example.common

data class ApiRes<T>(
    val success: Boolean,
    val data: T? = null,
    val msg: String,
    val code: Int
) {
    companion object {
        fun <T> ok(data: T): ApiRes<T> {
            return ApiRes(true, data, BizCode.SUCCESS.msg, BizCode.SUCCESS.code)
        }

        fun <T> error(bizCode: BizCode): ApiRes<T> {
            return ApiRes(false, null, bizCode.msg, bizCode.code)
        }

        fun <T> error(code: Int, msg: String): ApiRes<T> {
            return ApiRes(false, null, msg, code)
        }
    }
}

fun <T> T.okk(): ApiRes<T> {
    return ApiRes.ok(this)
}