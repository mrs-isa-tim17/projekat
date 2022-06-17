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
insert into address (longitude, latitude) values (16, 16);
insert into address (longitude, latitude) values (13, 13);


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
	--123
insert into users (type, id, deleted, email, loyalty_points, name, password,
	phonenumber, surname, user_type, address_id,last_password_reset_date, role_id, enabled) values
	('FI', nextval('user_seq'), false, 'milan.milanovi@example.com', 0, 'Milos', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',
	'+3817885', 'Milosevic', 0, 2, '2017-10-01 18:57:58.508-07',5, true);
insert into users(type, id, deleted, email, loyalty_points, name, password,
	phonenumber, surname, user_type, address_id,last_password_reset_date, role_id, enabled) values
	('CO', nextval('user_seq'), false, 'anastasijas@email.com', 0, 'Anastasija', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',
	'+38177590', 'Samcovic', 0, 3, '2017-10-01 18:57:58.508-07',3,true);
insert into users(type, id, deleted, email, loyalty_points, name, password,
	phonenumber, surname, user_type, address_id,last_password_reset_date, role_id, enabled) values
	('SO', nextval('user_seq'), false, 'bogi@email.com', 0, 'Bogdan', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',
	'+3853252', 'Radojevic', 0, 4, '2017-10-01 18:57:58.508-07',4,true);

insert into users (type, id, deleted, email, loyalty_points, name, password,
	phonenumber, surname, user_type, address_id,last_password_reset_date, role_id, enabled) values
	('CO', nextval('user_seq'), false, 'm.m@example.com', 0, 'Milan', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',
	'+3817885', 'Milanovic', 0, 11, '2017-10-01 18:57:58.508-07',3, true);


insert into users (type, id, deleted, email, loyalty_points, name, password,
	phonenumber, surname, user_type, address_id,last_password_reset_date, role_id, enabled) values
	('SO', nextval('user_seq'), false, 'ship.owner@example.com', 0, 'Milutin', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',
	'+3817885', 'Milutinovic', 0, 18, '2017-10-01 18:57:58.508-07',4, true);

	
	
insert into users(type, id, deleted, email, loyalty_points, name, password,
	phonenumber, surname, user_type, address_id,last_password_reset_date, role_id, enabled, init_login_changed) values
	('AD', nextval('user_seq'), false, 'admin@email.com', 0, 'Žika', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',
	'+3853252', 'Žikić', 0, 17, '2017-10-01 18:57:58.508-07',2,true, true);

	
	
insert into cottage (id, deleted,description, name, address_id, bed_quantity, room_quantity, owner) values
	(nextval('offer_seq'), false, ' Ponuda obuhvata restoran, vrt, terasu i smeštaj sa pogledom na planinu.', 'Meda na Tari', 16, 4, 2, 3);

insert into adventure (id, deleted, description, name, address_id, capacity, instructor_biography, owner) values
	(nextval('offer_seq'), false, 'Best adventure ever', 'Fishing on the Lake', 2, 8, 'Im the best instructor ever', 2);
	

insert into additional_services (name, price, deleted) values ('kabanica', 100, false);
insert into additional_services (name, price, deleted) values ('baterijska lampa', 100, false);
insert into additional_services (name, price, deleted) values ('bazen', 100, false);
insert into additional_services (name, price, deleted) values ('parking', 100, false);
insert into additional_services (name, price, deleted) values ('kablovska TV', 100, false);
insert into additional_services (name, price, deleted) values ('WiFi', 100, false);
insert into additional_services (name, price, deleted) values ('društvene igre', 100, false);
insert into additional_services (name, price, deleted) values ('besplatne pešačke ture', 100, false);
insert into additional_services (name, price, deleted) values ('bicikli', 100, false);

