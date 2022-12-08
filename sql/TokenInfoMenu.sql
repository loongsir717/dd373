-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('tokenInfo', '2014', '1', '/system/tokenInfo', 'C', '0', 'system:tokenInfo:view', '#', 'admin', sysdate(), '', null, 'tokenInfo菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('tokenInfo查询', @parentId, '1',  '#',  'F', '0', 'system:tokenInfo:list',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('tokenInfo新增', @parentId, '2',  '#',  'F', '0', 'system:tokenInfo:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('tokenInfo修改', @parentId, '3',  '#',  'F', '0', 'system:tokenInfo:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('tokenInfo删除', @parentId, '4',  '#',  'F', '0', 'system:tokenInfo:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('tokenInfo导出', @parentId, '5',  '#',  'F', '0', 'system:tokenInfo:export',       '#', 'admin', sysdate(), '', null, '');
