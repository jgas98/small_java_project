-- phpMyAdmin SQL Dump
-- version 4.8.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 23-12-2018 a las 17:20:28
-- Versión del servidor: 10.1.33-MariaDB
-- Versión de PHP: 7.2.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

--
-- Base de datos: `centro_de_estudios`
--
CREATE DATABASE IF NOT EXISTS `centro_de_estudios` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `centro_de_estudios`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `administracion`
--

DROP TABLE IF EXISTS `administracion`;
CREATE TABLE `administracion` (
  `codigo` int(11) NOT NULL,
  `id_persona` int(11) NOT NULL,
  `anyos_trabajados` int(11) NOT NULL,
  `departamento` varchar(25) NOT NULL,
  `horario` char(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `administracion`
--

INSERT INTO `administracion` (`codigo`, `id_persona`, `anyos_trabajados`, `departamento`, `horario`) VALUES
(1, 3, 13, 'Ingles', 'T'),
(2, 4, 6, 'Historia', 'M');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumnos`
--

DROP TABLE IF EXISTS `alumnos`;
CREATE TABLE `alumnos` (
  `codigo` int(11) NOT NULL,
  `id_persona` int(11) NOT NULL,
  `curso` int(11) NOT NULL,
  `carrera` varchar(30) NOT NULL,
  `nota_media` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `alumnos`
--

INSERT INTO `alumnos` (`codigo`, `id_persona`, `curso`, `carrera`, `nota_media`) VALUES
(1, 2, 4, 'Ingenieria Informática', 8.5),
(2, 1, 4, 'Hosteleria', 9.9),
(3, 4, 1, 'Ciencias politicas', 6.8);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `datos_fiscales`
--

DROP TABLE IF EXISTS `datos_fiscales`;
CREATE TABLE `datos_fiscales` (
  `Id` int(11) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `apellidos` varchar(45) NOT NULL,
  `nif` varchar(9) NOT NULL,
  `edad` int(11) NOT NULL,
  `poblacion` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `datos_fiscales`
--

INSERT INTO `datos_fiscales` (`Id`, `nombre`, `apellidos`, `nif`, `edad`, `poblacion`) VALUES
(1, 'Pablo', 'Pastor', '47693576p', 19, 1),
(2, 'Cristian', 'Lázaro', '24863964c', 22, 2),
(3, 'Rosita', 'Alvarez', '12324398r', 41, 2),
(4, 'Paco', 'Gomez', '12345678k', 35, 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `poblaciones`
--

DROP TABLE IF EXISTS `poblaciones`;
CREATE TABLE `poblaciones` (
  `codigo` int(11) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `cod_postal` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `poblaciones`
--

INSERT INTO `poblaciones` (`codigo`, `nombre`, `cod_postal`) VALUES
(1, 'Castellon', 12004),
(2, 'Valencia', 12017),
(3, 'Alicante', 12060),
(4, 'Tarragona', 12009),
(5, 'Castellon', 12005),
(6, 'Alicante', 12012);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `administracion`
--
ALTER TABLE `administracion`
  ADD PRIMARY KEY (`codigo`);

--
-- Indices de la tabla `alumnos`
--
ALTER TABLE `alumnos`
  ADD PRIMARY KEY (`codigo`);

--
-- Indices de la tabla `datos_fiscales`
--
ALTER TABLE `datos_fiscales`
  ADD PRIMARY KEY (`Id`);

--
-- Indices de la tabla `poblaciones`
--
ALTER TABLE `poblaciones`
  ADD PRIMARY KEY (`codigo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `datos_fiscales`
--
ALTER TABLE `datos_fiscales`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;
