CREATE TABLE categoria (
	codigo serial primary key not null,
	nome varchar(255) not null
)WITH (
  OIDS=FALSE
);
ALTER TABLE public.categoria
  OWNER TO postgres;

INSERT INTO categoria (nome) VALUES ('LAZER');
INSERT INTO categoria (nome) VALUES ('ALIMENTAÇÃO');
INSERT INTO categoria (nome) VALUES ('SUPERMERCADO');
INSERT INTO categoria (nome) VALUES ('FARMÁCIA');
INSERT INTO categoria (nome) VALUES ('OUTROS');