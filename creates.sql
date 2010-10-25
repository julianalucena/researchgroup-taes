CREATE TABLE membro (
  idMembro int(10) NOT NULL auto_increment,
  nome varchar(200) NOT NULL unique,
  departamento varchar(200) NOT NULL,
  universidade varchar(200) NOT NULL,
  email varchar(200) NOT NULL,
  telefone varchar(200) NOT NULL,
  website varchar(200) NOT NULL,
  cidade varchar(200) NOT NULL,
  pais varchar(200) NOT NULL,
  foto longblob NOT NULL,
  status varchar(200) NOT NULL,
  PRIMARY KEY  (idMembro)
);

CREATE TABLE professor (
  idProfessor int(10) unsigned NOT NULL auto_increment,
  FK_membro int(10) NOT NULL,
  FOREIGN KEY (FK_membro) REFERENCES membro(idMembro) ON DELETE CASCADE,
  PRIMARY KEY (idProfessor)
);

CREATE TABLE pesquisador (
  idPesquisador int(10) unsigned NOT NULL auto_increment,
  FK_membro int(10) NOT NULL,
  FOREIGN KEY (FK_membro) REFERENCES membro(idMembro) ON DELETE CASCADE,
  PRIMARY KEY (idPesquisador)
);

CREATE TABLE estudante (
  idEstudante int(10) unsigned NOT NULL auto_increment,
  nome_orientador varchar(200) NOT NULL,
  nome_coorientador varchar(200) NOT NULL,
  tipo_estudante varchar(200) NOT NULL,
  FK_membro int(10) NOT NULL,
  FOREIGN KEY (FK_membro) REFERENCES membro(idMembro) ON DELETE CASCADE,
  PRIMARY KEY (idEstudante)
);

CREATE TABLE publicacao (
  idPublicacao int(10) NOT NULL auto_increment,
  titulo varchar(200) NOT NULL,
  ano int(4) NOT NULL,
  pdf longblob NOT NULL,
  PRIMARY KEY (idPublicacao)
);

CREATE TABLE membro_publicacao (
  idMembro int(10) NOT NULL,
  idPublicacao int(10) NOT NULL,
  FOREIGN KEY (idMembro) REFERENCES membro(idMembro) ON DELETE CASCADE,
  FOREIGN KEY (idPublicacao) REFERENCES publicacao(idPublicacao) ON DELETE CASCADE,
  PRIMARY KEY (idMembro, idPublicacao)
);

CREATE TABLE tese_doutorado (
  idTeseDoutorado int(10) NOT NULL auto_increment,
  escola varchar(200) NOT NULL,
  mes int(2),
  fk_publicacao int(10) NOT NULL,
  FOREIGN KEY (fk_publicacao) REFERENCES publicacao(idPublicacao),
  PRIMARY KEY (idTeseDoutorado)
);

CREATE TABLE dissertacao_mestrado (
  idDissertacaoMestrado int(10) NOT NULL auto_increment,
  escola varchar(200) NOT NULL,
  mes int(2),
  fk_publicacao int(10) NOT NULL,
  FOREIGN KEY (fk_publicacao) REFERENCES publicacao(idPublicacao),
  PRIMARY KEY (idDissertacaoMestrado)
);

CREATE TABLE periodico_revista (
  idPeriodicoRevista int(10) NOT NULL auto_increment,
  journal varchar(200) NOT NULL,
  volume int(4),
  numero int(4),
  paginas int(4),
  fk_publicacao int(10) NOT NULL,
  FOREIGN KEY (fk_publicacao) REFERENCES publicacao(idPublicacao),
  PRIMARY KEY (idPeriodicoRevista)
);

CREATE TABLE conferencia (
  idConferencia int(10) NOT NULL auto_increment,
  conferencia varchar(200) NOT NULL,
  paginas int(4),
  mes int(2),
  fk_publicacao int(10) NOT NULL,
  FOREIGN KEY (fk_publicacao) REFERENCES publicacao(idPublicacao),
  PRIMARY KEY (idConferencia)
);

