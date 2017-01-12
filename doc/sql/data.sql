--用户
insert into SYS_USER (ID, LOGIN_NAME, USER_PASSWORD, USER_NAME) values (1, 'admin', '$2a$12$cbYHFk8hJ17GuhIEOmuNku4iVyFRdOJejM98oxAYGcYzJSimR4tyC','admin');
--管理员角色
INSERT INTO SYS_ROLE (ID, NAME, DESCRIPTION, ENABLED) VALUES ( 1, 'admin', '管理员角色',1 );
--权限
insert into SYS_AUTHORITIES(ID ,NAME, DESCRIPTION, PERMISSION) values (1, '用户管理','用户管理','user:nav');
insert into SYS_AUTHORITIES(ID ,NAME, DESCRIPTION, PERMISSION) values (2, '分销商管理','分销商管理','dealers:nav');
insert into SYS_AUTHORITIES(ID ,NAME, DESCRIPTION, PERMISSION) values (3, '客户管理','客户管理','customer:nav');
insert into SYS_AUTHORITIES(ID ,NAME, DESCRIPTION, PERMISSION) values (4, '产品管理','产品管理','product:nav');
--角色、权限关系
insert into SYS_ROLE_AUTHORITIES (id, role_id, auth_id) values (1, 1, 1);
insert into SYS_ROLE_AUTHORITIES (id, role_id, auth_id) values (2, 1, 2);
insert into SYS_ROLE_AUTHORITIES (id, role_id, auth_id) values (3, 1, 3);
insert into SYS_ROLE_AUTHORITIES (id, role_id, auth_id) values (4, 1, 4);

