package com.example.model.sys

import com.example.model.base.BaseEntity
import org.ktorm.entity.Entity
import org.ktorm.ksp.api.PrimaryKey
import org.ktorm.ksp.api.Table

@Table("sys_user_role")
interface SysUserRole : BaseEntity<SysUserRole> {
    @PrimaryKey
    var id: Long
    var userId: Long
    var roleId: Long

    companion object : Entity.Factory<SysUserRole>()
}
