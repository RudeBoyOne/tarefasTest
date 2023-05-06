CREATE TABLE `usuarios` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `email` varchar(100) NOT NULL,
    `nome` varchar(60) NOT NULL,
    `senha` varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
)