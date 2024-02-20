package com.example.util

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.Claim
import com.example.common.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest

@Component
class JwtUtil @Autowired constructor(val jwtProperties: JwtProperties) {

    fun createToken(payload: Map<String, Any>): String {
        return JWT.create()
            .withPayload(payload)
            .sign(Algorithm.HMAC512(jwtProperties.secret))
    }

    fun validateToken(authToken: String?): Boolean {
        return tryGetOrElse({
            JWT.require(Algorithm.HMAC512(jwtProperties.secret))
                .build()
                .verify(authToken)
            true
        }, false)
    }

    fun parseToken(token: String): Map<String?, Claim?>? {
        return token.replace(jwtProperties.tokenHead!!, "").trim()
            .let {
                validateToken(it).failThenThrow(BizCode.NO_AUTH)
                tryGetOrThrow({ JWT.decode(it).claims }, BizCode.NO_AUTH)
            }
    }

    fun parseToken(httpRequest: HttpServletRequest): Map<String?, Claim?>? {
        return tryGetOrThrow({
            parseToken(httpRequest.getHeader(jwtProperties.header!!))
        }, BizCode.NO_AUTH)
    }
}