insert into additional_services_offer (offer_id, additional_service_id) values (1, 1);
insert into additional_services_offer (offer_id, additional_service_id) values (1, 4);
insert into additional_services_offer (offer_id, additional_service_id) values (1, 9);
insert into additional_services_offer (offer_id, additional_service_id) values (2, 2);
insert into additional_services_offer (offer_id, additional_service_id) values (3, 2);
insert into additional_services_offer (offer_id, additional_service_id) values (7, 4);
insert into additional_services_offer (offer_id, additional_service_id) values (7, 5);

insert into additional_services_offer (offer_id, additional_service_id) values (7, 2);
insert into additional_services_offer (offer_id, additional_service_id) values (7, 9);

insert into additional_services_offer (offer_id, additional_service_id) values (8, 6);
insert into additional_services_offer (offer_id, additional_service_id) values (8, 7);

insert into additional_services_offer (offer_id, additional_service_id) values (9, 5);
insert into additional_services_offer (offer_id, additional_service_id) values (9, 8);

insert into additional_services_offer (offer_id, additional_service_id) values (10, 7);
insert into additional_services_offer (offer_id, additional_service_id) values (10, 9);

insert into behavior_rule (text) values ('zabranjeno pecanje');
insert into behavior_rule (text) values ('zabranjeno pusenje');
insert into behavior_rule (text) values ('dozvoljeno pecanje');
insert into behavior_rule (text) values ('dozvoljeno kupanje');
insert into behavior_rule (text) values ('zabranjeno kupanje');
insert into behavior_rule (text) values ('volimo ljubimce');

insert into behavior_rules_offer (offer_id, behavior_rule_id) values (2, 1);
insert into behavior_rules_offer (offer_id, behavior_rule_id) values (2, 2);
insert into behavior_rules_offer (offer_id, behavior_rule_id) values (1, 1);
insert into behavior_rules_offer (offer_id, behavior_rule_id) values (1, 2);
insert into behavior_rules_offer (offer_id, behavior_rule_id) values (1, 3);
insert into behavior_rules_offer (offer_id, behavior_rule_id) values (1, 4);
insert into behavior_rules_offer (offer_id, behavior_rule_id) values (1, 5);
insert into behavior_rules_offer (offer_id, behavior_rule_id) values (7, 1);
insert into behavior_rules_offer (offer_id, behavior_rule_id) values (8, 2);
insert into behavior_rules_offer (offer_id, behavior_rule_id) values (9, 5);
insert into behavior_rules_offer (offer_id, behavior_rule_id) values (10, 3);

insert into fishing_equipment (name) values('štapovi');
insert into fishing_equipment (name) values('udice');
insert into fishing_equipment (name) values('mamac');
insert into fishing_equipment (name) values('čamac');

insert into fishing_equipments_offer (adventure_id, fishing_equipment_id) values (2, 1);
insert into fishing_equipments_offer (adventure_id, fishing_equipment_id) values (2, 2);
insert into fishing_equipments_offer (adventure_id, fishing_equipment_id) values (2, 2);
--insert into fishing_equipments_offer (adventure_id, fishing_equipment_id) values (7, 2);
--insert into fishing_equipments_offer (adventure_id, fishing_equipment_id) values (8, 4);
--insert into fishing_equipments_offer (adventure_id, fishing_equipment_id) values (9, 1);
--insert into fishing_equipments_offer (adventure_id, fishing_equipment_id) values (10, 2);
--insert into fishing_equipments_offer (adventure_id, fishing_equipment_id) values (10, 2);
--insert into fishing_equipments_offer (adventure_id, fishing_equipment_id) values (10, 1);


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

insert into cancel_condition (days, precent) values (5, 13);
insert into cancel_condition (days, precent) values (10, 17);
insert into cancel_condition (days, precent) values (15, 18);
insert into cancel_condition (days, precent) values (20, 19);

insert into cancel_condition (days, precent) values (5, 13);
insert into cancel_condition (days, precent) values (10, 17);
insert into cancel_condition (days, precent) values (15, 18);
insert into cancel_condition (days, precent) values (20, 19);

