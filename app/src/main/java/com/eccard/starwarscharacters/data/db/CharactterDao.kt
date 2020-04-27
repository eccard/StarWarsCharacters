package com.eccard.starwarscharacters.data.db

import com.eccard.starwarscharacters.data.model.Charactter
import com.eccard.starwarscharacters.testing.OpenForTesting
import com.eccard.starwarscharacters.util.realm.Util
import io.realm.Case
import io.realm.Realm
import timber.log.Timber

@OpenForTesting
class CharactterDao {

    fun insert(charactterList: List<Charactter>){
        var realm : Realm? = null
        try {
            realm = Util.getRealm()
            realm.executeTransaction {
                it.copyToRealmOrUpdate(charactterList)
            }

        } catch (exeption : Exception){
            Timber.e(exeption)
        }finally {
            realm?.close()
        }
    }

    fun getAllCharactters() : List<Charactter>{

        var characterList : List<Charactter>? = null
        var realm : Realm? = null
        try {
            realm = Util.getRealm()
            characterList = realm.where(Charactter::class.java)
                .findAll().map { it.copyFromRealm() }
        } catch (exeption : Exception){
            Timber.e(exeption)
        }finally {
            realm?.close()
        }

        return characterList ?: emptyList()
    }

    fun getCharacttersWithName(name : String) : List<Charactter>{

        var characterList : List<Charactter>? = null
        var realm : Realm? = null
        try {
            realm = Util.getRealm()
            characterList = realm.where(Charactter::class.java)
                .contains("name",name, Case.INSENSITIVE)
                .findAll().map { it.copyFromRealm() }
        } catch (exeption : Exception){
            Timber.e(exeption)
        }finally {
            realm?.close()
        }

        return characterList ?: emptyList()

    }


    fun getCharacttersByIds(ids : List<Int>) : List<Charactter> {
        var characterList : List<Charactter>? = null

        if (ids.isNotEmpty()) {
            var realm : Realm? = null
            try {
                realm = Util.getRealm()
                characterList = realm.where(Charactter::class.java)
                    .`in`("id",ids.toTypedArray())
                    .findAll().map{it.copyFromRealm()}
            } catch (exeption : Exception){
                Timber.e(exeption)
            }finally {
                realm?.close()
            }

        }
        return characterList ?: emptyList()
    }

    private fun Charactter.copyFromRealm(): Charactter {
        return Charactter(this.id,this.isMain,this.name,this.imageUrl,this.gender)
    }
}