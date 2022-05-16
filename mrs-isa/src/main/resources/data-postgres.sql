
insert into address (longitude, latitude) values (19, 44);
insert into address (longitude, latitude) values (19, 43);
insert into address (longitude, latitude) values (42, -30);
insert into address (longitude, latitude) values (10, 30);
insert into address (longitude, latitude) values (80, 10);
insert into address (longitude, latitude) values (0, 44);
insert into address (longitude, latitude) values (20, 55);
insert into address (longitude, latitude) values (10, 30);
insert into address (longitude, latitude) values (80, 10);
insert into address (longitude, latitude) values (0, -30);
insert into address (longitude, latitude) values (11, 70);
insert into address (longitude, latitude) values (10, 30);
insert into address (longitude, latitude) values (80, 10);
insert into address (longitude, latitude) values (0, -30);
insert into address (longitude, latitude) values (11, 70);

insert into address (longitude, latitude) values (11, 70);

insert into address (longitude, latitude) values (16, 16);


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
	'+3815828', 'Markovic', 0,1, to_timestamp('2017-10-01 21:58:58', 'yyyy-MM-DD HH:mm:SS'), 1,
	0, true);
	--123
insert into users (type, id, deleted, email, loyalty_points, name, password,
	phonenumber, surname, user_type, address_id,last_password_reset_date, role_id, enabled) values
	('FI', nextval('user_seq'), false, 'milan.milanovi@example.com', 0, 'Milan', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',
	'+3817885', 'Milanovic', 0, 2, to_timestamp('2017-10-01 18:57:58', 'yyyy-MM-DD HH:mm:SS'),5, true);
insert into users(type, id, deleted, email, loyalty_points, name, password,
	phonenumber, surname, user_type, address_id,last_password_reset_date, role_id, enabled) values
	('CO', nextval('user_seq'), false, 'anastasijas@email.com', 0, 'Anastasija', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',
	'+38177590', 'Samcovic', 0, 3, to_timestamp('2017-10-01 18:57:58', 'yyyy-MM-DD HH:mm:SS'),3,true);
insert into users(type, id, deleted, email, loyalty_points, name, password,
	phonenumber, surname, user_type, address_id,last_password_reset_date, role_id, enabled) values
	('SO', nextval('user_seq'), false, 'bogi@email.com', 0, 'Bogdan', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',
	'+3853252', 'Radojevic', 0, 4, to_timestamp('2017-10-01 18:57:58', 'yyyy-MM-DD HH:mm:SS'),4,true);

insert into users (type, id, deleted, email, loyalty_points, name, password,
	phonenumber, surname, user_type, address_id,last_password_reset_date, role_id, enabled) values
	('CO', nextval('user_seq'), false, 'm.m@example.com', 0, 'Milan', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',
	'+3817885', 'Milanovic', 0, 11, to_timestamp('2017-10-01 18:57:58.508-07', 'yyyy-MM-DD HH:mm:SS'),3, true);


insert into users (type, id, deleted, email, loyalty_points, name, password,
	phonenumber, surname, user_type, address_id,last_password_reset_date, role_id, enabled) values
	('SO', nextval('user_seq'), false, 'ship.owner@example.com', 0, 'Milan', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',
	'+3817885', 'Milanovic', 0, 11, to_timestamp('2017-10-01 18:57:58.508-07', 'yyyy-MM-DD HH:mm:SS'),4, true);

	
	
insert into users(type, id, deleted, email, loyalty_points, name, password,
	phonenumber, surname, user_type, address_id,last_password_reset_date, role_id, enabled, init_login_changed) values
	('AD', nextval('user_seq'), false, 'admin@email.com', 0, 'Žika', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',
	'+3853252', 'Žikić', 0, 17, to_timestamp('2017-10-01 18:57:58.508-07', 'yyyy-MM-DD HH:mm:SS'),2,true, true);

	
	
