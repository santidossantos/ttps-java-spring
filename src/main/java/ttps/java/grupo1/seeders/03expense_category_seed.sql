SET FOREIGN_KEY_CHECKS=0;
DELETE FROM ttps.`category_expense`;
ALTER TABLE ttps.`category_expense` AUTO_INCREMENT = 0;

INSERT INTO ttps.`category_expense` (`icon`, `name`)
VALUES ('comida.png', 'Comida');

INSERT INTO ttps.`category_expense` (`icon`, `name`)
VALUES ('tecnologia.png', 'Tecnologia');

INSERT INTO ttps.`category_expense` (`icon`, `name`)
VALUES ('otro.png', 'Otro');

SET FOREIGN_KEY_CHECKS=1;