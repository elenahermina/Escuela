package com.elena.escuela

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Student::class, RegisteredUser :: class], version = 1)
abstract class Db : RoomDatabase() {
    abstract fun studentsDao(): StudentDao
    abstract  fun registeredUserDao(): RegisteredUserDao

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
                        val students: List<Student> = listOf(
                                Student("oliver@gmail.com", "Oliver", "Sanchez", R.drawable.avatar_1),
                                Student("ana@gmail.com", "Ana", "Lopez", R.drawable.avatar_11),
                                Student("isabel@gmail.com", "Isabel", "Blanco", R.drawable.avatar_17),
                                Student("dana@gmail.com", "Dana", "Negro", R.drawable.avatar_5),
                                Student("jose@gmail.com", "Jose", "Garcia", R.drawable.avatar_7),
                                Student("elena@neoland.com", "Yo", "barbullushi", R.mipmap.ic_launcher)


                        )

                        val usuarioValido = RegisteredUser("elena@neoland.com")

                        INSTANCE?.studentsDao()?.insertAll(students)
                        INSTANCE?.registeredUserDao()?.insert(usuarioValido)


                    }
                }

                override fun onOpen(db: SupportSQLiteDatabase) {
                }
            }
        }
    }


}


