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
insert into address (longitude, latitude) values (13, 13);

INSERT INTO ROLE (name) VALUES ('ROLE_CLIENT');
INSERT INTO ROLE (name) VALUES ('ROLE_ADMIN');
INSERT INTO ROLE (name) VALUES ('ROLE_COTTAGE_OWNER');
INSERT INTO ROLE (name) VALUES ('ROLE_SHIP_OWNER');
INSERT INTO ROLE (name) VALUES ('ROLE_FISHINSTRUCTOR');


-----------USERS
	--123
insert into users (type, id, deleted, email, loyalty_points, name, password,
	phonenumber,  surname, user_type, address_id, last_password_reset_date, role_id,
	penalty_number, enabled) values
	('CL', nextval('user_seq'), false, 'erdelji.violeta@gmail.com', 0, 'Marko', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',
	'+3815828', 'Markovic', 0,1, to_timestamp('2017-10-01 21:58:58.508-07', 'YYYY-MM-DD HH24:MI:SS'), 1,
	1, true);
	--123
insert into users (type, id, deleted, email, loyalty_points, name, password,
	phonenumber, surname, user_type, address_id,last_password_reset_date, role_id, enabled) values
	('FI', nextval('user_seq'), false, 'milan.milanovi@example.com', 0, 'Milan', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',
	'+3817885', 'Milanovi', 0, 2, to_timestamp('2017-10-01 18:57:58.508-07', 'YYYY-MM-DD HH24:MI:SS'),5, true);
insert into users(type, id, deleted, email, loyalty_points, name, password,
	phonenumber, surname, user_type, address_id,last_password_reset_date, role_id, enabled) values
	('CO', nextval('user_seq'), false, 'anastasijas@email.com', 0, 'Anastasija', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',
	'+38177590', 'Samcovic', 0, 3, to_timestamp('2017-10-01 18:57:58.508-07', 'YYYY-MM-DD HH24:MI:SS'),3,true);
insert into users(type, id, deleted, email, loyalty_points, name, password,
	phonenumber, surname, user_type, address_id,last_password_reset_date, role_id, enabled) values
	('SO', nextval('user_seq'), false, 'bogi@email.com', 0, 'Bogdan', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',
	'+3853252', 'Radojevic', 0, 4, to_timestamp('2017-10-01 18:57:58.508-07', 'YYYY-MM-DD HH24:MI:SS'),4,true);

insert into users (type, id, deleted, email, loyalty_points, name, password,
	phonenumber, surname, user_type, address_id,last_password_reset_date, role_id, enabled) values
	('CO', nextval('user_seq'), false, 'm.m@example.com', 0, 'Djura', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',
	'+3817885', 'Djuric', 0, 11, to_timestamp('2017-10-01 18:57:58.508-07', 'YYYY-MM-DD HH24:MI:SS'),3, true);


insert into users (type, id, deleted, email, loyalty_points, name, password,
	phonenumber, surname, user_type, address_id,last_password_reset_date, role_id, enabled) values
	('SO', nextval('user_seq'), false, 'ship.owner@example.com', 0, 'Jovan', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',
	'+3817885', 'Jovanovic', 0, 18, to_timestamp('2017-10-01 18:57:58.508-07', 'YYYY-MM-DD HH24:MI:SS'),4, true);



insert into users(type, id, deleted, email, loyalty_points, name, password,
	phonenumber, surname, user_type, address_id,last_password_reset_date, role_id, enabled, init_login_changed) values
	('AD', nextval('user_seq'), false, 'admin@email.com', 0, '??ika', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',
	'+3853252', '??iki??', 0, 17, to_timestamp('2017-10-01 18:57:58.508-07', 'YYYY-MM-DD HH24:MI:SS'),2,true, true);

	insert into users(type, id, deleted, email, loyalty_points, name, password,
	phonenumber, surname, user_type, address_id,last_password_reset_date, role_id, enabled, init_login_changed) values
	('AD', nextval('user_seq'), false, 'mika@email.com', 0, 'Mika', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',
	'+3853252', 'Miki??', 0, 79, to_timestamp('2017-10-01 18:57:58.508-07', 'YYYY-MM-DD HH24:MI:SS'),2,true, false);


insert into users (type, id, deleted, email, loyalty_points, name, password,
	phonenumber, surname, user_type, address_id,last_password_reset_date, role_id, enabled) values
	('FI', nextval('user_seq'), false, 'milos.milosevi@example.com', 0, 'Milos', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',
	'+3817885', 'Milosevic', 0, 30, to_timestamp('2017-10-01 18:57:58.508-07', 'YYYY-MM-DD HH24:MI:SS'),5, true);

insert into users (type, id, deleted, email, loyalty_points, name, password,
	phonenumber, surname, user_type, address_id,last_password_reset_date, role_id, enabled) values
	('FI', nextval('user_seq'), false, 'miroslav.miroslavljev@example.com', 0, 'Miroslav', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',
	'+3817885', 'Miroslavljev', 0, 31, to_timestamp('2017-10-01 18:57:58.508-07', 'YYYY-MM-DD HH24:MI:SS'),5, true);


insert into users (type, id, deleted, email, loyalty_points, name, password,
	phonenumber,  surname, user_type, address_id, last_password_reset_date, role_id,
	penalty_number, enabled) values
	('CO', nextval('user_seq'), false, 'nov.cottage.owner@gmail.com', 0, 'Marko', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',
	'+3815828', 'Markovic', 0,28, to_timestamp('2017-10-01 18:57:58.508-07', 'YYYY-MM-DD HH24:MI:SS'), 3,
	null, false);


