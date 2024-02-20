package com.example.model.sys

import com.example.model.base.BaseEntity
import org.ktorm.entity.Entity
import org.ktorm.ksp.api.PrimaryKey
import org.ktorm.ksp.api.Table

@Table("sys_permission")
interface SysPermission : BaseEntity<SysPermission> {
    @PrimaryKey
    var id: Long
    var name: String

    companion object : Entity.Factory<SysPermission>()
}
