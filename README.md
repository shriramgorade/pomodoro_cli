PostgreSQL table creation: <br>
CREATE TABLE sessions ( <br>
    id SERIAL PRIMARY KEY, <br>
    start_time TIMESTAMP, <br>
    end_time TIMESTAMP, <br>
    duration_minutes INT, <br>
    type VARCHAR(20)  -- WORK / BREAK <br>
); <br>