insert into cottage (id, deleted,description, name, address_id, bed_quantity, room_quantity, owner) values
	(nextval('offer_seq'), false, ' Ponuda obuhvata restoran, vrt, terasu i smeštaj sa pogledom na planinu.', 'Meda na Tari', 16, 4, 2, 3);

insert into adventure (id, deleted, description, name, address_id, capacity, instructor_biography, owner) values
	(nextval('offer_seq'), false, 'Best adventure ever', 'Fishing on the Lake', 2, 8, 'Im the best instructor ever', 2);
	
insert into additional_services (name, reservation_id, sale_appointment_id) values ('wifi', null, null);
insert into additional_services (name, reservation_id, sale_appointment_id) values ('kabanica', null, null);
insert into additional_services (name, reservation_id, sale_appointment_id) values ('baterijska lampa', null, null);
insert into additional_services (name, reservation_id, sale_appointment_id) values ('bazen', null, null);
insert into additional_services (name, reservation_id, sale_appointment_id) values ('parking', null, null);
insert into additional_services (name, reservation_id, sale_appointment_id) values ('kablovska TV', null, null);
insert into additional_services (name, reservation_id, sale_appointment_id) values ('WiFi', null, null);
insert into additional_services (name, reservation_id, sale_appointment_id) values ('društvene igre', null, null);
insert into additional_services (name, reservation_id, sale_appointment_id) values ('besplatne pešačke ture', null, null);
insert into additional_services (name, reservation_id, sale_appointment_id) values ('bicikli', null, null);

insert into additional_services_offer (offer_id, additional_service_id) values (1, 1);
insert into additional_services_offer (offer_id, additional_service_id) values (2, 2);

insert into behavior_rule (text) values ('zabranjeno pecanje');
insert into behavior_rule (text) values ('zabranjeno pusenje');
insert into behavior_rule (text) values ('dozvoljeno pecanje');
insert into behavior_rule (text) values ('dozvoljeno kupanje');
insert into behavior_rule (text) values ('zabranjeno kupanje');
insert into behavior_rule (text) values ('volimo ljubimce');

insert into behavior_rules_offer (offer_id, behavior_rule_id) values (2, 1);
insert into behavior_rules_offer (offer_id, behavior_rule_id) values (2, 2);
insert into behavior_rules_offer (offer_id, behavior_rule_id) values (1, 1);

insert into fishing_equipment (name, ship_id) values('štapovi', null);
insert into fishing_equipment (name, ship_id) values('udice', null);
insert into fishing_equipment (name, ship_id) values('mamac', null);
insert into fishing_equipment (name, ship_id) values('čamac', null);

insert into fishing_equipments_offer (adventure_id, fishing_equipment_id) values (2, 1);
insert into fishing_equipments_offer (adventure_id, fishing_equipment_id) values (2, 2);


insert into cancel_condition (days, precent) values (5, 15);
insert into cancel_condition (days, precent) values (10, 10);
insert into cancel_condition (days, precent) values (15, 0);
insert into cancel_condition (days, precent) values (20, 0);


insert into cancel_condition (days, precent) values (5, 3);
insert into cancel_condition (days, precent) values (10, 7);
insert into cancel_condition (days, precent) values (15, 8);
insert into cancel_condition (days, precent) values (20, 9);

insert into cancel_condition (days, precent) values (5, 13);
insert into cancel_condition (days, precent) values (10, 17);
insert into cancel_condition (days, precent) values (15, 18);
insert into cancel_condition (days, precent) values (20, 19);

insert into cancel_condition (days, precent) values (5, 5);
insert into cancel_condition (days, precent) values (10, 0);
insert into cancel_condition (days, precent) values (15, 0);
insert into cancel_condition (days, precent) values (20, 0);

insert into cancel_condition (days, precent) values (5, 0);
insert into cancel_condition (days, precent) values (10, 0);
insert into cancel_condition (days, precent) values (15, 0);
insert into cancel_condition (days, precent) values (20, 0);