insert into users (type, id, deleted, email, loyalty_points, name, password,
	phonenumber,  surname, user_type, address_id, last_password_reset_date, role_id,
	penalty_number, enabled) values
	('CL', nextval('user_seq'), false, 'stefan@gmail.com', 4, 'Stefan', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',
	'+3815828', 'Djokic', 0,32, to_timestamp('2017-10-01 18:57:58.508-07', 'YYYY-MM-DD HH24:MI:SS'), 1,
	0, true);

insert into users (type, id, deleted, email, loyalty_points, name, password,
	phonenumber,  surname, user_type, address_id, last_password_reset_date, role_id,
	penalty_number, enabled) values
	('CL', nextval('user_seq'), false, 'david@gmail.com', 4, 'David', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',
	'+3815828', 'Davidovic', 0,33, to_timestamp('2017-10-01 18:57:58.508-07', 'YYYY-MM-DD HH24:MI:SS'), 1,
	0, true);
--------COTTAGE

insert into cottage (id, deleted,description, name, address_id, bed_quantity, room_quantity, owner) values
	(nextval('offer_seq'), false, ' Ponuda obuhvata restoran, vrt, terasu i sme??taj sa pogledom na planinu.', 'Meda na Tari', 16, 4, 2, 3);

insert into adventure (id, deleted, description, name, address_id, capacity, instructor_biography, owner) values
	(nextval('offer_seq'), false, 'Best adventure ever', 'Fishing on the Lake', 2, 8, 'Im the best instructor ever', 2);

insert into cottage (id, deleted, description, name, address_id, bed_quantity, room_quantity, owner)
    values (nextval('offer_seq'), false, 'Idealno mesto za odmor', '??umske pri??e Div??ibare', 1, 8, 2, 3);

insert into cottage (id, deleted, description, name, address_id, bed_quantity, room_quantity, owner)
    values (nextval('offer_seq'), false, 'U objektu Kedar se slu??i kontinentalni doru??ak.', 'Kedar Go??', 4, 2, 1, 3);

insert into cottage (id, deleted, description, name, address_id, bed_quantity, room_quantity, owner)
    values (nextval('offer_seq'), false, 'Iz objekta se pru??a pogled na vrt i planinu.' , 'Holiday Home Golija', 5, 5, 3, 3);

insert into cottage (id, deleted, description, name, address_id, bed_quantity, room_quantity, owner)
    values (nextval('offer_seq'), false, 'Ova mala farma okru??ena je planinskom rekom i tipi??nim srpskim seoskim krajolikom.', 'Bogut Bogutovac', 6, 1, 1, 3);

--------SHIP

insert into ship (id, deleted, description, name, address_id, capacity, engine_designation, engine_power,
 length, max_speed, navigation_equipment, type, owner)
    values (nextval('offer_seq'), false, 'Svakog gosta do??eka??e pi??e dobrodo??lice i snekovi.', 'Nimbus 3100', 7, 4,
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
    values (nextval('offer_seq'), false, 'Namenjen je turisti??kim obilascima du?? Dunava.', 'Silver Star', 10, 200,
    'engine destignation', 2222, 50, 20, 1, 'tipppp', 4);



--------ADVENTURE
insert into adventure (id, deleted, description, name, address_id, capacity,
    instructor_biography, owner) values
	(nextval('offer_seq'), false, 'Best adventure ever', 'Pecanje na Dunavu', 12, 8,
	'Im the best instructor ever', 2);

insert into adventure (id, deleted, description, name, address_id, capacity,
    instructor_biography, owner) values
	(nextval('offer_seq'), false, 'Pali??', 'Pali??', 13, 10,
	'Humbalee humbalaa', 2);

insert into adventure (id, deleted, description, name, address_id, capacity,
    instructor_biography, owner) values
	(nextval('offer_seq'), false, 'Sava', 'Sava', 14, 2,
	'Oxfort oxfort oxy foxy roxfort', 9);

insert into adventure (id, deleted, description, name, address_id, capacity,
    instructor_biography, owner) values
	(nextval('offer_seq'), false, 'Comfy on the Tisa', 'Comfy on the Tisa', 15, 6,
	'Instruktor instruktor instruktor, najbolji na svetu', 10);





    insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id,ship_owner_present) values
        (false, to_date('28.06.2022', 'DD.MM.YYYY'), 0, 4500, true, false, to_date('25.06.2022', 'DD.MM.YYYY'), null, 1,false);
insert into sale_appointment (start_sale_date, duration, people_quantity, offer_id, discount, address_id, reserved, offer_type, version,reservation_id) values
    (to_date('25.06.2022', 'DD.MM.YYYY'), 3, 4, 1, 4500, 20, false, 0, 1, 1);

    insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id,ship_owner_present) values
        (false, to_date('15.07.2022', 'DD.MM.YYYY'), 0, 2000, true, false, to_date('10.07.2022', 'DD.MM.YYYY'), null, 1,false);
insert into sale_appointment (start_sale_date, duration, people_quantity, offer_id, discount, address_id, reserved, offer_type, version,reservation_id) values
    (to_date('10.07.2022', 'DD.MM.YYYY'), 5, 2, 1, 2000, 21, false, 0, 1, 2);

    insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id,ship_owner_present) values
        (false, to_date('05.07.2022', 'DD.MM.YYYY'), 2, 2000, true, false, to_date('01.07.2022', 'DD.MM.YYYY'), null, 2,false);
insert into sale_appointment (start_sale_date, duration, people_quantity, offer_id, discount, address_id, reserved, offer_type, version,reservation_id) values
    (to_date('1.07.2022', 'DD.MM.YYYY'), 5, 2, 2, 2000, 21, false, 2, 1, 3);


insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id,ship_owner_present) values
        (false, to_date('28.06.2022', 'DD.MM.YYYY'), 1, 4500, true, false, to_date('25.06.2022', 'DD.MM.YYYY'), null, 7,false);
