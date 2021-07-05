#INSERT INTO `role` (`id`, `role_name`)
#SELECT * FROM (SELECT NULL, 'USER_EMPLOYEE') AS tmp
#WHERE NOT EXISTS (SELECT role_name FROM role WHERE role_name = 'USER_EMPLOYEE') LIMIT 1;

# Drop all tables, use only for local storages
# Run program to create empty tables after dropping then runthe rest of this SQL

#DROP TABLE IF EXISTS warehouse;
#DROP TABLE IF EXISTS section;
#DROP TABLE IF EXISTS role;
#DROP TABLE IF EXISTS seller;
#DROP TABLE IF EXISTS user;
#DROP TABLE IF EXISTS product;
#DROP TABLE IF EXISTS supervisor;
#DROP TABLE IF EXISTS inbound_order;
#DROP TABLE IF EXISTS batch_item;

# BEFORE ALL
USE g3projdb;
SET sql_safe_updates = 0;

# US01 - Initial values

# Insert Warehouses

INSERT IGNORE INTO `warehouse` (`id`,`name`) VALUES (1,'Nordeste');
INSERT IGNORE INTO `warehouse` (`id`,`name`) VALUES (2,'SãoPaulo1');
INSERT IGNORE INTO `warehouse` (`id`,`name`) VALUES (3,'SãoPaulo2');
INSERT IGNORE INTO `warehouse` (`id`,`name`) VALUES (4,'SãoPaulo3');
INSERT IGNORE INTO `warehouse` (`id`,`name`) VALUES (5,'SãoPaulo4');
INSERT IGNORE INTO `warehouse` (`id`,`name`) VALUES (6,'MinasGerais1');

# Insert Sections

INSERT IGNORE INTO `section` (`id`,`max_temperature`,`min_temperature`,`section_code`,`warehouse_id`) VALUES (1,20,10,'1',1);
INSERT IGNORE INTO `section` (`id`,`max_temperature`,`min_temperature`,`section_code`,`warehouse_id`) VALUES (2,10,0,'2',1);
INSERT IGNORE INTO `section` (`id`,`max_temperature`,`min_temperature`,`section_code`,`warehouse_id`) VALUES (3,0,-10,'3',1);
INSERT IGNORE INTO `section` (`id`,`max_temperature`,`min_temperature`,`section_code`,`warehouse_id`) VALUES (4,20,10,'1',2);
INSERT IGNORE INTO `section` (`id`,`max_temperature`,`min_temperature`,`section_code`,`warehouse_id`) VALUES (5,10,0,'2',2);
INSERT IGNORE INTO `section` (`id`,`max_temperature`,`min_temperature`,`section_code`,`warehouse_id`) VALUES (6,0,-10,'3',2);
INSERT IGNORE INTO `section` (`id`,`max_temperature`,`min_temperature`,`section_code`,`warehouse_id`) VALUES (7,20,10,'1',3);
INSERT IGNORE INTO `section` (`id`,`max_temperature`,`min_temperature`,`section_code`,`warehouse_id`) VALUES (8,10,0,'2',3);
INSERT IGNORE INTO `section` (`id`,`max_temperature`,`min_temperature`,`section_code`,`warehouse_id`) VALUES (9,0,-10,'3',3);

# Insert Employees

ALTER TABLE employee ROW_FORMAT = DYNAMIC;

ALTER TABLE `employee`
    ADD UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
    ADD UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE;

