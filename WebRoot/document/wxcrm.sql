-- MySQL dump 10.13  Distrib 5.5.19, for Win32 (x86)
--
-- Host: localhost    Database: wxcrm
-- ------------------------------------------------------
-- Server version	5.5.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `lz_other_login`
--

DROP TABLE IF EXISTS `lz_other_login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lz_other_login` (
  `OLG_ID` int(11) NOT NULL AUTO_INCREMENT,
  `OLG_USERNAME` varchar(100) DEFAULT NULL,
  `OLG_TYPE` varchar(20) DEFAULT NULL,
  `OLG_TOKEN` varchar(200) DEFAULT NULL,
  `OLG_ACCOUNT` int(11) DEFAULT NULL,
  `OLG_STATUS` varchar(10) DEFAULT NULL,
  `OLG_LOGTIME` datetime DEFAULT NULL,
  PRIMARY KEY (`OLG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lz_other_login`
--

LOCK TABLES `lz_other_login` WRITE;
/*!40000 ALTER TABLE `lz_other_login` DISABLE KEYS */;
/*!40000 ALTER TABLE `lz_other_login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lz_wei_accesstoken`
--

DROP TABLE IF EXISTS `lz_wei_accesstoken`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lz_wei_accesstoken` (
  `WAT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `WAT_WEC_ID` int(11) DEFAULT NULL,
  `WAT_APPID` varchar(80) DEFAULT NULL,
  `WAT_TOKEN` varchar(200) DEFAULT NULL,
  `WAT_EXPIRES_IN` datetime DEFAULT NULL,
  `WAT_CREAT_TIME` datetime DEFAULT NULL,
  `WAT_STATUS` char(1) DEFAULT NULL,
  PRIMARY KEY (`WAT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lz_wei_accesstoken`
--

LOCK TABLES `lz_wei_accesstoken` WRITE;
/*!40000 ALTER TABLE `lz_wei_accesstoken` DISABLE KEYS */;
/*!40000 ALTER TABLE `lz_wei_accesstoken` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lz_wei_button`
--

DROP TABLE IF EXISTS `lz_wei_button`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lz_wei_button` (
  `WBT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `WBT_APP_ID` varchar(200) DEFAULT NULL,
  `WBT_JSON` text,
  `WBT_URL` varchar(200) DEFAULT NULL,
  `WBT_KEY` varchar(200) DEFAULT NULL,
  `WBT_LEVEL` int(11) DEFAULT NULL,
  `WBT_PARENT` int(11) DEFAULT NULL,
  `WBT_STATUS` varchar(20) DEFAULT NULL,
  `WBT_REGISTOR_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`WBT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lz_wei_button`
--

LOCK TABLES `lz_wei_button` WRITE;
/*!40000 ALTER TABLE `lz_wei_button` DISABLE KEYS */;
/*!40000 ALTER TABLE `lz_wei_button` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lz_wei_enter`
--

DROP TABLE IF EXISTS `lz_wei_enter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lz_wei_enter` (
  `WEC_ID` int(11) NOT NULL AUTO_INCREMENT,
  `WEC_APP_NAME` varchar(100) DEFAULT NULL,
  `WEC_APP_ID` varchar(80) NOT NULL,
  `WEC_APP_SECRET` varchar(100) NOT NULL,
  `WEC_REDERECT_URL` varchar(100) DEFAULT NULL,
  `WEC_TOKEN` varchar(200) DEFAULT NULL,
  `WEC_ENCODING_AES_KEY` varchar(200) DEFAULT NULL,
  `WEC_AES_TYPE` char(1) DEFAULT NULL,
  `WEC_APP_TYPE` char(1) DEFAULT NULL,
  `WEC_CUS_TYPE` char(1) DEFAULT NULL,
  `WEC_ACCOUNT_TYPE` char(1) DEFAULT NULL,
  `WEC_ENTER_ID` int(11) DEFAULT NULL,
  `WEC_DEFAULT_MSG` int(11) DEFAULT NULL,
  `WEC_SUBSCRIBE_MSG` int(11) DEFAULT NULL,
  `WEC_STATUS` varchar(20) DEFAULT NULL,
  `WEC_DESC` varchar(200) DEFAULT NULL,
  `WEC_REGISTOR` int(11) DEFAULT NULL,
  `WEC_REGISTDATE` datetime DEFAULT NULL,
  PRIMARY KEY (`WEC_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lz_wei_enter`
--

LOCK TABLES `lz_wei_enter` WRITE;
/*!40000 ALTER TABLE `lz_wei_enter` DISABLE KEYS */;
/*!40000 ALTER TABLE `lz_wei_enter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lz_wei_enter_cust`
--

DROP TABLE IF EXISTS `lz_wei_enter_cust`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lz_wei_enter_cust` (
  `WET_ID` int(11) NOT NULL AUTO_INCREMENT,
  `WET_OPEN_ID` varchar(200) DEFAULT NULL,
  `WET_CUSTENTER_NAME` varchar(200) DEFAULT NULL,
  `WET_MOBILE` varchar(20) DEFAULT NULL,
  `WET_NAME` varchar(40) DEFAULT NULL,
  `WET_TYPE` varchar(1) DEFAULT NULL,
  `WET_CUE_ID` int(11) DEFAULT NULL,
  `WET_CUU_ID` int(11) DEFAULT NULL,
  `WET_STATUS` varchar(20) DEFAULT NULL,
  `WET_DESC` varchar(200) DEFAULT NULL,
  `WET_REGISTOR` int(11) DEFAULT NULL,
  `WET_REGISTDATE` datetime DEFAULT NULL,
  PRIMARY KEY (`WET_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lz_wei_enter_cust`
--

LOCK TABLES `lz_wei_enter_cust` WRITE;
/*!40000 ALTER TABLE `lz_wei_enter_cust` DISABLE KEYS */;
/*!40000 ALTER TABLE `lz_wei_enter_cust` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lz_wei_enter_order`
--

DROP TABLE IF EXISTS `lz_wei_enter_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lz_wei_enter_order` (
  `WEO_ID` int(11) NOT NULL AUTO_INCREMENT,
  `WEO_GQ_ID` int(11) DEFAULT NULL,
  `WEO_WET_ID` int(11) DEFAULT NULL,
  `WEO_STATUS` varchar(20) DEFAULT NULL,
  `WEO_DESC` varchar(200) DEFAULT NULL,
  `WEO_REGISTOR` int(11) DEFAULT NULL,
  `WEO_REGISTDATE` datetime DEFAULT NULL,
  PRIMARY KEY (`WEO_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lz_wei_enter_order`
--

LOCK TABLES `lz_wei_enter_order` WRITE;
/*!40000 ALTER TABLE `lz_wei_enter_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `lz_wei_enter_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lz_wei_errorcode`
--

DROP TABLE IF EXISTS `lz_wei_errorcode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lz_wei_errorcode` (
  `WAE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `WAE_CODE` varchar(20) DEFAULT NULL,
  `WAE_DESC` varchar(400) DEFAULT NULL,
  `WAE_CREAT_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`WAE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lz_wei_errorcode`
--

LOCK TABLES `lz_wei_errorcode` WRITE;
/*!40000 ALTER TABLE `lz_wei_errorcode` DISABLE KEYS */;
/*!40000 ALTER TABLE `lz_wei_errorcode` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lz_wei_jsapi_ticket`
--

DROP TABLE IF EXISTS `lz_wei_jsapi_ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lz_wei_jsapi_ticket` (
  `WJT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `WJT_WEC_ID` int(11) DEFAULT NULL,
  `WJT_APPID` varchar(80) DEFAULT NULL,
  `WJT_JSAPI_TICKET` varchar(200) DEFAULT NULL,
  `WJT_EXPIRES_IN` datetime DEFAULT NULL,
  `WJT_CREAT_TIME` datetime DEFAULT NULL,
  `WJT_STATUS` char(1) DEFAULT NULL,
  PRIMARY KEY (`WJT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lz_wei_jsapi_ticket`
--

LOCK TABLES `lz_wei_jsapi_ticket` WRITE;
/*!40000 ALTER TABLE `lz_wei_jsapi_ticket` DISABLE KEYS */;
/*!40000 ALTER TABLE `lz_wei_jsapi_ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lz_wei_keyword_message`
--

DROP TABLE IF EXISTS `lz_wei_keyword_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lz_wei_keyword_message` (
  `WKG_ID` int(11) NOT NULL AUTO_INCREMENT,
  `WKG_WEC_ID` int(11) DEFAULT NULL,
  `WKG_APP_ID` varchar(200) DEFAULT NULL,
  `WKG_KEYWORDS` varchar(200) DEFAULT NULL,
  `WKG_WMG_ID` int(11) DEFAULT NULL,
  `WKG_STATUS` varchar(20) DEFAULT NULL,
  `WKG_DESC` varchar(200) DEFAULT NULL,
  `WKG_REGISTOR` int(11) DEFAULT NULL,
  `WKG_REGISTDATE` datetime DEFAULT NULL,
  PRIMARY KEY (`WKG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lz_wei_keyword_message`
--

LOCK TABLES `lz_wei_keyword_message` WRITE;
/*!40000 ALTER TABLE `lz_wei_keyword_message` DISABLE KEYS */;
/*!40000 ALTER TABLE `lz_wei_keyword_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lz_wei_menu`
--

DROP TABLE IF EXISTS `lz_wei_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lz_wei_menu` (
  `WMU_ID` int(11) NOT NULL AUTO_INCREMENT,
  `WMU_WEC_ID` int(11) DEFAULT NULL,
  `WMU_APP_ID` varchar(200) DEFAULT NULL,
  `WMU_JSON` text,
  `WMU_DESC` varchar(200) DEFAULT NULL,
  `WMU_STATUS` char(1) DEFAULT NULL,
  `WMU_REGISTOR_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`WMU_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lz_wei_menu`
--

LOCK TABLES `lz_wei_menu` WRITE;
/*!40000 ALTER TABLE `lz_wei_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `lz_wei_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lz_wei_menu_button`
--

DROP TABLE IF EXISTS `lz_wei_menu_button`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lz_wei_menu_button` (
  `WMB_BUTTON_ID` int(11) NOT NULL,
  `WMB_MENU_ID` int(11) NOT NULL,
  `WMB_STATUS` varchar(20) DEFAULT NULL,
  `WMB_REGISTOR_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`WMB_BUTTON_ID`,`WMB_MENU_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lz_wei_menu_button`
--

LOCK TABLES `lz_wei_menu_button` WRITE;
/*!40000 ALTER TABLE `lz_wei_menu_button` DISABLE KEYS */;
/*!40000 ALTER TABLE `lz_wei_menu_button` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lz_wei_message`
--

DROP TABLE IF EXISTS `lz_wei_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lz_wei_message` (
  `WMG_ID` int(11) NOT NULL AUTO_INCREMENT,
  `WMG_CONTENT` varchar(800) DEFAULT NULL,
  `WMG_CONTENT_XML` varchar(2000) DEFAULT NULL,
  `WMG_REPLY_TYPE` char(1) DEFAULT NULL,
  `WMG_MSG_TYPE` char(1) DEFAULT NULL,
  `WMG_AES_TYPE` char(1) DEFAULT NULL,
  `WMG_STATUS` varchar(20) DEFAULT NULL,
  `WMG_DESC` varchar(800) DEFAULT NULL,
  `WMG_REGISTOR` int(11) DEFAULT NULL,
  `WMG_REGISTDATE` datetime DEFAULT NULL,
  PRIMARY KEY (`WMG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lz_wei_message`
--

LOCK TABLES `lz_wei_message` WRITE;
/*!40000 ALTER TABLE `lz_wei_message` DISABLE KEYS */;
/*!40000 ALTER TABLE `lz_wei_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lz_wei_watcher`
--

DROP TABLE IF EXISTS `lz_wei_watcher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lz_wei_watcher` (
  `WAC_ID` int(11) NOT NULL AUTO_INCREMENT,
  `WAC_WEC_ID` int(11) DEFAULT NULL,
  `WAC_APPID` varchar(200) DEFAULT NULL,
  `WAC_OPENID` varchar(200) DEFAULT NULL,
  `WAC_SUBSCRIBE` int(11) DEFAULT NULL,
  `WAC_NICK_NAME` varchar(400) DEFAULT NULL,
  `WAC_SEX` char(1) DEFAULT NULL,
  `WAC_LANGUAGE` varchar(20) DEFAULT NULL,
  `WAC_CITY` varchar(100) DEFAULT NULL,
  `WAC_PROVINCE` varchar(100) DEFAULT NULL,
  `WAC_COUNTRY` varchar(100) DEFAULT NULL,
  `WAC_HEAD_IMG_URL` varchar(400) DEFAULT NULL,
  `WAC_SUBSCRIBE_TIME` datetime DEFAULT NULL,
  `WAC_STATUS` char(1) DEFAULT NULL,
  `WAC_REGISTOR_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`WAC_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lz_wei_watcher`
--

LOCK TABLES `lz_wei_watcher` WRITE;
/*!40000 ALTER TABLE `lz_wei_watcher` DISABLE KEYS */;
/*!40000 ALTER TABLE `lz_wei_watcher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wc_admin`
--

DROP TABLE IF EXISTS `wc_admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wc_admin` (
  `WAD_ID` int(11) NOT NULL AUTO_INCREMENT,
  `WAD_USERNAME` varchar(20) NOT NULL,
  `WAD_PWD` varchar(80) DEFAULT NULL,
  `WAD_NAME` varchar(200) DEFAULT NULL,
  `WAD_SEX` char(1) DEFAULT NULL,
  `WAD_REGISTOR` int(11) DEFAULT NULL,
  `WAD_REGISTDATE` datetime DEFAULT NULL,
  `WAD_LOGINDATE` datetime DEFAULT NULL,
  PRIMARY KEY (`WAD_ID`),
  UNIQUE KEY `WAD_ID` (`WAD_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wc_admin`
--

LOCK TABLES `wc_admin` WRITE;
/*!40000 ALTER TABLE `wc_admin` DISABLE KEYS */;
INSERT INTO `wc_admin` VALUES (1,'admin','21232f297a57a5a743894a0e4a801fc3','管理员','1',NULL,NULL,'2015-02-04 16:31:38');
/*!40000 ALTER TABLE `wc_admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wc_admin_menu`
--

DROP TABLE IF EXISTS `wc_admin_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wc_admin_menu` (
  `WAM_ADMIN_ID` int(11) NOT NULL,
  `WAM_MENU_ID` int(11) NOT NULL,
  `WAM_REGIST_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`WAM_ADMIN_ID`,`WAM_MENU_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wc_admin_menu`
--

LOCK TABLES `wc_admin_menu` WRITE;
/*!40000 ALTER TABLE `wc_admin_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `wc_admin_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wc_admin_role`
--

DROP TABLE IF EXISTS `wc_admin_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wc_admin_role` (
  `WAR_ADMIN_ID` int(11) NOT NULL,
  `WAR_ROLE_ID` int(11) NOT NULL,
  `WAR_REGIST_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`WAR_ADMIN_ID`,`WAR_ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wc_admin_role`
--

LOCK TABLES `wc_admin_role` WRITE;
/*!40000 ALTER TABLE `wc_admin_role` DISABLE KEYS */;
INSERT INTO `wc_admin_role` VALUES (1,1,NULL);
/*!40000 ALTER TABLE `wc_admin_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wc_menu`
--

DROP TABLE IF EXISTS `wc_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wc_menu` (
  `WME_ID` int(11) NOT NULL AUTO_INCREMENT,
  `WME_NAME` varchar(50) NOT NULL,
  `WME_URL` varchar(200) DEFAULT NULL,
  `WME_LEVEL` char(1) DEFAULT NULL,
  `WME_ORDER` int(11) DEFAULT NULL,
  `WME_DESC` varchar(400) DEFAULT NULL,
  `WME_PARENT_ID` int(11) DEFAULT NULL,
  `WME_REGISTOR` int(11) DEFAULT NULL,
  `WME_REGIST_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`WME_ID`),
  UNIQUE KEY `WME_ID` (`WME_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wc_menu`
--

LOCK TABLES `wc_menu` WRITE;
/*!40000 ALTER TABLE `wc_menu` DISABLE KEYS */;
INSERT INTO `wc_menu` VALUES (5,'系统管理','','1',1,'',0,NULL,NULL),(6,'菜单管理1','1','2',1,'',5,NULL,NULL),(7,'菜单查询','/menu/queryMenu','3',1,'',6,NULL,NULL),(8,'人员查询','/admin/queryAdmin','3',2,'',6,NULL,NULL),(9,'角色管理','/role/queryRole','3',3,'',6,NULL,NULL),(10,'站点管理','','2',2,'',5,NULL,NULL),(11,'站点查询','/website/queryWebsite','3',1,'',10,NULL,NULL),(12,'微信平台','','2',3,'',5,NULL,NULL),(13,'错误代码','/weixin/queryErrorCode','3',1,'',12,NULL,NULL),(14,'accessToken','/weixin/queryAccessToken','3',2,'',12,NULL,NULL),(15,'关注者列表','/weixin/queryWatcher','3',3,'',12,NULL,NULL),(16,'自定义菜单','/weixinmenu/queryMenu','3',4,'',12,NULL,NULL),(17,'微信账号','/weixin/queryWeixinEnter','3',5,'',12,NULL,NULL),(18,'消息列表','/weixinmsg/queryLzWeiMsg','3',6,'',12,NULL,NULL),(19,'JsApiTicket','/weixin/queryJsApiTicket','3',7,'',12,NULL,NULL);
/*!40000 ALTER TABLE `wc_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wc_role`
--

DROP TABLE IF EXISTS `wc_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wc_role` (
  `WRO_ROLE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `WRO_ROLE_NAME` varchar(100) NOT NULL,
  `WRO_ROLE_DESC` varchar(200) DEFAULT NULL,
  `WRO_REGISTOR` int(11) DEFAULT NULL,
  `WRO_REGIST_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`WRO_ROLE_ID`),
  UNIQUE KEY `WRO_ROLE_ID` (`WRO_ROLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wc_role`
--

LOCK TABLES `wc_role` WRITE;
/*!40000 ALTER TABLE `wc_role` DISABLE KEYS */;
INSERT INTO `wc_role` VALUES (1,'超级管理员','',NULL,NULL);
/*!40000 ALTER TABLE `wc_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wc_role_menu`
--

DROP TABLE IF EXISTS `wc_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wc_role_menu` (
  `WRM_ROLE_ID` int(11) NOT NULL,
  `WRM_MENU_ID` int(11) NOT NULL,
  `WRM_CREATE_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`WRM_ROLE_ID`,`WRM_MENU_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wc_role_menu`
--

LOCK TABLES `wc_role_menu` WRITE;
/*!40000 ALTER TABLE `wc_role_menu` DISABLE KEYS */;
INSERT INTO `wc_role_menu` VALUES (1,5,NULL),(1,6,NULL),(1,7,NULL),(1,8,NULL),(1,9,NULL),(1,10,NULL),(1,11,NULL),(1,12,NULL),(1,13,NULL),(1,14,NULL),(1,15,NULL),(1,16,NULL),(1,17,NULL),(1,18,NULL),(1,19,NULL);
/*!40000 ALTER TABLE `wc_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wc_website`
--

DROP TABLE IF EXISTS `wc_website`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wc_website` (
  `WCS_ID` int(11) NOT NULL AUTO_INCREMENT,
  `WCS_WEBSITE_NAME` varchar(200) NOT NULL,
  `WCS_APP_NAME` varchar(200) DEFAULT NULL,
  `WCS_APP_ID` varchar(100) DEFAULT NULL,
  `WCS_APP_SECRET` varchar(100) DEFAULT NULL,
  `WCS_REDIRECT_URL` varchar(200) DEFAULT NULL,
  `WCS_TOKEN` varchar(80) DEFAULT NULL,
  `WCS_ADMIN_ID` int(11) DEFAULT NULL,
  `WCS_REGISTOR` int(11) DEFAULT NULL,
  `WCS_REGISTDATE` datetime DEFAULT NULL,
  PRIMARY KEY (`WCS_ID`),
  UNIQUE KEY `WCS_ID` (`WCS_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wc_website`
--

LOCK TABLES `wc_website` WRITE;
/*!40000 ALTER TABLE `wc_website` DISABLE KEYS */;
INSERT INTO `wc_website` VALUES (1,'测试站点','微信公众号测试账号',' wxafe8f8406022a69f','74971f4930d6383430153e3fe8ddd28c','','',1,NULL,NULL);
/*!40000 ALTER TABLE `wc_website` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-02-04 16:50:11
