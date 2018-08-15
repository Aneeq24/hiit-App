package com.bwf.hiit.workout.abs.challenge.home.fitness.dao;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.bwf.hiit.workout.abs.challenge.home.fitness.models.Record;

import java.util.List;


@Dao
public interface Recorddao {

    @Query("SELECT * FROM record")
    LiveData<List<Record>> getAllRecords();

    @Query("SELECT * FROM record WHERE id = :id")
    LiveData<Record> findById(int id);

    @Insert
    void insertAll(Record... record);

    @Delete
    void delete(Record record);

    @Update
    void updateRecord(Record record);
}