insert into cancel_condition (days, precent) values (5, 13);
insert into cancel_condition (days, precent) values (10, 17);
insert into cancel_condition (days, precent) values (15, 18);
insert into cancel_condition (days, precent) values (20, 19);

insert into cancel_condition_offer (offer_id, cancel_condition_id) values (2, 1);
insert into cancel_condition_offer (offer_id, cancel_condition_id) values (2, 2);
insert into cancel_condition_offer (offer_id, cancel_condition_id) values (2, 3);
insert into cancel_condition_offer (offer_id, cancel_condition_id) values (2, 4);

insert into cancel_condition_offer (offer_id, cancel_condition_id) values (1, 21);
insert into cancel_condition_offer (offer_id, cancel_condition_id) values (1, 22);
insert into cancel_condition_offer (offer_id, cancel_condition_id) values (1, 23);
insert into cancel_condition_offer (offer_id, cancel_condition_id) values (1, 24);

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

insert into cancel_condition_offer (offer_id, cancel_condition_id) values (7, 25);
insert into cancel_condition_offer (offer_id, cancel_condition_id) values (7, 26);
insert into cancel_condition_offer (offer_id, cancel_condition_id) values (7, 27);
insert into cancel_condition_offer (offer_id, cancel_condition_id) values (7, 28);



insert into image (path, offer_id) values('/img/adventure/Drina1.jpg', 2);
insert into image (path, offer_id) values('/img/adventure/Drina2.jpg', 2);
insert into image (path, offer_id) values('/img/adventure/Drina3.jpg', 2);

insert into experience_review (rate, status, text, client_id, offer_id, offer_type) values(5, 1, 'Prelepa tura, sve pohvale.', 1, 2, 2);


insert into experience_review (rate, status, text, client_id, offer_id, offer_type) values(4, 1, 'Prelepo, ali daleko.', 1, 1, 1);
insert into experience_review (rate, status, text, client_id, offer_id, offer_type) values(4, 1, 'Prelepo, ali daleko.', 1, 3, 1);
insert into experience_review (rate, status, text, client_id, offer_id, offer_type) values(3, 1, 'Prelepo.', 1, 4, 1);
insert into experience_review (rate, status, text, client_id, offer_id, offer_type) values(5, 1, 'Sve pohvale.', 1, 5, 1);
insert into experience_review (rate, status, text, client_id, offer_id, offer_type) values(5, 1, 'Sve pohvale,odlično.', 1, 6, 1);
insert into experience_review (rate, status, text, client_id, offer_id, offer_type) values(5, 1, 'Sve pohvale,odlično.', 1, 7, 2);
insert into experience_review (rate, status, text, client_id, offer_id, offer_type) values(3, 1, 'Bilo bi dobro da je hrana ukusnija.', 1, 7, 2);
insert into experience_review (rate, status, text, client_id, offer_id, offer_type) values(5, 1, 'Sve pohvale,odlično.', 1, 8, 2);


insert into pricelist (end_date, price, start_date, offer_id) values (null, 12000, '29.04.2022.', 2);
insert into pricelist (end_date, price, start_date, offer_id) values (null, 3000, '30.05.2022.', 1);
insert into pricelist (end_date, price, start_date, offer_id) values ('30.05.2022.', 4000, '29.04.2022.', 7);
insert into pricelist (end_date, price, start_date, offer_id) values (null, 4000, '30.05.2022.', 7);
insert into pricelist (end_date, price, start_date, offer_id) values (null, 5000, '29.04.2022.', 8);

insert into cottage (id, deleted, description, name, address_id, bed_quantity, room_quantity, owner)
    values (nextval('offer_seq'), false, 'Idealno mesto za odmor', 'Šumske priče Divčibare', 1, 8, 2, 3);

insert into cottage (id, deleted, description, name, address_id, bed_quantity, room_quantity, owner)
    values (nextval('offer_seq'), false, 'U objektu Kedar se služi kontinentalni doručak.', 'Kedar Goč', 4, 2, 1, 3);

