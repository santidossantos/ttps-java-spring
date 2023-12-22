DELETE
FROM `expense`;

INSERT INTO `expense` (`expense_type`, `id`, `amount`, `date`, `img`, `name`, `period`, `category_id`,
                       `expense_strategy_id`, `group_id`, `paying_user_id`)
VALUES ('Expense', 1, 14, '2023-12-20 00:00:00.000000', 'card_travel', 'lupita', NULL, 3, 1, 2, 3);
INSERT INTO `expense` (`expense_type`, `id`, `amount`, `date`, `img`, `name`, `period`, `category_id`,
                       `expense_strategy_id`, `group_id`, `paying_user_id`)
VALUES ('Expense', 2, 7000, '2023-12-09 00:00:00.000000', 'fastfood', 'antares', NULL, 1, 1, 2, 3);

SET FOREIGN_KEY_CHECKS = 1;