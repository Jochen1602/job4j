CREATE TABLE IF NOT EXISTS vacancy (
  id      SERIAL          PRIMARY KEY,
  date    VARCHAR(10)     NOT NULL,
  name    TEXT            NOT NULL,
  text    TEXT            NOT NULL,
  link    TEXT            NOT NULL
  );