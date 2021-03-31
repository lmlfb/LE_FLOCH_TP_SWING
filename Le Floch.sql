-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : mer. 31 mars 2021 à 10:55
-- Version du serveur :  5.7.31
-- Version de PHP : 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `xtom_bdd`
--

-- --------------------------------------------------------

--
-- Structure de la table `salarie`
--

DROP TABLE IF EXISTS `salarie`;
CREATE TABLE IF NOT EXISTS `salarie` (
  `idSal` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(30) NOT NULL,
  `prenoms` varchar(30) NOT NULL,
  `age` int(11) NOT NULL,
  `fonction` varchar(30) NOT NULL,
  `service` varchar(30) NOT NULL,
  `grade` varchar(30) NOT NULL,
  PRIMARY KEY (`idSal`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `salarie`
--

INSERT INTO `salarie` (`idSal`, `nom`, `prenoms`, `age`, `fonction`, `service`, `grade`) VALUES
(1, 'Denis', 'Jean', 26, 'ingenieur', 'recherche', 'cadre'),
(2, 'Robert', 'Jean', 48, 'technicien', 'maintenance', 'expert');

-- --------------------------------------------------------

--
-- Structure de la table `session`
--

DROP TABLE IF EXISTS `session`;
CREATE TABLE IF NOT EXISTS `session` (
  `idSess` int(11) NOT NULL AUTO_INCREMENT,
  `date_deb` date NOT NULL,
  `nbre_jours` int(11) NOT NULL,
  `module` varchar(30) NOT NULL,
  `idSal` int(11) NOT NULL,
  `lieuForm` varchar(30) NOT NULL,
  PRIMARY KEY (`idSess`),
  KEY `idSal` (`idSal`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `session`
--

INSERT INTO `session` (`idSess`, `date_deb`, `nbre_jours`, `module`, `idSal`, `lieuForm`) VALUES
(4, '2020-07-06', 20, 'module info', 1, 'Paris');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `login` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`login`, `password`) VALUES
('root', 'lm');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `session`
--
ALTER TABLE `session`
  ADD CONSTRAINT `session_ibfk_1` FOREIGN KEY (`idSal`) REFERENCES `salarie` (`idSal`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
