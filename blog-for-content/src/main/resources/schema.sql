CREATE TABLE IF NOT EXISTS persistent_logins (
                                                 username  VARCHAR(64) NOT NULL,
                                                 series    VARCHAR(64) NOT NULL,
                                                 token     VARCHAR(64) NOT NULL,
                                                 last_used TIMESTAMP   NOT NULL,
                                                 PRIMARY KEY (series)
);
CREATE TABLE IF NOT EXISTS SPRING_SESSION (
                                              PRIMARY_ID CHAR(36) NOT NULL,
                                              SESSION_ID CHAR(36) NOT NULL,
                                              CREATION_TIME BIGINT NOT NULL,
                                              LAST_ACCESS_TIME BIGINT NOT NULL,
                                              MAX_INACTIVE_INTERVAL INT NOT NULL,
                                              EXPIRY_TIME BIGINT NOT NULL,
                                              PRINCIPAL_NAME VARCHAR(100),
                                              CONSTRAINT SPRING_SESSION_PK PRIMARY KEY (PRIMARY_ID)
);
CREATE UNIQUE INDEX IF NOT EXISTS SPRING_SESSION_IX1 ON SPRING_SESSION (SESSION_ID);
CREATE INDEX IF NOT EXISTS SPRING_SESSION_IX2 ON SPRING_SESSION (EXPIRY_TIME);
CREATE INDEX IF NOT EXISTS SPRING_SESSION_IX3 ON SPRING_SESSION (PRINCIPAL_NAME);
CREATE TABLE IF NOT EXISTS SPRING_SESSION_ATTRIBUTES (
                                                         SESSION_PRIMARY_ID CHAR(36) NOT NULL,
                                                         ATTRIBUTE_NAME VARCHAR(200) NOT NULL,
                                                         ATTRIBUTE_BYTES BYTEA NOT NULL,
                                                         CONSTRAINT SPRING_SESSION_ATTRIBUTES_PK PRIMARY KEY (SESSION_PRIMARY_ID, ATTRIBUTE_NAME),
                                                         CONSTRAINT SPRING_SESSION_ATTRIBUTES_FK FOREIGN KEY (SESSION_PRIMARY_ID) REFERENCES SPRING_SESSION(PRIMARY_ID) ON DELETE CASCADE
);


BEGIN;
INSERT INTO db_user (id, avatarurl, confirmationuiid, createdat, email, hashpassword, name, role, status, surname, vocation) VALUES (1, 'def/avatar', '135e6c97-6f55-43d3-9a32-2637b56fd1b0', '2020-06-02 17:22:14.460784', 'test@mail.ru', '$2a$10$8.Jt.sJFBV8tY8VdbP/NMeiGPa4QIrUD4F36Vqw70jqw/U.wNzvcS', 'Andrew', 'USER', 'CONFIRMED', 'Naumow', 'programmer');
INSERT INTO db_user (id, avatarurl, confirmationuiid, createdat, email, hashpassword, name, role, status, surname, vocation) VALUES (2, 'def/avatar', '341e63ec-c0c6-4df6-a13b-100dab06564b', '2020-06-02 17:30:40.737615', 'test1@mail.ru', '$2a$10$h4yFVZD4UyRwdlLX7irY0uL1WevXyXUJUxu4x.gbuipS1aOsLzlsC', 'Test1', 'USER', 'CONFIRMED', 'Tests', 'forTest');
INSERT INTO db_user (id, avatarurl, confirmationuiid, createdat, email, hashpassword, name, role, status, surname, vocation) VALUES (3, 'def/avatar', '757281f9-ea1a-4a28-97a5-f4852bd9feaf', '2020-06-02 17:50:46.419684', 'test2@mail.ru', '$2a$10$d293fpNzX2IATlyDMTrL5OuYC6390x.A1Mko4tUZxLDmWq2NKLmSq', 'Test2', 'USER', 'CONFIRMED', 'Test3', 'tests');
INSERT INTO db_user (id, avatarurl, confirmationuiid, createdat, email, hashpassword, name, role, status, surname, vocation) VALUES (4, 'def/avatar', '7d8fc2f4-84fc-452c-8600-86b64b11f17d', '2020-06-02 17:55:00.896156', 'test3@mail.ru', '$2a$10$yTwWi65C.1p0eSrmx.8ZDul.hi1vn0NJZ17S8JsSYQVCYeBFd6GUG', 'Test4', 'USER', 'CONFIRMED', 'Test5', 'tes');

