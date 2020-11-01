package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MessageGeneratorImpl implements MessageGenerator {

    private static final Logger log = LoggerFactory.getLogger(MessageGeneratorImpl.class);

    private final Game game;

    @Autowired
    public MessageGeneratorImpl(Game game) {
        this.game = game;
    }

    @PostConstruct
    public void game() {
        log.debug(String.valueOf(game));
    }

    @Override
    public String getMainMessage() {
        return "Number is between " +
                game.getSmallest() +
                " and " +
                game.getBiggest() +
                ". Can you guess it?";
    }

    @Override
    public String getResultMessage() {

        if (game.isGameWon()) {
            return "CONGRATULATIONS!! You guessed it! the number was " + game.getNumber();
        } else if (game.isGameLost()) {
            return "That's to bad! you don't seem to be smart enough for this. The number was " + game.getNumber();
        } else if (!game.isValidNumberRange()) {
            return "Invalid number!";
        } else if (game.getRemainingGuesses() == game.getGuessCount()) {
            return "what is your first guess?";
        } else {
            String direction = "Lower!";
            if (game.getGuess() < game.getNumber()) {
                direction = "Higher!";
            }
            return direction + "You have " + game.getRemainingGuesses() + " guesses left";
        }
    }
}
