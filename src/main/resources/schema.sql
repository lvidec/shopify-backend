CREATE TABLE IF NOT EXISTS app_user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    PASSWORD VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS authority (
    id INT AUTO_INCREMENT PRIMARY KEY,
    NAME VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS user_authority(
    user_id INT NOT NULL,
    authority_id INT NOT NULL,
    CONSTRAINT fk_user_authority FOREIGN KEY (user_id) REFERENCES app_user(id),
    CONSTRAINT fk_authority FOREIGN KEY (authority_id) REFERENCES authority(id)
);

CREATE TABLE IF NOT EXISTS clothing_type(
    id INT AUTO_INCREMENT PRIMARY KEY,
    TYPE VARCHAR(45) NOT NULL
);

CREATE TABLE IF NOT EXISTS clothing(
    id INT AUTO_INCREMENT,
    NAME VARCHAR(100) NOT NULL,
    details VARCHAR(200) NOT NULL,
    price FLOAT NOT NULL,
    img VARCHAR(200) NOT NULL,
    brand_name VARCHAR(45) NOT NULL,
    sex ENUM('MALE', 'FEMALE') NOT NULL,
    id_clothing_type INT,
    PRIMARY KEY (id),
    FOREIGN KEY (id_clothing_type) REFERENCES clothing_type(id)
);


CREATE TABLE IF NOT EXISTS shoes_type(
    id INT AUTO_INCREMENT PRIMARY KEY,
    TYPE VARCHAR(45) NOT NULL
);

CREATE TABLE IF NOT EXISTS shoes(
    id INT AUTO_INCREMENT,
    NAME VARCHAR(100) NOT NULL,
    details VARCHAR(200) NOT NULL,
    price FLOAT NOT NULL,
    img VARCHAR(200) NOT NULL,
    brand_name VARCHAR(45) NOT NULL,
    sex ENUM('MALE', 'FEMALE') NOT NULL,
    id_shoes_type INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_shoes_type) REFERENCES shoes_type(id)
);

