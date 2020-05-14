package lt.mk.mathgame.model;

public class PlaySettings {

    private int maxTotal;
    private int maxFirst;
    private int maxSecond;

    private boolean userPlus;
    private boolean userMinus;
    private boolean userMulti;
    private boolean userDiv;
    private String imagesPath;

    public int getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public int getMaxFirst() {
        return maxFirst;
    }

    public void setMaxFirst(int maxFirst) {
        this.maxFirst = maxFirst;
    }

    public int getMaxSecond() {
        return maxSecond;
    }

    public void setMaxSecond(int maxSecond) {
        this.maxSecond = maxSecond;
    }

    public boolean isUserPlus() {
        return userPlus;
    }

    public void setUserPlus(boolean userPlus) {
        this.userPlus = userPlus;
    }

    public boolean isUserMinus() {
        return userMinus;
    }

    public void setUserMinus(boolean userMinus) {
        this.userMinus = userMinus;
    }

    public boolean isUserMulti() {
        return userMulti;
    }

    public void setUserMulti(boolean userMulti) {
        this.userMulti = userMulti;
    }

    public boolean isUserDiv() {
        return userDiv;
    }

    public void setUserDiv(boolean userDiv) {
        this.userDiv = userDiv;
    }

    public String getImagesPath() {
        return imagesPath;
    }

    public void setImagesPath(String imagesPath) {
        this.imagesPath = imagesPath;
    }
}
