package app.com.androidmvpbasic.data

import io.realm.RealmModel

data class MovieRealm(val category: String,val imageUrl: String, val name: String, val desc: String)
    : RealmModel