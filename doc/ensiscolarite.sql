-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  jeu. 14 jan. 2021 à 10:52
-- Version du serveur :  10.4.10-MariaDB
-- Version de PHP :  7.4.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `ensiscolarite`
--

-- --------------------------------------------------------

--
-- Structure de la table `connexion`
--

DROP TABLE IF EXISTS `connexion`;
CREATE TABLE IF NOT EXISTS `connexion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idUser` int(11) NOT NULL,
  `login` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_id` (`idUser`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `connexion`
--

INSERT INTO `connexion` (`id`, `idUser`, `login`, `password`) VALUES
(1, 3, 'pope', 'password'),
(2, 4, 'youssef', 'password');

-- --------------------------------------------------------

--
-- Structure de la table `cours`
--

DROP TABLE IF EXISTS `cours`;
CREATE TABLE IF NOT EXISTS `cours` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `theme` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `nbHeure` int(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `cours`
--

INSERT INTO `cours` (`id`, `theme`, `nbHeure`) VALUES
(1, 'Le Javascript', 1),
(2, 'Le Php', 3);

-- --------------------------------------------------------

--
-- Structure de la table `cours_user`
--

DROP TABLE IF EXISTS `cours_user`;
CREATE TABLE IF NOT EXISTS `cours_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idUser` int(11) NOT NULL,
  `idCours` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `PRIMARY_ID` (`idUser`,`idCours`),
  KEY `fk_idUser` (`idUser`) USING BTREE,
  KEY `fk_idCours` (`idCours`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `cours_user`
--

INSERT INTO `cours_user` (`id`, `idUser`, `idCours`) VALUES
(6, 1, 1),
(7, 8, 2);

-- --------------------------------------------------------

--
-- Structure de la table `type_user`
--

DROP TABLE IF EXISTS `type_user`;
CREATE TABLE IF NOT EXISTS `type_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `type_user`
--

INSERT INTO `type_user` (`id`, `libelle`) VALUES
(1, 'Etudiant'),
(2, 'Enseignant'),
(3, 'Directeur'),
(4, 'ResponsableEtude');

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `prenom` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `telephone` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `rue` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `ville` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `codePostal` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `dateNaissance` date DEFAULT NULL,
  `matiere` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `idTypeUser` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`idTypeUser`),
  KEY `fk_idTypeUser` (`idTypeUser`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`id`, `nom`, `prenom`, `email`, `telephone`, `rue`, `ville`, `codePostal`, `dateNaissance`, `matiere`, `idTypeUser`) VALUES
(1, 'Monkey D', 'Luffy', 'luffy@mugiwara.op', '0166810444', 'la jungle', 'Wano Kuni', '91000', '1996-05-05', NULL, 1),
(3, 'Pope', 'Henry ', 'henry.pope@foxriver.us', '0186961494', '56 rue de la rivier du renard', 'Illinois', '9470535', NULL, NULL, 3),
(4, 'Jebarri', 'Youssef', 'youssef.jebarri@yahoo.com', '0788190947', '5 avenue de LMX', 'Les Mureaux', '78130', NULL, NULL, 4),
(5, 'Mbiandou', 'Douglas', 'mbiandou.douglas@objis.com', '06154861318', '10 rue de Java', 'Lyon', '63000', NULL, 'Informatique', 2),
(7, 'Damotte', 'Alexis', 'alexis@damotte.fr', '090084088', 'Dans le 91', 'dans le 91', '91000', '1998-07-22', NULL, 1),
(8, 'Ceyland', 'Kevin', 'contact@kevinceyland.fr', '0626494959', '10 rue la manche', 'porcheville', '78440', '1998-05-15', NULL, 1);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `connexion`
--
ALTER TABLE `connexion`
  ADD CONSTRAINT `connexion_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `users` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `cours_user`
--
ALTER TABLE `cours_user`
  ADD CONSTRAINT `cours_user_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `cours_user_ibfk_2` FOREIGN KEY (`idCours`) REFERENCES `cours` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`idTypeUser`) REFERENCES `type_user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
