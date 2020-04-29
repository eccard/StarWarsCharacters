package com.eccard.starwarscharacters.util.realm

import android.os.Parcel
import android.os.Parcelable
import com.eccard.starwarscharacters.data.model.Film
import io.realm.RealmList
import io.realm.RealmModel
import kotlinx.android.parcel.Parceler


fun <T> Parcel.readRealmList(clazz: Class<T>): RealmList<T>?
        where T : RealmModel,
              T : Parcelable = when {
    readInt() > 0 -> RealmList<T>().also { list ->
        repeat(readInt()) {
            list.add(readParcelable(clazz.classLoader))
        }
    }
    else -> null
}

fun <T> Parcel.writeRealmList(realmList: RealmList<T>?)
        where T : RealmModel,
              T : Parcelable {
    writeInt(
        when (realmList) {
            null -> 0
            else -> 1
        }
    )
    if (realmList != null) {
        writeInt(realmList.size)
        for (t in realmList) {
            writeParcelable(t, 0)
        }
    }
}

interface RealmListParceler <T>: Parceler<RealmList<T>?> where T: RealmModel, T: Parcelable {
    override fun create(parcel: Parcel): RealmList<T>? = parcel.readRealmList(clazz)

    override fun RealmList<T>?.write(parcel: Parcel, flags: Int) {
        parcel.writeRealmList(this)
    }

    val clazz : Class<T>
}

object FilmRealmListParceler: RealmListParceler<Film> {
    override val clazz: Class<Film>
        get() = Film::class.java
}

object IntRealmListParceler: RealmListParceler<RealmInt> {
    override val clazz: Class<RealmInt>
        get() = RealmInt::class.java
}
