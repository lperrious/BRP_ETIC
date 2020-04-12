-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Client :  localhost:8889
-- Généré le :  Dim 12 Avril 2020 à 15:03
-- Version du serveur :  5.6.35
-- Version de PHP :  7.0.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `toolBox`
--

-- --------------------------------------------------------

--
-- Structure de la table `avis`
--

CREATE TABLE `avis` (
  `id` int(11) NOT NULL,
  `entityId` int(11) NOT NULL,
  `textAvis` text NOT NULL,
  `status` varchar(10) NOT NULL,
  `type` varchar(10) NOT NULL,
  `dateAvis` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `contact`
--

CREATE TABLE `contact` (
  `id_msg` int(11) NOT NULL,
  `id_topic` int(11) NOT NULL,
  `mail` varchar(80) NOT NULL,
  `sujet` varchar(150) NOT NULL,
  `message` text NOT NULL,
  `status` int(1) NOT NULL,
  `date_msg` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `entity`
--

CREATE TABLE `entity` (
  `id` int(11) NOT NULL,
  `identifiant_national` int(11) DEFAULT NULL,
  `entity` varchar(30) NOT NULL,
  `email` varchar(80) NOT NULL,
  `workType` int(3) NOT NULL,
  `password` varchar(255) NOT NULL,
  `salt` int(11) NOT NULL,
  `token` varchar(255) NOT NULL,
  `pub` tinyint(1) NOT NULL,
  `dateInscription` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `entity`
--

INSERT INTO `entity` (`id`, `identifiant_national`, `entity`, `email`, `workType`, `password`, `salt`, `token`, `pub`, `dateInscription`) VALUES
(1, 0, 'personne', 'herve@gmail.com', 1, 'd0c2f3557aaaca6203add147b78e5ba9b064fcc3b2313bf18febf9d025a50564663455962fa394de0affc6380c703caef5fa88e17a518731455c93ecf165128b', 883132581, '672ea5072a15db7fe1d3598471bf3d2fd1b0a4cc0e3b5ba9dd09f2335642254ea5037f26c96ec4ca33e2cd439a9c1c1fdc00fc761b993913d717da256fd16ddd', 1, '2020-04-05 12:04:10'),
(2, 844570580, 'auto-entrepreneur', 'quentinmarc81@gmail.com', 19, '9ede1ebe857b34ebbd99947e4a67a94e0c90d5e0f3de673f8fb865106475552addba170998c920fbaab273490a4a417d0d2e6feadedac8ebabf25316eabd778a', 589088466, '2778a3b0eb6b17ebdb33cc0db83a90a11be9b318c03b8bc477b68dce77003f01443cc886a36ad0d5461af7c1258771343377484132bc40e0ce6e69fb0eb9335f', 1, '2020-04-05 12:06:54'),
(3, 399315613, 'entreprise', 'metro@gmail.com', 11, 'd5853ac8cfee2da2bab9019cc95110ebd7df63d74b62c9f7fd15b61931619c496679436df351f92bfecf7caf02b670e06fa2681ed79b9001cde4d738f0ae9467', 753233333, '890ee96ab3f8fe59100c0a6cf3cc32964131b48ea8d2807b72f4a1cea28b4874f15681994875ba0676cb6035717598ce5496bfb6e91aafa0cacae4612c6b6966', 1, '2020-04-05 12:09:01'),
(4, 302002017, 'assiociation', 'dorctorant@gmail.com', 11, '00f34f2eeae94e3e61c0a1fc027d7a14534e87460abd9f8c118a3b8c1cf61d7b2f850bd114f48597597677c0654a82e3bca0123fa15eac1412dd02bfb65c3732', 161663297, '6c210b822779ce51fe66987c9b0a64cb0b770c45a1930b1e823d4e0a814e6f4bfeadb50a55aeca774ec6742f8ccb18199a70f3997559ce866c0642b45b695b09', 0, '2020-04-05 12:12:11');

-- --------------------------------------------------------

--
-- Structure de la table `idea`
--

CREATE TABLE `idea` (
  `id` int(11) NOT NULL,
  `productId` int(11) DEFAULT NULL,
  `developper` int(11) DEFAULT NULL,
  `name` varchar(40) DEFAULT NULL,
  `categorie` int(11) NOT NULL,
  `status` varchar(10) NOT NULL,
  `userIdea` int(11) NOT NULL,
  `description` text NOT NULL,
  `fonctionnalites` text NOT NULL,
  `remu_type` varchar(12) NOT NULL,
  `prio_code` varchar(3) NOT NULL,
  `dateIdea` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `timeCreation` int(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `idea`
--

INSERT INTO `idea` (`id`, `productId`, `developper`, `name`, `categorie`, `status`, `userIdea`, `description`, `fonctionnalites`, `remu_type`, `prio_code`, `dateIdea`, `timeCreation`) VALUES
(1, NULL, NULL, NULL, 0, '0', 1, 'r', 'r', 'royalties', 'oui', '2020-03-30 08:14:58', NULL),
(2, NULL, NULL, NULL, 3, '0', 1, 'r', 'r', 'royalties', 'oui', '2020-03-30 08:21:32', NULL),
(3, NULL, NULL, NULL, 3, '0', 1, 's', 's', 'royalties', 'oui', '2020-03-30 08:23:33', NULL),
(4, NULL, NULL, NULL, 3, '0', 1, 'test', 'test', 'royalties', 'oui', '2020-03-30 08:25:28', NULL),
(5, NULL, NULL, NULL, 3, '0', 2, 'd', 'd', 'royalties', 'oui', '2020-03-30 08:26:32', NULL),
(6, NULL, NULL, NULL, 3, '0', 2, 'd', 'd', 'royalties', 'oui', '2020-03-30 10:48:20', NULL),
(7, NULL, NULL, NULL, 3, '0', 2, 'd', 'd', 'royalties', 'oui', '2020-03-30 10:50:23', NULL),
(8, NULL, NULL, NULL, 2, '0', 2, 'd', 'd', 'royalties', 'oui', '2020-03-30 10:53:07', NULL),
(9, NULL, NULL, NULL, 2, '0', 2, 'd', 'd', 'royalties', 'oui', '2020-03-30 10:53:19', NULL),
(10, NULL, NULL, NULL, 29, '0', 2, 's', 's', 'royalties', 'oui', '2020-03-30 10:53:28', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `intentionAchat`
--

CREATE TABLE `intentionAchat` (
  `idProduct` int(11) NOT NULL,
  `idEntity` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `type` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `likes`
--

CREATE TABLE `likes` (
  `idAvis` int(11) NOT NULL,
  `entityId` int(11) NOT NULL,
  `dateLike` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `MAJ`
--

CREATE TABLE `MAJ` (
  `id` int(11) NOT NULL,
  `productId` int(11) NOT NULL,
  `developper` int(11) DEFAULT NULL,
  `addPrice` double(6,2) NOT NULL,
  `status` varchar(10) NOT NULL,
  `dateInsert` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateCreation` datetime DEFAULT NULL,
  `timeCreation` int(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `organisation`
--

CREATE TABLE `organisation` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `organisation`
--

INSERT INTO `organisation` (`id`, `name`) VALUES
(3, 'Metro'),
(4, 'Doctorants de calisson');

-- --------------------------------------------------------

--
-- Structure de la table `postule`
--

CREATE TABLE `postule` (
  `userId` int(11) NOT NULL,
  `taskId` int(11) NOT NULL,
  `typeTask` varchar(4) NOT NULL,
  `status` varchar(10) NOT NULL,
  `dateStart` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `status` varchar(10) NOT NULL,
  `price` double(6,2) DEFAULT NULL,
  `categorie` int(11) NOT NULL,
  `userIdea` int(11) NOT NULL,
  `developper` int(11) NOT NULL,
  `dateCreation` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `product`
--

INSERT INTO `product` (`id`, `status`, `price`, `categorie`, `userIdea`, `developper`, `dateCreation`) VALUES
(1, 'stable', 69.00, 29, 1, 1, '2020-04-12 06:22:26'),
(2, 'stable', 78.00, 19, 3, 2, '2020-04-12 06:22:26'),
(3, 'stable', 657.00, 23, 4, 1, '2020-04-12 06:22:47');

-- --------------------------------------------------------

--
-- Structure de la table `products_transac`
--

CREATE TABLE `products_transac` (
  `idTransac` int(11) NOT NULL,
  `productId` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `typeConso` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `transaction`
--

CREATE TABLE `transaction` (
  `id` int(11) NOT NULL,
  `buyer` int(11) NOT NULL,
  `seller` int(11) NOT NULL,
  `reduction` int(2) NOT NULL,
  `dateTransaction` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `firstname` varchar(30) NOT NULL,
  `lastname` varchar(40) NOT NULL,
  `birthday` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `user`
--

INSERT INTO `user` (`id`, `firstname`, `lastname`, `birthday`) VALUES
(1, 'Hervé', 'Siffert', '1958-03-02'),
(2, 'Quentin', 'Marc', '1999-02-01');

-- --------------------------------------------------------

--
-- Structure de la table `workTypes`
--

CREATE TABLE `workTypes` (
  `id` int(11) NOT NULL,
  `french_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `workTypes`
--

INSERT INTO `workTypes` (`id`, `french_name`) VALUES
(1, 'Agriculture, agroalimentaire'),
(2, 'Administration'),
(3, 'Armée, sécurité'),
(4, 'Art, design'),
(5, 'Artisanat'),
(6, 'Audiovisuel, spectacle, cinéma'),
(7, 'Audit, conseil'),
(8, 'Banque, assurance'),
(9, 'BTP, Architecture'),
(10, 'Chimie'),
(11, 'Commerce, e-commerce, distribution'),
(12, 'Droit, justice'),
(13, 'Edition, journalisme'),
(14, 'Electronique'),
(15, 'Energie, environnement'),
(16, 'Enseignement'),
(17, 'Hotellerie, restauration'),
(18, 'Industrie'),
(19, 'Informatique et télécoms'),
(20, 'Logistique, transport'),
(21, 'Maintenance, entretien'),
(22, 'Marketing, publicité, communication'),
(23, 'Mécanique'),
(24, 'Mode et style'),
(25, 'Santé, médical et paramédical'),
(26, 'Social, service à la personne'),
(27, 'Sport et loisirs'),
(28, 'Tourisme'),
(29, 'universel');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `avis`
--
ALTER TABLE `avis`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `contact`
--
ALTER TABLE `contact`
  ADD PRIMARY KEY (`id_msg`);

--
-- Index pour la table `entity`
--
ALTER TABLE `entity`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `idea`
--
ALTER TABLE `idea`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `MAJ`
--
ALTER TABLE `MAJ`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `organisation`
--
ALTER TABLE `organisation`
  ADD UNIQUE KEY `SECONDARY` (`id`);

--
-- Index pour la table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD UNIQUE KEY `SECONDARY` (`id`);

--
-- Index pour la table `workTypes`
--
ALTER TABLE `workTypes`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `avis`
--
ALTER TABLE `avis`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `contact`
--
ALTER TABLE `contact`
  MODIFY `id_msg` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `entity`
--
ALTER TABLE `entity`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT pour la table `idea`
--
ALTER TABLE `idea`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT pour la table `MAJ`
--
ALTER TABLE `MAJ`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT pour la table `transaction`
--
ALTER TABLE `transaction`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `workTypes`
--
ALTER TABLE `workTypes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
