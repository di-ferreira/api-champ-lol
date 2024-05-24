package me.diferreira.apichamplol;

import me.diferreira.apichamplol.application.AskChampionUseCase;
import me.diferreira.apichamplol.application.ListChampionsUseCase;
import me.diferreira.apichamplol.domain.ports.ChampionsRepository;
import me.diferreira.apichamplol.domain.ports.GenerativeAiApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableFeignClients
@SpringBootApplication
public class ApiChampLolApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiChampLolApplication.class, args);
    }

    @Bean
    public ListChampionsUseCase providerListChampionsUseCase(ChampionsRepository repository) {
        return new ListChampionsUseCase(repository);
    }

    @Bean
    public AskChampionUseCase providerAskChampionsUseCase(ChampionsRepository repository, GenerativeAiApi genAiApi) {
        return new AskChampionUseCase(repository, genAiApi);
    }

}
