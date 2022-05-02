insert into address (longitude, latitude) values
	(28, 45);
insert into address (longitude, latitude) values
	(70, 80);
insert into address (longitude, latitude) values
	(42, 100);
insert into address (longitude, latitude) values
	(10, 30);
insert into address (longitude, latitude) values
	(80, 10);
insert into address (longitude, latitude) values
	(0, 100);
insert into address (longitude, latitude) values
	(42, 100);
insert into address (longitude, latitude) values
	(10, 30);
insert into address (longitude, latitude) values
	(80, 10);
insert into address (longitude, latitude) values
	(0, 100);

INSERT INTO ROLE (name) VALUES ('ROLE_CLIENT');
INSERT INTO ROLE (name) VALUES ('ROLE_ADMIN');
INSERT INTO ROLE (name) VALUES ('ROLE_COTTAGE_OWNER');
INSERT INTO ROLE (name) VALUES ('ROLE_SHIP_OWNER');
INSERT INTO ROLE (name) VALUES ('ROLE_FISHINSTRUCTOR');

	--123
insert into users (type, id, deleted, email, loyalty_points, name, password,
	phonenumber,  surname, user_type, address_id, last_password_reset_date, role_id,
	penalty_number, enabled) values
	('CL', nextval('user_seq'), false, 'marko.markovi@example.com', 0, 'Marko', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',
	'+3815828', 'Markovic', 0,1, '2017-10-01 21:58:58.508-07', 1,
	0, true);
	--123
insert into users (type, id, deleted, email, loyalty_points, name, password,
	phonenumber, surname, user_type, address_id,last_password_reset_date, role_id, enabled) values
	('FI', nextval('user_seq'), false, 'milan.milanovi@example.com', 0, 'Milan', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',
	'+3817885', 'Milanovic', 0, 2, '2017-10-01 18:57:58.508-07',5, true);


insert into calendar (id) values (1);
insert into calendar (id) values (2);


insert into additional_services (name, offer_id, reservation_id, sale_appointment_id) values ('wifi', '1', null, null);

insert into behavior_rule (text, offer_id) values ('Not allowed smoking', 1);

insert into cancel_condition (days, precent, offer_id) values (5, 20, 1);

insert into cottage (id, deleted, description, name, address_id, calendar_id, bed_quantity, room_quantity, owner)
    values (nextval('offer_seq'), false, 'Najbolja zabava', 'B myName', 3, 1, 4, 2, 1);

insert into cottage (id, deleted, description, name, address_id, calendar_id, bed_quantity, room_quantity, owner)
    values (nextval('offer_seq'), false, 'Najbolja zabava 2', 'Z Next name', 4, 1, 4, 2, 1);

insert into cottage (id, deleted, description, name, address_id, calendar_id, bed_quantity, room_quantity, owner)
    values (nextval('offer_seq'), false, 'Najbolja zabava 3' , 'A Neverovatno ime', 5, 1, 4, 2, 1);

insert into cottage (id, deleted, description, name, address_id, calendar_id, bed_quantity, room_quantity, owner)
    values (nextval('offer_seq'), false, 'Najbolja zabava 4', 'N Last name', 6, 1, 4, 2, 1);

insert into ship (id, deleted, description, name, address_id, calendar_id, capacity, engine_designation, engine_power,
 length, max_speed, navigation_equipment, type, owner)
    values (nextval('offer_seq'), false, 'Najbolja zabava brod', 'A Neverovatno ime', 7, 1, 4,
    'engine destignation', 2222, 455, 122, 1, 'tipppp', 1);


insert into ship (id, deleted, description, name, address_id, calendar_id, capacity, engine_designation, engine_power,
 length, max_speed, navigation_equipment, type, owner)
    values (nextval('offer_seq'), false, 'Najbolja zabava brod', 'A Neverovatno ime', 8, 1, 4,
    'engine destignation', 2222, 455, 122, 1, 'tipppp', 1);


insert into ship (id, deleted, description, name, address_id, calendar_id, capacity, engine_designation, engine_power,
 length, max_speed, navigation_equipment, type, owner)
    values (nextval('offer_seq'), false, 'Najbolja zabava brod', 'A Neverovatno ime', 9, 1, 4,
    'engine destignation', 2222, 455, 122, 1, 'tipppp', 1);


insert into ship (id, deleted, description, name, address_id, calendar_id, capacity, engine_designation, engine_power,
 length, max_speed, navigation_equipment, type, owner)
    values (nextval('offer_seq'), false, 'Najbolja zabava brod', 'A Neverovatno ime', 10, 1, 4,
    'engine destignation', 2222, 455, 122, 1, 'tipppp', 1);

insert into image (path, offer_id) values ('img/cottage/4/238068376.jpg', 1);
insert into image (path, offer_id) values ('img/cottage/3/132308681.jpg', 2);
insert into image (path, offer_id) values ('img/cottage/2/169294777.jpg', 3);
insert into image (path, offer_id) values ('img/cottage/1/331714665.jpg', 4);
insert into image (path, offer_id) values ('img/ship/4/41.jpg', 5);
insert into image (path, offer_id) values ('img/ship/3/31.jpg', 6);
insert into image (path, offer_id) values ('img/ship/2/21.jpg', 7);
insert into image (path, offer_id) values ('img/ship/1/11.jpg', 8);

insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id) values
    (false, '28.01.2022', 0, 4000, false, false, '24.01.2022.', 1, 1);

insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id) values
    (true, '17.02.2022', 0, 50000, false, false, '14.02.2022.', 1, 2);

insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id) values
    (false, '17.01.2022', 0, 100, true, false, '11.01.2022.', 1, 3);

insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id) values
    (false, '14.04.2022', 0, 42542, false, true, '12.04.2022.', 1, 4);


insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id) values
    (false, '28.01.2022', 1, 4000, false, false, '24.01.2022.', 1, 5);

insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id) values
    (true, '17.02.2022', 1, 50000, false, false, '14.02.2022.', 1, 6);

insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id) values
    (false, '17.01.2022', 1, 100, true, false, '11.01.2022.', 1, 7);

insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id) values
    (false, '14.04.2022', 1, 42542, false, true, '12.04.2022.', 1, 8);