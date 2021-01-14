## Use to run mysql db docker image, optional if you're not using a local mysqldb
# docker run --name mysqldb -p 3306:3306 -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -d mysql
# docker run --name mysqldb -p 3306:3306 -e MYSQL_ROOT_PASSWORD=password -d mysql
# connect to mysql and run as root user

#Create Databases
CREATE DATABASE pp_dev;
CREATE DATABASE pp_prod;

#Create database service accounts
CREATE USER 'pp_dev_user'@'localhost' IDENTIFIED BY 'petproject';
CREATE USER 'pp_prod_user'@'localhost' IDENTIFIED BY 'petproject';
CREATE USER 'pp_dev_user'@'%' IDENTIFIED BY 'petproject';
CREATE USER 'pp_prod_user'@'%' IDENTIFIED BY 'petproject';

#Database grants
GRANT SELECT ON pp_dev.* to 'pp_dev_user'@'localhost';
GRANT INSERT ON pp_dev.* to 'pp_dev_user'@'localhost';
GRANT DELETE ON pp_dev.* to 'pp_dev_user'@'localhost';
GRANT UPDATE ON pp_dev.* to 'pp_dev_user'@'localhost';
GRANT SELECT ON pp_prod.* to 'pp_prod_user'@'localhost';
GRANT INSERT ON pp_prod.* to 'pp_prod_user'@'localhost';
GRANT DELETE ON pp_prod.* to 'pp_prod_user'@'localhost';
GRANT UPDATE ON pp_prod.* to 'pp_prod_user'@'localhost';
GRANT SELECT ON pp_dev.* to 'pp_dev_user'@'%';
GRANT INSERT ON pp_dev.* to 'pp_dev_user'@'%';
GRANT DELETE ON pp_dev.* to 'pp_dev_user'@'%';
GRANT UPDATE ON pp_dev.* to 'pp_dev_user'@'%';
GRANT SELECT ON pp_prod.* to 'pp_prod_user'@'%';
GRANT INSERT ON pp_prod.* to 'pp_prod_user'@'%';
GRANT DELETE ON pp_prod.* to 'pp_prod_user'@'%';
GRANT UPDATE ON pp_prod.* to 'pp_prod_user'@'%';