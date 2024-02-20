package com.example.model.sys

import com.example.model.base.BaseJavaEntity
import java.time.LocalDateTime

data class UserVO(
    val id: Long,
    val uid: String,
    var roles: List<RoleVO>,

    override var createAt: LocalDateTime,
    override var updateAt: LocalDateTime,
) : BaseJavaEntity() {
    fun hasPermission(permissionName: String): Boolean {
        return roles.any { roleIt ->
            roleIt.permissions.any { it.name == permissionName }
        }
    }
}

fun SysUser.toVO(roles: List<RoleVO> = emptyList()): UserVO {
    return UserVO(this.id, this.uid, roles, this.createAt, this.updateAt)
}
