-- ----------------------------
-- Flyway deve ser usado somente em DEV ou TEST para evitar alterações indesejadas em PROD
-- Exclusões para inicio do processo
-- ----------------------------

DROP TABLE IF EXISTS poll_vote;
DROP TABLE IF EXISTS associado;
DROP TABLE IF EXISTS associate;
DROP TABLE IF EXISTS poll;

-- ----------------------------
-- Criação das tabelas 
-- ----------------------------

CREATE TABLE associado (
  id INT AUTO_INCREMENT PRIMARY KEY,
  cpf varchar(255),
  name varchar(255)
);

CREATE TABLE associate (
  id INT AUTO_INCREMENT PRIMARY KEY,
  `name` varchar(255),
  email varchar(255) not null,
	`password` varchar(255) not null
);

CREATE TABLE poll (
  id INT AUTO_INCREMENT PRIMARY KEY,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  expire_in DATETIME DEFAULT NULL,
  `result` varchar(255),
  `subject` varchar(255)
);

CREATE TABLE poll_vote (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `associado_id` int(11) NOT NULL,
  `poll_id` int(11) NOT NULL,
  `vote` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `associado_id`)
);

-- ----------------------------
-- Foreign Keys para a tabela poll_vote
-- ----------------------------
ALTER TABLE poll_vote ADD FOREIGN KEY (poll_id) REFERENCES poll(id);
ALTER TABLE poll_vote ADD FOREIGN KEY (associado_id) REFERENCES associado(id);

--  
-- INSERT INTO status (tipo) VALUES
--  ('ATIVA'),
--  ('CANCELADA');
-- 
-- INSERT INTO assinatura (status_id, criado_em, atualizado_em) VALUES
--  (1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
--  (2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
--  
--
-- INSERT INTO historico_assinatura (tipo, id_assinatura, criado_em) VALUES
--  ('Digital', 1, CURRENT_TIMESTAMP()),
--  ('Fisica', 1, CURRENT_TIMESTAMP());
-- 