insert into cottage (id, deleted, description, name, address_id, bed_quantity, room_quantity, owner)
    values (nextval('offer_seq'), false, 'Iz objekta se pruža pogled na vrt i planinu.' , 'Holiday Home Golija', 5, 5, 3, 3);

insert into cottage (id, deleted, description, name, address_id, bed_quantity, room_quantity, owner)
    values (nextval('offer_seq'), false, 'Ova mala farma okružena je planinskom rekom i tipičnim srpskim seoskim krajolikom.', 'Bogut Bogutovac', 6, 1, 1, 3);

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

insert into pricelist (end_date, price, start_date, offer_id) values (null, 12000, '29.04.2022.', 11);
insert into pricelist (end_date, price, start_date, offer_id) values (null, 12000, '29.04.2022.', 12);
insert into pricelist (end_date, price, start_date, offer_id) values (null, 12000, '29.04.2022.', 13);
insert into pricelist (end_date, price, start_date, offer_id) values (null, 12000, '29.04.2022.', 14);


insert into image (path, offer_id) values ('/img/cottage/meda/meda.jpg', 1);
--insert into image (path, offer_id) values ('/img/cottage/meda/meda1.jpg', 1);
insert into image (path, offer_id) values ('/img/cottage/meda/meda2.jpg', 1);
--insert into image (path, offer_id) values ('/img/cottage/3/132308681.jpg', 2);
insert into image (path, offer_id) values ('/img/cottage/suma/suma.jpg', 3);
insert into image (path, offer_id) values ('/img/cottage/suma/suma1.jpg', 3);
insert into image (path, offer_id) values ('/img/cottage/kedar/kedar.jpg', 4);
insert into image (path, offer_id) values ('/img/cottage/kedar/kedar1.jpg', 4);
insert into image (path, offer_id) values ('/img/cottage/kedar/kedar2.jpg', 4);
insert into image (path, offer_id) values ('/img/cottage/holiday/holiday.jpg', 5);
insert into image (path, offer_id) values ('/img/cottage/holiday/holiday1.jpg', 5);
insert into image (path, offer_id) values ('/img/cottage/bogut/bogut.jpg', 6);
insert into image (path, offer_id) values ('/img/cottage/bogut/bogut1.jpg', 6);
insert into image (path, offer_id) values ('/img/cottage/bogut/bogut2.jpg', 6);

insert into image (path, offer_id) values ('/img/ship/nimbus/nimbus.jpg', 7);
insert into image (path, offer_id) values ('/img/ship/nimbus/nimbus1.jpg', 7);
insert into image (path, offer_id) values ('/img/ship/nimbus/nimbus2.jpg',7);
insert into image (path, offer_id) values ('/img/ship/dionis/dionis.jpg',8);
insert into image (path, offer_id) values ('/img/ship/dionis/dionis1.jpg',8);
insert into image (path, offer_id) values ('/img/ship/party/party.jpg',9);
insert into image (path, offer_id) values ('/img/ship/party/party2.jpg',9);
insert into image (path, offer_id) values ('/img/ship/party/party1.jpg',9);
insert into image (path, offer_id) values ('/img/ship/silverstar/silverstar1.jpg',10);
insert into image (path, offer_id) values ('/img/ship/silverstar/silverstar.jpg',10);
insert into image (path, offer_id) values ('/img/cottage/4/238068376.jpg', 1);
insert into image (path, offer_id) values ('/img/cottage/3/132308681.jpg', 3);
insert into image (path, offer_id) values ('/img/cottage/1/331714665.jpg', 4);
insert into image (path, offer_id) values ('/img/cottage/2/169294777.jpg', 5);
insert into image (path, offer_id) values ('/img/cottage/1/331714665.jpg', 6);

insert into image (path, offer_id) values ('/img/ship/4/41.jpg', 7);
insert into image (path, offer_id) values ('/img/ship/3/31.jpg', 10);
insert into image (path, offer_id) values ('/img/ship/2/21.jpg', 9);
insert into image (path, offer_id) values ('/img/ship/1/11.jpg', 8);

