-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: localhost    Database: gpstreet
-- ------------------------------------------------------
-- Server version	5.1.54-community

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
  CONSTRAINT `groupIdFK` FOREIGN KEY (`groupid`) REFERENCES `gpst_groups` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `pageIdFK` FOREIGN KEY (`pageid`) REFERENCES `gpst_pages` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gpst_group_has_page`
--

LOCK TABLES `gpst_group_has_page` WRITE;
/*!40000 ALTER TABLE `gpst_group_has_page` DISABLE KEYS */;
INSERT INTO `gpst_group_has_page` VALUES (1,1),(1,2),(58,2),(1,3),(1,4),(1,5);
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
  `groupname` varchar(45) NOT NULL,
  `groupdesc` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gpst_groups`
--

LOCK TABLES `gpst_groups` WRITE;
/*!40000 ALTER TABLE `gpst_groups` DISABLE KEYS */;
INSERT INTO `gpst_groups` VALUES (1,'admin','Administrator'),(58,'sssss',''),(59,'ghghgh','');
/*!40000 ALTER TABLE `gpst_groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gpst_locations`
--

DROP TABLE IF EXISTS `gpst_locations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gpst_locations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gpst_locations`
--

LOCK TABLES `gpst_locations` WRITE;
/*!40000 ALTER TABLE `gpst_locations` DISABLE KEYS */;
INSERT INTO `gpst_locations` VALUES (1,'Dr. Ahmed Ali','Beside the round road',36.879703,30.706707);
/*!40000 ALTER TABLE `gpst_locations` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gpst_pages`
--

LOCK TABLES `gpst_pages` WRITE;
/*!40000 ALTER TABLE `gpst_pages` DISABLE KEYS */;
INSERT INTO `gpst_pages` VALUES (1,'settings.xhtml','Settings page'),(2,'index.xhtml','Home page'),(3,'dashboard.xhtml','Dashboard'),(4,'track.xhtml','Track users'),(5,'locations.xhtml','Loctions page');
/*!40000 ALTER TABLE `gpst_pages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gpst_state`
--

DROP TABLE IF EXISTS `gpst_state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gpst_state` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gpst_state`
--

LOCK TABLES `gpst_state` WRITE;
/*!40000 ALTER TABLE `gpst_state` DISABLE KEYS */;
INSERT INTO `gpst_state` VALUES (1,'check-in','check in'),(2,'check-out','check out');
/*!40000 ALTER TABLE `gpst_state` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gpst_tracker`
--

DROP TABLE IF EXISTS `gpst_tracker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gpst_tracker` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `date` datetime DEFAULT NULL,
  `state_id` int(11) NOT NULL,
  `device_id` double NOT NULL,
  `location_id` int(11) DEFAULT NULL,
  `notes` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id_fk_idx` (`user_id`),
  KEY `state_id_fk_idx` (`state_id`),
  KEY `location_id_fk_idx` (`location_id`),
  CONSTRAINT `location_id_fk` FOREIGN KEY (`location_id`) REFERENCES `gpst_locations` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `state_id_fk` FOREIGN KEY (`state_id`) REFERENCES `gpst_state` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `gpst_users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gpst_tracker`
--

LOCK TABLES `gpst_tracker` WRITE;
/*!40000 ALTER TABLE `gpst_tracker` DISABLE KEYS */;
INSERT INTO `gpst_tracker` VALUES (1,1,36.879466,30.667648,'2015-08-27 22:59:52',1,344534345,1,NULL),(2,1,36.883707,30.689215,'2015-08-27 22:59:52',2,344534345,1,NULL),(3,1,36.879703,30.706707,'2015-08-27 22:59:53',1,344534345,1,NULL),(4,1,36.885233,30.702323,'2015-08-27 22:59:50',1,344534345,1,NULL),(5,129,36.885233,30.702323,'2015-08-27 22:59:50',1,344534345,1,NULL),(6,129,36.883707,30.689217,'2015-08-27 22:59:50',1,344534345,1,NULL),(7,1,36.883707,30.689215,'2015-08-27 22:59:52',1,344534345,1,NULL),(8,1,36.883707,30.689215,'2015-08-27 22:59:52',1,344534345,1,NULL),(9,1,36.883707,30.689215,'2015-08-27 22:59:52',1,344534345,1,NULL),(10,1,36.883707,30.689215,'2015-08-27 22:59:52',1,344534345,1,NULL),(11,1,36.885233,30.702323,'2015-08-27 22:59:52',1,344534345,1,NULL),(12,1,36.885233,30.702323,'2015-08-27 22:59:52',1,344534345,1,NULL),(13,1,36.885233,30.702323,'2015-08-27 22:59:52',1,344534345,1,NULL),(14,1,36.885233,30.702323,'2015-08-27 22:59:52',1,344534345,1,NULL),(15,1,36.885232,30.702326,'2015-08-27 22:59:52',1,344534345,1,NULL);
/*!40000 ALTER TABLE `gpst_tracker` ENABLE KEYS */;
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
  CONSTRAINT `fk_User_has_Groups_User1` FOREIGN KEY (`User_userid`) REFERENCES `gpst_users` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gpst_user_has_groups`
--

LOCK TABLES `gpst_user_has_groups` WRITE;
/*!40000 ALTER TABLE `gpst_user_has_groups` DISABLE KEYS */;
INSERT INTO `gpst_user_has_groups` VALUES (1,1),(129,1),(130,1),(131,1),(133,1),(134,1),(135,1),(136,1),(137,1),(140,1),(132,58),(139,59);
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
) ENGINE=InnoDB AUTO_INCREMENT=141 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gpst_users`
--

LOCK TABLES `gpst_users` WRITE;
/*!40000 ALTER TABLE `gpst_users` DISABLE KEYS */;
INSERT INTO `gpst_users` VALUES (1,'ihab_ramadan','Rrr6dSFu/p7/SWfOszuti8kqtPY=','','','ihab','ramadan'),(129,'sssssss','ZnowzByJWfdMwd2TV7ISAY+2K2c=','','','s','ss'),(130,'qqqqqqqqqqq','fasBzX5mgxy79Qtg7nuVEzEMbW8=','','','q','q'),(131,'qw','K43Qce2pVZC6LZ9oeuxPk8+1y0I=','','','qw','qw'),(132,'vvvv','PpSsptdYvpJnQyFsiCI058OI8wU=','','','vvv','vvv'),(133,'ty','GKqzha0AFaspKVRFhiKYjWE0cgw=','','','yy','yy'),(134,'ioio','3UKqwKtqSNvMIa9gf1NO3g2S6Vg=','','','ioio','io'),(135,'uiuiuiu','fdwgXwIdy0dqXRJK3meh2+P1jaM=','','','uiui','uiui'),(136,'ppp','QAv2QIiYqojz9QNlJuCinBLd6K0=','','','ppp','pp'),(137,'ppppppp','i44cfPJWSE+TO929wiJ9o4jGu0o=','','','pppp','pppp'),(139,'werqwer','OynZ/56+2+YZ87Re/UERAd1q87Y=','','','werqwer','qwerqwer'),(140,'wer','DIc29DPPtI8OXf6GAFyz3coXriw=','','','werq','qw');
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

-- Dump completed on 2015-11-26 16:51:08
