package samples;
import controllers.Application;
import play.GlobalSettings;

public class SystemFusionGlobal extends GlobalSettings {

    public void onStart(Application app) {
        //Logger.info("Application has started");
    }

    public void onStop(Application app) {
        //Logger.info("Application shutdown...");
    }

}