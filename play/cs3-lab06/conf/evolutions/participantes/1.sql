# --- !Ups

CREATE SEQUENCE participante_id_seq;
CREATE TABLE participante (
	id INTEGER NOT NULL DEFAULT nextval('participante_id_seq'),
	nome VARCHAR(255) NOT NULL,
	email VARCHAR(255)
);

# --- !Downs

DROP TABLE participante;
DROP SEQUENCE participante_id_seq;
