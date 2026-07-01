# 🌍 DistanceOnEarth
Spring Boot приложение для сравнения геокоординат от Yandex Maps API и Dadata API.  
## 🔧 Настройка окружения  
Создайте файл .env в корне проекта:  
.env  

MYSQL_PASSWORD=*  
MYSQL_ROOT_PASSWORD=*  
MYSQL_USER=*  
DADATA_API_KEY=*  
DADATA_API_SECRET=*  
YANDEX_API_KEY=*  

Ключи:  
Yandex Maps API	developer.tech.yandex.ru	Ключ геокодера (Geocoder API)  
Dadata	dadata.ru/profile	API-ключ и Секретный ключ    
## 🚀 Сборка и Запуск  
docker compose up --build  

### bash  
```bash  
curl -X POST http://localhost:8080/api/geo/ -H "Content-Type: application/json" -d '{"address":"Москва, Красная площадь, 1"}'
```
### PowerShell
```PowerShell
curl.exe -X POST http://localhost:8080/api/geo/ -H "Content-Type: application/json" -d '{\"address\": \"Москва, ул Тверская, 1\"}'
```
