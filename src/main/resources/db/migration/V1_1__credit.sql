CREATE TABLE credit (
  credit_id     INT PRIMARY KEY AUTO_INCREMENT,
  student_number   VARCHAR(20) NOT NULL,
  year VARCHAR(5) NOT NULL,
  term      INT         NOT NULL,
  sports DOUBLE ,
  skills DOUBLE ,
  voluntary DOUBLE ,
  technological DOUBLE ,
  post DOUBLE ,
  ideological DOUBLE ,
  practical DOUBLE ,
  work DOUBLE ,
  achievement DOUBLE ,
  intellectual DOUBLE
);

CREATE  TABLE wining(
  wining_id     INT PRIMARY KEY AUTO_INCREMENT,
  credit_id      INT         NOT NULL,
  wining_content VARCHAR(500) NOT NULL,
  wining_score DOUBLE,
  FOREIGN KEY(credit_id) REFERENCES credit(credit_id)
);

CREATE TABLE diploma(
  diploma_id     INT PRIMARY KEY AUTO_INCREMENT,
  credit_id      INT         NOT NULL,
  diploma_name VARCHAR(500) NOT NULL,
  FOREIGN KEY(credit_id) REFERENCES credit(credit_id)
);

CREATE TABLE quality_release(
  quality_release_id  VARCHAR(64) PRIMARY KEY,
  release_title VARCHAR(100) NOT NULL,
  year VARCHAR(5) NOT NULL,
  term      INT         NOT NULL,
  release_date DATETIME NOT NULL,
  username VARCHAR(64) NOT NULL,
  FOREIGN KEY(username) REFERENCES USERS(username)
);

CREATE TABLE quality_apply(
  quality_apply_id VARCHAR(64) PRIMARY KEY,
  student_id INT NOT NULL,
  apply_content VARCHAR(100),
  apply_state INT DEFAULT 0 NOT NULL,
  apply_date DATETIME NOT NULL,
  quality_release_id VARCHAR(64) NOT NULL,
  FOREIGN KEY(student_id) REFERENCES STUDENT(student_id),
  FOREIGN KEY(quality_release_id) REFERENCES quality_release(quality_release_id)
);