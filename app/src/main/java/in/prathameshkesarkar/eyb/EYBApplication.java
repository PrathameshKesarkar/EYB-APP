package in.prathameshkesarkar.eyb;

import android.app.Application;

import com.evernote.android.job.JobManager;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by prathameshkesarkar on 31/03/17.
 */

public class EYBApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
    }
}
