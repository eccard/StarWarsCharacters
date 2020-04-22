package com.eccard.starwarscharacters.data.db

import com.eccard.starwarscharacters.data.model.Charactter
import io.realm.Case
import io.realm.Realm

//class CharactterDao (private val realm: Realm) {
class CharactterDao {

//    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insert(charactter: Charactter){
        val realm = Realm.getDefaultInstance()
        val v1 = realm.beginTransaction()
        val v2 = realm.copyToRealmOrUpdate(charactter)
        val v3 = realm.commitTransaction()
        val abc =123
    }

//    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insert(charactterList: List<Charactter>){
        val realm = Realm.getDefaultInstance()
        val v1= realm.beginTransaction()
        val v2 = realm.copyToRealmOrUpdate(charactterList)
        val v3 = realm.commitTransaction()
        val abc =123
    }

//    @Query ("SELECT * FROM  charactter")
    fun getAllCharactters() : List<Charactter>{
        val realm = Realm.getDefaultInstance()
        val r = realm.where(Charactter::class.java)
            .findAll()

//        val rr = r.map { Charactter(it.id,it.isMain,it.name,it.imageUrl
        val rr = r.map { it.clone() }
        return rr
    }

//    @Query("SELECT * FROM charactter WHERE name LIKE '%' || :name || '%'")
    fun getCharacttersWithName(name : String) : List<Charactter>{
        val realm = Realm.getDefaultInstance()
        val ret = realm.where(Charactter::class.java)
            .contains("name",name, Case.INSENSITIVE)
            .findAll()

        val rr = ret.map{it.clone()}
        return rr
    }


//    @Query("SELECT * FROM charactter WHERE id IN (:ids)")
    fun getCharacttersByIds(ids : List<Int>) : List<Charactter> {

        if (ids.size > 0) {
            val realm = Realm.getDefaultInstance()
            var realmQuery = realm.where(Charactter::class.java)
            for (id in ids) {
                realmQuery = realmQuery.equalTo("id", id)
            }
            return realmQuery.findAll()
//            return realmQuery.findAll().map { it.clone() }
        } else {
            return emptyList()
        }
    }
}