insert into users(id, username, password, active, first_name, last_name, document_info, birthday)
values (1, 'EMAIL@MAIL.COM', 'PASSWORD', true, 'admin', 'admin', '1221', '2000-06-05T00:00:00'),
       (2, 'ExampleEmail2@google.com', 'ExamplePassword2', true, 'user', 'user', '1221', '2000-06-05T00:00:00');

insert into user_role (user_id, roles)
values (1, 'ROLE_ADMIN'),
       (1, 'ROLE_USER'),
       (2, 'ROLE_USER');