package com.example.model.sys

import com.example.model.base.BaseEntity
import org.ktorm.entity.Entity
import org.ktorm.ksp.api.PrimaryKey
import org.ktorm.ksp.api.Table

@Table("sys_role_permission")
interface SysRolePermission : BaseEntity<SysRolePermission> {
    @PrimaryKey
    var id: Long
    var roleId: Long
    var permissionId: Long

    companion object : Entity.Factory<SysRolePermission>()
}