insert into cancel_condition_offer (offer_id, cancel_condition_id) values (2, 1);
insert into cancel_condition_offer (offer_id, cancel_condition_id) values (2, 2);
insert into cancel_condition_offer (offer_id, cancel_condition_id) values (2, 3);
insert into cancel_condition_offer (offer_id, cancel_condition_id) values (2, 4);

insert into cancel_condition_offer (offer_id, cancel_condition_id) values (11, 5);
insert into cancel_condition_offer (offer_id, cancel_condition_id) values (11, 6);
insert into cancel_condition_offer (offer_id, cancel_condition_id) values (11, 7);
insert into cancel_condition_offer (offer_id, cancel_condition_id) values (11, 8);

insert into cancel_condition_offer (offer_id, cancel_condition_id) values (12, 9);
insert into cancel_condition_offer (offer_id, cancel_condition_id) values (12, 10);
insert into cancel_condition_offer (offer_id, cancel_condition_id) values (12, 11);
insert into cancel_condition_offer (offer_id, cancel_condition_id) values (12, 12);

insert into cancel_condition_offer (offer_id, cancel_condition_id) values (13, 13);
insert into cancel_condition_offer (offer_id, cancel_condition_id) values (13, 14);
insert into cancel_condition_offer (offer_id, cancel_condition_id) values (13, 15);
insert into cancel_condition_offer (offer_id, cancel_condition_id) values (13, 16);


insert into cancel_condition_offer (offer_id, cancel_condition_id) values (14, 17);
insert into cancel_condition_offer (offer_id, cancel_condition_id) values (14, 18);
insert into cancel_condition_offer (offer_id, cancel_condition_id) values (14, 19);
insert into cancel_condition_offer (offer_id, cancel_condition_id) values (14, 20);

insert into image (path, offer_id) values('img/adventure/Drina1.jpg', 2);
insert into image (path, offer_id) values('img/adventure/Drina2.jpg', 2);
insert into image (path, offer_id) values('img/adventure/Drina3.jpg', 2);

insert into experience_review (rate, status, text, client_id, offer_id, offer_type) values(5, 1, 'Prelepa tura, sve pohvale.', 1, 2, 2);

insert into pricelist (end_date, price, start_date, offer_id) values (null, 12000, to_date('29.04.2022.', 'DD.MM.yyyy'), 2);

insert into cottage (id, deleted, description, name, address_id, bed_quantity, room_quantity, owner)
    values (nextval('offer_seq'), false, 'Idealno mesto za odmor', 'Šumske priče Divčibare', 1, 4, 2, 3);

insert into cottage (id, deleted, description, name, address_id, bed_quantity, room_quantity, owner)
    values (nextval('offer_seq'), false, 'U objektu Kedar se služi kontinentalni doručak.', 'Kedar Goč', 4, 4, 2, 3);

insert into cottage (id, deleted, description, name, address_id, bed_quantity, room_quantity, owner)
    values (nextval('offer_seq'), false, 'Iz objekta se pruža pogled na vrt i planinu.' , 'Holiday Home Golija', 5, 4, 2, 3);

insert into cottage (id, deleted, description, name, address_id, bed_quantity, room_quantity, owner)
    values (nextval('offer_seq'), false, 'Ova mala farma okružena je planinskom rekom i tipičnim srpskim seoskim krajolikom.', 'Bogut Bogutovac', 6, 4, 2, 3);

insert into ship (id, deleted, description, name, address_id, capacity, engine_designation, engine_power,
 length, max_speed, navigation_equipment, type, owner)
    values (nextval('offer_seq'), false, 'Svakog gosta dočekaće piće dobrodošlice i snekovi.', 'Nimbus 3100', 7, 4,
    'Volvo 3.6 l', 165, 100, 15, 1, 'tipppp', 4);


