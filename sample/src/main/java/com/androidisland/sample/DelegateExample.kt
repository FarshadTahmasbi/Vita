package com.androidisland.sample

import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

interface Dog {
    fun bark()
}

class Chawchaw : Dog {
    override fun bark() {
    }
}

class MyDog : Dog by Chawchaw()

class DogX : Dog by object : Dog {
    override fun bark() {

    }

}


class SomeDog(dog: Dog) : Dog by dog

class Test {
    init {
        val dog = SomeDog(Chawchaw())
        dog.bark()
        val dog2 = MyDog()
        val doggy by dogShop()
        val map : MutableMap<String, Any?> = mutableMapOf("0" to 1, "" to 2)
        val x : String by map
    }
}

class DelegateExample(map: MutableMap<String, Any?>) {
    var name: String by map
}

class dogShop : ReadWriteProperty<Any?, Dog> {

    var dog: Dog = Chawchaw()
    override fun getValue(thisRef: Any?, property: KProperty<*>): Dog {
        return dog
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Dog) {
        dog = value
    }
}


interface Repo {
    val observable : String
    fun fetch()
}

class RepoImpl : Repo {

    override val observable: String
    get() = "blah"

    override fun fetch() {
    }

}

class ViewModel : Repo by RepoImpl()

class ViewModel2(repo: Repo) : Repo by repo

class X {
    init {
        ViewModel().fetch()
        ViewModel2(RepoImpl()).fetch()
    }
}

private inline fun <T> SharedPreferences.delegate(
    defaultValue: T,
    key: String?,
    crossinline getter: SharedPreferences.(String, T) -> T,
    crossinline setter: SharedPreferences.Editor.(String, T) -> SharedPreferences.Editor
): ReadWriteProperty<Any, T> {
    return object : ReadWriteProperty<Any, T> {
        override fun getValue(thisRef: Any, property: KProperty<*>) =
            getter(key ?: property.name, defaultValue)

        override fun setValue(thisRef: Any, property: KProperty<*>,
                              value: T) =
            edit().setter(key ?: property.name, value).apply()
    }
}

fun SharedPreferences.int(def: Int = 0, key: String? = null) =
    delegate(def, key, SharedPreferences::getInt, SharedPreferences.Editor::putInt)

fun SharedPreferences.long(def: Long = 0, key: String? = null) =
    delegate(def, key, SharedPreferences::getLong, SharedPreferences.Editor::putLong)

class TokenHolder(prefs: SharedPreferences) {
    var token by prefs.long()
        private set

    var count by prefs.int()
        private set

    fun saveToken(newToken: Long) {
        token = newToken
        count++
    }
}

fun Test2(prefs : SharedPreferences){
    prefs.edit().putInt("count", prefs.getInt("count", 0) + 1).apply()
}