package lt.mk.mathgame.model;

import java.util.HashMap;
import java.util.Map;

public class PlaySettings {

    private Map<Operation, OperationSetting> values = new HashMap<>();

    private String imagesPath;


    public void setDefaults() {
        values.put(Operation.PLUS, new OperationSetting(10, 10, 20, true));
        values.put(Operation.MINUS, new OperationSetting(10, 10, 20, true));
        values.put(Operation.MULTI, new OperationSetting(10, 10, 50, true));
        values.put(Operation.DIV, new OperationSetting(10, 10, 50, true));
    }

    public Map<Operation, OperationSetting> getValues() {
        return values;
    }

    public boolean isUserPlus() {
        return values.get(Operation.PLUS).isEnabled();
    }


    public boolean isUserMinus() {
        return values.get(Operation.MINUS).isEnabled();
    }


    public boolean isUserMulti() {
        return values.get(Operation.MULTI).isEnabled();
    }


    public boolean isUserDiv() {
        return values.get(Operation.DIV).isEnabled();
    }


    public String getImagesPath() {
        return imagesPath;
    }

    public void setImagesPath(String imagesPath) {
        this.imagesPath = imagesPath;
    }
}
