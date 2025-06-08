CREATE TABLE if NOT EXISTS employees
(
    id
    INTEGER
    PRIMARY
    KEY,
    first_name
    VARCHAR
(
    250
),
    last_name VARCHAR
(
    250
),
    email VARCHAR
(
    250
),
    age INTEGER,
    designation VARCHAR
(
    250
),
    phone_number VARCHAR
(
    250
),
    salary DECIMAL
(
    10,
    2
),
    department VARCHAR
(
    250
),
    hire_date DATE,
    address VARCHAR
(
    500
),
    date_of_birth DATE,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
    );
