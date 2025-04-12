-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : sam. 12 avr. 2025 à 01:55
-- Version du serveur : 9.1.0
-- Version de PHP : 8.3.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `java`
--
DROP DATABASE IF EXISTS `java`;
CREATE DATABASE IF NOT EXISTS `java` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `java`;

-- --------------------------------------------------------

--
-- Structure de la table `avis`
--

DROP TABLE IF EXISTS `avis`;
CREATE TABLE IF NOT EXISTS `avis` (
                                      `avis_id` int NOT NULL AUTO_INCREMENT,
                                      `user_id` int NOT NULL,
                                      `hebergement_id` int NOT NULL,
                                      `date_avis` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                      `note` int NOT NULL,
                                      `commentaire` text,
                                      PRIMARY KEY (`avis_id`),
                                      KEY `avis_ibfk_1` (`user_id`),
                                      KEY `avis_ibfk_2` (`hebergement_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `hebergement`
--

DROP TABLE IF EXISTS `hebergement`;
CREATE TABLE IF NOT EXISTS `hebergement` (
                                             `hebergement_id` int NOT NULL AUTO_INCREMENT,
                                             `nom` varchar(255) NOT NULL,
                                             `type` int NOT NULL,
                                             `adresse` varchar(255) NOT NULL,
                                             `description` text,
                                             `prix_base` float UNSIGNED NOT NULL,
                                             `etoile` int NOT NULL,
                                             `photo` varchar(255) DEFAULT NULL,
                                             PRIMARY KEY (`hebergement_id`),
                                             UNIQUE KEY `hebergement_pk` (`nom`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `hebergement_option`
--

DROP TABLE IF EXISTS `hebergement_option`;
CREATE TABLE IF NOT EXISTS `hebergement_option` (
                                                    `option_id` int NOT NULL,
                                                    `hebergement_id` int NOT NULL,
                                                    PRIMARY KEY (`option_id`,`hebergement_id`),
                                                    KEY `hebergement_option_ibfk_2` (`hebergement_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `options`
--

DROP TABLE IF EXISTS `options`;
CREATE TABLE IF NOT EXISTS `options` (
                                         `option_id` int NOT NULL AUTO_INCREMENT,
                                         `nom_option` varchar(255) NOT NULL,
                                         `description` text,
                                         PRIMARY KEY (`option_id`),
                                         UNIQUE KEY `options_pk` (`nom_option`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `reduction`
--

DROP TABLE IF EXISTS `reduction`;
CREATE TABLE IF NOT EXISTS `reduction` (
                                           `reduction_id` int NOT NULL AUTO_INCREMENT,
                                           `code_promo` varchar(8) NOT NULL,
                                           `pourcentage` int NOT NULL,
                                           PRIMARY KEY (`reduction_id`),
                                           UNIQUE KEY `reduction_pk` (`code_promo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
CREATE TABLE IF NOT EXISTS `reservation` (
                                             `reservation_id` int NOT NULL AUTO_INCREMENT,
                                             `user_id` int NOT NULL,
                                             `hebergement_id` int NOT NULL,
                                             `date_debut` date NOT NULL,
                                             `date_fin` date NOT NULL,
                                             `tarif_final` float UNSIGNED NOT NULL,
                                             PRIMARY KEY (`reservation_id`),
                                             KEY `reservation_ibfk_1` (`user_id`),
                                             KEY `reservation_ibfk_2` (`hebergement_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
                                      `user_id` int NOT NULL AUTO_INCREMENT,
                                      `email` varchar(255) NOT NULL,
                                      `mdp` varchar(255) NOT NULL,
                                      `nom` varchar(255) NOT NULL,
                                      `prenom` varchar(255) NOT NULL,
                                      `newU` tinyint(1) NOT NULL DEFAULT '1',
                                      `admin` tinyint(1) NOT NULL DEFAULT '0',
                                      PRIMARY KEY (`user_id`),
                                      UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `avis`
--
ALTER TABLE `avis`
    ADD CONSTRAINT `avis_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
    ADD CONSTRAINT `avis_ibfk_2` FOREIGN KEY (`hebergement_id`) REFERENCES `hebergement` (`hebergement_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `hebergement_option`
--
ALTER TABLE `hebergement_option`
    ADD CONSTRAINT `hebergement_option_ibfk_1` FOREIGN KEY (`option_id`) REFERENCES `options` (`option_id`) ON DELETE CASCADE ON UPDATE CASCADE,
    ADD CONSTRAINT `hebergement_option_ibfk_2` FOREIGN KEY (`hebergement_id`) REFERENCES `hebergement` (`hebergement_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `reservation`
--
ALTER TABLE `reservation`
    ADD CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
    ADD CONSTRAINT `reservation_ibfk_2` FOREIGN KEY (`hebergement_id`) REFERENCES `hebergement` (`hebergement_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
