-- MySQL dump 10.13  Distrib 5.7.19, for Linux (x86_64)
--
-- Host: localhost    Database: petcaredb
-- ------------------------------------------------------
-- Server version	5.7.19-0ubuntu0.16.04.1
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO,POSTGRESQL' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table "fornecedor"
--

DROP TABLE IF EXISTS "fornecedor";
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE "fornecedor" (
  "IDFORNECEDOR" int(11) NOT NULL,
  "NOME" varchar(100) NOT NULL,
  "CNPJ" varchar(14) NOT NULL,
  "COD_STATUS" int(11) DEFAULT NULL,
  PRIMARY KEY ("IDFORNECEDOR"),
  KEY "COD_STATUS" ("COD_STATUS"),
  CONSTRAINT "fornecedor_ibfk_1" FOREIGN KEY ("COD_STATUS") REFERENCES "status_fornecedor" ("IDSTATUS")
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table "fornecedor"
--

LOCK TABLES "fornecedor" WRITE;
/*!40000 ALTER TABLE "fornecedor" DISABLE KEYS */;
INSERT INTO "fornecedor" VALUES (1,'Medical Pet&Things','64439010000100',1),(2,'Distri Equipamente Cirurgicos','38381000000151',1),(3,'DNAPet','21824544000148',1),(4,'Fornecedor Teste','28861816000129',0);
/*!40000 ALTER TABLE "fornecedor" ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table "movimentacoes"
--

DROP TABLE IF EXISTS "movimentacoes";
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE "movimentacoes" (
  "IDMOVIMENTACAO" int(11) NOT NULL,
  "COD_TIPOMOVIMENTO" int(11) NOT NULL,
  "COD_PRODUTO" int(11) DEFAULT NULL,
  "COD_SETOR" int(11) DEFAULT NULL,
  "DT_MOVIMENTO" timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "QTD_ITENS" int(11) NOT NULL,
  "USUARIO" varchar(255) NOT NULL,
  "VL_UNITARIO" double NOT NULL,
  PRIMARY KEY ("IDMOVIMENTACAO"),
  KEY "COD_SETOR" ("COD_SETOR"),
  KEY "COD_TIPOMOVIMENTO" ("COD_TIPOMOVIMENTO"),
  KEY "movimentacoes_ibfk_1" ("COD_PRODUTO"),
  CONSTRAINT "movimentacoes_ibfk_1" FOREIGN KEY ("COD_PRODUTO") REFERENCES "produto" ("IDPRODUTO") ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT "movimentacoes_ibfk_2" FOREIGN KEY ("COD_SETOR") REFERENCES "setor" ("IDSETOR") ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT "movimentacoes_ibfk_3" FOREIGN KEY ("COD_TIPOMOVIMENTO") REFERENCES "tipo_movimento" ("IDTIPOMOVIMENTO") ON DELETE NO ACTION ON UPDATE NO ACTION
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table "movimentacoes"
--

LOCK TABLES "movimentacoes" WRITE;
/*!40000 ALTER TABLE "movimentacoes" DISABLE KEYS */;
INSERT INTO "movimentacoes" VALUES (1,0,22,1,'2017-06-08 05:47:53',15,'zezinho',20),(3,1,2,2,'2017-04-04 03:14:10',3,'zezinho',15),(4,2,11,2,'2017-04-04 05:24:05',1,'admin',330),(5,1,2,2,'2017-04-04 03:14:10',3,'zezinho',15),(6,2,11,2,'2017-04-04 05:24:05',1,'admin',330),(7,1,11,3,'2017-04-07 00:32:05',1,'zezinho',400),(8,1,21,2,'2017-05-07 04:12:51',3,'admin',19),(9,2,2,3,'2017-05-07 04:13:11',3,'zezinho',12),(10,1,13,3,'2017-05-07 04:13:13',4,'admin',30),(11,0,21,2,'2017-05-08 03:52:44',40,'zezinho',15),(12,2,7,3,'2017-05-08 03:52:44',43,'jao',15),(13,2,15,2,'2017-05-08 03:52:44',12,'zezinho',15),(14,1,17,2,'2017-05-08 03:52:44',12,'admin',15),(15,1,21,2,'2017-05-08 04:25:09',4,'zezinho',19),(16,1,18,1,'2017-05-08 04:25:09',16,'admin',19),(17,1,21,2,'2017-05-09 03:26:47',4,'zezinho',19),(18,0,11,2,'2017-05-11 05:23:38',3,'admin',330),(19,0,13,2,'2017-05-11 05:23:38',15,'zezinho',20),(20,0,11,2,'2017-05-11 06:04:31',1,'admin',330),(21,0,13,2,'2017-05-11 06:04:31',0,'zezinho',20),(22,0,13,2,'2017-05-11 06:11:24',2,'jao',20),(23,0,2,2,'2017-05-11 06:12:10',8,'zezinho',12),(24,2,22,2,'2017-05-12 04:00:37',3,'admin',20),(25,0,20,3,'2017-05-12 04:00:37',16,'zezinho',6),(26,0,22,1,'2017-05-14 17:55:45',3,'admin',20),(27,0,21,1,'2017-05-14 21:07:22',4,'zezinho',15),(28,0,16,2,'2017-05-17 04:23:30',3,'admin',12),(29,0,14,1,'2017-05-18 04:03:23',12,'zezinho',20),(30,0,16,3,'2017-05-18 04:03:23',6,'admin',12),(31,2,19,3,'2017-05-18 04:03:23',8,'zezinho',12),(32,2,23,2,'2017-05-18 04:03:23',8,'jao',23.33),(33,1,13,2,'2017-05-18 04:03:23',12,'zezinho',30),(34,0,20,3,'2017-05-18 04:03:23',3,'admin',6),(35,0,14,1,'2017-05-23 03:55:09',12,'zezinho',20),(36,0,20,1,'2017-05-23 03:55:09',18,'admin',6),(37,1,20,1,'2017-05-23 03:55:09',1,'zezinho',8),(38,0,20,2,'2017-05-24 05:23:38',12,'admin',6),(39,0,11,1,'2017-05-24 05:23:38',22,'zezinho',330),(40,0,16,1,'2017-05-24 05:24:37',15,'admin',12),(41,1,21,1,'2017-05-24 05:58:21',15,'zezinho',19),(42,0,21,1,'2017-05-24 05:58:21',15,'jao',15),(43,1,16,1,'2017-05-24 05:58:21',20,'zezinho',18),(44,0,15,1,'2017-05-24 05:58:21',18,'admin',15),(45,1,21,1,'2017-05-25 03:01:00',15,'zezinho',19),(46,0,26,2,'2017-05-25 03:01:00',18,'admin',1),(47,2,9,3,'2017-05-25 03:51:52',15,'zezinho',15),(48,0,9,1,'2017-05-25 03:51:52',15,'admin',15),(49,0,20,1,'2017-05-25 03:51:52',18,'zezinho',6),(50,0,20,1,'2017-06-04 00:16:47',32,'jao',6),(51,0,19,1,'2017-06-04 00:19:46',33,'zezinho',12),(72,0,15,1,'2017-06-14 03:43:58',15,'jao',15),(73,0,2,3,'2017-06-14 04:20:47',20,'zezinho',12),(74,0,22,1,'2017-06-14 05:06:45',20,'admin',20),(75,1,22,1,'2017-06-14 05:06:45',20,'zezinho',30),(76,0,22,1,'2017-06-14 05:20:16',20,'admin',20),(77,0,30,1,'2017-06-21 03:18:31',20,'zezinho',55.55),(78,1,22,2,'2017-06-21 03:18:31',18,'admin',30),(79,0,21,2,'2017-06-21 06:21:13',20,'zezinho',15),(82,1,14,2,'2017-06-21 06:56:53',6,'jao',32),(83,1,9,1,'2017-07-02 06:12:26',20,'zezinho',19),(84,2,19,1,'2017-07-02 06:35:14',2,'admin',12),(85,0,30,2,'2017-07-06 06:32:34',20,'zezinho',55.55),(86,1,30,1,'2017-07-06 06:33:04',20,'admin',64.99),(87,1,23,2,'2017-07-06 06:35:18',10,'zezinho',30),(88,1,11,1,'2017-07-06 06:35:18',2,'admin',400),(89,1,2,1,'2017-07-07 03:04:23',5,'zezinho',15),(93,1,21,1,'2017-07-23 00:28:23',10,'zezinho',19),(94,1,26,1,'2017-07-23 00:28:23',5,'admin',19),(95,0,30,1,'2017-07-26 03:15:45',10,'zezinho',55.55),(96,0,15,5,'2017-07-26 03:15:45',15,'admin',15),(97,0,9,1,'2017-08-10 04:17:11',10,'zezinho',15),(98,1,30,2,'2017-08-10 04:17:20',16,'admin',64.99),(99,1,30,2,'2017-08-11 03:55:08',2,'jao',64.99),(104,0,30,1,'2017-08-11 04:00:10',50,'jao',55.55),(105,0,28,3,'2017-08-11 04:00:10',50,'zezinho',20),(106,1,30,1,'2017-08-11 04:01:18',30,'admin',64.99),(108,1,22,2,'2017-08-11 04:08:45',20,'jao',30),(109,1,11,1,'2017-08-11 04:08:45',2,'zezinho',400),(110,0,22,1,'2017-08-11 04:49:45',18,'admin',20),(111,1,23,2,'2017-08-11 04:49:45',10,'admin',30),(112,0,31,1,'2017-08-13 01:15:17',10,'admin',33),(113,0,33,1,'2017-08-13 01:15:17',20,'admin',40),(114,0,9,1,'2017-08-13 05:01:46',25,'admin',15),(115,0,23,1,'2017-08-13 05:01:46',40,'admin',23.33),(116,0,7,3,'2017-08-13 05:21:30',60,'admin',15),(117,0,17,3,'2017-08-13 05:21:30',20,'admin',10),(118,0,18,3,'2017-08-13 05:21:30',30,'admin',15),(119,1,15,3,'2017-08-13 05:40:36',20,'zezinho',25),(120,0,35,2,'2017-08-13 19:23:55',10,'admin',40),(121,0,30,1,'2017-08-15 16:08:22',10,'zezinho',55.55),(122,1,26,1,'2017-08-17 04:58:33',13,'admin',19),(123,1,23,2,'2017-08-20 16:46:29',10,'admin',30),(124,0,20,1,'2017-08-24 17:41:41',20,'admin',6),(125,0,22,1,'2017-08-26 22:08:16',10,'admin',20),(126,0,27,3,'2017-08-26 22:08:16',20,'admin',20),(127,0,26,2,'2017-08-26 22:08:16',20,'admin',1);
/*!40000 ALTER TABLE "movimentacoes" ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table "produto"
--

DROP TABLE IF EXISTS "produto";
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE "produto" (
  "IDPRODUTO" int(11) NOT NULL,
  "COD_TIPO" int(11) DEFAULT NULL,
  "COD_FORNECEDOR" int(11) DEFAULT NULL,
  "DESCRICAO" varchar(100) NOT NULL,
  "MEDIDA" varchar(20) NOT NULL,
  "VL_COMPRA" double NOT NULL,
  "VL_VENDA" double DEFAULT NULL,
  "STATUS_PRODUTO" bit(1) NOT NULL,
  PRIMARY KEY ("IDPRODUTO"),
  KEY "fk_status_idx" ("STATUS_PRODUTO"),
  KEY "fk_fornecedor" ("COD_FORNECEDOR"),
  CONSTRAINT "fk_fornecedor" FOREIGN KEY ("COD_FORNECEDOR") REFERENCES "fornecedor" ("IDFORNECEDOR") ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT "fk_status" FOREIGN KEY ("STATUS_PRODUTO") REFERENCES "status_produto" ("IDSTATUS") ON DELETE NO ACTION ON UPDATE NO ACTION
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table "produto"
--

LOCK TABLES "produto" WRITE;
/*!40000 ALTER TABLE "produto" DISABLE KEYS */;
INSERT INTO "produto" VALUES (2,2,2,'Remover de feridas','1',12,15,''),(7,3,2,'Tubo de limpesa','1',15,22,''),(9,3,1,'Alicate de Unha','0',15,19,''),(10,2,3,'Produto teste','1',15,33,'\0'),(11,2,3,'Kit Emergencial contra Queimaduras R2','1',330,400,''),(13,2,3,'Seringa RC2','2',20,30,''),(14,2,3,'Pomada Queimadura','1',20,32,''),(15,3,1,'Limpa Unhas','1',15,25,''),(16,3,1,'Shampoo Anti Pulga','0',12,18,''),(17,3,2,'Seringa 5ml','2',10,15,''),(18,3,1,'Sabonete Pet','1',15,19,''),(19,3,1,'Sabonete Liquido Pet','1',12,0.15,''),(20,3,2,'Algodão','1',6,8,''),(21,2,3,'Agulha 3ml','2',15,19,''),(22,3,3,'Agua Oxigenada','0',20,30,''),(23,2,3,'Anestesia  3mg','1',23.33,30,''),(26,2,1,'Anestesia  9mg','1',1,19,''),(27,2,3,'Seringa','2',20,190,''),(28,3,2,'Removedor','1',20,45,''),(29,3,3,'Seringa 4ml','2',50,65,''),(30,2,2,'Agulhas 2ml','2',55.55,64.99,''),(31,2,2,'Produto teste 4','2',33,190,'\0'),(32,2,3,'Produto teste 5','0',40,60,'\0'),(33,3,3,'Produto Teste 2','1',40,60,'\0'),(35,5,2,'Produto Teste 3','1',40,50,'\0');
/*!40000 ALTER TABLE "produto" ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table "roles"
--

DROP TABLE IF EXISTS "roles";
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE "roles" (
  "IDROLE" int(11) NOT NULL,
  "DESCRICAO" varchar(50) NOT NULL,
  "COD_STATUS" bit(1) NOT NULL,
  PRIMARY KEY ("IDROLE"),
  KEY "COD_STATUS" ("COD_STATUS"),
  CONSTRAINT "roles_ibfk_1" FOREIGN KEY ("COD_STATUS") REFERENCES "roles_status" ("IDSTATUS")
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table "roles"
--

LOCK TABLES "roles" WRITE;
/*!40000 ALTER TABLE "roles" DISABLE KEYS */;
INSERT INTO "roles" VALUES (1,'ADMIN',''),(3,'FUNC','');
/*!40000 ALTER TABLE "roles" ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table "roles_status"
--

DROP TABLE IF EXISTS "roles_status";
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE "roles_status" (
  "IDSTATUS" bit(1) NOT NULL,
  "STATUS" varchar(20) DEFAULT NULL,
  PRIMARY KEY ("IDSTATUS")
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table "roles_status"
--

LOCK TABLES "roles_status" WRITE;
/*!40000 ALTER TABLE "roles_status" DISABLE KEYS */;
INSERT INTO "roles_status" VALUES ('\0','DESATIVO'),('','ATIVO');
/*!40000 ALTER TABLE "roles_status" ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table "schema_version"
--

DROP TABLE IF EXISTS "schema_version";
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE "schema_version" (
  "installed_rank" int(11) NOT NULL,
  "version" varchar(50) DEFAULT NULL,
  "description" varchar(200) NOT NULL,
  "type" varchar(20) NOT NULL,
  "script" varchar(1000) NOT NULL,
  "checksum" int(11) DEFAULT NULL,
  "installed_by" varchar(100) NOT NULL,
  "installed_on" timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "execution_time" int(11) NOT NULL,
  "success" tinyint(1) NOT NULL,
  PRIMARY KEY ("installed_rank"),
  KEY "schema_version_s_idx" ("success")
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table "schema_version"
--

LOCK TABLES "schema_version" WRITE;
/*!40000 ALTER TABLE "schema_version" DISABLE KEYS */;
INSERT INTO "schema_version" VALUES (1,'1','<< Flyway Baseline >>','BASELINE','<< Flyway Baseline >>',NULL,'root','2017-05-18 03:53:47',0,1),(2,'1.2','create tables','SQL','V1_2__create_tables.sql',1141121446,'root','2017-05-18 03:53:47',7,1),(3,'2.2','Inserts tables','SQL','V2_2__Inserts_tables.sql',-283022542,'root','2017-05-18 03:55:08',246,1);
/*!40000 ALTER TABLE "schema_version" ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table "setor"
--

DROP TABLE IF EXISTS "setor";
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE "setor" (
  "IDSETOR" int(11) NOT NULL,
  "SETOR" varchar(100) NOT NULL,
  "STATUS" int(11) DEFAULT NULL,
  PRIMARY KEY ("IDSETOR"),
  KEY "STATUS" ("STATUS"),
  CONSTRAINT "setor_ibfk_1" FOREIGN KEY ("STATUS") REFERENCES "status_setor" ("IDSTATUS")
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table "setor"
--

LOCK TABLES "setor" WRITE;
/*!40000 ALTER TABLE "setor" DISABLE KEYS */;
INSERT INTO "setor" VALUES (1,'Clinica',1),(2,'Cirurgico',1),(3,'Limpeza',1),(4,'Loja',0),(5,'Alimentação',0);
/*!40000 ALTER TABLE "setor" ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table "status_fornecedor"
--

DROP TABLE IF EXISTS "status_fornecedor";
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE "status_fornecedor" (
  "IDSTATUS" int(11) NOT NULL,
  "STATUS" varchar(20) DEFAULT NULL,
  PRIMARY KEY ("IDSTATUS")
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table "status_fornecedor"
--

LOCK TABLES "status_fornecedor" WRITE;
/*!40000 ALTER TABLE "status_fornecedor" DISABLE KEYS */;
INSERT INTO "status_fornecedor" VALUES (0,'DESATIVO'),(1,'ATIVO');
/*!40000 ALTER TABLE "status_fornecedor" ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table "status_produto"
--

DROP TABLE IF EXISTS "status_produto";
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE "status_produto" (
  "IDSTATUS" bit(1) NOT NULL,
  "STATUS" varchar(20) DEFAULT NULL,
  PRIMARY KEY ("IDSTATUS")
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table "status_produto"
--

LOCK TABLES "status_produto" WRITE;
/*!40000 ALTER TABLE "status_produto" DISABLE KEYS */;
INSERT INTO "status_produto" VALUES ('\0','DESATIVO'),('','ATIVO');
/*!40000 ALTER TABLE "status_produto" ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table "status_setor"
--

DROP TABLE IF EXISTS "status_setor";
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE "status_setor" (
  "IDSTATUS" int(11) NOT NULL,
  "STATUS" varchar(20) DEFAULT NULL,
  PRIMARY KEY ("IDSTATUS")
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table "status_setor"
--

LOCK TABLES "status_setor" WRITE;
/*!40000 ALTER TABLE "status_setor" DISABLE KEYS */;
INSERT INTO "status_setor" VALUES (0,'DESATIVO'),(1,'ATIVO');
/*!40000 ALTER TABLE "status_setor" ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table "tipo_medida"
--

DROP TABLE IF EXISTS "tipo_medida";
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE "tipo_medida" (
  "IDMEDIDA" int(11) NOT NULL,
  "DESCRICAO" varchar(50) NOT NULL,
  PRIMARY KEY ("IDMEDIDA")
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table "tipo_medida"
--

LOCK TABLES "tipo_medida" WRITE;
/*!40000 ALTER TABLE "tipo_medida" DISABLE KEYS */;
INSERT INTO "tipo_medida" VALUES (0,'UNIDADE'),(1,'PACOTE'),(2,'CAIXA'),(3,'QUILOGRAMA'),(4,'GRAMA'),(5,'MILIGRAMA');
/*!40000 ALTER TABLE "tipo_medida" ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table "tipo_movimento"
--

DROP TABLE IF EXISTS "tipo_movimento";
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE "tipo_movimento" (
  "IDTIPOMOVIMENTO" int(11) NOT NULL,
  "TIPO_MOVIMENTO" varchar(50) NOT NULL,
  PRIMARY KEY ("IDTIPOMOVIMENTO")
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table "tipo_movimento"
--

LOCK TABLES "tipo_movimento" WRITE;
/*!40000 ALTER TABLE "tipo_movimento" DISABLE KEYS */;
INSERT INTO "tipo_movimento" VALUES (0,'ENTRADA'),(1,'SAIDA'),(2,'DESCARTE');
/*!40000 ALTER TABLE "tipo_movimento" ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table "tipo_produto"
--

DROP TABLE IF EXISTS "tipo_produto";
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE "tipo_produto" (
  "ID_TIPO" int(11) NOT NULL,
  "TIPO" varchar(50) NOT NULL,
  "DESCRICAO" varchar(300) DEFAULT NULL,
  "COD_STATUS" bit(1) NOT NULL,
  PRIMARY KEY ("ID_TIPO")
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table "tipo_produto"
--

LOCK TABLES "tipo_produto" WRITE;
/*!40000 ALTER TABLE "tipo_produto" DISABLE KEYS */;
INSERT INTO "tipo_produto" VALUES (2,'Cirurgico','Produtos utilizados em procedimentos cirúrgicos.',''),(3,'Limpeza','Produtos utilizados no setor de limpeza da clinica, utilizados também na limpeza dos pets, e para venda ao cliente.',''),(5,'Segurança','Produtos utilizados internamente na clinica para fins de segurança do trabalho!','');
/*!40000 ALTER TABLE "tipo_produto" ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table "users"
--

DROP TABLE IF EXISTS "users";
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE "users" (
  "IDUSUARIO" int(11) NOT NULL,
  "USUARIO" varchar(255) NOT NULL,
  "DT_NASCIMENTO" timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "EMAIL" varchar(300) NOT NULL,
  "SENHA" varchar(500) NOT NULL,
  "COD_STATUS" bit(1) NOT NULL,
  PRIMARY KEY ("IDUSUARIO"),
  KEY "COD_STATUS" ("COD_STATUS"),
  CONSTRAINT "users_ibfk_1" FOREIGN KEY ("COD_STATUS") REFERENCES "users_status" ("IDSTATUS")
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table "users"
--

LOCK TABLES "users" WRITE;
/*!40000 ALTER TABLE "users" DISABLE KEYS */;
INSERT INTO "users" VALUES (9,'admin','1997-03-22 03:00:00','brunosouzasantos07@gmail.com','$2a$10$gJmzboWtzLflYbBkkjbfROfZ/nwRfT4/fuI.AAclj7vtqugtdgSLW',''),(10,'zezinho','1989-09-25 03:00:00','zezinho@gmail.com','$2a$10$JTSfHpXDE/upq7y2OUKmI.sTfS0WBOwc2M79ZuE5jpTTZXVXOvPn6',''),(13,'jao','2009-11-26 03:00:00','jao@gmail.com','$2a$10$2n5rsBJlTK6Ir6Dma7JrZuYSOeWMyiYmS98riuMknOLLJjLpDNcji',''),(14,'Usuario Teste','2009-01-06 03:00:00','user@gmail.com','$2a$10$OQVrKeV.YKBdNgG5oB2WoOnKjcprvI5eY8ZSswImCDm561m5co.qi','\0');
/*!40000 ALTER TABLE "users" ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table "users_roles"
--

DROP TABLE IF EXISTS "users_roles";
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE "users_roles" (
  "IDREGISTRO" int(11) NOT NULL,
  "COD_USER" int(11) DEFAULT NULL,
  "COD_ROLE" int(11) DEFAULT NULL,
  PRIMARY KEY ("IDREGISTRO"),
  KEY "users_roles_ibfk_1" ("COD_USER"),
  KEY "users_roles_ibfk_2" ("COD_ROLE"),
  CONSTRAINT "users_roles_ibfk_1" FOREIGN KEY ("COD_USER") REFERENCES "users" ("IDUSUARIO") ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT "users_roles_ibfk_2" FOREIGN KEY ("COD_ROLE") REFERENCES "roles" ("IDROLE") ON DELETE CASCADE ON UPDATE CASCADE
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table "users_roles"
--

LOCK TABLES "users_roles" WRITE;
/*!40000 ALTER TABLE "users_roles" DISABLE KEYS */;
INSERT INTO "users_roles" VALUES (18,9,1),(23,10,3),(25,13,3),(26,14,3);
/*!40000 ALTER TABLE "users_roles" ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table "users_status"
--

DROP TABLE IF EXISTS "users_status";
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE "users_status" (
  "IDSTATUS" bit(1) NOT NULL,
  "STATUS" varchar(20) DEFAULT NULL,
  PRIMARY KEY ("IDSTATUS")
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table "users_status"
--

LOCK TABLES "users_status" WRITE;
/*!40000 ALTER TABLE "users_status" DISABLE KEYS */;
INSERT INTO "users_status" VALUES ('\0','DESATIVO'),('','ATIVO');
/*!40000 ALTER TABLE "users_status" ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-27  2:05:29
