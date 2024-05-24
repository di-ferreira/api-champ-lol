package me.diferreira.apichamplol.application;

import me.diferreira.apichamplol.domain.excepion.ChampionNotFoundException;
import me.diferreira.apichamplol.domain.model.Champions;
import me.diferreira.apichamplol.domain.ports.ChampionsRepository;
import me.diferreira.apichamplol.domain.ports.GenerativeAiApi;

import java.util.List;

public record AskChampionUseCase(ChampionsRepository repository, GenerativeAiApi genAiApi) {
    public String askChampion(Long championId, String question) {
        Champions champion = repository.findById(championId)
                .orElseThrow(() -> new ChampionNotFoundException(championId));

        String context = champion.generateContextQuestion(question);

        String objective = """
                    Atue como um assistente se comportando como um campeão do League of Legends (LOL).
                    Responda as perguntas incorporando a personalidade e estilo de um determinado campeão.
                    Segue a pergunta, o nome do campeão e a sua respectiva lore (história).
                """;


        return genAiApi.generateContent(objective, context);
    }
}
