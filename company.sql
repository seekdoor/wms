/*
Navicat MariaDB Data Transfer

Source Server         : EricALi
Source Server Version : 50556
Source Host           : 116.62.4.75:3306
Source Database       : lansea_wms

Target Server Type    : MariaDB
Target Server Version : 50556
File Encoding         : 65001

Date: 2019-05-31 09:24:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for company
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(45) DEFAULT NULL COMMENT '供应商编码',
  `category` tinyint(4) DEFAULT NULL COMMENT '企业属性：\n1：供应商\n2：客户\n3：承运商',
  `name` varchar(200) DEFAULT NULL COMMENT '供应商全称\n',
  `address` varchar(200) DEFAULT NULL COMMENT '地址',
  `contact_name` varchar(45) DEFAULT NULL COMMENT '联系人姓名',
  `phone` varchar(45) DEFAULT NULL,
  `mobile` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `remark` varchar(1000) DEFAULT NULL,
  `creator_id` int(10) unsigned DEFAULT NULL,
  `approver_id` int(10) unsigned DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='合作公司管理';

-- ----------------------------
-- Records of company
-- ----------------------------
INSERT INTO `company` VALUES ('1', 'KH1034', '2', '江南皮革有限公司', '江南某地', '张三', '57412458', '13542784512', 'zhangshan@qq.com', '江南皮革有限公司', '1', '0', '2019-05-30 11:14:46');
INSERT INTO `company` VALUES ('2', 'GYS4321', '1', '淮安螺丝制造厂', 'sdfsdf', '李四', '48751245', '19425412478', 'lishi@ddd.com', '淮安螺丝制造厂', '1', '0', '2019-05-31 08:46:00');
INSERT INTO `company` VALUES ('3', 'CYS1133', '3', '成都物流有限公司', '成都某地', '王五', '25487451', '18451245784', 'wangwu@qq.com', '成都物流有限公司', '1', '0', '2019-05-31 08:46:03');
