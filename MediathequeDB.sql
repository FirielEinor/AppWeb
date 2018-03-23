SET FEEDBACK OFF
SET LINESIZE 150
SET PAGESIZE 40

PROMPT --> DEBUT DU SCRIPT

REM ** Requete SQL de creation des tables **

DROP TABLE UTILISATEUR CASCADE CONSTRAINTS PURGE
/
DROP TABLE DOCUMENT CASCADE CONSTRAINTS PURGE
/

PROMPT CREATION DES TABLES

CREATE TABLE UTILISATEUR
(
	idUser INTEGER CONSTRAINT pk_idUser PRIMARY KEY,
	loginUser VARCHAR2(20),
	passwordUser VARCHAR2(10),
	typeUser VARCHAR2(10)
)
/

CREATE TABLE DOCUMENT
(
	idDoc INTEGER CONSTRAINT pk_idDoc PRIMARY KEY,
	titreDoc VARCHAR2(50),
	auteurDoc VARCHAR2(20),
	typeDoc INTEGER,
	numEmprunteur INTEGER
)
/

ALTER TABLE DOCUMENT
ADD CONSTRAINT fk_NumEmprunteur
	FOREIGN KEY(NumEmprunteur)
	REFERENCES UTILISATEUR(idUser)
	ON DELETE CASCADE

REM ** Ordres SQL de creation de Sequences  **

PROMPT CREATION DES SEQUENCES

DROP SEQUENCE seq_utilisateur
/
DROP SEQUENCE seq_doc
/
CREATE SEQUENCE seq_utilisateur start with 0 Minvalue 0
/
CREATE SEQUENCE seq_doc start with 0 Minvalue 0
/

INSERT INTO UTILISATEUR (idUser, loginUser, passwordUser, typeUser) VALUES (seq_utilisateur.nextval, 'abonne', 'passabonne', 'abonne');
INSERT INTO UTILISATEUR (idUser, loginUser, passwordUser, typeUser) VALUES (seq_utilisateur.nextval, 'biblio', 'passbiblio', 'biblio');

PROMPT --> SCRIPT COMPLETEMENT TERMINE



SET FEEDBACK ON
