localhost:1974/demo/all

git show 9dfff735914dc82616488ee4a1a880e16685903d:app.js > D:\GIT-REPO-INTELLIJ\MyUnibet\app.js
git show 9dfff735914dc82616488ee4a1a880e16685903d:README.md > D:\GIT-REPO-INTELLIJ\MyUnibet\README.md

git show 9dfff735914dc82616488ee4a1a880e16685903d:app.json> D:\GIT-REPO-INTELLIJ\MyUnibet\app.json

pour une release =>
git tag -a v1.3 -m "React Redux CRUD App selon rajaraodv"
committer sur IntelliJ

pour tester sur http://myccah.claurier.com/ =>
lancer mongodb
terminal 1 , faire npm start

On a actuellement du hot reloading , contrairement à l'appli originale, react-redux-registration-username-example-master, mais on a pas de debug.

CREATE DATABASE `myunibet` /*!40100 DEFAULT CHARACTER SET utf8 */;


INSERT INTO USER (ID, DATE_CREATION, USERNAME, PASSWORD,  EMAIL, ENABLED, LASTPASSWORDRESETDATE) VALUES (1, CURDATE() -1, 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi',  'admin@admin.com', 1, STR_TO_DATE('01/01/2018', '%c/%d/%Y'));


INSERT INTO USER (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE) VALUES (1, 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 'admin', 'admin@admin.com', 1, STR_TO_DATE('01/01/2016', '%c/%d/%Y'));
INSERT INTO USER (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE) VALUES (2, 'user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'enabled@user.com', 1, STR_TO_DATE('01/01/2016','%c/%d/%Y'));
INSERT INTO USER (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE) VALUES (3, 'disabled', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'disabled@user.com', 0, STR_TO_DATE('01/01/2016','%c/%d/%Y'));

INSERT INTO AUTHORITY (ID, NAME) VALUES (1, 'ROLE_USER');
INSERT INTO AUTHORITY (ID, NAME) VALUES (2, 'ROLE_ADMIN');

INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (1, 1);
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (1, 2);
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (2, 1);
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (3, 1);


// AuthorityName = Role dans isotope

