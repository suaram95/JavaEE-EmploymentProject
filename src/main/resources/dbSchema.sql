/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.18-log : Database - javaee_employment_project
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`javaee_employment_project` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `javaee_employment_project`;

/*Table structure for table `task` */

DROP TABLE IF EXISTS `task`;

CREATE TABLE `task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `created_date` date NOT NULL,
  `deadline` date DEFAULT NULL,
  `comment` text,
  `status` enum('NEW','DOING','FINISHED') NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `task_ibfk_1` (`user_id`),
  CONSTRAINT `task_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `task` */

insert  into `task`(`id`,`name`,`description`,`created_date`,`deadline`,`comment`,`status`,`user_id`) values (6,'dfgdfg','dfgdfgdfg','2021-01-22','2021-01-26','','NEW',14),(8,'dfgdfg','dfgdfgdf','2021-01-22','2021-01-30','','NEW',14),(10,'werwer','werwerw','2021-01-22','2021-01-23','wer','FINISHED',13);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 NOT NULL,
  `surname` varchar(255) CHARACTER SET utf8 NOT NULL,
  `age` int(3) DEFAULT NULL,
  `gender` enum('MALE','FEMALE') CHARACTER SET utf8 NOT NULL,
  `phone_number` varchar(255) NOT NULL,
  `work_experience` int(2) DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 NOT NULL,
  `password` varchar(255) NOT NULL,
  `staff_type` enum('IT','HRM','ACCOUNTING','ADMINISTRATIVE') NOT NULL,
  `user_type` enum('ADMIN','SECTION_MANAGER','EMPLOYEE') NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4;

/*Data for the table `user` */

insert  into `user`(`id`,`name`,`surname`,`age`,`gender`,`phone_number`,`work_experience`,`username`,`password`,`staff_type`,`user_type`) values (1,'admin','admin',0,'MALE','5456',0,'admin','admin','ADMINISTRATIVE','ADMIN'),(4,'Aram','Sukiasyan',26,'MALE','45654',5,'aram','aram','IT','SECTION_MANAGER'),(10,'hrm','hrm',25,'FEMALE','4645',5,'hrm','hrm','HRM','SECTION_MANAGER'),(11,'acc','acc',25,'FEMALE','66546',5,'acc','acc','ACCOUNTING','SECTION_MANAGER'),(12,'adm','adm',26,'MALE','6877878',6,'adm','adm','ADMINISTRATIVE','SECTION_MANAGER'),(13,'itemp','itemp',30,'MALE','45646456',7,'itemp','itemp','IT','EMPLOYEE'),(14,'hrmemp','hrmemp',26,'FEMALE','456',8,'hrmemp','hrmemp','HRM','EMPLOYEE'),(15,'accemp','accemp',28,'MALE','4564645',9,'accemp','accemp','ACCOUNTING','EMPLOYEE'),(16,'admemp','admemp',33,'MALE','4456546',9,'admemp','admemp','ADMINISTRATIVE','EMPLOYEE');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
