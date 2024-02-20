package com.example.model.sys

data class RoleVO(
    val id: Long,
    val roleName: String,
    val permissions: List<PermissionVO>
)