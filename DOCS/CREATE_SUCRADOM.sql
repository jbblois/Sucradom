
DROP DATABASE IF EXISTS Sucradom;
CREATE DATABASE IF NOT EXISTS Sucradom;

USE sucradom;

CREATE TABLE adresse
(
	ID INT NOT NULL AUTO_INCREMENT,
	Numero VARCHAR (50),
	Rue VARCHAR (255) NOT NULL,
	Cp VARCHAR (10) NOT NULL,
	Ville VARCHAR (255) NOT NULL,
	Pays VARCHAR (50) NOT NULL,
	FID_Client INT NOT NULL,
	PRIMARY KEY (ID)
)DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS categorie
(
	ID INT NOT NULL AUTO_INCREMENT,
	Libelle VARCHAR (255) NOT NULL,
	FID_Image INT NOT NULL,
	PRIMARY KEY (ID)
)DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS client
(
	ID INT NOT NULL AUTO_INCREMENT,
	Nom VARCHAR (255) NOT NULL,
	Prenom VARCHAR (255) NOT NULL,
	Telephone VARCHAR (255) NOT NULL,
	Email VARCHAR (255) NOT NULL,
	MotDePasse VARCHAR (255) NOT NULL,
	IsActif VARCHAR(3) NOT NULL,
	
	PRIMARY KEY (ID)
)DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS etatcommande
(
	ID INT NOT NULL AUTO_INCREMENT,
	Nom VARCHAR (50) NOT NULL,
	PRIMARY KEY (ID)
)DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS image
(
	ID INT NOT NULL AUTO_INCREMENT,
	Path VARCHAR (255) NOT NULL,
	Alt VARCHAR (255) NOT NULL,
	PRIMARY KEY (ID)
)DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS lignecommande
(
	FID_Commande INT NOT NULL,
	FID_Produit INT NOT NULL,
	Quantite INT NOT NULL,
	PrixUnitaire FLOAT NOT NULL,
	ValeurTaxe FLOAT NOT NULL,
	PRIMARY KEY (FID_Commande,FID_Produit)
)DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS produit
(
	ID INT NOT NULL AUTO_INCREMENT,
	Libelle VARCHAR (255) NOT NULL,
	Description VARCHAR (255) NOT NULL,
	Prix FLOAT (4) NOT NULL,
	IsDisponible VARCHAR (3),
	FID_Categorie INT NOT NULL,
	FID_Image INT NOT NULL,
	FID_Taxe INT NOT NULL,
	PRIMARY KEY (ID)
)DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS provision
(
	ID INT NOT NULL AUTO_INCREMENT,
	Date DATE NOT NULL,
	Quantite INT NOT NULL,
	FID_Produit INT NOT NULL,
	PRIMARY KEY (ID)
)DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS taxe
(
	ID INT NOT NULL AUTO_INCREMENT,
	Nom VARCHAR (255) NOT NULL,
	Valeur FLOAT (4) NOT NULL,
	PRIMARY KEY (ID)
)DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS tetecommande
(
	ID INT NOT NULL AUTO_INCREMENT,
	Date DATE NOT NULL,
	FID_Client INT NOT NULL,
	FID_Etat INT NOT NULL,
	FID_Adresse INT NOT NULL,
	
	PRIMARY KEY (ID)
)DEFAULT CHARSET=utf8;




ALTER TABLE adresse
	ADD FOREIGN KEY (FID_Client) REFERENCES client (ID);

ALTER TABLE categorie
	ADD FOREIGN KEY (FID_Image) REFERENCES image (ID);

ALTER TABLE lignecommande
	ADD FOREIGN KEY (FID_Commande) REFERENCES tetecommande (ID),
	ADD FOREIGN KEY (FID_Produit) REFERENCES produit (ID);

ALTER TABLE produit
	ADD FOREIGN KEY (FID_Categorie) REFERENCES categorie (ID),
	ADD FOREIGN KEY (FID_Image) REFERENCES image (ID),
	ADD FOREIGN KEY (FID_Taxe) REFERENCES taxe (ID);

ALTER TABLE provision
	ADD FOREIGN KEY (FID_Produit) REFERENCES produit (ID);
	
ALTER TABLE tetecommande
	ADD FOREIGN KEY (FID_Client) REFERENCES client (ID),
	ADD FOREIGN KEY (FID_Etat) REFERENCES etatcommande (ID),
	ADD FOREIGN KEY (FID_Adresse) REFERENCES adresse (ID);
	
	
	