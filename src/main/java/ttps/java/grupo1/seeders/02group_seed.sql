SET FOREIGN_KEY_CHECKS=0;
DELETE FROM `group`;
ALTER TABLE `group` AUTO_INCREMENT = 0;

INSERT INTO `group` (`name`, `hidden`, `category_id`)
VALUES ('Viaje a brasil', false, 2);

INSERT INTO `group` (`name`, `hidden`, `category_id`)
VALUES ('Viaje a china', false, 2);

INSERT INTO `group` (`name`, `hidden`, `category_id`)
VALUES ('Viaje a japon', false, 2);

INSERT INTO `group` (`name`, `hidden`, `category_id`)
VALUES ('Cena en pasillo', false, 3);

INSERT INTO `group` (`name`, `hidden`, `category_id`)
VALUES ('Supermercado vea', false, 1);

SET FOREIGN_KEY_CHECKS=1;