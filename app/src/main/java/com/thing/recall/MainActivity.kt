package com.thing.recall

import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.thing.recall.model.Fragment
import com.thing.recall.model.Memory
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DateFormat
import java.time.Instant
import java.util.*
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        thread {
            val db = Room.databaseBuilder(this, RecallDatabase::class.java, "memories").build()
            val memoryDao = db.memoryDao()
            //memoryDao.insert(Memory(Date(), listOf(Fragment(Date(), "Testing persistence lib"))))
            var mem : Memory = memoryDao.getMemory(Date()) ?: return@thread
            Handler(mainLooper).post {
                dateTitle.text = DateFormat.getDateInstance().format(mem.memoryOn)
                for(fragment in mem.fragments){
                    memWriter.editableText.append(fragment.description)
                }
            }
        }
    }
}
