-----------
CREATION BD
-----------

create schema FORUMDB;

create table FORUMDB.users(

	id integer generated always as identity primary key,
	username varchar(255),
	password varchar(255),
	email varchar(255),
	enabled boolean not null
);

create table FORUMDB.authorities(
	
	id integer generated always as identity primary key,
	user_id integer,
	authority varchar(255)
);

create table FORUMDB.threads(

	id integer generated always as identity primary key,
	user_id integer,
	title varchar(255),
	content varchar(3000),
	enabled boolean not null
);

create table FORUMDB.comments(

	id integer generated always as identity primary key,
	user_id integer,
	thread_id integer,
	content varchar(3000),
	enabled boolean not null

);

ALTER TABLE authorities ADD FOREIGN KEY (user_id) REFERENCES users(id);
ALTER TABLE threads ADD FOREIGN KEY (user_id) REFERENCES users(id);
ALTER TABLE comments ADD FOREIGN KEY (user_id) REFERENCES users(id);
ALTER TABLE comments ADD FOREIGN KEY (thread_id) REFERENCES threads(id);

insert into users(username,password,email,enabled) values ('ccvf2s','$2a$10$9ovSRF6b0KRRWDDe5z3ZjODbo6s4rGZUFEbePY12s7mJBWq7.d8/6','ccvf2s@ccvf2s.com',true);
insert into users(username,password,email,enabled) values ('ccvf2s_admin','$2a$10$8ej.zFtxOEkbciy6uqNSmOFE7RSRERAOwuVVTKh7ixQ0bGLpmyRea','ccvf2s@ccvf2sadmin.com',true);
insert into authorities(user_id,authority) values (1,'ROLE_USER');
insert into authorities(user_id,authority) values (2,'ROLE_ADMIN');
insert into authorities(user_id,authority) values (2,'ROLE_USER');