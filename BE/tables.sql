CREATE SCHEMA IF NOT EXISTS `baseballlog` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `baseballlog` ;

-- -----------------------------------------------------
-- Table `baseballlog`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

ALTER TABLE user MODIFY profileImg varchar(400) NULL DEFAULT '../../assets/icon/default-user.png';

CREATE TABLE IF NOT EXISTS `user` (
  `id` VARCHAR(30) NOT NULL,
  `pw` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `nickname` VARCHAR(100) NOT NULL,
  `team` INT(2) NULL,
  `statusMessage` VARCHAR(200),
  `profileImg` varchar(400) NULL DEFAULT '../../assets/icon/default-user.png',
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `baseballlog`.`diary`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `diary` ;



CREATE TABLE IF NOT EXISTS `diary` (
  `userId` VARCHAR(30) NOT NULL,
  `diaryId` INT(5) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(200) NOT NULL,
  `content` VARCHAR(2000) NULL,
  `img` VARCHAR(3000) NULL,
  `gamedate` DATE NULL DEFAULT (current_date()),
  `gametime` TIME NULL,
  `isStadium` INT(1) NULL DEFAULT 0,
  `location` VARCHAR(400) NULL,
  `homeTeam` INT(2) NULL,
  `homeScore` INT(5) NULL,
  `awayTeam` INT(2) NULL,
  `awayScore` INT(5) NULL,
  `result` INT(1) NULL,
  PRIMARY KEY (`diaryId`),
  INDEX `fk_diary_user_idx` (`userId` ASC) VISIBLE,
  CONSTRAINT `fk_diary_user`
    FOREIGN KEY (`userId`)
    REFERENCES `user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `baseballlog`.`teams`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `teams` ;

CREATE TABLE IF NOT EXISTS `teams` (
  `teamId` INT(2) NOT NULL AUTO_INCREMENT,
  `teamName` VARCHAR(45) NOT NULL,
  `teamImg` VARCHAR(400) NOT NULL,
  PRIMARY KEY (`teamId`))
ENGINE = InnoDB;


Select * from diary;
Select * from user;
Select * from teams;
