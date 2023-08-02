<div class="myWrapper" align="center" markdown="1">

# :syringe: Система записи на приём в медицинское учреждение

*ВКР К(П)ФУ, ИВМиИТ, Прикладная информатика*

</div>

https://github.com/zalimannard/rkib-appointment/assets/90920120/e8d42bfc-a734-45eb-b332-a70df2e10921

## :computer: Запуск

Для запуска понадобится docker-compose. Создайте docker-compose.yml со следующим содержимым:

```yml
version: "3.9"
services:
  database:
    image: postgres:latest
    container_name: rkib-appointment-db
    ports:
      - "5432:5432"
    volumes:
      - /var/lib/postgresql/data/
    environment:
      - POSTGRES_DB=rkib-appointment
      - POSTGRES_USER=user
      - POSTGRES_PASSWORD=password
    restart: unless-stopped

  backend:
    container_name: rkib-appointment-backend
    image: zalimannard/rkib-appointment-backend
    ports:
      - "8116:8116"
    environment:
      - DB_URL=jdbc:postgresql://database:5432/rkib-appointment
      - DB_USER=user
      - DB_PASSWORD=password
      - DDL_BEHAVIOUR=update
      - ADMIN_EMAIL=admin@admin.ru
      - ADMIN_PASSWORD=admin
    restart: unless-stopped
    depends_on:
      - database
  
  frontend:
    container_name: rkib-appointment-frontend
    image: zalimannard/rkib-appointment-frontend
    ports:
      - "4173:4173"
    environment:
      - VITE_API_URL=http://backend:8116
    restart: unless-stopped
    depends_on:
      - backend
```

Теперь программу можно запустить систему:

```shell
docker-compose up -d
```

## :deciduous_tree: Структура

Поскольку это дипломный проект, то добро пожаловать в монорепу. Структура файлов по подпроектам следующая:

```shell
.
├── backend - Серверная часть на Java/Spring
├── database - Описание схемы данных. Реализация через JPA
├── deploy - Файл для запуска программы
├── documents - Разные бумажки нужные
├── frontend - Клиентская часть на VueJS/Vuetify
└── tests
    ├── api - Некоторые тесты для API
    └── ui - Аж один UI-тест. Мне не хотелось этим заниматься, но по методичке надо было хоть какое-то
```
