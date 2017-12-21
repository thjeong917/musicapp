-- MySQL dump 10.13  Distrib 5.7.20, for osx10.12 (x86_64)
--
-- Host: localhost    Database: musicapp
-- ------------------------------------------------------
-- Server version	5.7.20

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
-- Table structure for table `album`
--

DROP TABLE IF EXISTS `album`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `album` (
  `Title` varchar(30) NOT NULL,
  `ReleasedDate` date DEFAULT NULL,
  `Album_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Artist_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`Album_ID`),
  KEY `Artist_ID` (`Artist_ID`),
  CONSTRAINT `album_ibfk_1` FOREIGN KEY (`Artist_ID`) REFERENCES `artist` (`Artist_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `album`
--

LOCK TABLES `album` WRITE;
/*!40000 ALTER TABLE `album` DISABLE KEYS */;
INSERT INTO `album` VALUES ('Real','2010-12-09',1,1),('Modern Times','2013-10-08',2,1),('CHAT-SHIRE','2015-10-23',3,1),('Happiness','2014-08-04',4,2),('The Red','2015-09-09',5,2),('Rookie','2017-02-01',6,2),('Some Type of Love','2015-05-06',7,3),('Nine Track Mind','2016-11-11',8,3),('PAGE TWO','2016-04-25',9,4),('TWICEcoaster : LANE 1','2016-10-24',10,4),('twicetagram','2017-10-30',11,4),('19','2008-01-27',12,5),('21','2011-01-21',13,5),('25','2015-11-20',14,5),('Songs About Jane','2003-09-06',15,6),('It Won\'t Be Soon Before Long','2007-05-22',16,6),('Hands All Over','2011-07-14',17,6),('Overexposed','2012-06-25',18,6);
/*!40000 ALTER TABLE `album` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `artist`
--

DROP TABLE IF EXISTS `artist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `artist` (
  `Name` varchar(30) NOT NULL,
  `Sex` char(1) DEFAULT NULL,
  `DebutDate` date DEFAULT NULL,
  `Artist_ID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`Artist_ID`),
  UNIQUE KEY `Name` (`Name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artist`
--

LOCK TABLES `artist` WRITE;
/*!40000 ALTER TABLE `artist` DISABLE KEYS */;
INSERT INTO `artist` VALUES ('IU','F','2008-09-18',1),('Red Velvet','G','2014-08-01',2),('Charlie Puth','M','2015-02-26',3),('TWICE','G','2015-10-20',4),('Adele','F','2008-01-20',5),('Maroon 5','G','2003-09-06',6);
/*!40000 ALTER TABLE `artist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favorite_artist`
--

DROP TABLE IF EXISTS `favorite_artist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `favorite_artist` (
  `User_ID` int(11) NOT NULL,
  `Artist_ID` int(11) NOT NULL,
  PRIMARY KEY (`User_ID`,`Artist_ID`),
  KEY `Artist_ID` (`Artist_ID`),
  CONSTRAINT `favorite_artist_ibfk_1` FOREIGN KEY (`User_ID`) REFERENCES `user` (`User_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `favorite_artist_ibfk_2` FOREIGN KEY (`Artist_ID`) REFERENCES `artist` (`Artist_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favorite_artist`
--

LOCK TABLES `favorite_artist` WRITE;
/*!40000 ALTER TABLE `favorite_artist` DISABLE KEYS */;
INSERT INTO `favorite_artist` VALUES (1,1),(1,2),(2,2),(3,2),(1,3),(2,3),(3,3),(1,4),(2,4),(1,5),(2,5),(3,5),(1,6),(2,6),(3,6),(4,6);
/*!40000 ALTER TABLE `favorite_artist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manager`
--

DROP TABLE IF EXISTS `manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `manager` (
  `Name` varchar(30) NOT NULL,
  `Sex` char(1) DEFAULT NULL,
  `MgrStartDate` date DEFAULT NULL,
  `Mgr_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Login_ID` varchar(30) NOT NULL,
  `Password` varchar(30) NOT NULL,
  PRIMARY KEY (`Mgr_ID`),
  UNIQUE KEY `Login_ID` (`Login_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manager`
--

LOCK TABLES `manager` WRITE;
/*!40000 ALTER TABLE `manager` DISABLE KEYS */;
INSERT INTO `manager` VALUES ('Manager1','M','2007-01-01',1,'admin','1234'),('Manager2','F','2007-01-01',2,'admin2','1234'),('Manager3','M','2007-01-01',3,'admin3','1234');
/*!40000 ALTER TABLE `manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `music`
--

DROP TABLE IF EXISTS `music`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `music` (
  `Title` varchar(30) NOT NULL,
  `Genre` varchar(10) DEFAULT NULL,
  `Music_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Album_ID` int(11) DEFAULT NULL,
  `Artist_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`Music_ID`),
  KEY `Album_ID` (`Album_ID`),
  KEY `Artist_ID` (`Artist_ID`),
  CONSTRAINT `music_ibfk_1` FOREIGN KEY (`Album_ID`) REFERENCES `album` (`Album_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `music_ibfk_2` FOREIGN KEY (`Artist_ID`) REFERENCES `artist` (`Artist_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `music`
--

LOCK TABLES `music` WRITE;
/*!40000 ALTER TABLE `music` DISABLE KEYS */;
INSERT INTO `music` VALUES ('Good Day','Dance',1,1,1),('Voice Mail','Ballad',2,2,1),('23','Ballad',3,3,1),('Happiness','Dance',4,4,2),('Dumb Dumb','Dance',5,5,2),('Rookie','Dance',6,6,2),('Marvin Gaye','R&B',7,7,3),('Some Type of Love','R&B',8,7,3),('One Call Away','Pop',9,8,3),('We Don\'t Talk Anymore','Pop',10,8,3),('River','Pop',11,8,3),('CHEER UP','Dance',12,9,4),('Touchdown','Pop',13,9,4),('TT','Dance',14,10,4),('JELLY JELLY','Dance',15,10,4),('LIKEY','Dance',16,11,4),('MISSING U','Dance',17,11,4),('Chasing Pavements','Ballad',18,12,5),('Rolling In The Deep','Ballad',19,13,5),('Set Fire To The Rain','Ballad',20,13,5),('Someone Like You','Ballad',21,13,5),('Hello','Ballad',22,14,5),('When We Were Young','Ballad',23,14,5),('This Love','Rock',24,15,6),('She Will Be Loved','Rock',25,15,6),('Sunday Morning','Rock',26,15,6),('Makes Me Wonder','Rock',27,16,6),('Wake Up Call','Rock',28,16,6),('Misery','Rock',29,17,6),('Give A Little More','Rock',30,17,6),('Stutter','Rock',31,17,6),('Moves Like Jagger','Rock',32,17,6),('One More Night','Rock',33,18,6),('Payphone','Rock',34,18,6),('Lucky Strike','Rock',35,18,6);
/*!40000 ALTER TABLE `music` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playlist`
--

DROP TABLE IF EXISTS `playlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `playlist` (
  `Name` varchar(30) NOT NULL,
  `Playlist_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Owner_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`Playlist_ID`),
  KEY `Owner_ID` (`Owner_ID`),
  CONSTRAINT `playlist_ibfk_1` FOREIGN KEY (`Owner_ID`) REFERENCES `user` (`User_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playlist`
--

LOCK TABLES `playlist` WRITE;
/*!40000 ALTER TABLE `playlist` DISABLE KEYS */;
INSERT INTO `playlist` VALUES ('Playlist_1_A',1,1),('Playlist_1_B',2,1),('Playlist_2_A',3,2),('Playlist_2_B',4,2),('Playlist_3_A',5,3),('Playlist_4_A',6,4);
/*!40000 ALTER TABLE `playlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playlist_music`
--

DROP TABLE IF EXISTS `playlist_music`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `playlist_music` (
  `Playlist_ID` int(11) NOT NULL,
  `Music_ID` int(11) NOT NULL,
  PRIMARY KEY (`Playlist_ID`,`Music_ID`),
  KEY `Music_ID` (`Music_ID`),
  CONSTRAINT `playlist_music_ibfk_1` FOREIGN KEY (`Playlist_ID`) REFERENCES `playlist` (`Playlist_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `playlist_music_ibfk_2` FOREIGN KEY (`Music_ID`) REFERENCES `music` (`Music_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playlist_music`
--

LOCK TABLES `playlist_music` WRITE;
/*!40000 ALTER TABLE `playlist_music` DISABLE KEYS */;
INSERT INTO `playlist_music` VALUES (1,2),(1,5),(2,5),(2,6),(3,6),(4,6),(4,11),(1,14),(1,16),(1,19),(1,20),(1,22),(2,31),(2,32),(3,32),(4,32),(5,32),(6,32);
/*!40000 ALTER TABLE `playlist_music` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `Name` varchar(30) NOT NULL,
  `Sex` char(1) DEFAULT NULL,
  `JoinedDate` date DEFAULT NULL,
  `User_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Login_ID` varchar(30) NOT NULL,
  `Password` varchar(30) NOT NULL,
  PRIMARY KEY (`User_ID`),
  UNIQUE KEY `Login_ID` (`Login_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('User1','M','2010-01-01',1,'user1','1234'),('User2','M','2010-01-01',2,'user2','1234'),('User3','F','2010-01-01',3,'user3','1234'),('User4','F','2010-01-01',4,'user4','1234');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-05 16:44:42