insert into image (path, offer_id) values ('/img/instructor/206076_pecanje_zalazak_sunca.jpg', 13);
insert into image (path, offer_id) values ('/img/instructor/images.jpg', 14);
insert into image (path, offer_id) values ('/img/instructor/906z513_PECANJE2310pog.jpg', 11);
insert into image (path, offer_id) values ('/img/instructor/maxresdefault.jpg', 12);


insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
    (false, '30.04.2022', 1, 4000, false, false, '28.04.2022.', 1, 7,false);
insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
        (false, '30.05.2022', 1, 4000, false, false, '18.05.2022.', 1, 7,false);
insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
            (false, '30.06.2022.', 1, 4000, false, false, '25.06.2022.', 1, 7,false);
insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
                (false, '30.06.2022', 1, 4000, false, false, '15.06.2022.', 1, 9,false);
insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
    (false, '30.06.2022', 1, 4000, false, false, '28.06.2022.', 1, 8,false);


insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
    (false, '30.04.2022', 0, 4000, false, false, '28.04.2022.', 1, 1,false);
insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
        (false, '20.05.2022', 0, 4000, false, false, '15.05.2022.', 1, 1,false);
insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
            (false, '12.05.2022', 0, 4000, false, false, '10.05.2022.', 1, 1,false);
insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
    (true, '17.02.2022', 0, 50000, false, false, '14.02.2022.', 1, 4,false);

insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
    (false, '17.01.2022', 0, 100, false, false, '11.01.2022.', 1, 5,false);

insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
    (false, '14.04.2022', 0, 42542, false, true, '12.04.2022.', 1, 6,false);
insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
        (false, '12.05.2022', 0, 5000, false, false, '10.05.2022.', 1, 1,false);


insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
    (false, '28.06.2022', 1, 4000, false, false, '24.06.2022.', 1, 7,false);

insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
    (true, '17.02.2022', 1, 50000, false, false, '14.02.2022.', 1, 8,false);

insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
    (false, '17.01.2022', 1, 100, true, false, '11.01.2022.', 1, 9,false);

insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
    (false, '14.04.2022', 1, 42542, false, true, '12.04.2022.', 1, 10,false);

insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
      (false, '14.06.2022', 0, 42542, false, false, '12.06.2022.', 1, 1,false);
      
      
insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
    (false, '28.06.2022', 1, 4000, false, false, '24.06.2022.', 1, 7, false);

insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
    (true, '17.02.2022', 1, 50000, false, false, '14.02.2022.', 1, 8, false);

insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
    (false, '17.01.2022', 1, 100, true, false, '11.01.2022.', 1, 9, false);

insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
    (false, '14.04.2022', 1, 42542, false, true, '12.04.2022.', 1, 10, false);

insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
(false, '14.06.2022', 1, 42542, false, false, '12.06.2022.', 1, 1, false);


