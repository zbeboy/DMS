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

CREATE TABLE evaluate (
  evaluate_id     VARCHAR(64) PRIMARY KEY,
  evaluate_content   VARCHAR(200)
);

INSERT INTO evaluate VALUES ('1','体育不错');
INSERT INTO evaluate VALUES ('2','技能不错');
INSERT INTO evaluate VALUES ('3','志愿公益不错');
INSERT INTO evaluate VALUES ('4','科技创新不错');
INSERT INTO evaluate VALUES ('5','任职经历不错');
INSERT INTO evaluate VALUES ('6','思想成长不错');
INSERT INTO evaluate VALUES ('7','实践实习不错');
INSERT INTO evaluate VALUES ('8','工作履历不错');
INSERT INTO evaluate VALUES ('9','学习成绩不错');
INSERT INTO evaluate VALUES ('10','智育成绩不错');