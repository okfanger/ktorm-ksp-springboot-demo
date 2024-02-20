package com.example.common

import org.springframework.web.bind.annotation.RequestMapping
import java.lang.annotation.Inherited

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
@Inherited
annotation class Need(
    val anyOf: Array<String> = [],
    val allOf: Array<String> = []
)