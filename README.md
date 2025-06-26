# 📊 Microservices Analytics Project

Микросервисный проект, состоящий из двух сервисов:

- `event-service` — основной сервис событий (описание API в `openapi.yaml`);
- `stat-service` — сервис сбора статистики посещений (`stat-service.json`).

## 🧱 Архитектура

```text
[Client] --> [event-service] --> [stat-service]
event-service при каждом обращении к своим публичным endpoint'ам отправляет информацию о посещении в stat-service. Таким образом, система централизованно собирает и анализирует статистику запросов.

📦 Stat Service
stat-service — отдельный микросервис, предоставляющий API для:

регистрации обращений к endpoint'ам;

получения агрегированной статистики.

🔌 Доступные endpoints
POST /hit
Регистрирует обращение к endpoint'у другого сервиса.

Тело запроса:

json
Копировать
Редактировать
{
  "app": "event-service",
  "uri": "/events/1",
  "ip": "192.163.0.1",
  "timestamp": "2022-09-06 11:00:23"
}
Ответ: 201 Created — информация успешно сохранена.

GET /stats
Получение статистики по посещениям.

Query-параметры:

start (обязательно): начало диапазона (yyyy-MM-dd HH:mm:ss)

end (обязательно): конец диапазона (yyyy-MM-dd HH:mm:ss)

uris (необязательно): список URI

unique (необязательно): true — учитывать только уникальные IP

Пример ответа:

json
Копировать
Редактировать
[
  {
    "app": "event-service",
    "uri": "/events/1",
    "hits": 6
  }
]
⚠️ Обратите внимание: параметры start и end нужно URL-кодировать (например, с помощью URLEncoder.encode в Java).

🛠 Пример вызовов
Отправка hit'а
bash
Копировать
Редактировать
curl -X POST http://localhost:9090/hit \
  -H "Content-Type: application/json" \
  -d '{
    "app": "event-service",
    "uri": "/events/1",
    "ip": "192.163.0.1",
    "timestamp": "2022-09-06 11:00:23"
  }'
Получение статистики
bash
Копировать
Редактировать
curl -G http://localhost:9090/stats \
  --data-urlencode "start=2022-09-06 00:00:00" \
  --data-urlencode "end=2022-09-07 00:00:00" \
  --data-urlencode "uris=/events/1" \
  --data-urlencode "unique=true"
📁 Структура проекта
perl
Копировать
Редактировать
.
├── event-service/           # Основной сервис событий
├── stat-service/            # Сервис сбора статистики
├── openapi.yaml             # Спецификация event-service
├── stat-service.json        # Спецификация stat-service
├── README.md                # Этот файл
