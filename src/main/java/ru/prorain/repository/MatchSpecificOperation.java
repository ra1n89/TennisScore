package ru.prorain.repository;

import ru.prorain.entity.Match;

import java.util.List;

public interface MatchSpecificOperation {

    List<Match> getFilteredMatchesByPlayerName(String filterByPlayerName);

    List<Match> getFilteredBySize(int page);

    Long getPagesAmount();
}