insert into reservation(canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
    (false, '09.05.2022.', 2, 3000, false, false, '05.05.2022.', 1, 2, false);
insert into reservation(canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
    (false, '16.05.2022.', 2, 3000, true, false, '15.05.2022.', 1, 2, false); 
     


insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id,ship_owner_present) values
    (false, '30.05.2022', 0, 4000, false, false, '26.05.2022.', 1, 1,false);

insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
  (false, '20.06.2022', 0, 4000, false, false, '08.06.2022.', 1, 1, false);

  insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
    (false, '20.06.2022', 0, 4000, false, false, '08.06.2022.', 1, 1, false);

insert into registration_request (registration_type, status, user_id, version) values (0, 0, 3,1);
insert into registration_request (registration_type, status, user_id, version) values (1, 0, 4,1);



 insert into period_availability (end_date, start_date, offer_id) values ('20.12.2022','01.01.2022',1);
 insert into period_unavailability (start_date, end_date, offer_id) values ('15.05.2022','25.05.2022',1);


 insert into period_availability (start_date,end_date,  offer_id) values ('20.01.2022','01.12.2022',3);
--insert into period_unavailability (start_date,end_date,  offer_id) values ('20.05.2022','30.05.2022',3);

insert into reservation (canceled, start_date, offer_type, price, quick, reviewed, end_date, client_id, offer_id, ship_owner_present) values
    (false, '20.05.2022', 0, 4000, false, false, '25.05.2022.', 1, 3,false);


insert into period_availability ( start_date,end_date, offer_id) values ('20.01.2022','01.12.2022',4);
insert into period_unavailability (start_date,end_date,  offer_id) values ('10.05.2022','30.05.2022',4);


insert into period_availability (start_date, end_date,  offer_id) values ('20.01.2022','01.12.2022',5);
--insert into period_unavailability (start_date, end_date, offer_id) values ('20.06.2022','22.06.2022',5);
--insert into period_unavailability (start_date, end_date, offer_id) values ('20.05.2022','22.05.2022',5);


insert into complaint (offer_type, status, text, client_id, offer_id, reservation_id) values (0, 0, 'Vikkendica se ne greje tokom proleca i prilicno je hladno.', 1, 4, 2);
insert into complaint (offer_type, status, text, client_id, offer_id, reservation_id) values (0, 0, 'Internet je jako los, spor i ne moze se koristiti.', 1, 5, 3);

insert into period_unavailability (start_date, end_date, offer_id) values ('03.06.2022. 20:00', '06.06.2022. 08:00', 2);


insert into delete_request (status, text, user_id) values (0, 'Molim da mi se obrise nalog jer se vise ne bavim ovim poslom.', 2);
insert into delete_request (status, text, user_id) values (0, 'Zelim da obrisem nalog.', 3);
insert into delete_request (status, text, user_id) values (0, 'Molim da mi se obrise nalog.', 5);

--insert into experience_review (rate, status, text, client_id, offer_id, offer_type) values(5, 1, 'juhi.', 1, 1, 0);
--insert into experience_review (rate, status, text, client_id, offer_id, offer_type) values(1, 1, 'nemojte.', 1,1, 0);
--insert into experience_review (rate, status, text, client_id, offer_id, offer_type) values(3, 1, 'ok.', 1, 1, 0);
--insert into experience_review (rate, status, text, client_id, offer_id, offer_type) values(4, 1, '', 1, 1, 0);

--insert into experience_review (rate, status, text, client_id, offer_id, offer_type) values(1, 1, 'nemojte.', 1,7,1);
--insert into experience_review (rate, status, text, client_id, offer_id, offer_type) values(3, 1, 'ok.', 1, 7, 1);
--insert into experience_review (rate, status, text, client_id, offer_id, offer_type) values(4, 1, '', 1, 7, 1);

insert into period_availability (end_date, start_date, offer_id) values ('20.12.2022','01.01.2022',7);
insert into period_unavailability (start_date, end_date, offer_id) values ('15.05.2022','25.05.2022',7);

    insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id,ship_owner_present) values
        (false, '15.06.2022', 0, 4500, true, false, '15.06.2022.', null, 1,false);
insert into sale_appointment (start_sale_date, duration, people_quantity, offer_id, discount, address_id, reserved, offer_type, version,reservation_id) values
    ('15.06.2022', 5, 4, 1, 4500, 20, false, 0, 1, 21);

    insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id,ship_owner_present) values
        (false, '24.05.2022', 0, 2000, true, false, '21.06.2022.', null, 1,false);
insert into sale_appointment (start_sale_date, duration, people_quantity, offer_id, discount, address_id, reserved, offer_type, version,reservation_id) values
    ('21.06.2022', 3, 2, 1, 2000, 21, false, 0, 1, 22);

