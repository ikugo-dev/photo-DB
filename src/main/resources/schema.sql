CREATE TABLE IF NOT EXISTS photos (
    id BINARY(16) NOT NULL PRIMARY KEY,
    file_name VARCHAR(255),
    file_type VARCHAR(32),
    data BINARY
);