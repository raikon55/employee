INSERT INTO address (street, zipcode, number, neighborhood, city, state, created_at, updated_at, deleted_at)
    VALUES ('Rua Adauto Feitosa', '31260340', 1, 'Dona Clara', 'Belo Horizonte', 'MG', NOW(), NULL, NULL);
INSERT INTO address (street, zipcode, number, neighborhood, city, state, created_at, updated_at, deleted_at)
    VALUES ('Rua Crisogno Goulart', '31340260', 1, 'Paqueta', 'Belo Horizonte', 'MG', NOW(), NULL, NULL);
INSERT INTO address (street, zipcode, number, neighborhood, city, state, created_at, updated_at, deleted_at)
    VALUES ('Rua Uba', '31110110', 1, 'Colegio Batista', 'Belo Horizonte', 'MG', NOW(), NULL, NULL);

INSERT INTO employee (name, cpf, role, salary_base, id_address, created_at, updated_at, deleted_at)
    VALUES ('Urruin', '20956632254', 'INTERN', 1000.0, 1, NOW(), NULL, NULL);
INSERT INTO employee (name, cpf, role, salary_base, id_address, created_at, updated_at, deleted_at)
    VALUES ('Dolerfe', '46259637292', 'PLAN', 1000.0, 2, NOW(), NULL, NULL);
INSERT INTO employee (name, cpf, role, salary_base, id_address, created_at, updated_at, deleted_at)
    VALUES ('Cuxu', '96962829230', 'SENIOR', 1000.0, 3, NOW(), NULL, NULL);