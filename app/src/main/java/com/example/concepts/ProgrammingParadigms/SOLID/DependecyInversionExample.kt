package com.example.concepts.ProgrammingParadigms.SOLID

import android.provider.ContactsContract.Data

//High level modules should not depend on low level modules
//Both should depend on abstractions (interfaces)

// Here you can only store the user data in Cache , and if we wanted to extend the functionality
// and store the user data in database as well , then it would not be possible to do it in a proper way

//------------------------ ❌❌ WRONG CODE ❌❌ -------------------------

//class CacheStore {
//
//    fun addKey(key : String,value : String)
//    {
//        // code
//    }
//
//    fun removeKey(key : String)
//    {
//        // code
//    }
//}
//
//class UserProfile         // this UserProfile module is dependent on CacheStore module
//{
//        private lateinit var cacheObj : CacheStore
//
//        fun storeUser()
//        {
//            cacheObj.addKey("arpit","male")
//        }
//}

//------------------------✅✅ CORRECT CODE ✅✅ -------------------------

interface StoreService {

    fun addKey(key: String, value: String)
    fun removeKey(key: String)
}

class CacheStoreService : StoreService {     // this stores user in Cache
    override fun addKey(key: String, value: String) {
        // code
        println("key -> $key, value -> $value store in Cache")
    }

    override fun removeKey(key: String) {
        // code
    }

}

// this is a additional class to show how it makes easier to extend the code

class DatabaseStoreService : StoreService     // this stores user in Database
{
    override fun addKey(key: String, value: String) {
        // code
        println("key -> $key, value -> $value store in Database")
    }

    override fun removeKey(key: String) {
       //code
    }

}

class UserProfile         // this UserProfile module is dependent on interface not an CacheStoreService Module
{
    private var cacheStoreService: StoreService = CacheStoreService()
    private var databaseStoreService : StoreService = DatabaseStoreService()

    fun storeUserinCache() {
        cacheStoreService.addKey("Harsh", "male")
    }

    fun storeUSerinDatabse()
    {
        databaseStoreService.addKey("Harsh", "male")
    }
}

fun main() {
    var userObj: UserProfile = UserProfile()
    userObj.storeUserinCache()
    userObj.storeUSerinDatabse()
}
