package com.eccard.starwarscharacters.util.realm

import android.os.Parcelable
import io.realm.RealmModel
import io.realm.annotations.RealmClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@RealmClass
open class RealmInt (
    var _value : Int = 0
) : RealmModel,Parcelable{
    fun getValue() = _value
    override fun toString(): String {
        return "RealmInt(_value=$_value)"
    }
}