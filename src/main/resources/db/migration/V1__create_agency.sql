CREATE TABLE IF NOT EXISTS agency (
     id          UUID PRIMARY KEY,
    name        VARCHAR(255) UNIQUE NOT NULL,
    position_x  BIGINT NOT NULL,
    position_y  BIGINT NOT NULL
    );
