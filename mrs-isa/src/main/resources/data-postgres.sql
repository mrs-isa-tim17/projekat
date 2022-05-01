
insert into place (country, place_name, post_number) values ('Serbia', 'Novi Sad', 21000);
insert into place (country, place_name, post_number) values ('Serbia', 'Bajina Basta', 507725);

insert into address (serial_number, street_name, place_id) values
	('11B', 'Jovan Jovanovic Zmaj Street', 1);
insert into address (serial_number, street_name, place_id) values
	('27', 'Gogoljeva Street', 2);
insert into address (serial_number, street_name, place_id) values
	('13A', 'Tolstojeva Street', 1);
	
insert into users (type, id, deleted, email, loyalty_points, name, password,
	phonenumber,  surname, user_type, address_id) values 
	('CO', nextval('user_seq'), false, 'marko.markovi@example.com', 0, 'Marko', '1',
	'+3815828', 'Markovic', 0,1);
insert into users (type, id, deleted, email, loyalty_points, name, password,
	phonenumber, surname, user_type, address_id) values 
	('FI', nextval('user_seq'), false, 'milan.milanovi@example.com', 0, 'Milan', '2',
	'+3817885', 'Milanovic', 0, 1);
--*/
insert into calendar (id) values (1);
insert into calendar (id) values (2);

insert into cottage (id, deleted,description, name, address_id, calendar_id, bed_quantity, room_quantity, owner) values
	(nextval('offer_seq'), false, 'Best cottage ever', 'Cottage on the Lake', 1, 1, 4, 2, 1);

insert into adventure (id, deleted, description, name, address_id, calendar_id, capacity, instructor_biography, owner) values
	(nextval('offer_seq'), false, 'Best adventure ever', 'Fishing on the Lake', 2, 2, 8, 'Im the best instructor ever', 2);
	

insert into additional_services (name, reservation_id, sale_appointment_id) values ('wifi', null, null);
insert into additional_services (name, reservation_id, sale_appointment_id) values ('kabanica', null, null);

insert into additional_services_offer (offer_id, additional_service_id) values (2, 1);
insert into additional_services_offer (offer_id, additional_service_id) values (2, 2);


insert into behavior_rule (text) values ('dozvoljeno pecanje');
insert into behavior_rule (text) values ('volimo ljubimce');

insert into behavior_rules_offer (offer_id, behavior_rule_id) values (2, 1);
insert into behavior_rules_offer (offer_id, behavior_rule_id) values (2, 2);
insert into behavior_rules_offer (offer_id, behavior_rule_id) values (1, 1);

insert into fishing_equipment (name, ship_id) values('štapovi', null);
insert into fishing_equipment (name, ship_id) values('udice', null);

insert into fishing_equipments_offer (adventure_id, fishing_equipment_id) values (2, 1);
insert into fishing_equipments_offer (adventure_id, fishing_equipment_id) values (2, 2);


insert into cancel_condition (days, precent, offer_id) values (5, 20, 1);
insert into cancel_condition (days, precent, offer_id) values (5, 20, 2);

insert into image (path, offer_id) values('@/assets/img/adventure/Drina1.jpg', 2);
insert into image (path, offer_id) values('@/assets/img/adventure/Drina2.jpg', 2);
insert into image (path, offer_id) values('@/assets/img/adventure/Drina3.jpg', 2);

insert into experience_review (rate, status, text, client_id, offer_id) values(5, 1, 'Prelepa tura, sve pohvale.', 1, 2);

insert into pricelist (end_date, price, start_date, offer_id) values ('01.05.2022.', 12000, '29.04.2022.', 2);