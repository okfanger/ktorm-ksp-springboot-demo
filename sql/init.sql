create table sys_user
(
    id       bigint auto_increment comment 'id'
        primary key,
    uid      varchar(64)                        not null comment '用户唯一id',
    password varchar(255)                       not null comment '密码',
    createAt datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updateAt datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间'
)
    comment '用户表';

INSERT INTO sys_user (id, uid, password, createAt, updateAt)
VALUES (1, 'okfang', '12345', '2024-02-20 23:51:30', '2024-02-20 23:51:38');

create table sys_role
(
    id       bigint auto_increment comment 'id'
        primary key,
    name     varchar(64)                        not null comment '角色名',
    createAt datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updateAt datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间'
)
    comment '角色表';

INSERT INTO sys_role (id, name, createAt, updateAt)
VALUES (1, 'admin', '2024-02-20 23:51:43', '2024-02-20 23:51:43');

create table sys_permission
(
    id       bigint auto_increment comment 'id'
        primary key,
    name     varchar(64)                        not null comment '权限名',
    createAt datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updateAt datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间'
)
    comment '权限表';

INSERT INTO sys_permission (id, name, createAt, updateAt)
VALUES (1, 'auth:info', '2024-02-20 23:52:37', '2024-02-20 23:52:37');
create table sys_role_permission
(
    id           bigint auto_increment comment 'id'
        primary key,
    roleId       bigint                             not null comment '角色id',
    permissionId bigint                             not null comment '权限id',
    createAt     datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updateAt     datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint fk_role_perm_perm
        foreign key (permissionId) references sys_permission (id),
    constraint fk_role_perm_role
        foreign key (roleId) references sys_role (id)
)
    comment '角色权限关联表';

INSERT INTO sys_role_permission (id, roleId, permissionId, createAt, updateAt)
VALUES (2, 1, 1, '2024-02-20 23:52:42', '2024-02-20 23:52:42');
create table sys_user_role
(
    id       bigint auto_increment comment 'id'
        primary key,
    userId   bigint                             not null comment '用户id',
    roleId   bigint                             not null comment '角色id',
    createAt datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updateAt datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint fk_user_role_role
        foreign key (roleId) references sys_role (id),
    constraint fk_user_role_user
        foreign key (userId) references sys_user (id)
)
    comment '用户角色关联表';

INSERT INTO sys_user_role (id, userId, roleId, createAt, updateAt)
VALUES (1, 1, 1, '2024-02-20 23:51:49', '2024-02-20 23:51:49');
