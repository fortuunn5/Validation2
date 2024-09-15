CREATE TABLE if not exists profanity (
  id SERIAL PRIMARY KEY,
  word_value VARCHAR(100) UNIQUE NOT NULL
);