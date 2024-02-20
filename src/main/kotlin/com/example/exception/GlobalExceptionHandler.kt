package com.example.exception

import com.example.common.ApiRes
import com.example.common.BizCode
import com.example.common.BizException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
@ResponseBody
class GlobalExceptionHandler {
    @ExceptionHandler(BizException::class)
    fun handleBizException(
            ex: BizException
    ): ApiRes<Nothing> {
        return ApiRes.error(ex.code, ex.msg)
    }

    @ExceptionHandler(RuntimeException::class)
    fun handleRuntimeException(
        ex: RuntimeException
    ): ApiRes<Nothing> {
        ex.printStackTrace()
        return ApiRes.error(BizCode.SYSTEM_ERROR.code, ex.message ?: BizCode.SYSTEM_ERROR.msg)
    }

}
