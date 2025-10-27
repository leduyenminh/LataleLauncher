DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
	`user_id` BIGINT NOT NULL AUTO_INCREMENT,
	`username` varchar(255) COLLATE utf8_bin UNIQUE NOT NULL,
	`email` varchar(255) COLLATE utf8_bin UNIQUE NOT NULL,
	`password` varchar(255) COLLATE utf8_bin NOT NULL,
	`userinfo` varchar(255) COLLATE utf8_bin,
	`role` varchar(255) COLLATE utf8_bin NOT NULL,
	PRIMARY KEY (`user_id`),
	UNIQUE KEY `UK_userName` (`username`)
) ROW_FORMAT=DYNAMIC ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;