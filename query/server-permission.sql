DROP USER 'server'@'localhost';
CREATE USER 'server'@'localhost' IDENTIFIED BY 'NhduD2Yda5X2mt5mVJQV';
GRANT ALL PRIVILEGES ON testingdb.* TO 'server'@'localhost';
FLUSH PRIVILEGES;