insert into sale_appointment (start_sale_date, duration, people_quantity, offer_id, discount, address_id, reserved, offer_type, version, reservation_id) values
    (to_date('25.06.2022', 'DD.MM.YYYY'), 3, 4, 7, 4500, 22, false, 1,1, 4);

    insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id,ship_owner_present) values
        (false, to_date('3.07.2022', 'DD.MM.YYYY'), 1, 3500, true, false, to_date('01.07.2022', 'DD.MM.YYYY'), null, 7,false);
insert into sale_appointment (start_sale_date, duration, people_quantity, offer_id, discount, address_id, reserved, offer_type, version,reservation_id) values
    (to_date('01.07.2022', 'DD.MM.YYYY'), 3, 4, 7, 3500, 23, false, 1,1, 5);

    insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id,ship_owner_present) values
        (false, to_date('11.07.2022', 'DD.MM.YYYY'), 1, 1000, true, false, to_date('10.07.2022', 'DD.MM.YYYY'), null, 7,false);
insert into sale_appointment (start_sale_date, duration, people_quantity, offer_id, discount, address_id, reserved, offer_type, version,reservation_id) values
    (to_date('10.06.2022', 'DD.MM.YYYY'), 1, 4, 7, 1000, 24, false, 1,1, 6);

    insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id,ship_owner_present) values
        (false, to_date('03.07.2022', 'DD.MM.YYYY'), 1, 1200, true, false, to_date('01.07.2022', 'DD.MM.YYYY'), null, 7,false);
insert into sale_appointment (start_sale_date, duration, people_quantity, offer_id, discount, address_id, reserved, offer_type, version,reservation_id) values
    (to_date('01.07.2022', 'DD.MM.YYYY'), 3, 4, 8, 1200, 25, false, 1,1,7);


    insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id,ship_owner_present) values
        (false, to_timestamp('24.08.2022 14:00', 'DD.MM.YYYY HH24:MI'), 2, 1000, true, false, to_timestamp('22.08.2022 12:00', 'DD.MM.YYYY HH24:MI'), null, 2,false);
insert into sale_appointment (start_sale_date, duration, people_quantity, offer_id, discount, address_id, reserved, offer_type, version,reservation_id) values
    (to_timestamp('22.08.2022 12:00', 'DD.MM.YYYY HH24:MI'), 44, 4, 2, 1000, 26, false, 2,1,8);

    insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id,ship_owner_present) values
        (false, to_timestamp('13.07.2022 12:00', 'DD.MM.YYYY HH24:MI'), 2, 1200, true, false, to_timestamp('11.07.2022 12:00', 'DD.MM.YYYY HH24:MI'), null, 2,false);
insert into sale_appointment (start_sale_date, duration, people_quantity, offer_id, discount, address_id, reserved, offer_type, version,reservation_id) values
    (to_timestamp('11.07.2022 12:00', 'DD.MM.YYYY HH24:MI'), 76, 4, 2, 1200, 27, false, 2,1,9);

   insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id,ship_owner_present) values
            (false, to_timestamp('26.06.2022 15:00', 'DD.MM.YYYY HH24:MI'), 2, 1200, false, false, to_timestamp('19.06.2022 12:00', 'DD.MM.YYYY HH24:MI'), 12, 2, false);

   insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id,ship_owner_present) values
            (false, to_timestamp('26.06.2022 15:00', 'DD.MM.YYYY HH24:MI'), 2, 1200, false, false, to_timestamp('19.06.2022 12:00', 'DD.MM.YYYY HH24:MI'), 13, 11, false);


insert into additional_services (name, price, deleted) values ('kabanica', 100, false);
insert into additional_services (name, price, deleted) values ('baterijska lampa', 100, false);
insert into additional_services (name, price, deleted) values ('bazen', 100, false);
insert into additional_services (name, price, deleted) values ('parking', 100, false);
insert into additional_services (name, price, deleted) values ('kablovska TV', 100, false);
insert into additional_services (name, price, deleted) values ('WiFi', 100, false);
insert into additional_services (name, price, deleted) values ('dru??tvene igre', 100, false);
insert into additional_services (name, price, deleted) values ('besplatne pe??a??ke ture', 100, false);
insert into additional_services (name, price, deleted) values ('bicikli', 100, false);

insert into additional_services_offer (offer_id, additional_service_id) values (1, 4);
insert into additional_services_offer (offer_id, additional_service_id) values (1, 5);
insert into additional_services_offer (offer_id, additional_service_id) values (1, 6);
insert into additional_services_offer (offer_id, additional_service_id) values (3, 3);
insert into additional_services_offer (offer_id, additional_service_id) values (3, 6);
insert into additional_services_offer (offer_id, additional_service_id) values (4, 4);
insert into additional_services_offer (offer_id, additional_service_id) values (4, 6);
insert into additional_services_offer (offer_id, additional_service_id) values (4, 7);
insert into additional_services_offer (offer_id, additional_service_id) values (5, 3);
insert into additional_services_offer (offer_id, additional_service_id) values (5, 4);
insert into additional_services_offer (offer_id, additional_service_id) values (5, 6);
insert into additional_services_offer (offer_id, additional_service_id) values (5, 7);
insert into additional_services_offer (offer_id, additional_service_id) values (6, 6);

insert into additional_services_offer (offer_id, additional_service_id) values (7, 2);
insert into additional_services_offer (offer_id, additional_service_id) values (7, 1);
insert into additional_services_offer (offer_id, additional_service_id) values (7, 4);
insert into additional_services_offer (offer_id, additional_service_id) values (8, 2);
insert into additional_services_offer (offer_id, additional_service_id) values (9, 1);
insert into additional_services_offer (offer_id, additional_service_id) values (9, 2);
insert into additional_services_offer (offer_id, additional_service_id) values (10, 2);

