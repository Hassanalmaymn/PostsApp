ALTER TABLE role_privilege
ADD CONSTRAINT fk_role
FOREIGN KEY (role_id) REFERENCES role(id);

ALTER TABLE role_privilege
ADD CONSTRAINT fk_privilege
FOREIGN KEY (privilege_id) REFERENCES privilege(id);
