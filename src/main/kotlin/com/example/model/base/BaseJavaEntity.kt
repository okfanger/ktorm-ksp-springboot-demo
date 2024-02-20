package com.example.model.base

import java.time.LocalDateTime

abstract class BaseJavaEntity {
    abstract var createAt: LocalDateTime;
    abstract var updateAt: LocalDateTime;
}