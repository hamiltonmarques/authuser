CREATE TABLE tb_users_courses
(
    id        UUID PRIMARY KEY,
    user_id   UUID NOT NULL,
    course_id UUID NOT NULL,

    CONSTRAINT fk_user_courses FOREIGN KEY (user_id)
        REFERENCES tb_users (id)
);