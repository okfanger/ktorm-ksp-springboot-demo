package com.example.model.sys

import com.example.model.base.BaseEntity
import org.ktorm.entity.Entity
import org.ktorm.ksp.api.PrimaryKey
import org.ktorm.ksp.api.Table

@Table("sys_role")
interface SysRole : BaseEntity<SysRole> {
    @PrimaryKey
    var id: Long
    var roleName: String

    companion object : Entity.Factory<SysRole>()
}
