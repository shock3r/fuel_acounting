drop table if exists transport;
drop table if exists fuel;

CREATE TABLE fuel (
  fuel_id   INTEGER NOT NULL AUTO_INCREMENT,
  fuel_name VARCHAR(30) NOT NULL,
  PRIMARY KEY (fuel_id)
);

CREATE TABLE transport (
  transport_id    INTEGER NOT NULL AUTO_INCREMENT,
  transport_name VARCHAR(30) NOT NULL,
  fuel_id   INTEGER NOT NULL,
  transport_tank_capasity INTEGER(3) NOT NULL DEFAULT 0,
  transport_date Date NOT NULL,
  PRIMARY KEY (transport_id),
  CONSTRAINT transport_to_fuel_fk
	FOREIGN KEY (fuel_id)
	REFERENCES fuel (fuel_id)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
);