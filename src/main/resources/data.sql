USE load_image;
CREATE TABLE imageEntity
(
    id_image INTEGER AUTO_INCREMENT,
    id VARCHAR(100),
    code VARCHAR(50),
    campaign VARCHAR(5),
    sequence INTEGER,
    set_ VARCHAR(6),
    tags VARCHAR(50),
    PRIMARY KEY(id_image)
);

CREATE TABLE variantEntity
(
    id INTEGER AUTO_INCREMENT,
    image_id INTEGER,
    tags VARCHAR(50),
    relativePath VARCHAR(100),
    width INTEGER,
    height INTEGER,
    extension VARCHAR(5),
    PRIMARY KEY(id),
    CONSTRAINT FK_variant FOREIGN KEY (image_id)
        REFERENCES imageEntity(id_image)
);

INSERT INTO imageEntity  (id, code, campaign, sequence, set_, tags) VALUES ( '7d7de6e2e56d070ff47a71c41727ee59', 04239702923, 'V2022', 1, '003', 'main, test');
INSERT INTO imageEntity (id, code, campaign, sequence, set_, tags) VALUES ( '8d7de6e2e56d070ff47a71c41727ff85', 10239702852, 'I2022', 1, '005', 'main');
INSERT INTO imageEntity (id, code, campaign, sequence, set_, tags) VALUES ( '9d7de6e2e56d070ff47a71c41727aa35', 22239702741, 'V2023', 1, '007', 'main, test, abc');

INSERT INTO variantEntity (image_id, tags, relativePath, width, height, extension) VALUES (1, 'front, test, ppp', '/2023/V/0/2/p/4239/702/922/trendyol/4239702922_2_6_1.jpg?ts=123123123132', 1200, 600, 'jpg');

INSERT INTO variantEntity (image_id, tags, relativePath, width, height, extension) VALUES (2, 'front, test, ppp', '/2022/I/0/2/p/10239/702/922/trendyol/10239702852_2_6_1.jpg?ts=123123123132', 1200, 600, 'jpg');

INSERT INTO variantEntity (image_id, tags, relativePath, width, height, extension) VALUES (1, 'front, test, ppp', '/2022/V/0/2/p/4239/702/922/trendyol/4239702922_2_6_1.png?ts=123123123132', 1250, 650, 'png');

INSERT INTO variantEntity (image_id, tags, relativePath, width, height, extension) VALUES (3, 'front', '/2023/V/0/2/p/2223/702/922/trendyol/22239702852_2_6_1.jpg?ts=123123123132', 1300, 700, 'jpg');

INSERT INTO variantEntity (image_id, tags, relativePath, width, height, extension) VALUES (3, 'front, ppp', '/2023/V/0/2/p/2223/702/922/trendyol/22239702852_2_6_1.png?ts=123123123132', 1100, 500, 'png');