insert into additional_services_offer (offer_id, additional_service_id) values (2, 1);
insert into additional_services_offer (offer_id, additional_service_id) values (11, 1);
insert into additional_services_offer (offer_id, additional_service_id) values (11, 2);
insert into additional_services_offer (offer_id, additional_service_id) values (12, 2);
insert into additional_services_offer (offer_id, additional_service_id) values (12, 1);
insert into additional_services_offer (offer_id, additional_service_id) values (13, 2);
insert into additional_services_offer (offer_id, additional_service_id) values (14, 1);
insert into additional_services_offer (offer_id, additional_service_id) values (14, 7);

insert into behavior_rule (text) values ('zabranjeno pecanje');
insert into behavior_rule (text) values ('dozvoljeno pecanje');
insert into behavior_rule (text) values ('zabranjeno pu??enje');
insert into behavior_rule (text) values ('dozvoljeno pu??enje');
insert into behavior_rule (text) values ('dozvoljeno kupanje');
insert into behavior_rule (text) values ('zabranjeno kupanje');
insert into behavior_rule (text) values ('dozvoljeni ljubimci');
insert into behavior_rule (text) values ('zabranjeni ljubimci');

insert into behavior_rules_offer (offer_id, behavior_rule_id) values (2, 2);
insert into behavior_rules_offer (offer_id, behavior_rule_id) values (2, 4);
insert into behavior_rules_offer (offer_id, behavior_rule_id) values (2, 8);
insert into behavior_rules_offer (offer_id, behavior_rule_id) values (11, 2);
insert into behavior_rules_offer (offer_id, behavior_rule_id) values (11, 7);
insert into behavior_rules_offer (offer_id, behavior_rule_id) values (12, 2);
insert into behavior_rules_offer (offer_id, behavior_rule_id) values (12, 7);
insert into behavior_rules_offer (offer_id, behavior_rule_id) values (13, 2);
insert into behavior_rules_offer (offer_id, behavior_rule_id) values (13, 8);
insert into behavior_rules_offer (offer_id, behavior_rule_id) values (13, 4);
insert into behavior_rules_offer (offer_id, behavior_rule_id) values (14, 8);
insert into behavior_rules_offer (offer_id, behavior_rule_id) values (14, 2);

insert into behavior_rules_offer (offer_id, behavior_rule_id) values (7, 2);
insert into behavior_rules_offer (offer_id, behavior_rule_id) values (7, 4);
insert into behavior_rules_offer (offer_id, behavior_rule_id) values (8, 2);
insert into behavior_rules_offer (offer_id, behavior_rule_id) values (8, 4);
insert into behavior_rules_offer (offer_id, behavior_rule_id) values (9, 2);
insert into behavior_rules_offer (offer_id, behavior_rule_id) values (9, 4);
insert into behavior_rules_offer (offer_id, behavior_rule_id) values (10, 1);
insert into behavior_rules_offer (offer_id, behavior_rule_id) values (10, 3);
insert into behavior_rules_offer (offer_id, behavior_rule_id) values (10, 7);

insert into behavior_rules_offer (offer_id, behavior_rule_id) values (1, 3);
insert into behavior_rules_offer (offer_id, behavior_rule_id) values (1, 7);
insert into behavior_rules_offer (offer_id, behavior_rule_id) values (3, 3);
insert into behavior_rules_offer (offer_id, behavior_rule_id) values (3, 7);
insert into behavior_rules_offer (offer_id, behavior_rule_id) values (4, 3);
insert into behavior_rules_offer (offer_id, behavior_rule_id) values (4, 7);
insert into behavior_rules_offer (offer_id, behavior_rule_id) values (5, 3);
insert into behavior_rules_offer (offer_id, behavior_rule_id) values (5, 8);
insert into behavior_rules_offer (offer_id, behavior_rule_id) values (6, 8);
insert into behavior_rules_offer (offer_id, behavior_rule_id) values (6, 3);

insert into fishing_equipment (name) values('??tapovi');
insert into fishing_equipment (name) values('udice');
insert into fishing_equipment (name) values('mamac');
insert into fishing_equipment (name) values('??amac');

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



insert into image (path, offer_id) values('/Drina1.jpg', 2);
insert into image (path, offer_id) values('/Drina2.jpg', 2);
insert into image (path, offer_id) values('/Drina3.jpg', 2);

insert into experience_review (rate, status, text, client_id, offer_id, offer_type) values(5, 0, 'Prelepa tura, sve pohvale.', 1, 2, 2);


insert into experience_review (rate, status, text, client_id, offer_id, offer_type) values(4, 0, 'Prelepo, ali daleko.', 1, 1, 0);
insert into experience_review (rate, status, text, client_id, offer_id, offer_type) values(4, 1, 'Prelepo, ali daleko.', 1, 3, 0);
insert into experience_review (rate, status, text, client_id, offer_id, offer_type) values(3, 1, 'Prelepo.', 1, 4, 0);
insert into experience_review (rate, status, text, client_id, offer_id, offer_type) values(5, 1, 'Sve pohvale.', 1, 5, 0);
insert into experience_review (rate, status, text, client_id, offer_id, offer_type) values(5, 1, 'Sve pohvale,odli??no.', 1, 6, 0);

insert into experience_review (rate, status, text, client_id, offer_id, offer_type) values(5, 0, 'Sve pohvale,odli??no. Avantura je divna.', 1, 2, 2);


