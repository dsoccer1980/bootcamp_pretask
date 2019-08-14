DELETE FROM user_roles;
DELETE FROM user;

INSERT INTO user VALUES(1, "user", "$2a$10$9vBkM90.Nc5WfI9MmGmCv.h2zxTn/imIq2n1HD/9w8/CNMRrn3oN.");
INSERT INTO user VALUES(2, "admin", "$2a$10$9vBkM90.Nc5WfI9MmGmCv.h2zxTn/imIq2n1HD/9w8/CNMRrn3oN.");


INSERT INTO user_roles VALUES (1, "USER");
INSERT INTO user_roles VALUES (2, "ADMIN");

