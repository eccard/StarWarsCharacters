package com.eccard.starwarscharacters.util.realm

import io.realm.Realm
import io.realm.RealmConfiguration





class Util{

    companion object {
        fun getRealm(): Realm {
            val config = RealmConfiguration.Builder()
                .name("starWars.realm")
                .schemaVersion(3)
                .deleteRealmIfMigrationNeeded()
                .build()
            return Realm.getInstance(config);
        }
    }
}