insert into additional_services_sale_appointment (sale_appointment_id, additional_service_id) values (1, 2);
insert into additional_services_sale_appointment (sale_appointment_id, additional_service_id) values (1, 4);
insert into additional_services_sale_appointment (sale_appointment_id, additional_service_id) values (1, 9);
insert into additional_services_sale_appointment (sale_appointment_id, additional_service_id) values (2, 5);
insert into additional_services_sale_appointment (sale_appointment_id, additional_service_id) values (2, 6);


    insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id,ship_owner_present) values
        (false, '27.06.2022', 1, 4500, true, false, '23.06.2022.', null, 7,false);
insert into sale_appointment (start_sale_date, duration, people_quantity, offer_id, discount, address_id, reserved, offer_type, version, reservation_id) values
    ('23.06.2022', 4, 4, 7, 4500, 22, false, 1,1, 23);

    insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id,ship_owner_present) values
        (false, '30.06.2022', 1, 3500, true, false, '25.06.2022.', null, 7,false);
insert into sale_appointment (start_sale_date, duration, people_quantity, offer_id, discount, address_id, reserved, offer_type, version,reservation_id) values
    ('01.06.2022', 5, 4, 7, 3500, 23, false, 1,1, 24);

    insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id,ship_owner_present) values
        (false, '31.07.2022', 1, 1000, true, false, '30.06.2022.', null, 7,false);
insert into sale_appointment (start_sale_date, duration, people_quantity, offer_id, discount, address_id, reserved, offer_type, version,reservation_id) values
    ('30.06.2022', 2, 4, 7, 1000, 24, false, 1,1, 25);

    insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id,ship_owner_present) values
        (false, '06.06.2022', 1, 1200, true, false, '03.06.2022.', null, 7,false);
insert into sale_appointment (start_sale_date, duration, people_quantity, offer_id, discount, address_id, reserved, offer_type, version,reservation_id) values
    ('03.06.2022', 3, 4, 7, 1200, 25, false, 1,1,26);

insert into additional_services_sale_appointment (sale_appointment_id, additional_service_id) values (3, 2);
insert into additional_services_sale_appointment (sale_appointment_id, additional_service_id) values (4, 4);
insert into additional_services_sale_appointment (sale_appointment_id, additional_service_id) values (6, 9);
insert into additional_services_sale_appointment (sale_appointment_id, additional_service_id) values (5, 5);
insert into additional_services_sale_appointment (sale_appointment_id, additional_service_id) values (2, 6);

    insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id,ship_owner_present) values
        (false, '23.06.2022 14:00', 2, 1000, true, false, '23.06.2022. 12:00', null, 2,false);
insert into sale_appointment (start_sale_date, duration, people_quantity, offer_id, discount, address_id, reserved, offer_type, version,reservation_id) values
    ('23.06.2022', 2, 4, 2, 1000, 26, false, 2,1,27);

    insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id,ship_owner_present) values
        (false, '26.06.2022 15:00', 2, 1200, true, false, '26.06.2022. 12:00', null, 2,false);
insert into sale_appointment (start_sale_date, duration, people_quantity, offer_id, discount, address_id, reserved, offer_type, version,reservation_id) values
    ('26.06.2022', 3, 4, 2, 1200, 27, false, 2,1,28);


insert into period_availability (end_date, start_date, offer_id) values ('20.12.2022','01.01.2022',2);

insert into subscriptions (client_id, offer_id) values (1,7);
insert into subscriptions (client_id, offer_id) values (1,2);

insert into percentage_of_earnings (start_date, end_date, percentage) values ('01.01.2022.', null, 3);

insert into pricelist (end_date, price, start_date, offer_id) values (null, 1500, '29.04.2022.', 3);
insert into pricelist (end_date, price, start_date, offer_id) values (null, 4000, '29.04.2022.', 4);

 insert into period_availability (end_date, start_date, offer_id) values ('20.12.2022','01.01.2022',8);
 insert into period_availability (end_date, start_date, offer_id) values ('20.12.2022','01.01.2022',9);
