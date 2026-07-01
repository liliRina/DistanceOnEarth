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

Ключи:  
Yandex Maps API	developer.tech.yandex.ru	Ключ геокодера (Geocoder API)  
Dadata	dadata.ru/profile	API-ключ и Секретный ключ    
🚀 Запуск  
docker compose up --build  
