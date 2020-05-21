package neversmile.packag.com.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("Aw3zhfAaF5szR6wO1XWjKMdi0WkbDIUkeBTpOvSy")
                // if defined
                .clientKey("8II6PrS2bgKuFy3f3vz4TV89gi9lv5T0AUE7Jp1N")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
