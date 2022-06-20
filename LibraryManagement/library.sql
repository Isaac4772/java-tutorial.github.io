-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 20, 2022 at 03:19 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `library`
--

-- --------------------------------------------------------

--
-- Table structure for table `authors`
--

CREATE TABLE `authors` (
  `author_id` int(11) NOT NULL,
  `name` varchar(225) NOT NULL,
  `country` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `authors`
--

INSERT INTO `authors` (`author_id`, `name`, `country`) VALUES
(1, 'Isaac', 'Netherlands'),
(2, 'Maria', 'France');

-- --------------------------------------------------------

--
-- Table structure for table `books`
--

CREATE TABLE `books` (
  `code` int(11) NOT NULL,
  `title` varchar(225) NOT NULL,
  `author_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  `published_date` date NOT NULL,
  `is_available` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `books`
--

INSERT INTO `books` (`code`, `title`, `author_id`, `category_id`, `published_date`, `is_available`) VALUES
(1, 'Testin1', 1, 2, '2022-06-02', 1),
(2, 'Testing2', 1, 3, '2022-06-01', 1);

-- --------------------------------------------------------

--
-- Table structure for table `borrow_books`
--

CREATE TABLE `borrow_books` (
  `id` int(11) NOT NULL,
  `card_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  `borrow_date` date NOT NULL,
  `due_date` date NOT NULL,
  `return_date` date DEFAULT NULL,
  `fine` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `borrow_books`
--

INSERT INTO `borrow_books` (`id`, `card_id`, `book_id`, `borrow_date`, `due_date`, `return_date`, `fine`) VALUES
(2, 3, 1, '2022-06-20', '2022-06-27', '2022-06-20', 0);

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `category_id` int(11) NOT NULL,
  `name` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`category_id`, `name`) VALUES
(1, 'Horror'),
(2, 'Drama'),
(3, 'Adventure');

-- --------------------------------------------------------

--
-- Table structure for table `librarians`
--

CREATE TABLE `librarians` (
  `id` int(11) NOT NULL,
  `username` varchar(225) NOT NULL,
  `password` varchar(225) NOT NULL,
  `nrcno` varchar(225) NOT NULL,
  `phone` varchar(225) NOT NULL,
  `created_at` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `librarians`
--

INSERT INTO `librarians` (`id`, `username`, `password`, `nrcno`, `phone`, `created_at`) VALUES
(1, 'HarryStyles', 'harrystyles123', '12/BaTaHta(N)012345', '09-1234678', '2020-06-05'),
(2, 'Sadie Sink', 'sadiesink123', '12/BaTaHta(N)012234', '09-123456788', '2022-06-19'),
(3, '', '', '', '', '2022-06-20');

-- --------------------------------------------------------

--
-- Table structure for table `members`
--

CREATE TABLE `members` (
  `card_id` int(11) NOT NULL,
  `name` varchar(225) NOT NULL,
  `roll_no` varchar(225) NOT NULL,
  `academic_year` year(4) NOT NULL,
  `class_year` varchar(225) NOT NULL,
  `register_date` date NOT NULL,
  `expire_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `members`
--

INSERT INTO `members` (`card_id`, `name`, `roll_no`, `academic_year`, `class_year`, `register_date`, `expire_date`) VALUES
(2, 'Aung Aung', 'Ab-334', 2021, 'Second Year', '2022-06-20', '2023-06-20'),
(3, 'Hla Hla', 'BC_446', 2018, 'Fourth Year', '2022-06-20', '2023-06-20');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `authors`
--
ALTER TABLE `authors`
  ADD PRIMARY KEY (`author_id`);

--
-- Indexes for table `books`
--
ALTER TABLE `books`
  ADD PRIMARY KEY (`code`),
  ADD KEY `books_authors` (`author_id`),
  ADD KEY `books_categories` (`category_id`);

--
-- Indexes for table `borrow_books`
--
ALTER TABLE `borrow_books`
  ADD PRIMARY KEY (`id`),
  ADD KEY `borrow_books_books` (`book_id`),
  ADD KEY `borrow_books_members` (`card_id`);

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`category_id`);

--
-- Indexes for table `librarians`
--
ALTER TABLE `librarians`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `members`
--
ALTER TABLE `members`
  ADD PRIMARY KEY (`card_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `authors`
--
ALTER TABLE `authors`
  MODIFY `author_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `books`
--
ALTER TABLE `books`
  MODIFY `code` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `borrow_books`
--
ALTER TABLE `borrow_books`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `category_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `librarians`
--
ALTER TABLE `librarians`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `members`
--
ALTER TABLE `members`
  MODIFY `card_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `books`
--
ALTER TABLE `books`
  ADD CONSTRAINT `books_authors` FOREIGN KEY (`author_id`) REFERENCES `authors` (`author_id`),
  ADD CONSTRAINT `books_categories` FOREIGN KEY (`category_id`) REFERENCES `categories` (`category_id`);

--
-- Constraints for table `borrow_books`
--
ALTER TABLE `borrow_books`
  ADD CONSTRAINT `borrow_books_books` FOREIGN KEY (`book_id`) REFERENCES `books` (`code`),
  ADD CONSTRAINT `borrow_books_members` FOREIGN KEY (`card_id`) REFERENCES `members` (`card_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
