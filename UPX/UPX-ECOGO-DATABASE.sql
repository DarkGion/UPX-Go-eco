drop database if exists Ecogo;
create database Ecogo;
use Ecogo;

create table usuarios (
	user_id		int 			auto_increment		primary key,
	nome		varchar(50)		not null,
    endereco_ref int REFERENCES endereco(endereco_id),
    tipo_ref int REFERENCES tipo(tipo_id),
    prod_ref int REFERENCES produtos(prod_id),
    contato_ref int REFERENCES contato(contato_id)
);

create table tipo (
tipo_id int 			auto_increment		primary key,
tipo varchar(50)
);

create table produtos (

	prod_id			int 			auto_increment 		primary key,
	nome_prod		varchar(50)		not null,
	qtd_estoque		int				not null 			default 0,
    descricao		varchar(300)		not null,
	valor			decimal(10,2)
);


create table contato(
	contato_id		int 			auto_increment 		primary key,
	nome_contato		varchar(50)		not null,
    email		varchar(50)		not null,
    telefone        VARCHAR(15)     NOT NULL
);

	CREATE TABLE endereco (
    endereco_id     INT             AUTO_INCREMENT PRIMARY KEY,
    logradouro      VARCHAR(100)    NOT NULL,
    numero          VARCHAR(10)     NOT NULL,
    complemento     VARCHAR(50),
    bairro          VARCHAR(50)     NOT NULL,
    cidade          VARCHAR(50)     NOT NULL,
    estado          VARCHAR(2)      NOT NULL,
    cep             VARCHAR(10)     NOT NULL
);




