insert into address (longitude, latitude) values (19, 44);
insert into address (longitude, latitude) values (19, 43);
insert into address (longitude, latitude) values (42, -30);

INSERT INTO ROLE (name) VALUES ('ROLE_CLIENT');
INSERT INTO ROLE (name) VALUES ('ROLE_ADMIN');
INSERT INTO ROLE (name) VALUES ('ROLE_COTTAGE_OWNER');
INSERT INTO ROLE (name) VALUES ('ROLE_SHIP_OWNER');
INSERT INTO ROLE (name) VALUES ('ROLE_FISHINSTRUCTOR');

--123
insert into users (type, id, deleted, email, loyalty_points, name, password,
	phonenumber,  surname, user_type, address_id, last_password_reset_date, role_id,
	penalty_number, enabled) values
	('CL', nextval('user_seq'), false, 'erdelji.violeta@gmail.com', 0, 'Marko', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',
	'+3815828', 'Markovic', 0,1, '2017-10-01 21:58:58.508-07', 1,
	1, true);

insert into users (type, id, deleted, email, loyalty_points, name, password,
	phonenumber, surname, user_type, address_id,last_password_reset_date, role_id, enabled) values
	('FI', nextval('user_seq'), false, 'milan.milanovi@example.com', 0, 'Milos', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',
	'+3817885', 'Milosevic', 0, 2, '2017-10-01 18:57:58.508-07',5, true);
insert into users(type, id, deleted, email, loyalty_points, name, password,
	phonenumber, surname, user_type, address_id,last_password_reset_date, role_id, enabled) values
	('CO', nextval('user_seq'), false, 'anastasijas@email.com', 0, 'Anastasija', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',
	'+38177590', 'Samcovic', 0, 3, '2017-10-01 18:57:58.508-07',3,true);


insert into cottage (id, deleted,description, name, address_id, bed_quantity, room_quantity, owner) values
	(nextval('offer_seq'), false, ' Ponuda obuhvata restoran, vrt, terasu i smeštaj sa pogledom na planinu.', 'Meda na Tari', 2, 4, 2, 3);

insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
   (false, '30.09.2022', 0, 4000, false, false, '20.09.2022.', 1, 1,false);

insert into period_availability (end_date, start_date, offer_id) values ('20.12.2022','01.01.2022',1);
insert into period_unavailability (end_date, start_date, offer_id) values ('31.12.2022','01.12.2022',1);

insert into pricelist (end_date, price, start_date, offer_id) values (null, 1500, '01.01.2021.', 1);



insert into address (longitude, latitude) values (19, 44);
insert into address (longitude, latitude) values (19, 43);
insert into address (longitude, latitude) values (42, -30);

insert into adventure (id, deleted, description, name, address_id, capacity, instructor_biography, owner) values
	(nextval('offer_seq'), false, 'Jezero je puno ribe.', 'Pecanje na Sotskom jezeru', 4, 3, 'Im the best instructor ever', 2);
	
insert into adventure (id, deleted, description, name, address_id, capacity, instructor_biography, owner) values
	(nextval('offer_seq'), false, 'Najbolje skijaske staze', 'Planinarenje Kopaonik', 5, 2, 'Im the best instructor ever', 2);

insert into adventure (id, deleted, description, name, address_id, capacity, instructor_biography, owner) values
	(nextval('offer_seq'), true, 'Nezaboravno iskustvo', 'Rafting Tarom', 6, 1, 'Im the best instructor ever', 2);

insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
   (false, '30.09.2022', 2, 10000, false, false, '20.09.2022.', 1, 2,false);

