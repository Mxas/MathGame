package lt.mk.mathgame.service;

public class ServiceManager {

    static class SettingsServiceHolder {
        static SettingsService INSTANCE = new SettingsService();
    }

    static class MathServiceHolder {
        static MathService INSTANCE = new MathService(SettingsServiceHolder.INSTANCE.loadSettings());
    }

    static class PicServiceHolder {
        static PicService INSTANCE = new PicService(SettingsServiceHolder.INSTANCE.loadSettings().getImagesPath());
    }



    public static SettingsService settingsService() {
        return SettingsServiceHolder.INSTANCE;
    }

    public static MathService mathService() {
        return MathServiceHolder.INSTANCE;
    }

    public static PicService picService() {
        return PicServiceHolder.INSTANCE;
    }


}
