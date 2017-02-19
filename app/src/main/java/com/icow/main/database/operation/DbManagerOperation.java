package com.icow.main.database.operation;

import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by hangnadi on 2/10/17.
 */
public interface DbManagerOperation<T extends BaseModel, T1> {

    void store(T data);

    void delete();

    boolean isExpired(long currentTime);

    T getTable();

    T1 getData();

    boolean isQueryDataEmpty();

}
