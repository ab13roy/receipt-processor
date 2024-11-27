create table Receipt (
    receiptId VARCHAR(255) NOT NULL,
    retailer VARCHAR(255) NOT NULL,
    purchaseDate VARCHAR(255) NOT NULL,
    purchaseTime VARCHAR(255) NOT NULL,
    PRIMARY KEY (receiptId)
);

create table Products(
    receiptId VARCHAR(255) NOT NULL,
    shortDesc VARCHAR(255) NOT NULL,
    cost NUMERIC NOT NULL,
    FOREIGN KEY(receiptId) REFERENCES Receipt(receiptId)
);
