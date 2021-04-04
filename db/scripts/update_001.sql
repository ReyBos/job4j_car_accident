CREATE TABLE accident_type (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100)
);

CREATE TABLE rule (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100)
);

CREATE TABLE accident (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    text VARCHAR(1000),
    address VARCHAR(500),
    accident_type_id INT REFERENCES accident_type(id)
);

CREATE TABLE accident_rule (
    id SERIAL PRIMARY KEY,
    accident_id INT REFERENCES accident(id),
    rule_id INT REFERENCES rule(id)
);

INSERT INTO accident_type(name) VALUES ('Две машины'), ('Машина и человек'), ('Машина и велосипед');
INSERT INTO rule(name) VALUES ('Статья. 1'), ('Статья. 2'), ('Статья. 3');