INSERT INTO blog (id, alias, avatarurl, backgroundurl, cratedat, subtitle, title, owner_id) VALUES (1, 'my-first-blog', '/user-1/file-1591107975664-1658836340', '/user-1/file-1591107975611-682225808', '2020-06-02 17:26:15.669305', 'subtitle', 'Best blog ever', 1);
INSERT INTO blog (id, alias, avatarurl, backgroundurl, cratedat, subtitle, title, owner_id) VALUES (2, 'superBlog', '/user-2/file-1591108281957-1285174477', '/user-2/file-1591108281997-77387560', '2020-06-02 17:31:22.002793', 'subtitle', 'title', 2);
INSERT INTO blog (id, alias, avatarurl, backgroundurl, cratedat, subtitle, title, owner_id) VALUES (3, 'pro100Blog', '/user-3/file-1591109490276-1804464003', '/user-3/file-1591109490314-1746971695', '2020-06-02 17:51:30.346796', 'sub', 'title', 3);
INSERT INTO blog (id, alias, avatarurl, backgroundurl, cratedat, subtitle, title, owner_id) VALUES (4, 'helloBlog', '/user-4/file-1591109759294-1373730942', '/user-4/file-1591109759331-255569527', '2020-06-02 17:55:59.336848', 'hi!', 'Hello', 4);

INSERT INTO sub (id, level, user_id) VALUES (1, 1, 1);
INSERT INTO sub (id, level, user_id) VALUES (5, 1, 1);


INSERT INTO blog_sub (blog_id, subs_id) VALUES (2, 1);
INSERT INTO blog_sub (blog_id, subs_id) VALUES (4, 5);

INSERT INTO comment (id, cratedat, text, user_id) VALUES (34, '2020-06-02 18:20:37.322246', 'comment', 1);

INSERT INTO content (id, url) VALUES (1, '/my-first-blog/file-1591108148825-1128033741');
INSERT INTO content (id, url) VALUES (7, '/superBlog/file-1591109021776-1794289023');
INSERT INTO content (id, url) VALUES (8, '/superBlog/file-1591109074911-1040537533');
INSERT INTO content (id, url) VALUES (9, '/superBlog/file-1591109120658-897889979');
INSERT INTO content (id, url) VALUES (10, '/pro100Blog/file-1591109510036-119440120');
INSERT INTO content (id, url) VALUES (12, '/pro100Blog/file-1591109645191-896641056');
INSERT INTO content (id, url) VALUES (13, '/helloBlog/file-1591109774169-640027577');
INSERT INTO content (id, url) VALUES (14, '/helloBlog/file-1591109786587-472118921');
INSERT INTO content (id, url) VALUES (15, '/helloBlog/file-1591109798296-480191961');
INSERT INTO content (id, url) VALUES (16, '/my-first-blog/file-1591112071274-96354517');


