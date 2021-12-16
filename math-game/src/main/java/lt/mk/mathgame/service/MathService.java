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


    private int nextFirst(Operation op) {
        return RandomUtils.next(playSettings.getValues().get(op).getMaxFirst()) + 1;
    }

    private int nextSecond(Operation op, int maxTotal) {
        return RandomUtils.next(Math.min(playSettings.getValues().get(op).getMaxSecond(), maxTotal)) + 1;
    }

    private PlayValues nextPlus() {
        int firstNumber = nextFirst(PLUS);
        int secondNumber = nextSecond(PLUS,playSettings.getValues().get(PLUS).getMaxTotal() - firstNumber);
        return new PlayValues(PLUS, firstNumber, secondNumber, firstNumber + secondNumber);

    }

    private PlayValues nextMinus() {
        int firstNumber = nextFirst(MINUS);
        int secondNumber = nextSecond(MINUS, Math.min(playSettings.getValues().get(MINUS).getMaxTotal() - firstNumber, firstNumber));
        return new PlayValues(MINUS, firstNumber, secondNumber, firstNumber - secondNumber);
    }

    private PlayValues nextMulti() {
        int firstNumber = nextFirst(MULTI);
        if (firstNumber > playSettings.getValues().get(MULTI).getMaxTotal()){
            return new PlayValues(MULTI, firstNumber, 1, firstNumber);
        }

        if (firstNumber == 0){
            int secondNumber = nextSecond(MULTI,  playSettings.getValues().get(MULTI).getMaxTotal());
            return new PlayValues(MULTI, firstNumber, secondNumber, 0);
        }

        int secondNumber = nextSecond(MULTI,  playSettings.getValues().get(MULTI).getMaxTotal() / firstNumber);
        return new PlayValues(MULTI, firstNumber, secondNumber, firstNumber * secondNumber);
    }

    private PlayValues nextDiv() {
        int firstNumber = nextFirst(DIV);
        int secondNumber = nextSecond(DIV, playSettings.getValues().get(DIV).getMaxSecond());
        return new PlayValues(DIV, firstNumber * secondNumber, firstNumber, secondNumber);
    }
}
