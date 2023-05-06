CREATE TABLE `tarefas` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `data_conclusao` datetime(6) DEFAULT NULL,
    `data_criacao` datetime(6) NOT NULL,
    `descricao` varchar(255) NOT NULL,
    `status` varchar(50) NOT NULL,
    `titulo` varchar(50) NOT NULL,
    PRIMARY KEY (`id`)
)