package com.thing.recall

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.transition.Fade
import android.widget.LinearLayout
import com.google.gson.Gson
import com.thing.recall.helper.RecallHelper
import com.thing.recall.adapter.MemoryAdapter
import com.thing.recall.fragment.WriteFragment
import kotlinx.android.synthetic.main.activity_main.*
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import com.google.gson.GsonBuilder
import com.thing.recall.model.Memory
import java.util.*


class MainActivity : AppCompatActivity(), WriteFragment.OnDescriptionListener {
    override fun onDescription(mem: Memory?) {
        supportFragmentManager.popBackStack()
        if(mem == null){
            //no-op
            return
        }
        val gson = GsonBuilder().create()
        val strJson = gson.toJson(mem)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CalligraphyConfig.initDefault(
            CalligraphyConfig.Builder()
                //.setDefaultFontPath("fonts/Roboto-RobotoRegular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        )
        setContentView(R.layout.activity_main)
        var memories = LinkedList<Memory>()

        memories.add(Memory(Date(), getString(R.string.sammple_text)))
        memories.add(Memory(Date(), getString(R.string.sammple_text)))
        memories.add(Memory(Date(), getString(R.string.sammple_text)))
        memories.add(Memory(Date(), getString(R.string.sammple_text)))
        memories.add(Memory(Date(), getString(R.string.sammple_text)))
        memories.add(Memory(Date(), getString(R.string.sammple_text)))


        var adapter = MemoryAdapter(this, memories) {
            supportFragmentManager.beginTransaction()
                .addToBackStack("null")
                .replace(R.id.writeContainer, WriteFragment.newInstance(Gson().toJson(it)).apply {
                    enterTransition = Fade(Fade.MODE_IN)
                    exitTransition = Fade(Fade.MODE_OUT)
                })
                .commit()
        }
        recallRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true).apply {
            stackFromEnd = true
        }
        recallRecyclerView.adapter = adapter
    }
}
