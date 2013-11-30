#Display schema

# --- !Ups

CREATE SEQUENCE display_id_seq;
CREATE TABLE display (
	id integer NOT NULL DEFAULT nextval('display_id_seq'),
	name varchar(255),
	ip_screen_address varchar(100)
);

# --- !Downs

DROP TABLE display;

DROP SEQUENCE display_id_seq;


