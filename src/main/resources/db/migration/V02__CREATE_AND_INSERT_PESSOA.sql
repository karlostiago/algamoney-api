CREATE TABLE public.pessoa (
	codigo BIGSERIAL PRIMARY KEY,
	nome VARCHAR(100) NOT NULL,
	ativo BOOL NOT NULL,
	logradouro VARCHAR(150),
	numero VARCHAR(50),
	complemento VARCHAR(150),
	bairro VARCHAR(100),
	cep VARCHAR(20),
	cidade VARCHAR(100),
	estado VARCHAR(50)
);

INSERT INTO public.pessoa (nome, ativo, logradouro, numero, bairro, cep, cidade, estado) VALUES ('Cristiano silva', true, 'Rua das aves', '556', 'Santana', '33.000-231', 'Belo Horizonte', 'MG');
INSERT INTO public.pessoa (nome, ativo) VALUES ('Maria Fernanda', true);
INSERT INTO public.pessoa (nome, ativo) VALUES ('João Pedro', false);