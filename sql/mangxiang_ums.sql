/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 8.0.22 : Database - mangxiang_ums
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mangxiang_ums` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `mangxiang_ums`;

/*Table structure for table `mx_user` */

DROP TABLE IF EXISTS `mx_user`;

CREATE TABLE `mx_user` (
  `id` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户id',
  `username` varchar(31) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '密码',
  `birthday` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '生日',
  `sex` int DEFAULT NULL COMMENT '性别1男2女',
  `name` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '联系人(姓名)',
  `phonenumber` varchar(31) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '联系电话(手机号码)',
  `address` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '收货地址',
  `avatar` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像',
  `nickname` varchar(31) COLLATE utf8mb4_general_ci DEFAULT '张三' COMMENT '昵称',
  `email` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '邮箱',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  `version` int DEFAULT '1' COMMENT '乐观锁',
  `is_deleted` int DEFAULT '0' COMMENT '是否删除1删除0未删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `mx_user` */

insert  into `mx_user`(`id`,`username`,`password`,`birthday`,`sex`,`name`,`phonenumber`,`address`,`avatar`,`nickname`,`email`,`gmt_create`,`gmt_modified`,`version`,`is_deleted`) values 
('1358246440094711810','test','$2a$10$zeRY5brXOe7vayoAJcBz2OzrWAazz.CnfZ/443Cz8GET4BLl0Pdey',NULL,NULL,NULL,NULL,NULL,NULL,'张三',NULL,'2021-02-07 10:49:21','2021-02-07 10:49:21',1,0),
('1358249962735493121','admin','$2a$10$EWMZf9a9uCxSOax39kD7UesnDCxvkDY44MiTQ76ZgLp7zf4t3Pa32',NULL,NULL,NULL,NULL,NULL,NULL,'张三',NULL,'2021-02-07 11:03:21','2021-02-07 11:03:21',1,0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
