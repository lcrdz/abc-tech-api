create table assistances
(
    id          bigint auto_increment
        primary key,
    description varchar(300) not null,
    name        varchar(100) not null
);

INSERT INTO assistances (name, description) VALUES ('Troca de aparelho', 'Troca de aparelho danificado');
INSERT INTO assistances (name, description) VALUES ('Troca de cabo interno', 'Troca de cabo interno danificado');