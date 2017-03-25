-- MySQL dump 10.13  Distrib 5.7.17, for Linux (x86_64)
--
-- Host: localhost    Database: ramanidb2
-- ------------------------------------------------------
-- Server version	5.7.17-0ubuntu0.16.04.1

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
-- Table structure for table `house_plan`
--

DROP TABLE IF EXISTS `house_plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `house_plan` (
  `plan_id` int(11) NOT NULL AUTO_INCREMENT,
  `upload_date` datetime NOT NULL,
  `title` varchar(255) DEFAULT 'New House Plan',
  `img_fileset_dir` varchar(255) NOT NULL,
  `opt_fileset_dir` varchar(255) NOT NULL,
  `description` text,
  `price_plan` decimal(13,2) NOT NULL,
  `price_boq` decimal(13,2) NOT NULL,
  `price_mtr_schedule` decimal(13,2) NOT NULL,
  `total_area` decimal(10,0) NOT NULL,
  `featured_state` int(11) NOT NULL DEFAULT '0',
  `archived_state` int(11) NOT NULL DEFAULT '0',
  `roomcount_id` int(11) NOT NULL,
  `typology_id` int(11) NOT NULL,
  `roof_id` int(11) NOT NULL,
  PRIMARY KEY (`plan_id`),
  UNIQUE KEY `img_fileset_dir` (`img_fileset_dir`),
  UNIQUE KEY `opt_fileset_dir` (`opt_fileset_dir`),
  UNIQUE KEY `roomcount_id` (`roomcount_id`),
  KEY `fk_pln_typology` (`typology_id`),
  KEY `fk_pln_roof` (`roof_id`),
  CONSTRAINT `fk_pln_roof` FOREIGN KEY (`roof_id`) REFERENCES `pln_roofing` (`roofing_id`),
  CONSTRAINT `fk_pln_rooms` FOREIGN KEY (`roomcount_id`) REFERENCES `pln_roomcount` (`roomcount_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_pln_typology` FOREIGN KEY (`typology_id`) REFERENCES `pln_typology` (`typology_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1003 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `house_plan`
--

LOCK TABLES `house_plan` WRITE;
/*!40000 ALTER TABLE `house_plan` DISABLE KEYS */;
INSERT INTO `house_plan` VALUES (1001,'2017-03-20 22:53:37','The First plan','9817','9271','open plan kitchen<br/>spacious living room',12000.00,5600.00,3400.00,1027,1,0,1,1,1),(1002,'2017-03-25 10:45:46','Thika Rediscovered','0012','2309','Large kitchen front<br>open plan backyard <br>',23200.00,1400.00,3200.00,1200,1,0,14,2,3);
/*!40000 ALTER TABLE `house_plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pln_roofing`
--

DROP TABLE IF EXISTS `pln_roofing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pln_roofing` (
  `roofing_id` int(11) NOT NULL AUTO_INCREMENT,
  `roof_type` varchar(255) NOT NULL,
  PRIMARY KEY (`roofing_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pln_roofing`
--

LOCK TABLES `pln_roofing` WRITE;
/*!40000 ALTER TABLE `pln_roofing` DISABLE KEYS */;
INSERT INTO `pln_roofing` VALUES (1,'Gable'),(2,'Hip'),(3,'Flat'),(4,'Hip (with parachet)'),(5,'Attic');
/*!40000 ALTER TABLE `pln_roofing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pln_roomcount`
--

DROP TABLE IF EXISTS `pln_roomcount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pln_roomcount` (
  `roomcount_id` int(11) NOT NULL AUTO_INCREMENT,
  `bathrooms` int(11) NOT NULL,
  `bedrooms` int(11) NOT NULL,
  `storeys` int(11) NOT NULL,
  `kitchens` int(11) NOT NULL,
  `dining` int(11) NOT NULL,
  `living` int(11) NOT NULL,
  `laundry` int(11) NOT NULL,
  PRIMARY KEY (`roomcount_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pln_roomcount`
--

LOCK TABLES `pln_roomcount` WRITE;
/*!40000 ALTER TABLE `pln_roomcount` DISABLE KEYS */;
INSERT INTO `pln_roomcount` VALUES (1,2,2,2,2,2,2,0),(2,1,2,3,4,5,1,2),(3,4,1,3,2,2,3,2),(4,4,1,3,2,2,3,2),(5,4,1,3,2,2,3,2),(6,1,2,2,1,2,2,2),(7,1,2,2,1,2,2,2),(8,1,3,2,1,2,3,2),(9,1,3,2,1,2,3,4),(10,7,2,6,8,9,1,2),(11,1,3,1,1,1,1,1),(12,1,3,1,1,1,1,1),(13,1,2,2,1,3,1,1),(14,1,3,4,1,1,1,1);
/*!40000 ALTER TABLE `pln_roomcount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pln_typology`
--

DROP TABLE IF EXISTS `pln_typology`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pln_typology` (
  `typology_id` int(11) NOT NULL AUTO_INCREMENT,
  `style` varchar(200) NOT NULL,
  PRIMARY KEY (`typology_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pln_typology`
--

LOCK TABLES `pln_typology` WRITE;
/*!40000 ALTER TABLE `pln_typology` DISABLE KEYS */;
INSERT INTO `pln_typology` VALUES (1,'Country House'),(2,'Farm House'),(3,'Town House'),(4,'Apartments'),(5,'Go-Downs');
/*!40000 ALTER TABLE `pln_typology` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-25 11:18:30
