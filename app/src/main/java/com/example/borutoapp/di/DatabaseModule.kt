package com.example.borutoapp.di

import android.content.Context
import androidx.room.Room
import com.example.borutoapp.data.local.BorutoDataBase
import com.example.borutoapp.util.Constants.BORUTO_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import kotlin.jvm.java

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        BorutoDataBase::class.java,
        BORUTO_DATABASE
    ).build()

}
