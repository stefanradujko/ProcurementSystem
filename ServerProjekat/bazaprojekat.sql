/*
SQLyog Ultimate v10.00 Beta1
MySQL - 5.5.5-10.4.11-MariaDB : Database - bazaprojekat
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`bazaprojekat` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `bazaprojekat`;

/*Table structure for table `komponenta` */

DROP TABLE IF EXISTS `komponenta`;

CREATE TABLE `komponenta` (
  `komponentaID` int(11) NOT NULL AUTO_INCREMENT,
  `nazivKomponente` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cenaKomponente` double DEFAULT NULL,
  `godinaProizvodnje` int(11) DEFAULT NULL,
  `kratakOpis` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `tipID` int(11) NOT NULL,
  `proizvodjacID` int(11) NOT NULL,
  `status` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`komponentaID`),
  KEY `tipID` (`tipID`),
  KEY `proizvodjacID` (`proizvodjacID`),
  CONSTRAINT `komponenta_ibfk_1` FOREIGN KEY (`tipID`) REFERENCES `tipkomponente` (`tipID`),
  CONSTRAINT `komponenta_ibfk_2` FOREIGN KEY (`proizvodjacID`) REFERENCES `proizvodjac` (`proizvodjacID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `komponenta` */

insert  into `komponenta`(`komponentaID`,`nazivKomponente`,`cenaKomponente`,`godinaProizvodnje`,`kratakOpis`,`tipID`,`proizvodjacID`,`status`) values (3,'RX 580',30000,2017,'8 GB GDDR5, 256bit, HDMI',2,1,'Aktivan'),(5,'Ryzen 5 1500',22000,2018,'6 core, 3.2 GHz',1,1,'Aktivan'),(6,'i-9 9900K',42000.98,2018,'8 core, 5.0 GHz',1,3,'Aktivan'),(8,'Hyperx',12000,2018,'16GB DDR4',6,4,'Povucen'),(9,'HyperX',12000,2018,'16 GB DDR4',6,4,'Povucen');

/*Table structure for table `narudzbenica` */

DROP TABLE IF EXISTS `narudzbenica`;

CREATE TABLE `narudzbenica` (
  `brojNarudzbenice` int(11) NOT NULL AUTO_INCREMENT,
  `datumKreiranja` date DEFAULT NULL,
  `ukupnaCena` double DEFAULT NULL,
  `zaposleniID` int(11) NOT NULL,
  `proizvodjacID` int(11) NOT NULL,
  `status` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`brojNarudzbenice`),
  KEY `zaposleniID` (`zaposleniID`),
  KEY `proizvodjacID` (`proizvodjacID`),
  CONSTRAINT `narudzbenica_ibfk_1` FOREIGN KEY (`zaposleniID`) REFERENCES `zaposleni` (`zaposleniID`),
  CONSTRAINT `narudzbenica_ibfk_2` FOREIGN KEY (`proizvodjacID`) REFERENCES `proizvodjac` (`proizvodjacID`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `narudzbenica` */

insert  into `narudzbenica`(`brojNarudzbenice`,`datumKreiranja`,`ukupnaCena`,`zaposleniID`,`proizvodjacID`,`status`) values (16,'2021-03-14',52000,3,1,'Obradjena'),(19,'2021-04-21',82000,1,1,'Obradjena');

/*Table structure for table `proizvodjac` */

DROP TABLE IF EXISTS `proizvodjac`;

CREATE TABLE `proizvodjac` (
  `proizvodjacID` int(11) NOT NULL AUTO_INCREMENT,
  `nazivProizvodjaca` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `emailProizvodjaca` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`proizvodjacID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `proizvodjac` */

insert  into `proizvodjac`(`proizvodjacID`,`nazivProizvodjaca`,`emailProizvodjaca`) values (1,'AMD','amd@yahoo.com'),(3,'Intel','intel@gmail.com'),(4,'Kingston','kingston12@gmail.com'),(6,'MSI','mstar1@yahoo.com');

/*Table structure for table `stavkanarudzbenice` */

DROP TABLE IF EXISTS `stavkanarudzbenice`;

CREATE TABLE `stavkanarudzbenice` (
  `brojNarudzbenice` int(11) NOT NULL,
  `brojStavke` int(11) NOT NULL AUTO_INCREMENT,
  `kolicinaKomponente` int(11) DEFAULT NULL,
  `cenaKomponente` double DEFAULT NULL,
  `komponentaID` int(11) NOT NULL,
  PRIMARY KEY (`brojNarudzbenice`,`brojStavke`),
  KEY `komponentaID` (`komponentaID`),
  KEY `brojStavke` (`brojStavke`),
  CONSTRAINT `stavkanarudzbenice_ibfk_2` FOREIGN KEY (`komponentaID`) REFERENCES `komponenta` (`komponentaID`),
  CONSTRAINT `stavkanarudzbenice_ibfk_3` FOREIGN KEY (`brojNarudzbenice`) REFERENCES `narudzbenica` (`brojNarudzbenice`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `stavkanarudzbenice` */

insert  into `stavkanarudzbenice`(`brojNarudzbenice`,`brojStavke`,`kolicinaKomponente`,`cenaKomponente`,`komponentaID`) values (16,14,1,30000,3),(16,15,1,22000,5),(19,19,2,60000,3),(19,20,1,22000,5);

/*Table structure for table `tipkomponente` */

DROP TABLE IF EXISTS `tipkomponente`;

CREATE TABLE `tipkomponente` (
  `tipID` int(11) NOT NULL,
  `nazivTipa` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`tipID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `tipkomponente` */

insert  into `tipkomponente`(`tipID`,`nazivTipa`) values (1,'Procesor'),(2,'Graficka kartica'),(3,'Maticna ploca'),(4,'HDD'),(5,'SSD'),(6,'RAM');

/*Table structure for table `zaposleni` */

DROP TABLE IF EXISTS `zaposleni`;

CREATE TABLE `zaposleni` (
  `zaposleniID` int(11) NOT NULL,
  `ime` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `prezime` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `korisnickoIme` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `sifra` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`zaposleniID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `zaposleni` */

insert  into `zaposleni`(`zaposleniID`,`ime`,`prezime`,`korisnickoIme`,`sifra`) values (1,'Petar','Njegoš','PetarPetrovic','4c08c42eec7f787003a757f3f64b5962'),(2,'Marko','Mrkalj','MarkoM','bd31fdf86deb9579e1b5f80209b0f353'),(3,'Ivana','Balotić','Iva','efedad5b7988f090c543f266b37c9d9f'),(4,'Petar','Kraljević','Kralj','8d3aaa29627400786d2485d2d1089f1f'),(5,'Teša','Tešanović','TšaTšanovic','7e27a6fe8f2a4967a9987d92918584d6'),(6,'Miloš','Milošević','MMilošević','bd004a83cf2e3f23fe986eda58c754f0'),(7,'Nikola','Simić','NikSimic','e4ecf52ce03b22faa3115fff64e2fae8'),(8,'Marko','Kraljević','MKralj','8d3aaa29627400786d2485d2d1089f1f');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
