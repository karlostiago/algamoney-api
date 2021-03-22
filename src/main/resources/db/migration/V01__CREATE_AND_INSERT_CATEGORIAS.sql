CREATE TABLE public.categoria (
	codigo BIGSERIAL PRIMARY KEY,
	nome VARCHAR(50) NOT NULL
);

INSERT INTO public.categoria (nome) VALUES ('Lazer');
INSERT INTO public.categoria (nome) VALUES ('Alimentação');
INSERT INTO public.categoria (nome) VALUES ('Supermercado');
INSERT INTO public.categoria (nome) VALUES ('Farmácia');
INSERT INTO public.categoria (nome) VALUES ('Outros');