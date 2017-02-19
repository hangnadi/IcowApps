package com.icow.main.user.data.source.local.dbmanager;

import com.icow.main.database.model.DbUser;
import com.icow.main.database.operation.DbManagerOperation;
import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import retrofit2.Response;
import rx.Observable;

/**
 * Created by hangnadi on 2/14/17.
 */
public class UserDbManager implements DbManagerOperation<DbUser,Observable<Response<String>>> {
    @Override
    public void store(DbUser data) {
        data.save();
    }

    @Override
    public void delete() {
        Delete.tables(DbUser.class);
    }

    @Override
    public boolean isExpired(long currentTime) {
        try {
            long lastUpdated = getTable().getLastUpdated();
            return isQueryDataEmpty() || isMoreThanFifteenMinute(currentTime, lastUpdated);
        } catch (Exception e) {
            return true;
        }
    }

    @Override
    public DbUser getTable() {
        return SQLite.select().from(DbUser.class).querySingle();
    }

    @Override
    public Observable<Response<String>> getData() {
        String contentUser = getTable().getContentUser();
        if (contentUser != null) {
            return Observable.just(Response.success(contentUser));
        } else {
            return Observable.empty();
        }
    }

    @Override
    public boolean isQueryDataEmpty() {
        return getTable() == null;
    }

    private boolean isMoreThanFifteenMinute(long currentTime, long oldTime) {
        long fifteenMinutes = 1000 * 60 * 15;
        return currentTime - oldTime >= fifteenMinutes;
    }
}
