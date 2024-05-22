package me.diferreira.apichamplol.domain.ports;

import me.diferreira.apichamplol.domain.model.Champions;

import java.util.List;
import java.util.Optional;

public interface ChampionsRepository {
    List<Champions> findAll();

    Optional<Champions> findById(Long id);
}
