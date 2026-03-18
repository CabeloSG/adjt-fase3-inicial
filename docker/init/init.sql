CREATE DATABASE IF NOT EXISTS authdb;
CREATE DATABASE IF NOT EXISTS pedidodb;
CREATE DATABASE IF NOT EXISTS pagamentodb;

USE pedidodb;

CREATE TABLE restaurantes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

CREATE TABLE produtos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    restaurante_id BIGINT,
    nome VARCHAR(255),
    preco DECIMAL(10,2),
    FOREIGN KEY (restaurante_id) REFERENCES restaurantes(id)
);

INSERT INTO restaurantes (nome) VALUES
('Pizzaria Napoli'),
('Burger House'),
('Sushi Express');

INSERT INTO produtos (restaurante_id, nome, preco) VALUES
(1, 'Pizza Calabresa', 60),
(1, 'Pizza Margherita', 55),
(1, 'Pizza Portuguesa', 65),

(2, 'Burger Clássico', 30),
(2, 'Burger Bacon', 35),
(2, 'Batata Frita', 20),

(3, 'Combo Sushi 12 peças', 50),
(3, 'Combo Sushi 24 peças', 90),
(3, 'Temaki Salmão', 28);