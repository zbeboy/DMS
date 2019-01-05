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
  achievement DOUBLE
);