package com.eccard.starwarscharacters.util.realm

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import io.realm.RealmList

class IntListTypeAdapter : TypeAdapter<RealmList<RealmInt>>() {
    override fun write(out: JsonWriter?, value: RealmList<RealmInt>?) {
        TODO("Not yet implemented")
    }

    override fun read(input: JsonReader?): RealmList<RealmInt> {
        val list = RealmList<RealmInt>()
        input?.let {
            input.beginArray()
            while (input.hasNext()) {
                list.add(RealmInt(input.nextInt()))
            }
            input.endArray()
        }



        return list
    }
}