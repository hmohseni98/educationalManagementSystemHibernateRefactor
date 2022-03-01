CREATE TYPE professor_contract_type AS ENUM ('scienceCommittee', 'tuition');
CREATE TABLE IF NOT EXISTS student(
    national_code char(10) PRIMARY KEY ,
    first_name varchar(30),
    last_name varchar(50),
    address varchar(100),
    password varchar(16)
);
CREATE TABLE IF NOT EXISTS employee(
    national_code char(10) PRIMARY KEY ,
    first_name varchar(30),
    last_name varchar(50),
    address varchar(100),
    password varchar(16),
    income int
);
CREATE TABLE IF NOT EXISTS professor(
    national_code char(10) PRIMARY KEY ,
    first_name varchar(30),
    last_name varchar(50),
    address varchar(100),
    password varchar(16),
    contract_type professor_contract_type,
    income int
);
CREATE TABLE IF NOT EXISTS lesson(
    id int PRIMARY KEY ,
    name varchar(30),
    unit int
);
CREATE TABLE IF NOT EXISTS presenting_lesson(
    id int PRIMARY KEY ,
    lesson_id int,
    professor_id char(10),
    year int,
    term int,
    CONSTRAINT lesson_id_fk FOREIGN KEY (lesson_id) REFERENCES lesson(id),
    CONSTRAINT professor_id_fk FOREIGN KEY (professor_id) REFERENCES professor(national_code)
);
CREATE TABLE IF NOT EXISTS lesson_scores(
    id int PRIMARY KEY ,
    student_id char(10),
    presenting_lesson_id int,
    year int,
    term int,
    score float,
    CONSTRAINT student_id_fk FOREIGN KEY (student_id) REFERENCES student(national_code),
    CONSTRAINT lesson_scores_id_fk FOREIGN KEY (presenting_lesson_id) REFERENCES presenting_lesson(id)
);
CREATE TABLE IF NOT EXISTS educational_background(
    id int PRIMARY KEY ,
    student_id char(10),
    lesson_scores_id int,
    year int,
    term int,
    total_avg float,
    CONSTRAINT student_id_fk FOREIGN KEY (student_id) REFERENCES student(national_code),
    CONSTRAINT lesson_scores_id_fk FOREIGN KEY (lesson_scores_id) REFERENCES lesson_scores(id)
);
