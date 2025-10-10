CREATE DATABASE exam_system;
USE exam_system;

CREATE TABLE users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(100) UNIQUE,
  password VARCHAR(100)
);

CREATE TABLE questions (
  id INT AUTO_INCREMENT PRIMARY KEY,
  question_text VARCHAR(255),
  option_a VARCHAR(100),
  option_b VARCHAR(100),
  option_c VARCHAR(100),
  option_d VARCHAR(100),
  correct_answer CHAR(1)
);

INSERT INTO questions (question_text, option_a, option_b, option_c, option_d, correct_answer) VALUES
('What is the capital of France?', 'Paris', 'London', 'Berlin', 'Rome', 'A'),
('Which is largest planet?', 'Earth', 'Jupiter', 'Mars', 'Venus', 'B');
