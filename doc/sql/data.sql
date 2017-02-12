--用户
insert into sys_user (ID, LOGIN_NAME, USER_PASSWORD, USER_NAME) values (1, 'admin', '$2a$12$cbYHFk8hJ17GuhIEOmuNku4iVyFRdOJejM98oxAYGcYzJSimR4tyC','admin');

--用户角色
insert into  sys_user_role (ID, USER_ID, ROLE_ID) values (1, 1, 1);

--管理员角色
INSERT INTO sys_role (ID, NAME, DESCRIPTION, ENABLED) VALUES ( 1, 'admin', '管理员角色',1 );
--权限
insert into sys_authorities(ID ,NAME, DESCRIPTION, PERMISSION) values (1, '用户管理','用户管理','user:nav');
insert into sys_authorities(ID ,NAME, DESCRIPTION, PERMISSION) values (2, '分销商管理','分销商管理','dealers:nav');
insert into sys_authorities(ID ,NAME, DESCRIPTION, PERMISSION) values (3, '客户管理','客户管理','customer:nav');
insert into sys_authorities(ID ,NAME, DESCRIPTION, PERMISSION) values (4, '产品管理','产品管理','product:nav');
insert into sys_authorities(ID ,NAME, DESCRIPTION, PERMISSION) values (5, '订单统计','产品管理','order:nav');
--角色、权限关系
insert into sys_role_authorities (id, role_id, auth_id) values (1, 1, 1);
insert into sys_role_authorities (id, role_id, auth_id) values (2, 1, 2);
insert into sys_role_authorities (id, role_id, auth_id) values (3, 1, 3);
insert into sys_role_authorities (id, role_id, auth_id) values (4, 1, 4);
insert into sys_role_authorities (id, role_id, auth_id) values (5, 1, 5);

