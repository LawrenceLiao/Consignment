
CREATE TABLE carrier(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL,
    prefix VARCHAR(30) NOT NULL,
    created_date_time TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_date_time TIMESTAMP WITH TIME ZONE NOT NULL
)