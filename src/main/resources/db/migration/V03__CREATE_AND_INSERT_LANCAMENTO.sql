CREATE TABLE public.lancamento (
	codigo BIGSERIAL PRIMARY KEY,
	descricao VARCHAR(100) NOT NULL,
	data_vencimento DATE NOT NULL,
	data_pagamento DATE,
	valor NUMERIC(10, 2) NOT NULL,
	observacao VARCHAR(100),
	tipo VARCHAR(20) NOT NULL,
	codigo_categoria INT8 NOT NULL,
	codigo_pessoa INT8 NOT NULL,
	FOREIGN KEY (codigo_categoria) REFERENCES categoria(codigo),
	FOREIGN KEY (codigo_pessoa) REFERENCES pessoa(codigo)
);

INSERT INTO public.lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa)
VALUES('Salário mensal', '2021-06-10', null, 6500.00, 'Distribuição de lucros', 'RECEITA', 2, 2);

INSERT INTO public.lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa)
VALUES('Bahamas', '2021-02-20', '2021-03-20', 100.32, null, 'DESPESA', 2, 2);

INSERT INTO public.lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa)
VALUES('Top club', '2021-01-05', null, 120.00, null, 'RECEITA', 3, 3);