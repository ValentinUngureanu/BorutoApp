package com.example.borutoapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.borutoapp.data.local.dao.HeroDao
import com.example.borutoapp.data.local.dao.HeroRemoteKeyDao
import com.example.borutoapp.domain.model.Hero

@Database(entities = [Hero::class], version = 1)
@TypeConverters(DatabaseConvertor::class)
abstract class BorutoDataBase : RoomDatabase() {
    abstract fun heroDao(): HeroDao

    abstract fun heroRemoteKeyDao(): HeroRemoteKeyDao
}
