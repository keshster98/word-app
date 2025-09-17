package com.team.wordapp

import android.app.Application
import androidx.room.Room
import com.google.android.material.color.DynamicColors
import com.team.wordapp.data.db.MyDatabase
import com.team.wordapp.data.repo.WordRepo

class MyApp: Application() {
    lateinit var repo: WordRepo

    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)

        val db = Room.databaseBuilder(
            this,
            MyDatabase::class.java,
            MyDatabase.NAME
        ).build()
        repo = WordRepo(db.getWordDao())
    }
}