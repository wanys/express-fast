/*
SQLyog Ultimate v12.5.0 (64 bit)
MySQL - 5.7.21-log 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

create table `sys_menu` (
	`menu_id` bigint (20),
	`parent_id` bigint (20),
	`name` varchar (150),
	`url` varchar (600),
	`perms` varchar (1500),
	`type` int (11),
	`icon` varchar (150),
	`order_num` int (11)
); 
insert into `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('1','0','系统管理',NULL,NULL,'0','system','0');
insert into `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('2','1','管理员列表','sys/user',NULL,'1','admin','1');
insert into `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('3','1','角色管理','sys/role',NULL,'1','role','2');
insert into `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('4','1','菜单管理','sys/menu',NULL,'1','menu','3');
insert into `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('5','1','SQL监控','http://localhost:8080/renren-fast/druid/sql.html',NULL,'1','sql','4');
insert into `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('6','1','定时任务','job/schedule',NULL,'1','job','5');
insert into `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('7','6','查看',NULL,'sys:schedule:list,sys:schedule:info','2',NULL,'0');
insert into `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('8','6','新增',NULL,'sys:schedule:save','2',NULL,'0');
insert into `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('9','6','修改',NULL,'sys:schedule:update','2',NULL,'0');
insert into `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('10','6','删除',NULL,'sys:schedule:delete','2',NULL,'0');
insert into `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('11','6','暂停',NULL,'sys:schedule:pause','2',NULL,'0');
insert into `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('12','6','恢复',NULL,'sys:schedule:resume','2',NULL,'0');
insert into `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('13','6','立即执行',NULL,'sys:schedule:run','2',NULL,'0');
insert into `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('14','6','日志列表',NULL,'sys:schedule:log','2',NULL,'0');
insert into `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('15','2','查看',NULL,'sys:user:list,sys:user:info','2',NULL,'0');
insert into `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('16','2','新增',NULL,'sys:user:save,sys:role:select','2',NULL,'0');
insert into `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('17','2','修改',NULL,'sys:user:update,sys:role:select','2',NULL,'0');
insert into `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('18','2','删除',NULL,'sys:user:delete','2',NULL,'0');
insert into `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('19','3','查看',NULL,'sys:role:list,sys:role:info','2',NULL,'0');
insert into `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('20','3','新增',NULL,'sys:role:save,sys:menu:list','2',NULL,'0');
insert into `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('21','3','修改',NULL,'sys:role:update,sys:menu:list','2',NULL,'0');
insert into `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('22','3','删除',NULL,'sys:role:delete','2',NULL,'0');
insert into `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('23','4','查看',NULL,'sys:menu:list,sys:menu:info','2',NULL,'0');
insert into `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('24','4','新增',NULL,'sys:menu:save,sys:menu:select','2',NULL,'0');
insert into `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('25','4','修改',NULL,'sys:menu:update,sys:menu:select','2',NULL,'0');
insert into `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('26','4','删除',NULL,'sys:menu:delete','2',NULL,'0');
insert into `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('27','1','参数管理','sys/config','sys:config:list,sys:config:info,sys:config:save,sys:config:update,sys:config:delete','1','config','6');
insert into `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('29','1','系统日志','sys/log','sys:log:list','1','log','7');
insert into `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('30','1','文件上传','oss/oss','sys:oss:all','1','oss','6');
insert into `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('31','1','订单表','express/order',NULL,'1','config','6');
insert into `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('32','31','查看',NULL,'express:order:list,express:order:info','2',NULL,'6');
insert into `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('33','31','新增',NULL,'express:order:save','2',NULL,'6');
insert into `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('34','31','修改',NULL,'express:order:update','2',NULL,'6');
insert into `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('36','1','任务表','express/task',NULL,'1','config','6');
insert into `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('37','36','查看',NULL,'express:task:list,express:task:info','2',NULL,'6');
insert into `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('38','36','新增',NULL,'express:task:save','2',NULL,'6');
insert into `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('39','36','修改',NULL,'express:task:update','2',NULL,'6');
insert into `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('40','36','删除',NULL,'express:task:delete','2',NULL,'6');
insert into `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('46','1','快递员评价表','express/comment',NULL,'1','config','6');
insert into `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('47','46','查看',NULL,'express:comment:list,express:comment:info','2',NULL,'6');
insert into `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('48','46','新增',NULL,'express:comment:save','2',NULL,'6');
insert into `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('49','46','修改',NULL,'express:comment:update','2',NULL,'6');
insert into `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('50','46','删除',NULL,'express:comment:delete','2',NULL,'6');
insert into `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('51','1','快递公司表','express/expresscompany',NULL,'1','config','6');
insert into `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('52','51','查看',NULL,'express:expresscompany:list,express:expresscompany:info','2',NULL,'6');
insert into `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('53','51','新增',NULL,'express:expresscompany:save','2',NULL,'6');
insert into `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('54','51','修改',NULL,'express:expresscompany:update','2',NULL,'6');
insert into `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('55','51','删除',NULL,'express:expresscompany:delete','2',NULL,'6');
insert into `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('56','1','用户表','express/suser',NULL,'1','config','6');
insert into `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('57','56','查看',NULL,'express:suser:list,express:suser:info','2',NULL,'6');
insert into `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('58','56','新增',NULL,'express:suser:save','2',NULL,'6');
insert into `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('59','56','修改',NULL,'express:suser:update','2',NULL,'6');
insert into `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('60','56','删除',NULL,'express:suser:delete','2',NULL,'6');
insert into `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('61','36','分配','','express:task:allocation','2','','0');
insert into `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('62','1','快递单二维码打印','express/qrcode','','1','duanxin','6');
insert into `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) values('63','62','打印',NULL,'express:qrcode:print','2',NULL,'6');
