-- Création de la base de données java
CREATE DATABASE IF NOT EXISTS java;
USE java;

-- Table: user
-- Note : le nom "user" étant un mot réservé, il est encadré par des backticks.
CREATE TABLE `user` (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    mdp VARCHAR(255) NOT NULL,
    nom VARCHAR(255) NOT NULL,
    prenom VARCHAR(255) NOT NULL,
    newU BOOLEAN NOT NULL DEFAULT TRUE,
    admin BOOLEAN NOT NULL DEFAULT FALSE
) ENGINE=InnoDB;

-- Table: reduction
CREATE TABLE reduction (
    reduction_id INT AUTO_INCREMENT PRIMARY KEY,
    code_promo VARCHAR(8) NOT NULL,
    pourcentage INT NOT NULL
) ENGINE=InnoDB;

-- Table: options
-- "options" est également encadré pour éviter tout conflit potentiel avec des mots réservés.
CREATE TABLE `options` (
    option_id INT AUTO_INCREMENT PRIMARY KEY,
    nom_option VARCHAR(255) NOT NULL,
    description TEXT
) ENGINE=InnoDB;

-- Table: hebergement
CREATE TABLE hebergement (
    hebergement_id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    type INT NOT NULL,
    adresse VARCHAR(255) NOT NULL,
    description TEXT,
    prix_base FLOAT UNSIGNED NOT NULL,
    etoile INT NOT NULL,
    proprietaire VARCHAR(255) NOT NULL,
    statut INT NOT NULL,
    photo VARCHAR(255)
) ENGINE=InnoDB;

-- Table: reservation
CREATE TABLE reservation (
    reservation_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    hebergement_id INT NOT NULL,
    date_debut DATE NOT NULL,
    date_fin DATE NOT NULL,
    tarif_final FLOAT UNSIGNED NOT NULL,
    statut BOOLEAN NOT NULL DEFAULT FALSE,
    FOREIGN KEY (user_id) REFERENCES `user`(user_id),
    FOREIGN KEY (hebergement_id) REFERENCES hebergement(hebergement_id)
) ENGINE=InnoDB;

-- Table: avis
CREATE TABLE avis (
    avis_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    hebergement_id INT NOT NULL,
    date_avis TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    note INT NOT NULL,
    commentaire TEXT,
    FOREIGN KEY (user_id) REFERENCES `user`(user_id),
    FOREIGN KEY (hebergement_id) REFERENCES hebergement(hebergement_id)
) ENGINE=InnoDB;

-- Table: hebergement_option
-- Table de liaison pour gérer la relation plusieurs-à-plusieurs entre hébergements et options.
CREATE TABLE hebergement_option (
    option_id INT NOT NULL,
    hebergement_id INT NOT NULL,
    PRIMARY KEY (option_id, hebergement_id),
    FOREIGN KEY (option_id) REFERENCES `options`(option_id),
    FOREIGN KEY (hebergement_id) REFERENCES hebergement(hebergement_id)
) ENGINE=InnoDB;
