--liquibase formatted sql

--changeset A.Mirhosseini:db.changelog-20230203-1419-user-table
CREATE TABLE USER(
                     ID VARCHAR(255) not null,
                     USERNAME VARCHAR(255) not null ,
                     PASSWORD VARCHAR(255),
                     PRIMARY KEY(ID)
);

INSERT INTO USER(ID, USERNAME, PASSWORD) VALUES ('ADMIN_USER', 'admin', '$2a$10$CKMnDf6IHm7f6WhQ1/QRQez22ND4fELjQD8vzizL63VWSu9pX0pe.');