CREATE TABLE naoMembro (
  idNaomembro int(10) NOT NULL auto_increment,
  nome varchar(200) NOT NULL,
  PRIMARY KEY (idnaomembro)
);

CREATE TABLE naomembro_publicacao (
  idnaoMembro int(10) NOT NULL,
  idPublicacao int(10) NOT NULL,
  FOREIGN KEY (idnaoMembro) REFERENCES naomembro(idnaoMembro) ON DELETE CASCADE,
  FOREIGN KEY (idPublicacao) REFERENCES publicacao(idPublicacao) ON DELETE CASCADE,
  PRIMARY KEY (idnaoMembro, idPublicacao)
);

CREATE TABLE linha_pesquisa (
  idLinhaPesquisa int(10) NOT NULL auto_increment,
  titulo varchar(400) NOT NULL,
  breve_descricao varchar(2000) NOT NULL,
  detalhada_descricao varchar(10000) NOT NULL,
  financiadores varchar(1000) NOT NULL,
  links_relacionados varchar(1000) NOT NULL,
  PRIMARY KEY (idLinhaPesquisa)
);

CREATE TABLE publicacao_linhapesquisa (
  idLinhaPesquisa int(10) NOT NULL,
  idPublicacao int(10) NOT NULL,
  FOREIGN KEY (idLinhaPesquisa) REFERENCES linha_pesquisa(idLinhaPesquisa) ON DELETE CASCADE,
  FOREIGN KEY (idPublicacao) REFERENCES publicacao(idPublicacao) ON DELETE CASCADE,
  PRIMARY KEY (idLinhaPesquisa, idPublicacao)
);

CREATE TABLE membro_linhapesquisa (
  idLinhaPesquisa int(10) NOT NULL,
  idMembro int(10) NOT NULL,
  FOREIGN KEY (idLinhaPesquisa) REFERENCES linha_pesquisa(idLinhaPesquisa) ON DELETE CASCADE,
  FOREIGN KEY (idMembro) REFERENCES membro(idMembro) ON DELETE CASCADE,
  PRIMARY KEY (idLinhaPesquisa, idMembro)
);

CREATE TABLE projeto_pesquisa (
  idProjetoPesquisa int(10) NOT NULL auto_increment,
  nome varchar(400) NOT NULL,
  descricao varchar(1000) NOT NULL,
  PRIMARY KEY (idProjetoPesquisa)
);

CREATE TABLE projetopesquisa_publicacao (
  idProjetoPesquisa int(10) NOT NULL,
  idPublicacao int(10) NOT NULL,
  FOREIGN KEY (idProjetoPesquisa) REFERENCES projeto_pesquisa(idProjetoPesquisa) ON DELETE CASCADE,
  FOREIGN KEY (idPublicacao) REFERENCES publicacao(idPublicacao) ON DELETE CASCADE,
  PRIMARY KEY (idProjetoPesquisa, idPublicacao)
);

CREATE TABLE tipo_publicacao (
id int(10) not null auto_increment
, nome varchar(100) not null
, PRIMARY KEY(id)
);

CREATE TABLE tipo_propriedade (
id int(10) not null auto_increment
, nome varchar(100) not null
, tipo varchar(100) not null
, idTipoPublicacao  int(10) not null
, PRIMARY KEY (id)
, FOREIGN KEY (idTipoPublicacao ) REFERENCES tipo_publicacao (id)
);

CREATE TABLE publicacao_AOM (
id int(10) not null auto_increment
, nome varchar(100) not null
, idTipoPublicacao int(10) not null
, PRIMARY KEY (id)
, FOREIGN KEY (idTipoPublicacao) REFERENCES tipo_publicacao (id)
);

CREATE TABLE propriedade (
id int(10) not null auto_increment
, nome varchar(100) not null
, idPublicacao int(10) not null
, idTipoPropriedade int(10) not null
, PRIMARY KEY (id)
, FOREIGN KEY (idPublicacao) REFERENCES publicacao_AOM (id)
, FOREIGN KEY (idTipoPropriedade) REFERENCES tipo_propriedade (id) 
);