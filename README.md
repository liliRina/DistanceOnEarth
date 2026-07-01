🌍 DistanceOnEarth
Spring Boot приложение для сравнения геокоординат от Yandex Maps API и Dadata API.
🔧 Настройка окружения
Создайте файл .env в корне проекта:
.env

MYSQL_PASSWORD=*
MYSQL_ROOT_PASSWORD=*
MYSQL_USER=*
DADATA_API_KEY=*
DADATA_API_SECRET=*
YANDEX_API_KEY=*

Где взять API-ключи?
Table
Сервис	Ссылка	Что нужно
Yandex Maps API	developer.tech.yandex.ru	Ключ геокодера (Geocoder API)
Dadata	dadata.ru/profile	API-ключ и Секретный ключ
🚀 Запуск
bash
# Сборка и запуск всех сервисов
docker compose up --build


📡 API
Расчёт расстояния между координатами
POST /api/geo
bash
curl -X POST http://localhost:8080/api/geo \
-H "Content-Type: application/json" \
-d '{"address":"Москва, Красная площадь, 1"}'
Ответ:
JSON
{
"address": "Москва, Красная площадь, 1",
"yandexLat": 55.75396,
"yandexLon": 37.620393,
"dadataLat": 55.75396,
"dadataLon": 37.620393,
"distanceMeters": 0
}