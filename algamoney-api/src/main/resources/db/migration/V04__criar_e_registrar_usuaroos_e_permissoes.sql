CREATE TABLE if not exists usuario(
	codigo BIGINT(20) PRIMARY KEY,
	nome VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
	senha VARCHAR(150) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE if not exists permissao(
	codigo BIGINT(20) PRIMARY KEY,
	descricao VARCHAR(50) NOT NULL
) ENGINE= InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE if not exists usuario_permissao(
	codigo_usuario BIGINT(20) NOT NULL,
	codigo_permissao BIGINT(20) NOT NULL,
	PRIMARY KEY (codigo_usuario, codigo_permissao),
	FOREIGN KEY (codigo_usuario) REFERENCES usuario(codigo),
	FOREIGN KEY (codigo_permissao) REFERENCES permissao(codigo)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

INSERT INTO usuario(codigo, nome, email, senha) values (1,'Admin', 'admin@admin.com.br' , '$2a$10$BBcRjaV2wWwXUABWWAibdOKYG2.i2toLmV/x.GIWpCQ9q9wiRaXGi');
INSERT INTO usuario(codigo, nome, email, senha) values (2, 'Mat', 'mgsantos177','$2a$10$kS90nSXzdDbThMOyny0lw.EhufKq14cVVKVJJBnj1eLiXV4JmLcLy');

INSERT INTO permissao(codigo,descricao) values (1,'ROLE_CADASTRAR_CATEGORIA');
INSERT INTO permissao(codigo,descricao) values (2,'ROLE_PESQUISAR_CATEGORIA');
INSERT INTO permissao(codigo,descricao) values (3,'ROLE_CADASTRAR_PESSOA');
INSERT INTO permissao(codigo,descricao) values (4,'ROLE_REMOVER_PESSOA');
INSERT INTO permissao(codigo,descricao) values (5,'ROLE_PESQUISAR_PESSOA');
INSERT INTO permissao(codigo,descricao) values (6,'ROLE_CADASTRAR_LANCAMENTO');
INSERT INTO permissao(codigo,descricao) values (7,'ROLE_REMOVER_LANCAMENTO');
INSERT INTO permissao(codigo,descricao) values (8,'ROLE_PESQUISAR_LANCAMENTO');

--admin
INSERT INTO usuario_permissao(codigo_usuario, codigo_permissao) values (1,1);
INSERT INTO usuario_permissao(codigo_usuario, codigo_permissao) values (1,2);
INSERT INTO usuario_permissao(codigo_usuario, codigo_permissao) values (1,3);
INSERT INTO usuario_permissao(codigo_usuario, codigo_permissao) values (1,4);
INSERT INTO usuario_permissao(codigo_usuario, codigo_permissao) values (1,5);
INSERT INTO usuario_permissao(codigo_usuario, codigo_permissao) values (1,6);
INSERT INTO usuario_permissao(codigo_usuario, codigo_permissao) values (1,7);
INSERT INTO usuario_permissao(codigo_usuario, codigo_permissao) values (1,8);

--mat

INSERT INTO usuario_permissao(codigo_usuario, codigo_permissao) values (2,2);
INSERT INTO usuario_permissao(codigo_usuario, codigo_permissao) values (2,5);
INSERT INTO usuario_permissao(codigo_usuario, codigo_permissao) values (2,8);
