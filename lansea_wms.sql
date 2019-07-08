/*
Navicat MariaDB Data Transfer

Source Server         : EricALi
Source Server Version : 50556
Source Host           : 116.62.4.75:3306
Source Database       : lansea_wms

Target Server Type    : MariaDB
Target Server Version : 50556
File Encoding         : 65001

Date: 2019-07-02 17:11:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for approve_log
-- ----------------------------
DROP TABLE IF EXISTS `approve_log`;
CREATE TABLE `approve_log` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `pid` int(10) unsigned DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL COMMENT '审批日志类型：\n1：出入库审批；\n2：移位审批；\n',
  `status` tinyint(3) unsigned DEFAULT NULL COMMENT '100：修改数据\n101：删除数据',
  `remark` varchar(500) DEFAULT NULL,
  `request` text COMMENT '请求信息',
  `create_uid` int(10) unsigned DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of approve_log
-- ----------------------------
INSERT INTO `approve_log` VALUES ('55', '25', '1', '1', null, null, '1', '2019-07-02 10:24:54');
INSERT INTO `approve_log` VALUES ('56', '26', '1', '1', null, null, '1', '2019-07-02 10:32:05');
INSERT INTO `approve_log` VALUES ('57', '26', '1', '2', null, null, '1', '2019-07-02 10:32:38');
INSERT INTO `approve_log` VALUES ('58', '26', '1', '3', null, null, '1', '2019-07-02 10:32:40');
INSERT INTO `approve_log` VALUES ('59', '26', '1', '5', null, null, '1', '2019-07-02 10:32:43');

-- ----------------------------
-- Table structure for auth
-- ----------------------------
DROP TABLE IF EXISTS `auth`;
CREATE TABLE `auth` (
  `pid` int(10) unsigned DEFAULT '0',
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT '',
  `url` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of auth
-- ----------------------------
INSERT INTO `auth` VALUES ('2', '7', '添加合作伙伴', '/company/insert');
INSERT INTO `auth` VALUES ('0', '17', '分类字典', '/category');
INSERT INTO `auth` VALUES ('17', '18', '删除字典', '/category/delete');
INSERT INTO `auth` VALUES ('17', '19', '添加字典', '/category/insert');
INSERT INTO `auth` VALUES ('17', '20', '修改字典', '/category/update');
INSERT INTO `auth` VALUES ('0', '21', '合作方公司', '/company');
INSERT INTO `auth` VALUES ('21', '22', '添加公司', '/company/add');
INSERT INTO `auth` VALUES ('21', '23', '删除公司', '/company/delete');
INSERT INTO `auth` VALUES ('21', '24', '编辑公司', '/company/edit');
INSERT INTO `auth` VALUES ('0', '25', '部门管理', '/depart');
INSERT INTO `auth` VALUES ('25', '26', '删除部门', '/depart/delete');
INSERT INTO `auth` VALUES ('25', '27', '添加部门', '/depart/insert');
INSERT INTO `auth` VALUES ('25', '28', '编辑部门', '/depart/update');
INSERT INTO `auth` VALUES ('29', '30', '物料管理', '/material');
INSERT INTO `auth` VALUES ('0', '31', '物料管理', '/material');
INSERT INTO `auth` VALUES ('31', '32', '删除物料', '/material/delete');
INSERT INTO `auth` VALUES ('31', '33', '添加物料', '/material/insert');
INSERT INTO `auth` VALUES ('31', '34', '编辑物料', '/material/update');
INSERT INTO `auth` VALUES ('0', '35', '角色权限', '/role_auth');
INSERT INTO `auth` VALUES ('35', '36', '角色删除', '/role_auth/role_delete');
INSERT INTO `auth` VALUES ('35', '37', '角色新增', '/role_auth/insert');
INSERT INTO `auth` VALUES ('35', '38', '角色编辑', '/role_auth/update');
INSERT INTO `auth` VALUES ('0', '39', '出入库操作', '/stock_entry');
INSERT INTO `auth` VALUES ('39', '40', '出入库审批', '/stock_entry/approve');
INSERT INTO `auth` VALUES ('39', '41', '删除出入库单', '/stock_entry/delete');
INSERT INTO `auth` VALUES ('39', '42', '入库完成', '/stock_entry/finish');
INSERT INTO `auth` VALUES ('39', '43', '出库发货完成', '/stock_entry/delivery_finish');
INSERT INTO `auth` VALUES ('39', '44', '添加出入库单', '/stock_entry/insert');
INSERT INTO `auth` VALUES ('39', '45', '提交出入库单', '/stock_entry/submit');
INSERT INTO `auth` VALUES ('39', '46', '修改车入库单', '/stock_entry/update');
INSERT INTO `auth` VALUES ('0', '47', '库存移位操作', '/stock_trans');
INSERT INTO `auth` VALUES ('47', '48', '移位单审批', '/stock_trans/approve');
INSERT INTO `auth` VALUES ('47', '49', '删除移位单', '/stock_trans/delete');
INSERT INTO `auth` VALUES ('47', '50', '完成移位', '/stock_trans/finish');
INSERT INTO `auth` VALUES ('47', '51', '添加移位单', '/stock_trans/insert');
INSERT INTO `auth` VALUES ('47', '52', '提交移位单', '/stock_trans/submit');
INSERT INTO `auth` VALUES ('47', '53', '修改移位单', '/stock_trans/update');
INSERT INTO `auth` VALUES ('0', '54', '用户管理', '/user');
INSERT INTO `auth` VALUES ('54', '55', '添加用户', '/user/add');
INSERT INTO `auth` VALUES ('54', '56', '删除用户', '/user/delete');
INSERT INTO `auth` VALUES ('54', '57', '编辑用户', '/user/edit');
INSERT INTO `auth` VALUES ('0', '58', '仓库管理', '/warehouse');
INSERT INTO `auth` VALUES ('58', '59', '删除库位', '/warehouse/res_delete');
INSERT INTO `auth` VALUES ('58', '60', '添加库位', '/warehouse/res_insert');
INSERT INTO `auth` VALUES ('58', '61', '编辑库位', '/warehouse/res_update');
INSERT INTO `auth` VALUES ('58', '62', '删除货架', '/warehouse/stock_delete');
INSERT INTO `auth` VALUES ('58', '63', '添加货架', '/warehouse/stock_insert');
INSERT INTO `auth` VALUES ('58', '64', '编辑货架', '/warehouse/stock_update');
INSERT INTO `auth` VALUES ('58', '66', '删除仓库', '/warehouse/war_delete');
INSERT INTO `auth` VALUES ('58', '67', '添加仓库', '/warehouse/war_insert');
INSERT INTO `auth` VALUES ('58', '68', '编辑仓库', '/warehouse/war_update');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `type` tinyint(4) DEFAULT NULL COMMENT '1： 单位类别；\n2：物料分类；\n3：入库单；\n4：出库单；\n5：设备分类；\n6：设备产权；',
  `name` varchar(45) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  `is_del` datetime DEFAULT NULL,
  `create_uid` int(11) DEFAULT NULL,
  `update_uid` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('15', '1', '千克', '', null, '1', null, '2019-07-02 09:35:37', null);
INSERT INTO `category` VALUES ('16', '1', '米', '', null, '1', null, '2019-07-02 09:35:49', null);
INSERT INTO `category` VALUES ('17', '1', '平方米', '', null, '1', null, '2019-07-02 09:35:54', null);
INSERT INTO `category` VALUES ('18', '1', '立方米', '', null, '1', null, '2019-07-02 09:36:08', null);
INSERT INTO `category` VALUES ('19', '1', '件', '', null, '1', null, '2019-07-02 09:36:13', null);
INSERT INTO `category` VALUES ('20', '1', '套', '', null, '1', null, '2019-07-02 09:36:25', null);
INSERT INTO `category` VALUES ('21', '2', '耗材', '', null, '1', null, '2019-07-02 09:38:48', null);
INSERT INTO `category` VALUES ('22', '2', '普通材料', '', null, '1', null, '2019-07-02 09:39:02', null);
INSERT INTO `category` VALUES ('23', '2', '包装材料', '', null, '1', '1', '2019-07-02 09:39:16', '2019-07-02 10:14:46');
INSERT INTO `category` VALUES ('24', '2', '危险品材料', '', null, '1', null, '2019-07-02 09:39:29', null);
INSERT INTO `category` VALUES ('25', '2', '成品类', '', null, '1', null, '2019-07-02 09:39:49', null);
INSERT INTO `category` VALUES ('26', '2', '工具', '', null, '1', null, '2019-07-02 09:40:04', null);
INSERT INTO `category` VALUES ('27', '2', '成品', '', null, '1', null, '2019-07-02 09:40:11', null);
INSERT INTO `category` VALUES ('28', '3', '采购入库', '', null, '1', '1', '2019-07-02 09:41:06', '2019-07-02 09:41:32');
INSERT INTO `category` VALUES ('29', '3', '退货入库', '', null, '1', null, '2019-07-02 09:41:26', null);
INSERT INTO `category` VALUES ('30', '3', '试用入库', '', null, '1', null, '2019-07-02 09:41:42', null);
INSERT INTO `category` VALUES ('31', '4', '成品出库', '', null, '1', null, '2019-07-02 09:42:04', null);
INSERT INTO `category` VALUES ('32', '7', '吸尘器', '', null, '1', null, '2019-07-02 09:42:48', null);
INSERT INTO `category` VALUES ('33', '7', '电动牙刷', '', null, '1', null, '2019-07-02 09:42:56', null);
INSERT INTO `category` VALUES ('34', '7', '扫地机器人', '', null, '1', null, '2019-07-02 09:43:10', null);

-- ----------------------------
-- Table structure for company
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(45) DEFAULT NULL COMMENT '供应商编码',
  `name` varchar(200) DEFAULT NULL COMMENT '供应商全称\n',
  `category` tinyint(4) DEFAULT NULL COMMENT '企业属性：\n1：供应商\n2：客户\n3：承运商',
  `is_del` datetime DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL COMMENT '地址',
  `contact_name` varchar(45) DEFAULT NULL COMMENT '联系人姓名',
  `phone` varchar(45) DEFAULT NULL,
  `mobile` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `remark` varchar(1000) DEFAULT NULL,
  `create_uid` int(10) unsigned DEFAULT '0',
  `update_uid` int(10) unsigned DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COMMENT='合作公司管理';

-- ----------------------------
-- Records of company
-- ----------------------------
INSERT INTO `company` VALUES ('4', 'CP0001', '上海有前途包装材料有限公司', '1', null, '上海市前途路100弄888号', '衡牛碧', '78593215', '17587856892', '78512589@qq.com', '提供包装材料', '1', '0', '2019-07-02 09:49:44', null);
INSERT INTO `company` VALUES ('5', 'CP0002', '江苏五金制造有限公司', '1', null, '江苏省苏州市五金很多路432号', '牛武金', '78512568', '13587986857', '78512568@qq.com', '专业五金供货商', '1', '0', '2019-07-02 10:00:50', null);
INSERT INTO `company` VALUES ('6', 'CP0003', '浙江很强电气有限公司', '1', null, '浙江省杭州市很强路876号', '郑恒强', '87558785', '13879878888', '87558785@qq.com', '电子元器件供货商', '1', '1', '2019-07-02 10:02:35', '2019-07-02 10:05:06');
INSERT INTO `company` VALUES ('7', 'CP0004', '上海完美大老板有限公司', '2', null, '上海市完美路8888888弄', '笪老阪', '88888888', '18888888888', '88888888@qq.com', '大老板很厉害', '1', '0', '2019-07-02 10:06:53', null);
INSERT INTO `company` VALUES ('8', 'CP0005', '上海又快又稳物流有限公司', '3', null, '上海市快又稳路666号', '蒯友文', '66666666', '13666666666', '66666666@qq.com', '非常快，非常问的物流公司', '1', '0', '2019-07-02 10:08:48', null);

-- ----------------------------
-- Table structure for delivery
-- ----------------------------
DROP TABLE IF EXISTS `delivery`;
CREATE TABLE `delivery` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `stock_entry_id` int(10) unsigned DEFAULT NULL,
  `company_id` int(10) unsigned DEFAULT NULL,
  `number` varchar(45) DEFAULT NULL COMMENT '快递单号',
  `happen_time` datetime DEFAULT NULL,
  `create_uid` int(10) unsigned DEFAULT NULL,
  `update_uid` int(10) unsigned DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_del` datetime DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of delivery
-- ----------------------------

-- ----------------------------
-- Table structure for depart
-- ----------------------------
DROP TABLE IF EXISTS `depart`;
CREATE TABLE `depart` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(45) DEFAULT NULL,
  `is_del` datetime DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `create_uid` int(11) DEFAULT NULL,
  `update_uid` varchar(45) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of depart
-- ----------------------------
INSERT INTO `depart` VALUES ('6', 'DP001', null, '财务部', '1', '1', '2019-07-01 17:22:00', '2019-07-01 17:23:39');
INSERT INTO `depart` VALUES ('7', 'DP002', null, '总裁办', '1', '0', '2019-07-01 17:22:12', null);
INSERT INTO `depart` VALUES ('8', 'DP003', null, '采购部', '1', '0', '2019-07-01 17:22:27', null);
INSERT INTO `depart` VALUES ('9', 'DP004', null, '销售部', '1', '0', '2019-07-01 17:22:38', null);
INSERT INTO `depart` VALUES ('10', 'DP005', null, '仓储部', '1', '0', '2019-07-01 17:23:13', null);
INSERT INTO `depart` VALUES ('11', 'DP006', null, '生产部', '1', '0', '2019-07-01 17:23:31', null);
INSERT INTO `depart` VALUES ('12', 'DP007', null, '研发部', '1', '0', '2019-07-01 17:24:07', null);
INSERT INTO `depart` VALUES ('13', 'DP008', null, '人力资源', '1', '0', '2019-07-01 17:24:23', null);
INSERT INTO `depart` VALUES ('14', 'DP009', null, '行政部', '1', '0', '2019-07-01 17:24:35', null);

-- ----------------------------
-- Table structure for inventory
-- ----------------------------
DROP TABLE IF EXISTS `inventory`;
CREATE TABLE `inventory` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `material_id` int(10) unsigned DEFAULT NULL,
  `quantity` decimal(12,2) DEFAULT '0.00',
  `is_del` datetime DEFAULT NULL,
  `last_in_time` datetime DEFAULT NULL,
  `last_out_time` datetime DEFAULT NULL,
  `last_in_quantity` decimal(12,2) DEFAULT NULL,
  `last_out_quantity` decimal(12,2) DEFAULT NULL,
  `create_uid` int(10) unsigned DEFAULT NULL,
  `update_uid` int(10) unsigned DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `warehouse_id` int(10) unsigned DEFAULT NULL,
  `reservoir_id` int(10) unsigned DEFAULT NULL,
  `stock_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COMMENT='库存清单';

-- ----------------------------
-- Records of inventory
-- ----------------------------
INSERT INTO `inventory` VALUES ('30', '8', '1000.00', null, '2019-07-02 10:32:43', null, '1000.00', null, '1', '1', '2019-07-02 10:25:13', '2019-07-02 10:32:20', '12', '10', '8');

-- ----------------------------
-- Table structure for login_record
-- ----------------------------
DROP TABLE IF EXISTS `login_record`;
CREATE TABLE `login_record` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ip` varchar(20) DEFAULT NULL,
  `uid` int(10) unsigned DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '1： 登录；\n2：登出；',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of login_record
-- ----------------------------
INSERT INTO `login_record` VALUES ('10', '127.0.0.1', '1', '1', '2019-07-01 11:13:43');
INSERT INTO `login_record` VALUES ('11', '127.0.0.1', '1', '2', '2019-07-01 11:35:15');
INSERT INTO `login_record` VALUES ('12', '127.0.0.1', '1', '1', '2019-07-01 11:35:16');
INSERT INTO `login_record` VALUES ('13', '127.0.0.1', '1', '2', '2019-07-01 14:02:18');
INSERT INTO `login_record` VALUES ('14', '127.0.0.1', '1', '1', '2019-07-01 14:02:19');
INSERT INTO `login_record` VALUES ('15', '127.0.0.1', '1', '2', '2019-07-01 14:03:28');
INSERT INTO `login_record` VALUES ('16', '127.0.0.1', '1', '1', '2019-07-01 14:03:29');
INSERT INTO `login_record` VALUES ('17', '127.0.0.1', '1', '2', '2019-07-01 14:03:29');
INSERT INTO `login_record` VALUES ('18', '127.0.0.1', '1', '1', '2019-07-01 14:03:30');
INSERT INTO `login_record` VALUES ('19', '127.0.0.1', '1', '2', '2019-07-01 14:03:31');
INSERT INTO `login_record` VALUES ('20', '127.0.0.1', '1', '1', '2019-07-01 14:03:32');
INSERT INTO `login_record` VALUES ('21', '127.0.0.1', '1', '2', '2019-07-01 14:03:33');
INSERT INTO `login_record` VALUES ('22', '127.0.0.1', '1', '1', '2019-07-01 14:03:33');
INSERT INTO `login_record` VALUES ('23', '127.0.0.1', '1', '2', '2019-07-01 14:03:35');
INSERT INTO `login_record` VALUES ('24', '127.0.0.1', '1', '1', '2019-07-01 14:03:36');
INSERT INTO `login_record` VALUES ('25', '127.0.0.1', '1', '2', '2019-07-01 14:03:37');
INSERT INTO `login_record` VALUES ('26', '127.0.0.1', '1', '1', '2019-07-01 14:03:38');
INSERT INTO `login_record` VALUES ('27', '127.0.0.1', '1', '2', '2019-07-01 14:03:39');
INSERT INTO `login_record` VALUES ('28', '127.0.0.1', '1', '1', '2019-07-01 14:03:40');
INSERT INTO `login_record` VALUES ('29', '127.0.0.1', '1', '2', '2019-07-01 14:03:42');
INSERT INTO `login_record` VALUES ('30', '127.0.0.1', '1', '1', '2019-07-01 14:03:43');
INSERT INTO `login_record` VALUES ('31', '127.0.0.1', '1', '1', '2019-07-01 16:02:53');
INSERT INTO `login_record` VALUES ('32', '127.0.0.1', '1', '1', '2019-07-01 16:04:45');
INSERT INTO `login_record` VALUES ('33', '127.0.0.1', '1', '1', '2019-07-01 16:06:29');
INSERT INTO `login_record` VALUES ('34', '127.0.0.1', '1', '1', '2019-07-01 16:06:36');
INSERT INTO `login_record` VALUES ('35', '127.0.0.1', '1', '1', '2019-07-01 16:06:44');
INSERT INTO `login_record` VALUES ('36', '127.0.0.1', '1', '1', '2019-07-01 16:07:18');
INSERT INTO `login_record` VALUES ('37', '127.0.0.1', '1', '1', '2019-07-01 16:08:22');
INSERT INTO `login_record` VALUES ('38', '127.0.0.1', '1', '1', '2019-07-01 16:08:26');
INSERT INTO `login_record` VALUES ('39', '127.0.0.1', '1', '1', '2019-07-01 16:08:51');
INSERT INTO `login_record` VALUES ('40', '127.0.0.1', '1', '1', '2019-07-01 16:12:44');
INSERT INTO `login_record` VALUES ('41', '127.0.0.1', '1', '1', '2019-07-01 16:13:54');
INSERT INTO `login_record` VALUES ('42', '127.0.0.1', '1', '1', '2019-07-01 16:47:43');
INSERT INTO `login_record` VALUES ('43', '127.0.0.1', '1', '2', '2019-07-01 16:53:27');
INSERT INTO `login_record` VALUES ('44', '127.0.0.1', '1', '1', '2019-07-01 16:53:29');
INSERT INTO `login_record` VALUES ('45', '127.0.0.1', '1', '2', '2019-07-01 16:54:28');
INSERT INTO `login_record` VALUES ('46', '127.0.0.1', '1', '1', '2019-07-01 17:03:17');
INSERT INTO `login_record` VALUES ('47', '127.0.0.1', '1', '2', '2019-07-01 17:03:20');
INSERT INTO `login_record` VALUES ('48', '127.0.0.1', '1', '1', '2019-07-01 17:03:21');
INSERT INTO `login_record` VALUES ('49', '127.0.0.1', '1', '2', '2019-07-01 17:05:07');
INSERT INTO `login_record` VALUES ('50', '127.0.0.1', '1', '1', '2019-07-01 17:05:08');
INSERT INTO `login_record` VALUES ('51', '127.0.0.1', '1', '2', '2019-07-01 17:07:27');
INSERT INTO `login_record` VALUES ('52', '127.0.0.1', '1', '1', '2019-07-01 17:07:29');
INSERT INTO `login_record` VALUES ('53', '127.0.0.1', '1', '1', '2019-07-01 17:20:17');
INSERT INTO `login_record` VALUES ('54', '127.0.0.1', '1', '2', '2019-07-02 11:04:31');
INSERT INTO `login_record` VALUES ('55', '127.0.0.1', '14', '1', '2019-07-02 11:04:36');
INSERT INTO `login_record` VALUES ('56', '127.0.0.1', '1', '1', '2019-07-02 15:14:41');
INSERT INTO `login_record` VALUES ('57', '127.0.0.1', '1', '1', '2019-07-02 15:46:35');
INSERT INTO `login_record` VALUES ('58', '127.0.0.1', '1', '2', '2019-07-02 17:02:56');
INSERT INTO `login_record` VALUES ('59', '127.0.0.1', '1', '1', '2019-07-02 17:03:01');

-- ----------------------------
-- Table structure for material
-- ----------------------------
DROP TABLE IF EXISTS `material`;
CREATE TABLE `material` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `type` tinyint(4) DEFAULT '1' COMMENT '物料类型：\n1：采购物料;\n2：生产产品;\n3：物料和产品；',
  `category_id` int(10) unsigned DEFAULT NULL,
  `unit_id` int(10) unsigned DEFAULT NULL,
  `safe_num` int(11) DEFAULT NULL COMMENT '安全库存',
  `valid_day` int(11) DEFAULT NULL COMMENT '有效天',
  `quantity` decimal(12,2) DEFAULT '0.00' COMMENT '库存数量',
  `is_del` datetime DEFAULT NULL,
  `remark` varchar(1000) DEFAULT NULL,
  `create_uid` int(10) unsigned DEFAULT NULL,
  `update_uid` int(10) unsigned DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='物料管理';

-- ----------------------------
-- Records of material
-- ----------------------------
INSERT INTO `material` VALUES ('6', 'XCQ0001', '手持吸尘器 T700', '2', '32', '19', '100', '0', '0.00', null, '', '1', null, '2019-07-02 10:12:27', null);
INSERT INTO `material` VALUES ('7', 'XCQ0002', '吸尘器 T100', '2', '32', '19', '100', '0', '0.00', null, '', '1', null, '2019-07-02 10:12:53', null);
INSERT INTO `material` VALUES ('8', 'BZ0001', 'T700包装盒', '1', '23', '19', '100', '0', '0.00', null, '', '1', null, '2019-07-02 10:13:57', null);
INSERT INTO `material` VALUES ('9', 'LS0001', '螺丝 5mm', '1', '21', '19', '1000', '0', '0.00', null, '', '1', null, '2019-07-02 10:16:09', null);

-- ----------------------------
-- Table structure for move
-- ----------------------------
DROP TABLE IF EXISTS `move`;
CREATE TABLE `move` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(45) DEFAULT NULL COMMENT '移动单编号',
  `type` tinyint(4) DEFAULT NULL COMMENT '出入库类型\n1：入库\n2：出库\n3：移位',
  `material_id` int(10) unsigned DEFAULT NULL,
  `inventory_id` int(10) unsigned DEFAULT NULL,
  `inventory_tid` int(10) unsigned DEFAULT NULL COMMENT '移位情况有效：\n表示移往库存点',
  `company_id` int(10) unsigned DEFAULT NULL,
  `plan_quantity` decimal(12,2) DEFAULT NULL,
  `quantity` decimal(12,2) DEFAULT NULL,
  `is_del` datetime DEFAULT NULL,
  `stock_entry_id` int(10) unsigned DEFAULT NULL,
  `stock_trans_id` int(10) unsigned DEFAULT NULL,
  `delivery_id` int(10) unsigned DEFAULT '0',
  `happen_date` datetime DEFAULT NULL,
  `status` tinyint(3) unsigned DEFAULT '1' COMMENT '状态：\n1：初始提交；\n2：审核中；\n3：审核通过；\n4：审核未通过；\n5：已完成；',
  `create_uid` int(11) DEFAULT NULL,
  `update_uid` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `approve_uid` int(10) unsigned DEFAULT NULL,
  `approve_time` datetime DEFAULT NULL,
  `reject_remark` varchar(500) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of move
-- ----------------------------
INSERT INTO `move` VALUES ('48', null, '1', '8', '30', null, '4', '1000.00', '1000.00', null, '26', null, '0', null, '5', '1', null, '2019-07-02 10:32:20', null, '1', '2019-07-02 10:32:40', '', null);

-- ----------------------------
-- Table structure for number_create
-- ----------------------------
DROP TABLE IF EXISTS `number_create`;
CREATE TABLE `number_create` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(45) DEFAULT NULL,
  `prefix` varchar(45) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  `last_date` date DEFAULT NULL,
  `index` int(11) DEFAULT '1',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of number_create
-- ----------------------------
INSERT INTO `number_create` VALUES ('4', 'stock_in', 'SI', '入库订单自动生成', '2019-07-02', '2', '2019-07-02 10:32:05');
INSERT INTO `number_create` VALUES ('5', 'stock_out', 'SO', '出库订单自动生成', '2019-07-02', '1', null);
INSERT INTO `number_create` VALUES ('6', 'stock_trans', 'ST', '库存移位单号', '2019-07-02', '1', null);

-- ----------------------------
-- Table structure for operate_log
-- ----------------------------
DROP TABLE IF EXISTS `operate_log`;
CREATE TABLE `operate_log` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `pid` int(10) unsigned DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL COMMENT '日志类型：\n1：用户登录登出日志；\n2：出入库单审批日志；',
  `info` text,
  `create_uid` int(10) unsigned DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of operate_log
-- ----------------------------

-- ----------------------------
-- Table structure for reservoir
-- ----------------------------
DROP TABLE IF EXISTS `reservoir`;
CREATE TABLE `reservoir` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(45) DEFAULT NULL,
  `warehouse_id` int(10) unsigned DEFAULT NULL,
  `is_del` datetime DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  `create_uid` int(11) DEFAULT NULL,
  `update_uid` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of reservoir
-- ----------------------------
INSERT INTO `reservoir` VALUES ('7', 'KQ001', '11', null, '液体危险品区', '', '1', null, '2019-07-02 10:18:01', null);
INSERT INTO `reservoir` VALUES ('8', 'KQ002', '11', null, '固体危险品', '', '1', null, '2019-07-02 10:18:17', null);
INSERT INTO `reservoir` VALUES ('9', 'KQ003', '12', null, '耗材区', '', '1', null, '2019-07-02 10:18:59', null);
INSERT INTO `reservoir` VALUES ('10', 'KQ004', '12', null, '大件存放去', '', '1', null, '2019-07-02 10:19:32', null);
INSERT INTO `reservoir` VALUES ('11', 'KQ005', '13', null, '吸尘器区', '', '1', null, '2019-07-02 10:19:59', null);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  `create_uid` int(10) unsigned DEFAULT NULL,
  `update_uid` int(10) unsigned DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('9', '管理员', '', '1', '14', '2019-07-01 15:29:21', '2019-07-02 11:04:48');
INSERT INTO `role` VALUES ('10', '仓库管理员', '', '1', '1', '2019-07-02 09:32:03', '2019-07-02 09:32:03');
INSERT INTO `role` VALUES ('11', 'IT管理', '', '1', null, '2019-07-02 09:33:42', null);

-- ----------------------------
-- Table structure for role_auth
-- ----------------------------
DROP TABLE IF EXISTS `role_auth`;
CREATE TABLE `role_auth` (
  `role_id` int(10) unsigned DEFAULT NULL,
  `auth_id` int(10) unsigned DEFAULT NULL,
  KEY `fk_role_auth_role_idx` (`role_id`),
  KEY `fk_role_auth_auth1_idx` (`auth_id`),
  CONSTRAINT `fk_role_auth_auth1` FOREIGN KEY (`auth_id`) REFERENCES `auth` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_role_auth_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of role_auth
-- ----------------------------
INSERT INTO `role_auth` VALUES ('10', '39');
INSERT INTO `role_auth` VALUES ('10', '40');
INSERT INTO `role_auth` VALUES ('10', '41');
INSERT INTO `role_auth` VALUES ('10', '42');
INSERT INTO `role_auth` VALUES ('10', '43');
INSERT INTO `role_auth` VALUES ('10', '44');
INSERT INTO `role_auth` VALUES ('10', '45');
INSERT INTO `role_auth` VALUES ('10', '46');
INSERT INTO `role_auth` VALUES ('10', '47');
INSERT INTO `role_auth` VALUES ('10', '48');
INSERT INTO `role_auth` VALUES ('10', '49');
INSERT INTO `role_auth` VALUES ('10', '50');
INSERT INTO `role_auth` VALUES ('10', '51');
INSERT INTO `role_auth` VALUES ('10', '52');
INSERT INTO `role_auth` VALUES ('10', '53');
INSERT INTO `role_auth` VALUES ('10', '58');
INSERT INTO `role_auth` VALUES ('10', '59');
INSERT INTO `role_auth` VALUES ('10', '60');
INSERT INTO `role_auth` VALUES ('10', '61');
INSERT INTO `role_auth` VALUES ('10', '62');
INSERT INTO `role_auth` VALUES ('10', '63');
INSERT INTO `role_auth` VALUES ('10', '64');
INSERT INTO `role_auth` VALUES ('10', '66');
INSERT INTO `role_auth` VALUES ('10', '67');
INSERT INTO `role_auth` VALUES ('10', '68');
INSERT INTO `role_auth` VALUES ('11', '17');
INSERT INTO `role_auth` VALUES ('11', '18');
INSERT INTO `role_auth` VALUES ('11', '19');
INSERT INTO `role_auth` VALUES ('11', '20');
INSERT INTO `role_auth` VALUES ('11', '25');
INSERT INTO `role_auth` VALUES ('11', '26');
INSERT INTO `role_auth` VALUES ('11', '27');
INSERT INTO `role_auth` VALUES ('11', '28');
INSERT INTO `role_auth` VALUES ('11', '31');
INSERT INTO `role_auth` VALUES ('11', '32');
INSERT INTO `role_auth` VALUES ('11', '33');
INSERT INTO `role_auth` VALUES ('11', '34');
INSERT INTO `role_auth` VALUES ('11', '54');
INSERT INTO `role_auth` VALUES ('11', '55');
INSERT INTO `role_auth` VALUES ('11', '56');
INSERT INTO `role_auth` VALUES ('11', '57');
INSERT INTO `role_auth` VALUES ('9', '17');
INSERT INTO `role_auth` VALUES ('9', '18');
INSERT INTO `role_auth` VALUES ('9', '19');
INSERT INTO `role_auth` VALUES ('9', '20');
INSERT INTO `role_auth` VALUES ('9', '21');
INSERT INTO `role_auth` VALUES ('9', '22');
INSERT INTO `role_auth` VALUES ('9', '23');
INSERT INTO `role_auth` VALUES ('9', '24');
INSERT INTO `role_auth` VALUES ('9', '25');
INSERT INTO `role_auth` VALUES ('9', '26');
INSERT INTO `role_auth` VALUES ('9', '27');
INSERT INTO `role_auth` VALUES ('9', '28');
INSERT INTO `role_auth` VALUES ('9', '31');
INSERT INTO `role_auth` VALUES ('9', '32');
INSERT INTO `role_auth` VALUES ('9', '33');
INSERT INTO `role_auth` VALUES ('9', '34');
INSERT INTO `role_auth` VALUES ('9', '35');
INSERT INTO `role_auth` VALUES ('9', '36');
INSERT INTO `role_auth` VALUES ('9', '37');
INSERT INTO `role_auth` VALUES ('9', '38');
INSERT INTO `role_auth` VALUES ('9', '54');
INSERT INTO `role_auth` VALUES ('9', '55');
INSERT INTO `role_auth` VALUES ('9', '56');
INSERT INTO `role_auth` VALUES ('9', '57');

-- ----------------------------
-- Table structure for stock
-- ----------------------------
DROP TABLE IF EXISTS `stock`;
CREATE TABLE `stock` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `is_del` datetime DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  `warehouse_id` int(10) unsigned DEFAULT NULL,
  `reservoir_id` int(10) unsigned DEFAULT NULL,
  `create_uid` int(11) DEFAULT NULL,
  `update_uid` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of stock
-- ----------------------------
INSERT INTO `stock` VALUES ('4', 'HJ001', 'HJ001', null, '', '11', '8', '1', null, '2019-07-02 10:21:15', null);
INSERT INTO `stock` VALUES ('5', 'HJ002', 'HJ002', null, '', '11', '8', '1', null, '2019-07-02 10:21:28', null);
INSERT INTO `stock` VALUES ('6', 'HJ003', 'HJ003', null, '', '11', '7', '1', null, '2019-07-02 10:21:39', null);
INSERT INTO `stock` VALUES ('7', 'HJ004', 'HJ004', null, '', '12', '9', '1', null, '2019-07-02 10:21:49', null);
INSERT INTO `stock` VALUES ('8', 'HJ005', 'HJ005', null, '', '12', '10', '1', null, '2019-07-02 10:21:57', null);
INSERT INTO `stock` VALUES ('9', 'HJ006', 'HJ006', null, '', '13', '11', '1', null, '2019-07-02 10:22:07', null);

-- ----------------------------
-- Table structure for stock_entry
-- ----------------------------
DROP TABLE IF EXISTS `stock_entry`;
CREATE TABLE `stock_entry` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `number` varchar(45) DEFAULT NULL COMMENT '入库或出库单号',
  `order_number` varchar(45) DEFAULT NULL COMMENT '订单号',
  `category_id` int(11) DEFAULT NULL,
  `company_id` int(11) DEFAULT NULL,
  `status` tinyint(4) DEFAULT '1' COMMENT '状态：\n1：初始提交；\n2：审核中；\n3：审核通过；\n4：审核未通过；\n5：已完成；',
  `delivery_id` int(10) unsigned DEFAULT '0',
  `type` tinyint(4) DEFAULT NULL COMMENT '1：入库\n2：出库',
  `is_del` datetime DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  `create_uid` int(11) DEFAULT NULL,
  `update_uid` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `approve_uid` int(10) unsigned DEFAULT NULL,
  `approve_time` datetime DEFAULT NULL,
  `reject_remark` varchar(500) DEFAULT NULL COMMENT '驳回理由',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of stock_entry
-- ----------------------------
INSERT INTO `stock_entry` VALUES ('26', 'SI201907020001', '30125789551266', '28', '4', '5', '0', '1', null, '', '1', '1', '2019-07-02 10:32:05', '2019-07-02 11:04:22', '1', '2019-07-02 10:32:40', '');

-- ----------------------------
-- Table structure for stock_trans
-- ----------------------------
DROP TABLE IF EXISTS `stock_trans`;
CREATE TABLE `stock_trans` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `number` varchar(45) DEFAULT NULL,
  `warehouse_id` int(10) unsigned DEFAULT NULL,
  `reservoir_id` int(10) unsigned DEFAULT NULL,
  `stock_id` int(10) unsigned DEFAULT NULL,
  `status` tinyint(3) unsigned DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  `warehouse_tid` int(10) unsigned DEFAULT NULL,
  `reservoir_tid` int(10) unsigned DEFAULT NULL,
  `stock_tid` int(10) unsigned DEFAULT NULL,
  `create_uid` int(10) unsigned DEFAULT NULL,
  `update_uid` int(10) unsigned DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_del` datetime DEFAULT NULL,
  `approve_uid` int(11) unsigned zerofill DEFAULT NULL,
  `approve_time` datetime DEFAULT NULL,
  `reject_remark` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of stock_trans
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) DEFAULT NULL,
  `real_name` varchar(45) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL COMMENT '常用邮箱',
  `is_del` datetime DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `activated` tinyint(4) DEFAULT '1' COMMENT '状态：\n1：未激活\n2：激活',
  `mobile` varchar(45) DEFAULT NULL COMMENT '手机号',
  `phone` varchar(45) DEFAULT NULL COMMENT '固定电话',
  `token` varchar(1000) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `gender` tinyint(4) DEFAULT '1' COMMENT '1：女\n2：男',
  `avatar` varchar(200) DEFAULT '',
  `role_id` int(10) unsigned DEFAULT '0',
  `depart_id` int(10) unsigned DEFAULT '0',
  `is_root` tinyint(4) DEFAULT '0' COMMENT '是否为超级管理员',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name_UNIQUE` (`user_name`),
  KEY `fk_user_role1_idx` (`role_id`),
  CONSTRAINT `fk_user_role1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '陈相敏', 'admin@qq.com', null, '5c801d2534e9464adc1f712c2fa5e4f9', '2', '15322547812', '68745147', 'VYcDU67ldIeQBA4QeRRHVPgdloA8XSC0prX3xcaZbBAh78l9XqltBoYiUnQQr/Pprpf9jforMTrkpGCf45dSkzcnAzAlkS1DHe4xzSMG7ts3MpcomEOt7TG/irlTLLBy', '2019-05-27 14:24:53', '1', '', '9', '14', '1');
INSERT INTO `user` VALUES ('14', 'test', '测试员', '88888888@qq.com', null, 'bcbfacabae00b238a2e1b4db63dd7d70', '2', '13546454578', '51888888', 'VYcDU67ldIeQBA4QeRRHVDNGCTs+NkEEGCdEzD1EcPzNxTP7M/U2mf3vO0pK3ShDCUcvmHxsXgqduJ1e5vmIMEfl1Lnrnia+e8I9LX6Yl/Eu9/1RL9DCmVc0qhmoLiIe', '2019-07-02 09:14:58', '1', '', '9', '14', '0');
INSERT INTO `user` VALUES ('15', 'U55110', '陆仁贾', '78978952@qq.com', null, 'bcbfacabae00b238a2e1b4db63dd7d70', '2', '13888888888', '78512589', null, '2019-07-02 09:29:42', '1', '', '10', '10', '0');
INSERT INTO `user` VALUES ('16', 'U55111', '陆仁怡', '85125785@qq.com', null, 'bcbfacabae00b238a2e1b4db63dd7d70', '2', '15785789655', '85125785', null, '2019-07-02 09:30:34', '1', '', '11', '14', '0');

-- ----------------------------
-- Table structure for warehouse
-- ----------------------------
DROP TABLE IF EXISTS `warehouse`;
CREATE TABLE `warehouse` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `is_del` datetime DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  `create_uid` int(11) DEFAULT NULL,
  `update_uid` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of warehouse
-- ----------------------------
INSERT INTO `warehouse` VALUES ('11', 'CK001', '危险品仓库', null, '存放危险品耗材和产品', '1', null, '2019-07-02 10:16:45', null);
INSERT INTO `warehouse` VALUES ('12', 'CK002', '物料仓库', null, '存放物料仓库', '1', null, '2019-07-02 10:17:16', null);
INSERT INTO `warehouse` VALUES ('13', 'CK003', '成品仓库', null, '存放生产完成成品', '1', null, '2019-07-02 10:17:33', null);
