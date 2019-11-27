-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema railway_ticket_booking
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `railway_ticket_booking` ;

-- -----------------------------------------------------
-- Schema railway_ticket_booking
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `railway_ticket_booking` DEFAULT CHARACTER SET utf8mb4 ;
USE `railway_ticket_booking` ;

-- -----------------------------------------------------
-- Table `railway_ticket_booking`.`user_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `railway_ticket_booking`.`user_type` ;

CREATE TABLE IF NOT EXISTS `railway_ticket_booking`.`user_type` (
  `id` BIGINT(10) NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `railway_ticket_booking`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `railway_ticket_booking`.`user` ;

CREATE TABLE IF NOT EXISTS `railway_ticket_booking`.`user` (
  `id` BIGINT(10) NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(90) NULL,
  `last_name` VARCHAR(90) NULL,
  `email` VARCHAR(80) NULL,
  `password` VARCHAR(80) NULL,
  `user_type_id` BIGINT(10) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_user_type1_idx` (`user_type_id` ASC),
  CONSTRAINT `fk_user_user_type1`
    FOREIGN KEY (`user_type_id`)
    REFERENCES `railway_ticket_booking`.`user_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `railway_ticket_booking`.`station`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `railway_ticket_booking`.`station` ;

CREATE TABLE IF NOT EXISTS `railway_ticket_booking`.`station` (
  `id` BIGINT(10) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(60) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `railway_ticket_booking`.`route`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `railway_ticket_booking`.`route` ;

CREATE TABLE IF NOT EXISTS `railway_ticket_booking`.`route` (
  `id` BIGINT(10) NOT NULL AUTO_INCREMENT,
  `departure_station_id` BIGINT(10) NOT NULL,
  `arrival_station_id` BIGINT(10) NOT NULL,
  `departure_date` DATE NULL,
  `arrival_date` DATE NULL,
  `departure_time` TIME NULL,
  `arrival_time` TIME NULL,
  `route_length_factor` DOUBLE NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_route_station1_idx` (`departure_station_id` ASC),
  INDEX `fk_route_station2_idx` (`arrival_station_id` ASC),
  CONSTRAINT `fk_route_station1`
    FOREIGN KEY (`departure_station_id`)
    REFERENCES `railway_ticket_booking`.`station` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_route_station2`
    FOREIGN KEY (`arrival_station_id`)
    REFERENCES `railway_ticket_booking`.`station` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `railway_ticket_booking`.`train_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `railway_ticket_booking`.`train_type` ;

