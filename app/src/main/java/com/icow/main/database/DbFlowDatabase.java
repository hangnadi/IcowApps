package com.icow.main.database;

import com.icow.main.BuildConfig;
import com.raizlabs.android.dbflow.annotation.Database;
import com.raizlabs.android.dbflow.annotation.ModelContainer;

/**
 * Created by hangnadi on 2/14/17.
 */
@SuppressWarnings("WeakerAccess")
@Database(name = DbFlowDatabase.NAME, version = DbFlowDatabase.VERSION, foreignKeysSupported = true)
@ModelContainer
public class DbFlowDatabase {

    public static final String NAME = "icowDB";

    public static final int VERSION = BuildConfig.VERSION_CODE;

}
