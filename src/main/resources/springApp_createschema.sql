DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
	`userId` varchar(255) COLLATE utf8_bin NOT NULL,
	`userName` varchar(255) COLLATE utf8_bin NOT NULL,
	`password` varchar(255) COLLATE utf8_bin NOT NULL,
	`userinfo` varchar(255) COLLATE utf8_bin,
	PRIMARY KEY (`userId`)
) ROW_FORMAT=DYNAMIC ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

DROP TABLE IF EXISTS `characters`;
CREATE TABLE `characters` (
	`characterId` varchar(255) COLLATE utf8_bin NOT NULL,
	`characterName` varchar(255) COLLATE utf8_bin NOT NULL,
	`characterClass` varchar(255) COLLATE utf8_bin NOT NULL,
	PRIMARY KEY (`characterId`),
	CONSTRAINTS FK_characterUserId ()
) ROW_FORMAT=DYNAMIC ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
