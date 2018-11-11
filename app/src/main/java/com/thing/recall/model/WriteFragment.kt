package com.thing.recall.model

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.thing.recall.R
import kotlinx.android.synthetic.main.fragment_write.*

private const val ARG_DESC = "param1"

class WriteFragment : Fragment(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.finishButton -> {
                listener?.onDescription(memWriter.text.toString())
            }
        }
    }

    private var param1: String? = null
    private var listener: OnDescriptionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_DESC)
        }
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
        fun onDescription(desc: String)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            WriteFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_DESC, param1)
                }
            }
    }
}
