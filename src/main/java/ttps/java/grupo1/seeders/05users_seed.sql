SET FOREIGN_KEY_CHECKS=0;
DELETE FROM `user`;

INSERT INTO `user` (`id`, `email`, `name`, `password`, `username`)
VALUES (1, 'santiago@san.com', 'santiago', '$2a$10$lSBLlUNdx3YenSPeYYlM2.sjVShoHs..aiWCtXxAgh1PHLJ5EYOGi', 'santiago');
INSERT INTO `user` (`id`, `email`, `name`, `password`, `username`)
VALUES (2, 'santiago@gmail.com', 'Santi', '$2a$10$pNEpDz6K59qIqo2gmBprZOE3nFNUwzX.4x1eKOt4KPsZVyyTnqbm.', 'santiago2');
INSERT INTO `user` (`id`, `email`, `name`, `password`, `username`)
VALUES (3, 'laura@gmail.com', 'laura', '$2a$10$x5dsLNSz7faF7CAXbKWH5eWtK9pTB6BuwXqolMngJEGs8nQpGT6ye', 'laura');
INSERT INTO `user` (`id`, `email`, `name`, `password`, `username`)
VALUES (4, 'jorge@gmail.com', 'jorge', '$2a$10$J.373z/XZn.X/QIsDH4pduhU.Ep0n.KuXcwCgKhLiC3atPpq/priS', 'jorge'),
       (5, 'sun@gmail.com', 'sun', '$2a$10$WdnON2s8pkCLwrxSOCvg..cUGdzmdKXm.d7O2gd337vnHavHzxUIe', 'sun'),
       (6, 'cath@gmail.com', 'unixcat', '$2a$10$f/ECZbn8y6ZeQca/.k6yiuiqSJqqFmE30Fr0WOQRMFsJ4XXFROESy', 'cath');

SET FOREIGN_KEY_CHECKS=1;