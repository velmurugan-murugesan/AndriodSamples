package app.com.androidmvpbasic.data

import app.com.androidmvpbasic.Movie
import io.realm.Realm

class RealmHandler {

    fun addMovie(item: Movie) {
        var realm: Realm? = null
        try {
            realm = Realm.getDefaultInstance()

            realm.executeTransaction {
                it.insertOrUpdate(item)
            }
        }

        finally {
            realm?.close()
        }

    }


}