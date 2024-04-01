insert into mast_account (id,ma_user_id,ma_username,update_user_id) values (1,9999,'admin',9999);

INSERT INTO "public"."sys_menu" ("sm_id", "sm_parent_id", "sm_type", "sm_path", "sm_state", "sm_icon", "sm_name", "sm_code", "sm_level_chain", "sm_level", "sm_sort", "sm_remark", "create_time", "update_time", "create_account_id", "update_account_id", "sm_enable_edit", "sm_enable_delete")
VALUES (  1, NULL, 1, '/dashboard', 1, 'DashboardOutlined', '{"zh_CN": "工作台"}', 'dashboard', '0', 1, 1, NULL, '2024-03-28 12:27:04+09', '2024-03-28 12:27:04+09', 'AUTO', 'AUTO', 'f', 'f'),
    (  3, 2, 1, '/user-manage', 1, 'UserOutlined', '{"zh_CN": "用户管理"}', 'system:user', '0.2', 2, 1, NULL, '2024-03-28 13:01:43+09', '2024-03-28 13:01:43+09', 'AUTO', 'AUTO', 'f', 'f'),
    (  2, NULL, 1, NULL, 1, 'ProductOutlined', '{"zh_CN": "系统管理"}', 'system', '0', 1, 2, NULL, '2024-03-28 12:34:58+09', '2024-03-28 12:34:58+09', 'AUTO', 'AUTO', 'f', 'f'),
    (  4, 3, 2, NULL, 1, NULL, '{"zh_CN": "查看"}', 'system:user@read', '0.2.3', 3, 1, NULL, '2024-03-28 13:11:37+09', '2024-03-28 13:11:37+09', 'AUTO', 'AUTO', 'f', 'f'),
    (  5, 3, 2, NULL, 1, NULL, '{"zh_CN": "用户数据导入"}', 'system:user@import', '0.2.3', 3, 2, NULL, '2024-03-28 13:16:08+09', '2024-03-28 13:16:08+09', 'AUTO', 'AUTO', 'f', 'f'),
    (  6, 3, 2, NULL, 1, NULL, '{"zh_CN": "编辑"}', 'system:user@update', '0.2.3', 3, 3, NULL, '2024-03-28 13:17:21+09', '2024-03-28 13:17:21+09', 'AUTO', 'AUTO', 'f', 'f'),
    (  7, 3, 2, NULL, 1, NULL, '{"zh_CN": "删除"}', 'system:user@delete', '0.2.3', 3, 4, NULL, '2024-03-28 13:18:22+09', '2024-03-28 13:18:22+09', 'AUTO', 'AUTO', 'f', 'f'),
    (  8, 2, 1, '/role-manage', 1, 'TeamOutlined', '{"zh_CN": "角色管理"}', 'system:role', '0.2', 2, 2, NULL, '2024-03-28 13:26:43+09', '2024-03-28 13:26:43+09', 'AUTO', 'AUTO', 'f', 'f'),
    (  9, 8, 2, NULL, 1, NULL, '{"zh_CN": "查看"}', 'system:role@read', '0.2.8', 3, 1, NULL, '2024-03-28 13:28:48+09', '2024-03-28 13:28:48+09', 'AUTO', 'AUTO', 'f', 'f'),
    (  10, 8, 2, NULL, 1, NULL, '{"zh_CN": "新增"}', 'system:role@create', '0.2.8', 3, 2, NULL, '2024-03-28 13:31:11+09', '2024-03-28 13:31:11+09', 'AUTO', 'AUTO', 'f', 'f'),
    (  11, 8, 2, NULL, 1, NULL, '{"zh_CN": "编辑"}', 'system:role@update', '0.2.8', 3, 3, NULL, '2024-03-28 13:32:13+09', '2024-03-28 13:32:13+09', 'AUTO', 'AUTO', 'f', 'f'),
    (  12, 8, 2, NULL, 1, NULL, '{"zh_CN": "设置权限"}', 'system:role@config', '0.2.8', 3, 4, NULL, '2024-03-28 13:33:29+09', '2024-03-28 13:33:29+09', 'AUTO', 'AUTO', 'f', 'f'),
    (  13, 8, 2, NULL, 1, NULL, '{"zh_CN": "删除"}', 'system:role@delete', '0.2.8', 3, 5, NULL, '2024-03-28 13:34:08+09', '2024-03-28 13:34:08+09', 'AUTO', 'AUTO', 'f', 'f'),
    (  14, NULL, 1, '/dept-manage', 1, 'ApartmentOutlined', '{"zh_CN": "部门管理"}', 'system:dept', '0', 1, 3, NULL, '2024-03-28 13:42:18+09', '2024-03-28 13:42:18+09', 'AUTO', 'AUTO', 'f', 'f'),
    (  15, 14, 2, NULL, 1, NULL, '{"zh_CN": "查看"}', 'system:dept@read', '0.14', 2, 1, NULL, '2024-03-28 13:49:49+09', '2024-03-28 13:49:49+09', 'AUTO', 'AUTO', 'f', 'f'),
    (  16, 14, 2, NULL, 1, NULL, '{"zh_CN": "部门数据导入"}', 'system:dept@import', '0.14', 2, 2, NULL, '2024-03-28 14:02:55+09', '2024-03-28 14:02:55+09', 'AUTO', 'AUTO', 'f', 'f'),
    (  17, 2, 1, '/menu-manage', 1, 'MenuOutlined', '{"zh_CN": "菜单管理"}', 'system:menu', '0.2', 2, 4, NULL, '2024-03-28 14:15:28+09', '2024-03-28 14:15:28+09', 'AUTO', 'AUTO', 'f', 'f'),
    (  18, 17, 2, NULL, 1, NULL, '{"zh_CN": "查看"}', 'system:menu@read', '0.2.17', 3, 1, NULL, '2024-03-28 14:17:57+09', '2024-03-28 14:17:57+09', 'AUTO', 'AUTO', 'f', 'f'),
    (  19, 17, 2, NULL, 1, NULL, '{"zh_CN": "新增"}', 'system:menu@create', '0.2.17', 3, 2, NULL, '2024-03-28 14:20:21+09', '2024-03-28 14:20:21+09', 'AUTO', 'AUTO', 'f', 'f'),
    (  20, 17, 2, NULL, 1, NULL, '{"zh_CN": "编辑"}', 'system:menu@update', '0.2.17', 3, 3, NULL, '2024-03-28 14:21:12+09', '2024-03-28 14:21:12+09', 'AUTO', 'AUTO', 'f', 'f'),
    (  21, 17, 2, NULL, 1, NULL, '{"zh_CN": "删除"}', 'system:menu@delete', '0.2.17', 3, 4, NULL, '2024-03-28 14:22:21+09', '2024-03-28 14:22:21+09', 'AUTO', 'AUTO', 'f', 'f'),
    (  22, 2, 1, '/log-manage', 1, NULL, '{"zh_CN": "日志管理"}', 'system:log', '0.2', 2, 5, NULL, '2024-03-28 14:24:44+09', '2024-03-28 14:24:44+09', 'AUTO', 'AUTO', 'f', 'f'),
    (  23, 22, 2, NULL, 1, NULL, '{"zh_CN": "查看"}', 'system:log@read', '0.2.22', 3, 1, NULL, '2024-03-28 14:28:36+09', '2024-03-28 14:28:36+09', 'AUTO', 'AUTO', 'f', 'f'),
    (  24, 22, 2, NULL, 1, NULL, '{"zh_CN": "删除"}', 'system:log@delete', '0.2.22', 3, 2, NULL, '2024-03-28 14:29:22+09', '2024-03-28 14:29:22+09', 'AUTO', 'AUTO', 'f', 'f'),
    (  25, 22, 2, NULL, 1, NULL, '{"zh_CN": "导出日志数据"}', 'system:log@export', '0.2.22', 3, 3, NULL, '2024-03-28 14:30:28+09', '2024-03-28 14:30:28+09', 'AUTO', 'AUTO', 'f', 'f');

