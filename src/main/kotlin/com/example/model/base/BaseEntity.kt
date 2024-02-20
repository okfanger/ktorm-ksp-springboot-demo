package com.example.model.base

import org.ktorm.entity.Entity
import java.time.LocalDateTime

interface BaseEntity<T : Entity<T>> : Entity<T> {
    var createAt: LocalDateTime
    var updateAt: LocalDateTime
}