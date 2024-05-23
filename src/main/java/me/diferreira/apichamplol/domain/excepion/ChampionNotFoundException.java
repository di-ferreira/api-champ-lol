package me.diferreira.apichamplol.domain.excepion;

public class ChampionNotFoundException extends RuntimeException {

    public ChampionNotFoundException(Long championId) {
        super("Champion %d not found. ".formatted(championId));
    }

}
