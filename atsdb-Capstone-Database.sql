-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: atsdb
-- ------------------------------------------------------
-- Server version	8.4.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `airports`
--

DROP TABLE IF EXISTS `airports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `airports` (
  `port_id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  `country` varchar(255) NOT NULL,
  PRIMARY KEY (`port_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `airports`
--

/*!40000 ALTER TABLE `airports` DISABLE KEYS */;
INSERT INTO `airports` VALUES ('AP0001','Visakhapatnam Airport','Visakhapatnam','India'),('AP0002','Chennai Airport','Chennai','India');
/*!40000 ALTER TABLE `airports` ENABLE KEYS */;

--
-- Table structure for table `booking_details`
--

DROP TABLE IF EXISTS `booking_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking_details` (
  `booking_id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `booking_date` date NOT NULL,
  `seat_number` int NOT NULL,
  `ticket_price` int NOT NULL,
  `total` int NOT NULL,
  `departure_date` date NOT NULL,
  `departure_time` time NOT NULL,
  `arrival_date` date NOT NULL,
  `arrival_time` time NOT NULL,
  PRIMARY KEY (`booking_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking_details`
--

/*!40000 ALTER TABLE `booking_details` DISABLE KEYS */;
INSERT INTO `booking_details` VALUES ('BD0001','Nandu','2024-08-22',12,100,100,'2024-08-23','15:00:00','2024-08-23','18:00:00'),('BD0002','Chintu','2024-08-25',22,150,50,'2024-08-27','15:00:00','2024-08-28','18:00:00'),('BD0003','Bindu','2024-08-25',30,250,100,'2024-08-29','15:00:00','2024-08-30','18:00:00'),('BD0004','Nandu','2024-08-24',21,150,150,'2024-08-30','14:00:00','2024-08-31','16:00:00'),('BD0005','Nandu','2024-08-24',31,150,150,'2024-08-30','14:00:00','2024-08-31','16:00:00'),('BD0006','Bindu','2024-08-26',35,150,150,'2024-08-30','14:00:00','2024-08-31','16:00:00');
/*!40000 ALTER TABLE `booking_details` ENABLE KEYS */;

--
-- Table structure for table `login_details`
--

DROP TABLE IF EXISTS `login_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `login_details` (
  `user_id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `email_id` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone_number` varchar(20) NOT NULL,
  `failed_attempts` int NOT NULL DEFAULT '0',
  `is_account_locked` tinyint(1) NOT NULL DEFAULT '0',
  `lock_time` datetime DEFAULT NULL,
  `last_login` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email_id` (`email_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login_details`
--

/*!40000 ALTER TABLE `login_details` DISABLE KEYS */;
INSERT INTO `login_details` VALUES ('UI0001','Chintu','chintu@example.com','Chintu123!','9666111333',0,0,NULL,'2024-08-23 10:22:27'),('UI0002','Nandu','Nandu@example.com','Nandu@12','9106111533',3,1,'2024-08-26 10:27:44','2024-08-25 20:59:01'),('UI0003','Bindu','Bindudu@example.com','Binduu!65','9000002533',0,0,NULL,NULL),('UI0004','Chandu','Chandu@example.com','Chan@125','9002302533',0,0,NULL,NULL),('UI0005','Raju ch','raju@example.com','Rajuu@15','9000543210',0,0,NULL,NULL),('UI0006','Manvi','manu@example.com','Manvi@10','9002306233',0,0,NULL,NULL),('UI0007','Manvi','manuuu@example.com','Manvi@10','9023306233',0,0,NULL,NULL),('UI0008','Tanvi','Tanvii@example.com','Tanvi@110','9023306033',0,0,NULL,NULL);
/*!40000 ALTER TABLE `login_details` ENABLE KEYS */;

--
-- Table structure for table `planes`
--

DROP TABLE IF EXISTS `planes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `planes` (
  `plane_id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `capacity` int NOT NULL,
  `model` varchar(255) NOT NULL,
  `manufacturer` varchar(255) NOT NULL,
  PRIMARY KEY (`plane_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `planes`
--

/*!40000 ALTER TABLE `planes` DISABLE KEYS */;
INSERT INTO `planes` VALUES ('PI0001','Indigo',600,'70-600','Indigo'),('PI0002','Indigo',600,'70-500','Indigo'),('PI0003','Indigo',800,'70-550','Indigo');
/*!40000 ALTER TABLE `planes` ENABLE KEYS */;

--
-- Dumping routines for database 'atsdb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-08-26 14:30:39
