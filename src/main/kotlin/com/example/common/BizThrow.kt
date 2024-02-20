package com.example.common


fun <T> T.okk(): ApiRes<T> {
    return ApiRes.ok(this)
}
fun Boolean.thenThrow(exception: BizException) {
    if (this) {
        throw exception
    }
}

fun Boolean.thenThrow(bizCode: BizCode) {
    return this.thenThrow(BizException(bizCode))
}

fun Boolean.failThenThrow(bizCode: BizCode) {
    if (!this) {
        throw BizException(bizCode)
    }
}

fun justThrow(bizCode: BizCode) {
    throw BizException(bizCode)
}

inline fun <T> tryGetOrThrow(block: () -> T, bizCode: BizCode): T {
    return try {
        block()
    } catch (e: Exception) {
        throw BizException(bizCode)
    }
}

inline fun <T> tryOrThrow(block: () -> T, bizCode: BizCode) {
    try {
        block()
    } catch (e: Exception) {
        throw BizException(bizCode)
    }
}

inline fun <T> tryOrElse(block: () -> T, default: T): T {
    return try {
        block()
    } catch (e: Exception) {
        default
    }
}

inline fun <T> tryGetOrElse(block: () -> T, default: T): T {
    return try {
        block()
    } catch (e: Exception) {
        default
    }
}