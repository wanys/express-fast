/*
SQLyog Ultimate v12.5.0 (64 bit)
MySQL - 5.7.21-log : Database - renren_fast
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`renren_fast` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `renren_fast`;

/*Table structure for table `工作量统计表` */

DROP TABLE IF EXISTS `工作量统计表`;

CREATE TABLE `工作量统计表` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `快递员id` int(11) DEFAULT NULL COMMENT '快递员ID',
  `收件量` int(25) DEFAULT NULL COMMENT '揽件量',
  `发件量` int(25) DEFAULT NULL COMMENT '派件量',
  `创建时间` datetime DEFAULT NULL COMMENT '创建时间',
  `extend` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='工作量统计';

/*Data for the table `工作量统计表` */

/*Table structure for table `qrtz_blob_triggers` */

DROP TABLE IF EXISTS `qrtz_blob_triggers`;

CREATE TABLE `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `qrtz_blob_triggers` */

/*Table structure for table `qrtz_calendars` */

DROP TABLE IF EXISTS `qrtz_calendars`;

CREATE TABLE `qrtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `qrtz_calendars` */

/*Table structure for table `qrtz_cron_triggers` */

DROP TABLE IF EXISTS `qrtz_cron_triggers`;

CREATE TABLE `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(120) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `qrtz_cron_triggers` */

insert  into `qrtz_cron_triggers`(`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`CRON_EXPRESSION`,`TIME_ZONE_ID`) values 
('RenrenScheduler','TASK_1','DEFAULT','0 0/30 * * * ?','Asia/Shanghai');

/*Table structure for table `qrtz_fired_triggers` */

DROP TABLE IF EXISTS `qrtz_fired_triggers`;

CREATE TABLE `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`) USING BTREE,
  KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`) USING BTREE,
  KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`) USING BTREE,
  KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `qrtz_fired_triggers` */

/*Table structure for table `qrtz_job_details` */

DROP TABLE IF EXISTS `qrtz_job_details`;

CREATE TABLE `qrtz_job_details` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`) USING BTREE,
  KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `qrtz_job_details` */

insert  into `qrtz_job_details`(`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`,`DESCRIPTION`,`JOB_CLASS_NAME`,`IS_DURABLE`,`IS_NONCONCURRENT`,`IS_UPDATE_DATA`,`REQUESTS_RECOVERY`,`JOB_DATA`) values 
('RenrenScheduler','TASK_1','DEFAULT',NULL,'io.renren.modules.job.utils.ScheduleJob','0','0','0','0','��\0sr\0org.quartz.JobDataMap���迩��\0\0xr\0&org.quartz.utils.StringKeyDirtyFlagMap�����](\0Z\0allowsTransientDataxr\0org.quartz.utils.DirtyFlagMap�.�(v\n�\0Z\0dirtyL\0mapt\0Ljava/util/Map;xpsr\0java.util.HashMap���`�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\rJOB_PARAM_KEYsr\0.io.renren.modules.job.entity.ScheduleJobEntity\0\0\0\0\0\0\0\0L\0beanNamet\0Ljava/lang/String;L\0\ncreateTimet\0Ljava/util/Date;L\0cronExpressionq\0~\0	L\0jobIdt\0Ljava/lang/Long;L\0paramsq\0~\0	L\0remarkq\0~\0	L\0statust\0Ljava/lang/Integer;xpt\0testTasksr\0java.util.Datehj�KYt\0\0xpw\0\0m%%J�xt\00 0/30 * * * ?sr\0java.lang.Long;��̏#�\0J\0valuexr\0java.lang.Number������\0\0xp\0\0\0\0\0\0\0t\0renrent\0参数测试sr\0java.lang.Integer⠤���8\0I\0valuexq\0~\0\0\0\0\0x\0');

/*Table structure for table `qrtz_locks` */

DROP TABLE IF EXISTS `qrtz_locks`;

CREATE TABLE `qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `qrtz_locks` */

insert  into `qrtz_locks`(`SCHED_NAME`,`LOCK_NAME`) values 
('RenrenScheduler','STATE_ACCESS'),
('RenrenScheduler','TRIGGER_ACCESS');

/*Table structure for table `qrtz_paused_trigger_grps` */

DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;

CREATE TABLE `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `qrtz_paused_trigger_grps` */

/*Table structure for table `qrtz_scheduler_state` */

DROP TABLE IF EXISTS `qrtz_scheduler_state`;

CREATE TABLE `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `qrtz_scheduler_state` */

insert  into `qrtz_scheduler_state`(`SCHED_NAME`,`INSTANCE_NAME`,`LAST_CHECKIN_TIME`,`CHECKIN_INTERVAL`) values 
('RenrenScheduler','MWWComputer1574260825084',1574260958826,15000);

/*Table structure for table `qrtz_simple_triggers` */

DROP TABLE IF EXISTS `qrtz_simple_triggers`;

CREATE TABLE `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `qrtz_simple_triggers` */

