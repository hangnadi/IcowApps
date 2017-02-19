package com.icow.main.database.model;

import com.icow.main.database.DbFlowDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ConflictAction;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by hangnadi on 2/14/17.
 */
@SuppressWarnings("WeakerAccess")
@ModelContainer
@Table(database = DbFlowDatabase.class, insertConflict = ConflictAction.REPLACE)
public class DbUser extends BaseModel {

    @Column
    @PrimaryKey
    int id;

    @Column
    public String contentUser;

    @Column
    public long lastUpdated;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContentUser() {
        return contentUser;
    }

    public void setContentUser(String contentUser) {
        this.contentUser = contentUser;
    }

    public long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
