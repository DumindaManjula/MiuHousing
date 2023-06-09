package com.miu.housing.db

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.miu.housing.dao.*
import com.miu.housing.data.Converter

@Database(
    entities = [User::class, Building::class, Room::class, Booking::class,
        Faq::class, Damage::class, Complain::class],
    version = 10
)
@TypeConverters(Converter::class)
abstract class MiuHousingDatabase: RoomDatabase() {

    abstract fun getUserDao(): UserDao

    abstract fun getBuildingDao(): BuildingDao

    abstract fun getRoomDao(): RoomDao

    abstract fun getBookingDao(): BookingDao

    abstract fun getFaqDao(): FaqDao

    abstract fun getDamageDao(): DamageDao
    abstract fun getComplainDao(): ComplainDao

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
        ).fallbackToDestructiveMigration()
            .build()
    }
}
