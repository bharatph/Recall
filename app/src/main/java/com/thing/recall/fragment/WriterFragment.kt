package com.thing.recall.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.thing.recall.R
import kotlinx.android.synthetic.main.fragment_writer.*
import java.util.*

private const val ARG_PARAM1 = "desc"
private const val ARG_PARAM2 = "date"

class WriterFragment : Fragment() {
    private var string: String? = null
    private var date: Date? = null
    private var listener: OnShouldUpdateListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            string = it.getString(ARG_PARAM1)
            date = Date(it.getLong(ARG_PARAM2))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_writer, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        memWriter.setText(string)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnShouldUpdateListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnShouldUpdateListener")
        }
    }

    override fun onPause() {
        super.onPause()
        listener?.onShouldUpdate(date, memWriter.text.toString())
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnShouldUpdateListener {
        fun onShouldUpdate(date: Date?, string: String?)
    }

    companion object {
        @JvmStatic
        fun newInstance(param2: Long, param1: String) =
            WriterFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putLong(ARG_PARAM2, param2)
                }
            }
    }
}
