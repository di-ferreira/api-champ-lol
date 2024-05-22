package me.diferreira.apichamplol.application;

import me.diferreira.apichamplol.domain.model.Champions;
import me.diferreira.apichamplol.domain.ports.ChampionsRepository;

import java.util.List;

public record ListChampionsUseCase(ChampionsRepository repository) {
    public List<Champions> findAll(){
        return repository.findAll();
    }
}
