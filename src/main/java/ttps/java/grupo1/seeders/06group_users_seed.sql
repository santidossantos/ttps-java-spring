SET FOREIGN_KEY_CHECKS = 0;
DELETE
FROM `group_users`;

INSERT INTO `group_users` (`groups_id`, `users_id`)
VALUES (2, 3);
INSERT INTO `group_users` (`groups_id`, `users_id`)
VALUES (2, 1);
INSERT INTO `group_users` (`groups_id`, `users_id`)
VALUES (1, 3);
INSERT INTO `group_users` (`groups_id`, `users_id`)
VALUES (1, 4),
       (2, 2);

SET FOREIGN_KEY_CHECKS = 1;