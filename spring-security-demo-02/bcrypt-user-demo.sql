DROP DATABASE  IF EXISTS `spring_security_demo_bcrypt`;

CREATE DATABASE  IF NOT EXISTS `spring_security_demo_bcrypt`;
USE `spring_security_demo_bcrypt`;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` char(68) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--
-- NOTE: The passwords are encrypted using BCrypt
--
-- Passwords here are: burak -> burak1907
-- 						caner -> caner1905
-- 						omer -> omer1903
-- 

INSERT INTO `users` 
VALUES 
('burak','{bcrypt}$2a$10$33DlKbJFOvi4buY6jnK8COu1r/hylf7o79CrZQop6Uu5HTORuLLU.',1),
('caner','{bcrypt}$2a$10$kP5epb0MWSW3Lxw28URhcuDHOyIuuMarPAGaRy/mVjuAkxOwWTURW',1),
('omer','{bcrypt}$2a$10$a43fQSG6eSqC6lZdj1i/L.hP6X77NMDz4HN9U.qYDnW1Q7IHnWbyy',1);


--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `authorities`
--

INSERT INTO `authorities` 
VALUES 
('burak','ROLE_EMPLOYEE'),
('caner','ROLE_EMPLOYEE'),
('caner','ROLE_MANAGER'),
('omer','ROLE_EMPLOYEE'),
('omer','ROLE_ADMIN');