CREATE TABLE IF NOT EXISTS `railway_ticket_booking`.`train_type` (
  `id` BIGINT(10) NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(60) NOT NULL,
  `seat_price` DECIMAL(10,2) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `railway_ticket_booking`.`train`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `railway_ticket_booking`.`train` ;

CREATE TABLE IF NOT EXISTS `railway_ticket_booking`.`train` (
  `id` BIGINT(10) NOT NULL AUTO_INCREMENT,
  `train_type_id` BIGINT(10) NOT NULL,
  `route_id` BIGINT(10) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_train_train_type1_idx` (`train_type_id` ASC),
  INDEX `fk_train_route1_idx` (`route_id` ASC),
  CONSTRAINT `fk_train_train_type1`
    FOREIGN KEY (`train_type_id`)
    REFERENCES `railway_ticket_booking`.`train_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_train_route1`
    FOREIGN KEY (`route_id`)
    REFERENCES `railway_ticket_booking`.`route` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `railway_ticket_booking`.`route_station`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `railway_ticket_booking`.`route_station` ;

CREATE TABLE IF NOT EXISTS `railway_ticket_booking`.`route_station` (
  `id` BIGINT(10) NOT NULL AUTO_INCREMENT,
  `route_id` BIGINT(10) NOT NULL,
  `station_id` BIGINT(10) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_route_station_route1_idx` (`route_id` ASC),
  INDEX `fk_route_station_station1_idx` (`station_id` ASC),
  CONSTRAINT `fk_route_station_route1`
    FOREIGN KEY (`route_id`)
    REFERENCES `railway_ticket_booking`.`route` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_route_station_station1`
    FOREIGN KEY (`station_id`)
    REFERENCES `railway_ticket_booking`.`station` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `railway_ticket_booking`.`invoice`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `railway_ticket_booking`.`invoice` ;

CREATE TABLE IF NOT EXISTS `railway_ticket_booking`.`invoice` (
  `id` BIGINT(10) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(10) NOT NULL,
  `train_id` BIGINT(10) NOT NULL,
  `seats_amount` INT NOT NULL,
  `price` DECIMAL(10,2) NULL,
  `date_time` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_payment_invoice_user1_idx` (`user_id` ASC),
  INDEX `fk_payment_invoice_train1_idx` (`train_id` ASC),
  CONSTRAINT `fk_payment_invoice_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `railway_ticket_booking`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_payment_invoice_train1`
    FOREIGN KEY (`train_id`)
    REFERENCES `railway_ticket_booking`.`train` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


-- -----------------------------------------------------
-- data insertion commands
-- -----------------------------------------------------

insert into station(name) values('Киев');
insert into station(name) values('Фастов');
insert into station(name) values('Житомир');
insert into station(name) values('Коростень');
insert into station(name) values('Олевск');
insert into station(name) values('Остки');
insert into station(name) values('Клесов');
insert into station(name) values('Сарны');

-- second route stations
insert into station(name) values('Ровно');
insert into station(name) values('Здолбунов');
insert into station(name) values('Острог');
insert into station(name) values('Полонное');
insert into station(name) values('Мирополь');
insert into station(name) values('Фастов 1');

-- third route stations
insert into station(name) values('Львов');
insert into station(name) values('Тернополь');
insert into station(name) values('Хмельницкий');
insert into station(name) values('Винница');

-- fourth route stations
insert into station(name) values('Дубно');
insert into station(name) values('Подзамче');


-- fifth route stations
insert into station(name) values('Подольск');
insert into station(name) values('Одесса');
insert into station(name) values('Подволочиск');
insert into station(name) values('Волочиск');

-- seventh route stations
insert into station(name) values('Харьков');
insert into station(name) values('Сумы');
insert into station(name) values('Ворожба');
insert into station(name) values('Путивль');
insert into station(name) values('Конотоп');
insert into station(name) values('Нежин');
insert into station(name) values('Черновцы');
insert into station(name) values('Ивано-Франковск');

-- ROUTE
insert into route(departure_station_id, arrival_station_id, departure_date, arrival_date, departure_time, arrival_time, route_length_factor)
values(1, 8, '2019.10.26', '2019.10.26', '13:36:00', '21:46:00', 0.8);
insert into route(departure_station_id, arrival_station_id, departure_date, arrival_date, departure_time, arrival_time, route_length_factor)
values(9, 1, '2019.10.31', '2019.11.01', '21:15:00', '05:58:00', 0.7);
insert into route(departure_station_id, arrival_station_id, departure_date, arrival_date, departure_time, arrival_time, route_length_factor)
values(15, 1,'2019.10.31', '2019.11.01', '17:40:00', '03:38:00', 0.6);
insert into route(departure_station_id, arrival_station_id, departure_date, arrival_date, departure_time, arrival_time, route_length_factor)
values(9, 15,'2019.10.31', '2019.10.31', '06:45:00', '09:35:00', 0.5);
insert into route(departure_station_id, arrival_station_id, departure_date, arrival_date, departure_time, arrival_time, route_length_factor)
values(1, 22,'2019.10.31', '2019.11.01', '21:15:00', '05:58:00', 0.8);
insert into route(departure_station_id, arrival_station_id, departure_date, arrival_date, departure_time, arrival_time, route_length_factor)
values(16, 1,'2019.10.31', '2019.11.01', '20:15:00', '04:58:00', 0.5);
insert into route(departure_station_id, arrival_station_id, departure_date, arrival_date, departure_time, arrival_time, route_length_factor)
values(25, 1,'2019.10.31', '2019.11.01', '19:15:00', '07:58:00', 0.6);
insert into route(departure_station_id, arrival_station_id, departure_date, arrival_date, departure_time, arrival_time, route_length_factor)
values(31, 1,'2019.10.31', '2019.11.01', '22:15:00', '09:58:00', 0.9);



insert into route_station(route_id, station_id) values(1, 1);
insert into route_station(route_id, station_id) values(1, 2);
insert into route_station(route_id, station_id) values(1, 3);
insert into route_station(route_id, station_id) values(1, 4);
insert into route_station(route_id, station_id) values(1, 5);
insert into route_station(route_id, station_id) values(1, 6);
insert into route_station(route_id, station_id) values(1, 7);
insert into route_station(route_id, station_id) values(1, 8);

insert into route_station(route_id, station_id) values(2, 9);
insert into route_station(route_id, station_id) values(2, 10);
insert into route_station(route_id, station_id) values(2, 11);
insert into route_station(route_id, station_id) values(2, 12);
insert into route_station(route_id, station_id) values(2, 13);
insert into route_station(route_id, station_id) values(2, 14);
insert into route_station(route_id, station_id) values(2, 1);

insert into route_station(route_id, station_id) values(3, 15);
insert into route_station(route_id, station_id) values(3, 16);
insert into route_station(route_id, station_id) values(3, 17);
insert into route_station(route_id, station_id) values(3, 18);
insert into route_station(route_id, station_id) values(3, 14);
insert into route_station(route_id, station_id) values(3, 1);

insert into route_station(route_id, station_id) values(4, 19);
insert into route_station(route_id, station_id) values(4, 10);
insert into route_station(route_id, station_id) values(4, 20);
insert into route_station(route_id, station_id) values(4, 21);
insert into route_station(route_id, station_id) values(4, 22);

insert into route_station(route_id, station_id) values(5, 1);
insert into route_station(route_id, station_id) values(5, 18);
insert into route_station(route_id, station_id) values(5, 21);
insert into route_station(route_id, station_id) values(5, 22);

insert into route_station(route_id, station_id) values(6, 16);
insert into route_station(route_id, station_id) values(6, 23);
insert into route_station(route_id, station_id) values(6, 24);
insert into route_station(route_id, station_id) values(6, 17);
insert into route_station(route_id, station_id) values(6, 18);
insert into route_station(route_id, station_id) values(6, 14);
insert into route_station(route_id, station_id) values(6, 1);

insert into route_station(route_id, station_id) values(7, 25);
insert into route_station(route_id, station_id) values(7, 26);
insert into route_station(route_id, station_id) values(7, 27);
insert into route_station(route_id, station_id) values(7, 28);
insert into route_station(route_id, station_id) values(7, 29);
insert into route_station(route_id, station_id) values(7, 30);
insert into route_station(route_id, station_id) values(7, 1);

insert into route_station(route_id, station_id) values(8, 31);
insert into route_station(route_id, station_id) values(8, 32);
insert into route_station(route_id, station_id) values(8, 15);
insert into route_station(route_id, station_id) values(8, 1);


insert into train_type(type, seat_price) values('Люкс', 100.00);
insert into train_type(type, seat_price) values('Среднего класса', 50.00);
insert into train_type(type, seat_price) values('Эконом', 30.00);
insert into train_type(type, seat_price) values('Электричка', 15.00);


insert into user_type(type) values('guest');
insert into user_type(type) values('administrator');
insert into user_type(type) values('passenger');


insert into user(first_name, last_name, email, password, user_type_id) values('Василий', 'Админский', 'vas.admin@gmail.com', '89ed6c642bf0d24f3f3f4bd1324c5e8a29d8738875f414b50ae087f48fb3a696', 2);
insert into user(first_name, last_name, email, password, user_type_id) values('Геннадий', 'Пасажирский', 'gen.omel@gmail.com', '30b2989c477c6ccbdd4491479c62dce4ce4b9b2f3458b654a7bc02978d1f8f53', 3);
insert into user(first_name, last_name, email, password, user_type_id) values('Валентина', 'Пиронова', 'val.pir@gmail.com', 'be0342d0b45a010fee5b58b8b697cd4a1230c404f9ef0259b956496730788ee4', 3);
insert into user(first_name, last_name, email, password, user_type_id) values('Офанасий', 'Билетский', 'ofan.bill@gmail.com', '82ce47b23e77e9c53e777172aacbccf2576c9141b8de2bbe4194bdd77035e09d', 3);
insert into user(first_name, last_name, email, password, user_type_id) values('Анастасия', 'Проводницкая', 'anas.prov@gmail.com', 'f5aa40c760e7fc530f189136d882bcedd47e7940e29bddeaa5c43dff9f16142e', 3);


insert into train(train_type_id, route_id) values(1, 5);
insert into train(train_type_id, route_id) values(2, 1);
insert into train(train_type_id, route_id) values(1, 2);
insert into train(train_type_id, route_id) values(4, 4);
insert into train(train_type_id, route_id) values(3, 3);
insert into train(train_type_id, route_id) values(1, 6);
insert into train(train_type_id, route_id) values(2, 7);
insert into train(train_type_id, route_id) values(3, 8);



