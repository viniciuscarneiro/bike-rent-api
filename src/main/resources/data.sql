delete
from bike_rentals;
delete
from bike_images;
delete
from bikes;

insert into bikes(id, name, type, body_size, max_load, rate, description, ratings)
values (1, 'Monahan and Sons', 'Cruiser Bicycle', 24, 70, 97,
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
        4.6);
insert into bike_images(id, url, bike_id)
values (1, 'https://cremecycles.com/images/glowne/16.jpg', 1);
insert into bike_images(id, url, bike_id)
values (2, 'https://cremecycles.com/images/glowne/13.jpg', 1);
insert into bike_images(id, url, bike_id)
values (3, 'https://cremecycles.com/images/glowne/15.jpg', 1);
insert into bikes(id, name, type, body_size, max_load, rate, description, ratings)
values (2, 'Lebsack Group', 'Mountain Bicycle', 27, 80, 67,
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
        4.7);
insert into bike_images(id, url, bike_id)
values (4, 'https://cremecycles.com/images/glowne/15.jpg', 2);
insert into bike_images(id, url, bike_id)
values (5, 'https://cremecycles.com/images/glowne/14.jpg', 2);
insert into bike_images(id, url, bike_id)
values (6, 'https://cremecycles.com/images/glowne/13.jpg', 2);
insert into bikes(id, name, type, body_size, max_load, rate, description, ratings)
values (3, 'Reynolds, Zemlak and Bernhard', 'Fitness Bicycle', 26, 100, 106,
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
        4.8);
insert into bike_images(id, url, bike_id)
values (7, 'https://cremecycles.com/images/glowne/13.jpg', 3);
insert into bike_images(id, url, bike_id)
values (8, 'https://cremecycles.com/images/glowne/14.jpg', 3);
insert into bike_images(id, url, bike_id)
values (9, 'https://cremecycles.com/images/glowne/14.jpg', 3);
insert into bikes(id, name, type, body_size, max_load, rate, description, ratings)
values (4, 'McCullough - Wintheiser', 'Road Bicycle', 29, 90, 54,
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
        4.6);
insert into bike_images(id, url, bike_id)
values (10, 'https://cremecycles.com/images/glowne/12.jpg', 4);
insert into bike_images(id, url, bike_id)
values (11, 'https://cremecycles.com/images/glowne/13.jpg', 4);
insert into bike_images(id, url, bike_id)
values (12, 'https://cremecycles.com/images/glowne/16.jpg', 4);
insert into bikes(id, name, type, body_size, max_load, rate, description, ratings)
values (5, 'Johnston - White', 'Dual-Sport Bicycle', 27, 100, 52,
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
        5);
insert into bike_images(id, url, bike_id)
values (13, 'https://cremecycles.com/images/glowne/12.jpg', 5);
insert into bike_images(id, url, bike_id)
values (14, 'https://cremecycles.com/images/glowne/16.jpg', 5);
insert into bike_images(id, url, bike_id)
values (15, 'https://cremecycles.com/images/glowne/13.jpg', 5);
insert into bikes(id, name, type, body_size, max_load, rate, description, ratings)
values (6, 'Rutherford LLC', 'Flat-Foot Comfort Bicycle', 29, 90, 38,
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
        5);
insert into bike_images(id, url, bike_id)
values (16, 'https://cremecycles.com/images/glowne/14.jpg', 6);
insert into bike_images(id, url, bike_id)
values (17, 'https://cremecycles.com/images/glowne/13.jpg', 6);
insert into bike_images(id, url, bike_id)
values (18, 'https://cremecycles.com/images/glowne/14.jpg', 6);
insert into bikes(id, name, type, body_size, max_load, rate, description, ratings)
values (7, 'Stracke, Ebert and Purdy', 'Folding Bicycle', 27, 110, 159,
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
        5);
