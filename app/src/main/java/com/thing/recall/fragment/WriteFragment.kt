package com.thing.recall.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson

import com.thing.recall.R
import com.thing.recall.model.Memory
import kotlinx.android.synthetic.main.fragment_write.*
import java.text.DateFormat

private val ARG_MEM: String? = null
private var mem: Memory? = null

class WriteFragment : Fragment(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.finishButton -> {
                listener?.onDescription(mem)
            }
        }
    }

    private var param1: String? = null
    private var listener: OnDescriptionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_MEM)
            mem = Gson().fromJson(param1, Memory::class.java)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        finishButton.setOnClickListener(this)
        dateTitle.text = DateFormat.getDateInstance(DateFormat.MEDIUM).format(mem?.date) //FIXME
        memWriter.setText(mem?.description)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_write, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnDescriptionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnDescriptionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnDescriptionListener {
        fun onDescription(mem: Memory?)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            WriteFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_MEM, param1)
                }
            }
    }
}
