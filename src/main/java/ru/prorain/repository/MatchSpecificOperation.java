package ru.prorain.repository;

import ru.prorain.entity.Match;

import java.util.List;

public interface MatchSpecificOperation {

    public List<Match> getFilteredMatchesByPlayerName(String filterByPlayerName);
}