insert into bike_images(id, url, bike_id)
values (19, 'https://cremecycles.com/images/glowne/15.jpg', 7);
insert into bike_images(id, url, bike_id)
values (20, 'https://cremecycles.com/images/glowne/13.jpg', 7);
insert into bike_images(id, url, bike_id)
values (21, 'https://cremecycles.com/images/glowne/14.jpg', 7);
insert into bikes(id, name, type, body_size, max_load, rate, description, ratings)
values (8, 'Wolf, Treutel and Tromp', 'Adventure Road Bicycle', 20, 70, 195,
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
        4.8);
insert into bike_images(id, url, bike_id)
values (22, 'https://cremecycles.com/images/glowne/14.jpg', 8);
insert into bike_images(id, url, bike_id)
values (23, 'https://cremecycles.com/images/glowne/15.jpg', 8);
insert into bike_images(id, url, bike_id)
values (24, 'https://cremecycles.com/images/glowne/16.jpg', 8);
insert into bikes(id, name, type, body_size, max_load, rate, description, ratings)
values (9, 'Wintheiser, Schumm and Leannon', 'City Bicycle', 29, 100, 79,
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
        4.6);
insert into bike_images(id, url, bike_id)
values (25, 'https://cremecycles.com/images/glowne/14.jpg', 9);
insert into bike_images(id, url, bike_id)
values (26, 'https://cremecycles.com/images/glowne/14.jpg', 9);
insert into bike_images(id, url, bike_id)
values (27, 'https://cremecycles.com/images/glowne/12.jpg', 9);
insert into bikes(id, name, type, body_size, max_load, rate, description, ratings)
values (10, 'Rowe Group', 'Flat-Foot Comfort Bicycle', 26, 100, 78,
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
        4.6);
insert into bike_images(id, url, bike_id)
values (28, 'https://cremecycles.com/images/glowne/12.jpg', 10);
insert into bike_images(id, url, bike_id)
values (29, 'https://cremecycles.com/images/glowne/12.jpg', 10);
insert into bike_images(id, url, bike_id)
values (30, 'https://cremecycles.com/images/glowne/15.jpg', 10);
insert into bikes(id, name, type, body_size, max_load, rate, description, ratings)
values (11, 'Emard Group', 'Tricycle', 20, 80, 28,
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
        4.5);
insert into bike_images(id, url, bike_id)
values (31, 'https://cremecycles.com/images/glowne/12.jpg', 11);
insert into bike_images(id, url, bike_id)
values (32, 'https://cremecycles.com/images/glowne/12.jpg', 11);
insert into bike_images(id, url, bike_id)
values (33, 'https://cremecycles.com/images/glowne/16.jpg', 11);
insert into bikes(id, name, type, body_size, max_load, rate, description, ratings)
values (12, 'Gleason Group', 'Tricycle', 27, 80, 148,
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
        4.6);
insert into bike_images(id, url, bike_id)
values (34, 'https://cremecycles.com/images/glowne/12.jpg', 12);
insert into bike_images(id, url, bike_id)
values (35, 'https://cremecycles.com/images/glowne/13.jpg', 12);
insert into bike_images(id, url, bike_id)
values (36, 'https://cremecycles.com/images/glowne/14.jpg', 12);
insert into bikes(id, name, type, body_size, max_load, rate, description, ratings)
values (13, 'Huels, Stiedemann and Dibbert', 'Dual-Sport Bicycle', 20, 70, 44,
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
        4.6);
insert into bike_images(id, url, bike_id)
values (37, 'https://cremecycles.com/images/glowne/13.jpg', 13);
insert into bike_images(id, url, bike_id)
values (38, 'https://cremecycles.com/images/glowne/13.jpg', 13);
insert into bike_images(id, url, bike_id)
values (39, 'https://cremecycles.com/images/glowne/14.jpg', 13);
insert into bikes(id, name, type, body_size, max_load, rate, description, ratings)
values (14, 'Barton, Satterfield and Gutmann', 'Triathlon/Time Trial Bicycle', 29, 120, 26,
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
        4.7);
