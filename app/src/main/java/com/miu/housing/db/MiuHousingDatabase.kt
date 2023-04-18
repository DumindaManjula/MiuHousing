package com.miu.housing.db

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.miu.housing.dao.BookingDao
import com.miu.housing.dao.BuildingDao
import com.miu.housing.dao.RoomDao
import com.miu.housing.dao.UserDao

@Database(
    entities = [User::class, Building::class, Room::class, Booking::class],
    version = 1
)
abstract class MiuHousingDatabase: RoomDatabase() {

    abstract fun getUserDao(): UserDao

    abstract fun getBuildingDao(): BuildingDao

    abstract fun getRoomDao(): RoomDao

    abstract fun getBookingDao(): BookingDao

    // Build RoomDB
    companion object {
        @Volatile
        private var instance: MiuHousingDatabase? = null
        private val LOCK =   Any()
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }
        // Function to build database
        private fun buildDatabase(context: Context) = androidx.room.Room.databaseBuilder(
            context.applicationContext,
            MiuHousingDatabase::class.java,
            "notedatabase"
        ).build()
    }
}
