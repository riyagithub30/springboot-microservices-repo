create table exchange_value
(
 id int not null,
 currency_from varchar(255) not null,
 currency_to varchar(255) not null,
 conversionMultiple int not null,
 port int not null,
 primary key(id)
 
);


