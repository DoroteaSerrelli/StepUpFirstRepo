DROP SCHEMA IF EXISTS users;
CREATE SCHEMA users;
USE users;

CREATE TABLE user_account (
username varchar(25) NOT NULL,
userpassword varchar(129) NOT NULL,
email varchar(30) NOT NULL,

PRIMARY KEY(username) 
) ENGINE=InnoDB;

CREATE TABLE dati_anagrafici(
username varchar(25) NOT NULL,
nome varchar(30) NOT NULL,
cognome varchar(40) NOT NULL,
telefono char(16),
CHECK(telefono LIKE '+39 ___ ___ ____'),
sesso enum('Uomo', 'Donna'),

PRIMARY KEY(nome, cognome, username),
FOREIGN KEY (username) REFERENCES user_account(username)
						ON DELETE CASCADE ON UPDATE CASCADE

) ENGINE=InnoDB;

CREATE TABLE indirizzo(
idIndirizzo smallint NOT NULL AUTO_INCREMENT, 
username varchar(25) NOT NULL,
via char(50) NOT NULL,
numCivico smallint NOT NULL,
citta varchar(50) NOT NULL,
CAP smallint NOT NULL,
provincia varchar(50) NOT NULL,

PRIMARY KEY(idIndirizzo, username),
FOREIGN KEY(username) REFERENCES user_account(username) 
						ON DELETE CASCADE	ON UPDATE CASCADE
)ENGINE=InnoDB AUTO_INCREMENT = 1;

CREATE TABLE categoria(
nomeCategoria varchar(50) NOT NULL,
Icona blob,
Descrizione text,

PRIMARY KEY(nomeCategoria)
)ENGINE=InnoDB;

CREATE TABLE prodotto(
IDProdotto int NOT NULL,
NomeProdotto varchar(30) NOT NULL,
Descrizione_breve text NOT NULL,
Descrizione_dettagliata text,
Prezzo float NOT NULL,
Categoria varchar(30) NOT NULL,
Brand varchar(50),
TopImage mediumblob ,

PRIMARY KEY(IDProdotto),
FOREIGN KEY (Categoria) REFERENCES Categoria(NomeCategoria)
						ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB;

CREATE TABLE wishlist(
username varchar(25) NOT NULL,
numprodotti smallint NOT NULL,

PRIMARY KEY(username),
FOREIGN KEY(username) REFERENCES user_account(username) 
						ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE = InnoDB;

CREATE TABLE costituita(
username varchar(25) NOT NULL,
IDProdotto int NOT NULL,

PRIMARY KEY(username, IDProdotto),
FOREIGN KEY(username) REFERENCES wishlist(username) 
						ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY(IDProdotto) REFERENCES prodotto(IDProdotto)
						ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE = InnoDB;

CREATE TABLE ordine(
IDOrdine int AUTO_INCREMENT NOT NULL,
username varchar(25) NOT NULL,
MetodoSpedizione enum('Spedizione standard', 'Spedizione assicurata', 'Spedizione premium') NOT NULL,
MetodoConsegna enum('Consegna a domicilio', 'Consegna presso punto di ritiro', 'Consegna priority') NOT NULL,
DataOrdine date NOT NULL,
Ora time NOT NULL,

PRIMARY KEY(IDOrdine),
FOREIGN KEY(username) REFERENCES user_account(username)
						ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB;

CREATE TABLE galleriaImmagini(
IDImmagine int AUTO_INCREMENT NOT NULL,
Immagine blob NOT NULL,

PRIMARY KEY(IDImmagine) 
)ENGINE=InnoDB;

CREATE TABLE vetrina(
IDVetrina int AUTO_INCREMENT NOT NULL,
nomeVetrina varchar(30),

PRIMARY KEY(IDVetrina)
)ENGINE=InnoDB;

CREATE TABLE evidenza(
IDProdotto int NOT NULL,
IDVetrina int NOT NULL,

PRIMARY KEY(IDProdotto, IDVetrina),
FOREIGN KEY(IDProdotto) REFERENCES prodotto(IDProdotto)
						ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY(IDVetrina) REFERENCES vetrina(IDVetrina)
						ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB;

CREATE TABLE composizione(
IDOrdine int NOT NULL, 
IDProdotto int NOT NULL,
Quantità smallint NOT NULL,
PrezzoAcquisto float NOT NULL,

PRIMARY KEY(IDOrdine, IDProdotto),
FOREIGN KEY(IDOrdine) REFERENCES ordine(IDOrdine)
						ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY(IDProdotto) REFERENCES prodotto(IDProdotto)
						ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB;

CREATE TABLE pagamento(
IDPagamento int AUTO_INCREMENT NOT NULL,
IDOrdine int NOT NULL,
DataPag date NOT NULL,
OraPag time NOT NULL,
MetodoPag varchar(50) NOT NULL,
Importo smallint NOT NULL,

PRIMARY KEY(IDPagamento),
FOREIGN KEY (IDOrdine) REFERENCES ordine(IDOrdine)
						ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE = InnoDB;



INSERT INTO categoria(nomeCategoria, descrizione) VALUES ("Sport", "Raccolta di attrezzature sportive");
INSERT INTO categoria(nomeCategoria, descrizione) VALUES ("Moda", "Abbigliamento sportivo");
INSERT INTO categoria(nomeCategoria, descrizione) VALUES ("Integratori", "Raccolta di integratori sportivi");
INSERT INTO categoria(nomeCategoria, descrizione) VALUES ("Scarpe", "Scarpe per attività fisica");
INSERT INTO categoria(nomeCategoria, descrizione) VALUES ("Accessori", "Accessori per il wellness e il fitness");