INSERT IGNORE INTO `employee` (`id`,`email`,`name`,`password`,`username`,`warehouse_id`) VALUES (1,'sandro@warehouse.com','Sandro','954ght4h6','sandro',1);
INSERT IGNORE INTO `employee` (`id`,`email`,`name`,`password`,`username`,`warehouse_id`) VALUES (2,'paulo@warehouse.com','Paulo','39v4785n','paulo',2);
INSERT IGNORE INTO `employee` (`id`,`email`,`name`,`password`,`username`,`warehouse_id`) VALUES (3,'elisa@warehouse.com','Elisa','30b469u8','elisa',1);
INSERT IGNORE INTO `employee` (`id`,`email`,`name`,`password`,`username`,`warehouse_id`) VALUES (4,'maria@warehouse.com','Maria','2xein4988','maria',2);
INSERT IGNORE INTO `employee` (`id`,`email`,`name`,`password`,`username`,`warehouse_id`) VALUES (5,'ricardo@warehouse.com','Ricardo','3onf7rty','ricardo',2);
INSERT IGNORE INTO `employee` (`id`,`email`,`name`,`password`,`username`,`warehouse_id`) VALUES (6,'joao@warehouse.com','João','dfo3289gh5','joao',3);
INSERT IGNORE INTO `employee` (`id`,`email`,`name`,`password`,`username`,`warehouse_id`) VALUES (7,'sabrina@warehouse.com','Sabrina','1qas346g','sabrina',3);
INSERT IGNORE INTO `employee` (`id`,`email`,`name`,`password`,`username`,`warehouse_id`) VALUES (8,'bruno@warehouse.com','Bruno','954ght4h6','bruno',4);
INSERT IGNORE INTO `employee` (`id`,`email`,`name`,`password`,`username`,`warehouse_id`) VALUES (9,'roberta@warehouse.com','Roberta','108vn375y','roberta',4);
INSERT IGNORE INTO `employee` (`id`,`email`,`name`,`password`,`username`,`warehouse_id`) VALUES (10,'jose@warehouse.com','José','290478ty','jose',5);
INSERT IGNORE INTO `employee` (`id`,`email`,`name`,`password`,`username`,`warehouse_id`) VALUES (11,'isadora@warehouse.com','Isaadora','230947n85','isadora',4);

# Insert Roles

INSERT IGNORE INTO `role` (`id`,`role_name`) VALUES (1,'USER_EMPLOYEE');

# Insert Users

ALTER TABLE user ROW_FORMAT = DYNAMIC;

ALTER TABLE `user`
    ADD UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
    ADD UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE;

INSERT IGNORE INTO `user` (`id`,`email`,`name`,`password`,`username`) VALUES (1,'matheus@meli.com','Matheus','123','matheuzin2020');
INSERT IGNORE INTO `user` (`id`,`email`,`name`,`password`,`username`) VALUES (2,'alberto@meli.com','Alberto','158','alberto1515');
INSERT IGNORE INTO `user` (`id`,`email`,`name`,`password`,`username`) VALUES (3,'rayan@meli.com','Rayan','856','rayan2021');
INSERT IGNORE INTO `user` (`id`,`email`,`name`,`password`,`username`) VALUES (4,'vivian@meli.com','Vivian','784','vivian2021');
INSERT IGNORE INTO `user` (`id`,`email`,`name`,`password`,`username`) VALUES (5,'giovanni@meli.com','Giovanni','1324','giovanni2021');
INSERT IGNORE INTO `user` (`id`,`email`,`name`,`password`,`username`) VALUES (6,'paula@meli.com','Paula','346','paula09');
INSERT IGNORE INTO `user` (`id`,`email`,`name`,`password`,`username`) VALUES (7,'cleberson@meli.com','Cleberson','23453','cleber02');
INSERT IGNORE INTO `user` (`id`,`email`,`name`,`password`,`username`) VALUES (8,'cintya@meli.com','Cyntia','5789','cyntya23');

# Insert Sellers

INSERT IGNORE INTO `seller` (`id`) VALUES (1);
INSERT IGNORE INTO `seller` (`id`) VALUES (2);
INSERT IGNORE INTO `seller` (`id`) VALUES (3);
INSERT IGNORE INTO `seller` (`id`) VALUES (4);
INSERT IGNORE INTO `seller` (`id`) VALUES (5);
INSERT IGNORE INTO `seller` (`id`) VALUES (6);

# Insert Products

INSERT IGNORE INTO `product` (`id`,`description`,`name`,`price`,`rating`,`seller_id`) VALUES (1,'danoninho do dinossauro','Danone',20,10,1);
INSERT IGNORE INTO `product` (`id`,`description`,`name`,`price`,`rating`,`seller_id`) VALUES (2,'bolo caseiro','Bolo',12,9,2);
INSERT IGNORE INTO `product` (`id`,`description`,`name`,`price`,`rating`,`seller_id`) VALUES (3,'alface crespa','Alface',2,7,3);
INSERT IGNORE INTO `product` (`id`,`description`,`name`,`price`,`rating`,`seller_id`) VALUES (4,'repolho roxo','Repolho',5,8,2);
INSERT IGNORE INTO `product` (`id`,`description`,`name`,`price`,`rating`,`seller_id`) VALUES (5,'picanha do zé','Picanha',35,10,4);
INSERT IGNORE INTO `product` (`id`,`description`,`name`,`price`,`rating`,`seller_id`) VALUES (6,'gelo','Gelo',8,7,5);
INSERT IGNORE INTO `product` (`id`,`description`,`name`,`price`,`rating`,`seller_id`) VALUES (7,'batata tinha','Batata',5,7.5,6);
INSERT IGNORE INTO `product` (`id`,`description`,`name`,`price`,`rating`,`seller_id`) VALUES (8,'pipoca','Pipoca',3,7,6);

