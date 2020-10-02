/*
* SQL USADO NA CRUD
*/
create table sexo(
	pk_sexo serial primary key,
	descricao varchar(40) not null
);

create table provincia(
	pk_provincia serial primary key,
	descricao varchar(40) not null
);

create table contacto(
	pk_contacto serial primary key,
	nome_contacto varchar(40) not null,
	fk_sexo int,
	telefone varchar(25) not null,
	fk_provincia int,
	constraint contacto_fk_sexo foreign key (fk_sexo) references sexo(pk_sexo) 
	on delete cascade on update cascade,
	constraint contacto_fk_provincia foreign key (fk_provincia) references provincia(pk_provincia)
	on delete cascade on update cascade
);