-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jun 20, 2025 at 10:48 PM
-- Server version: 9.1.0
-- PHP Version: 8.3.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bank`
--

DELIMITER $$
--
-- Procedures
--
DROP PROCEDURE IF EXISTS `create_employee`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `create_employee` (IN `emp_name` VARCHAR(255), IN `emp_salary` DECIMAL(10,2))   BEGIN
    INSERT INTO employees (name, salary, date_hired)
    VALUES (emp_name, emp_salary, CURDATE());
END$$

DROP PROCEDURE IF EXISTS `delete_employee`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `delete_employee` (IN `emp_id` INT)   BEGIN
    DELETE FROM employees WHERE id = emp_id;
END$$

DROP PROCEDURE IF EXISTS `get_employees`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_employees` ()   BEGIN
    SELECT * FROM employees;
END$$

DROP PROCEDURE IF EXISTS `update_salary`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `update_salary` (IN `emp_id` INT, IN `new_salary` DECIMAL(10,2))   BEGIN
    UPDATE employees
    SET salary = new_salary
    WHERE id = emp_id;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
CREATE TABLE IF NOT EXISTS `employees` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `salary` decimal(10,2) NOT NULL,
  `date_hired` date NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`id`, `name`, `salary`, `date_hired`) VALUES
(1, 'Alyria Flora', 600.00, '2025-06-21'),
(2, 'Gordon Freeman', 1204.20, '2025-06-21'),
(3, 'Coryanna Grish', 420.65, '2025-06-21'),
(4, 'Lilac Bouqe', 625.23, '2025-06-21'),
(5, 'Ghislain Potter', 1210.23, '2025-06-21');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
