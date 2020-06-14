DROP DATABASE IF EXISTS accountmanager;
CREATE DATABASE accountmanager;
GRANT ALL PRIVILEGES ON accountmanager.* TO 'mobiuser'@'%' IDENTIFIED BY 'demo';