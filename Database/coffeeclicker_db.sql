-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 09-05-2024 a las 19:01:00
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `coffeeclicker_db`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `game`
--

CREATE TABLE `game` (
  `nameGame` varchar(255) NOT NULL,
  `gameId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `coffeesAvailable` int(11) NOT NULL,
  `elapsedTime` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `game`
--

INSERT INTO `game` (`nameGame`, `gameId`, `userId`, `coffeesAvailable`, `elapsedTime`) VALUES
('Game_3', 3, 2, 42, 14);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gamegenerators`
--

CREATE TABLE `gamegenerators` (
  `gameId` int(11) NOT NULL,
  `generatorName` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gameupgrades`
--

CREATE TABLE `gameupgrades` (
  `gameId` int(11) NOT NULL,
  `upgradeName` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `generator`
--

CREATE TABLE `generator` (
  `name` varchar(255) NOT NULL,
  `baseCost` float NOT NULL,
  `baseProduction` float NOT NULL,
  `incrementCost` float NOT NULL,
  `image` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `generator`
--

INSERT INTO `generator` (`name`, `baseCost`, `baseProduction`, `incrementCost`, `image`) VALUES
('Alpha', 10, 1, 1.2, 'Images/CoffeeClicker_Cave.png'),
('Beta', 300, 5, 1.4, 'Images/CoffeeClicker_Farm.png'),
('Gamma', 3000, 15, 1.8, 'Images/CoffeeClicker_Factory.png');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `statistics`
--

CREATE TABLE `statistics` (
  `userId` int(11) NOT NULL,
  `gameId` int(11) NOT NULL,
  `time` int(11) NOT NULL,
  `nameGame` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `coffeesAvailable` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `statistics`
--

INSERT INTO `statistics` (`userId`, `gameId`, `time`, `nameGame`, `username`, `coffeesAvailable`) VALUES
(2, 1, 1, 'Game_1', 'user123', 75),
(2, 1, 2, 'Game_1', 'user123', 645),
(2, 1, 3, 'Game_1', 'user123', 1235),
(2, 1, 4, 'Game_1', 'user123', 1835),
(2, 1, 5, 'Game_1', 'user123', 40),
(2, 1, 6, 'Game_1', 'user123', 500),
(2, 2, 1, 'Game_2', 'user123', 31),
(2, 2, 2, 'Game_2', 'user123', 450),
(2, 2, 3, 'Game_2', 'user123', 1353),
(2, 2, 4, 'Game_2', 'user123', 2239),
(2, 2, 5, 'Game_2', 'user123', 225),
(2, 2, 6, 'Game_2', 'user123', 2375),
(2, 2, 7, 'Game_2', 'user123', 1850),
(2, 2, 8, 'Game_2', 'user123', 6050),
(2, 2, 9, 'Game_2', 'user123', 2310),
(2, 2, 10, 'Game_2', 'user123', 7345),
(2, 3, 1, 'Game_3', 'user123', 42),
(8, 4, 1, 'Juego_A', 'JuanPerez', 175),
(8, 4, 2, 'Juego_A', 'JuanPerez', 130),
(8, 5, 1, 'Juego_B', 'JuanPerez', 69),
(8, 5, 2, 'Juego_B', 'JuanPerez', 482),
(8, 5, 3, 'Juego_B', 'JuanPerez', 1115),
(8, 5, 4, 'Juego_B', 'JuanPerez', 2697),
(8, 5, 5, 'Juego_B', 'JuanPerez', 446),
(8, 5, 6, 'Juego_B', 'JuanPerez', 557),
(8, 5, 7, 'Juego_B', 'JuanPerez', 4208),
(8, 5, 8, 'Juego_B', 'JuanPerez', 8755),
(8, 5, 9, 'Juego_B', 'JuanPerez', 13298),
(8, 5, 10, 'Juego_B', 'JuanPerez', 17918),
(8, 5, 11, 'Juego_B', 'JuanPerez', 22461),
(8, 5, 12, 'Juego_B', 'JuanPerez', 27004),
(8, 5, 13, 'Juego_B', 'JuanPerez', 31624),
(8, 5, 14, 'Juego_B', 'JuanPerez', 36167),
(8, 5, 15, 'Juego_B', 'JuanPerez', 25078),
(8, 5, 16, 'Juego_B', 'JuanPerez', 739);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `upgrade`
--

CREATE TABLE `upgrade` (
  `upgradeName` varchar(255) NOT NULL,
  `generatorName` varchar(255) NOT NULL,
  `cost` float NOT NULL,
  `productionMultiplier` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `upgrade`
--

INSERT INTO `upgrade` (`upgradeName`, `generatorName`, `cost`, `productionMultiplier`) VALUES
('Alpha', 'Alpha', 2500, 2),
('Beta', 'Beta', 5000, 2),
('Gamma', 'Gamma', 10000, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user`
--

CREATE TABLE `user` (
  `userId` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `user`
--

INSERT INTO `user` (`userId`, `username`, `email`, `password`) VALUES
(2, 'user123', 'newuser@example.com', 'Password1234'),
(3, 'newuser', 'user123@example.com', 'Password1235'),
(4, 'JohDoe', 'john.doe@example.com', 'SecureP@ssw0rd'),
(6, 'SamSmith', 'sam.smith@example.com', 'Sammy789'),
(7, 'lucia_rodriguez', 'lucia.rodriguez@example.com', 'LuciaPass123'),
(8, 'JuanPerez', 'juan.perez@example.com', 'Abcdefg1'),
(9, 'MariaGarcia', 'maria.garcia@example.com', 'Passw0rd'),
(10, 'Carlos123', 'carlos123@example.com', 'StrongP@ssw0rd'),
(11, 'Laura_89', 'laura_89@example.com', 'SecureP@ss123'),
(12, 'AlexSmith', 'alex.smith@example.com', 'MyP@55w0rd');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `statistics`
--
ALTER TABLE `statistics`
  ADD UNIQUE KEY `userId` (`userId`,`gameId`,`time`);

--
-- Indices de la tabla `upgrade`
--
ALTER TABLE `upgrade`
  ADD PRIMARY KEY (`upgradeName`);

--
-- Indices de la tabla `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userId`) USING BTREE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
