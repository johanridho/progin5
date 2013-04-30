-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 30, 2013 at 11:58 AM
-- Server version: 5.5.16
-- PHP Version: 5.3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `progin_405_13510001`
--

-- --------------------------------------------------------

--
-- Table structure for table `assign`
--

CREATE TABLE IF NOT EXISTS `assign` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `task_id` int(11) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=44 ;

--
-- Dumping data for table `assign`
--

INSERT INTO `assign` (`id`, `task_id`, `category_id`, `user_id`) VALUES
(2, 1, NULL, 2),
(3, NULL, 1, 2),
(8, 1, NULL, 3),
(19, NULL, 6, 2),
(20, 30, NULL, 3),
(24, 1, NULL, 5),
(36, 62, NULL, 2),
(37, 63, NULL, 4),
(38, 63, NULL, 3),
(39, 64, NULL, 4),
(40, 65, NULL, 4),
(41, 66, NULL, 4),
(42, 67, NULL, 4),
(43, 68, NULL, 4);

-- --------------------------------------------------------

--
-- Table structure for table `attachment`
--

CREATE TABLE IF NOT EXISTS `attachment` (
  `attachment_id` int(11) NOT NULL AUTO_INCREMENT,
  `task_id` int(11) DEFAULT NULL,
  `type` enum('image','video','file') NOT NULL,
  `filename` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`attachment_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=34 ;

--
-- Dumping data for table `attachment`
--

INSERT INTO `attachment` (`attachment_id`, `task_id`, `type`, `filename`) VALUES
(1, 1, 'image', '1.jpg'),
(2, 1, 'video', '2.mp4'),
(3, 1, 'file', '3.pdf'),
(12, 30, 'image', '12.png'),
(13, 30, 'video', '13.avi'),
(14, 30, 'file', '14.xlsx'),
(15, 31, 'image', '15.jpg'),
(26, 62, 'image', '26.jpg'),
(27, 63, 'image', '27.png'),
(28, 63, 'image', '28.jpg'),
(29, 64, 'image', '29.jpg'),
(30, 65, 'image', '30.jpg'),
(31, 66, 'image', '31.jpg'),
(32, 67, 'image', '32.jpg'),
(33, 68, 'image', '33.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE IF NOT EXISTS `category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `name` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`category_id`, `user_id`, `name`) VALUES
(1, 1, 'Cinta'),
(6, 1, 'Le Meridien');

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `task_id` int(11) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `content` text,
  `time` int(11) NOT NULL,
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=69 ;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`comment_id`, `task_id`, `user_id`, `content`, `time`) VALUES
(20, 1, 1, 'wawawa', 1362904292),
(21, 1, 1, 'dor', 1362904615),
(22, 1, 1, 'waha', 1362904677),
(23, 1, 1, 'door', 1362904685),
(24, 1, 1, 'dafuq', 1362904698),
(25, 1, 1, 'banyak banget', 1362904703),
(26, 1, 1, 'si andai', 1362904713),
(27, 1, 1, 'wahaha', 1362904716),
(46, 1, 1, 'hah', 1362906180),
(48, 1, 1, 'wahaw', 1362907113),
(49, 1, 1, 'dani', 1362967051),
(50, 1, 1, 'halo', 1362967058),
(51, 1, 1, 'wawa', 1362967062),
(53, 1, 2, 'gyahaha', 1363068620),
(57, 31, 1, 'wawa', 1364052561),
(58, 31, 1, 'caca', 1364052564),
(59, 1, 1, 'haha', 1365503504),
(64, 1, 1, 'gawad', 1365504449),
(65, 1, 1, 'halo', 1366701428),
(66, 1, 1, 'wada', 1366973298),
(67, 1, 1, 'gahaha', 1366973308),
(68, 1, 16, 'hyhy', 1367302550);

-- --------------------------------------------------------

--
-- Table structure for table `tag`
--

