CREATE TABLE tb_users
(
    id           UUID PRIMARY KEY,
    username     VARCHAR(50)  NOT NULL,
    email        VARCHAR(50)  NOT NULL,
    password     VARCHAR(255) NOT NULL,
    full_name    VARCHAR(150) NOT NULL,
    status       VARCHAR(20)  NOT NULL,
    type         VARCHAR(20)  NOT NULL,
    phone_number VARCHAR(20),
    cpf          VARCHAR(20),
    image_url    VARCHAR(255),
    created_at   TIMESTAMP    NOT NULL,
    updated_at   TIMESTAMP    NOT NULL,

    CONSTRAINT uk_tb_users_username UNIQUE (username),
    CONSTRAINT uk_tb_users_email UNIQUE (email),
    CONSTRAINT uk_tb_users_cpf UNIQUE (cpf)
);
