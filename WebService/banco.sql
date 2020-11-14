-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           8.0.21 - MySQL Community Server - GPL
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              11.1.0.6116
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Copiando estrutura do banco de dados para av1_db
CREATE DATABASE IF NOT EXISTS `av1_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `av1_db`;

-- Copiando estrutura para tabela av1_db.aluno
CREATE TABLE IF NOT EXISTS `aluno` (
  `MATRICULA` int NOT NULL,
  `ALUNO_NOME` varchar(45) DEFAULT NULL,
  `CURSO_idCURSO` int NOT NULL,
  `idusuario` int DEFAULT NULL,
  PRIMARY KEY (`MATRICULA`,`CURSO_idCURSO`),
  KEY `fk_ALUNO_CURSO1_idx` (`CURSO_idCURSO`),
  CONSTRAINT `fk_ALUNO_CURSO1` FOREIGN KEY (`CURSO_idCURSO`) REFERENCES `curso` (`idCURSO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela av1_db.aluno: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `aluno` DISABLE KEYS */;
INSERT INTO `aluno` (`MATRICULA`, `ALUNO_NOME`, `CURSO_idCURSO`, `idusuario`) VALUES
	(2020100, 'Leonardo Oliveira', 1, 2),
	(2020101, 'Thiago Gerpi', 2, NULL),
	(2020102, 'Paulo Enrique', 3, NULL);
/*!40000 ALTER TABLE `aluno` ENABLE KEYS */;

