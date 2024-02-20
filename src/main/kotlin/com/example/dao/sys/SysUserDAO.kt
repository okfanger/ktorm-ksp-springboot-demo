package com.example.dao.sys

import com.example.dao.base.BaseDAO
import com.example.model.sys.*
import org.ktorm.dsl.*
import org.springframework.stereotype.Repository

@Repository
class SysUserDAO : BaseDAO<SysUser, SysUsers>(SysUsers) {

    fun getRoleVOsByUserIds(ids: List<Long>): List<RoleVO> {
        return getRolesByUserIds(ids).let { roleList ->
            val roleIds = roleList.map { it.id }
            val rolePermissionsMap = getRolePermissionsMapByRoleIds(roleIds)
            roleList.map { role ->
                val permissions = rolePermissionsMap[role.id]?.map { permission ->
                    PermissionVO(permission.id, permission.name)
                } ?: emptyList()
                RoleVO(role.id, role.name, permissions)
            }
        }
    }

    private fun getRolesByUserIds(ids: List<Long>): List<SysRole> {
        return database.from(SysUserRoles)
            .leftJoin(SysRoles, SysUserRoles.roleId eq SysRoles.id)
            .select(SysRoles.columns)
            .where { SysUserRoles.userId inList ids }
            .map { SysRoles.createEntity(it) }
    }


    private fun getRolePermissionsMapByRoleIds(roleIds: List<Long>): Map<Long, List<SysPermission>> {
        return database.from(SysRolePermissions)
            .leftJoin(SysPermissions, SysRolePermissions.permissionId eq SysPermissions.id)
            .selectDistinct(SysRolePermissions.roleId, SysPermissions.name, SysPermissions.id)
            .where { SysRolePermissions.roleId inList roleIds }
            .map { it[SysRolePermissions.roleId] to SysPermissions.createEntity(it) }
            .toList()
            .map { (it.first ?: 0L) to it.second }
            .let { pairs ->
                pairs.groupBy({ it.first }, { it.second })
            }
    }
}