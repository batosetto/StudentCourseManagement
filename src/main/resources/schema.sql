create table students (
    id          LONG NOT NULL Primary Key AUTO_INCREMENT,
    number      LONG NOT NULL UNIQUE,
    name        VARCHAR(128) NOT NULL
);

create table courses (
    id          LONG NOT NULL PRIMARY KEY AUTO_INCREMENT,
    studentId   LONG NOT NULL,
    name        VARCHAR(128) NOT NULL,
    grade       LONG NOT NULL
);

alter table courses
    add constraint student_course_fk foreign key (studentId)
    references students (id);

insert into students (number, name)
    values (01535342, 'Barbara Tosetto');

insert into students (number, name)
    values (01535343, 'Henrique Sampaio');

insert into students (number, name)
    values (01535344, 'Thiely Gimenez');

insert into students (number, name)
values (01535345, 'Roya Mohammadi');

insert into courses (name, grade, studentId)
    values ('Advanced Database Programming', 96, 1);

insert into courses (name, grade, studentId)
    values ('Web Application Development', 89, 3);

insert into courses (name, grade, studentId)
values ('Networking Programming', 93, 2);

insert into courses (name, grade, studentId)
values ('Project Management', 98, 4);

