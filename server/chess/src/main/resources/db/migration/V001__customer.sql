CREATE TABLE customer
(
    id                BIGINT(20)   NOT NULL AUTO_INCREMENT,
    username          VARCHAR(255) NOT NULL,
    email             VARCHAR(255) NOT NULL,
    password          VARCHAR(255) NOT NULL,
    registration_date DATETIME DEFAULT NOW(),

    PRIMARY KEY (id),
    INDEX customer_username_idx (username)
);

CREATE TABLE customer_details
(
    id          BIGINT(20)   NOT NULL AUTO_INCREMENT,
    customer_id BIGINT(20)   NOT NULL,
    name        VARCHAR(64)  NOT NULL,
    last_name   VARCHAR(128) NOT NULL,
    birth_date  DATE         NOT NULL,
    city        VARCHAR(128),


    PRIMARY KEY (id),

    CONSTRAINT customer_details_customer_id_dk FOREIGN KEY (customer_id) REFERENCES customer (id),

    INDEX customer_details_customer_id_idx (customer_id)
);