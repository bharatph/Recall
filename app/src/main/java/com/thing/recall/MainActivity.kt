package com.thing.recall

import android.arch.persistence.room.Room
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import com.thing.recall.model.Fragment
import com.thing.recall.model.Memory
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.thread
import android.widget.DatePicker
import android.app.DatePickerDialog
import android.arch.persistence.room.RoomDatabase
import com.thing.recall.fragment.WriterFragment
import java.lang.StringBuilder


class MainActivity : AppCompatActivity(), WriterFragment.OnShouldUpdateListener {

    override fun onShouldUpdate(date: Date?, string: String?) {
        if (date == null || string == null) return
        thread {
            var mem = memoryDao.getMemory(date)
            if (mem == null) {
                return@thread
            }
            mem.description = string
            memoryDao.insert(mem)
        }
    }

    fun showFragmentFor(date: Date) {
        supportFragmentManager.popBackStack()
        thread {
            var mem = memoryDao.getMemory(date)
            if(mem == null){
                mem = Memory(date, "")
                memoryDao.insert(mem)
            }
            dateTitle.text = SimpleDateFormat("MMM dd").format(mem.memoryOn.time)
            supportFragmentManager.beginTransaction()
                .addToBackStack("value")
                .replace(
                    R.id.writerFragmentContainer,
                    WriterFragment.newInstance(mem.memoryOn.time, mem.description)
                )
                .commit()
        }
    }

    lateinit var memoryDao: MemoryDao
    lateinit var db: RecallDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = Room.databaseBuilder(this, RecallDatabase::class.java, "mem").build()
        memoryDao = db.memoryDao()
        setContentView(R.layout.activity_main)
        showFragmentFor(Date())
        dateTitle.setOnClickListener {
            val c = Calendar.getInstance()
            var mYear = c.get(Calendar.YEAR)
            var mMonth = c.get(Calendar.MONTH)
            var mDay = c.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    val date = Calendar.getInstance()
                    date.set(year - 1900, monthOfYear, dayOfMonth)
                    showFragmentFor(date.time)
                },
                mYear,
                mMonth,
                mDay
            )
            datePickerDialog.show()
        }
    }

}
