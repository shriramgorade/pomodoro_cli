PostgreSQL table creation:
CREATE TABLE sessions (
    id SERIAL PRIMARY KEY,
    start_time TIMESTAMP,
    end_time TIMESTAMP,
    duration_minutes INT,
    type VARCHAR(20)  -- WORK / BREAK
);
