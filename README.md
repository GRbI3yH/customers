# customers

# Введение
Необходимо разработать web-сервис, который будет создавать, изменять и находить
клиентов в базе данных.
# Компоненты
1. База данных (DB). Структура БД находится в файле schema_ddl.sql
2. Web-сервис (CustomerService), который реализует следующие операции:
a. Создание клиента
b. Изменение фактического адреса клиента
c. Поиск клиента по имени и фамилии
# Что требуется от кандидата:
- Развернуть БД
- Создать предложенную схему таблиц (schema_ddl.sql)
- Создать web-сервис (REST) CustomerService, который будет:
- Принимать GET, POST и PUT запросы
- Реализовывать логику создания, изменения и поиска клиента согласно
пунктам 2.a, 2.b, 2.c
# Требования к реализации
- Язык программирования Java
- БД - postgres
- Для реализации кейсов 2.a, 2.b, 2.c может быть использовано любое
количество методов и маршрутов
- Сервис CustomerService должен уметь обрабатывать несколько запросов
одновременно
- Наличие unit тестов
- Наличие комментариев в ключевых местах кода


CREATE TABLE address (
id bigserial NOT NULL,
contry varchar(255),
region varchar(255),
city varchar(255),
street varchar(255),
house varchar(255),
flat varchar(255),
created timestamp,
modified timestamp,
CONSTRAINT pk_address PRIMARY KEY (id)
);

CREATE TABLE customer (
id bigserial NOT NULL,
registred_address_id bigint NOT NULL,
actual_address_id bigint NOT NULL,
first_name varchar(255) NULL,
last_name varchar(255) NULL,
middle_name varchar(255) NULL,
sex varchar(6) NOT NULL,
CONSTRAINT ck_customer_sex CHECK (((sex)::text = ANY ((ARRAY['male'::character
varying, 'female'::character varying])::text[]))),
CONSTRAINT pk_customer PRIMARY KEY (id),
CONSTRAINT fk_registred_address_id FOREIGN KEY (registred_address_id)
REFERENCES address(id) ON UPDATE RESTRICT ON DELETE RESTRICT,
CONSTRAINT fk_actual_address_id FOREIGN KEY (actual_address_id) REFERENCES
address(id) ON UPDATE RESTRICT ON DELETE RESTRICT
)
