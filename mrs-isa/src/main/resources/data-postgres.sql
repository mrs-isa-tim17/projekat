insert into address (longitude, latitude) values
	(28, 45);
insert into address (longitude, latitude) values
	(70, 80);

insert into users (type, id, deleted, email, loyalty_points, name, password,
	phonenumber,  surname, user_type, is_authenticated, penalty_number, address_id) values 
	('CL', nextval('user_seq'), false, 'amanda.amandacevic@example.com', 0, 'Amanda', '1',
	'+3252556', 'Amandacevic', 0, true, 3,1);
insert into users (type, id, deleted, email, loyalty_points, name, password,
	phonenumber,  surname, user_type, is_authenticated, penalty_number, address_id) values 
	('CL', nextval('user_seq'), false, 'jovan555665@example.com', 20, 'Jovan', '123',
	'+46546541', 'Mikovic', 1, true, 0,2);
insert into users (type, id, deleted, email, loyalty_points, name, password,
	phonenumber,  surname, user_type, is_authenticated, penalty_number, address_id) values 
	('CL', nextval('user_seq'), false, 'will.smith@example.com', 1, 'Will', 'thenbr1',
	'+5465454', 'Smith', 0, true, 1, 1);
insert into users (type, id, deleted, email, loyalty_points, name, password,
	phonenumber,  surname, user_type, is_authenticated, penalty_number, address_id) values 
	('CL', nextval('user_seq'), false, 'herkules@example.com', 0, 'Herkules', 'hohohoho',
	'+11296', 'Hades', 2, false, 0, 2);
	

INSERT INTO ROLE (name) VALUES ('ROLE_CLIENT');
INSERT INTO ROLE (name) VALUES ('ROLE_ADMIN');
INSERT INTO ROLE (name) VALUES ('ROLE_COTTAGE_OWNER');
INSERT INTO ROLE (name) VALUES ('ROLE_SHIP_OWNER');
INSERT INTO ROLE (name) VALUES ('ROLE_FISHINSTRUCTOR');

	--123
insert into users (type, id, deleted, email, loyalty_points, name, password,
	phonenumber,  surname, user_type, address_id, last_password_reset_date, role_id) values
	('CO', nextval('user_seq'), false, 'marko.markovi@example.com', 0, 'Marko', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',
	'+3815828', 'Markovic', 0,1, '2017-10-01 21:58:58.508-07', 3);
	--123
insert into users (type, id, deleted, email, loyalty_points, name, password,
	phonenumber, surname, user_type, address_id,last_password_reset_date, role_id) values
	('FI', nextval('user_seq'), false, 'milan.milanovi@example.com', 0, 'Milan', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',
	'+3817885', 'Milanovic', 0, 1, '2017-10-01 18:57:58.508-07',5);


insert into calendar (id) values (1);
insert into calendar (id) values (2);


insert into additional_services (name, offer_id, reservation_id, sale_appointment_id) values ('wifi', '1', null, null);

insert into behavior_rule (text, offer_id) values ('Not allowed smoking', 1);

insert into cancel_condition (days, precent, offer_id) values (5, 20, 1);