# Insert Supervisors

INSERT IGNORE INTO `supervisor` (`id`) VALUES (1);
INSERT IGNORE INTO `supervisor` (`id`) VALUES (2);
INSERT IGNORE INTO `supervisor` (`id`) VALUES (3);
INSERT IGNORE INTO `supervisor` (`id`) VALUES (4);
INSERT IGNORE INTO `supervisor` (`id`) VALUES (5);

# Insert Inbound Orders

INSERT IGNORE INTO `inbound_order` (`id`,`order_date`,`section_id`,`supervisor_id`) VALUES (1,'2021-07-02',1,1);
INSERT IGNORE INTO `inbound_order` (`id`,`order_date`,`section_id`,`supervisor_id`) VALUES (2,'2021-07-02',1,1);
INSERT IGNORE INTO `inbound_order` (`id`,`order_date`,`section_id`,`supervisor_id`) VALUES (3,'2021-07-02',2,1);
INSERT IGNORE INTO `inbound_order` (`id`,`order_date`,`section_id`,`supervisor_id`) VALUES (4,'2021-07-02',3,1);
INSERT IGNORE INTO `inbound_order` (`id`,`order_date`,`section_id`,`supervisor_id`) VALUES (5,'2021-07-02',1,1);

# Insert Batches

INSERT IGNORE INTO `batch` (`id`,`current_quantity`,`current_temperature`,`due_date`,`initial_quantity`,`manufacturing_date`,`manufacturing_time`,`max_temperature`,`min_temperature`,`product_id`) VALUES (1,12,12,'2021-08-01',2,'2012-05-01','2012-05-01 01:12:00.000000',15,10,1);
INSERT IGNORE INTO `batch` (`id`,`current_quantity`,`current_temperature`,`due_date`,`initial_quantity`,`manufacturing_date`,`manufacturing_time`,`max_temperature`,`min_temperature`,`product_id`) VALUES (2,5,7,'2021-08-15',1,'2012-05-25','2012-05-25 09:15:00.000000',11,5,2);
INSERT IGNORE INTO `batch` (`id`,`current_quantity`,`current_temperature`,`due_date`,`initial_quantity`,`manufacturing_date`,`manufacturing_time`,`max_temperature`,`min_temperature`,`product_id`) VALUES (3,15,3,'2021-08-12',8,'2012-06-23','2012-06-23 04:55:33.000000',0,-6,3);
INSERT IGNORE INTO `batch` (`id`,`current_quantity`,`current_temperature`,`due_date`,`initial_quantity`,`manufacturing_date`,`manufacturing_time`,`max_temperature`,`min_temperature`,`product_id`) VALUES (4,6,18,'2021-07-22',2,'2012-06-22','2012-06-22 05:33:00.000000',20,15,4);
INSERT IGNORE INTO `batch` (`id`,`current_quantity`,`current_temperature`,`due_date`,`initial_quantity`,`manufacturing_date`,`manufacturing_time`,`max_temperature`,`min_temperature`,`product_id`) VALUES (5,7,-2,'2021-09-01',1,'2012-06-07','2012-06-07 07:15:00.000000',0,-5,5);

# Create inboundo order 1 to batch stock 1

INSERT IGNORE INTO `g3projdb`.`inbound_order_batch_stock` (`inbound_order_id`, `batch_stock_id`) VALUES ('1', '1');

# US02 - Update values and conditions

# Update section codes

UPDATE `g3projdb`.`section` SET `section_code` = 'FS', `max_temperature` = 10, `min_temperature` = 4  WHERE (`section_code` = '1');
UPDATE `g3projdb`.`section` SET `section_code` = 'RS', `max_temperature` = 5, `min_temperature` = 0   WHERE (`section_code` = '2');
UPDATE `g3projdb`.`section` SET `section_code` = 'FF', `max_temperature` = -10, `min_temperature` = -25   WHERE (`section_code` = '3');

# Update batch_item to batch

ALTER TABLE `g3projdb`.`batch_item` RENAME TO  `g3projdb`.`batch` ;

# Inserir role de buyer
INSERT IGNORE INTO `role` (`id`,`role_name`) VALUES (2,'USER_BUYER');