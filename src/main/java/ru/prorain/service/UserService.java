package ru.prorain.service;

import ru.prorain.dto.MatchDto;
import ru.prorain.entity.User;
import ru.prorain.repository.CrudRepository;
import ru.prorain.repository.UserRepository;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class UserService {

    private static final UserService USER_SERVICE = new UserService();
    CrudRepository<User, Integer> userRepository = UserRepository.getInstance();
    ConcurrentMap<UUID, MatchDto> concurrentHashMap = new ConcurrentHashMap<>();

    private UserService() {

    }

    public MatchDto save(User firstPlayer, User secondPlayer) {
        User firstPlayerWithId;
        User secondPlayerWithId;

        firstPlayerWithId = userRepository.save(firstPlayer);
        secondPlayerWithId = userRepository.save(secondPlayer);

        MatchDto match = new MatchDto(firstPlayerWithId, secondPlayerWithId, 0, 0, 0, 0, 0, 0);
        concurrentHashMap.put(match.getId(), match);
        return match;
    }


    public User update(User id) {
        return null;
    }


    public Collection getAll() {
        return null;
    }

    public User getOne() {

        return null;
    }

    public MatchDto getMatch(UUID uuid) {

        return concurrentHashMap.get(uuid);
    }

    public ConcurrentMap<UUID, MatchDto> getList() {
        return concurrentHashMap;
    }

    public static UserService getInstance() {
        return USER_SERVICE;
    }

    public void updateScore(int id, UUID uuid) {
        MatchDto match = getMatch(uuid);
        User player1 = match.getPlayer1();
        User player2 = match.getPlayer2();
        int sets1 = match.getSets1();
        int sets2 = match.getSets2();
        int game1 = match.getGame1();
        int game2 = match.getGame2();
        int score1 = match.getScore1();
        int score2 = match.getScore2();
        boolean isPlayerOneWinScore = player1.getId() == id;
        boolean isPlayerTwoWinScore = player2.getId() == id;


            //Подсчёт очков в ситуации ровно (первый игрок выигрывает очко)
        if (isPlayerOneWinScore && (score1 >= 40 && score2 >= 40)) {
            //метод подсчёта в ситуации ровно
            score1 = scoreCountInBothHaveForthy(score1, score2);
            //устанавливаем счёт и геймы в матче
            setScoreAndGamesAfterCounting(match, score1, isPlayerOneWinScore, game1, game2);

                   //Подсчёт очков в ситуации ровно (второй игрок выигрывает очко)
        } else if (isPlayerTwoWinScore && (score1 >= 40 && score2 >= 40)) {

            score2 = scoreCountInBothHaveForthy(score2, score1);

            setScoreAndGamesAfterCounting(match, score2, isPlayerOneWinScore, game2);

        } else if (isPlayerOneWinScore && (score2 <= 40 && score1 <= 40)) {

            score1 = scoreCount(score1);

            setScoreAndGamesAfterCounting(match, score1, isPlayerOneWinScore, game1);

        } else if (isPlayerTwoWinScore && (score2 <= 40 && score1 <= 40)) {
            score2 = scoreCount(score2);

            setScoreAndGamesAfterCounting(match, score2, isPlayerOneWinScore, game2);
        }
    }

    private int scoreCount(int score) {
        switch (score) {
            case 0:
                score = 15;
                return score;
            case 15:
                score = 30;
                return score;
            case 30:
                score = 40;
                return score;
            case 40:
                score = 0;
                return score;
        }
        return score;
    }

    private int scoreCountInBothHaveForthy(int score1, int score2) {
        //увеличиваем счёт догоняющего игрока
        score1 += 10;
        //считаем если разрыва больше двух игр (20 очков) то обнуляем счёт победившего игрока
        if (score1 - score2 == 20) {
            score1 = 0;
            return score1;
        }
        return score1;
    }

    private void setScoreAndGamesAfterCounting(MatchDto match, int score, boolean isPlayerOneWinScore, int game1, int game2) {

        if (isPlayerOneWinScore) {
            if (score == 0) {
                game1++;
                match.setScore1(0);
                match.setScore2(0);
                match.setGame1(game1);
            }
            match.setScore1(score);
        } else {
            if (score == 0) {
                game1++;
                match.setScore2(0);
                match.setScore1(0);
                match.setGame2(game1);
            }
            match.setScore2(score);
        }

    }
}
