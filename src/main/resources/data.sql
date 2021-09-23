INSERT INTO USER(id, username, email, PASSWORD) VALUES
(1, 'Lima', 'leonardo.videc@gmail.com', '$2a$10$ERT6Je2dwmNVwAko2iTv9.MTJ0KKlXreb6SjRZ2Cwn0LeRrn/Jtw6'),
(2, 'Siti', 'alen.sitar@gmail.com', '$2a$10$x5IZe8QL.e57RQZxRERilufIVDfHsRe5YtB1rtURvey/HuqG7rxuG'),
(3, 'Edi', 'edi.petak@gmail.com', '$2a$10$DrwxcXhyVOPZH46dyNm0g.6QxQfjJbFnWE4mTcJ3UAlyWzsf7rlO2');

INSERT INTO authority(id, NAME) VALUES
(1, 'ADMIN'),
(2, 'USER');

INSERT INTO user_authority(user_id, authority_id) VALUES
(1, 1),
(2, 2),
(3, 2);

INSERT INTO clothing_type(id, TYPE) VALUES
(1, 'Hoodie'),
(2, 'Shirt'),
(3, 'Jeans');

INSERT INTO clothing(id, NAME, details, price, img, brand_name, sex, id_clothing_type) VALUES
(1, 'Gucci shirt', 'Details of Gucci shirt', 200, 'https://cdn-images.farfetch-contents.com/12/14/71/57/12147157_10105325_480.jpg', 'Gucci', 'MALE', 2),
(2, 'Philipp Plein hoodie', 'Details of Philipp Plein hoodie', 300, 'https://cdn-images.farfetch-contents.com/14/04/61/82/14046182_18977676_480.jpg', 'Philipp Plein', 'MALE', 1),
(3, 'Tommy Hilfiger jeans', 'Details of Tommy Hilfiger jeans', 120, 'https://cdn-images.farfetch-contents.com/15/53/83/39/15538339_28183416_480.jpg', 'Tommy Hilfiger', 'MALE', 3),
(4, 'Versace hoodie', 'Details of Verace hoodie', 690, 'https://cdn-images.farfetch-contents.com/16/80/73/63/16807363_33575858_480.jpg', 'Versace', 'MALE', 1),
(5, 'Marni shirt', 'Details of Marni shirt', 320, 'https://cdn-images.farfetch-contents.com/17/30/84/82/17308482_35735942_480.jpg', 'Marni', 'MALE', 2),
(6, 'Versace denim jacket', 'Details of Versace denim jacket', 3270, 'https://cdn-images.farfetch-contents.com/16/80/59/51/16805951_33654034_480.jpg', 'Versace', 'MALE', 2),
(7, 'Off-White denim jacket', 'Details of Off-White denim jacket', 1150, 'https://cdn-images.farfetch-contents.com/16/04/72/38/16047238_31241599_480.jpg', 'Off-White', 'MALE', 2),
(8, 'Fendi shirt', 'Details of Fendi shirt', 650, 'https://cdn-images.farfetch-contents.com/16/77/39/47/16773947_33160620_480.jpg', 'Fendi', 'MALE', 2);


INSERT INTO shoes_type(id, TYPE) VALUES
(1, 'Sneakers'),
(2, 'Flip-Flops'),
(3, 'Sport');

INSERT INTO shoes(id, NAME, details, price, img, brand_name, sex, id_shoes_type) VALUES
(1, 'Versace flip flops', 'Details of Versace flip flops', 787, 'https://cdn-images.farfetch-contents.com/16/36/88/85/16368885_33485927_480.jpg', 'Versace', 'MALE', 2),
(2, 'Nike x AMBUSH sneakers', 'Details of Nike x AMBUSH sneakers', 415, 'https://cdn-images.farfetch-contents.com/17/17/31/20/17173120_34835338_480.jpg', 'Nike', 'MALE', 1),
(3, 'Gucci sport shoes', 'Details of Gucci sport shoes', 690, 'https://cdn-images.farfetch-contents.com/16/35/93/23/16359323_33405889_480.jpg', 'Gucci', 'MALE', 3),
(4, 'Dolce & Gabbana sneakers', 'Details of Dolce & Gabbana sneakers', 3450, 'https://cdn-images.farfetch-contents.com/16/07/89/57/16078957_31086730_480.jpg', 'Dolce & Gabbana', 'MALE', 1);