insert into experience_review (rate, status, text, client_id, offer_id, offer_type) values(4, 1, 'Prelepo, ali daleko.', 1, 1, 0);
insert into experience_review (rate, status, text, client_id, offer_id, offer_type) values(4, 1, 'Prelepo, ali daleko.', 1, 3, 0);
insert into experience_review (rate, status, text, client_id, offer_id, offer_type) values(3, 1, 'Prelepo.', 1, 4, 0);
insert into experience_review (rate, status, text, client_id, offer_id, offer_type) values(5, 1, 'Sve pohvale.', 1, 5, 0);
insert into experience_review (rate, status, text, client_id, offer_id, offer_type) values(5, 1, 'Sve pohvale,odli??no.', 1, 6, 0);
insert into experience_review (rate, status, text, client_id, offer_id, offer_type) values(5, 1, 'Sve pohvale,odli??no.', 1, 7, 1);
insert into experience_review (rate, status, text, client_id, offer_id, offer_type) values(3, 1, 'Bilo bi dobro da je hrana ukusnija.', 1, 7, 1);
insert into experience_review (rate, status, text, client_id, offer_id, offer_type) values(5, 1, 'Sve pohvale,odli??no.', 1, 8, 1);


--experience review za avanture
insert into experience_review (rate, status, text, client_id, offer_id, offer_type) values(5, 1, 'Sve pohvale.', 1, 2, 2);
insert into experience_review (rate, status, text, client_id, offer_id, offer_type) values(4, 1, 'Sve pohvale,odli??no.', 1, 2, 2);
insert into experience_review (rate, status, text, client_id, offer_id, offer_type) values(5, 1, 'Sve pohvale,odli??no.', 1, 11, 2);
insert into experience_review (rate, status, text, client_id, offer_id, offer_type) values(3, 1, 'Bilo bi dobro da je bolji ??ator.', 1, 12, 2);
insert into experience_review (rate, status, text, client_id, offer_id, offer_type) values(5, 1, 'Sve pohvale,odli??no.', 1, 13, 2);
insert into experience_review (rate, status, text, client_id, offer_id, offer_type) values(3, 1, 'Bilo bi dobro da je bolji ??ator.', 1, 14, 2);
insert into experience_review (rate, status, text, client_id, offer_id, offer_type) values(5, 1, 'Sve pohvale,odli??no.', 1, 14, 2);





insert into pricelist (end_date, price, start_date, offer_id) values (null, 5000, to_date('29.04.2022', 'DD.MM.YYYY'), 2);
insert into pricelist (end_date, price, start_date, offer_id) values (null, 3000, to_date('30.05.2022', 'DD.MM.YYYY'), 1);
insert into pricelist (end_date, price, start_date, offer_id) values (null, 3500, to_date('30.05.2022', 'DD.MM.YYYY'), 5);
insert into pricelist (end_date, price, start_date, offer_id) values (null, 3600, to_date('30.05.2022', 'DD.MM.YYYY'), 6);
insert into pricelist (end_date, price, start_date, offer_id) values (to_date('30.05.2022.', 'DD.MM.YYYY'), 4000, to_date('29.04.2022.', 'DD.MM.YYYY'), 7);
insert into pricelist (end_date, price, start_date, offer_id) values (null, 4000, to_date('30.05.2022', 'DD.MM.YYYY'), 7);
insert into pricelist (end_date, price, start_date, offer_id) values (null, 5000, to_date('29.04.2022', 'DD.MM.YYYY'), 8);
insert into pricelist (end_date, price, start_date, offer_id) values (null, 5000, to_date('29.04.2022', 'DD.MM.YYYY'), 9);
insert into pricelist (end_date, price, start_date, offer_id) values (null, 5000, to_date('29.04.2022', 'DD.MM.YYYY'), 10);
insert into pricelist (end_date, price, start_date, offer_id) values (null, 2000, to_date('29.04.2022', 'DD.MM.YYYY'), 11);
insert into pricelist (end_date, price, start_date, offer_id) values (null, 1234, to_date('29.04.2022', 'DD.MM.YYYY'), 12);
insert into pricelist (end_date, price, start_date, offer_id) values (null, 9876, to_date('29.04.2022', 'DD.MM.YYYY'), 13);
insert into pricelist (end_date, price, start_date, offer_id) values (null, 2500, to_date('29.04.2022', 'DD.MM.YYYY'), 14);


insert into image (path, offer_id) values ('/meda.jpg', 1);
insert into image (path, offer_id) values ('/meda1.jpg', 1);
insert into image (path, offer_id) values ('/meda2.jpg', 1);
--insert into image (path, offer_id) values ('/img/cottage/3/132308681.jpg', 2);
insert into image (path, offer_id) values ('/suma.jpg', 3);
insert into image (path, offer_id) values ('/suma1.jpg', 3);
insert into image (path, offer_id) values ('/kedar.jpg', 4);
insert into image (path, offer_id) values ('/kedar1.jpg', 4);
insert into image (path, offer_id) values ('/kedar2.jpg', 4);
insert into image (path, offer_id) values ('/holiday.jpg', 5);
insert into image (path, offer_id) values ('/holiday1.jpg', 5);
insert into image (path, offer_id) values ('/bogut.jpg', 6);
insert into image (path, offer_id) values ('/bogut1.jpg', 6);
insert into image (path, offer_id) values ('/bogut2.jpg', 6);

insert into image (path, offer_id) values ('/nimbus.jpg', 7);
insert into image (path, offer_id) values ('/nimbus1.jpg', 7);
insert into image (path, offer_id) values ('/nimbus2.jpg',7);
insert into image (path, offer_id) values ('/dionis.jpg',8);
insert into image (path, offer_id) values ('/dionis1.jpg',8);
insert into image (path, offer_id) values ('/party.jpg',9);
insert into image (path, offer_id) values ('/party2.jpg',9);
insert into image (path, offer_id) values ('/party1.jpg',9);
insert into image (path, offer_id) values ('/silverstar1.jpg',10);
insert into image (path, offer_id) values ('/silverstar.jpg',10);
insert into image (path, offer_id) values ('/238068376.jpg', 1);
insert into image (path, offer_id) values ('/132308681.jpg', 3);
insert into image (path, offer_id) values ('/331714665.jpg', 4);
insert into image (path, offer_id) values ('/169294777.jpg', 5);
insert into image (path, offer_id) values ('/331714665.jpg', 6);

