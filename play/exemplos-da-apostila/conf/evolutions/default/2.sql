# --- !Ups
CREATE TABLE produto (
    id INTEGER NOT NULL,
    nome VARCHAR(255) NOT NULL,
	marca VARCHAR(255) NOT NULL
);

# --- !Downs
DROP TABLE produto;
