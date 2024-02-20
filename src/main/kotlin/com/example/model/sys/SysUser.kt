package com.example.model.sys

import com.example.model.base.BaseEntity
import org.ktorm.entity.Entity
import org.ktorm.ksp.api.PrimaryKey
import org.ktorm.ksp.api.Table

@Table("sys_user")
interface SysUser : BaseEntity<SysUser> {
    @PrimaryKey
    var id: Long

    var uid: String
    var password: String

    companion object : Entity.Factory<SysUser>()
}
