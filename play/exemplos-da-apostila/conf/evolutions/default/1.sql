# --- !Ups

CREATE SEQUENCE item_id_seq;
CREATE TABLE item (
    id INTEGER NOT NULL DEFAULT nextval('item_id_seq'),
    nome VARCHAR(255) NOT NULL,
);

# --- !Downs

DROP TABLE item;
DROP SEQUENCE item_id_seq;
