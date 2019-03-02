CREATE TABLE files (
    file_id VARCHAR(64) PRIMARY KEY,
    file_size BIGINT,
	  content_type VARCHAR(20),
    original_file_name VARCHAR(300),
    new_name VARCHAR(300),
    relative_path VARCHAR(800),
    ext VARCHAR(20)
);

CREATE TABLE users_type (
    users_type_id INT AUTO_INCREMENT PRIMARY KEY,
    users_type_name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE users (
    username VARCHAR(64) PRIMARY KEY,
    password VARCHAR(300) NOT NULL,
    enabled BOOLEAN NOT NULL,
    account_non_expired BOOLEAN NOT NULL,
    credentials_non_expired BOOLEAN NOT NULL,
    account_non_locked BOOLEAN NOT NULL,
    users_type_id INT NOT NULL,
    real_name VARCHAR(30),
    avatar VARCHAR(64),
    join_date DATE,
    FOREIGN KEY (users_type_id)
        REFERENCES users_type (users_type_id),
    FOREIGN KEY (avatar)
        REFERENCES files (file_id)
);

CREATE TABLE authorities (
    username VARCHAR(64) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    FOREIGN KEY (username)
        REFERENCES users (username),
    PRIMARY KEY (username , authority)
);

CREATE TABLE persistent_logins (
    username VARCHAR(64) NOT NULL,
    series VARCHAR(64) PRIMARY KEY,
    token VARCHAR(64) NOT NULL,
    last_used TIMESTAMP NOT NULL
);

CREATE TABLE school (
    school_id INT AUTO_INCREMENT PRIMARY KEY,
    school_name VARCHAR(200) NOT NULL,
    school_is_del BOOLEAN
);

CREATE TABLE college (
    college_id INT AUTO_INCREMENT PRIMARY KEY,
    college_name VARCHAR(200) NOT NULL,
    college_address VARCHAR(500) NOT NULL,
    school_id INT NOT NULL,
    college_is_del  BOOLEAN,
    FOREIGN KEY (school_id)
        REFERENCES school (school_id)
);

CREATE TABLE department (
    department_id INT AUTO_INCREMENT PRIMARY KEY,
    department_name VARCHAR(200) NOT NULL,
    college_id INT NOT NULL,
    department_is_del BOOLEAN,
    FOREIGN KEY (college_id)
        REFERENCES college (college_id)
);

CREATE TABLE science (
    science_id INT AUTO_INCREMENT PRIMARY KEY,
    science_name VARCHAR(200) NOT NULL,
    department_id INT NOT NULL,
    science_is_del BOOLEAN,
    FOREIGN KEY (department_id)
        REFERENCES department (department_id)
);

CREATE TABLE grade (
    grade_id INT AUTO_INCREMENT PRIMARY KEY,
    grade VARCHAR(5),
    science_id INT NOT NULL,
    grade_is_del BOOLEAN,
    FOREIGN KEY (science_id)
        REFERENCES science (science_id)
);

CREATE TABLE organize (
    organize_id INT AUTO_INCREMENT PRIMARY KEY,
    organize_name VARCHAR(200) NOT NULL,
    grade_id INT NOT NULL,
    organize_is_del BOOLEAN,
    staff_id int,
    FOREIGN KEY (grade_id)
        REFERENCES grade (grade_id)
);

CREATE TABLE political_landscape (
    political_landscape_id INT AUTO_INCREMENT PRIMARY KEY,
    political_landscape_name VARCHAR(30) NOT NULL
);

CREATE TABLE student (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    student_number VARCHAR(20) UNIQUE NOT NULL,
    sex VARCHAR(2),
    political_landscape_id INT,
    place_origin VARCHAR(200),
    organize_id INT NOT NULL,
    username VARCHAR(64) NOT NULL,
    FOREIGN KEY (organize_id)
        REFERENCES organize (organize_id),
    FOREIGN KEY (username)
        REFERENCES users (username)
);

CREATE TABLE staff (
    staff_id INT AUTO_INCREMENT PRIMARY KEY,
    staff_number VARCHAR(20) UNIQUE NOT NULL,
    sex VARCHAR(2),
    political_landscape_id INT,
    department_id INT NOT NULL,
    username VARCHAR(64) NOT NULL,
    FOREIGN KEY (department_id)
        REFERENCES department (department_id),
    FOREIGN KEY (username)
        REFERENCES users (username)
);

CREATE TABLE system_configure(
    data_key VARCHAR(50) PRIMARY KEY ,
    data_value VARCHAR(100)
);

CREATE TABLE system_operator_log(
    log_id VARCHAR(64) PRIMARY KEY ,
    behavior VARCHAR(200) NOT NULL,
    operating_time DATETIME NOT NULL,
    username VARCHAR(200) NOT NULL,
    ip_address VARCHAR(50) NOT NULL
);

INSERT INTO files(file_id, file_size, content_type, original_file_name, new_name, relative_path, ext) VALUES (
'1000',4213,'image/jpg','avatar','avatar','img/avatar.jpg','jpg'
);
INSERT INTO users_type (users_type_name) VALUES ('系统');
INSERT INTO users_type (users_type_name) VALUES ('学生');
INSERT INTO users_type (users_type_name) VALUES ('教职工');

INSERT INTO users (username, password, enabled, account_non_expired, credentials_non_expired, account_non_locked,
                   users_type_id, real_name, avatar, join_date)
VALUES ('zbeboy', '$2a$10$HKXHRhnhlC1aZQ4hukD0S.zYep/T5A7FULBo7S2UrJsqQCThUxdo2', 1, 1, 1, 1, 1, '赵银', '1000', '2016-08-18');

INSERT INTO authorities (username, authority) VALUES ('zbeboy', 'ROLE_SYSTEM');

INSERT INTO political_landscape (political_landscape_name) VALUES ('群众');
INSERT INTO political_landscape (political_landscape_name) VALUES ('共青团员');
INSERT INTO political_landscape (political_landscape_name) VALUES ('中共预备党员');
INSERT INTO political_landscape (political_landscape_name) VALUES ('中共党员');
INSERT INTO political_landscape (political_landscape_name) VALUES ('民革党员');
INSERT INTO political_landscape (political_landscape_name) VALUES ('民盟盟员');
INSERT INTO political_landscape (political_landscape_name) VALUES ('民建会员');
INSERT INTO political_landscape (political_landscape_name) VALUES ('民进会员');
INSERT INTO political_landscape (political_landscape_name) VALUES ('农工党党员');
INSERT INTO political_landscape (political_landscape_name) VALUES ('致公党党员');
INSERT INTO political_landscape (political_landscape_name) VALUES ('九三学社社员');
INSERT INTO political_landscape (political_landscape_name) VALUES ('台盟盟员');
INSERT INTO political_landscape (political_landscape_name) VALUES ('无党派民主人士');
