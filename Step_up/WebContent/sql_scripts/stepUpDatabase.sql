DROP SCHEMA IF EXISTS stepup;
CREATE SCHEMA stepup;
USE stepup;

CREATE TABLE user_account (
username varchar(25) NOT NULL,
userpassword varchar(129) NOT NULL,

PRIMARY KEY(username) 
) ENGINE=InnoDB;

CREATE TABLE dati_personali(
username varchar(25) NOT NULL,
nome varchar(30) NOT NULL,
cognome varchar(40) NOT NULL,
email varchar(40) NOT NULL,
telefono char(16),
CHECK(telefono LIKE '___-___-____'),
sesso enum('Uomo', 'Donna'),

PRIMARY KEY(email),
FOREIGN KEY (username) REFERENCES user_account(username)
						ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE indirizzo(
idIndirizzo int NOT NULL AUTO_INCREMENT, 
via char(50) NOT NULL,
numCivico varchar(4) NOT NULL,
citta varchar(50) NOT NULL,
CAP char(5) NOT NULL,
provincia varchar(50) NOT NULL,

PRIMARY KEY(idIndirizzo)
)ENGINE=InnoDB AUTO_INCREMENT = 1;

CREATE TABLE risiede(
username varchar(25) NOT NULL,
idIndirizzo int NOT NULL,

PRIMARY KEY(username, idIndirizzo),
FOREIGN KEY (idIndirizzo) REFERENCES indirizzo(idIndirizzo)
			ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (username) REFERENCES user_account(username) 
			ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE categoria(
nomeCategoria varchar(50) NOT NULL,
Descrizione text,

PRIMARY KEY(nomeCategoria)
)ENGINE=InnoDB;

CREATE TABLE prodotto(
IDProdotto int NOT NULL,
NomeProdotto varchar(80) NOT NULL,
Descrizione_breve text NOT NULL,
Descrizione_dettagliata text,
Prezzo float NOT NULL,
Categoria varchar(30) NOT NULL,
Brand varchar(50),
flag_disponibile boolean NOT NULL,
TopImage mediumblob ,

PRIMARY KEY(IDProdotto),
FOREIGN KEY (Categoria) REFERENCES Categoria(NomeCategoria)
						ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB;

CREATE TABLE wishlist(
username varchar(25) NOT NULL,
numProdotti smallint NOT NULL,

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
Prodotto int NOT NULL,
Immagine mediumblob NOT NULL,

PRIMARY KEY(IDImmagine, Prodotto),
FOREIGN KEY(Prodotto) REFERENCES Prodotto(IDProdotto) ON DELETE CASCADE ON UPDATE CASCADE
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
Importo float NOT NULL,

PRIMARY KEY(IDPagamento),
FOREIGN KEY (IDOrdine) REFERENCES ordine(IDOrdine)
						ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE = InnoDB;