CREATE TABLE menus (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(500) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE sections (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(500) NOT NULL,
    PRIMARY KEY (id),
);

CREATE TABLE menu_sections (
    menu_id INT NOT NULL,
    section_id INT NOT NULL,
    FOREIGN KEY (menu_id) REFERENCES menus(id),
    FOREIGN KEY (section_id) REFERENCES sections(id)
);

CREATE TABLE menu_items (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(500) NOT NULL,
    cost DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (id),
);

CREATE TABLE section_items (
    section_id INT NOT NULL,
    item_id INT NOT NULL,
    FOREIGN KEY (section_id) REFERENCES sections(id),
    FOREIGN KEY (item_id) REFERENCES menu_items(id)
);

CREATE TABLE accounts (
    id INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    address VARCHAR(500) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE account_orders (
    id INT NOT NULL AUTO_INCREMENT,
    account_id INT NOT NULL,
    total DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (account_id) REFERENCES accounts(id)
);

CREATE TABLE order_items (
    order_id INT NOT NULL AUTO_INCREMENT,
    item_id INT NOT NULL,
    quantity INT NOT NULL,
    FOREIGN KEY (order_id) REFERENCES account_orders(id),
    FOREIGN KEY (item_id) REFERENCES menu_items(id)
);