insert into image (path, offer_id) values ('/41.jpg', 7);
insert into image (path, offer_id) values ('/31.jpg', 10);
insert into image (path, offer_id) values ('/21.jpg', 9);
insert into image (path, offer_id) values ('/11.jpg', 8);

insert into image (path, offer_id) values ('/206076_pecanje_zalazak_sunca.jpg', 13);
insert into image (path, offer_id) values ('/images.jpg', 14);
insert into image (path, offer_id) values ('/906z513_PECANJE2310pog.jpg', 11);
insert into image (path, offer_id) values ('/maxresdefault.jpg', 12);

insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
    (true, to_date('17.02.2022', 'DD.MM.YYYY'), 0, 50000, false, false, to_date('14.02.2022', 'DD.MM.YYYY'), 1, 4,false);
insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
    (false, to_date('21.04.2022', 'DD.MM.YYYY'), 0, 4000, false, false, to_date('18.04.2022', 'DD.MM.YYYY'), 1, 1,false);
insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
    (false, to_date('10.04.2022', 'DD.MM.YYYY'), 0, 4000, false, false, to_date('08.04.2022', 'DD.MM.YYYY'), 12, 1,false);
insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
    (false, to_date('30.04.2022', 'DD.MM.YYYY'), 0, 4000, false, false, to_date('28.04.2022', 'DD.MM.YYYY'), 13, 1,false);

insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
    (false, to_date('07.05.2022', 'DD.MM.YYYY'), 0, 4000, false, false, to_date('03.05.2022', 'DD.MM.YYYY'), 1, 1,false);
insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
    (false, to_date('09.05.2022', 'DD.MM.YYYY'), 2, 4000, false, false, to_date('08.05.2022', 'DD.MM.YYYY'), 13, 2,false);
insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
    (false, to_date('27.05.2022', 'DD.MM.YYYY'), 2, 5000, false, false, to_date('26.05.2022', 'DD.MM.YYYY'), 12, 2,false);
insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
    (false, to_date('29.08.2022', 'DD.MM.YYYY'), 0, 5000, false, false, to_date('28.08.2022', 'DD.MM.YYYY'), 13, 1,false);
insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
    (false, to_date('29.05.2022', 'DD.MM.YYYY'), 0, 5000, false, false, to_date('28.05.2022', 'DD.MM.YYYY'), 13, 1,false);
insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
    (false, to_date('26.06.2022', 'DD.MM.YYYY'), 0, 42542, false, false, to_date('20.06.2022', 'DD.MM.YYYY'), 1, 1,false);


insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
    (false, to_date('26.04.2022', 'DD.MM.YYYY'), 0, 4000, false, false, to_date('23.04.2022', 'DD.MM.YYYY'), 1, 3,false);
insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
    (false, to_date('03.05.2022', 'DD.MM.YYYY'), 0, 4000, false, false, to_date('01.05.2022', 'DD.MM.YYYY'), 12, 3,false);

insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
    (false, to_date('27.06.2022', 'DD.MM.YYYY'), 0, 4000, false, false, to_date('20.06.2022', 'DD.MM.YYYY'), 13, 4,false);

insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
    (false, to_date('17.01.2022', 'DD.MM.YYYY'), 0, 100, false, false, to_date('11.01.2022', 'DD.MM.YYYY'), 12, 5,false);
insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
    (false, to_date('14.05.2022', 'DD.MM.YYYY'), 0, 100, false, false, to_date('10.05.2022', 'DD.MM.YYYY'), 1, 5,false);

insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
    (false, to_date('05.04.2022', 'DD.MM.YYYY'), 0, 42542, false, false, to_date('01.04.2022', 'DD.MM.YYYY'), 13, 6,false);


insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
    (false, to_date('05.04.2022', 'DD.MM.YYYY'), 1, 4000, false, false, to_date('03.04.2022', 'DD.MM.YYYY'), 13, 7,false);
insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
    (false, to_date('06.05.2022', 'DD.MM.YYYY'), 1, 4000, false, false, to_date('03.05.2022', 'DD.MM.YYYY'), 1, 7,false);
insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
    (false, to_date('09.05.2022', 'DD.MM.YYYY'), 1, 4000, false, false, to_date('08.05.2022', 'DD.MM.YYYY'), 12, 7,false);

insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
    (false, to_date('02.06.2022', 'DD.MM.YYYY'), 1, 4000, false, false, to_date('01.06.2022', 'DD.MM.YYYY'), 1, 7,false);
insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
    (false, to_date('12.06.2022', 'DD.MM.YYYY'), 1, 4000, false, false, to_date('10.06.2022', 'DD.MM.YYYY'), 12, 7,false);
insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
    (false, to_date('29.06.2022', 'DD.MM.YYYY'), 1, 4000, false, false, to_date('28.06.2022', 'DD.MM.YYYY'), 13, 7, false);

insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
    (true, to_date('17.02.2022', 'DD.MM.YYYY'), 1, 50000, false, false, to_date('14.02.2022', 'DD.MM.YYYY'), 1, 8,false);
insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
    (true, to_date('20.02.2022', 'DD.MM.YYYY'), 1, 50000, false, false, to_date('19.02.2022', 'DD.MM.YYYY'), 12, 8, false);
insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
    (false, to_date('02.05.2022', 'DD.MM.YYYY'), 1, 4000, false, false, to_date('01.05.2022', 'DD.MM.YYYY'), 1, 8,false);
insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
    (false, to_date('07.05.2022', 'DD.MM.YYYY'), 1, 4000, false, false, to_date('04.05.2022', 'DD.MM.YYYY'), 13, 8,false);
insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
    (false, to_date('09.05.2022', 'DD.MM.YYYY'), 1, 4000, false, false, to_date('08.05.2022', 'DD.MM.YYYY'), 12, 8,false);

insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
    (false, to_date('17.01.2022', 'DD.MM.YYYY'), 1, 100, false, false, to_date('11.01.2022', 'DD.MM.YYYY'), 1, 9,false);
insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
    (false, to_date('17.01.2022', 'DD.MM.YYYY'), 1, 100, false, false, to_date('11.01.2022', 'DD.MM.YYYY'), 12, 9, false);
insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
    (false, to_date('07.06.2022', 'DD.MM.YYYY'), 1, 4000, false, false, to_date('04.06.2022', 'DD.MM.YYYY'), 13, 9,false);

insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
    (false, to_date('14.04.2022', 'DD.MM.YYYY'), 1, 42542, false, false, to_date('11.04.2022', 'DD.MM.YYYY'), 12, 10,false);
insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
    (false, to_date('03.07.2022', 'DD.MM.YYYY'), 1, 42542, false, false, to_date('01.07.2022', 'DD.MM.YYYY'), 13, 10, false);



