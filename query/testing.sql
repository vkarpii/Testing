
drop database if exists testingdb;
create database testingdb;
use testingdb;

create table answer
(
   ans_id               int not null  comment '' AUTO_INCREMENT,
   ans_text             varchar(100) not null  comment '',
   correct              bool not null  comment '',
   primary key (ans_id)
);

create table `group`
(
   group_id             int not null  comment '' AUTO_INCREMENT,
   group_name           varchar(50) not null  comment '',
   primary key (group_id)
);

create table group_user
(
   group_id             int not null  comment '',
   user_id              int not null  comment '',
   primary key (group_id, user_id)
);

create table locale
(
   locale_id            int not null  comment '',
   locale_name          varchar(50) not null  comment '',
   primary key (locale_id)
);

create table question
(
   qa_id                int not null  comment '' AUTO_INCREMENT,
   qa_text              varchar(300) not null  comment '',
   checkbox              bool not null  comment '',
   primary key (qa_id)
);

create table question_answer
(
   ans_id               int not null  comment '',
   qa_id                int not null  comment '',
   primary key (ans_id, qa_id)
);

create table result
(
   user_id              int not null  comment '',
   test_id              int not null  comment '',
   result               double not null  comment '',
   test_attemps         int not null  comment '',
   test_time            int not null  comment '',
   primary key (user_id, test_id)
);

create table `role`
(
   role_id              int not null  comment '',
   role_name            varchar(50) not null  comment '',
   primary key (role_id)
);

create table test
(
   test_id              int not null  comment '' AUTO_INCREMENT,
   locale_id            int not null  comment '',
   sub_id               int not null  comment '',
   test_name            varchar(100) not null  comment '',
   test_max_time        int not null  comment '',
   test_max_attemps     int not null  comment '',
   complexity           smallint not null  comment '',
   primary key (test_id)
);

create table test_group
(
   test_id              int not null  comment '',
   group_id             int not null  comment '',
   primary key (test_id, group_id)
);

create table test_question
(
   qa_id                int not null  comment '',
   test_id              int not null  comment '',
   primary key (qa_id, test_id)
);

create table `user`
(
   user_id              int not null  comment '' AUTO_INCREMENT,
   role_id              int not null  comment '',
   login                varchar(50) not null  comment '',
   password             varchar(100) not null  comment '',
   email                varchar(50)  comment '',
   first_name           varchar(50) not null  comment '',
   last_name            varchar(50) not null  comment '',
   blocked              bool not null  comment '',
   primary key (user_id)
);

create table `subject`
(
   sub_id               int not null  comment '' AUTO_INCREMENT,
   sub_name             varchar(100) not null  comment '',
   primary key (sub_id)
);

alter table group_user add constraint FK_GROUP_US_RELATIONS_USER foreign key (user_id)
      references `user` (user_id) on delete CASCADE on update restrict;

alter table group_user add constraint FK_GROUP_US_RELATIONS_GROUP foreign key (group_id)
      references `group` (group_id) on delete restrict on update restrict;

alter table question_answer add constraint FK_QUESTION_RELATIONS_QUESTION foreign key (qa_id)
      references question (qa_id) on delete CASCADE on update restrict;

alter table question_answer add constraint FK_QUESTION_RELATIONS_ANSWER foreign key (ans_id)
      references answer (ans_id) on delete CASCADE on update restrict;

alter table result add constraint FK_RESULT_RELATIONS_TEST foreign key (test_id)
      references test (test_id) on delete CASCADE on update restrict;

alter table result add constraint FK_RESULT_RELATIONS_USER foreign key (user_id)
      references `user` (user_id) on delete CASCADE on update restrict;

alter table test add constraint FK_TEST_RELATIONS_LOCALE foreign key (locale_id)
      references locale (locale_id) on delete restrict on update cascade;

alter table test add constraint FK_TEST_RELATIONS_SUBJECT foreign key (sub_id)
      references `subject` (sub_id) on delete restrict on update restrict;

alter table test_group add constraint FK_TEST_GRO_RELATIONS_GROUP foreign key (group_id)
      references `group` (group_id) on delete restrict on update restrict;

alter table test_group add constraint FK_TEST_GRO_RELATIONS_TEST foreign key (test_id)
      references test (test_id) on delete CASCADE on update restrict;

alter table test_question add constraint FK_TEST_QUE_RELATIONS_TEST foreign key (test_id)
      references test (test_id) on delete CASCADE on update restrict;

alter table test_question add constraint FK_TEST_QUE_RELATIONS_QUESTION foreign key (qa_id)
      references question (qa_id) on delete CASCADE on update restrict;

alter table `user` add constraint FK_USER_RELATIONS_ROLE foreign key (role_id)
      references `role` (role_id) on delete restrict on update restrict;

INSERT INTO `testingdb`.`role` (`role_id`, `role_name`) VALUES ('1', 'student');
INSERT INTO `testingdb`.`role` (`role_id`, `role_name`) VALUES ('2', 'admin');
INSERT INTO `testingdb`.`locale` (`locale_id`, `locale_name`) VALUES ('1', 'english');
INSERT INTO `testingdb`.`locale` (`locale_id`, `locale_name`) VALUES ('2', 'ukrainian');
INSERT INTO `testingdb`.`group` (`group_id`, `group_name`) VALUES ('1', 'For all');
INSERT INTO `testingdb`.`subject` (`sub_id`, `sub_name`) VALUES ('1', 'Undefined');
INSERT INTO `testingdb`.`user` (`user_id`, `role_id`, `login`, `password`, `email`, `first_name`, `last_name`,`blocked`) VALUES ('1', '2', 'admin20', 'rqRqURDOw52+31xzZEm8Iw==', 'admin@gmail.com', 'Vitaly', 'Karpii','0');
INSERT INTO `testingdb`.`user` (`user_id`, `role_id`, `login`, `password`, `email`, `first_name`, `last_name`,`blocked`) VALUES ('2', '1', 'test', 'rqRqURDOw52+31xzZEm8Iw==', 'test@gmail.com', 'test', 'test','0');
INSERT INTO `testingdb`.`group_user` (`group_id`, `user_id`) VALUES ('1', '1');
INSERT INTO `testingdb`.`group_user` (`group_id`, `user_id`) VALUES ('1', '2');

