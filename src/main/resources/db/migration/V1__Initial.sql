CREATE TABLE menu (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(500) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE section (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(500) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE menu_sections (
    menu_id INT NOT NULL,
    section_id INT NOT NULL,
    FOREIGN KEY (menu_id) REFERENCES menu(id),
    FOREIGN KEY (section_id) REFERENCES section(id)
);

CREATE TABLE menu_item (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(500) NOT NULL,
    cost DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE section_items (
    section_id INT NOT NULL,
    item_id INT NOT NULL,
    FOREIGN KEY (section_id) REFERENCES section(id),
    FOREIGN KEY (item_id) REFERENCES menu_item(id)
);

CREATE TYPE account_role AS ENUM ('ROLE_ADMIN', 'ROLE_USER');

CREATE TABLE account (
    id INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    address VARCHAR(500) NOT NULL,
    password_hash VARCHAR(500) NOT NULL,
    role account_role NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE account_order (
    id INT NOT NULL AUTO_INCREMENT,
    account_id INT NOT NULL,
    total DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (account_id) REFERENCES account(id)
);

CREATE TABLE order_items (
    order_id INT NOT NULL AUTO_INCREMENT,
    item_id INT NOT NULL,
    quantity INT NOT NULL,
    FOREIGN KEY (order_id) REFERENCES account_order(id),
    FOREIGN KEY (item_id) REFERENCES menu_item(id)
);