insert into reservation(canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
    (false, to_date('07.04.2022', 'DD.MM.YYYY'), 2, 3000, false, false, to_date('04.04.2022', 'DD.MM.YYYY'), 1, 2, false);
insert into reservation(canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
    (false, to_date('24.04.2022', 'DD.MM.YYYY'), 2, 3000, false, false, to_date('22.04.2022', 'DD.MM.YYYY'), 13, 2, false);
insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id,ship_owner_present) values
    (false, to_date('03.06.2022', 'DD.MM.YYYY'), 2, 10000, false, false, to_date('01.06.2022', 'DD.MM.YYYY'), 12, 2,false);
insert into reservation (canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
    (false, to_date('06.06.2022', 'DD.MM.YYYY'), 2, 12000, false, false, to_date('05.06.2022', 'DD.MM.YYYY'), 1, 2, false);
insert into reservation(canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
    (false, to_timestamp('01.07.2022 20:20', 'DD.MM.YYYY HH24:MI'), 2, 3000, false, false, to_timestamp('01.07.2022 17:00', 'DD.MM.YYYY HH24:MI'), 13, 2, false);
insert into reservation(canceled, end_date, offer_type, price, quick, reviewed, start_date, client_id, offer_id, ship_owner_present) values
    (false, to_date('28.07.2022', 'DD.MM.YYYY'), 2, 3000, false, false, to_date('25.07.2022', 'DD.MM.YYYY'), 12, 2, false);




insert into registration_request (registration_type, status, user_id, version,request_message) values (0, 0, 3,1, 'Hocu da koristim aplikaciju');
insert into registration_request (registration_type, status, user_id, version,request_message) values (1, 0, 4,1, 'Imam entitete koje bih reklamisala ovde');



 insert into period_availability (end_date, start_date, offer_id) values (to_date('20.12.2022', 'DD.MM.YYYY'),to_date('01.01.2022', 'DD.MM.YYYY'),1);
 insert into period_unavailability (start_date, end_date, offer_id) values (to_date('15.05.2022', 'DD.MM.YYYY'),to_date('25.05.2022', 'DD.MM.YYYY'),1);


 insert into period_availability (start_date,end_date,  offer_id) values (to_date('20.01.2022', 'DD.MM.YYYY'),to_date('01.12.2022', 'DD.MM.YYYY'),3);
--insert into period_unavailability (start_date,end_date,  offer_id) values ('20.05.2022','30.05.2022',3);


insert into period_availability ( start_date,end_date, offer_id) values (to_date('20.01.2022', 'DD.MM.YYYY'),to_date('01.12.2022', 'DD.MM.YYYY'),4);
insert into period_unavailability (start_date,end_date,  offer_id) values (to_date('10.05.2022', 'DD.MM.YYYY'),to_date('30.05.2022', 'DD.MM.YYYY'),4);


insert into period_availability (start_date, end_date,  offer_id) values (to_date('20.01.2022', 'DD.MM.YYYY'),to_date('01.12.2022', 'DD.MM.YYYY'),5);
--insert into period_unavailability (start_date, end_date, offer_id) values ('20.06.2022','22.06.2022',5);
--insert into period_unavailability (start_date, end_date, offer_id) values ('20.05.2022','22.05.2022',5);


insert into complaint (version, offer_type, status, text, client_id, offer_id, reservation_id) values (0, 0, 0, 'Vikkendica se ne greje tokom proleca i prilicno je hladno.', 1, 4, 2);
insert into complaint (version, offer_type, status, text, client_id, offer_id, reservation_id) values (0, 0, 0, 'Internet je jako los, spor i ne moze se koristiti.', 1, 5, 3);

insert into period_unavailability (start_date, end_date, offer_id) values (to_timestamp('03.06.2022 20:00', 'DD.MM.YYYY HH24:MI'), to_timestamp('06.06.2022 08:00', 'DD.MM.YYYY HH24:MI'), 2);

-- samo zbog testiranja komentarisem
--insert into delete_request (version, status, text, user_id) values (0, 0, 'Molim da mi se obrise nalog jer se vise ne bavim ovim poslom.', 2);
insert into delete_request (version, status, text, user_id) values (0, 0, 'Zelim da obrisem nalog.', 3);
insert into delete_request (version, status, text, user_id) values (0, 0, 'Molim da mi se obrise nalog.', 5);


insert into period_availability (end_date, start_date, offer_id) values (to_date('20.12.2022', 'DD.MM.YYYY'),to_date('01.01.2022', 'DD.MM.YYYY'),7);
insert into period_unavailability (start_date, end_date, offer_id) values (to_date('15.05.2022', 'DD.MM.YYYY'),to_date('25.05.2022', 'DD.MM.YYYY'),7);

insert into additional_services_sale_appointment (sale_appointment_id, additional_service_id) values (1, 2);
insert into additional_services_sale_appointment (sale_appointment_id, additional_service_id) values (1, 4);
insert into additional_services_sale_appointment (sale_appointment_id, additional_service_id) values (1, 9);
insert into additional_services_sale_appointment (sale_appointment_id, additional_service_id) values (2, 5);
insert into additional_services_sale_appointment (sale_appointment_id, additional_service_id) values (2, 6);



insert into additional_services_sale_appointment (sale_appointment_id, additional_service_id) values (3, 2);
insert into additional_services_sale_appointment (sale_appointment_id, additional_service_id) values (4, 4);
insert into additional_services_sale_appointment (sale_appointment_id, additional_service_id) values (6, 9);
insert into additional_services_sale_appointment (sale_appointment_id, additional_service_id) values (5, 5);
insert into additional_services_sale_appointment (sale_appointment_id, additional_service_id) values (2, 6);


insert into period_availability (end_date, start_date, offer_id) values (to_date('20.12.2022', 'DD.MM.YYYY'),to_date('01.01.2022', 'DD.MM.YYYY'),2);

insert into subscriptions (client_id, offer_id) values (1,7);
insert into subscriptions (client_id, offer_id) values (1,2);

insert into percentage_of_earnings (start_date, end_date, percentage) values (to_date('01.01.2022', 'DD.MM.YYYY'), null, 3);

insert into pricelist (end_date, price, start_date, offer_id) values (null, 1500, to_date('29.04.2022', 'DD.MM.YYYY'), 3);
insert into pricelist (end_date, price, start_date, offer_id) values (null, 4000, to_date('29.04.2022', 'DD.MM.YYYY'), 4);

 insert into period_availability (end_date, start_date, offer_id) values (to_date('20.12.2022', 'DD.MM.YYYY'),to_date('01.01.2022', 'DD.MM.YYYY'),8);
 insert into period_availability (end_date, start_date, offer_id) values (to_date('20.12.2022', 'DD.MM.YYYY'),to_date('01.01.2022', 'DD.MM.YYYY'),9);

insert into loyalty_scale(discount, start_date, end_date, trashhold, user_type, role_id) values (0, to_date('01.01.2022', 'DD.MM.YYYY'), null, 0, 0, 1);
insert into loyalty_scale(discount, start_date, end_date, trashhold, user_type, role_id) values (2, to_date('01.01.2022', 'DD.MM.YYYY'), null, 20, 1, 1);
insert into loyalty_scale(discount, start_date, end_date, trashhold, user_type, role_id) values (3, to_date('01.01.2022', 'DD.MM.YYYY'), null, 30, 2, 1);


insert into loyalty_scale(discount, start_date, end_date, trashhold, user_type, role_id) values (0, to_date('01.01.2022', 'DD.MM.YYYY'), null, 0, 0, 3);
insert into loyalty_scale(discount, start_date, end_date, trashhold, user_type, role_id) values (2, to_date('01.01.2022', 'DD.MM.YYYY'), null, 40, 1, 3);
insert into loyalty_scale(discount, start_date, end_date, trashhold, user_type, role_id) values (3, to_date('01.01.2022', 'DD.MM.YYYY'), null, 60, 2, 3);


insert into loyalty_scale(discount, start_date, end_date, trashhold, user_type, role_id) values (0, to_date('01.01.2022', 'DD.MM.YYYY'), null, 0, 0, 4);
insert into loyalty_scale(discount, start_date, end_date, trashhold, user_type, role_id) values (2, to_date('01.01.2022', 'DD.MM.YYYY'), null, 45, 1, 4);
insert into loyalty_scale(discount, start_date, end_date, trashhold, user_type, role_id) values (3, to_date('01.01.2022', 'DD.MM.YYYY'), null, 65, 2, 4);

insert into loyalty_scale(discount, start_date, end_date, trashhold, user_type, role_id) values (0, to_date('01.01.2022', 'DD.MM.YYYY'), null, 0, 0, 5);
insert into loyalty_scale(discount, start_date, end_date, trashhold, user_type, role_id) values (2, to_date('01.01.2022', 'DD.MM.YYYY'), null, 25, 1, 5);
insert into loyalty_scale(discount, start_date, end_date, trashhold, user_type, role_id) values (3, to_date('01.01.2022', 'DD.MM.YYYY'), null, 35, 2, 5);


insert into loyalty_points(start_date, end_date, points, user_type_loyalty_points) values (to_date('01.01.2022', 'DD.MM.YYYY'), null, 3, 0);
insert into loyalty_points(start_date, end_date, points, user_type_loyalty_points) values (to_date('01.01.2022', 'DD.MM.YYYY'), null, 5, 1);


insert into reservation_report(version, report_text, penallty, reservation_id, client_id, unarrived_client, status) values (0, 'Neprijatni gosti, prilicno neuredni, ostavlisi su haos.', true, 6, 1, false, 0);
insert into reservation_report(version, report_text, penallty, reservation_id, client_id, unarrived_client, status) values (0, 'GOst se nije pojavio.', false, 10, 1, true, 0);





insert into registration_request (registration_type, status, user_id, version,request_message) values (2, 0, 9,1, 'Avanturiste');