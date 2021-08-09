insert into courses (course_name)
values ('Software Engineering'),
       ('Computer Science 122'),
       ('Computer Engineering');

insert into groups (group_name, course_id)
values ('Software Engineering 121', 1),
       ('Computer Science 122', 2),
       ('Computer Engineering 123', 3);

INSERT INTO students (email, first_name, last_name, group_id)
VALUES ('konstantinov03051999@gmail.com', 'Alex', 'Konstantinov', 1),
       ('alfonsovna@gmail.com', 'Augustine', 'Alfonsovna', 2),
       ('marco1curly@gmail.com', 'Marco', 'Curly', 3),
       ('e.wealthy@gmail.com', 'Efrosimya', 'Wealthy', 1),
       ('notwanted@gmail.com', 'Armen', 'Notwanted', 2),
       ('T0mashevsk1y@gmail.com', 'Arthur', 'Tomashevsky', 3),
       ('redred10@gmail.com', 'Amphis', 'Red', 3),
       ('superd1rty@gmail.com', 'dolph', 'dirty', 2),
       ('franco777@gmail.com', 'Franco', 'Colombo', 1),
       ('pierrenarcissus@gmail.com', 'Pierre', 'Narcissus', 2);

INSERT INTO teachers (email, first_name, last_name)
VALUES ('Lundgren5000@gmail.com', 'Dolph', 'Lundgren'),
       ('1amalive@gmail.com', 'Steve', 'Jobs'),
       ('howhawking@gmail.com', 'Stephen', 'Hawking');

INSERT INTO topics (topic_name, teacher_id)
VALUES ('How to knock out stallone ', 1),
       ('Life after...', 2),
       ('Black hole exploration', 3);

INSERT INTO lessons (date_time, name, topic_id, group_id)
VALUES ('2021-07-06 12:00:00', 'Ironfist ', 1, 1),
       ('2045-12-01 10:30:00', 'Life', 2, 2),
       ('2018-03-12 08:00:00', 'Holes,', 3, 3);