-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('多多373店铺', '2012', '1', '/system/ddpayshop', 'C', '0', 'system:ddpayshop:view', '#', 'admin', sysdate(), '', null, '多多373店铺菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('多多373店铺查询', @parentId, '1',  '#',  'F', '0', 'system:ddpayshop:list',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('多多373店铺新增', @parentId, '2',  '#',  'F', '0', 'system:ddpayshop:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('多多373店铺修改', @parentId, '3',  '#',  'F', '0', 'system:ddpayshop:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('多多373店铺删除', @parentId, '4',  '#',  'F', '0', 'system:ddpayshop:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('多多373店铺导出', @parentId, '5',  '#',  'F', '0', 'system:ddpayshop:export',       '#', 'admin', sysdate(), '', null, '');
