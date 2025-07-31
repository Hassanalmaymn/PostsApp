CREATE TABLE role (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50)
);
CREATE TABLE user_role(
user_id BIGINT,
role_id BIGINT,
PRIMARY KEY (user_id, role_id),
  FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
  FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE

);
CREATE TABLE privilege (
id BIGINT PRIMARY KEY AUTO_INCREMENT,
privilege VARCHAR(50)
);
CREATE TABLE role_privilege(
role_id BIGINT,
privilege_id BIGINT,
PRIMARY KEY (role_id, privilege_id)
);

