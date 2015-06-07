-- ----------------------------------------------------------------------------
-- MySQL Workbench Migration
-- Migrated Schemata: gpstreetbkp
-- Source Schemata: gpstreet
-- Created: Thu Jun 04 16:54:46 2015
-- ----------------------------------------------------------------------------

SET FOREIGN_KEY_CHECKS = 0;;

-- ----------------------------------------------------------------------------
-- Schema gpstreetbkp
-- ----------------------------------------------------------------------------
DROP SCHEMA IF EXISTS `gpstreetbkp` ;
CREATE SCHEMA IF NOT EXISTS `gpstreetbkp` ;

-- ----------------------------------------------------------------------------
-- Table gpstreetbkp.gpst_group_has_page
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `gpstreetbkp`.`gpst_group_has_page` (
  `groupid` INT(11) NOT NULL,
  `pageid` INT(11) NOT NULL,
  PRIMARY KEY (`groupid`, `pageid`),
  INDEX `pageIdFK_idx` (`pageid` ASC),
  CONSTRAINT `groupIdFK`
    FOREIGN KEY (`groupid`)
    REFERENCES `gpstreetbkp`.`gpst_groups` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `pageIdFK`
    FOREIGN KEY (`pageid`)
    REFERENCES `gpstreetbkp`.`gpst_pages` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- ----------------------------------------------------------------------------
-- Table gpstreetbkp.gpst_groups
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `gpstreetbkp`.`gpst_groups` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `groupname` VARCHAR(45) NULL DEFAULT NULL,
  `groupdesc` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;

-- ----------------------------------------------------------------------------
-- Table gpstreetbkp.gpst_pages
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `gpstreetbkp`.`gpst_pages` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `description` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;

-- ----------------------------------------------------------------------------
-- Table gpstreetbkp.gpst_user_has_groups
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `gpstreetbkp`.`gpst_user_has_groups` (
  `User_userid` INT(11) NOT NULL,
  `Groups_groupid` INT(11) NOT NULL,
  PRIMARY KEY (`User_userid`, `Groups_groupid`),
  INDEX `fk_User_has_Groups_Groups1` (`Groups_groupid` ASC),
  INDEX `fk_User_has_Groups_User1` (`User_userid` ASC),
  CONSTRAINT `fk_User_has_Groups_Groups1`
    FOREIGN KEY (`Groups_groupid`)
    REFERENCES `gpstreetbkp`.`gpst_groups` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_User_has_Groups_User1`
    FOREIGN KEY (`User_userid`)
    REFERENCES `gpstreetbkp`.`gpst_users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

-- ----------------------------------------------------------------------------
-- Table gpstreetbkp.gpst_users
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `gpstreetbkp`.`gpst_users` (
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
AUTO_INCREMENT = 25
DEFAULT CHARACTER SET = utf8;

-- ----------------------------------------------------------------------------
-- View gpstreetbkp.v_user_role
-- ----------------------------------------------------------------------------
USE `gpstreetbkp`;
CREATE  OR REPLACE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `gpstreet`.`v_user_role` AS select `gpstreet`.`gpst_users`.`username` AS `username`,`gpstreet`.`gpst_users`.`password` AS `password`,`gpstreet`.`gpst_groups`.`groupname` AS `groupname` from ((`gpstreet`.`gpst_user_has_groups` join `gpstreet`.`gpst_users` on((`gpstreet`.`gpst_user_has_groups`.`User_userid` = `gpstreet`.`gpst_users`.`id`))) join `gpstreet`.`gpst_groups` on((`gpstreet`.`gpst_groups`.`id` = `gpstreet`.`gpst_user_has_groups`.`Groups_groupid`)));
SET FOREIGN_KEY_CHECKS = 1;;
