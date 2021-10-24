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

class UpdateItemFragment: Fragment() {
    companion object {
        fun newInstance() = UpdateItemFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_updatelist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val name = view.findViewById<EditText>(R.id.NameUpdateText).text
        val phoneNumber = view.findViewById<EditText>(R.id.PhoneNumberUpdateText).text

        val btn = view.findViewById<Button>(R.id.btnUpdateSave)
        btn.setOnClickListener{
            parentFragmentManager
                .setFragmentResult("UPDATEITEMFRESULT",
                    bundleOf("updateDriverNameKEY" to name,
                        "updateDriverPhoneKEY" to phoneNumber)
                )
            parentFragmentManager.popBackStack()

        }
    }
}