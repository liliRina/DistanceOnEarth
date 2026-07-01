CREATE TABLE IF NOT EXISTS distance_requests (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    address TEXT NOT NULL,
    yandex_lat DOUBLE,
    yandex_lon DOUBLE,
    dadata_lat DOUBLE,
    dadata_lon DOUBLE,
    distance DOUBLE
);
