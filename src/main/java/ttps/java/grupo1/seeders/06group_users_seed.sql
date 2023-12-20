SET FOREIGN_KEY_CHECKS = 0;
DELETE
FROM `group_users`;

INSERT INTO `group_users` (`groups_id`, `users_id`)
VALUES (6, 1);
INSERT INTO `group_users` (`groups_id`, `users_id`)
VALUES (7, 1);
INSERT INTO `group_users` (`groups_id`, `users_id`)
VALUES (8, 2);
INSERT INTO `group_users` (`groups_id`, `users_id`)
VALUES (9, 3),
       (10, 3);

SET FOREIGN_KEY_CHECKS = 1;