-- Copiando estrutura para tabela av1_db.aluno_has_disciplina
CREATE TABLE IF NOT EXISTS `aluno_has_disciplina` (
  `ALUNO_MATRICULA` int NOT NULL,
  `DISCIPLINA_idDISCIPLINA` int NOT NULL,
  PRIMARY KEY (`ALUNO_MATRICULA`,`DISCIPLINA_idDISCIPLINA`),
  KEY `fk_ALUNO_has_DISCIPLINA_DISCIPLINA1_idx` (`DISCIPLINA_idDISCIPLINA`),
  KEY `fk_ALUNO_has_DISCIPLINA_ALUNO1_idx` (`ALUNO_MATRICULA`),
  CONSTRAINT `fk_ALUNO_has_DISCIPLINA_ALUNO1` FOREIGN KEY (`ALUNO_MATRICULA`) REFERENCES `aluno` (`MATRICULA`),
  CONSTRAINT `fk_ALUNO_has_DISCIPLINA_DISCIPLINA1` FOREIGN KEY (`DISCIPLINA_idDISCIPLINA`) REFERENCES `disciplina` (`idDISCIPLINA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela av1_db.aluno_has_disciplina: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `aluno_has_disciplina` DISABLE KEYS */;
INSERT INTO `aluno_has_disciplina` (`ALUNO_MATRICULA`, `DISCIPLINA_idDISCIPLINA`) VALUES
	(2020100, 1),
	(2020101, 1),
	(2020100, 2),
	(2020100, 3),
	(2020101, 3),
	(2020101, 4),
	(2020100, 5),
	(2020101, 5),
	(2020102, 6),
	(2020102, 7),
	(2020102, 8),
	(2020102, 10);
/*!40000 ALTER TABLE `aluno_has_disciplina` ENABLE KEYS */;

-- Copiando estrutura para tabela av1_db.avaliacao
CREATE TABLE IF NOT EXISTS `avaliacao` (
  `idAVALIACAO` int NOT NULL,
  `TRABALHO_ACADEMICO_AV1` double DEFAULT NULL,
  `APS1` double DEFAULT NULL,
  `TRABALHO_ACADEMICO_AV2` double DEFAULT NULL,
  `APS2` double DEFAULT NULL,
  `TRABALHO_ACADEMICO_AV3` double DEFAULT NULL,
  `idMATRICULA` int NOT NULL,
  `idDISCIPLINA` int NOT NULL,
  PRIMARY KEY (`idAVALIACAO`),
  KEY `idMATRICULA` (`idMATRICULA`),
  KEY `idDISCIPLINA` (`idDISCIPLINA`),
  CONSTRAINT `avaliacao_ibfk_1` FOREIGN KEY (`idMATRICULA`) REFERENCES `aluno` (`MATRICULA`),
  CONSTRAINT `avaliacao_ibfk_2` FOREIGN KEY (`idDISCIPLINA`) REFERENCES `disciplina` (`idDISCIPLINA`),
  CONSTRAINT `avaliacao_chk_1` CHECK (((`APS1` >= 0.0) and (`APS1` <= 3.0))),
  CONSTRAINT `avaliacao_chk_2` CHECK (((`APS2` >= 0.0) and (`APS2` <= 2.0))),
  CONSTRAINT `avaliacao_chk_3` CHECK (((`TRABALHO_ACADEMICO_AV1` >= 0.0) and (`TRABALHO_ACADEMICO_AV1` <= 7.0))),
  CONSTRAINT `avaliacao_chk_4` CHECK (((`TRABALHO_ACADEMICO_AV2` >= 0.0) and (`TRABALHO_ACADEMICO_AV2` <= 8.0))),
  CONSTRAINT `avaliacao_chk_5` CHECK (((`TRABALHO_ACADEMICO_AV3` >= 0.0) and (`TRABALHO_ACADEMICO_AV3` <= 10.0)))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela av1_db.avaliacao: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `avaliacao` DISABLE KEYS */;
INSERT INTO `avaliacao` (`idAVALIACAO`, `TRABALHO_ACADEMICO_AV1`, `APS1`, `TRABALHO_ACADEMICO_AV2`, `APS2`, `TRABALHO_ACADEMICO_AV3`, `idMATRICULA`, `idDISCIPLINA`) VALUES
	(100, 5, 3, 6, 1.5, NULL, 2020100, 1),
	(101, 5.4, 3, 6.3, 1.8, NULL, 2020100, 2),
	(102, 5.5, 3, 6, 1.3, NULL, 2020100, 3),
	(103, 5.7, 2.8, 5.4, 1.6, NULL, 2020100, 5),
	(104, 7, 2.8, 5, 2, NULL, 2020101, 1),
	(105, 6, 3, 6, 2, NULL, 2020101, 3),
	(106, 6.5, 2.7, 6, 2, NULL, 2020101, 4),
	(107, 7, 3, 6, 2, NULL, 2020101, 5),
	(108, 7, 2.6, 5.5, 2, NULL, 2020102, 8),
	(109, 6.5, 3, 6, 2, NULL, 2020102, 6),
	(110, 6.7, 2.8, 6, 1.8, NULL, 2020102, 7),
	(111, 7, 2.7, 7, 1.7, NULL, 2020102, 10);
/*!40000 ALTER TABLE `avaliacao` ENABLE KEYS */;

-- Copiando estrutura para tabela av1_db.curso
CREATE TABLE IF NOT EXISTS `curso` (
  `idCURSO` int NOT NULL,
  `NOME_CURSO` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idCURSO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela av1_db.curso: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `curso` DISABLE KEYS */;
INSERT INTO `curso` (`idCURSO`, `NOME_CURSO`) VALUES
	(1, 'ADS'),
	(2, 'CC'),
	(3, 'ENC');
/*!40000 ALTER TABLE `curso` ENABLE KEYS */;

-- Copiando estrutura para tabela av1_db.curso_has_aluno
CREATE TABLE IF NOT EXISTS `curso_has_aluno` (
  `CURSO_idCURSO` int NOT NULL,
  `ALUNO_MATRICULA` int NOT NULL,
  PRIMARY KEY (`CURSO_idCURSO`,`ALUNO_MATRICULA`),
  KEY `fk_CURSO_has_ALUNO_ALUNO1_idx` (`ALUNO_MATRICULA`),
  KEY `fk_CURSO_has_ALUNO_CURSO_idx` (`CURSO_idCURSO`),
  CONSTRAINT `fk_CURSO_has_ALUNO_ALUNO1` FOREIGN KEY (`ALUNO_MATRICULA`) REFERENCES `aluno` (`MATRICULA`),
  CONSTRAINT `fk_CURSO_has_ALUNO_CURSO` FOREIGN KEY (`CURSO_idCURSO`) REFERENCES `curso` (`idCURSO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela av1_db.curso_has_aluno: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `curso_has_aluno` DISABLE KEYS */;
INSERT INTO `curso_has_aluno` (`CURSO_idCURSO`, `ALUNO_MATRICULA`) VALUES
	(1, 2020100),
	(2, 2020101),
	(3, 2020102);
/*!40000 ALTER TABLE `curso_has_aluno` ENABLE KEYS */;

-- Copiando estrutura para tabela av1_db.disciplina
CREATE TABLE IF NOT EXISTS `disciplina` (
  `idDISCIPLINA` int NOT NULL,
  `DISCIPLINA_NOME` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idDISCIPLINA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela av1_db.disciplina: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `disciplina` DISABLE KEYS */;
INSERT INTO `disciplina` (`idDISCIPLINA`, `DISCIPLINA_NOME`) VALUES
	(1, 'Algoritmos I'),
	(2, 'Raciocínio lógico'),
	(3, 'Algoritmos I'),
	(4, 'Introdução a programação'),
	(5, 'Algoritmos II'),
	(6, 'Arquitetura de computadores'),
	(7, 'Lógica matemática'),
	(8, 'Programação estruturada'),
	(9, 'Estatística'),
	(10, 'Banco de dados I');
/*!40000 ALTER TABLE `disciplina` ENABLE KEYS */;

-- Copiando estrutura para tabela av1_db.usuario
CREATE TABLE IF NOT EXISTS `usuario` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) CHARACTER SET armscii8 COLLATE armscii8_bin NOT NULL DEFAULT '',
  `login` varchar(50) CHARACTER SET armscii8 COLLATE armscii8_bin NOT NULL DEFAULT '',
  `senha` varchar(50) CHARACTER SET armscii8 COLLATE armscii8_bin NOT NULL DEFAULT '',
  `tipo` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=armscii8 COLLATE=armscii8_bin;

-- Copiando dados para a tabela av1_db.usuario: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`id`, `nome`, `login`, `senha`, `tipo`) VALUES
	(1, 'cris', 'cris', '123', 0),
	(2, '2', '2', '2', 1);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
