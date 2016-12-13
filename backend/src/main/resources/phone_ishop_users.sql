
DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id bigint NOT NULL AUTO_INCREMENT,
  login varchar(45) NOT NULL,
  password varchar(45) NOT NULL,
  email varchar(45) NOT NULL,
  admin bit(1) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY login_UNIQUE (login),
  UNIQUE KEY email_UNIQUE (email)
) ;

INSERT INTO users (login,password, email, admin) VALUES ('admin','admin','admin@phone-ishop.ru',1);

DROP TABLE IF EXISTS products;

CREATE TABLE products (
  id bigint NOT NULL AUTO_INCREMENT,
  name varchar(45) NOT NULL,
  short_description varchar(200) NOT NULL,
  full_description varchar(1000) NOT NULL,
  price int NOT NULL,
  creation_timestamp bigint NOT NULL,
  brand varchar(45) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY name_UNIQUE (name)
 ) ;

 INSERT INTO products (name, short_description, full_description, price, brand, creation_timestamp) VALUES ('','','','','')
 
 CREATE TABLE buckets (
   user_id BIGINT NOT NULL,
   product_id BIGINT NOT NULL,
   INDEX userlink_idx (user_id ASC),
   INDEX productlink_idx (product_id ASC),
   CONSTRAINT userlink
     FOREIGN KEY (user_id)
     REFERENCES users (id)
     ON DELETE NO ACTION
     ON UPDATE NO ACTION,
   CONSTRAINT productlink
     FOREIGN KEY (product_id)
     REFERENCES products (id)
     ON DELETE NO ACTION
     ON UPDATE NO ACTION
     );

   CREATE TABLE `messages` (
   `id` bigint(20) NOT NULL AUTO_INCREMENT,
   `user_id` bigint(20) NOT NULL,
   `product_id` bigint(20) NOT NULL,
   `message` varchar(5000) NOT NULL,
   `creation_timestamp` bigint(20) NOT NULL,
   PRIMARY KEY (`id`),
   KEY `productlink_idx` (`product_id`),
   KEY `userId_FK_idx` (`user_id`),
   CONSTRAINT `productId_FK` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
   CONSTRAINT `userId_FK` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
 );





