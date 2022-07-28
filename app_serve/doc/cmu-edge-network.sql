-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: vue_wygl_210507
-- ------------------------------------------------------
-- Server version	5.7.9
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `cmu_edge_network`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `cmu_edge_network` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `cmu_edge_network`;


--
-- Table structure for table `spring_session`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `spring_session` (
  `PRIMARY_ID` char(36) NOT NULL,
  `SESSION_ID` char(36) NOT NULL,
  `CREATION_TIME` bigint(20) NOT NULL,
  `LAST_ACCESS_TIME` bigint(20) NOT NULL,
  `MAX_INACTIVE_INTERVAL` int(11) NOT NULL,
  `EXPIRY_TIME` bigint(20) NOT NULL,
  `PRINCIPAL_NAME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`PRIMARY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spring_session`
--


--
-- Table structure for table `spring_session_attributes`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `spring_session_attributes` (
  `SESSION_PRIMARY_ID` char(36) NOT NULL,
  `ATTRIBUTE_NAME` varchar(200) NOT NULL,
  `ATTRIBUTE_BYTES` blob NOT NULL,
  PRIMARY KEY (`SESSION_PRIMARY_ID`,`ATTRIBUTE_NAME`),
  CONSTRAINT `SPRING_SESSION_ATTRIBUTES_FK` FOREIGN KEY (`SESSION_PRIMARY_ID`) REFERENCES `spring_session` (`PRIMARY_ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spring_session_attributes`
--


--
-- Table structure for table `user`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `username` varchar(255) DEFAULT '',
  `password` varchar(255) DEFAULT '',
  `mobile` varchar(255) DEFAULT '',
  `name` varchar(255) DEFAULT '',
  `avatar` varchar(255) DEFAULT '',
  `role` varchar(255) DEFAULT '',
  `qq` varchar(255) DEFAULT '',
  `email` varchar(255) DEFAULT '',
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create time',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT 'update time',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--


--
-- Table structure for table `model_file`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `model_file` (
                        `username` varchar(40) NOT NULL,
                        `path` varchar(80) NOT NULL,
                        `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create time',
                        `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT 'update time',
                        PRIMARY KEY (`username`, `path`)
) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `model_file`
--


--
-- Table structure for table `report`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `report` (
                              `username` varchar(40) NOT NULL,
                              `filename` varchar(80) NOT NULL,
                              `value1` varchar(10) DEFAULT '',
                              `value2` varchar(10) DEFAULT '',
                              `value3` varchar(10) DEFAULT '',
                              `value4` varchar(10) DEFAULT '',
                              `value5` varchar(10) DEFAULT '',
                              `value6` varchar(10) DEFAULT '',
                              `value7` varchar(10) DEFAULT '',
                              `value8` varchar(10) DEFAULT '',
                              `value9` varchar(10) DEFAULT '',
                              `value10` varchar(10) DEFAULT '',
                              `value11` varchar(10) DEFAULT '',
                              `value12` varchar(10) DEFAULT '',
                              `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create time',
                              `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT 'update time',
                              PRIMARY KEY (`username`, `filename`)
) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--



/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-08 23:26:01
