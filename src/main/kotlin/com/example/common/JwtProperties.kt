package com.example.common

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "jwt")
data class JwtProperties(
    var header: String? = null,
    var tokenHead: String? = null,
    var secret: String? = null,
)