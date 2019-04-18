package ru.alekseyld.greenhouseapp.repository.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import ru.alekseyld.greenhouseapp.model.GreenState

@Database(entities = arrayOf(GreenState::class), version = 1, exportSchema = false)
abstract class GreenAppDatabase : RoomDatabase() {
    abstract fun greenStateDao(): GreenStateDao
}
