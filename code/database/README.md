<div align="center">

  # База данных

</div>

## Схема данных

![Логическая схема данных](documentation/er-logical.png)

## Начать

Написал несколько скриптов для работы с базой данных. Проверялись на Oracle Database 21c

- [Генерация базы данных](scripts/database-generation.sql)
- [Удаление всех таблиц](scripts/drop-all-tables.sql)

## Установка Oracle XE

### 1. Установка Docker и Docker-compose

Следуйте инструкции с [Официального сайта](https://docs.docker.com/engine/install)

### 2. Создание docker-compose.yml

```yml
services:
  oracle-xe:
    image: gvenzl/oracle-xe:latest
    container_name: oracle-xe
    ports:
      - "1521:1521"
    environment:
      - ORACLE_PASSWORD=<ВАШ ПАРОЛЬ>
    volumes:
      - ./oracle-volume:/opt/oracle/oradata:rw
    restart: "unless-stopped"
```

### 3. Запуск

```shell
docker-compose up -d
```

### Параметры для подключения

Администратор базы данных: sys

Пароль: <ваш пароль>

Порт: 1521

Имя службы: XEPDB1

### Создание пользователя

```sql
alter session set "_ORACLE_SCRIPT"=true;
CREATE USER <ИМЯ ПОЛЬЗОВАТЕЛЯ> identified by <ПАРОЛЬ>;
grant create session to <ИМЯ ПОЛЬЗОВАТЕЛЯ>;
grant create table to <ИМЯ ПОЛЬЗОВАТЕЛЯ>;
grant create procedure to <ИМЯ ПОЛЬЗОВАТЕЛЯ>;
grant create trigger to <ИМЯ ПОЛЬЗОВАТЕЛЯ>;
grant create view to <ИМЯ ПОЛЬЗОВАТЕЛЯ>;
grant create sequence to <ИМЯ ПОЛЬЗОВАТЕЛЯ>;
grant alter any table to <ИМЯ ПОЛЬЗОВАТЕЛЯ>;
grant alter any procedure to <ИМЯ ПОЛЬЗОВАТЕЛЯ>;
grant alter any trigger to <ИМЯ ПОЛЬЗОВАТЕЛЯ>;
grant alter profile to <ИМЯ ПОЛЬЗОВАТЕЛЯ>;
grant delete any table to <ИМЯ ПОЛЬЗОВАТЕЛЯ>;
grant drop any table to <ИМЯ ПОЛЬЗОВАТЕЛЯ>;
grant drop any procedure to <ИМЯ ПОЛЬЗОВАТЕЛЯ>;
grant drop any trigger to <ИМЯ ПОЛЬЗОВАТЕЛЯ>;
grant drop any view to <ИМЯ ПОЛЬЗОВАТЕЛЯ>;
grant drop profile to <ИМЯ ПОЛЬЗОВАТЕЛЯ>;

grant select on sys.v_$session to <ИМЯ ПОЛЬЗОВАТЕЛЯ>;
grant select on sys.v_$sesstat to <ИМЯ ПОЛЬЗОВАТЕЛЯ>;
grant select on sys.v_$statname to <ИМЯ ПОЛЬЗОВАТЕЛЯ>;
grant SELECT ANY DICTIONARY to <ИМЯ ПОЛЬЗОВАТЕЛЯ>;
```

## Ссылки

- https://docs.docker.com/engine/install - официальный сайт Docker. Установка Docker
- https://hub.docker.com/r/gvenzl/oracle-xe - Docker-контейнер с Oracle XE
