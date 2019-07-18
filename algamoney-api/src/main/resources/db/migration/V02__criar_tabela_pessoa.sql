CREATE TABLE if not exists pessoa (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	logradouro VARCHAR(50),
	numero VARCHAR(50),
	complemento VARCHAR(50),
	bairro VARCHAR(50),
	cep VARCHAR(50),
	cidade VARCHAR(50),
	estado VARCHAR(50),
	ativo BOOLEAN NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pessoa(nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) 
values ('Matheus Gonçalves Santos', 'Rua Padre Francisco Veloso', '340', 'a', 'Itaim Paulista',
'08131-180', 'São Paulo', 'SP', true);

INSERT INTO pessoa(nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) 
values ('Pedro Henrique Neves', 'Rua Figuração Musical', '27', null, 'Itaim Paulista',
'08141-180', 'São Paulo', 'SP', true);

INSERT INTO pessoa(nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) 
values ('Beatriz Candido da Silva', 'Rua Ivampa Duarte', '230', 'a', 'Itaim Paulista',
'08171-180', 'São Paulo', 'SP', true);

INSERT INTO pessoa(nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) 
values ('Beatriz Candido da Silva', 'Rua Ivampa Duarte', '230', 'a', 'Itaim Paulista',
'08171-180', 'São Paulo', 'SP', true);

INSERT INTO pessoa(nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) 
values ('Beatriz Candido da Silva', 'Rua Ivampa Duarte', '230', 'a', 'Itaim Paulista',
'08171-180', 'São Paulo', 'SP', true);
 
