-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 27, 2017 at 02:16 PM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `phplogin`
--
CREATE DATABASE IF NOT EXISTS `phplogin` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `phplogin`;

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

CREATE TABLE `comments` (
  `comment_id` int(11) NOT NULL,
  `topic_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `topic_parent_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `comments`
--

INSERT INTO `comments` (`comment_id`, `topic_id`, `user_id`, `topic_parent_id`) VALUES
(40, 71, 26, 70),
(41, 72, 27, 70);

-- --------------------------------------------------------

--
-- Table structure for table `topic_content`
--

CREATE TABLE `topic_content` (
  `topic_content_id` int(11) NOT NULL,
  `content_text` varchar(5000) DEFAULT NULL,
  `content_photo` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `topic_content`
--

INSERT INTO `topic_content` (`topic_content_id`, `content_text`, `content_photo`) VALUES
(70, 'Sample topic text.', 'marjetica.jpg'),
(71, 'o najs hrana', 'marjetica.jpg'),
(72, 'first comment', 'disturbed.jpg'),
(73, 'Nezanimiv tekst', 'disturbed.jpg'),
(74, 'Še en tekst', 'marjetica.jpg'),
(75, 'Še en tekst', 'disturbed.jpg'),
(76, 'še en tekst', 'jernej.jpg'),
(77, 'Poljuben tekst', 'marjetica.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `topics`
--

CREATE TABLE `topics` (
  `topic_id` int(11) NOT NULL,
  `topic_name` varchar(30) DEFAULT NULL,
  `topic_date` date DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `topic_content_id` int(11) DEFAULT NULL,
  `is_comment` tinyint(1) DEFAULT NULL,
  `topic_votes` int(11) DEFAULT '0',
  `topic_downvotes` int(11) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `topics`
--

INSERT INTO `topics` (`topic_id`, `topic_name`, `topic_date`, `user_id`, `topic_content_id`, `is_comment`, `topic_votes`, `topic_downvotes`) VALUES
(70, 'Tema 1', '2017-04-25', 26, 70, 0, 0, 0),
(71, 'Sick food', '2017-04-25', 26, 71, 1, 172, 172),
(72, 'First', '2017-04-25', 27, 72, 1, 0, 0),
(73, 'Tema2', '2017-04-26', 9, 73, 0, 0, 0),
(74, 'tema 3', '2017-04-26', 9, 74, 0, 0, 0),
(75, 'Tema 4', '2017-04-26', 9, 75, 0, 0, 0),
(76, 'tema 5', '2017-04-26', 9, 76, 0, 0, 0),
(77, 'Tema 6', '2017-04-26', 9, 77, 0, 0, 0);

--
-- Triggers `topics`
--
DELIMITER $$
CREATE TRIGGER `update_topic_count` AFTER INSERT ON `topics` FOR EACH ROW BEGIN

    UPDATE users
    SET num_topics = num_topics + 1
    WHERE users.user_id = NEW.user_id;

  END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `username` varchar(25) CHARACTER SET utf8 COLLATE utf8_slovenian_ci NOT NULL,
  `user_password` varchar(64) NOT NULL,
  `fullname` varchar(100) CHARACTER SET utf8 COLLATE utf8_slovenian_ci NOT NULL,
  `dateRegistered` date NOT NULL,
  `salt` varchar(40) NOT NULL,
  `num_replies` int(11) DEFAULT '0',
  `score` int(11) DEFAULT '0',
  `num_topics` int(11) DEFAULT '0',
  `datumZadnjePrijave` date DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `pic_path` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `username`, `user_password`, `fullname`, `dateRegistered`, `salt`, `num_replies`, `score`, `num_topics`, `datumZadnjePrijave`, `email`, `pic_path`) VALUES
(9, 'darkneess10', '4a7a30660ae7d32ac19c9f8ef010f969567e65922eaffabed483ee6a707f888f', 'Jernej Habjan', '2017-04-22', '209230039559011f04403007.11829208', 0, 0, 25, '0000-00-00', 'jernej.habjan96@gmail.com', '9jernej.jpg'),
(25, 'darkneess11', '912751df8a0f4ef23b60fc72996a668d92aab3863312b55fbedbd0d05f08d112', 'Janez Novak', '2017-04-24', '19272846958fe51b9e287e5.87687279', 0, 0, 3, NULL, 'jernej.habjan96@gmail.com', 'default.png'),
(26, 'darkneess12', '1101c023028288f38aa1f1be004950e8b23c42abb17e0f557811bc6b7105ddc0', 'Jernej Habjan', '2017-04-24', '112140641858fe5b74b20be8.75254016', 0, 0, 3, NULL, 'jernej.habjan96@gmail.com', 'default.png'),
(27, 'darkneess15', '6952f846d3aa61252075cf389ecb8d50bbbad59f00c679e2e47c8cbfd76bdeb9', 'Micka Kovačeva', '2017-04-24', '38995308858fe5c45c5b4f4.51566122', 0, 0, 8, NULL, 'jernej.habjan95@gmail.com', 'default.png'),
(28, 'kumrček', '2f34c278d6fe41cb8739fa33c77d54a0f883540dbbb6bb85d8d6940dd6e51b0c', 'Janez Kumr', '2017-04-26', '2055692926590099f48017b3.68061275', 0, 0, 0, '0000-00-00', 'kumrcek@gmail.com', '28disturbed.jpg'),
(29, 'neki', '6952f846d3aa61252075cf389ecb8d50bbbad59f00c679e2e47c8cbfd76bdeb9', 'Generated', '2017-04-26', '112140641858fe5b74b20be8.75254016', 0, 0, 0, NULL, 'kumrcek@gmail.com', '28disturbed.jpg'),
(30, 'neki', '6952f846d3aa61252075cf389ecb8d50bbbad59f00c679e2e47c8cbfd76bdeb9', 'Generated', '2017-04-26', '112140641858fe5b74b20be8.75254016', NULL, NULL, NULL, NULL, 'kumrcek@gmail.com', '28disturbed.jpg'),
(31, 'neki', '6952f846d3aa61252075cf389ecb8d50bbbad59f00c679e2e47c8cbfd76bdeb9', 'Generated', '2017-04-26', '112140641858fe5b74b20be8.75254016', NULL, NULL, NULL, NULL, 'kumrcek@gmail.com', '28disturbed.jpg'),
(32, 'neki', '6952f846d3aa61252075cf389ecb8d50bbbad59f00c679e2e47c8cbfd76bdeb9', 'Generated', '2017-04-26', '112140641858fe5b74b20be8.75254016', NULL, NULL, NULL, NULL, 'kumrcek@gmail.com', '28disturbed.jpg'),
(33, 'neki', '6952f846d3aa61252075cf389ecb8d50bbbad59f00c679e2e47c8cbfd76bdeb9', 'Generated', '2017-04-26', '112140641858fe5b74b20be8.75254016', NULL, NULL, NULL, NULL, 'kumrcek@gmail.com', '28disturbed.jpg'),
(34, 'neki', '6952f846d3aa61252075cf389ecb8d50bbbad59f00c679e2e47c8cbfd76bdeb9', 'Generated', '2017-04-26', '112140641858fe5b74b20be8.75254016', NULL, NULL, NULL, NULL, 'kumrcek@gmail.com', '28disturbed.jpg');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`comment_id`),
  ADD KEY `comments_users_fk` (`user_id`),
  ADD KEY `comments_topics_fk` (`topic_id`),
  ADD KEY `comments_topic_parent_fk` (`topic_parent_id`);

--
-- Indexes for table `topic_content`
--
ALTER TABLE `topic_content`
  ADD PRIMARY KEY (`topic_content_id`);

--
-- Indexes for table `topics`
--
ALTER TABLE `topics`
  ADD PRIMARY KEY (`topic_id`),
  ADD KEY `topics_users_fk` (`user_id`),
  ADD KEY `topics_topic_content_fk` (`topic_content_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD KEY `date_index` (`score`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `comments`
--
ALTER TABLE `comments`
  MODIFY `comment_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;
--
-- AUTO_INCREMENT for table `topic_content`
--
ALTER TABLE `topic_content`
  MODIFY `topic_content_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=78;
--
-- AUTO_INCREMENT for table `topics`
--
ALTER TABLE `topics`
  MODIFY `topic_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=78;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `comments`
--
ALTER TABLE `comments`
  ADD CONSTRAINT `comments_topic_parent_fk` FOREIGN KEY (`topic_parent_id`) REFERENCES `topics` (`topic_id`),
  ADD CONSTRAINT `comments_topics_fk` FOREIGN KEY (`topic_id`) REFERENCES `topics` (`topic_id`),
  ADD CONSTRAINT `comments_users_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

--
-- Constraints for table `topics`
--
ALTER TABLE `topics`
  ADD CONSTRAINT `topics_topic_content_fk` FOREIGN KEY (`topic_content_id`) REFERENCES `topic_content` (`topic_content_id`),
  ADD CONSTRAINT `topics_users_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
