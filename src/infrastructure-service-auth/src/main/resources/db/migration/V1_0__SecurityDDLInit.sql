--User Table
create table _users (
oid varchar(36),
mandant varchar(20),
user_enabled boolean,
user_username varchar(255) not null UNIQUE ,
user_password varchar(255),
user_email varchar(255),
user_birthdate date,
user_forname varchar(255),
user_surname varchar(255),
primary key (oid));

--Authority Table
create table _authorities (
oid varchar(36),
auth_authority varchar(255),
primary key (oid));

--Permission Table
create table _permissions (
oid varchar(36),
perm_permission varchar(255),
primary key (oid));

--users_authorities Table: Join Table between User and Authority
create table _users_authorities (
user_oid varchar(36),
authority_oid varchar(36),
primary key (authority_oid, user_oid));

alter table _users_authorities
add constraint FK_users_authorities_TO_users
foreign key (user_oid)
references _users;

alter table _users_authorities
add constraint FK_users_authorities_TO_authorities
foreign key (authority_oid)
references _authorities;

--Authority-Permission Table: Join Table between Authority and Permission
create table _authorities_permissions (
authority_oid varchar(36),
permission_oid varchar(36),
primary key (authority_oid, permission_oid));

alter table _authorities_permissions
add constraint FK_authorities_permissions_TO_authorities
foreign key (authority_oid)
references _authorities;

alter table _authorities_permissions
add constraint FK_authorities_permissions_TO_permissions
foreign key (permission_oid)
references _permissions;

