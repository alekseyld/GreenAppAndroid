package ru.alekseyld.greenhouseapp.repository.room

import android.arch.persistence.room.*
import io.reactivex.Single
import ru.alekseyld.greenhouseapp.model.GreenState

@Dao
public interface GreenStateDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertOrUpdate(greenState: GreenState)

    @Delete
    fun delete(greenState: GreenState)

    @Query("SELECT * FROM green_state")
    fun getAll(): Single<List<GreenState>>

    @Query("SELECT * FROM green_state WHERE id > -1 LIMIT 1")
    fun getFirst(): Single<GreenState>
}