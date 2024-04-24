CREATE TABLE users
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(20)  NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    fio      VARCHAR(100) NOT NULL,
    role     VARCHAR(20)  NOT NULL DEFAULT 'ROLE_USER',
    status   VARCHAR(10)  NOT NULL DEFAULT 'ACTIVE'
);

INSERT INTO users (username, password, fio, role, status)
VALUES ('admin', '$2a$12$G.4d7O91fOwN4cZIuMnAqu1hMTF4dRV8uikR8cXaj5X1kAYnXrLcW', 'Алексеев Алексей', 'ROLE_ADMIN',
        'ACTIVE'),
       ('user_1', '$2a$12$c2EgYvwXXhLawzeOE1TteOcbEAFPFa.ajcope0y6UelKxbvX4LLhS', 'Иванов Иван', 'ROLE_USER',
        'ACTIVE'),
       ('user_2', '$2a$12$t3Zs5bbc9GwdazeBKexTgONTucXMcfMbVF76Ytt.zue4WrXP6RGda', 'Петров Пётр', 'ROLE_USER',
        'ACTIVE'),
       ('user_3', '$2a$12$X.MkVN5OznUhB/AzbcrISe2hlI3DrugyPydI.BKyv.u555wP0pwsa', 'Сергеев Сергей', 'ROLE_STRIKED',
        'ACTIVE');

CREATE TABLE news
(
    id      SERIAL PRIMARY KEY,
    header  VARCHAR(100)  NOT NULL,
    text    VARCHAR(1000) NOT NULL,
    date    TIMESTAMP     NOT NULL,
    user_id INTEGER       NOT NULL REFERENCES users (ID)
);

INSERT INTO news (header, text, date, user_id)
VALUES ('Российские студенты победили в чемпионате мира по программированию ICPC',
        'Команды факультета компьютерных наук Высшей школы экономики FFTilted и Undertrained+Overpressured победили ' ||
        'на Международной студенческой олимпиаде по программированию ICPC. С 14 по 19 апреля в египетском Луксоре ' ||
        'состоялись два финала состязания: в 46-м финале россияне получили золотые медали, а в 47-м — стали чемпионами' ||
        ' мира и получили золотые медали.', '19.04.2024 18:22', 2),
       ('В «Яндекс Карты» добавили трёхмерные модели скверов и парков',
        'На платформе «Яндекса Карты» добавились более 4 млн трёхмерных моделей деревьев — они составили реалистичные ' ||
        'зелёные зоны в Москве внутри линии МКАД и Санкт-Петербурге, в том числе в Петергофе. Пользователям сервиса будет' ||
        ' удобнее подбирать места для прогулок или оценивать район при выборе недвижимости.', '19.04.2024 19:45', 2),
       ('Microsoft запретит настраивать Edge на неактивированных Windows 11',
        'Пользователи тестовых сборок Microsoft Edge на неактивированных Windows 11 заметили, что потеряли доступ к ' ||
        'настройкам браузера. Видимо, этот сомнительной эффективности шаг призван подтолкнуть людей к активации ' ||
        'операционной системы. Microsoft разрешает пользоваться неактивированными версиями Windows 11, но с некоторыми ' ||
        'ограничениями — и одним из них, предположительно, станет блокировка настроек браузера Edge. Пока это лишь ' ||
        'эксперимент в предварительной версии браузера, который может закончиться, не выйдя в релиз.',
        '18.04.2024 15:35', 3);
