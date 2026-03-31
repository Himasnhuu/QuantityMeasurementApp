CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255),
    provider VARCHAR(50)
);

CREATE TABLE quantity_measurement(
 id INT AUTO_INCREMENT PRIMARY KEY,
 operation VARCHAR(50),
 operand1 VARCHAR(100),
 operand2 VARCHAR(100),
 result VARCHAR(100),
 error VARCHAR(255),

 user_id BIGINT,
 FOREIGN KEY (user_id) REFERENCES users(id)
);