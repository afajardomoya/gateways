INSERT INTO gateway (serial_number, name, ip_address) VALUES ('xzc-2366-89987', 'gateway 1', '192.168.26.1');
INSERT INTO gateway (serial_number, name, ip_address) VALUES ('kjl-2366-89987', 'gateway 2', '192.168.26.2');

INSERT INTO device (uid, vendor, created_at, status, gateway_id) VALUES (11111, 'cisco', '2020-01-10', 'ONLINE', 1);
INSERT INTO device (uid, vendor, created_at, status, gateway_id) VALUES (22222, 'cisco', '2020-01-10', 'OFFLINE', 1);
INSERT INTO device (uid, vendor, created_at, status, gateway_id) VALUES (33333, 'cisco', '2020-01-10', 'ONLINE', 1);


INSERT INTO device (uid, vendor, created_at, status, gateway_id) VALUES (44444, 'huawei', '2020-01-10', 'OFFLINE', 2);
INSERT INTO device (uid, vendor, created_at, status, gateway_id) VALUES (55555, 'huawei', '2020-01-10', 'ONLINE', 2);
INSERT INTO device (uid, vendor, created_at, status, gateway_id) VALUES (66666, 'huawei', '2020-01-10', 'OFFLINE', 2);
