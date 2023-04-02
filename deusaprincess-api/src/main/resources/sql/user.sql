CREATE TABLE tb_functionality (
                                  uuid UUID NOT NULL PRIMARY KEY,
                                  endpoint VARCHAR(255) NOT NULL,
                                  description VARCHAR(255) NOT NULL,
                                  method VARCHAR(10) NOT NULL

);

CREATE TABLE tb_profile (
                            uuid UUID NOT NULL PRIMARY KEY,
                            name VARCHAR(255) NOT NULL
);

CREATE TABLE tb_profile_functionality (
                                          uuid_profile UUID NOT NULL,
                                          uuid_functionality UUID NOT NULL,
                                          FOREIGN KEY (uuid_profile) REFERENCES tb_profile(uuid),
                                          FOREIGN KEY (uuid_functionality) REFERENCES tb_functionality(uuid)
);

CREATE TABLE tb_user (
                         cpf BIGINT PRIMARY KEY,
                         email VARCHAR(255) UNIQUE NOT NULL,
                         name VARCHAR(255) NOT NULL,
                         password VARCHAR(255) NOT NULL,
                         birthdate DATE NOT NULL,
                         phone VARCHAR(20) NOT NULL,
                         active BOOLEAN NOT NULL
);

CREATE TABLE tb_address (
                            uuid UUID PRIMARY KEY,
                            street VARCHAR(255) NOT NULL,
                            number VARCHAR(10) NOT NULL,
                            district VARCHAR(255) NOT NULL,
                            city VARCHAR(255) NOT NULL,
                            state VARCHAR(2) NOT NULL,
                            zipCode VARCHAR(8) NOT NULL
);

CREATE TABLE tb_user_address (
                                 cpf BIGINT NOT NULL,
                                 uuid_address UUID NOT NULL,
                                 FOREIGN KEY (cpf) REFERENCES tb_user(cpf),
                                 FOREIGN KEY (uuid_address) REFERENCES tb_address(uuid)
);

CREATE TABLE tb_user_profile (
                                 cpf BIGINT NOT NULL,
                                 uuid_profile UUID NOT NULL,
                                 FOREIGN KEY (cpf) REFERENCES tb_user(cpf),
                                 FOREIGN KEY (uuid_profile) REFERENCES tb_profile(uuid)
);

INSERT INTO tb_profile (uuid, name) VALUES ('6a44a5d5-6c1b-41f2-8f36-c3692c7e5b47', 'ADMIN');
INSERT INTO tb_profile (uuid, name) VALUES ('da1eaf42-8a77-4e26-bf10-1c71ea9ab4e4', 'SUPPLIER');
INSERT INTO tb_profile (uuid, name) VALUES ('3a331ef1-5062-4f9d-9d1c-28fa12f3ec09', 'CUSTOMER');

INSERT INTO tb_address (uuid, street, number, district, city, state, zipCode)
VALUES ('d235ff6c-7a4a-4c3f-a4c4-73a54cf1eb12', 'Rua das Flores', '123', 'Centro', 'São Paulo', 'SP', '01234567');

INSERT INTO tb_user (cpf, active, phone, email, name, password, birthdate)
VALUES (12345678901, true, '99999999999', 'usuario@teste.com', 'Usuario Teste', '$2a$10$NGL42HaCQChMcSp.sfRwxe1ZipnFO/C26iNFqYATk7UDdvNeJSS8K', '1990-01-01');

INSERT INTO tb_user_profile (cpf, uuid_profile)
VALUES (12345678901, '6a44a5d5-6c1b-41f2-8f36-c3692c7e5b47');

INSERT INTO tb_user_address (cpf, uuid_address)
VALUES (12345678901, 'd235ff6c-7a4a-4c3f-a4c4-73a54cf1eb12');

INSERT INTO tb_functionality (uuid, endpoint, description, method) VALUES ('f8aaee7f-c3af-4f7f-9532-b8e7b4c2d51d', '/user', 'Create User', 'POST');

INSERT INTO tb_functionality (uuid, endpoint, description, method) VALUES ('d9de42f6-8b53-4e26-aa4c-4b4d8ce2cfb4', '/user/{cpf}', 'Get User by CPF', 'GET');

INSERT INTO tb_functionality (uuid, endpoint, description, method) VALUES ('6eefc9d1-1c8e-471a-9680-f15df0b487d7', '/user/email/{email}', 'Get User by Email', 'GET');

INSERT INTO tb_functionality (uuid, endpoint, description, method) VALUES ('58c46f03-83d3-4415-a5a5-b5a5f5e97a5a', '/user', 'Update User', 'PUT');

INSERT INTO tb_functionality (uuid, endpoint, description, method) VALUES ('e6892f13-540d-48c1-a698-42cbb0c679df', '/user/{cpf}', 'Delete User', 'DELETE');

INSERT INTO tb_functionality (uuid, endpoint, description, method) VALUES ('9a7eaf71-3224-4dd4-b5d2-35f890b8c49a', '/user', 'Get All Users', 'GET');

INSERT INTO tb_profile_functionality (uuid_profile, uuid_functionality)
VALUES ('6a44a5d5-6c1b-41f2-8f36-c3692c7e5b47', 'f8aaee7f-c3af-4f7f-9532-b8e7b4c2d51d');

INSERT INTO tb_profile_functionality (uuid_profile, uuid_functionality)
VALUES ('6a44a5d5-6c1b-41f2-8f36-c3692c7e5b47', 'd9de42f6-8b53-4e26-aa4c-4b4d8ce2cfb4');

INSERT INTO tb_profile_functionality (uuid_profile, uuid_functionality)
VALUES ('6a44a5d5-6c1b-41f2-8f36-c3692c7e5b47', '6eefc9d1-1c8e-471a-9680-f15df0b487d7');

INSERT INTO tb_profile_functionality (uuid_profile, uuid_functionality)
VALUES ('6a44a5d5-6c1b-41f2-8f36-c3692c7e5b47', '58c46f03-83d3-4415-a5a5-b5a5f5e97a5a');

INSERT INTO tb_profile_functionality (uuid_profile, uuid_functionality)
VALUES ('6a44a5d5-6c1b-41f2-8f36-c3692c7e5b47', 'e6892f13-540d-48c1-a698-42cbb0c679df');

INSERT INTO tb_profile_functionality (uuid_profile, uuid_functionality)
VALUES ('6a44a5d5-6c1b-41f2-8f36-c3692c7e5b47', '9a7eaf71-3224-4dd4-b5d2-35f890b8c49a');