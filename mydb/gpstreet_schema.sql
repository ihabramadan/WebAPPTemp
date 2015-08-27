-- ----------------------------------------------------------------------------
-- MySQL Workbench Migration
-- Migrated Schemata: gpstreet_bkp
-- Source Schemata: gpstreet
-- Created: Thu Aug 27 17:12:35 2015
-- ----------------------------------------------------------------------------

SET FOREIGN_KEY_CHECKS = 0;;

-- ----------------------------------------------------------------------------
-- Schema gpstreet_bkp
-- ----------------------------------------------------------------------------
DROP SCHEMA IF EXISTS `gpstreet_bkp` ;
CREATE SCHEMA IF NOT EXISTS `gpstreet_bkp` ;

-- ----------------------------------------------------------------------------
-- Table gpstreet_bkp.gpst_group_has_page
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `gpstreet_bkp`.`gpst_group_has_page` (
  `groupid` INT(11) NOT NULL,
  `pageid` INT(11) NOT NULL,
  PRIMARY KEY (`groupid`, `pageid`),
  INDEX `pageIdFK_idx` (`pageid` ASC),
  CONSTRAINT `groupIdFK`
    FOREIGN KEY (`groupid`)
    REFERENCES `gpstreet_bkp`.`gpst_groups` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `pageIdFK`
    FOREIGN KEY (`pageid`)
    REFERENCES `gpstreet_bkp`.`gpst_pages` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- ----------------------------------------------------------------------------
-- Table gpstreet_bkp.gpst_groups
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `gpstreet_bkp`.`gpst_groups` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `groupname` VARCHAR(45) NOT NULL,
  `groupdesc` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 65
DEFAULT CHARACTER SET = utf8;

-- ----------------------------------------------------------------------------
-- Table gpstreet_bkp.gpst_pages
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `gpstreet_bkp`.`gpst_pages` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `description` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;

-- ----------------------------------------------------------------------------
-- Table gpstreet_bkp.gpst_locations
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `gpstreet_bkp`.`gpst_locations` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(1000) NULL DEFAULT NULL,
  `latitude` DOUBLE NULL DEFAULT NULL,
  `longitude` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- ----------------------------------------------------------------------------
-- Table gpstreet_bkp.gpst_state
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `gpstreet_bkp`.`gpst_state` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;

-- ----------------------------------------------------------------------------
-- Table gpstreet_bkp.gpst_tracker
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `gpstreet_bkp`.`gpst_tracker` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  `latitude` DOUBLE NOT NULL,
  `longitude` DOUBLE NOT NULL,
  `date` DATETIME NULL DEFAULT NULL,
  `state_id` INT(11) NOT NULL,
  `device_id` DOUBLE NOT NULL,
  `notes` VARCHAR(1000) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `user_id_fk_idx` (`user_id` ASC),
  INDEX `state_id_fk_idx` (`state_id` ASC),
  CONSTRAINT `state_id_fk`
    FOREIGN KEY (`state_id`)
    REFERENCES `gpstreet_bkp`.`gpst_state` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `user_id_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `gpstreet_bkp`.`gpst_users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;

-- ----------------------------------------------------------------------------
-- Table gpstreet_bkp.gpst_users
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `gpstreet_bkp`.`gpst_users` (
  `id` INT(10) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(25) NOT NULL,
  `password` VARCHAR(50) NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `phone` VARCHAR(45) NULL DEFAULT NULL,
  `firstname` VARCHAR(45) NULL DEFAULT NULL,
  `lastname` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 132
DEFAULT CHARACTER SET = utf8;

-- ----------------------------------------------------------------------------
-- Table gpstreet_bkp.gpst_user_has_groups
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `gpstreet_bkp`.`gpst_user_has_groups` (
  `User_userid` INT(11) NOT NULL,
  `Groups_groupid` INT(11) NOT NULL,
  PRIMARY KEY (`User_userid`, `Groups_groupid`),
  INDEX `fk_User_has_Groups_Groups1` (`Groups_groupid` ASC),
  INDEX `fk_User_has_Groups_User1` (`User_userid` ASC),
  CONSTRAINT `fk_User_has_Groups_Groups1`
    FOREIGN KEY (`Groups_groupid`)
    REFERENCES `gpstreet_bkp`.`gpst_groups` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_User_has_Groups_User1`
    FOREIGN KEY (`User_userid`)
    REFERENCES `gpstreet_bkp`.`gpst_users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

-- ----------------------------------------------------------------------------
-- View gpstreet_bkp.v_user_role
-- ----------------------------------------------------------------------------
USE `gpstreet_bkp`;
CREATE  OR REPLACE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_user_role` AS select `gpst_users`.`username` AS `username`,`gpst_users`.`password` AS `password`,`gpst_groups`.`groupname` AS `groupname` from ((`gpst_user_has_groups` join `gpst_users` on((`gpst_user_has_groups`.`User_userid` = `gpst_users`.`id`))) join `gpst_groups` on((`gpst_groups`.`id` = `gpst_user_has_groups`.`Groups_groupid`)));
SET FOREIGN_KEY_CHECKS = 1;;
