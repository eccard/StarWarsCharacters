package com.eccard.starwarscharacters.data.model

import android.os.Parcelable
import io.realm.RealmModel
import io.realm.RealmObject
import io.realm.annotations.RealmClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@RealmClass
open class RealmInt (

    var _value : Int = 0
//        get() {
//            return this._value
//        }
//        set(value) {
//            this._value = value
//        }
////
//    fun setValue(newValue : Int){
//        _value = newValue
//    }
//
//    fun getValue() : Int = _value

//    override fun toString(): String {
//        return "RealmInt(_value=$_value)"
//    }
) : RealmModel,Parcelable{
    fun getValue() = _value
    override fun toString(): String {
        return "RealmInt(_value=$_value)"
    }
}