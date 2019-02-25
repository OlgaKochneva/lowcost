INSERT INTO USERS (active, birthday, document_info, first_name, last_name, password, username, id)
  values (true, '2000-06-05 00:00:00', 'docs', 'Admin', 'Admin', 'PASSWORD', 'EMAIL@MAIL.COM', 1);
INSERT INTO USERS (active, birthday, document_info, first_name, last_name, password, username, id)
  values (true, '2000-06-04 00:00:00', 'docs', 'User', 'User', 'PASSWORD', 'EMAIL@MAIL.COM', 2);

insert into user_role (user_id, roles)
  values (1, 'ROLE_ADMIN');
insert into user_role (user_id, roles)
  values (1, 'ROLE_USER');
insert into user_role (user_id, roles)
  values (2, 'ROLE_USER');

