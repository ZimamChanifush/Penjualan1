package com.example.penjualan_zimamchanifushs.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [tb_barang::class],
    version = 1
)

abstract class Penjualan : RoomDatabase(){
    abstract fun PenjualanDAO(): PenjualanDAO
    companion object{
        @Volatile private var instance : Penjualan? = null
        private val LOCK = Any()
         operator fun invoke(context: Context) = instance?:synchronized(LOCK){
             instance?: buildDatabase(context).also{
                 instance= it
             }
         }
        private fun buildDatabase(context: Context)= Room.databaseBuilder(
            context.applicationContext,
            Penjualan::class.java,
        "1234.db"
        ).build()
    }
}