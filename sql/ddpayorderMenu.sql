-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('多多373订单', '2013', '1', '/system/ddpayorder', 'C', '0', 'system:ddpayorder:view', '#', 'admin', sysdate(), '', null, '多多373订单菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('多多373订单查询', @parentId, '1',  '#',  'F', '0', 'system:ddpayorder:list',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('多多373订单新增', @parentId, '2',  '#',  'F', '0', 'system:ddpayorder:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('多多373订单修改', @parentId, '3',  '#',  'F', '0', 'system:ddpayorder:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('多多373订单删除', @parentId, '4',  '#',  'F', '0', 'system:ddpayorder:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('多多373订单导出', @parentId, '5',  '#',  'F', '0', 'system:ddpayorder:export',       '#', 'admin', sysdate(), '', null, '');
