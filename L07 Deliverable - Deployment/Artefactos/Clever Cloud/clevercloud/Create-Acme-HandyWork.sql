start transaction;
drop database if exists `bbvscjgbdllenjrmuvzm`;
create database `bbvscjgbdllenjrmuvzm`;
use `bbvscjgbdllenjrmuvzm`;

-- MySQL dump 10.13  Distrib 5.5.29, for Win64 (x86)
--
-- Host: localhost    Database: Acme-HandyWork
-- ------------------------------------------------------
-- Server version	5.5.29

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
-- Table structure for table `actor_boxes`
--

DROP TABLE IF EXISTS `actor_boxes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor_boxes` (
  `actor` int(11) NOT NULL,
  `boxes` int(11) NOT NULL,
  UNIQUE KEY `UK_6n6psqivvjho155qcf9kjvv1h` (`boxes`),
  CONSTRAINT `FK_6n6psqivvjho155qcf9kjvv1h` FOREIGN KEY (`boxes`) REFERENCES `box` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor_boxes`
--

LOCK TABLES `actor_boxes` WRITE;
/*!40000 ALTER TABLE `actor_boxes` DISABLE KEYS */;
INSERT INTO `actor_boxes` VALUES (33,34),(33,35),(33,36),(33,37);
/*!40000 ALTER TABLE `actor_boxes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actor_profiles`
--

DROP TABLE IF EXISTS `actor_profiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor_profiles` (
  `actor` int(11) NOT NULL,
  `profiles` int(11) NOT NULL,
  UNIQUE KEY `UK_hdd4x67ooucg6f2yfe4edbs52` (`profiles`),
  CONSTRAINT `FK_hdd4x67ooucg6f2yfe4edbs52` FOREIGN KEY (`profiles`) REFERENCES `profile` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor_profiles`
--

LOCK TABLES `actor_profiles` WRITE;
/*!40000 ALTER TABLE `actor_profiles` DISABLE KEYS */;
INSERT INTO `actor_profiles` VALUES (33,32);
/*!40000 ALTER TABLE `actor_profiles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `administrator` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `adress` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `suspicious` bit(1) NOT NULL,
  `user_account_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_2a5vcjo3stlfcwadosjfq49l1` (`user_account_id`),
  CONSTRAINT `FK_2a5vcjo3stlfcwadosjfq49l1` FOREIGN KEY (`user_account_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` VALUES (33,0,'C/Reina Mercedes','administrator@us.es','Administrator1Middle','Administrator1','954152635','https://www.photo.com','Administrator1Surname','\0',31);
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `application`
--

DROP TABLE IF EXISTS `application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `application` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `brand_name` varchar(255) DEFAULT NULL,
  `codecvv` int(11) DEFAULT NULL,
  `expiration` datetime DEFAULT NULL,
  `holder_name` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `moment` date DEFAULT NULL,
  `moment_elapsed` date DEFAULT NULL,
  `offered_price` double NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `fix_up_task` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_i544pbdabjdesit0c4afue6pl` (`fix_up_task`),
  CONSTRAINT `FK_i544pbdabjdesit0c4afue6pl` FOREIGN KEY (`fix_up_task`) REFERENCES `fix_up_task` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application`
--

LOCK TABLES `application` WRITE;
/*!40000 ALTER TABLE `application` DISABLE KEYS */;
/*!40000 ALTER TABLE `application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `application_comments`
--

DROP TABLE IF EXISTS `application_comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `application_comments` (
  `application` int(11) NOT NULL,
  `comments` varchar(255) DEFAULT NULL,
  KEY `FK_asntu2obphtnxwbuuh83c3c13` (`application`),
  CONSTRAINT `FK_asntu2obphtnxwbuuh83c3c13` FOREIGN KEY (`application`) REFERENCES `application` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_comments`
--

LOCK TABLES `application_comments` WRITE;
/*!40000 ALTER TABLE `application_comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `application_comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `box`
--

DROP TABLE IF EXISTS `box`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `box` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `from_system` bit(1) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `box`
--

LOCK TABLES `box` WRITE;
/*!40000 ALTER TABLE `box` DISABLE KEYS */;
INSERT INTO `box` VALUES (34,0,'','In Box'),(35,0,'','Out Box'),(36,0,'','Spam Box'),(37,0,'','Trash Box');
/*!40000 ALTER TABLE `box` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (38,0,'Carprenty'),(39,0,'Ceiling repairs'),(40,0,'Cleaning'),(41,0,'Concrete Work'),(42,0,'Doors'),(43,0,'Electrical wiring'),(44,0,'Fan installation'),(45,0,'Fence Fixing'),(46,0,'Home security systems'),(47,0,'Insulation installation'),(48,0,'Lamp repairs'),(49,0,'Moving'),(50,0,'Painting'),(51,0,'Pest control'),(52,0,'Plumbing repairs'),(53,0,'Roofing'),(54,0,'Shelf installation'),(55,0,'Solar panels'),(56,0,'Soundproofing'),(57,0,'Sprinkler repair'),(58,0,'Window repair');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category_categories`
--

DROP TABLE IF EXISTS `category_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category_categories` (
  `category` int(11) NOT NULL,
  `categories` int(11) NOT NULL,
  UNIQUE KEY `UK_7um9d6vh8tpm6caj3ics9jjax` (`categories`),
  KEY `FK_s52fgdy2v2gjx1b795j9l9ua6` (`category`),
  CONSTRAINT `FK_s52fgdy2v2gjx1b795j9l9ua6` FOREIGN KEY (`category`) REFERENCES `category` (`id`),
  CONSTRAINT `FK_7um9d6vh8tpm6caj3ics9jjax` FOREIGN KEY (`categories`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_categories`
--

LOCK TABLES `category_categories` WRITE;
/*!40000 ALTER TABLE `category_categories` DISABLE KEYS */;
/*!40000 ALTER TABLE `category_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category_otherlanguages`
--

DROP TABLE IF EXISTS `category_otherlanguages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category_otherlanguages` (
  `category` int(11) NOT NULL,
  `otherlanguages` varchar(255) DEFAULT NULL,
  KEY `FK_tcsewp5xq01u5qa93xp4m0ee8` (`category`),
  CONSTRAINT `FK_tcsewp5xq01u5qa93xp4m0ee8` FOREIGN KEY (`category`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_otherlanguages`
--

LOCK TABLES `category_otherlanguages` WRITE;
/*!40000 ALTER TABLE `category_otherlanguages` DISABLE KEYS */;
INSERT INTO `category_otherlanguages` VALUES (38,'Carpinteria'),(39,'Reparación de techos'),(40,'Limpieza'),(41,'Trabajo de hormigón'),(42,'Puertas'),(43,'Cableado electrico'),(44,'Instalación de aire acondicionado'),(45,'Reparación de vallas'),(46,'Sistemas de seguridad'),(47,'Trabajo de aislamiento'),(48,'Reparación de lamparas'),(49,'Reparación de motores'),(50,'Pintura'),(51,'Control de plagas'),(52,'Fontaneria'),(53,'Trabajo de techos'),(54,'Instalación de estanterias'),(55,'Paneles solares'),(56,'Aislamiento de sonido'),(57,'Reparación de aspersores'),(58,'Reparación de ventanas');
/*!40000 ALTER TABLE `category_otherlanguages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `complaint`
--

DROP TABLE IF EXISTS `complaint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `complaint` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `moment` date DEFAULT NULL,
  `ticker` varchar(255) DEFAULT NULL,
  `referee` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_jlpo668tu0b8mmsjsg8g13inu` (`ticker`),
  KEY `FK_n7kqs8a7c2q1jwjcc44oticll` (`referee`),
  CONSTRAINT `FK_n7kqs8a7c2q1jwjcc44oticll` FOREIGN KEY (`referee`) REFERENCES `referee` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `complaint`
--

LOCK TABLES `complaint` WRITE;
/*!40000 ALTER TABLE `complaint` DISABLE KEYS */;
/*!40000 ALTER TABLE `complaint` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `complaint_attachment`
--

DROP TABLE IF EXISTS `complaint_attachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `complaint_attachment` (
  `complaint` int(11) NOT NULL,
  `attachment` varchar(255) DEFAULT NULL,
  KEY `FK_gf1y3fnhbb27v294jickim98o` (`complaint`),
  CONSTRAINT `FK_gf1y3fnhbb27v294jickim98o` FOREIGN KEY (`complaint`) REFERENCES `complaint` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `complaint_attachment`
--

LOCK TABLES `complaint_attachment` WRITE;
/*!40000 ALTER TABLE `complaint_attachment` DISABLE KEYS */;
/*!40000 ALTER TABLE `complaint_attachment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curriculum`
--

DROP TABLE IF EXISTS `curriculum`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curriculum` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `ticker` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_3ai7h3tynp97g8r0g93r84m8w` (`ticker`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curriculum`
--

LOCK TABLES `curriculum` WRITE;
/*!40000 ALTER TABLE `curriculum` DISABLE KEYS */;
/*!40000 ALTER TABLE `curriculum` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curriculum_education_record`
--

DROP TABLE IF EXISTS `curriculum_education_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curriculum_education_record` (
  `curriculum` int(11) NOT NULL,
  `education_record` int(11) NOT NULL,
  KEY `FK_swaim73hvcfmy5ixc5bhenjvj` (`education_record`),
  KEY `FK_ckat1tstfh2td5pfj6ammqc2n` (`curriculum`),
  CONSTRAINT `FK_ckat1tstfh2td5pfj6ammqc2n` FOREIGN KEY (`curriculum`) REFERENCES `curriculum` (`id`),
  CONSTRAINT `FK_swaim73hvcfmy5ixc5bhenjvj` FOREIGN KEY (`education_record`) REFERENCES `education_record` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curriculum_education_record`
--

LOCK TABLES `curriculum_education_record` WRITE;
/*!40000 ALTER TABLE `curriculum_education_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `curriculum_education_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curriculum_endorser_record`
--

DROP TABLE IF EXISTS `curriculum_endorser_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curriculum_endorser_record` (
  `curriculum` int(11) NOT NULL,
  `endorser_record` int(11) NOT NULL,
  KEY `FK_a5tygogek39wfegvkn57107qh` (`endorser_record`),
  KEY `FK_1ow10dwsshhqwgwum9tb50r8x` (`curriculum`),
  CONSTRAINT `FK_1ow10dwsshhqwgwum9tb50r8x` FOREIGN KEY (`curriculum`) REFERENCES `curriculum` (`id`),
  CONSTRAINT `FK_a5tygogek39wfegvkn57107qh` FOREIGN KEY (`endorser_record`) REFERENCES `endorser_record` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curriculum_endorser_record`
--

LOCK TABLES `curriculum_endorser_record` WRITE;
/*!40000 ALTER TABLE `curriculum_endorser_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `curriculum_endorser_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curriculum_miscellaneous_record`
--

DROP TABLE IF EXISTS `curriculum_miscellaneous_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curriculum_miscellaneous_record` (
  `curriculum` int(11) NOT NULL,
  `miscellaneous_record` int(11) NOT NULL,
  KEY `FK_qhqx7r6gxa1fuxgtcrhma55y6` (`miscellaneous_record`),
  KEY `FK_cm6woc0v3lcy06e4f9f3nbbgm` (`curriculum`),
  CONSTRAINT `FK_cm6woc0v3lcy06e4f9f3nbbgm` FOREIGN KEY (`curriculum`) REFERENCES `curriculum` (`id`),
  CONSTRAINT `FK_qhqx7r6gxa1fuxgtcrhma55y6` FOREIGN KEY (`miscellaneous_record`) REFERENCES `miscellaneous_record` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curriculum_miscellaneous_record`
--

LOCK TABLES `curriculum_miscellaneous_record` WRITE;
/*!40000 ALTER TABLE `curriculum_miscellaneous_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `curriculum_miscellaneous_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curriculum_professional_record`
--

DROP TABLE IF EXISTS `curriculum_professional_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curriculum_professional_record` (
  `curriculum` int(11) NOT NULL,
  `professional_record` int(11) NOT NULL,
  KEY `FK_9f74r0oqdsbayf4fbdu4dhhej` (`professional_record`),
  KEY `FK_etym1a8lrau19bpdge9plpt25` (`curriculum`),
  CONSTRAINT `FK_etym1a8lrau19bpdge9plpt25` FOREIGN KEY (`curriculum`) REFERENCES `curriculum` (`id`),
  CONSTRAINT `FK_9f74r0oqdsbayf4fbdu4dhhej` FOREIGN KEY (`professional_record`) REFERENCES `professional_record` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curriculum_professional_record`
--

LOCK TABLES `curriculum_professional_record` WRITE;
/*!40000 ALTER TABLE `curriculum_professional_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `curriculum_professional_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `adress` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `suspicious` bit(1) NOT NULL,
  `user_account_id` int(11) NOT NULL,
  `score` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_4qm52n85kwhmxj2nktmj1kv9h` (`user_account_id`),
  CONSTRAINT `FK_4qm52n85kwhmxj2nktmj1kv9h` FOREIGN KEY (`user_account_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_complaint`
--

DROP TABLE IF EXISTS `customer_complaint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer_complaint` (
  `customer` int(11) NOT NULL,
  `complaint` int(11) NOT NULL,
  UNIQUE KEY `UK_6m6bj3xvsycxec736256o24k3` (`complaint`),
  KEY `FK_6nip9au2cr87aaglarwrfma92` (`customer`),
  CONSTRAINT `FK_6nip9au2cr87aaglarwrfma92` FOREIGN KEY (`customer`) REFERENCES `customer` (`id`),
  CONSTRAINT `FK_6m6bj3xvsycxec736256o24k3` FOREIGN KEY (`complaint`) REFERENCES `complaint` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_complaint`
--

LOCK TABLES `customer_complaint` WRITE;
/*!40000 ALTER TABLE `customer_complaint` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer_complaint` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_fix_up_task`
--

DROP TABLE IF EXISTS `customer_fix_up_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer_fix_up_task` (
  `customer` int(11) NOT NULL,
  `fix_up_task` int(11) NOT NULL,
  UNIQUE KEY `UK_lmyvuk4ios3yk656whygrmpla` (`fix_up_task`),
  KEY `FK_48onibykdw1ro2eoqav3bxam` (`customer`),
  CONSTRAINT `FK_48onibykdw1ro2eoqav3bxam` FOREIGN KEY (`customer`) REFERENCES `customer` (`id`),
  CONSTRAINT `FK_lmyvuk4ios3yk656whygrmpla` FOREIGN KEY (`fix_up_task`) REFERENCES `fix_up_task` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_fix_up_task`
--

LOCK TABLES `customer_fix_up_task` WRITE;
/*!40000 ALTER TABLE `customer_fix_up_task` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer_fix_up_task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_notes`
--

DROP TABLE IF EXISTS `customer_notes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer_notes` (
  `customer` int(11) NOT NULL,
  `notes` int(11) NOT NULL,
  UNIQUE KEY `UK_pbsu2jt5r8hmmwphv8sutew2y` (`notes`),
  KEY `FK_erywlkd5vs7qo0b258ve7ms4p` (`customer`),
  CONSTRAINT `FK_erywlkd5vs7qo0b258ve7ms4p` FOREIGN KEY (`customer`) REFERENCES `customer` (`id`),
  CONSTRAINT `FK_pbsu2jt5r8hmmwphv8sutew2y` FOREIGN KEY (`notes`) REFERENCES `note` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_notes`
--

LOCK TABLES `customer_notes` WRITE;
/*!40000 ALTER TABLE `customer_notes` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer_notes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customisation_system`
--

DROP TABLE IF EXISTS `customisation_system`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customisation_system` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `banner` varchar(255) DEFAULT NULL,
  `hours_finder` int(11) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `phone_prefix` int(11) DEFAULT NULL,
  `result_finder` int(11) DEFAULT NULL,
  `system_name` varchar(255) DEFAULT NULL,
  `vat` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customisation_system`
--

LOCK TABLES `customisation_system` WRITE;
/*!40000 ALTER TABLE `customisation_system` DISABLE KEYS */;
INSERT INTO `customisation_system` VALUES (59,0,'https://i.imgur.com/Xu6aiSe.png',2,'Welcome to Acme Handy Worker! Price, quality, and trust in a single place. <br> ¡Bienvenidos a Acme Handy Worker! Precio, calidad y confianza en el mismo sitio.',34,15,'Acme-HandyWorker',0.21);
/*!40000 ALTER TABLE `customisation_system` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customisation_system_bad_words`
--

DROP TABLE IF EXISTS `customisation_system_bad_words`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customisation_system_bad_words` (
  `customisation_system` int(11) NOT NULL,
  `bad_words` varchar(255) DEFAULT NULL,
  KEY `FK_7a95xaw5qq7xld7a6eo6d9l5k` (`customisation_system`),
  CONSTRAINT `FK_7a95xaw5qq7xld7a6eo6d9l5k` FOREIGN KEY (`customisation_system`) REFERENCES `customisation_system` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customisation_system_bad_words`
--

LOCK TABLES `customisation_system_bad_words` WRITE;
/*!40000 ALTER TABLE `customisation_system_bad_words` DISABLE KEYS */;
INSERT INTO `customisation_system_bad_words` VALUES (59,'not'),(59,'bad'),(59,'horrible'),(59,'average'),(59,'disaster'),(59,'no'),(59,'malo'),(59,'horrible'),(59,'media'),(59,'desstre');
/*!40000 ALTER TABLE `customisation_system_bad_words` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customisation_system_good_words`
--

DROP TABLE IF EXISTS `customisation_system_good_words`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customisation_system_good_words` (
  `customisation_system` int(11) NOT NULL,
  `good_words` varchar(255) DEFAULT NULL,
  KEY `FK_9cld8fahedak69sq167mkf6by` (`customisation_system`),
  CONSTRAINT `FK_9cld8fahedak69sq167mkf6by` FOREIGN KEY (`customisation_system`) REFERENCES `customisation_system` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customisation_system_good_words`
--

LOCK TABLES `customisation_system_good_words` WRITE;
/*!40000 ALTER TABLE `customisation_system_good_words` DISABLE KEYS */;
INSERT INTO `customisation_system_good_words` VALUES (59,'good'),(59,'fantastic'),(59,'excellent'),(59,'great'),(59,'amazing'),(59,'terrific'),(59,'beatiful'),(59,'bueno'),(59,'fantastico'),(59,'excelente'),(59,'genial'),(59,'asombroso'),(59,'estupendo'),(59,'bonito');
/*!40000 ALTER TABLE `customisation_system_good_words` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customisation_system_spam_words`
--

DROP TABLE IF EXISTS `customisation_system_spam_words`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customisation_system_spam_words` (
  `customisation_system` int(11) NOT NULL,
  `spam_words` varchar(255) DEFAULT NULL,
  KEY `FK_2hmee8sh9enh34bygyfw20r0p` (`customisation_system`),
  CONSTRAINT `FK_2hmee8sh9enh34bygyfw20r0p` FOREIGN KEY (`customisation_system`) REFERENCES `customisation_system` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customisation_system_spam_words`
--

LOCK TABLES `customisation_system_spam_words` WRITE;
/*!40000 ALTER TABLE `customisation_system_spam_words` DISABLE KEYS */;
INSERT INTO `customisation_system_spam_words` VALUES (59,'sex'),(59,'viagra'),(59,'cialis'),(59,'one millon'),(59,'you\'ve been selected'),(59,'Nigeria'),(59,'sexo'),(59,'un millón'),(59,'ha sido seleccionado');
/*!40000 ALTER TABLE `customisation_system_spam_words` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `education_record`
--

DROP TABLE IF EXISTS `education_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `education_record` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `attachment` varchar(255) DEFAULT NULL,
  `diploma_title` varchar(255) DEFAULT NULL,
  `end_studies` date DEFAULT NULL,
  `institution_diploma` varchar(255) DEFAULT NULL,
  `start_studies` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `education_record`
--

LOCK TABLES `education_record` WRITE;
/*!40000 ALTER TABLE `education_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `education_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `education_record_comments`
--

DROP TABLE IF EXISTS `education_record_comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `education_record_comments` (
  `education_record` int(11) NOT NULL,
  `comments` varchar(255) DEFAULT NULL,
  KEY `FK_aax0pm4f91pvoctytosg8oi02` (`education_record`),
  CONSTRAINT `FK_aax0pm4f91pvoctytosg8oi02` FOREIGN KEY (`education_record`) REFERENCES `education_record` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `education_record_comments`
--

LOCK TABLES `education_record_comments` WRITE;
/*!40000 ALTER TABLE `education_record_comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `education_record_comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `endorsement`
--

DROP TABLE IF EXISTS `endorsement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `endorsement` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `moment` datetime DEFAULT NULL,
  `user_received` int(11) NOT NULL,
  `user_sended` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `endorsement`
--

LOCK TABLES `endorsement` WRITE;
/*!40000 ALTER TABLE `endorsement` DISABLE KEYS */;
/*!40000 ALTER TABLE `endorsement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `endorsement_comments`
--

DROP TABLE IF EXISTS `endorsement_comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `endorsement_comments` (
  `endorsement` int(11) NOT NULL,
  `comments` varchar(255) DEFAULT NULL,
  KEY `FK_pscx7i90sy9rn656hbdtcot64` (`endorsement`),
  CONSTRAINT `FK_pscx7i90sy9rn656hbdtcot64` FOREIGN KEY (`endorsement`) REFERENCES `endorsement` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `endorsement_comments`
--

LOCK TABLES `endorsement_comments` WRITE;
/*!40000 ALTER TABLE `endorsement_comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `endorsement_comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `endorser_record`
--

DROP TABLE IF EXISTS `endorser_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `endorser_record` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `full_name_endorser` varchar(255) DEFAULT NULL,
  `linkedin` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `endorser_record`
--

LOCK TABLES `endorser_record` WRITE;
/*!40000 ALTER TABLE `endorser_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `endorser_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `endorser_record_comments`
--

DROP TABLE IF EXISTS `endorser_record_comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `endorser_record_comments` (
  `endorser_record` int(11) NOT NULL,
  `comments` varchar(255) DEFAULT NULL,
  KEY `FK_ih84910fhba35ytrq2p7vg9sw` (`endorser_record`),
  CONSTRAINT `FK_ih84910fhba35ytrq2p7vg9sw` FOREIGN KEY (`endorser_record`) REFERENCES `endorser_record` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `endorser_record_comments`
--

LOCK TABLES `endorser_record_comments` WRITE;
/*!40000 ALTER TABLE `endorser_record_comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `endorser_record_comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `finder`
--

DROP TABLE IF EXISTS `finder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `finder` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `creation_date` datetime DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `price1` double DEFAULT NULL,
  `price2` double DEFAULT NULL,
  `single_key` varchar(255) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `category` int(11) DEFAULT NULL,
  `warranty` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_n9t1ayk0x7h5vrsfuhygo043j` (`category`),
  KEY `FK_fsgvely8c4othsty26jul4qfl` (`warranty`),
  CONSTRAINT `FK_fsgvely8c4othsty26jul4qfl` FOREIGN KEY (`warranty`) REFERENCES `warranty` (`id`),
  CONSTRAINT `FK_n9t1ayk0x7h5vrsfuhygo043j` FOREIGN KEY (`category`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `finder`
--

LOCK TABLES `finder` WRITE;
/*!40000 ALTER TABLE `finder` DISABLE KEYS */;
/*!40000 ALTER TABLE `finder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `finder_fix_up_task`
--

DROP TABLE IF EXISTS `finder_fix_up_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `finder_fix_up_task` (
  `finder` int(11) NOT NULL,
  `fix_up_task` int(11) NOT NULL,
  KEY `FK_3vayn26asbv6xkwtfmo94xl3w` (`fix_up_task`),
  KEY `FK_rkebmdefvgdr5q0u7m1eeq253` (`finder`),
  CONSTRAINT `FK_rkebmdefvgdr5q0u7m1eeq253` FOREIGN KEY (`finder`) REFERENCES `finder` (`id`),
  CONSTRAINT `FK_3vayn26asbv6xkwtfmo94xl3w` FOREIGN KEY (`fix_up_task`) REFERENCES `fix_up_task` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `finder_fix_up_task`
--

LOCK TABLES `finder_fix_up_task` WRITE;
/*!40000 ALTER TABLE `finder_fix_up_task` DISABLE KEYS */;
/*!40000 ALTER TABLE `finder_fix_up_task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fix_up_task`
--

DROP TABLE IF EXISTS `fix_up_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fix_up_task` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end` date DEFAULT NULL,
  `maximum_price` double NOT NULL,
  `moment` date DEFAULT NULL,
  `start` date DEFAULT NULL,
  `ticker` varchar(255) DEFAULT NULL,
  `category` int(11) DEFAULT NULL,
  `warranty` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_1ucy18fywvpk17lfcafstl8p` (`ticker`),
  KEY `FK_rraseqm4xixdwpi08ac3s0wo5` (`category`),
  KEY `FK_eeisx1c0ohidkpqgqbib91s6x` (`warranty`),
  CONSTRAINT `FK_eeisx1c0ohidkpqgqbib91s6x` FOREIGN KEY (`warranty`) REFERENCES `warranty` (`id`),
  CONSTRAINT `FK_rraseqm4xixdwpi08ac3s0wo5` FOREIGN KEY (`category`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fix_up_task`
--

LOCK TABLES `fix_up_task` WRITE;
/*!40000 ALTER TABLE `fix_up_task` DISABLE KEYS */;
/*!40000 ALTER TABLE `fix_up_task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fix_up_task_complaint`
--

DROP TABLE IF EXISTS `fix_up_task_complaint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fix_up_task_complaint` (
  `fix_up_task` int(11) NOT NULL,
  `complaint` int(11) NOT NULL,
  UNIQUE KEY `UK_lla5wo088fisyk12jglduftlh` (`complaint`),
  KEY `FK_5r557phi5ltaui6xeh1dt60wr` (`fix_up_task`),
  CONSTRAINT `FK_5r557phi5ltaui6xeh1dt60wr` FOREIGN KEY (`fix_up_task`) REFERENCES `fix_up_task` (`id`),
  CONSTRAINT `FK_lla5wo088fisyk12jglduftlh` FOREIGN KEY (`complaint`) REFERENCES `complaint` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fix_up_task_complaint`
--

LOCK TABLES `fix_up_task_complaint` WRITE;
/*!40000 ALTER TABLE `fix_up_task_complaint` DISABLE KEYS */;
/*!40000 ALTER TABLE `fix_up_task_complaint` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fix_up_task_phases`
--

DROP TABLE IF EXISTS `fix_up_task_phases`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fix_up_task_phases` (
  `fix_up_task` int(11) NOT NULL,
  `phases` int(11) NOT NULL,
  UNIQUE KEY `UK_g0happ3dypgasjxo76l9wv58g` (`phases`),
  KEY `FK_26itr0ptcg08ju96f9is6am1o` (`fix_up_task`),
  CONSTRAINT `FK_26itr0ptcg08ju96f9is6am1o` FOREIGN KEY (`fix_up_task`) REFERENCES `fix_up_task` (`id`),
  CONSTRAINT `FK_g0happ3dypgasjxo76l9wv58g` FOREIGN KEY (`phases`) REFERENCES `phase` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fix_up_task_phases`
--

LOCK TABLES `fix_up_task_phases` WRITE;
/*!40000 ALTER TABLE `fix_up_task_phases` DISABLE KEYS */;
/*!40000 ALTER TABLE `fix_up_task_phases` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `handy_worker`
--

DROP TABLE IF EXISTS `handy_worker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `handy_worker` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `adress` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `suspicious` bit(1) NOT NULL,
  `user_account_id` int(11) NOT NULL,
  `score` double DEFAULT NULL,
  `make` varchar(255) DEFAULT NULL,
  `curriculum` int(11) DEFAULT NULL,
  `finder` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_pm2c30i84i18p3b91vnovwmmj` (`user_account_id`),
  KEY `FK_qilb1l0o66abqy9o4fk8accvs` (`curriculum`),
  KEY `FK_s80hn9dk7bcwsqotvtoo6wxr3` (`finder`),
  CONSTRAINT `FK_pm2c30i84i18p3b91vnovwmmj` FOREIGN KEY (`user_account_id`) REFERENCES `user_account` (`id`),
  CONSTRAINT `FK_qilb1l0o66abqy9o4fk8accvs` FOREIGN KEY (`curriculum`) REFERENCES `curriculum` (`id`),
  CONSTRAINT `FK_s80hn9dk7bcwsqotvtoo6wxr3` FOREIGN KEY (`finder`) REFERENCES `finder` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `handy_worker`
--

LOCK TABLES `handy_worker` WRITE;
/*!40000 ALTER TABLE `handy_worker` DISABLE KEYS */;
/*!40000 ALTER TABLE `handy_worker` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `handy_worker_application`
--

DROP TABLE IF EXISTS `handy_worker_application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `handy_worker_application` (
  `handy_worker` int(11) NOT NULL,
  `application` int(11) NOT NULL,
  UNIQUE KEY `UK_fafsops2d4ipbmwsertfqloc3` (`application`),
  KEY `FK_7u2x52vms3d3i3nke1omeh0m5` (`handy_worker`),
  CONSTRAINT `FK_7u2x52vms3d3i3nke1omeh0m5` FOREIGN KEY (`handy_worker`) REFERENCES `handy_worker` (`id`),
  CONSTRAINT `FK_fafsops2d4ipbmwsertfqloc3` FOREIGN KEY (`application`) REFERENCES `application` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `handy_worker_application`
--

LOCK TABLES `handy_worker_application` WRITE;
/*!40000 ALTER TABLE `handy_worker_application` DISABLE KEYS */;
/*!40000 ALTER TABLE `handy_worker_application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `handy_worker_notes`
--

DROP TABLE IF EXISTS `handy_worker_notes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `handy_worker_notes` (
  `handy_worker` int(11) NOT NULL,
  `notes` int(11) NOT NULL,
  UNIQUE KEY `UK_1wwfrwn8lrjg9rc77inc1n8k1` (`notes`),
  KEY `FK_lec75hv1jvhqi1ni4en03njlu` (`handy_worker`),
  CONSTRAINT `FK_lec75hv1jvhqi1ni4en03njlu` FOREIGN KEY (`handy_worker`) REFERENCES `handy_worker` (`id`),
  CONSTRAINT `FK_1wwfrwn8lrjg9rc77inc1n8k1` FOREIGN KEY (`notes`) REFERENCES `note` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `handy_worker_notes`
--

LOCK TABLES `handy_worker_notes` WRITE;
/*!40000 ALTER TABLE `handy_worker_notes` DISABLE KEYS */;
/*!40000 ALTER TABLE `handy_worker_notes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `handy_worker_tutorials`
--

DROP TABLE IF EXISTS `handy_worker_tutorials`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `handy_worker_tutorials` (
  `handy_worker` int(11) NOT NULL,
  `tutorials` int(11) NOT NULL,
  UNIQUE KEY `UK_5xqh3bm4r621glqojes715rid` (`tutorials`),
  KEY `FK_ipumwi0ufisd6j8trvvr1sqlb` (`handy_worker`),
  CONSTRAINT `FK_ipumwi0ufisd6j8trvvr1sqlb` FOREIGN KEY (`handy_worker`) REFERENCES `handy_worker` (`id`),
  CONSTRAINT `FK_5xqh3bm4r621glqojes715rid` FOREIGN KEY (`tutorials`) REFERENCES `tutorial` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `handy_worker_tutorials`
--

LOCK TABLES `handy_worker_tutorials` WRITE;
/*!40000 ALTER TABLE `handy_worker_tutorials` DISABLE KEYS */;
/*!40000 ALTER TABLE `handy_worker_tutorials` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequences`
--

DROP TABLE IF EXISTS `hibernate_sequences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) DEFAULT NULL,
  `sequence_next_hi_value` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequences`
--

LOCK TABLES `hibernate_sequences` WRITE;
/*!40000 ALTER TABLE `hibernate_sequences` DISABLE KEYS */;
INSERT INTO `hibernate_sequences` VALUES ('domain_entity',1);
/*!40000 ALTER TABLE `hibernate_sequences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mesage`
--

DROP TABLE IF EXISTS `mesage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mesage` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `body` varchar(255) DEFAULT NULL,
  `moment` date DEFAULT NULL,
  `priority` varchar(255) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `sender` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mesage`
--

LOCK TABLES `mesage` WRITE;
/*!40000 ALTER TABLE `mesage` DISABLE KEYS */;
/*!40000 ALTER TABLE `mesage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mesage_box`
--

DROP TABLE IF EXISTS `mesage_box`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mesage_box` (
  `message` int(11) NOT NULL,
  `box` int(11) NOT NULL,
  KEY `FK_pyeait29c4ji8e12um9prhel3` (`box`),
  KEY `FK_6ft3u3nqprk0jkm8rat63s09s` (`message`),
  CONSTRAINT `FK_6ft3u3nqprk0jkm8rat63s09s` FOREIGN KEY (`message`) REFERENCES `mesage` (`id`),
  CONSTRAINT `FK_pyeait29c4ji8e12um9prhel3` FOREIGN KEY (`box`) REFERENCES `box` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mesage_box`
--

LOCK TABLES `mesage_box` WRITE;
/*!40000 ALTER TABLE `mesage_box` DISABLE KEYS */;
/*!40000 ALTER TABLE `mesage_box` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mesage_receiver`
--

DROP TABLE IF EXISTS `mesage_receiver`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mesage_receiver` (
  `mesage` int(11) NOT NULL,
  `receiver` int(11) NOT NULL,
  KEY `FK_kidkohwrgl7vhb2l8ih5pyhaw` (`mesage`),
  CONSTRAINT `FK_kidkohwrgl7vhb2l8ih5pyhaw` FOREIGN KEY (`mesage`) REFERENCES `mesage` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mesage_receiver`
--

LOCK TABLES `mesage_receiver` WRITE;
/*!40000 ALTER TABLE `mesage_receiver` DISABLE KEYS */;
/*!40000 ALTER TABLE `mesage_receiver` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mesage_tags`
--

DROP TABLE IF EXISTS `mesage_tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mesage_tags` (
  `mesage` int(11) NOT NULL,
  `tags` varchar(255) DEFAULT NULL,
  KEY `FK_iwn47lvu2ip5ge2d8vvyrmqea` (`mesage`),
  CONSTRAINT `FK_iwn47lvu2ip5ge2d8vvyrmqea` FOREIGN KEY (`mesage`) REFERENCES `mesage` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mesage_tags`
--

LOCK TABLES `mesage_tags` WRITE;
/*!40000 ALTER TABLE `mesage_tags` DISABLE KEYS */;
/*!40000 ALTER TABLE `mesage_tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `miscellaneous_record`
--

DROP TABLE IF EXISTS `miscellaneous_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `miscellaneous_record` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `attachment` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `miscellaneous_record`
--

LOCK TABLES `miscellaneous_record` WRITE;
/*!40000 ALTER TABLE `miscellaneous_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `miscellaneous_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `miscellaneous_record_comments`
--

DROP TABLE IF EXISTS `miscellaneous_record_comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `miscellaneous_record_comments` (
  `miscellaneous_record` int(11) NOT NULL,
  `comments` varchar(255) DEFAULT NULL,
  KEY `FK_ld8qxycuk2b97nvdk1dp8u99c` (`miscellaneous_record`),
  CONSTRAINT `FK_ld8qxycuk2b97nvdk1dp8u99c` FOREIGN KEY (`miscellaneous_record`) REFERENCES `miscellaneous_record` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `miscellaneous_record_comments`
--

LOCK TABLES `miscellaneous_record_comments` WRITE;
/*!40000 ALTER TABLE `miscellaneous_record_comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `miscellaneous_record_comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `note`
--

DROP TABLE IF EXISTS `note`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `note` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `customer_comment` varchar(255) DEFAULT NULL,
  `handy_worker_comment` varchar(255) DEFAULT NULL,
  `moment` date DEFAULT NULL,
  `referee_comment` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `note`
--

LOCK TABLES `note` WRITE;
/*!40000 ALTER TABLE `note` DISABLE KEYS */;
/*!40000 ALTER TABLE `note` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phase`
--

DROP TABLE IF EXISTS `phase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `phase` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end_moment` date DEFAULT NULL,
  `number` int(11) NOT NULL,
  `start_moment` date DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phase`
--

LOCK TABLES `phase` WRITE;
/*!40000 ALTER TABLE `phase` DISABLE KEYS */;
/*!40000 ALTER TABLE `phase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `professional_record`
--

DROP TABLE IF EXISTS `professional_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `professional_record` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `attachment` varchar(255) DEFAULT NULL,
  `company_name` varchar(255) DEFAULT NULL,
  `end_working` date DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `start_working` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `professional_record`
--

LOCK TABLES `professional_record` WRITE;
/*!40000 ALTER TABLE `professional_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `professional_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `professional_record_comments`
--

DROP TABLE IF EXISTS `professional_record_comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `professional_record_comments` (
  `professional_record` int(11) NOT NULL,
  `comments` varchar(255) DEFAULT NULL,
  KEY `FK_7r5b094ef74saryrrhv7xo8e5` (`professional_record`),
  CONSTRAINT `FK_7r5b094ef74saryrrhv7xo8e5` FOREIGN KEY (`professional_record`) REFERENCES `professional_record` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `professional_record_comments`
--

LOCK TABLES `professional_record_comments` WRITE;
/*!40000 ALTER TABLE `professional_record_comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `professional_record_comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profile`
--

DROP TABLE IF EXISTS `profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `profile` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `link` varchar(255) DEFAULT NULL,
  `nick` varchar(255) DEFAULT NULL,
  `social_network_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profile`
--

LOCK TABLES `profile` WRITE;
/*!40000 ALTER TABLE `profile` DISABLE KEYS */;
INSERT INTO `profile` VALUES (32,0,'https://www.linkedin/DP1819','DP1819','LinkedIn');
/*!40000 ALTER TABLE `profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `referee`
--

DROP TABLE IF EXISTS `referee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `referee` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `adress` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `suspicious` bit(1) NOT NULL,
  `user_account_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_2yosw1qwkhoq88d34cj0c8csh` (`user_account_id`),
  CONSTRAINT `FK_2yosw1qwkhoq88d34cj0c8csh` FOREIGN KEY (`user_account_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `referee`
--

LOCK TABLES `referee` WRITE;
/*!40000 ALTER TABLE `referee` DISABLE KEYS */;
/*!40000 ALTER TABLE `referee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `referee_notes`
--

DROP TABLE IF EXISTS `referee_notes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `referee_notes` (
  `referee` int(11) NOT NULL,
  `notes` int(11) NOT NULL,
  UNIQUE KEY `UK_moduja6v2mvft8gxyk5j30pou` (`notes`),
  KEY `FK_ocermyjoxia3i2pcvchivp3pq` (`referee`),
  CONSTRAINT `FK_ocermyjoxia3i2pcvchivp3pq` FOREIGN KEY (`referee`) REFERENCES `referee` (`id`),
  CONSTRAINT `FK_moduja6v2mvft8gxyk5j30pou` FOREIGN KEY (`notes`) REFERENCES `note` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `referee_notes`
--

LOCK TABLES `referee_notes` WRITE;
/*!40000 ALTER TABLE `referee_notes` DISABLE KEYS */;
/*!40000 ALTER TABLE `referee_notes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `report` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `final_mode` bit(1) DEFAULT NULL,
  `moment` date DEFAULT NULL,
  `complaint` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_48rqaecflpcs8unotw4drrtfw` (`complaint`),
  CONSTRAINT `FK_48rqaecflpcs8unotw4drrtfw` FOREIGN KEY (`complaint`) REFERENCES `complaint` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report_attachments`
--

DROP TABLE IF EXISTS `report_attachments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `report_attachments` (
  `report` int(11) NOT NULL,
  `attachments` varchar(255) DEFAULT NULL,
  KEY `FK_8pqwcub4o2xip8o8ohqk3bu05` (`report`),
  CONSTRAINT `FK_8pqwcub4o2xip8o8ohqk3bu05` FOREIGN KEY (`report`) REFERENCES `report` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report_attachments`
--

LOCK TABLES `report_attachments` WRITE;
/*!40000 ALTER TABLE `report_attachments` DISABLE KEYS */;
/*!40000 ALTER TABLE `report_attachments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report_notes`
--

DROP TABLE IF EXISTS `report_notes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `report_notes` (
  `report` int(11) NOT NULL,
  `notes` int(11) NOT NULL,
  KEY `FK_m22isl38uqck3fp3rmsmbnh5k` (`notes`),
  KEY `FK_o7r37khw24dlpe07md1qqakce` (`report`),
  CONSTRAINT `FK_o7r37khw24dlpe07md1qqakce` FOREIGN KEY (`report`) REFERENCES `report` (`id`),
  CONSTRAINT `FK_m22isl38uqck3fp3rmsmbnh5k` FOREIGN KEY (`notes`) REFERENCES `note` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report_notes`
--

LOCK TABLES `report_notes` WRITE;
/*!40000 ALTER TABLE `report_notes` DISABLE KEYS */;
/*!40000 ALTER TABLE `report_notes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `section`
--

DROP TABLE IF EXISTS `section`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `section` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `number` int(11) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `section`
--

LOCK TABLES `section` WRITE;
/*!40000 ALTER TABLE `section` DISABLE KEYS */;
/*!40000 ALTER TABLE `section` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `section_picture`
--

DROP TABLE IF EXISTS `section_picture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `section_picture` (
  `section` int(11) NOT NULL,
  `picture` varchar(255) DEFAULT NULL,
  KEY `FK_fh57fkpnnsolwgi3xej83ow7c` (`section`),
  CONSTRAINT `FK_fh57fkpnnsolwgi3xej83ow7c` FOREIGN KEY (`section`) REFERENCES `section` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `section_picture`
--

LOCK TABLES `section_picture` WRITE;
/*!40000 ALTER TABLE `section_picture` DISABLE KEYS */;
/*!40000 ALTER TABLE `section_picture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sponsor`
--

DROP TABLE IF EXISTS `sponsor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sponsor` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `adress` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `suspicious` bit(1) NOT NULL,
  `user_account_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_20xk0ev32hlg96kqynl6laie2` (`user_account_id`),
  CONSTRAINT `FK_20xk0ev32hlg96kqynl6laie2` FOREIGN KEY (`user_account_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sponsor`
--

LOCK TABLES `sponsor` WRITE;
/*!40000 ALTER TABLE `sponsor` DISABLE KEYS */;
/*!40000 ALTER TABLE `sponsor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sponsorship`
--

DROP TABLE IF EXISTS `sponsorship`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sponsorship` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `brand_name` varchar(255) DEFAULT NULL,
  `codecvv` int(11) DEFAULT NULL,
  `expiration` datetime DEFAULT NULL,
  `holder_name` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `linktpage` varchar(255) DEFAULT NULL,
  `url_banner` varchar(255) DEFAULT NULL,
  `sponsor` int(11) NOT NULL,
  `tutorial` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_huglhkud0ihqdljyou4eshra6` (`sponsor`),
  KEY `FK_hddl83ddicym7ft1xmg89d4c6` (`tutorial`),
  CONSTRAINT `FK_hddl83ddicym7ft1xmg89d4c6` FOREIGN KEY (`tutorial`) REFERENCES `tutorial` (`id`),
  CONSTRAINT `FK_huglhkud0ihqdljyou4eshra6` FOREIGN KEY (`sponsor`) REFERENCES `sponsor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sponsorship`
--

LOCK TABLES `sponsorship` WRITE;
/*!40000 ALTER TABLE `sponsorship` DISABLE KEYS */;
/*!40000 ALTER TABLE `sponsorship` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tutorial`
--

DROP TABLE IF EXISTS `tutorial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tutorial` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `last_update` date DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tutorial`
--

LOCK TABLES `tutorial` WRITE;
/*!40000 ALTER TABLE `tutorial` DISABLE KEYS */;
/*!40000 ALTER TABLE `tutorial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tutorial_picture`
--

DROP TABLE IF EXISTS `tutorial_picture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tutorial_picture` (
  `tutorial` int(11) NOT NULL,
  `picture` varchar(255) DEFAULT NULL,
  KEY `FK_l1glslmb6svq1bs4d3uye99pl` (`tutorial`),
  CONSTRAINT `FK_l1glslmb6svq1bs4d3uye99pl` FOREIGN KEY (`tutorial`) REFERENCES `tutorial` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tutorial_picture`
--

LOCK TABLES `tutorial_picture` WRITE;
/*!40000 ALTER TABLE `tutorial_picture` DISABLE KEYS */;
/*!40000 ALTER TABLE `tutorial_picture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tutorial_section`
--

DROP TABLE IF EXISTS `tutorial_section`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tutorial_section` (
  `tutorial` int(11) NOT NULL,
  `section` int(11) NOT NULL,
  UNIQUE KEY `UK_bfhvigjwro4vvo5ytm8owlwnt` (`section`),
  KEY `FK_4bn6g60jaoxnj2ykjf3k2ui5d` (`tutorial`),
  CONSTRAINT `FK_4bn6g60jaoxnj2ykjf3k2ui5d` FOREIGN KEY (`tutorial`) REFERENCES `tutorial` (`id`),
  CONSTRAINT `FK_bfhvigjwro4vvo5ytm8owlwnt` FOREIGN KEY (`section`) REFERENCES `section` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tutorial_section`
--

LOCK TABLES `tutorial_section` WRITE;
/*!40000 ALTER TABLE `tutorial_section` DISABLE KEYS */;
/*!40000 ALTER TABLE `tutorial_section` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_account`
--

DROP TABLE IF EXISTS `user_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_account` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_castjbvpeeus0r8lbpehiu0e4` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account`
--

LOCK TABLES `user_account` WRITE;
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;
INSERT INTO `user_account` VALUES (31,0,'','e00cf25ad42683b3df678c61f42c6bda','admin1');
/*!40000 ALTER TABLE `user_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_account_authorities`
--

DROP TABLE IF EXISTS `user_account_authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_account_authorities` (
  `user_account` int(11) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  KEY `FK_pao8cwh93fpccb0bx6ilq6gsl` (`user_account`),
  CONSTRAINT `FK_pao8cwh93fpccb0bx6ilq6gsl` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account_authorities`
--

LOCK TABLES `user_account_authorities` WRITE;
/*!40000 ALTER TABLE `user_account_authorities` DISABLE KEYS */;
INSERT INTO `user_account_authorities` VALUES (31,'ADMIN');
/*!40000 ALTER TABLE `user_account_authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `warranty`
--

DROP TABLE IF EXISTS `warranty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `warranty` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `draft_mode` bit(1) NOT NULL,
  `laws` varchar(255) DEFAULT NULL,
  `terms` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `warranty`
--

LOCK TABLES `warranty` WRITE;
/*!40000 ALTER TABLE `warranty` DISABLE KEYS */;
INSERT INTO `warranty` VALUES (60,0,'\0','Lorem Ipsum is simply dummy text of the printing and typesetting industry.','TerminosPredeterminadosFP123456','Decreto Ley 2014/13');
/*!40000 ALTER TABLE `warranty` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-17 22:08:00
commit;