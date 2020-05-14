package lt.mk.mathgame.service;

import static lt.mk.mathgame.model.Operation.DIV;
import static lt.mk.mathgame.model.Operation.MINUS;
import static lt.mk.mathgame.model.Operation.MULTI;
import static lt.mk.mathgame.model.Operation.PLUS;

import java.util.ArrayList;
import java.util.List;

import lt.mk.mathgame.model.Operation;
import lt.mk.mathgame.model.PlaySettings;
import lt.mk.mathgame.model.PlayValues;
import lt.mk.mathgame.utils.RandomUtils;

public class MathService {

    private PlaySettings playSettings;

    private List<Operation> availableOperations = new ArrayList<>();


    public MathService(PlaySettings playSettings) {
        this.playSettings = playSettings;
        initSettings();
        initSaver();
    }

    private void initSettings() {
        availableOperations.clear();

        if (playSettings.isUserPlus()) {
            availableOperations.add(PLUS);
        }
        if (playSettings.isUserMinus()) {
            availableOperations.add(Operation.MINUS);
        }
        if (playSettings.isUserMulti()) {
            availableOperations.add(Operation.MULTI);
        }
        if (playSettings.isUserDiv()) {
            availableOperations.add(Operation.DIV);
        }

        if (availableOperations.isEmpty()) {
            availableOperations.add(PLUS);
            availableOperations.add(Operation.MINUS);
        }
    }

    private void initSaver() {

    }

    public PlayValues nextPlay() {

        switch (nextOperation()) {
            case PLUS:
                return nextPlus();
            case MINUS:
                return nextMinus();
            case MULTI:
                return nextMulti();
            default:
                return nextDiv();
        }

    }

    private Operation nextOperation() {
        return availableOperations.get(RandomUtils.next(availableOperations.size()));
    }


    private int nextFirst() {
        return RandomUtils.next(playSettings.getMaxFirst()) + 1;
    }

    private int nextSecond(int maxTotal) {
        return RandomUtils.next(Math.min(playSettings.getMaxSecond(), maxTotal)) + 1;
    }

    private PlayValues nextPlus() {
        int firstNumber = nextFirst();
        int secondNumber = nextSecond(playSettings.getMaxTotal() - firstNumber);
        return new PlayValues(PLUS, firstNumber, secondNumber, firstNumber + secondNumber);

    }

    private PlayValues nextMinus() {
        int firstNumber = nextFirst();
        int secondNumber = nextSecond(Math.min(playSettings.getMaxTotal() - firstNumber, firstNumber));
        return new PlayValues(MINUS, firstNumber, secondNumber, firstNumber - secondNumber);
    }

    private PlayValues nextMulti() {
        int firstNumber = nextFirst();
        int secondNumber = nextSecond(playSettings.getMaxSecond());
        return new PlayValues(MULTI, firstNumber, secondNumber, firstNumber * secondNumber);
    }

    private PlayValues nextDiv() {
        int firstNumber = nextFirst();
        int secondNumber = nextSecond(playSettings.getMaxSecond());
        return new PlayValues(DIV, firstNumber * secondNumber, firstNumber, secondNumber);
    }
}
