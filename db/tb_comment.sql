/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : renren_fast

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 10/10/2020 22:17:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_comment
-- ----------------------------
DROP TABLE IF EXISTS `tb_comment`;
CREATE TABLE `tb_comment`  (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `messenger_id` int(11) DEFAULT NULL COMMENT '快递员ID',
  `order_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '订单id',
  `service_attitude` int(10) DEFAULT NULL COMMENT '快递员服务态度',
  `professional` int(10) DEFAULT NULL COMMENT '快递员专业性',
  `spending` int(10) DEFAULT NULL COMMENT '费用合理性',
  `get_speed` int(10) DEFAULT NULL COMMENT '取货/送货时间',
  `begin_speed` int(10) DEFAULT NULL COMMENT '下单之后发货时间/网点派送时间',
  `transport_speed` int(10) DEFAULT NULL COMMENT '物品运输速度',
  `personal_info_sec` int(10) DEFAULT NULL COMMENT '个人信息安全',
  `complete` int(10) DEFAULT NULL COMMENT '物品完整度',
  `goods_sec` int(10) DEFAULT NULL COMMENT '物品安全性',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '评价内容',
  `score` int(25) DEFAULT NULL COMMENT '综合得分',
  `create_by` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `modify_by` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime(0) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`comment_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '快递员评价表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_comment
-- ----------------------------
INSERT INTO `tb_comment` VALUES (1, 4, 4, '1177937715115724809', 5, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 5, '不错', 5, NULL, '2020-03-05 22:26:10', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
