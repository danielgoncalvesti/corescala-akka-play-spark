# --- !Ups

CREATE SEQUENCE participantes_id_seq;
CREATE TABLE participantes (
	id INTEGER NOT NULL DEFAULT nextval('participantes_id_seq'),
	nome VARCHAR(255) NOT NULL,
	email VARCHAR(255)
);

# --- !Downs

DROP TABLE participantes;
DROP SEQUENCE participante_id_seq;
