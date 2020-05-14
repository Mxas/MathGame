package lt.mk.mathgame.model;

public class PlayValues {
    private Operation operation;
    private int first;
    private int second;

    private int answer;

    public PlayValues(Operation operation, int first, int second, int answer) {
        this.operation = operation;
        this.first = first;
        this.second = second;
        this.answer = answer;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public Operation getOperation() {
        return operation;
    }
}
