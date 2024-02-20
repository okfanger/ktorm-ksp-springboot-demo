package com.example.model.sys

data class RoleVO(
    val id: Long,
    val name: String,
    val permissions: List<PermissionVO>
)