package com.eccard.starwarscharacters.util

import com.eccard.starwarscharacters.data.model.Film

object FilmRealmListParceler: RealmListParceler<Film> {
    override val clazz: Class<Film>
        get() = Film::class.java
}