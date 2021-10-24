package com.example.listviewhomework.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.listviewhomework.R


class NewItemFragment : Fragment() {
    companion object {
        fun newInstance(s: String) = NewItemFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_newlist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = view.findViewById<EditText>(R.id.nameEditText).text
        val phoneNumber = view.findViewById<EditText>(R.id.PhoneNumberEditText).text
        val carNumber =view.findViewById<EditText>(R.id.carNumberEditText).text

        val btn = view.findViewById<Button>(R.id.btnSave)
        btn.setOnClickListener{
           parentFragmentManager
               .setFragmentResult("NEWITEMFRESULT",
                   bundleOf("newDriverNameKEY" to name,
                                   "newDriverPhoneKEY" to phoneNumber,
                                   "newDriverCarKEY" to carNumber))
           parentFragmentManager.popBackStack()

        }
    }

}