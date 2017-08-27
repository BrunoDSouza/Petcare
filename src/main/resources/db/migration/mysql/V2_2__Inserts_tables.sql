INSERT INTO status_fornecedor(idstatus, status)
values(0, "DESATIVO");

INSERT INTO status_fornecedor(idstatus, status)
values(1, "ATIVO");


INSERT INTO status_setor(idstatus, status)
values(0, "DESATIVO");

INSERT INTO status_setor(idstatus, status)
values(1, "ATIVO");


INSERT INTO status_produto(idstatus, status)
values(0, "DESATIVO");

INSERT INTO status_produto(idstatus, status)
values(1, "ATIVO"); 

INSERT INTO roles_status(idstatus, status)
values(0, "DESATIVO");

INSERT INTO roles_status(idstatus, status)
values(1, "ATIVO"); 

INSERT INTO users_status(idstatus, status)
values(0, "DESATIVADO");

INSERT INTO users_status(idstatus, status)
values(1, "ATIVO"); 

INSERT INTO tipo_medida(idmedida, descricao)
values(0, "UNIDADE");

INSERT INTO tipo_medida(idmedida, descricao)
values(1, "PACOTE");

INSERT INTO tipo_medida(idmedida, descricao)
values(2, "CAIXA");

INSERT INTO tipo_medida(idmedida, descricao)
values(3, "QUILOGRAMA");

INSERT INTO tipo_medida(idmedida, descricao)
values(4, "GRAMA");

INSERT INTO tipo_medida(idmedida, descricao)
values(5, "MILIGRAMA");


INSERT into tipo_movimento(idtipomovimento, tipo_movimento)
values(0, "ENTRADA");

INSERT into tipo_movimento(idtipomovimento, tipo_movimento)
values(1, "SAIDA");

INSERT into tipo_movimento(idtipomovimento, tipo_movimento)
values(2, "DESCARTE");


insert into roles(DESCRICAO, COD_STATUS)
values('ADMIN', 1);

insert into roles(DESCRICAO, COD_STATUS)
values('FUNC', 1);


INSERT INTO users(usuario, dt_nascimento, email, senha, user_status)
values('admin', '1997-04-22 00:00:00', 'brunosouzasantos07@gmail.com', '$2a$10$UiKAcpN2buchLOuTyyEEeuQVGsU4x0AZUJv9lyzXMchdjogA481WK', '1')

INSERT INTO users_roles(cod_user,cod_role)
values(1,1), (1,2)
