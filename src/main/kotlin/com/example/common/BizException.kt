package com.example.common

import com.example.common.BizCode

class BizException(val code: Int, val msg: String) : RuntimeException(msg) {
    constructor(bizCode: BizCode) : this(bizCode.code, bizCode.msg)
}

