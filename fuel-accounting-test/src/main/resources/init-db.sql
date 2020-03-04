INSERT INTO fuel (fuel_id, fuel_name) VALUES (1, 'Gasoline');
INSERT INTO fuel (fuel_id, fuel_name) VALUES (2, 'Disel');

--INSERT INTO transport (transport_id, transport_name, fuel_id, fuel_tank_capasity, fuel_date) VALUES (1, 'Renault Megane', 1, 45, TO_DATE('17.12.2020', 'DD.MM.YYYY'));
INSERT INTO transport (transport_id, transport_name, fuel_id, transport_tank_capasity, transport_date) VALUES (1, 'Renault Megane', 1, 45, '2020.01.01');
INSERT INTO transport (transport_id, transport_name, fuel_id, transport_tank_capasity, transport_date) VALUES (2, 'BMW X5', 2, 63, '2020.01.04');
INSERT INTO transport (transport_id, transport_name, fuel_id, transport_tank_capasity, transport_date) VALUES (3, 'VW Golf', 2, 51, '2020.02.01');