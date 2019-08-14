DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS user;

CREATE TABLE user (
  id BIGINT PRIMARY KEY,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  CONSTRAINT uniq_username UNIQUE (username)
);

CREATE TABLE user_roles (
  user_id BIGINT NOT NULL,
  role VARCHAR(10) NOT NULL,
  CONSTRAINT uniq_user_id_role UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES user(id)
)

