<div align="center">

# Серверная часть

</div>

## Запуск

### 1. Установите Docker и Docker-compose

Следуйте инструкции с [Официального сайта](https://docs.docker.com/engine/install)

### 2. Напишите docker-compose.yml

Пример написанного файла:

```yml
services:
  rkib-appointment-server:
    container_name: rkib-appointment-server
    image: zalimannard/rkib-appointment-server
    ports:
      - "8116:8116"
    environment:
      # Ссылка на вашу базу данных
      - DB_URL=jdbc:oracle:thin:@8.8.8.8:1521/XEPDB1
      # Пользователь в базе данных
      - DB_USER=admin
      # Пароль этого пользователя
      - DB_PASSWORD=admin
    restart: unless-stopped
```

### 3. Запустите

```shell
docker-compose up -d
```

## Ссылки

## Ссылки

- https://docs.docker.com/engine/install - официальный сайт Docker. Установка Docker
- https://hub.docker.com/repository/docker/zalimannard/rkib-appointment-server - Docker-контейнер с этим приложением