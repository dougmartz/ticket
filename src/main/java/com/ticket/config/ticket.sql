-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 19-08-2019 a las 00:07:02
-- Versión del servidor: 10.1.25-MariaDB
-- Versión de PHP: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `ticket`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tickets`
--

CREATE TABLE `tickets` (
  `id` bigint(20) NOT NULL,
  `type` varchar(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `venue` varchar(20) NOT NULL,
  `date` date NOT NULL,
  `price` double NOT NULL,
  `number` int(11) NOT NULL,
  `remarks` varchar(25) NOT NULL,
  `customer` varchar(20) NOT NULL,
  `phone` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tickets`
--

INSERT INTO `tickets` (`id`, `type`, `name`, `venue`, `date`, `price`, `number`, `remarks`, `customer`, `phone`) VALUES
(1, 'Concert', 'Metalica', 'San Francisco', '2019-08-12', 120, 1, 'S/N', 'Douglas', '+58 0424-1234567'),
(2, 'Movie', 'Halloween', 'Mexico DF', '2019-08-30', 5, 2, 'S/N', 'Mary', '+27 0534589631'),
(5, 'Movie', 'Avengers', 'LA', '2019-02-02', 5, 3, 'S/N', 'Tom', '+255446456'),
(6, 'Concert', 'Alice in Chains', 'Seattle', '2019-12-02', 6, 1, 'S/N', 'Layne', '+5836232564');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `username` varchar(15) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `password` varchar(15) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`id`, `username`, `password`) VALUES
(1, 'Admin', '1234');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `tickets`
--
ALTER TABLE `tickets`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `tickets`
--
ALTER TABLE `tickets`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT de la tabla `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
