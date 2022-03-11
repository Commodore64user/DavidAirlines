drop table if exists passengers CASCADE; 
create table passengers (
id integer AUTO_INCREMENT, 
booked_flight integer not null,
email varchar(255), 
first_name varchar(255), 
last_name varchar(255), 
passport varchar(255), 
premium boolean not null, 
reservation varchar(255), 
primary key (id)
);