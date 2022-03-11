drop table if exists schedule CASCADE;
create table schedule (
flight_num integer AUTO_INCREMENT, 
departure time, 
destination varchar(255), 
origin varchar(255), 
primary key (flight_num)
);