CREATE TABLE IF NOT EXISTS `tag` (
  `tag_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`tag_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `tag`
--

INSERT INTO `tag` (`tag_id`, `name`) VALUES
(1, 'satu'),
(2, 'dua'),
(3, 'tiga'),
(4, 'liar'),
(5, 'makan'),
(6, 'lincah'),
(7, 'hajar'),
(8, ''),
(9, 'lol'),
(10, 'wawa');

-- --------------------------------------------------------

--
-- Table structure for table `tags`
--

CREATE TABLE IF NOT EXISTS `tags` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `task_id` int(11) DEFAULT NULL,
  `tag_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=124 ;

--
-- Dumping data for table `tags`
--

INSERT INTO `tags` (`id`, `task_id`, `tag_id`) VALUES
(1, 1, 1),
(2, 1, 2),
(16, 29, 1),
(17, 29, 2),
(18, 29, 3),
(19, 29, 5),
(21, 30, 6),
(22, 30, 2),
(23, 31, 1),
(24, 31, 2),
(25, 31, 3),
(26, 31, 5),
(49, 0, 8),
(51, 38, 8),
(52, 39, 8),
(53, 40, 8),
(54, 41, 8),
(55, 42, 8),
(56, 43, 8),
(57, 44, 8),
(58, 45, 8),
(60, 1, 3),
(103, 62, 1),
(104, 62, 2),
(105, 62, 3),
(110, 1, 5),
(113, 64, 8),
(114, 65, 8),
(115, 66, 8),
(116, 67, 1),
(117, 67, 2),
(118, 67, 3),
(119, 67, 5),
(120, 68, 1),
(121, 68, 2),
(122, 68, 3),
(123, 68, 5);

-- --------------------------------------------------------

--
-- Table structure for table `task`
--

CREATE TABLE IF NOT EXISTS `task` (
  `task_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `category_id` int(11) DEFAULT NULL,
  `name` varchar(25) DEFAULT NULL,
  `deadline` date DEFAULT NULL,
  `done` tinyint(1) NOT NULL,
  PRIMARY KEY (`task_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=64 ;

--
-- Dumping data for table `task`
--

INSERT INTO `task` (`task_id`, `user_id`, `category_id`, `name`, `deadline`, `done`) VALUES
(1, 1, 1, 'Mencari Cinta', '2013-04-11', 0),
(6, 1, 1, 'Cerita Cinta', '2013-03-01', 1),
(8, 1, 1, 'Cinta is Love', '2013-03-01', 1),
(12, 1, 1, 'Katakan Cinta', '2013-03-01', 0),
(13, 1, 1, 'Cinta Ibu', '2013-03-01', 0),
(14, 1, 1, 'Cinta Ayah', '2013-03-01', 0),
(15, 1, 1, 'Cinta Keluarga', '2013-03-01', 1),
(16, 1, 1, 'Cinta Sahabat', '2013-03-01', 0),
(17, 1, 1, 'Zona Cinta', '2013-03-01', 0),
(18, 1, 1, 'Arjuna Mencari Cinta', '2013-03-01', 0),
(19, 1, 1, 'Cinta Satu Malam', '2013-03-01', 0),
(20, 1, 1, 'Cinta Ilahi', '2013-03-01', 0),
(21, 1, 1, 'Cinta Rasul', '2013-03-01', 0),
(29, 1, 6, 'daca', '2013-03-05', 0),
(30, 1, 6, 'homa', '2013-03-01', 0),
(31, 1, 6, 'Irfan Kamil', '2013-03-03', 0),
(62, 1, 1, 'Makan cinta', '2013-04-30', 0),
(63, 1, 6, 'My love', '2013-04-18', 0);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `name` varchar(25) DEFAULT NULL,
  `birthdate` date DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=17 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `username`, `password`, `name`, `birthdate`, `email`) VALUES
(1, 'admin', 'admin', 'Mr Admin', '2013-04-01', 'admin@admin.com'),
(2, 'kamilersz', 'test', 'Irfan Kamil 2', '2013-04-17', 'irfan@kamil.web.id'),
(3, 'cintalaura', 'cinta', 'Cinta Laura', '2013-03-01', 'cinta@laura.com'),
(4, 'irfankamil2', 'irfankamil', 'irfan kamil', '2013-03-13', 'irfan@kamil.web.id'),
(5, 'irfankamil', 'kamilersz', 'irfankamil ss', '2013-03-05', 'irfan@kamil.web.ids'),
(7, 'kamilersz2', 'irfankamil', 'irfan kamil', '2013-04-02', 'kmz.in.act@gmail.com'),
(12, 'test01', 'irfankamil', 'irfan kamil', '2005-11-29', 'a@a.com'),
(13, 'test02', 'irfankamil', 'irfan kamil', '2013-12-31', 'irfan@kamil.web.id2'),
(14, 'halohalo', 'halohalohalo', 'tara', '2013-04-25', 'halo@halo.com'),
(15, 'haniferida', 'hanifkania', 'Hanif Erida', '2013-04-17', 'hanif@erida.putra'),
(16, 'nyamuk', 'nyamuklaron', 'Nyamuk L', '2013-04-17', 'nyamuk@google.com');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
