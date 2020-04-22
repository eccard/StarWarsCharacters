package com.eccard.starwarscharacters.data.model

import io.realm.RealmObject

open class RealmInt : RealmObject() {

    var _value : Int = 0

    fun setValue(newValue : Int){
        _value = newValue
    }

    fun getValue() : Int = _value

    override fun toString(): String {
        return "RealmInt(_value=$_value)"
    }
}