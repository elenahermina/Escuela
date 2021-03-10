package com.elena.escuela.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.elena.escuela.*
import com.elena.escuela.dao.DbDao
import com.elena.escuela.dao.RegisteredUserDao
import com.elena.escuela.dao.StudentDao
import com.elena.escuela.student.RegisteredUser
import com.elena.escuela.student.Student
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Student::class, RegisteredUser :: class, DbEntity::class], version = 1)
abstract class Db : RoomDatabase() {
    abstract fun studentsDao(): StudentDao
    abstract  fun registeredUserDao(): RegisteredUserDao
    abstract  fun dbDao () : DbDao

    companion object {

        @Volatile
        private var INSTANCE: Db? = null

        fun getDatabase(context: Context): Db {
            return INSTANCE ?: synchronized(this) {
                INSTANCE?.let {
                    return INSTANCE as Db
                }
                val roomBuilder =
                        Room.databaseBuilder(context.applicationContext, Db::class.java, "database-db")
                roomBuilder.addCallback(getCallback())
                INSTANCE = roomBuilder.build()
                return INSTANCE as Db
            }
        }

        private fun getCallback(): RoomDatabase.Callback {
            return object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    CoroutineScope(Dispatchers.IO).launch {
                        INSTANCE?.registeredUserDao()?.insert(RegisteredUser("elena@neoland.com"))

                        INSTANCE?.studentsDao()?.insert(Student("oliver@gmail.com", "Oliver", "Sanchez", R.drawable.avatar_1))
                        INSTANCE?.studentsDao()?.insert(Student("ana@gmail.com", "Ana", "Lopez", R.drawable.avatar_11))
                        INSTANCE?.studentsDao()?.insert(Student("isabel@gmail.com", "Isabel", "Blanco", R.drawable.avatar_17))
                        INSTANCE?.studentsDao()?.insert(Student("dana@gmail.com", "Dana", "Negro", R.drawable.avatar_5))
                        INSTANCE?.studentsDao()?.insert(Student("jose@gmail.com", "Jose", "Garcia", R.drawable.avatar_7))

                        INSTANCE?.dbDao()?.insert(DbEntity(0, true))

                    }
                }


                override fun onOpen(db: SupportSQLiteDatabase) {
                }
            }
        }
    }
}