INSERT INTO file_info (id, contenttype, createdat, originalfilename, size, storagefilename) VALUES (1, 'image/jpeg', '2020-06-02 17:26:15.656370', '1561182942_2.jpg', 132583, 'file-1591107975611-682225808');
INSERT INTO file_info (id, contenttype, createdat, originalfilename, size, storagefilename) VALUES (2, 'image/jpeg', '2020-06-02 17:26:15.668307', 'kartinki-na-instagram-na-avu_GLAV.jpg', 58493, 'file-1591107975664-1658836340');
INSERT INTO file_info (id, contenttype, createdat, originalfilename, size, storagefilename) VALUES (3, 'image/png', '2020-06-02 17:28:37.878316', 'img_home_auto3.png', 47435, 'file-1591108117834-219690059');
INSERT INTO file_info (id, contenttype, createdat, originalfilename, size, storagefilename) VALUES (4, 'text/plain', '2020-06-02 17:29:08.980290', null, 80, 'file-1591108148825-1128033741');
INSERT INTO file_info (id, contenttype, createdat, originalfilename, size, storagefilename) VALUES (5, 'image/jpeg', '2020-06-02 17:31:21.994777', 'images.jpg', 8656, 'file-1591108281957-1285174477');
INSERT INTO file_info (id, contenttype, createdat, originalfilename, size, storagefilename) VALUES (6, 'image/jpeg', '2020-06-02 17:31:22.001759', '34ae953c59587d5242eebd743020ba0516fc55d41c5bf8b036916c4a8242613b.jpg', 793193, 'file-1591108281997-77387560');
INSERT INTO file_info (id, contenttype, createdat, originalfilename, size, storagefilename) VALUES (7, 'text/plain', '2020-06-02 17:32:20.781682', null, 1081, 'file-1591108340739-1342578642');
INSERT INTO file_info (id, contenttype, createdat, originalfilename, size, storagefilename) VALUES (8, 'text/plain', '2020-06-02 17:35:55.647560', null, 1081, 'file-1591108555601-212074089');
INSERT INTO file_info (id, contenttype, createdat, originalfilename, size, storagefilename) VALUES (9, 'text/plain', '2020-06-02 17:36:10.681089', null, 15, 'file-1591108570636-2004240805');
INSERT INTO file_info (id, contenttype, createdat, originalfilename, size, storagefilename) VALUES (10, 'text/plain', '2020-06-02 17:38:18.687636', null, 12, 'file-1591108698648-1067859195');
INSERT INTO file_info (id, contenttype, createdat, originalfilename, size, storagefilename) VALUES (11, 'text/plain', '2020-06-02 17:40:30.280471', null, 14, 'file-1591108830215-1996092802');
INSERT INTO file_info (id, contenttype, createdat, originalfilename, size, storagefilename) VALUES (12, 'text/plain', '2020-06-02 17:43:41.816295', null, 1358, 'file-1591109021776-1794289023');
INSERT INTO file_info (id, contenttype, createdat, originalfilename, size, storagefilename) VALUES (13, 'image/jpeg', '2020-06-02 17:44:10.521606', '141a6c2a8a5a7267b17ca257da29d03a.jpg', 79038, 'file-1591109050475-1823854828');
INSERT INTO file_info (id, contenttype, createdat, originalfilename, size, storagefilename) VALUES (14, 'image/jpeg', '2020-06-02 17:44:30.669259', 'kia.jpg', 314755, 'file-1591109070572-1765029729');
INSERT INTO file_info (id, contenttype, createdat, originalfilename, size, storagefilename) VALUES (15, 'text/plain', '2020-06-02 17:44:34.952936', null, 88, 'file-1591109074911-1040537533');
INSERT INTO file_info (id, contenttype, createdat, originalfilename, size, storagefilename) VALUES (16, 'image/jpeg', '2020-06-02 17:44:59.656132', '44223040-abstract-polygon-geometric-background-vector-and-illustration.jpg', 7563, 'file-1591109099612-15375976');
INSERT INTO file_info (id, contenttype, createdat, originalfilename, size, storagefilename) VALUES (17, 'text/plain', '2020-06-02 17:45:20.698748', null, 156, 'file-1591109120658-897889979');
INSERT INTO file_info (id, contenttype, createdat, originalfilename, size, storagefilename) VALUES (18, 'image/png', '2020-06-02 17:51:30.312887', 'images.png', 3520, 'file-1591109490276-1804464003');
INSERT INTO file_info (id, contenttype, createdat, originalfilename, size, storagefilename) VALUES (19, 'image/jpeg', '2020-06-02 17:51:30.318870', 'priroda.jpg', 89709, 'file-1591109490314-1746971695');
INSERT INTO file_info (id, contenttype, createdat, originalfilename, size, storagefilename) VALUES (20, 'text/plain', '2020-06-02 17:51:50.077774', null, 80, 'file-1591109510036-119440120');
INSERT INTO file_info (id, contenttype, createdat, originalfilename, size, storagefilename) VALUES (21, 'text/plain', '2020-06-02 17:52:33.448324', null, 217, 'file-1591109553408-1436905496');
INSERT INTO file_info (id, contenttype, createdat, originalfilename, size, storagefilename) VALUES (22, 'text/plain', '2020-06-02 17:54:05.230502', null, 70, 'file-1591109645191-896641056');
INSERT INTO file_info (id, contenttype, createdat, originalfilename, size, storagefilename) VALUES (23, 'image/jpeg', '2020-06-02 17:55:59.329867', '1585631876_00_4.jpg', 48315, 'file-1591109759294-1373730942');
INSERT INTO file_info (id, contenttype, createdat, originalfilename, size, storagefilename) VALUES (24, 'image/jpeg', '2020-06-02 17:55:59.335849', '350px-140-P1020281_-_Flickr_-_Laurie_Nature_Bee.jpg', 31881, 'file-1591109759331-255569527');
INSERT INTO file_info (id, contenttype, createdat, originalfilename, size, storagefilename) VALUES (25, 'text/plain', '2020-06-02 17:56:14.207387', null, 18, 'file-1591109774169-640027577');
INSERT INTO file_info (id, contenttype, createdat, originalfilename, size, storagefilename) VALUES (26, 'text/plain', '2020-06-02 17:56:26.627209', null, 17, 'file-1591109786587-472118921');
INSERT INTO file_info (id, contenttype, createdat, originalfilename, size, storagefilename) VALUES (27, 'text/plain', '2020-06-02 17:56:38.337813', null, 18, 'file-1591109798296-480191961');
INSERT INTO file_info (id, contenttype, createdat, originalfilename, size, storagefilename) VALUES (28, 'image/jpeg', '2020-06-02 18:29:36.589589', '350px-140-P1020281_-_Flickr_-_Laurie_Nature_Bee.jpg', 31881, 'file-1591111776541-1240899209');
INSERT INTO file_info (id, contenttype, createdat, originalfilename, size, storagefilename) VALUES (29, 'text/plain', '2020-06-02 18:34:31.317445', null, 797, 'file-1591112071274-96354517');

