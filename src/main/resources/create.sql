DROP DATABASE IF EXISTS blog;

CREATE DATABASE blog DEFAULT CHARACTER SET 'utf8'
    DEFAULT COLLATE 'utf8_unicode_ci';

USE blog;

CREATE TABLE user_role (
    ur_id BIGINT UNSIGNED NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
    ur_role VARCHAR(50) NOT NULL
) ENGINE = InnoDB;

CREATE TABLE user_info (
    ui_id BIGINT UNSIGNED NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
    ui_name VARCHAR(50) NULL,
    ui_surname VARCHAR(50) NULL,
    ui_registeredAt DATETIME NOT NULL
) ENGINE = InnoDB;

CREATE TABLE user (
    u_id BIGINT UNSIGNED NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
    u_ui_id BIGINT UNSIGNED NULL DEFAULT NULL,
    u_ur_id BIGINT UNSIGNED NOT NULL,
    u_email VARCHAR(50) NOT NULL UNIQUE,
    u_login VARCHAR(50) NOT NULL UNIQUE,
    u_passwordHash VARCHAR(60) NOT NULL,
    FOREIGN KEY (u_ui_id) REFERENCES user_info (ui_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY (u_ur_id) REFERENCES user_role (ur_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB;

CREATE TABLE category (
    c_id BIGINT UNSIGNED NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
    c_title VARCHAR(75) NOT NULL,
    c_slug VARCHAR(100) NOT NULL
) ENGINE = InnoDB;

CREATE TABLE post (
    p_id BIGINT UNSIGNED NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
    p_u_id BIGINT UNSIGNED NOT NULL,
    p_c_id BIGINT UNSIGNED NOT NULL,
    p_title VARCHAR(75) NOT NULL UNIQUE,
    p_slug VARCHAR(100) NOT NULL UNIQUE,
    p_published TINYINT(1) NOT NULL DEFAULT 0,
    p_content TEXT NULL DEFAULT NULL,
    p_createdAt DATETIME NOT NULL,
    p_updatedAt DATETIME NULL DEFAULT NULL,
    p_publishedAt DATETIME NULL DEFAULT NULL,
    FOREIGN KEY (p_u_id) REFERENCES user (u_id) 
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    FOREIGN KEY (p_c_id) REFERENCES category (c_id) 
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB;

CREATE TABLE post_comment (
    pc_id BIGINT UNSIGNED NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
    pc_p_id BIGINT UNSIGNED NOT NULL,
    pc_u_login VARCHAR(50) NOT NULL UNIQUE,
    pc_text TEXT NOT NULL,
    pc_publishedAt DATETIME NOT NULL,
    FOREIGN KEY (pc_p_id) REFERENCES post (p_id) 
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB;

CREATE TABLE tag (
    t_id BIGINT UNSIGNED NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
    t_title VARCHAR(100) NOT NULL
) ENGINE = InnoDB;

CREATE TABLE post_tag (
    pt_t_id BIGINT UNSIGNED NOT NULL,
    pt_p_id BIGINT UNSIGNED NOT NULL,
    PRIMARY KEY (pt_p_id, pt_t_id),
    FOREIGN KEY (pt_t_id) REFERENCES tag (t_id) 
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    FOREIGN KEY (pt_p_id) REFERENCES post (p_id) 
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB;

-- user_role table 
INSERT INTO user_role(ur_role) VALUES ('admin');
INSERT INTO user_role(ur_role) VALUES ('writer');
INSERT INTO user_role(ur_role) VALUES ('user');

-- user_info table
-- INSERT INTO user_info(ui_name, ui_surname, ui_registeredAt) 
-- VALUES ('Alexey', 'Erevan', '2021-11-26 23:18:37');
-- INSERT INTO user_info(ui_name, ui_surname, ui_registeredAt) 
-- VALUES ('Writer', 'Just Writer', '2021-11-29 23:18:37');
-- INSERT INTO user_info(ui_name, ui_surname, ui_registeredAt) 
-- VALUES ('User', 'Just User', '2021-12-20 12:18:37');

-- user table
INSERT INTO user(u_ur_id, u_email, u_login, u_passwordHash) 
VALUES (1, 'noadmin@yandex.com', 'admin', 'a23er54');
-- INSERT INTO user(u_ur_id, u_ui_id, u_email, u_login, u_passwordHash) 
-- VALUES (3, 1, 'fushiguro@death.com', 'fushiguro', 'bober4ik');
-- INSERT INTO user(u_ur_id, u_ui_id, u_email, u_login, u_passwordHash) 
-- VALUES (2, 2, 'writer@death.com', 'writer', 'justwriter');
-- INSERT INTO user(u_ur_id, u_ui_id, u_email, u_login, u_passwordHash) 
-- VALUES (3, 3, 'user@gmail.com', 'user', 'a23er54iten1');

-- category table
INSERT INTO category(c_title, c_slug) VALUES ("Development","develop");
INSERT INTO category(c_title, c_slug) VALUES ("Admin", "admin");
INSERT INTO category(c_title, c_slug) VALUES ("Design", "design");

-- post table 

INSERT INTO post(p_u_id, p_c_id, p_title, p_content, p_published, p_slug, p_createdAt, p_publishedAt)
VALUES (2, 1, 'Расширенный материал по Java 8','Не секрет, что многие Java-программисты, начиная свой путь в индустрии, уделяют большое внимание «тяжелым» технологиям — OpenJPA, Spring, JAX-RS, EJB, WS-*,… Это дает возможность как скорее влиться в современные корпоративные проекты, так и максимизировать скорость роста зарплаты.
Многие из них в конце концов «спускаются» до технологий лежащих в основе указанных фреймворков — JDBC, Servlet API, NIO/NIO.2. Однако прискорбно, что зачастую не остается время на детальное изучение самого языка и возможностей платформы.Речь идет не о тонкостях или экзотике, а о том, что составляет существенную часть работы фреймворка: Servlet-контейнер использует множественные ClassLoader-ы, JPA2-провайдер использует манипулирование байткодом, абсолютное большинство библиотек используют Reflection API, всеобщее использование Generics только «усугубилось» с появлением функциональных интерфейсов (java.lang.function.*) и лямбд.
На недопонимание изначальной платформы (ClassLoader, Reflection API) накладываются «новвоведения» Java 5 (Generics), а теперь еще и Java 8 (методы в интерфейсах, ссылки на методы, лямбды, Stream API, JSR 308: Pluggable Type Systems). Надо обратить внимание на то, что Generics + Java 8 — это не просто языковые фичи, это частично переход к функциональному стилю программирования.', 1, '228603', '2014-7-4 16:38:37','2014-7-8 16:38:37');

INSERT INTO post(p_u_id, p_c_id, p_title, p_content, p_published, p_slug, p_createdAt, p_publishedAt)
VALUES (3, 1, 'The most interesting C# / .NET blogs and websites',
'Let's take a look at the list of information sources that can be useful for the C# / .NET developers. Our list includes blogs, repositories with source code, standards and accounts of developers who covers the deep aspects of the C# and .NET.',
 1, '599503', '2021-1-6 14:00:00', '2021-1-7 14:00:00');

INSERT INTO post(p_u_id, p_c_id, p_title, p_content, p_published, p_slug, p_createdAt, p_publishedAt)
VALUES (4, 2, 'Windows and Linux (Fedora KDE): difference, configuration, dual-boot',
'I reinstalled both Windows and Linux recently on a notebook PC, and I decided to write the summary article about my experience with both OS. I\'m also going to describe how to configure each OS via command-line and set up a dual-boot system.
I couldn\'t place very detailed information about each aspect in this article, so if you think there\'s something important missing - please send me a message, I\'ll do my best to update the article.',
 1, '597959', '2021-12-26 19:11:00', '2021-12-29 19:11:00');
 
INSERT INTO post(p_u_id, p_c_id, p_title, p_content, p_published, p_slug, p_createdAt, p_publishedAt)
VALUES (5, 3, 'Японский дизайн — больше, чем минимализм',
'Однородная страна, полная контрастов
Проблема, с которой я, как дизайнер презентаций, сталкиваюсь чаще всего, — это необходимость втиснуть большой объем информации на минимальное количество слайдов (так хочет клиент) и при этом разместить информацию эстетически привлекательно.
«Это невозможно» — думаю я, переставляя текстовые блоки, словно игрок в Тетрис. Но внезапно мне приходит мысль, что именно в этом преуспели японцы. Просто взгляните на их меню, на их журналы, брошюры или даже на улицы японских городов.',
 1, '596679', '2021-12-29 11:43:00','2022-1-1 11:43:00' );
 
 

-- comment table
INSERT INTO post_comment(pc_p_id, pc_u_login, pc_text, pc_publishedAt)
VALUES (1, 'Andrey2008', 'Cool', '2014-08-4 22:00:00');

-- tag table 

INSERT INTO tag(t_title) VALUES ('Big Data');
INSERT INTO tag(t_title) VALUES ('SQL');
INSERT INTO tag(t_title) VALUES ('Java');
INSERT INTO tag(t_title) VALUES ('C++');
INSERT INTO tag(t_title) VALUES ('C#');
INSERT INTO tag(t_title) VALUES ('.NET');
INSERT INTO tag(t_title) VALUES ('C');
INSERT INTO tag(t_title) VALUES ('C');

INSERT INTO tag(t_title) VALUES ('Windows');
INSERT INTO tag(t_title) VALUES ('Linux');
INSERT INTO tag(t_title) VALUES ('*nix');
INSERT INTO tag(t_title) VALUES ('Software');
INSERT INTO tag(t_title) VALUES ('Information Security');
INSERT INTO tag(t_title) VALUES ('Network technologies');

INSERT INTO tag(t_title) VALUES ('Web design');
INSERT INTO tag(t_title) VALUES ('Graphic design');
INSERT INTO tag(t_title) VALUES ('Game design');
INSERT INTO tag(t_title) VALUES ('Design');
INSERT INTO tag(t_title) VALUES ('Branding');
INSERT INTO tag(t_title) VALUES ('Working with video');
INSERT INTO tag(t_title) VALUES ('Website development');
INSERT INTO tag(t_title) VALUES ('Interfaces');

-- post_tag
INSERT INTO post_tag(pt_p_id, pt_t_id) VALUES (1, 3);
INSERT INTO post_tag(pt_p_id, pt_t_id) VALUES (2, 5);
INSERT INTO post_tag(pt_p_id, pt_t_id) VALUES (2, 6);
INSERT INTO post_tag(pt_p_id, pt_t_id) VALUES (3, 10);
INSERT INTO post_tag(pt_p_id, pt_t_id) VALUES (3, 11);
INSERT INTO post_tag(pt_p_id, pt_t_id) VALUES (3, 12);
INSERT INTO post_tag(pt_p_id, pt_t_id) VALUES (4, 15);
INSERT INTO post_tag(pt_p_id, pt_t_id) VALUES (4, 16);