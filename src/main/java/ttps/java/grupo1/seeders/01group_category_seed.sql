SET FOREIGN_KEY_CHECKS=0;
DELETE FROM `category_group`;
ALTER TABLE `category_group` AUTO_INCREMENT = 0;

INSERT INTO `category_group` (`icon`, `name`)
VALUES ('familia.png', 'Familia');

INSERT INTO `category_group` (`icon`, `name`)
VALUES ('avioncito.png', 'Viajes');

INSERT INTO `category_group` (`icon`, `name`)
VALUES ('fiesta.png', 'Salida');

INSERT INTO `category_group` (`icon`, `name`)
VALUES ('comodin.png', 'Otro');

SET FOREIGN_KEY_CHECKS=1;