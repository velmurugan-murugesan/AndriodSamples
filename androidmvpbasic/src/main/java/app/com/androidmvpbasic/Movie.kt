package app.com.androidmvpbasic

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Movie() : RealmObject() {
    @PrimaryKey public open var category: String = ""
    var imageUrl: String? = null
    var name: String? = null
    var desc: String? = null
}
