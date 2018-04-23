CREATE DATABASE  IF NOT EXISTS `studentmanagement` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `studentmanagement`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: studentmanagement
-- ------------------------------------------------------
-- Server version	5.7.20-log

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
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login` (
  `Name` varchar(40) NOT NULL,
  `Pwd` varchar(45) NOT NULL,
  PRIMARY KEY (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES ('1','1'),('Admin','123456');
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stuinformation`
--

DROP TABLE IF EXISTS `stuinformation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stuinformation` (
  `stuId` char(15) NOT NULL,
  `stuName` varchar(50) NOT NULL,
  `stuSex` char(1) NOT NULL,
  `stuAge` int(11) NOT NULL,
  `stuSpecialty` varchar(50) NOT NULL,
  `stuDept` varchar(45) NOT NULL,
  PRIMARY KEY (`stuId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stuinformation`
--

LOCK TABLES `stuinformation` WRITE;
/*!40000 ALTER TABLE `stuinformation` DISABLE KEYS */;
INSERT INTO `stuinformation` VALUES ('123271631100710','张三','女',21,'计算机','电子信息系'),('123271631102101','王五','男',20,'金融管理','财经管理系'),('123271631102102','张三','女',19,'金融管理','财经管理系'),('123271631102103','张三','女',19,'金融管理','财经管理系'),('123271631102104','张正贤','男',21,'计算机','电子信息系'),('123271631102105','猪八戒','男',31,'食品鉴赏','食品营养系'),('123271631102106','孙悟空','男',31,'演员的素质修养','跑龙套专业'),('123271631102107','沙僧','男',21,'物流管理','工商系'),('123271631102108','李','女',18,'电子信息','财经管理系');
/*!40000 ALTER TABLE `stuinformation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'studentmanagement'
--

--
-- Dumping routines for database 'studentmanagement'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-23 21:08:43
