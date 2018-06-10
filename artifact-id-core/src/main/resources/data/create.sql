-- MySQL Script generated by MySQL Workbench
-- Sun Jun 10 14:51:59 2018
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema artifact_id
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `artifact_id` ;

-- -----------------------------------------------------
-- Schema artifact_id
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `artifact_id` DEFAULT CHARACTER SET utf8 ;
USE `artifact_id` ;

-- -----------------------------------------------------
-- Table `artifact_id`.`book`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `artifact_id`.`book` ;

CREATE TABLE IF NOT EXISTS `artifact_id`.`book` (
  `id` VARCHAR(255) NOT NULL,
  `entity_creation_timestamp` DATETIME(6) NOT NULL,
  `entity_version` BIGINT(20) NOT NULL,
  `last_updated_timestamp` DATETIME(6) NOT NULL,
  `is_old` BIT(1) NOT NULL,
  `pages` INT(11) NOT NULL,
  `published` DATETIME(6) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `artifact_id`.`rating`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `artifact_id`.`rating` ;

CREATE TABLE IF NOT EXISTS `artifact_id`.`rating` (
  `id` VARCHAR(255) NOT NULL,
  `entity_creation_timestamp` DATETIME(6) NOT NULL,
  `entity_version` BIGINT(20) NOT NULL,
  `last_updated_timestamp` DATETIME(6) NOT NULL,
  `rating_enum` INT(11) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `artifact_id`.`genre`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `artifact_id`.`genre` ;

CREATE TABLE IF NOT EXISTS `artifact_id`.`genre` (
  `id` VARCHAR(255) NOT NULL,
  `entity_creation_timestamp` DATETIME(6) NOT NULL,
  `entity_version` BIGINT(20) NOT NULL,
  `last_updated_timestamp` DATETIME(6) NOT NULL,
  `number` FLOAT NOT NULL,
  `rating_id` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK4egpykof7tvleyt2grmp8pfh8` (`rating_id` ASC),
  CONSTRAINT `FK4egpykof7tvleyt2grmp8pfh8`
    FOREIGN KEY (`rating_id`)
    REFERENCES `artifact_id`.`rating` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `artifact_id`.`book_genre`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `artifact_id`.`book_genre` ;

CREATE TABLE IF NOT EXISTS `artifact_id`.`book_genre` (
  `book_id` VARCHAR(255) NOT NULL,
  `genre_id` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`book_id`, `genre_id`),
  INDEX `FK8l6ops8exmjrlr89hmfow4mmo` (`genre_id` ASC),
  CONSTRAINT `FK52evq6pdc5ypanf41bij5u218`
    FOREIGN KEY (`book_id`)
    REFERENCES `artifact_id`.`book` (`id`),
  CONSTRAINT `FK8l6ops8exmjrlr89hmfow4mmo`
    FOREIGN KEY (`genre_id`)
    REFERENCES `artifact_id`.`genre` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `artifact_id`.`copy`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `artifact_id`.`copy` ;

CREATE TABLE IF NOT EXISTS `artifact_id`.`copy` (
  `id` VARCHAR(255) NOT NULL,
  `entity_creation_timestamp` DATETIME(6) NOT NULL,
  `entity_version` BIGINT(20) NOT NULL,
  `last_updated_timestamp` DATETIME(6) NOT NULL,
  `price` DOUBLE NOT NULL,
  `title` VARCHAR(255) NOT NULL,
  `book_id` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKof5k7k6c41i06j6fj3slgsmam` (`book_id` ASC),
  CONSTRAINT `FKof5k7k6c41i06j6fj3slgsmam`
    FOREIGN KEY (`book_id`)
    REFERENCES `artifact_id`.`book` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;