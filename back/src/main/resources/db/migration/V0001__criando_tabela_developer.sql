create table developer(
	id bigint not null auto_increment,
	nome varchar(50) not null,
    sexo char(20) not null,
    idade int(3) not null,
    hobby varchar(50) not null,
    data_nascimento date not null,
    
    primary key(id)
);