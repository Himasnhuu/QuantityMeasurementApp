CREATE TABLE quantity_measurement(
	id INT AUTO_INCREMENT PRIMARY KEY,
	operation VARCHAR(50),
	operand1 VARCHAR(100),
	operand2 VARCHAR(100),
	result VARCHAR(100),
	error VARCHAR(255)
);