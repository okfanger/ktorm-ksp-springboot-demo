create database if not exists `ktorm-ksp-springboot-demo`;
create table if not exists sys_user
(
    id       bigint       not null auto_increment primary key comment 'id',
    uid      varchar(64)  not null comment '用户唯一id',
    password varchar(255) not null comment '密码',
    createAt datetime default CURRENT_TIMESTAMP comment '创建时间',
    updateAt datetime default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '更新时间'
) comment '用户表';

create table if not exists  sys_role
(
    id       bigint      not null auto_increment primary key comment 'id',
    name varchar(64) not null comment '角色名',
    createAt datetime default CURRENT_TIMESTAMP comment '创建时间',
    updateAt datetime default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '更新时间'
) comment '角色表';

create table if not exists  sys_user_role
(
    id       bigint      not null auto_increment primary key comment 'id',
    userId   bigint not null comment '用户id',
    roleId   bigint      not null comment '角色id',
    createAt datetime default CURRENT_TIMESTAMP comment '创建时间',
    updateAt datetime default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '更新时间'
) comment '用户角色关联表';

create table if not exists  sys_permission
(
    id       bigint      not null auto_increment primary key comment 'id',
    permName varchar(64) not null comment '权限名',
    createAt datetime default CURRENT_TIMESTAMP comment '创建时间',
    updateAt datetime default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '更新时间'
) comment '权限表';

create table if not exists  sys_role_permission
(
    id           bigint not null auto_increment primary key comment 'id',
    roleId       bigint not null comment '角色id',
    permissionId bigint not null comment '权限id',
    createAt datetime default CURRENT_TIMESTAMP comment '创建时间',
    updateAt datetime default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '更新时间'
) comment '角色权限关联表';

-- 加外键约束
alter table sys_user_role
    add constraint fk_user_role_user foreign key (userId) references sys_user (id);
alter table sys_user_role
    add constraint fk_user_role_role foreign key (roleId) references sys_role (id);
alter table sys_role_permission
    add constraint fk_role_perm_role foreign key (roleId) references sys_role (id);
alter table sys_role_permission
    add constraint fk_role_perm_perm foreign key (permissionId) references sys_permission (id);