insert into bike_images(id, url, bike_id)
values (40, 'https://cremecycles.com/images/glowne/14.jpg', 14);
insert into bike_images(id, url, bike_id)
values (41, 'https://cremecycles.com/images/glowne/16.jpg', 14);
insert into bike_images(id, url, bike_id)
values (42, 'https://cremecycles.com/images/glowne/13.jpg', 14);
insert into bikes(id, name, type, body_size, max_load, rate, description, ratings)
values (15, 'Gutkowski - Baumbach', 'Adventure Road Bicycle', 24, 120, 67,
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
        4.8);
insert into bike_images(id, url, bike_id)
values (43, 'https://cremecycles.com/images/glowne/15.jpg', 15);
insert into bike_images(id, url, bike_id)
values (44, 'https://cremecycles.com/images/glowne/12.jpg', 15);
insert into bike_images(id, url, bike_id)
values (45, 'https://cremecycles.com/images/glowne/13.jpg', 15);
insert into bikes(id, name, type, body_size, max_load, rate, description, ratings)
values (16, 'Walter Inc', 'Dual-Sport Bicycle', 20, 100, 133,
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
        4.6);
insert into bike_images(id, url, bike_id)
values (46, 'https://cremecycles.com/images/glowne/13.jpg', 16);
insert into bike_images(id, url, bike_id)
values (47, 'https://cremecycles.com/images/glowne/13.jpg', 16);
insert into bike_images(id, url, bike_id)
values (48, 'https://cremecycles.com/images/glowne/12.jpg', 16);
insert into bikes(id, name, type, body_size, max_load, rate, description, ratings)
values (17, 'Streich Group', 'Mountain Bicycle', 26, 90, 173,
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
        4.6);
insert into bike_images(id, url, bike_id)
values (49, 'https://cremecycles.com/images/glowne/16.jpg', 17);
insert into bike_images(id, url, bike_id)
values (50, 'https://cremecycles.com/images/glowne/13.jpg', 17);
insert into bike_images(id, url, bike_id)
values (51, 'https://cremecycles.com/images/glowne/16.jpg', 17);
insert into bikes(id, name, type, body_size, max_load, rate, description, ratings)
values (18, 'Hintz LLC', 'Folding Bicycle', 29, 120, 59,
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
        4.7);
insert into bike_images(id, url, bike_id)
values (52, 'https://cremecycles.com/images/glowne/13.jpg', 18);
insert into bike_images(id, url, bike_id)
values (53, 'https://cremecycles.com/images/glowne/16.jpg', 18);
insert into bike_images(id, url, bike_id)
values (54, 'https://cremecycles.com/images/glowne/14.jpg', 18);
insert into bikes(id, name, type, body_size, max_load, rate, description, ratings)
values (19, 'Beier Inc', 'Road Bicycle', 20, 70, 81,
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
        4.7);
insert into bike_images(id, url, bike_id)
values (55, 'https://cremecycles.com/images/glowne/15.jpg', 19);
insert into bike_images(id, url, bike_id)
values (56, 'https://cremecycles.com/images/glowne/13.jpg', 19);
insert into bike_images(id, url, bike_id)
values (57, 'https://cremecycles.com/images/glowne/15.jpg', 19);
insert into bikes(id, name, type, body_size, max_load, rate, description, ratings)
values (20, 'Gusikowski - Johnston', 'Hybrid Bicycle', 27, 90, 192,
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
        4.9);
insert into bike_images(id, url, bike_id)
values (58, 'https://cremecycles.com/images/glowne/12.jpg', 20);
insert into bike_images(id, url, bike_id)
values (59, 'https://cremecycles.com/images/glowne/15.jpg', 20);
insert into bike_images(id, url, bike_id)
values (60, 'https://cremecycles.com/images/glowne/12.jpg', 20);
