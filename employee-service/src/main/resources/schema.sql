DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS address;

CREATE TABLE address (
	id INT NOT NULL AUTO_INCREMENT,
    street VARCHAR(50),
    zipcode VARCHAR(9) NOT NULL,
    number SMALLINT NOT NULL,
    neighborhood VARCHAR(50),
    city VARCHAR(100),
    state VARCHAR(3),
    PRIMARY KEY (id)
);

CREATE TABLE employee (
	id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    cpf VARCHAR(12) NOT NULL UNIQUE,
    role ENUM('INTERN', 'JUNIOR', 'PLAN', 'SENIOR') NOT NULL,
    salary_base FLOAT,
    id_address INT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (id_address) REFERENCES address(id)
);