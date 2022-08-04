package com.example.bloodsugartracking9d

import com.example.bloodsugartracking9d.koincomponents.RepositoryKoin
import com.example.bloodsugartracking9d.koincomponents.ViewmodelKoin
import com.example.bloodsugartracking9d.prefrences.TinyDB

import com.example.bloodsugartracking9d.room.UserDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


object AppModule {


    fun applicationModule() = module {
        single { UserDatabase.getDatabase(androidContext()) }

        single { get<UserDatabase>().getdao() }
        single { TinyDB(get()) }
        single { CommonFunction (get())}
        single { RepositoryKoin(get(), get(), get()) }
        single { ViewmodelKoin(get()) }
    }


}


