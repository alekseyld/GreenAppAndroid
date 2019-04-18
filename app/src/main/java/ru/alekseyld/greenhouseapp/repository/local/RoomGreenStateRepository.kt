package ru.alekseyld.greenhouseapp.repository.local

import io.reactivex.Completable
import io.reactivex.Single
import ru.alekseyld.greenhouseapp.model.GreenState
import ru.alekseyld.greenhouseapp.repository.IGreenStateRepository
import ru.alekseyld.greenhouseapp.repository.room.GreenStateDao

class RoomGreenStateRepository(private val greenStateDao: GreenStateDao) : IGreenStateRepository {

    override fun getAllGreenStates(): Single<List<GreenState>> = greenStateDao.getAll()

    override fun getGreenState(): Single<GreenState> = greenStateDao.getFirst()

    override fun saveGreenState(greenState: GreenState): Completable {
        return Completable.fromAction {
            greenStateDao.insertOrUpdate(greenState)
        }
    }
}