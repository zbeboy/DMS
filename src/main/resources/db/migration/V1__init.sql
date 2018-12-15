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
    school_name VARCHAR(200) NOT NULL
);

CREATE TABLE college (
    college_id INT AUTO_INCREMENT PRIMARY KEY,
    college_name VARCHAR(200) NOT NULL,
    college_address VARCHAR(500) NOT NULL,
    school_id INT NOT NULL,
    FOREIGN KEY (school_id)
        REFERENCES school (school_id)
);

CREATE TABLE department (
    department_id INT AUTO_INCREMENT PRIMARY KEY,
    department_name VARCHAR(200) NOT NULL,
    college_id INT NOT NULL,
    FOREIGN KEY (college_id)
        REFERENCES college (college_id)
);

CREATE TABLE science (
    science_id INT AUTO_INCREMENT PRIMARY KEY,
    science_name VARCHAR(200) NOT NULL,
    department_id INT NOT NULL,
    FOREIGN KEY (department_id)
        REFERENCES department (department_id)
);

CREATE TABLE grade (
    grade_id INT AUTO_INCREMENT PRIMARY KEY,
    grade VARCHAR(5),
    science_id INT NOT NULL,
    FOREIGN KEY (science_id)
        REFERENCES science (science_id)
);

CREATE TABLE organize (
    organize_id INT AUTO_INCREMENT PRIMARY KEY,
    organize_name VARCHAR(200) NOT NULL,
    grade_id INT NOT NULL,
    FOREIGN KEY (grade_id)
        REFERENCES grade (grade_id)
);

CREATE TABLE political_landscape (
    political_landscape_id INT AUTO_INCREMENT PRIMARY KEY,
    political_landscape_name VARCHAR(30) NOT NULL
);

CREATE TABLE nation (
    nation_id INT AUTO_INCREMENT PRIMARY KEY,
    nation_name VARCHAR(30) NOT NULL
);

CREATE TABLE academic_title (
    academic_title_id INT AUTO_INCREMENT PRIMARY KEY,
    academic_title_name VARCHAR(30) NOT NULL
);

CREATE TABLE student (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    student_number VARCHAR(20) UNIQUE NOT NULL,
    birthday DATE,
    sex VARCHAR(2),
    family_residence VARCHAR(200),
    political_landscape_id INT,
    nation_id INT,
    dormitory_number VARCHAR(15),
    parent_name VARCHAR(10),
    parent_contact_phone VARCHAR(15),
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
    birthday DATE,
    sex VARCHAR(2),
    family_residence VARCHAR(200),
    political_landscape_id INT,
    nation_id INT,
    post VARCHAR(50),
    academic_title_id INT,
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

INSERT INTO nation (nation_name) VALUES ('汉族');
INSERT INTO nation (nation_name) VALUES ('蒙古族');
INSERT INTO nation (nation_name) VALUES ('回族');
INSERT INTO nation (nation_name) VALUES ('藏族');
INSERT INTO nation (nation_name) VALUES ('维吾尔族');
INSERT INTO nation (nation_name) VALUES ('苗族');
INSERT INTO nation (nation_name) VALUES ('彝族');
INSERT INTO nation (nation_name) VALUES ('壮族');
INSERT INTO nation (nation_name) VALUES ('布依族');
INSERT INTO nation (nation_name) VALUES ('朝鲜族');
INSERT INTO nation (nation_name) VALUES ('满族');
INSERT INTO nation (nation_name) VALUES ('侗族');
INSERT INTO nation (nation_name) VALUES ('瑶族');
INSERT INTO nation (nation_name) VALUES ('白族');
INSERT INTO nation (nation_name) VALUES ('土家族');
INSERT INTO nation (nation_name) VALUES ('哈尼族');
INSERT INTO nation (nation_name) VALUES ('哈萨克族');
INSERT INTO nation (nation_name) VALUES ('傣族');
INSERT INTO nation (nation_name) VALUES ('黎族');
INSERT INTO nation (nation_name) VALUES ('傈僳族');
INSERT INTO nation (nation_name) VALUES ('佤族');
INSERT INTO nation (nation_name) VALUES ('畲族');
INSERT INTO nation (nation_name) VALUES ('高山族');
INSERT INTO nation (nation_name) VALUES ('拉祜族');
INSERT INTO nation (nation_name) VALUES ('水族');
INSERT INTO nation (nation_name) VALUES ('东乡族');
INSERT INTO nation (nation_name) VALUES ('纳西族');
INSERT INTO nation (nation_name) VALUES ('景颇族');
INSERT INTO nation (nation_name) VALUES ('柯尔克孜族');
INSERT INTO nation (nation_name) VALUES ('土族');
INSERT INTO nation (nation_name) VALUES ('达斡尔族');
INSERT INTO nation (nation_name) VALUES ('仫佬族');
INSERT INTO nation (nation_name) VALUES ('羌族');
INSERT INTO nation (nation_name) VALUES ('布朗族');
INSERT INTO nation (nation_name) VALUES ('撒拉族');
INSERT INTO nation (nation_name) VALUES ('毛难族');
INSERT INTO nation (nation_name) VALUES ('仡佬族');
INSERT INTO nation (nation_name) VALUES ('锡伯族');
INSERT INTO nation (nation_name) VALUES ('阿昌族');
INSERT INTO nation (nation_name) VALUES ('普米族');
INSERT INTO nation (nation_name) VALUES ('塔吉克族');
INSERT INTO nation (nation_name) VALUES ('怒族');
INSERT INTO nation (nation_name) VALUES ('乌孜别克族');
INSERT INTO nation (nation_name) VALUES ('俄罗斯族');
INSERT INTO nation (nation_name) VALUES ('鄂温克族');
INSERT INTO nation (nation_name) VALUES ('崩龙族');
INSERT INTO nation (nation_name) VALUES ('保安族');
INSERT INTO nation (nation_name) VALUES ('裕固族');
INSERT INTO nation (nation_name) VALUES ('京族');
INSERT INTO nation (nation_name) VALUES ('塔塔尔族');
INSERT INTO nation (nation_name) VALUES ('独龙族');
INSERT INTO nation (nation_name) VALUES ('鄂伦春族');
INSERT INTO nation (nation_name) VALUES ('赫哲族');
INSERT INTO nation (nation_name) VALUES ('门巴族');
INSERT INTO nation (nation_name) VALUES ('珞巴族');
INSERT INTO nation (nation_name) VALUES ('基诺族');

INSERT INTO academic_title (academic_title_name) VALUES ('讲师');
INSERT INTO academic_title (academic_title_name) VALUES ('副教授');
INSERT INTO academic_title (academic_title_name) VALUES ('教授');
INSERT INTO academic_title (academic_title_name) VALUES ('助教');
INSERT INTO academic_title (academic_title_name) VALUES ('工程师');
INSERT INTO academic_title (academic_title_name) VALUES ('高级工程师');
INSERT INTO academic_title (academic_title_name) VALUES ('教授级高级工程师');
INSERT INTO academic_title (academic_title_name) VALUES ('助理工程师');
INSERT INTO academic_title (academic_title_name) VALUES ('实验师');
INSERT INTO academic_title (academic_title_name) VALUES ('助理实验师');
INSERT INTO academic_title (academic_title_name) VALUES ('高级实验师');
INSERT INTO academic_title (academic_title_name) VALUES ('副研究员');
INSERT INTO academic_title (academic_title_name) VALUES ('研究员');
INSERT INTO academic_title (academic_title_name) VALUES ('助理研究员');