insert into ship (id, deleted, description, name, address_id, capacity, engine_designation, engine_power,
 length, max_speed, navigation_equipment, type, owner)
    values (nextval('offer_seq'), false, 'Aluminijumski deplasmanski brod sa mahagoni enterijerom.', 'Dionis', 8, 7,
    'Motor dizel Timray 50', 2222, 9, 10, 1, 'tipppp', 4);


insert into ship (id, deleted, description, name, address_id, capacity, engine_designation, engine_power,
 length, max_speed, navigation_equipment, type, owner)
    values (nextval('offer_seq'), false, 'Brod za manje zabave.', 'Party', 9, 25,
    'engine destignation', 2222, 15, 10, 1, 'tipppp', 4);


insert into ship (id, deleted, description, name, address_id, capacity, engine_designation, engine_power,
 length, max_speed, navigation_equipment, type, owner)
    values (nextval('offer_seq'), false, 'Namenjen je turističkim obilascima duž Dunava.', 'Silver Star', 10, 200,
    'engine destignation', 2222, 50, 20, 1, 'tipppp', 4);

insert into adventure (id, deleted, description, name, address_id, capacity,
    instructor_biography, owner) values
	(nextval('offer_seq'), false, 'Best adventure ever', 'Pecanje na Dunavu', 12, 8,
	'Im the best instructor ever', 2);


insert into adventure (id, deleted, description, name, address_id, capacity,
    instructor_biography, owner) values
	(nextval('offer_seq'), false, 'Palić', 'Palić', 13, 10,
	'Humbalee humbalaa', 2);

insert into adventure (id, deleted, description, name, address_id, capacity,
    instructor_biography, owner) values
	(nextval('offer_seq'), false, 'Sava', 'Sava', 14, 2,
	'Oxfort oxfort oxy foxy roxfort', 2);

insert into adventure (id, deleted, description, name, address_id, capacity,
    instructor_biography, owner) values
	(nextval('offer_seq'), false, 'Comfy on the Tisa', 'Comfy on the Tisa', 15, 6,
	'Instruktor instruktor instruktor, najbolji na svetu', 2);

insert into pricelist (end_date, price, start_date, offer_id) values (null, 12000, to_date('29.04.2022.', 'DD.MM.yyyy'), 11);
insert into pricelist (end_date, price, start_date, offer_id) values (null, 12000, to_date('29.04.2022.', 'DD.MM.yyyy'), 12);
insert into pricelist (end_date, price, start_date, offer_id) values (null, 12000, to_date('29.04.2022.', 'DD.MM.yyyy'), 13);
insert into pricelist (end_date, price, start_date, offer_id) values (null, 12000, to_date('29.04.2022.', 'DD.MM.yyyy'), 14);


insert into image (path, offer_id) values ('img/cottage/meda/meda.jpg', 1);
insert into image (path, offer_id) values ('img/cottage/meda/meda1.jpg', 1);
insert into image (path, offer_id) values ('img/cottage/meda/meda2.jpg', 1);
--insert into image (path, offer_id) values ('img/cottage/3/132308681.jpg', 2);
insert into image (path, offer_id) values ('img/cottage/suma/suma.jpg', 3);
insert into image (path, offer_id) values ('img/cottage/suma/suma1.jpg', 3);
insert into image (path, offer_id) values ('img/cottage/kedar/kedar.jpg', 4);
insert into image (path, offer_id) values ('img/cottage/kedar/kedar1.jpg', 4);
insert into image (path, offer_id) values ('img/cottage/kedar/kedar2.jpg', 4);
insert into image (path, offer_id) values ('img/cottage/holiday/holiday.jpg', 5);
insert into image (path, offer_id) values ('img/cottage/holiday/holiday1.jpg', 5);
insert into image (path, offer_id) values ('img/cottage/bogut/bogut.jpg', 6);
insert into image (path, offer_id) values ('img/cottage/bogut/bogut1.jpg', 6);
insert into image (path, offer_id) values ('img/cottage/bogut/bogut2.jpg', 6);