INSERT INTO post (id, active, cratedat, level, type, blog_id, content_id) VALUES (1, true, '2020-06-02 17:29:08.986082', 1, 's', 1, 1);
INSERT INTO post (id, active, cratedat, level, type, blog_id, content_id) VALUES (7, true, '2020-06-02 17:43:41.820281', 1, 's', 2, 7);
INSERT INTO post (id, active, cratedat, level, type, blog_id, content_id) VALUES (8, true, '2020-06-02 17:44:34.956927', 1, 's', 2, 8);
INSERT INTO post (id, active, cratedat, level, type, blog_id, content_id) VALUES (9, true, '2020-06-02 17:45:20.702738', 1, 'l', 2, 9);
INSERT INTO post (id, active, cratedat, level, type, blog_id, content_id) VALUES (10, true, '2020-06-02 17:51:50.082760', 1, 's', 3, 10);
INSERT INTO post (id, active, cratedat, level, type, blog_id, content_id) VALUES (12, true, '2020-06-02 17:54:05.234494', 1, 'r', 3, 12);
INSERT INTO post (id, active, cratedat, level, type, blog_id, content_id) VALUES (13, true, '2020-06-02 17:56:14.210379', 1, 's', 4, 13);
INSERT INTO post (id, active, cratedat, level, type, blog_id, content_id) VALUES (14, true, '2020-06-02 17:56:26.632187', 1, 'l', 4, 14);
INSERT INTO post (id, active, cratedat, level, type, blog_id, content_id) VALUES (15, true, '2020-06-02 17:56:38.342800', 1, 'r', 4, 15);
INSERT INTO post (id, active, cratedat, level, type, blog_id, content_id) VALUES (16, true, '2020-06-02 18:34:31.322883', 1, 's', 1, 16);

INSERT INTO post_comment (post_id, comments_id) VALUES (13, 34);

INSERT INTO post_db_user (post_id, likes_id) VALUES (13, 1);

COMMIT;


