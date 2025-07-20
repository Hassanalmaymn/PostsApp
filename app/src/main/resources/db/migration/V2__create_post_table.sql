CREATE TABLE post (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT,
    title VARCHAR(255),
    content TEXT,
    FOREIGN KEY (user_id) REFERENCES user(id)
);