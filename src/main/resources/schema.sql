   create table flights
   (
     id bigint not null,
     arrival_airport varchar(255),
     arrival_date timestamp,
     business_price bigint not null,
     departure_airport varchar(255),
     departure_date timestamp,
     initial_price bigint not null,
     is_deleted boolean not null,
     luggage_price bigint not null,
     place_priority_price bigint not null,
     plane_id bigint,
     primary key (id)
   );
   create table planes (id bigint not null, business_places_number integer not null, econom_places_number integer not null, is_deleted boolean, model varchar(255), primary key (id));
   create table tickets (id bigint not null, has_luggage boolean not null, is_business boolean not null, is_deleted boolean not null, place_priority boolean not null, price bigint not null, purchase_date timestamp, flight_id bigint, user_id bigint, primary key (id));

   create table user_role
   (
     user_id bigint not null,
     roles varchar(255)
   );

   create table users
   (id bigint not null,
   active boolean not null,
   birthday timestamp,
   document_info varchar(255),
   first_name varchar(255),
   last_name varchar(255),
   password varchar(255),
   username varchar(255),
   primary key (id)
   );
   