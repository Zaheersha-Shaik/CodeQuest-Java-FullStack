CREATE DATABASE IF NOT EXISTS codequest_db;
USE codequest_db;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    xp INT NOT NULL DEFAULT 0,
    level INT NOT NULL DEFAULT 1,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE admins (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE missions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(150) NOT NULL,
    description TEXT NOT NULL,
    category VARCHAR(30) NOT NULL,
    difficulty VARCHAR(20) NOT NULL,
    option1 VARCHAR(255) NOT NULL,
    option2 VARCHAR(255) NOT NULL,
    option3 VARCHAR(255) NOT NULL,
    option4 VARCHAR(255) NOT NULL,
    correct_answer VARCHAR(255) NOT NULL,
    xp_reward INT NOT NULL DEFAULT 10,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE mission_submissions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    mission_id INT NOT NULL,
    selected_answer VARCHAR(255) NOT NULL,
    is_correct BOOLEAN NOT NULL,
    submitted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE KEY one_attempt_per_mission (user_id, mission_id),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (mission_id) REFERENCES missions(id) ON DELETE CASCADE
);

INSERT INTO admins(username, password) VALUES ('admin', 'admin123');

INSERT INTO missions(title, description, category, difficulty, option1, option2, option3, option4, correct_answer, xp_reward) VALUES
('Java Entry Point', 'Which method is the entry point of a Java application?', 'Java', 'Easy', 'start()', 'main()', 'run()', 'init()', 'main()', 20),
('SQL Data Retrieval', 'Which SQL command is used to retrieve data from a table?', 'SQL', 'Easy', 'GET', 'SELECT', 'FETCHALL', 'READ', 'SELECT', 20),
('Stack Principle', 'Which principle is followed by a Stack?', 'DSA', 'Easy', 'FIFO', 'LIFO', 'Random', 'Priority', 'LIFO', 20);
