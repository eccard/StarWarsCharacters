package com.eccard.starwarscharacters.data.db

import com.eccard.starwarscharacters.data.model.CharacterAdapterPojo
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

        } catch (exception : Exception){
            Timber.e(exception)
        }finally {
            realm?.close()
        }
    }

    fun getAllCharactters() : List<Charactter>{

        var characterList : List<Charactter>? = null
        var realm : Realm? = null
        try {
            realm = Util.getRealm()
            characterList = realm.copyFromRealm(
                realm.where(Charactter::class.java)
                    .findAll())
        } catch (exception : Exception){
            Timber.e(exception)
        }finally {
            realm?.close()
        }

        return characterList ?: emptyList()
    }

    fun getAllCharactersInAdapterFormat() : List<CharacterAdapterPojo>{
        return getAllCharactters().map { CharacterAdapterPojo(it,null) }
    }

    fun getCharacttersWithName(name : String) : List<Charactter>{

        var characterList : List<Charactter>? = null
        var realm : Realm? = null
        try {
            realm = Util.getRealm()
            characterList = realm.copyFromRealm(realm.where(Charactter::class.java)
                .contains("name",name, Case.INSENSITIVE)
                .findAll())
        } catch (exception : Exception){
            Timber.e(exception)
        }finally {
            realm?.close()
        }

        return characterList ?: emptyList()

    }

    fun getCharacttersWithNameInAdapterFormat(name : String) : List<CharacterAdapterPojo>{
        return getCharacttersWithName(name).map { CharacterAdapterPojo(it,null) }
    }


    fun getCharacttersByIds(ids : List<Int>) : List<Charactter> {
        var characterList : List<Charactter>? = null

        if (ids.isNotEmpty()) {
            var realm : Realm? = null
            try {
                realm = Util.getRealm()
                characterList = realm.copyFromRealm(realm.where(Charactter::class.java)
                    .`in`("id",ids.toTypedArray())
                    .findAll())
            } catch (exeption : Exception){
                Timber.e(exeption)
            }finally {
                realm?.close()
            }

        }
        return characterList ?: emptyList()
    }

    fun getCharacttersByIdsWithAdapterFormat(ids : List<Int>) :  List<CharacterAdapterPojo>{
        return getCharacttersByIds(ids).map { CharacterAdapterPojo(it,null) }
    }

}