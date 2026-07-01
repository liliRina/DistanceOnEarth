package my_project.DistanceOnEarth;

import io.netty.channel.ChannelOption;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.*;
import org.springframework.beans.factory.annotation.Value;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

@Configuration
public class WebClientConfig {

    @Bean(name = "yandexWebClient")
    public WebClient yandexWebClient() {

        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000) //соединение
                .responseTimeout(Duration.ofSeconds(10));

        return WebClient.builder()
                .baseUrl("https://geocode-maps.yandex.ru/v1/")
                .defaultHeader("Accept", "application/json")
                .defaultHeader("User-Agent", "MyApp/1.0")
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }

    @Bean(name = "dadataWebClient")
    public WebClient dadataWebClient(
            @Value("${dadata.api.key}") String apiKey,
            @Value("${dadata.api.secret}") String apiSecret) {

        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000) //соединение
                .responseTimeout(Duration.ofSeconds(10));

        return WebClient.builder()
                .baseUrl("https://cleaner.dadata.ru/api/v1/clean/address")
                .defaultHeader("Accept", "application/json")
                .defaultHeader("Content-Type", "application/json")
                .defaultHeader("Authorization", "Token " + apiKey)
                .defaultHeader("X-Secret", apiSecret)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }
}
