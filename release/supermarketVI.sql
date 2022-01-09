drop database if exists `supermarketVI`;

CREATE DATABASE `supermarketVI`;

USE `supermarketVI`;

 CREATE TABLE `customer` (
 `id` INT not null auto_increment,
 `name` VARCHAR(20) not null,
 `phone` VARCHAR(20) not null,
 `address` VARCHAR(100) not null,
 `type` VARCHAR(20) not null,
 `info` VARCHAR(100) not null,
 PRIMARY KEY (`id`)
 )ENGINE=InnoDB auto_increment=1 DEFAULT CHARSET=utf8;

 create table `invoice` (
`id` INT not null auto_increment,
`customer_id` INT not null,
`time` DATETIME not null,
`total_price` DECIMAL(20,10) not null,
`status` VARCHAR(20) not null,
`gross_margin` DECIMAL(20,10) not null,
`user_id` INT not null,
PRIMARY KEY (`id`)
)ENGINE=InnoDB auto_increment=1 DEFAULT CHARSET=utf8;

create table `user` (
`id` INT not null auto_increment,
`authority` VARCHAR(10) not null,
`password` VARCHAR(20) not null,
`username` VARCHAR(20) not null,
`name` VARCHAR(20) not null,
`phone_number` VARCHAR(20) not null,
`gender` VARCHAR(10) not null,
PRIMARY KEY (`id`)
)ENGINE=InnoDB auto_increment=1 DEFAULT CHARSET=utf8;

create table `payment` (
`id` INT not null auto_increment,
`invoice_id` INT not null,
`amount` DECIMAL(20,10) not null,
`time` DATETIME not null,
PRIMARY KEY (`id`)
)ENGINE=InnoDB auto_increment=1 DEFAULT CHARSET=utf8;

create table `invoiceline` (
`id` INT not null auto_increment,
`invoice_id` INT not null,
`good_id` INT not null,
`warehouse_id` INT not null,
`unit_price` DECIMAL(20,10),
`amount` INT not null,
PRIMARY KEY (`id`)
)ENGINE=InnoDB auto_increment=1 DEFAULT CHARSET=utf8;

create table `warehouse` (
`id` INT not null auto_increment,
`name` VARCHAR(20) not null,
`address` VARCHAR(100) not null,
PRIMARY KEY (`id`)
)ENGINE=InnoDB auto_increment=1 DEFAULT CHARSET=utf8;

create table `good` (
`id` INT not null auto_increment,
`name` VARCHAR(20) not null,
`state` VARCHAR(100) not null,
`priceA` DECIMAL(20,10) not null,
`priceB` DECIMAL(20,10) not null,
`priceC` DECIMAL(20,10) not null,
`purchase_price` DECIMAL(20,10) not null,
`default_warehouse_id` INT not null,
`info` VARCHAR(100) not null,
`barcode` VARCHAR(100) not null,
PRIMARY KEY (`id`)
)ENGINE=InnoDB auto_increment=1 DEFAULT CHARSET=utf8;

create table `purchase` (
`id` INT not null auto_increment,
`good_id` INT not null,
`warehouse_id` INT not null,
`price` DECIMAL(20,10) not null,
`amount` INT not null,
`supplier` VARCHAR(20) not null,
`time` DATETIME not null,
PRIMARY KEY (`id`)
)ENGINE=InnoDB auto_increment=1 DEFAULT CHARSET=utf8;

create table `inventory` (
`id` INT not null auto_increment,
`warehouse_id` INT not null,
`good_id` INT not null,
`amount` INT not null,
`info` VARCHAR(100) not null,
PRIMARY KEY (`id`)
)ENGINE=InnoDB auto_increment=1 DEFAULT CHARSET=utf8;

create table `draft` (
`id` INT not null auto_increment,
`customer_id` INT not null,
`user_id` INT not null,
`status` VARCHAR(20) not null,
PRIMARY KEY (`id`)
)ENGINE=InnoDB auto_increment=1 DEFAULT CHARSET=utf8;


create table `draftline` (
`id` INT not null auto_increment,
`draft_id` INT not null,
`good_id` INT not null,
`amount` INT not null,
`warehouse_id` INT not null,
`unit_price` DECIMAL(20,10) not null,
PRIMARY KEY (`id`)
)ENGINE=InnoDB auto_increment=1 DEFAULT CHARSET=utf8;

create table `netcustomer` (
`id` INT not null auto_increment,
`customer_id` INT not null,
`username` VARCHAR(20) not null,
`password` VARCHAR(20) not null,
PRIMARY KEY (`id`)
)ENGINE=InnoDB auto_increment=1 DEFAULT CHARSET=utf8;

create table `shoppingcart` (
`id` INT not null auto_increment,
`netcustomer_id` INT not null,
PRIMARY KEY (`id`)
)ENGINE=InnoDB auto_increment=1 DEFAULT CHARSET=utf8;

create table `shoppingcartline` (
`id` INT not null auto_increment,
`shoppingcart_id` INT not null,
`good_id` INT not null,
`amount` INT not null,
`unit_price` DECIMAL(20,10) not null,
PRIMARY KEY (`id`)
)ENGINE=InnoDB auto_increment=1 DEFAULT CHARSET=utf8;


alter table invoice
add constraint fk_INVOICEcustomer_id
foreign key (customer_id)
references customer(id);

alter table invoice
add constraint fk_INVOICEuser_id
foreign key (user_id)
references user(id);

alter table payment
add constraint fk_PAYMENTinvoice_id
foreign key (invoice_id)
references invoice(id);

alter table draft
add constraint fk_DRAFTcustomer_id
foreign key (customer_id)
references customer(id);

alter table draft
add constraint fk_DRAFTuser_id
foreign key (user_id)
references user(id);

alter table invoiceline
add constraint fk_INVOICELINEinvoice_id
foreign key (invoice_id)
references invoice(id);

alter table invoiceline
add constraint fk_INVOICELINEgood_id
foreign key (good_id)
references good(id);

alter table invoiceline
add constraint fk_INVOICELINEwarehouse_id
foreign key (warehouse_id)
references warehouse(id);

alter table draftline
add constraint fk_DRAFTLINEdraft_id
foreign key (draft_id)
references draft(id);

alter table draftline
add constraint fk_DRAFTLINEwarehouse_id
foreign key (warehouse_id)
references warehouse(id);

alter table draftline
add constraint fk_DRAFTLINEgood_id
foreign key (good_id)
references good(id);

alter table good
add constraint fk_GOODwarehouse_id
foreign key (default_warehouse_id)
references warehouse(id);

alter table purchase
add constraint fk_PURCHASEwarehouse_id
foreign key (warehouse_id)
references warehouse(id);

alter table purchase
add constraint fk_PURCHASEgood_id
foreign key (good_id)
references good(id);

alter table inventory
add constraint fk_INVENTORYwarehouse_id
foreign key (warehouse_id)
references warehouse(id);

alter table inventory
add constraint fk_INVENTORYgood_id
foreign key (good_id)
references good(id);


alter table netcustomer
add constraint fk_NETCUSTOMERcustomer_id
foreign key (customer_id)
references customer(id);

alter table shoppingcart
add constraint fk_SHOPPINGCARTnetcustomer_id
foreign key (netcustomer_id)
references netcustomer(id);

alter table shoppingcartline
add constraint fk_SHOPPINGCARTLINEshoppingcart_id
foreign key (shoppingcart_id)
references shoppingcart(id);

alter table shoppingcartline
add constraint fk_SHOPPINGCARTLINEgood_id
foreign key (good_id)
references good(id);