/*Table structure for table `qrtz_simprop_triggers` */

DROP TABLE IF EXISTS `qrtz_simprop_triggers`;

CREATE TABLE `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `qrtz_simprop_triggers` */

/*Table structure for table `qrtz_triggers` */

DROP TABLE IF EXISTS `qrtz_triggers`;

CREATE TABLE `qrtz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`) USING BTREE,
  KEY `IDX_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`) USING BTREE,
  KEY `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`) USING BTREE,
  KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`) USING BTREE,
  KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`) USING BTREE,
  KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`) USING BTREE,
  KEY `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`) USING BTREE,
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`) USING BTREE,
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`) USING BTREE,
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `qrtz_triggers` */

insert  into `qrtz_triggers`(`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`JOB_NAME`,`JOB_GROUP`,`DESCRIPTION`,`NEXT_FIRE_TIME`,`PREV_FIRE_TIME`,`PRIORITY`,`TRIGGER_STATE`,`TRIGGER_TYPE`,`START_TIME`,`END_TIME`,`CALENDAR_NAME`,`MISFIRE_INSTR`,`JOB_DATA`) values 
('RenrenScheduler','TASK_1','DEFAULT','TASK_1','DEFAULT',NULL,1574262000000,-1,5,'WAITING','CRON',1568287660000,0,NULL,2,'��\0sr\0org.quartz.JobDataMap���迩��\0\0xr\0&org.quartz.utils.StringKeyDirtyFlagMap�����](\0Z\0allowsTransientDataxr\0org.quartz.utils.DirtyFlagMap�.�(v\n�\0Z\0dirtyL\0mapt\0Ljava/util/Map;xpsr\0java.util.HashMap���`�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\rJOB_PARAM_KEYsr\0.io.renren.modules.job.entity.ScheduleJobEntity\0\0\0\0\0\0\0\0L\0beanNamet\0Ljava/lang/String;L\0\ncreateTimet\0Ljava/util/Date;L\0cronExpressionq\0~\0	L\0jobIdt\0Ljava/lang/Long;L\0paramsq\0~\0	L\0remarkq\0~\0	L\0statust\0Ljava/lang/Integer;xpt\0testTasksr\0java.util.Datehj�KYt\0\0xpw\0\0m%%J�xt\00 0/30 * * * ?sr\0java.lang.Long;��̏#�\0J\0valuexr\0java.lang.Number������\0\0xp\0\0\0\0\0\0\0t\0renrent\0参数测试sr\0java.lang.Integer⠤���8\0I\0valuexq\0~\0\0\0\0\0x\0');

/*Table structure for table `schedule_job` */

DROP TABLE IF EXISTS `schedule_job`;

CREATE TABLE `schedule_job` (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务id',
  `bean_name` varchar(200) DEFAULT NULL COMMENT 'spring bean名称',
  `params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `cron_expression` varchar(100) DEFAULT NULL COMMENT 'cron表达式',
  `status` tinyint(4) DEFAULT NULL COMMENT '任务状态  0：正常  1：暂停',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='定时任务';

/*Data for the table `schedule_job` */

insert  into `schedule_job`(`job_id`,`bean_name`,`params`,`cron_expression`,`status`,`remark`,`create_time`) values 
(1,'testTask','renren','0 0/30 * * * ?',0,'参数测试','2019-09-12 19:04:24');

/*Table structure for table `schedule_job_log` */

DROP TABLE IF EXISTS `schedule_job_log`;

CREATE TABLE `schedule_job_log` (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志id',
  `job_id` bigint(20) NOT NULL COMMENT '任务id',
  `bean_name` varchar(200) DEFAULT NULL COMMENT 'spring bean名称',
  `params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `status` tinyint(4) NOT NULL COMMENT '任务状态    0：成功    1：失败',
  `error` varchar(2000) DEFAULT NULL COMMENT '失败信息',
  `times` int(11) NOT NULL COMMENT '耗时(单位：毫秒)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`log_id`) USING BTREE,
  KEY `job_id` (`job_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='定时任务日志';

/*Data for the table `schedule_job_log` */

insert  into `schedule_job_log`(`log_id`,`job_id`,`bean_name`,`params`,`status`,`error`,`times`,`create_time`) values 
(1,1,'testTask','renren',0,NULL,0,'2019-09-12 19:30:00'),
(2,1,'testTask','renren',0,NULL,4,'2019-09-12 20:00:00'),
(3,1,'testTask','renren',0,NULL,1,'2019-09-12 20:30:00'),
(4,1,'testTask','renren',0,NULL,2,'2019-09-12 21:00:00'),
(5,1,'testTask','renren',0,NULL,1,'2019-09-12 21:30:00'),
(6,1,'testTask','renren',0,NULL,7,'2019-09-15 00:00:00'),
(7,1,'testTask','renren',0,NULL,2,'2019-09-15 00:30:00'),
(8,1,'testTask','renren',0,NULL,4,'2019-09-15 09:00:00'),
(9,1,'testTask','renren',0,NULL,2,'2019-09-15 09:30:00'),
(10,1,'testTask','renren',0,NULL,3,'2019-09-15 10:00:00'),
(11,1,'testTask','renren',0,NULL,1,'2019-09-15 10:30:00'),
(12,1,'testTask','renren',0,NULL,3,'2019-09-15 11:00:00'),
(13,1,'testTask','renren',0,NULL,6,'2019-09-15 15:00:00'),
(14,1,'testTask','renren',0,NULL,3,'2019-09-15 15:30:00'),
(15,1,'testTask','renren',0,NULL,2,'2019-09-15 16:00:00'),
(16,1,'testTask','renren',0,NULL,1,'2019-09-15 20:00:00'),
(17,1,'testTask','renren',0,NULL,5,'2019-09-16 10:00:00'),
(18,1,'testTask','renren',0,NULL,1,'2019-09-16 10:30:00'),
(19,1,'testTask','renren',0,NULL,1,'2019-09-16 11:00:00'),
(20,1,'testTask','renren',0,NULL,2,'2019-09-16 11:30:00'),
(21,1,'testTask','renren',0,NULL,1,'2019-09-16 12:00:00'),
(22,1,'testTask','renren',0,NULL,1,'2019-09-16 13:00:00'),
(23,1,'testTask','renren',0,NULL,4,'2019-09-16 13:30:00'),
(24,1,'testTask','renren',0,NULL,4,'2019-09-16 15:00:00'),
(25,1,'testTask','renren',0,NULL,2,'2019-09-16 15:30:00'),
(26,1,'testTask','renren',0,NULL,3,'2019-09-16 16:00:00'),
(27,1,'testTask','renren',0,NULL,2,'2019-09-16 16:30:00'),
(28,1,'testTask','renren',0,NULL,2,'2019-09-16 17:00:00'),
(29,1,'testTask','renren',0,NULL,2,'2019-09-16 19:00:00'),
(30,1,'testTask','renren',0,NULL,2,'2019-09-16 19:30:00'),
(31,1,'testTask','renren',0,NULL,4,'2019-09-16 20:00:00'),
(32,1,'testTask','renren',0,NULL,2,'2019-09-16 20:30:00'),
(33,1,'testTask','renren',0,NULL,47,'2019-09-16 21:00:01'),
(34,1,'testTask','renren',0,NULL,3,'2019-09-16 21:30:00'),
(35,1,'testTask','renren',0,NULL,6,'2019-09-16 22:00:00'),
(36,1,'testTask','renren',0,NULL,1,'2019-09-16 22:30:00'),
(37,1,'testTask','renren',0,NULL,2,'2019-09-17 21:30:00'),
(38,1,'testTask','renren',0,NULL,97,'2019-09-17 22:00:02'),
(39,1,'testTask','renren',0,NULL,59,'2019-09-17 22:30:00'),
(40,1,'testTask','renren',0,NULL,174,'2019-09-17 23:00:00'),
(41,1,'testTask','renren',0,NULL,1,'2019-09-19 18:30:00'),
(42,1,'testTask','renren',0,NULL,1,'2019-09-19 19:00:00'),
(43,1,'testTask','renren',0,NULL,11,'2019-09-19 20:30:00'),
(44,1,'testTask','renren',0,NULL,114,'2019-09-21 17:30:04'),
(45,1,'testTask','renren',0,NULL,22,'2019-09-21 21:00:00'),
(46,1,'testTask','renren',0,NULL,2,'2019-09-22 10:30:17'),
(47,1,'testTask','renren',0,NULL,18,'2019-09-22 15:30:01'),
(48,1,'testTask','renren',0,NULL,125,'2019-09-22 16:00:00'),
(49,1,'testTask','renren',0,NULL,19,'2019-09-22 17:00:00'),
(50,1,'testTask','renren',0,NULL,2,'2019-09-22 18:00:01'),
(51,1,'testTask','renren',0,NULL,50,'2019-09-24 21:30:01'),
(52,1,'testTask','renren',0,NULL,2,'2019-09-25 21:30:00'),
(53,1,'testTask','renren',0,NULL,2,'2019-09-25 22:00:00'),
(54,1,'testTask','renren',0,NULL,4,'2019-09-26 21:00:01'),
(55,1,'testTask','renren',0,NULL,44,'2019-09-26 21:30:00'),
(56,1,'testTask','renren',0,NULL,4,'2019-09-28 11:30:00'),
(57,1,'testTask','renren',0,NULL,439,'2019-09-28 16:00:07'),
(58,1,'testTask','renren',0,NULL,148,'2019-09-28 17:00:01'),
(59,1,'testTask','renren',0,NULL,9,'2019-09-28 17:30:00'),
(60,1,'testTask','renren',0,NULL,2,'2019-09-28 20:00:00'),
(61,1,'testTask','renren',0,NULL,333,'2019-10-07 20:00:07'),
(62,1,'testTask','renren',0,NULL,2,'2019-10-07 21:00:00'),
(63,1,'testTask','renren',0,NULL,4,'2019-10-07 21:30:00'),
(64,1,'testTask','renren',0,NULL,3,'2019-10-07 22:30:00'),
(65,1,'testTask','renren',0,NULL,2,'2019-10-08 21:30:00'),
(66,1,'testTask','renren',0,NULL,2,'2019-10-08 22:00:00'),
(67,1,'testTask','renren',0,NULL,4,'2019-10-08 22:30:00'),
(68,1,'testTask','renren',0,NULL,52,'2019-10-09 20:30:02'),
(69,1,'testTask','renren',0,NULL,12,'2019-10-09 21:00:00'),
(70,1,'testTask','renren',0,NULL,35,'2019-10-09 21:30:01'),
(71,1,'testTask','renren',0,NULL,3,'2019-10-09 22:00:00'),
(72,1,'testTask','renren',0,NULL,170,'2019-10-09 22:30:01'),
(73,1,'testTask','renren',0,NULL,2,'2019-11-01 20:30:00'),
(74,1,'testTask','renren',0,NULL,36,'2019-11-01 21:00:00'),
(75,1,'testTask','renren',0,NULL,2,'2019-11-01 21:30:00'),
(76,1,'testTask','renren',0,NULL,1,'2019-11-17 20:30:00'),
(77,1,'testTask','renren',0,NULL,1,'2019-11-18 21:00:00'),
(78,1,'testTask','renren',0,NULL,1,'2019-11-19 20:00:00'),
(79,1,'testTask','renren',0,NULL,22,'2019-11-19 21:00:01'),
(80,1,'testTask','renren',0,NULL,409,'2019-11-19 21:30:01'),
(81,1,'testTask','renren',0,NULL,1,'2019-11-19 22:00:00'),
(82,1,'testTask','renren',0,NULL,2,'2019-11-20 21:00:00'),
(83,1,'testTask','renren',0,NULL,1,'2019-11-20 21:30:00');

/*Table structure for table `sys_captcha` */

DROP TABLE IF EXISTS `sys_captcha`;

CREATE TABLE `sys_captcha` (
  `uuid` char(36) NOT NULL COMMENT 'uuid',
  `code` varchar(6) NOT NULL COMMENT '验证码',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`uuid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统验证码';

/*Data for the table `sys_captcha` */

insert  into `sys_captcha`(`uuid`,`code`,`expire_time`) values 
('12','cnb5d','2019-09-16 22:00:04'),
('123123','6nyx7','2019-09-16 21:55:24'),
('31d1bee4-52a8-4c9f-890a-58eea223ed48','7ngnw','2019-11-20 22:05:46'),
('363ebd9b-eb21-4438-8514-ca76ad1f017a','646gb','2019-09-16 21:35:35'),
('62fc2c1e-8318-4be9-81fe-7bf35e1a3326','5n67x','2019-10-07 19:42:41'),
('c5fec7f3-3ae9-47f3-8655-39f62d675148','e7dn5','2019-09-19 20:26:34'),
('dd7ad37b-3909-4594-880f-2e62bd72bc31','pggwe','2019-10-07 21:28:58');

/*Table structure for table `sys_config` */

DROP TABLE IF EXISTS `sys_config`;

CREATE TABLE `sys_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `param_key` varchar(50) DEFAULT NULL COMMENT 'key',
  `param_value` varchar(2000) DEFAULT NULL COMMENT 'value',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态   0：隐藏   1：显示',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `param_key` (`param_key`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统配置信息表';

/*Data for the table `sys_config` */

insert  into `sys_config`(`id`,`param_key`,`param_value`,`status`,`remark`) values 
(1,'CLOUD_STORAGE_CONFIG_KEY','{\"aliyunAccessKeyId\":\"\",\"aliyunAccessKeySecret\":\"\",\"aliyunBucketName\":\"\",\"aliyunDomain\":\"\",\"aliyunEndPoint\":\"\",\"aliyunPrefix\":\"\",\"qcloudBucketName\":\"\",\"qcloudDomain\":\"\",\"qcloudPrefix\":\"\",\"qcloudSecretId\":\"\",\"qcloudSecretKey\":\"\",\"qiniuAccessKey\":\"NrgMfABZxWLo5B-YYSjoE8-AZ1EISdi1Z3ubLOeZ\",\"qiniuBucketName\":\"ios-app\",\"qiniuDomain\":\"http://7xqbwh.dl1.z0.glb.clouddn.com\",\"qiniuPrefix\":\"upload\",\"qiniuSecretKey\":\"uIwJHevMRWU0VLxFvgy0tAcOdGqasdtVlJkdy6vV\",\"type\":1}',0,'云存储配置信息'),
(2,'测试参数','1',1,'测试一下');

/*Table structure for table `sys_log` */

DROP TABLE IF EXISTS `sys_log`;

CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `time` bigint(20) NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统日志';

/*Data for the table `sys_log` */

insert  into `sys_log`(`id`,`username`,`operation`,`method`,`params`,`time`,`ip`,`create_date`) values 
(1,'admin','保存用户','io.renren.modules.sys.controller.SysUserController.save()','[{\"userId\":2,\"username\":\"wys\",\"password\":\"56924e61e9485fce68a1ef219487c1e447f2df787a9cb810e6c94287028c298b\",\"salt\":\"ljrlcIEElhsQFj7El4Iv\",\"email\":\"1275534396@qq.com\",\"mobile\":\"18255147511\",\"status\":1,\"roleIdList\":[],\"createUserId\":1,\"createTime\":\"Sep 15, 2019, 12:02:13 AM\"}]',92,'0:0:0:0:0:0:0:1','2019-09-15 00:02:14'),
(2,'admin','保存角色','io.renren.modules.sys.controller.SysRoleController.save()','[{\"roleId\":1,\"roleName\":\"组长\",\"remark\":\"小组长\",\"createUserId\":1,\"menuIdList\":[23,24,-666666,1,4],\"createTime\":\"Sep 15, 2019, 12:03:01 AM\"}]',44,'0:0:0:0:0:0:0:1','2019-09-15 00:03:01'),
(3,'admin','修改用户','io.renren.modules.sys.controller.SysUserController.update()','[{\"userId\":2,\"username\":\"wys\",\"password\":\"56924e61e9485fce68a1ef219487c1e447f2df787a9cb810e6c94287028c298b\",\"salt\":\"ljrlcIEElhsQFj7El4Iv\",\"email\":\"1275534396@qq.com\",\"mobile\":\"18255147511\",\"status\":1,\"roleIdList\":[1],\"createUserId\":1}]',23,'0:0:0:0:0:0:0:1','2019-09-15 00:03:50'),
(4,'admin','保存配置','io.renren.modules.sys.controller.SysConfigController.save()','[{\"id\":2,\"paramKey\":\"测试参数\",\"paramValue\":\"1\",\"remark\":\"测试一下\"}]',60,'0:0:0:0:0:0:0:1','2019-09-15 19:48:43'),
(5,'admin','保存菜单','io.renren.modules.sys.controller.SysMenuController.save()','[{\"menuId\":61,\"parentId\":36,\"name\":\"分配\",\"url\":\"\",\"perms\":\"express:task:allocation\",\"type\":2,\"icon\":\"\",\"orderNum\":0}]',30,'0:0:0:0:0:0:0:1','2019-10-08 20:07:22'),
(6,'admin','删除菜单','io.renren.modules.sys.controller.SysMenuController.delete()','[35]',33166,'0:0:0:0:0:0:0:1','2019-11-01 20:20:06');

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='菜单管理';

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`menu_id`,`parent_id`,`name`,`url`,`perms`,`type`,`icon`,`order_num`) values 
(1,0,'系统管理',NULL,NULL,0,'system',0),
(2,1,'管理员列表','sys/user',NULL,1,'admin',1),
(3,1,'角色管理','sys/role',NULL,1,'role',2),
(4,1,'菜单管理','sys/menu',NULL,1,'menu',3),
(5,1,'SQL监控','http://localhost:8080/renren-fast/druid/sql.html',NULL,1,'sql',4),
(6,1,'定时任务','job/schedule',NULL,1,'job',5),
(7,6,'查看',NULL,'sys:schedule:list,sys:schedule:info',2,NULL,0),
(8,6,'新增',NULL,'sys:schedule:save',2,NULL,0),
(9,6,'修改',NULL,'sys:schedule:update',2,NULL,0),
(10,6,'删除',NULL,'sys:schedule:delete',2,NULL,0),
(11,6,'暂停',NULL,'sys:schedule:pause',2,NULL,0),
(12,6,'恢复',NULL,'sys:schedule:resume',2,NULL,0),
(13,6,'立即执行',NULL,'sys:schedule:run',2,NULL,0),
(14,6,'日志列表',NULL,'sys:schedule:log',2,NULL,0),
(15,2,'查看',NULL,'sys:user:list,sys:user:info',2,NULL,0),
(16,2,'新增',NULL,'sys:user:save,sys:role:select',2,NULL,0),
(17,2,'修改',NULL,'sys:user:update,sys:role:select',2,NULL,0),
(18,2,'删除',NULL,'sys:user:delete',2,NULL,0),
(19,3,'查看',NULL,'sys:role:list,sys:role:info',2,NULL,0),
(20,3,'新增',NULL,'sys:role:save,sys:menu:list',2,NULL,0),
(21,3,'修改',NULL,'sys:role:update,sys:menu:list',2,NULL,0),
(22,3,'删除',NULL,'sys:role:delete',2,NULL,0),
(23,4,'查看',NULL,'sys:menu:list,sys:menu:info',2,NULL,0),
(24,4,'新增',NULL,'sys:menu:save,sys:menu:select',2,NULL,0),
(25,4,'修改',NULL,'sys:menu:update,sys:menu:select',2,NULL,0),
(26,4,'删除',NULL,'sys:menu:delete',2,NULL,0),
(27,1,'参数管理','sys/config','sys:config:list,sys:config:info,sys:config:save,sys:config:update,sys:config:delete',1,'config',6),
(29,1,'系统日志','sys/log','sys:log:list',1,'log',7),
(30,1,'文件上传','oss/oss','sys:oss:all',1,'oss',6),
(31,1,'订单表','express/order',NULL,1,'config',6),
(32,31,'查看',NULL,'express:order:list,express:order:info',2,NULL,6),
(33,31,'新增',NULL,'express:order:save',2,NULL,6),
(34,31,'修改',NULL,'express:order:update',2,NULL,6),
(36,1,'任务表','express/task',NULL,1,'config',6),
(37,36,'查看',NULL,'express:task:list,express:task:info',2,NULL,6),
(38,36,'新增',NULL,'express:task:save',2,NULL,6),
(39,36,'修改',NULL,'express:task:update',2,NULL,6),
(40,36,'删除',NULL,'express:task:delete',2,NULL,6),
(46,1,'快递员评价表','express/comment',NULL,1,'config',6),
(47,46,'查看',NULL,'express:comment:list,express:comment:info',2,NULL,6),
(48,46,'新增',NULL,'express:comment:save',2,NULL,6),
(49,46,'修改',NULL,'express:comment:update',2,NULL,6),
(50,46,'删除',NULL,'express:comment:delete',2,NULL,6),
(51,1,'快递公司表','express/expresscompany',NULL,1,'config',6),
(52,51,'查看',NULL,'express:expresscompany:list,express:expresscompany:info',2,NULL,6),
(53,51,'新增',NULL,'express:expresscompany:save',2,NULL,6),
(54,51,'修改',NULL,'express:expresscompany:update',2,NULL,6),
(55,51,'删除',NULL,'express:expresscompany:delete',2,NULL,6),
(56,1,'用户表','express/suser',NULL,1,'config',6),
(57,56,'查看',NULL,'express:suser:list,express:suser:info',2,NULL,6),
(58,56,'新增',NULL,'express:suser:save',2,NULL,6),
(59,56,'修改',NULL,'express:suser:update',2,NULL,6),
(60,56,'删除',NULL,'express:suser:delete',2,NULL,6),
(61,36,'分配','','express:task:allocation',2,'',0);

/*Table structure for table `sys_oss` */

DROP TABLE IF EXISTS `sys_oss`;

CREATE TABLE `sys_oss` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(200) DEFAULT NULL COMMENT 'URL地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='文件上传';

/*Data for the table `sys_oss` */

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='角色';

/*Data for the table `sys_role` */

insert  into `sys_role`(`role_id`,`role_name`,`remark`,`create_user_id`,`create_time`) values 
(1,'组长','小组长',1,'2019-09-15 00:03:01');

/*Table structure for table `sys_role_menu` */

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='角色与菜单对应关系';

/*Data for the table `sys_role_menu` */

insert  into `sys_role_menu`(`id`,`role_id`,`menu_id`) values 
(1,1,23),
(2,1,24),
(3,1,-666666),
(4,1,1),
(5,1,4);

/*Table structure for table `sys_sequence` */

DROP TABLE IF EXISTS `sys_sequence`;

CREATE TABLE `sys_sequence` (
  `id` int(11) unsigned NOT NULL,
  `no` varchar(4) NOT NULL DEFAULT '',
  `name` varchar(64) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `sys_sequence` */

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `salt` varchar(20) DEFAULT NULL COMMENT '盐',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统用户';

/*Data for the table `sys_user` */

insert  into `sys_user`(`user_id`,`username`,`password`,`salt`,`email`,`mobile`,`status`,`create_user_id`,`create_time`) values 
(1,'admin','9ec9750e709431dad22365cabc5c625482e574c74adaebba7dd02f1129e4ce1d','YzcmCZNvbXocrsz9dm8e','root@renren.io','13612345678',1,1,'2016-11-11 11:11:11'),
(2,'wys','56924e61e9485fce68a1ef219487c1e447f2df787a9cb810e6c94287028c298b','ljrlcIEElhsQFj7El4Iv','1275534396@qq.com','18255147511',1,1,'2019-09-15 00:02:14');

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户与角色对应关系';

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`id`,`user_id`,`role_id`) values 
(1,2,1);

/*Table structure for table `sys_user_token` */

DROP TABLE IF EXISTS `sys_user_token`;

CREATE TABLE `sys_user_token` (
  `user_id` bigint(20) NOT NULL,
  `token` varchar(100) NOT NULL COMMENT 'token',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE KEY `token` (`token`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统用户Token';

/*Data for the table `sys_user_token` */

insert  into `sys_user_token`(`user_id`,`token`,`expire_time`,`update_time`) values 
(1,'4cddf514dfc093473cc450ef4fa6d064','2019-11-21 10:17:29','2019-11-20 22:17:29'),
(2,'32ce5ce6e52ab67013245ce4dfc74bc6','2019-09-15 12:04:14','2019-09-15 00:04:14');

/*Table structure for table `tb_comment` */

DROP TABLE IF EXISTS `tb_comment`;

CREATE TABLE `tb_comment` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `messenger_id` int(11) DEFAULT NULL COMMENT '快递员ID',
  `service` int(25) DEFAULT NULL COMMENT '服务质量打分',
  `speed` int(25) DEFAULT NULL COMMENT '物流速度打分',
  `goods` int(25) DEFAULT NULL COMMENT '物品完好度',
  `content` varchar(255) DEFAULT NULL COMMENT '评价内容',
  `score` int(25) DEFAULT NULL COMMENT '综合得分',
  `create_by` varchar(25) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_by` varchar(25) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`comment_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='快递员评价表';

/*Data for the table `tb_comment` */

/*Table structure for table `tb_express_company` */

DROP TABLE IF EXISTS `tb_express_company`;

CREATE TABLE `tb_express_company` (
  `express_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '快递公司ID',
  `express_code` varchar(25) DEFAULT NULL COMMENT '快递公司编码',
  `express_name` varchar(25) DEFAULT NULL COMMENT '快递公司名称',
  `express_img` varchar(255) DEFAULT NULL COMMENT '快递公司头像',
  `express_phone` int(25) DEFAULT NULL COMMENT '快递公司电话',
  PRIMARY KEY (`express_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='快递公司表';

/*Data for the table `tb_express_company` */

/*Table structure for table `tb_goods` */

DROP TABLE IF EXISTS `tb_goods`;

CREATE TABLE `tb_goods` (
  `goods_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '商品名',
  `intro` varchar(500) DEFAULT NULL COMMENT '介绍',
  `price` decimal(10,2) DEFAULT NULL COMMENT '价格',
  `num` int(11) DEFAULT NULL COMMENT '数量',
  PRIMARY KEY (`goods_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品管理';

/*Data for the table `tb_goods` */

/*Table structure for table `tb_order` */

DROP TABLE IF EXISTS `tb_order`;

CREATE TABLE `tb_order` (
  `order_id` varchar(255) NOT NULL COMMENT '订单编号',
  `user_id` varchar(20) DEFAULT NULL COMMENT '用户ID',
  `sender_name` varchar(25) DEFAULT NULL COMMENT '寄件人姓名',
  `sender_phone` varchar(25) DEFAULT NULL COMMENT '寄件人号码',
  `sender_province` varchar(500) DEFAULT NULL COMMENT '寄件人省份',
  `sender_city` varchar(25) DEFAULT NULL COMMENT '寄件人城市',
  `sender_area` varchar(25) DEFAULT NULL COMMENT '寄件人区',
  `sender_detaile_addr` varchar(255) DEFAULT NULL COMMENT '寄件人地址',
  `receiver_name` varchar(25) DEFAULT NULL COMMENT '收件人姓名',
  `receiver_phone` varchar(25) DEFAULT NULL COMMENT '收件人号码',
  `receiver_province` varchar(25) DEFAULT NULL COMMENT '收件人省份',
  `receiver_city` varchar(25) DEFAULT NULL COMMENT '收件人城市',
  `receiver_area` varchar(25) DEFAULT NULL COMMENT '收件人区',
  `receiver_detaile_addr` varchar(255) DEFAULT NULL COMMENT '收件人地址',
  `goods_type` varchar(25) DEFAULT NULL COMMENT '物品种类',
  `transport_no` varchar(25) DEFAULT NULL COMMENT '运单号',
  `order_status` varchar(10) DEFAULT NULL COMMENT '订单状态',
  `express` varchar(255) DEFAULT NULL COMMENT '快递公司',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_by` varchar(255) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='订单表';

/*Data for the table `tb_order` */

insert  into `tb_order`(`order_id`,`user_id`,`sender_name`,`sender_phone`,`sender_province`,`sender_city`,`sender_area`,`sender_detaile_addr`,`receiver_name`,`receiver_phone`,`receiver_province`,`receiver_city`,`receiver_area`,`receiver_detaile_addr`,`goods_type`,`transport_no`,`order_status`,`express`,`create_time`,`create_by`,`modify_time`,`modify_by`) values 
('1177937715115724800',NULL,'孟望望','18255147511','北京市','北京市','东城区',NULL,'万迎霜','15556926080','天津市','天津市','和平区','天津西路002','大件',NULL,'10',NULL,'2019-09-28 21:26:52','6',NULL,NULL),
('1177940032716148736','6','万迎霜','15556926080','山西省','太原市','小店区','太原003','孟望望','18255147511','内蒙古自治区','呼和浩特市','新城区','呼和浩特009','文件',NULL,'10',NULL,'2019-09-28 21:36:05','6',NULL,NULL);

/*Table structure for table `tb_suser` */

DROP TABLE IF EXISTS `tb_suser`;

CREATE TABLE `tb_suser` (
  `user_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `mobile` varchar(20) NOT NULL COMMENT '手机号',
  `user_role` varchar(25) DEFAULT NULL COMMENT '用户角色',
  `user_type` varchar(255) DEFAULT NULL COMMENT '用户类型（0用户1快递员）',
  `user_img` varchar(255) DEFAULT NULL COMMENT '头像路径',
  `wechat_id` varchar(30) DEFAULT NULL COMMENT '微信id',
  `wechat_name` varchar(255) DEFAULT NULL COMMENT '微信名',
  `IDcar` varchar(255) CHARACTER SET utf16le DEFAULT NULL COMMENT '身份信息号码',
  `create_by` varchar(25) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_by` varchar(25) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户表';

/*Data for the table `tb_suser` */

insert  into `tb_suser`(`user_id`,`username`,`password`,`mobile`,`user_role`,`user_type`,`user_img`,`wechat_id`,`wechat_name`,`IDcar`,`create_by`,`create_time`,`modify_by`,`modify_time`) values 
(1,'mark','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918','13612345678',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2017-03-23 22:37:41',NULL,NULL),
(2,'wys','123','15555555555',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `tb_task` */

DROP TABLE IF EXISTS `tb_task`;

CREATE TABLE `tb_task` (
  `task_id` varchar(25) NOT NULL COMMENT '任务编号',
  `task_receiver_id` varchar(25) DEFAULT NULL COMMENT '任务领取人id',
  `order_id` varchar(25) DEFAULT NULL COMMENT '订单id',
  `transport_no` varchar(25) DEFAULT NULL COMMENT '运单号',
  `phone_num` varchar(20) DEFAULT NULL COMMENT '发/收手机号',
  `province` varchar(100) DEFAULT NULL COMMENT '省',
  `city` varchar(100) DEFAULT NULL COMMENT '市',
  `area` varchar(100) DEFAULT NULL COMMENT '区',
  `detaile_addr` varchar(255) DEFAULT NULL COMMENT '取货/派送地址',
  `task_type` varchar(25) DEFAULT NULL COMMENT '任务类型（0 揽件；1派送）',
  `task_status` varchar(25) DEFAULT NULL COMMENT '任务状态（0有效；1取消）',
  `allocation_by` varchar(25) DEFAULT NULL COMMENT '分配人',
  `allocation_time` datetime DEFAULT NULL COMMENT '分配时间',
  `create_by` varchar(25) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_by` varchar(255) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`task_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='任务表';

/*Data for the table `tb_task` */

insert  into `tb_task`(`task_id`,`task_receiver_id`,`order_id`,`transport_no`,`phone_num`,`province`,`city`,`area`,`detaile_addr`,`task_type`,`task_status`,`allocation_by`,`allocation_time`,`create_by`,`create_time`,`modify_by`,`modify_time`) values 
('1177937744366800896',NULL,'1177937715115724800',NULL,'18255147511','北京市','北京市','东城区',NULL,NULL,'10','1','2019-10-08 21:47:44','6','2019-09-28 21:27:04','1','2019-10-08 21:47:44'),
('1177940155068190720',NULL,'1177940032716148736',NULL,'15556926080','山西省','太原市','小店区','太原003',NULL,'10','1','2019-10-08 21:47:44','6','2019-09-28 21:36:38','1','2019-10-08 21:47:44');

/*Table structure for table `tb_user` */

DROP TABLE IF EXISTS `tb_user`;

CREATE TABLE `tb_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `openid` varchar(50) DEFAULT NULL COMMENT '微信唯一识别码',
  `role` varchar(10) DEFAULT NULL COMMENT '用户角色',
  `phone` varchar(20) NOT NULL COMMENT '手机号',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户';

/*Data for the table `tb_user` */

insert  into `tb_user`(`user_id`,`username`,`openid`,`role`,`phone`,`password`,`create_time`) values 
(1,'18255147511',NULL,'Common','18255147511','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','2019-09-17 21:32:08'),
(6,'孟望望','o_1K15It4HzWwgEfnTSZaqT2k9_Y','ExpressMan','',NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
