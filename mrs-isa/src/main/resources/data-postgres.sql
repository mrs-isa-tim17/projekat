
insert into place (country, place_name, post_number) values ('Serbia', 'Novi Sad', 21000);

insert into address (serial_number, street_name, place_id) values
	('11B', 'Jovan Jovanovic Zmaj Street', 1);

	
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
	(nextval('offer_seq'), false, 'Best adventure ever', 'Fishing on the Lake', 1, 2, 8, 'Im the best instructor ever', 2);
	

insert into additional_services (name, offer_id, reservation_id, sale_appointment_id) values ('wifi', '1', null, null);

insert into behavior_rule (text, offer_id) values ('Not allowed smoking', 1);

insert into cancel_condition (days, precent, offer_id) values (5, 20, 1);

