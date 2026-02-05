-- Database schema definitions for the launcher service.
--ALTER TABLE characters
--ADD CONSTRAINT FK_char_user
--FOREIGN KEY (fk_user_character)
--REFERENCES users (user_id)
--ON DELETE CASCADE
--ON UPDATE CASCADE;

-- DROP TABLE IF EXISTS `users`;
-- CREATE TABLE `users` (
-- 	`user_id` BIGINT NOT NULL AUTO_INCREMENT,
-- 	`username` varchar(255) COLLATE utf8_bin UNIQUE NOT NULL,
-- 	`email` varchar(255) COLLATE utf8_bin UNIQUE NOT NULL,
-- 	`password` varchar(255) COLLATE utf8_bin NOT NULL,
-- 	`userinfo` varchar(255) COLLATE utf8_bin,
-- 	PRIMARY KEY (`user_id`),
-- 	UNIQUE KEY `UK_userName` (`username`)
-- ) ROW_FORMAT=DYNAMIC ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

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

DROP TABLE IF EXISTS `characters`;
CREATE TABLE `characters` (
	`character_id` BIGINT NOT NULL AUTO_INCREMENT,
	`character_name` varchar(255) COLLATE utf8_bin UNIQUE NOT NULL,
	`character_class` varchar(255) COLLATE utf8_bin NOT NULL,
	`character_bio` varchar(255) COLLATE utf8_bin,
	`fk_user_character` BIGINT NOT NULL,
	PRIMARY KEY (`character_id`),
	UNIQUE KEY `UK_characterName` (`character_name`)
--    CONSTRAINT FK_user_character
--        FOREIGN KEY (user_id) REFERENCES users (user_id)
--        ON DELETE CASCADE
--        ON UPDATE CASCADE
) ROW_FORMAT=DYNAMIC ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

DROP TABLE IF EXISTS `server`;
CREATE TABLE `server` (
	`server_id` BIGINT NOT NULL AUTO_INCREMENT,
	`server_name` varchar(255) COLLATE utf8_bin UNIQUE NOT NULL,
	`server_status` varchar(255) COLLATE utf8_bin NOT NULL,
	`server_population` varchar(255) COLLATE utf8_bin,
	`server_ping` INT,
	PRIMARY KEY (`server_id`),
	UNIQUE KEY `UK_server_name` (`server_name`)
) ROW_FORMAT=DYNAMIC ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- create index to improving querying character based on class
CREATE INDEX IDX_CHARACTER_CLASS ON characters(character_class);
