# TestRosatom
# File Storage Service

## Описание
Проект представляет собой сервис для хранения файлов с возможностью их создания, получения и отображения информации о них. Приложение реализовано на Java с использованием Spring Boot, PostgreSQL и Docker.

## Требования
1. **Docker** — необходим для сборки и запуска контейнеров.
2. **Docker Compose** — для автоматической настройки и управления многоконтейнерными приложениями.

## Установка Docker

### Для Windows и Mac:
1. Перейдите на [официальный сайт Docker](https://www.docker.com/products/docker-desktop) и скачайте Docker Desktop.
2. Установите и запустите Docker Desktop.

### Для Linux:
1. Выполните следующие команды в терминале для установки Docker:
    ```bash
    sudo apt-get update
    sudo apt-get install docker-ce docker-ce-cli containerd.io
    ```
2. Для установки Docker Compose, выполните:
    ```bash
    sudo curl -L "https://github.com/docker/compose/releases/download/1.29.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
    sudo chmod +x /usr/local/bin/docker-compose
    ```

### Проверка установки:
1. Проверьте установку Docker:
    ```bash
    docker --version
    ```
2. Проверьте установку Docker Compose:
    ```bash
    docker-compose --version
    ```

## Запуск проекта

1. **Сборка Docker-образа**:
    Перейдите в корневую директорию проекта и выполните команду для сборки Docker-образа:
    ```bash
    docker build -t filestorage-app .
    ```

2. **Запуск контейнера**:
    Используйте `docker-compose` для запуска приложения:
    ```bash
    docker-compose up --build
    ```

3. После успешного запуска сервис будет доступен по адресу:
    ```
    http://localhost:8080
    ```

## API Методы

- **GET /api/files/all** — получить список всех файлов с пагинацией и сортировкой.
- **GET /api/files/{id}** — получить информацию о файле по его ID.
- **POST /api/files** — загрузить новый файл (с данными в формате JSON).

## Пример запроса для получения всех файлов с пагинацией:
**GET /api/files?page=0&size=10&sortDirection=desc&sortBy=creationDate**

## Примеры запросов для Postman

### Получение всех файлов с пагинацией и сортировкой
**GET** `/api/files`

Параметры:
- `page`: номер страницы (например, 0)
- `size`: количество элементов на странице (например, 10)
- `sortDirection`: направление сортировки (например, `desc` или `asc`)
- `sortBy`: поле для сортировки (например, `creationDate`)

**Пример**:  
    ```
    http://localhost:8080/api/files?page=0&size=10&sortDirection=desc&sortBy=creationDate
    ```

### Получение файла по ID
**GET** `http://localhost:8080/api/files/{id}`


### Создание файла
**POST** `http://localhost:8080/api/files`


Тело запроса (JSON):

Важно:
Все поля обязательны и не могут быть пустыми. Если какое-либо поле отсутствует или пусто, запрос не пройдет валидацию и вернёт ошибку.

```json
{
  "title": "Sample File",
  "creationDate": "2024-09-06T14:30:00",
  "description": "This is a sample file",
  "fileData": "SGVsbG8gd29ybGQh"  // Пример Base64-кодированного файла
}

Важно:
Все поля обязательны и не могут быть пустыми. Если какое-либо поле отсутствует или пусто, запрос не пройдет валидацию и вернёт ошибку.

## Контакты
Если у вас есть вопросы или предложения, обращайтесь на [61457leize@gmail.com].
