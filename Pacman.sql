-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le :  lun. 11 mars 2019 à 15:35
-- Version du serveur :  10.1.35-MariaDB
-- Version de PHP :  7.2.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `Pacman`
--

-- --------------------------------------------------------

--
-- Structure de la table `Partie`
--

CREATE TABLE `Partie` (
  `id` int(11) NOT NULL,
  `idUtilisateur` int(11) NOT NULL,
  `score` int(11) NOT NULL,
  `fantomeManges` int(11) NOT NULL,
  `capsulesManges` int(11) NOT NULL,
  `pacGommesManges` int(11) NOT NULL,
  `mapEffectuees` int(11) NOT NULL,
  `pasEffectues` int(11) NOT NULL,
  `date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_bin;

--
-- Déchargement des données de la table `Partie`
--

INSERT INTO `Partie` (`id`, `idUtilisateur`, `score`, `fantomeManges`, `capsulesManges`, `pacGommesManges`, `mapEffectuees`, `pasEffectues`, `date`) VALUES
(1, 1, 1, 0, 0, 0, 0, 0, '2019-02-26 13:12:30'),
(2, 1, 245, 0, 0, 0, 0, 0, '2019-02-26 13:27:40'),
(3, 1, 198, 0, 0, 0, 0, 0, '2019-02-26 13:29:42'),
(4, 1, 48, 0, 0, 0, 0, 0, '2019-02-26 13:31:51'),
(5, 1, 2, 0, 0, 0, 0, 0, '2019-02-26 13:34:19'),
(6, 1, 86, 0, 0, 0, 0, 0, '2019-02-26 13:41:19'),
(7, 1, 98, 0, 0, 0, 0, 0, '2019-02-26 13:42:57'),
(8, 1, 253, 0, 0, 0, 0, 0, '2019-02-26 13:44:11'),
(9, 1, 25, 0, 0, 0, 0, 0, '2019-02-26 13:47:30'),
(10, 1, 1, 0, 0, 0, 0, 0, '2019-02-26 13:48:47'),
(11, 1, 55, 0, 0, 0, 0, 0, '2019-02-26 13:51:09'),
(12, 1, 59, 0, 0, 0, 0, 0, '2019-02-26 13:53:04'),
(13, 1, 87, 0, 0, 0, 0, 0, '2019-02-26 13:55:43'),
(14, 1, 782, 0, 0, 0, 0, 0, '2019-02-26 14:00:13'),
(15, 1, 41, 0, 0, 0, 0, 0, '2019-02-26 14:04:24'),
(16, 1, 0, 0, 0, 0, 0, 0, '2019-02-26 14:05:05'),
(17, 1, 15, 0, 0, 0, 0, 0, '2019-02-26 14:11:56'),
(18, 1, 1, 0, 0, 0, 0, 0, '2019-02-26 14:12:14'),
(19, 1, 0, 0, 0, 0, 0, 0, '2019-02-26 14:12:27'),
(20, 1, 180, 0, 0, 0, 0, 0, '2019-02-26 14:13:53'),
(21, 1, 0, 0, 0, 0, 0, 0, '2019-02-26 14:51:18'),
(22, 1, 0, 0, 0, 0, 0, 0, '2019-02-26 14:53:46'),
(23, 1, 0, 0, 0, 0, 0, 0, '2019-02-26 14:54:24'),
(24, 1, 0, 0, 0, 0, 0, 0, '2019-02-26 14:54:54'),
(25, 1, 0, 0, 0, 0, 0, 0, '2019-02-26 14:55:06'),
(26, 1, 0, 0, 0, 0, 0, 0, '2019-02-26 14:56:47'),
(27, 1, 0, 0, 0, 0, 0, 0, '2019-02-26 14:56:55'),
(28, 1, 0, 0, 0, 0, 0, 0, '2019-02-26 14:58:34'),
(29, 1, 0, 0, 0, 0, 0, 0, '2019-02-26 15:01:45'),
(30, 1, 0, 0, 0, 0, 0, 0, '2019-02-26 15:02:06'),
(31, 1, 0, 0, 0, 0, 0, 0, '2019-02-26 15:09:43'),
(32, 1, 0, 0, 0, 0, 0, 0, '2019-02-26 15:09:58'),
(33, 1, 0, 0, 0, 0, 0, 0, '2019-03-06 14:50:12'),
(34, 1, 0, 0, 0, 0, 0, 0, '2019-03-06 15:50:27'),
(35, 1, 0, 0, 0, 0, 0, 0, '2019-03-06 15:51:24'),
(36, 1, 0, 0, 0, 0, 0, 0, '2019-03-06 15:51:31'),
(37, 3, 0, 0, 0, 0, 0, 0, '2019-03-06 15:52:53'),
(38, 3, 0, 0, 0, 0, 0, 0, '2019-03-06 15:55:35'),
(39, 3, 0, 0, 0, 0, 0, 0, '2019-03-06 15:55:48'),
(40, 3, 0, 0, 0, 0, 0, 0, '2019-03-06 16:15:51'),
(41, 1, 0, 0, 0, 0, 0, 0, '2019-03-06 16:20:43'),
(42, 4, 1, 0, 0, 0, 0, 0, '2019-03-11 13:11:36');

-- --------------------------------------------------------

--
-- Structure de la table `Utilisateur`
--

CREATE TABLE `Utilisateur` (
  `id` int(11) NOT NULL,
  `pseudo` varchar(30) COLLATE utf32_bin NOT NULL,
  `mot_de_passe` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `date_inscription` datetime NOT NULL,
  `activite` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_bin;

--
-- Déchargement des données de la table `Utilisateur`
--

INSERT INTO `Utilisateur` (`id`, `pseudo`, `mot_de_passe`, `date_inscription`, `activite`) VALUES
(1, 'test', 'PeaNlV2+Pifopdw7IlE5qfJCudpoWu0Ee/US3NVBA50hOoa8k5xtvg==', '2019-03-04 12:49:04', 0),
(2, 'aaa', 'CIHCkXOmMZrfEEc+w6ITokSI53jOLZUiBgzTN7f6J0M0Ek00Q7enLg==', '2019-03-05 16:03:44', 1),
(3, 'abc', 'PNgdZLeMXP8FT0XTxggBh0S0RvqHP5UTBRRn6q+mMtlCZ2Sr0Tklgg==', '2019-03-06 14:56:37', 1),
(4, 'zzz', 'qc12mqsfxf35W6ICrLxMtcQxxhnMS+l5KRf+ohAbLXqlF6f4L5/LrQ==', '2019-03-11 13:09:49', 1);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `Partie`
--
ALTER TABLE `Partie`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_utilisateur` (`idUtilisateur`);

--
-- Index pour la table `Utilisateur`
--
ALTER TABLE `Utilisateur`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `Partie`
--
ALTER TABLE `Partie`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `Partie`
--
ALTER TABLE `Partie`
  ADD CONSTRAINT `Partie_ibfk_1` FOREIGN KEY (`idUtilisateur`) REFERENCES `Utilisateur` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
