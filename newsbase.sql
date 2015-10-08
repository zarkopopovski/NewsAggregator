# ************************************************************
# Sequel Pro SQL dump
# Version 4096
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: 127.0.0.1 (MySQL 5.1.44)
# Database: newsbase
# Generation Time: 2013-12-31 12:07:46 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table companies
# ------------------------------------------------------------

DROP TABLE IF EXISTS `companies`;

CREATE TABLE `companies` (
  `id` varchar(50) NOT NULL DEFAULT '',
  `customer_id` varchar(80) NOT NULL,
  `company_name` varchar(80) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `city` int(11) DEFAULT NULL,
  `zip` varchar(8) DEFAULT NULL,
  `country` varchar(4) DEFAULT NULL,
  `email` varchar(120) DEFAULT NULL,
  `telephone1` varchar(80) DEFAULT NULL,
  `telephone2` varchar(80) DEFAULT NULL,
  `deleted` int(1) NOT NULL DEFAULT '0',
  `date_created` date NOT NULL,
  `date_modified` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;



# Dump of table credentials
# ------------------------------------------------------------

DROP TABLE IF EXISTS `credentials`;

CREATE TABLE `credentials` (
  `id` varchar(50) NOT NULL DEFAULT '',
  `email` varchar(80) NOT NULL DEFAULT '',
  `password` varchar(80) NOT NULL DEFAULT '',
  `type` enum('CUSTOMER','ADMIN') NOT NULL DEFAULT 'CUSTOMER',
  `deleted` int(1) NOT NULL DEFAULT '0',
  `date_created` date NOT NULL,
  `date_modified` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;



# Dump of table keywords
# ------------------------------------------------------------

DROP TABLE IF EXISTS `keywords`;

CREATE TABLE `keywords` (
  `id` varchar(50) NOT NULL DEFAULT '',
  `customer_id` varchar(50) NOT NULL DEFAULT '',
  `words` tinytext NOT NULL,
  `deleted` int(11) NOT NULL DEFAULT '0',
  `date_created` date NOT NULL,
  `date_modified` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;



# Dump of table news_indexes
# ------------------------------------------------------------

DROP TABLE IF EXISTS `news_indexes`;

CREATE TABLE `news_indexes` (
  `news_index` varchar(50) NOT NULL DEFAULT '',
  `date_created` date NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;



# Dump of table news_pool
# ------------------------------------------------------------

DROP TABLE IF EXISTS `news_pool`;

CREATE TABLE `news_pool` (
  `id` varchar(50) NOT NULL DEFAULT '',
  `title` text,
  `body` longtext,
  `medium_name` varchar(40) DEFAULT NULL,
  `category_name` varchar(50) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `date_created` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  FULLTEXT KEY `title` (`title`,`body`),
  FULLTEXT KEY `title_2` (`title`,`body`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;



# Dump of table setings
# ------------------------------------------------------------

DROP TABLE IF EXISTS `setings`;

CREATE TABLE `setings` (
  `id` varchar(50) NOT NULL DEFAULT '',
  `key` varchar(40) NOT NULL DEFAULT '',
  `value` varchar(255) NOT NULL DEFAULT '',
  `deleted` int(1) NOT NULL DEFAULT '0',
  `date_created` date NOT NULL,
  `date_modified` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;



# Dump of table used_words
# ------------------------------------------------------------

DROP TABLE IF EXISTS `used_words`;

CREATE TABLE `used_words` (
  `id` varchar(50) NOT NULL DEFAULT '',
  `customer_id` varchar(50) NOT NULL DEFAULT '',
  `keyword` varchar(40) NOT NULL DEFAULT '',
  `count_no` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
