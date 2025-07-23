# RestApiNews

**RestApiNews** — это RESTful веб-приложение на Java, разработанное с использованием Spring Boot. Оно предоставляет API для управления новостями, категориями, связями между ними и пользователями с поддержкой аутентификации и авторизации.

## 📦 Технологии

- Java 17
- Spring Boot 2.2.4
- Spring Data JPA
- Spring Security
- MapStruct
- Hibernate
- Maven
- MySQL
- REST API

## 📁 Структура проекта

```
src/main/java/RestApiNews
│
├── controller          # REST-контроллеры
├── dto                # Data Transfer Objects
├── entity             # JPA-сущности
├── mapper             # MapStruct-мапперы
├── repository         # Spring Data JPA репозитории
├── security           # Конфигурация безопасности
├── service            # Бизнес-логика
└── Main.java          # Точка входа
```

## 🚀 Быстрый старт

1. **Клонируйте репозиторий:**
   ```bash
   git clone https://github.com/Vitaliy-54/Practice2025.git
   cd Practice2025
   ```

2. **Настройте базу данных** (например, MySQL) и укажите параметры в `application.yml`.

3. **Соберите и запустите проект:**
   ```bash
   mvn spring-boot:run
   ```

4. **API будет доступно по адресу:**
   ```
   http://localhost:8089/api
   ```

---

## 📚 Примеры API-запросов

### 📰 Новости

- **Получить все новости**
  ```
  GET /api/news
  ```

- **Добавить новость**
  ```http
  POST /api/news
  Content-Type: application/json

  {
    "title": "Название новости",
    "content": "Содержание новости",
    "publishDate": "2025-07-23"
  }
  ```

- **Обновить новость**
  ```http
  PUT /api/news/{id}
  Content-Type: application/json

  {
    "id": 1,
    "title": "Название новости",
    "content": "Содержание новости",
    "publishDate": "2025-07-23"
  }
  ```

- **Удалить новость**
  ```
  DELETE /api/news/{id}
  ```

- **Найти новость по ID**
  ```
  GET /api/news/{id}
  ```

---

### 🗂 Категории

- **Получить все категории**
  ```
  GET /api/categories
  ```

- **Добавить категорию**
  ```http
  POST /api/categories
  Content-Type: application/json

  {
    "name": "Название вашей категории"
  }
  ```

- **Обновить категорию**
  ```http
  PUT /api/categories/{id}
  Content-Type: application/json

  {
    "id": 1,
    "name": "Обновленное название категории"
  }
  ```

- **Удалить категорию**
  ```
  DELETE /api/categories/{id}
  ```

- **Найти категорию по ID**
  ```
  GET /api/categories/{id}
  ```

---

### 🔗 Связи новость-категория

- **Получить все связи**
  ```
  GET /api/newscategories
  ```

- **Добавить связь**
  ```http
  POST /api/newscategories
  Content-Type: application/json

  {
    "news": {
      "id": 1
    },
    "categories": {
      "id": 2
    }
  }
  ```

- **Обновить связь**
  ```http
  PUT /api/newscategories/{id}
  Content-Type: application/json

  {
    "id": 3,
    "news": {
      "id": 5
    },
    "categories": {
      "id": 2
    }
  }
  ```

- **Удалить связь**
  ```
  DELETE /api/newscategories/{id}
  ```

- **Найти связь по ID**
  ```
  GET /api/newscategories/{id}
  ```

---

### 👤 Пользователи

- **Получить всех пользователей**
  ```
  GET /api/users
  ```

- **Зарегистрировать нового пользователя**
  ```http
  POST /api/users/registration
  Content-Type: application/json

  {
    "username": "newUser",
    "password": "password123",
    "authority": "ROLE_USER"
  }
  ```

- **Удалить пользователя по логину**
  ```
  DELETE /api/users/delete/{username}
  ```

---

## 🔐 Аутентификация

Некоторые endpoints могут требовать авторизации пользователей. Убедитесь, что вы авторизованы перед вызовом защищённых маршрутов.

## 🧪 Тестирование

Можно использовать Postman или аналогичный инструмент для отправки запросов и тестирования API.

---

_Разработано в рамках технологической практики 2025 года._
