INSERT INTO users (name,dob) VALUES ('User1', CURRENT_DATE());
INSERT INTO users (name,dob) VALUES ('User2',CURRENT_DATE());
INSERT INTO users (name,dob) VALUES ('User3',CURRENT_DATE());
INSERT INTO users (name,dob) VALUES ('User4',CURRENT_DATE());

INSERT INTO posts (descriptions,user_id) VALUES ('My First Post', SELECT TOP 1 id FROM users);
INSERT INTO posts (descriptions,user_id) VALUES ('My Second Post',SELECT TOP 1 id FROM users);