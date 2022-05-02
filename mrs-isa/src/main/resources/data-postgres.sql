insert into address (longitude, latitude) values
	(28, 45);
insert into address (longitude, latitude) values
	(70, 80);
insert into address (longitude, latitude) values
	(42, 100);

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
    values (nextval('offer_seq'), false, 'Najbolja zabava', 'myName', 3, 1, 4, 2, 1);

insert into image (path, offer_id) values ('img/cottage/4/238068376.jpg', 1);

insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id) values
    (false, '17.01.2022', 0, 4000, false, false, '10.01.2022.', 1, 1);