insert into image (path, offer_id) values ('img/ship/nimbus/nimbus.jpg', 7);
insert into image (path, offer_id) values ('img/ship/nimbus/nimbus1.jpg', 7);
insert into image (path, offer_id) values ('img/ship/nimbus/nimbus2.jpg',7);
insert into image (path, offer_id) values ('img/ship/dionis/dionis.jpg',8);
insert into image (path, offer_id) values ('img/ship/dionis/dionis1.jpg',8);
insert into image (path, offer_id) values ('img/ship/party/party.jpg',9);
insert into image (path, offer_id) values ('img/ship/party/party2.jpg',9);
insert into image (path, offer_id) values ('img/ship/party/party1.jpg',9);
insert into image (path, offer_id) values ('img/ship/silverstar/silverstar1.jpg',10);
insert into image (path, offer_id) values ('img/ship/silverstar/silverstar.jpg',10);
insert into image (path, offer_id) values ('img/cottage/4/238068376.jpg', 1);
insert into image (path, offer_id) values ('img/cottage/3/132308681.jpg', 3);
insert into image (path, offer_id) values ('img/cottage/1/331714665.jpg', 4);
insert into image (path, offer_id) values ('img/cottage/2/169294777.jpg', 5);
insert into image (path, offer_id) values ('img/cottage/1/331714665.jpg', 6);

insert into image (path, offer_id) values ('img/ship/4/41.jpg', 7);
insert into image (path, offer_id) values ('img/ship/3/31.jpg', 10);
insert into image (path, offer_id) values ('img/ship/2/21.jpg', 9);
insert into image (path, offer_id) values ('img/ship/1/11.jpg', 8);

insert into image (path, offer_id) values ('img/instructor/206076_pecanje_zalazak_sunca.jpg', 13);
insert into image (path, offer_id) values ('img/instructor/images.jpg', 14);
insert into image (path, offer_id) values ('img/instructor/906z513_PECANJE2310pog.jpg', 11);
insert into image (path, offer_id) values ('img/instructor/maxresdefault.jpg', 12);

insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id) values
    (false, to_date('28.01.2022', 'DD.MM.yyyy'), 0, 4000, false, false, to_date('24.01.2022.', 'DD.MM.yyyy'), 1, 1);

insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id) values
    (true, to_date('17.02.2022', 'DD.MM.yyyy'), 0, 50000, false, false, to_date('14.02.2022.', 'DD.MM.yyyy'), 1, 4);

insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id) values
    (false, to_date('17.01.2022', 'DD.MM.yyyy'), 0, 100, true, false, to_date('11.01.2022.', 'DD.MM.yyyy'), 1, 5);

insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id) values
    (false, to_date('14.04.2022', 'DD.MM.yyyy'), 0, 42542, false, true, to_date('12.04.2022.', 'DD.MM.yyyy'), 1, 6);


insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id) values
    (false, to_date('28.01.2022', 'DD.MM.yyyy'), 1, 4000, false, false, to_date('24.01.2022.', 'DD.MM.yyyy'), 1, 7);

insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id) values
    (true, to_date('17.02.2022', 'DD.MM.yyyy'), 1, 50000, false, false, to_date('14.02.2022.', 'DD.MM.yyyy'), 1, 8);

insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id) values
    (false, to_date('17.01.2022', 'DD.MM.yyyy'), 1, 100, true, false, to_date('11.01.2022.', 'DD.MM.yyyy'), 1, 9);

insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id) values
    (false, to_date('14.04.2022', 'DD.MM.yyyy'), 1, 42542, false, true, to_date('12.04.2022.', 'DD.MM.yyyy'), 1, 10);
    
    
insert into registration_request (registration_type, status, user_id) values (0, 0, 3);
 insert into registration_request (registration_type, status, user_id) values (1, 0, 4);

 