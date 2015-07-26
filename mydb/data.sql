CREATE DATABASE  IF NOT EXISTS `gpstreet` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `gpstreet`;
-- MySQL dump 10.13  Distrib 5.6.18, for Win32 (x86)
--
-- Host: localhost    Database: gpstreet
-- ------------------------------------------------------
-- Server version	5.6.19-enterprise-commercial-advanced

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
-- Table structure for table `gpst_group_has_page`
--

DROP TABLE IF EXISTS `gpst_group_has_page`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gpst_group_has_page` (
  `groupid` int(11) NOT NULL,
  `pageid` int(11) NOT NULL,
  PRIMARY KEY (`groupid`,`pageid`),
  KEY `pageIdFK_idx` (`pageid`),
  CONSTRAINT `groupIdFK` FOREIGN KEY (`groupid`) REFERENCES `gpst_groups` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pageIdFK` FOREIGN KEY (`pageid`) REFERENCES `gpst_pages` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gpst_group_has_page`
--

LOCK TABLES `gpst_group_has_page` WRITE;
/*!40000 ALTER TABLE `gpst_group_has_page` DISABLE KEYS */;
INSERT INTO `gpst_group_has_page` VALUES (1,1),(1,2),(2,2);
/*!40000 ALTER TABLE `gpst_group_has_page` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gpst_groups`
--

DROP TABLE IF EXISTS `gpst_groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gpst_groups` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `groupname` varchar(45) DEFAULT NULL,
  `groupdesc` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gpst_groups`
--

LOCK TABLES `gpst_groups` WRITE;
/*!40000 ALTER TABLE `gpst_groups` DISABLE KEYS */;
INSERT INTO `gpst_groups` VALUES (1,'admin','Administrator'),(2,'guest','Guest');
/*!40000 ALTER TABLE `gpst_groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gpst_pages`
--

DROP TABLE IF EXISTS `gpst_pages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gpst_pages` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gpst_pages`
--

LOCK TABLES `gpst_pages` WRITE;
/*!40000 ALTER TABLE `gpst_pages` DISABLE KEYS */;
INSERT INTO `gpst_pages` VALUES (1,'settings.xhtml','Settings page'),(2,'index.xhtml','Home page');
/*!40000 ALTER TABLE `gpst_pages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gpst_user_has_groups`
--

DROP TABLE IF EXISTS `gpst_user_has_groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gpst_user_has_groups` (
  `User_userid` int(11) NOT NULL,
  `Groups_groupid` int(11) NOT NULL,
  PRIMARY KEY (`User_userid`,`Groups_groupid`),
  KEY `fk_User_has_Groups_Groups1` (`Groups_groupid`),
  KEY `fk_User_has_Groups_User1` (`User_userid`),
  CONSTRAINT `fk_User_has_Groups_Groups1` FOREIGN KEY (`Groups_groupid`) REFERENCES `gpst_groups` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_User_has_Groups_User1` FOREIGN KEY (`User_userid`) REFERENCES `gpst_users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gpst_user_has_groups`
--

LOCK TABLES `gpst_user_has_groups` WRITE;
/*!40000 ALTER TABLE `gpst_user_has_groups` DISABLE KEYS */;
INSERT INTO `gpst_user_has_groups` VALUES (1,1),(19,2),(24,2);
/*!40000 ALTER TABLE `gpst_user_has_groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gpst_users`
--

DROP TABLE IF EXISTS `gpst_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gpst_users` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(25) NOT NULL,
  `password` varchar(50) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `firstname` varchar(45) DEFAULT NULL,
  `lastname` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gpst_users`
--

LOCK TABLES `gpst_users` WRITE;
/*!40000 ALTER TABLE `gpst_users` DISABLE KEYS */;
INSERT INTO `gpst_users` VALUES (1,'ihab_ramadan','Rrr6dSFu/p7/SWfOszuti8kqtPY=','elagoza@gmail.com','01007570826','ihab','ramadan'),(2,'admin','Rrr6dSFu/p7/SWfOszuti8kqtPY=','','','admin','admin'),(6,'cvccdafwrt','pP6ejoV7rIbyGzGlLEpedKYL060=','','','qwrwqr','qwreqr'),(7,'asdfasdfasdf','AdycQNk+MAMCwL7oD3qqop9U1uk=','','','asdfasdf','asdfadsf'),(9,'asdfsdfasdf','0vqoBAafkRwgqWXOw9xxZcGCe7U=','','','asdfsdf','sdfasdf'),(10,'gggggggggggg','xrVtBa0ZuMeziLPGJC82PC2Ii80=','','','ggggg','gggggg'),(11,'AADAAA','fhPAA6glbNQhBVVjxdplcdUHE8k=','','','aaaAA','AAAAQ'),(19,'cfffffffffffff','Njxz9H6J0EQtohYMP99Wgfmg5nY=','','','cfc','fcfcf'),(24,'ihab','Rrr6dSFu/p7/SWfOszuti8kqtPY=','','','ihab','ihab');
/*!40000 ALTER TABLE `gpst_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `v_user_role`
--

DROP TABLE IF EXISTS `v_user_role`;
/*!50001 DROP VIEW IF EXISTS `v_user_role`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `v_user_role` (
  `username` tinyint NOT NULL,
  `password` tinyint NOT NULL,
  `groupname` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `v_user_role`
--

/*!50001 DROP TABLE IF EXISTS `v_user_role`*/;
/*!50001 DROP VIEW IF EXISTS `v_user_role`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `v_user_role` AS select `gpst_users`.`username` AS `username`,`gpst_users`.`password` AS `password`,`gpst_groups`.`groupname` AS `groupname` from ((`gpst_user_has_groups` join `gpst_users` on((`gpst_user_has_groups`.`User_userid` = `gpst_users`.`id`))) join `gpst_groups` on((`gpst_groups`.`id` = `gpst_user_has_groups`.`Groups_groupid`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-06-04 16:57:04
