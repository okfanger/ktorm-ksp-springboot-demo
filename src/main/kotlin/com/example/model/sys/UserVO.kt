package com.example.model.sys

import com.example.common.Need
import com.example.model.base.BaseJavaEntity
import java.time.LocalDateTime

data class UserVO(
    val id: Long,
    val uid: String,
    var roles: List<RoleVO>,

    override var createAt: LocalDateTime,
    override var updateAt: LocalDateTime,
) : BaseJavaEntity() {
    fun hasPermission(need: Need): Boolean {
        with(need) {
            if (allOf.isNotEmpty()) {
                for (it in allOf) {
                    if (!hasPermission(it))
                        return false
                }
                return true
            } else if (anyOf.isNotEmpty()) {
                for (it in anyOf) {
                    if (hasPermission(it))
                        return true
                }
                return false
            } else {
                return false
            }
        }
    }
    private fun hasPermission(permissionName: String): Boolean {
        return roles.any { roleIt ->
            roleIt.permissions.any { it.name == permissionName }
        }
    }
}

fun SysUser.toVO(roles: List<RoleVO> = emptyList()): UserVO {
    return UserVO(this.id, this.uid, roles, this.createAt, this.updateAt)
}
