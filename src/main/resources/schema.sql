CREATE TABLE IF NOT EXISTS trending_recipes (
    id INT NOT NULL,
    name VARCHAR(250) NOT NULL,
    image_url VARCHAR(250) NOT NULL,
    difficulty VARCHAR(250) NOT NULL,
    "position" INT NOT NULL,
    version INT,
    PRIMARY KEY (id)
);