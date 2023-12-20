SET FOREIGN_KEY_CHECKS=0;
DELETE FROM `group`;
ALTER TABLE `group` AUTO_INCREMENT = 0;

INSERT INTO `group` (`name`, `hidden`, `category_id`, `img`)
VALUES ('Viaje a brasil', false, 2, 'local_airport');

INSERT INTO `group` (`name`, `hidden`, `category_id`, `img`)
VALUES ('Viaje a china', false, 2, 'local_airport');

INSERT INTO `group` (`name`, `hidden`, `category_id`, `img`)
VALUES ('Viaje a japon', false, 2, 'local_airport');

INSERT INTO `group` (`name`, `hidden`, `category_id`, `img`)
VALUES ('Cena en pasillo', false, 3, 'local_bar');

INSERT INTO `group` (`name`, `hidden`, `category_id`, `img`)
VALUES ('Supermercado vea', false, 1, 'group');

SET FOREIGN_KEY_CHECKS=1;