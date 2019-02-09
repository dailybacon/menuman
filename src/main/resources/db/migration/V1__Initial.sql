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

CREATE TABLE menu_item (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(500) NOT NULL,
    cost DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE account (
    id INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    address VARCHAR(500) NOT NULL,
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