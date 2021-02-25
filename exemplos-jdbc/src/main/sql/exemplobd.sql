create database exemplobd;

use exemplobd;

create table pessoa (
	id integer not null auto_increment,
	nome varchar(100) not null,
	data_nascimento date,
	data_cadastro datetime,
	constraint pk_pessoa primary key (id)
);

insert into pessoa (nome, data_nascimento, data_cadastro) values
('Fulano da Silva', '2000-05-18', now());
insert into pessoa (nome, data_nascimento, data_cadastro) values
('Ciclano de Souza', '2001-09-28', now());

select * from pessoa;