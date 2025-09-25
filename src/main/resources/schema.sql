DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
	`userId` BIGINT NOT NULL AUTO_INCREMENT,
	`username` varchar(255) COLLATE utf8_bin NOT NULL,
	`password` varchar(255) COLLATE utf8_bin NOT NULL,
	`userinfo` varchar(255) COLLATE utf8_bin,
	PRIMARY KEY (`userId`),
	UNIQUE KEY `UK_userName` (`username`)
) ROW_FORMAT=DYNAMIC ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

DROP TABLE IF EXISTS `characters`;
CREATE TABLE `characters` (
	`characterId` BIGINT NOT NULL AUTO_INCREMENT,
	`characterName` varchar(255) COLLATE utf8_bin NOT NULL,
	`characterClass` varchar(255) COLLATE utf8_bin NOT NULL,
	`characterBio` varchar(255) COLLATE utf8_bin,
	`userId` BIGINT NOT NULL,
	PRIMARY KEY (`characterId`),
	UNIQUE KEY `UK_characterName` (`characterName`)
--    KEY FK_user_character (userId)
--    CONSTRAINT FK_user_character
--        FOREIGN KEY (userId) REFERENCES users (userId)
--        ON DELETE CASCADE
--        ON UPDATE CASCADE
) ROW_FORMAT=DYNAMIC ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- create index to improving querying character based on class
CREATE INDEX IDX_CHARACTER_CLASS ON characters(characterClass);

