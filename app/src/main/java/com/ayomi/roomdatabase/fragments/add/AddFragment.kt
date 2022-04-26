package com.ayomi.roomdatabase.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ayomi.roomdatabase.R
import com.ayomi.roomdatabase.data.User
import com.ayomi.roomdatabase.viewmodel.UserViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {

    private lateinit var viewModel: UserViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this)[UserViewModel::class.java]
        view.button.setOnClickListener {
            insertDataToDatabase()
        }
    }

    private fun insertDataToDatabase() {

        var firstName = edt_firstname.text.toString()
        var lastName = edt_lastname.text.toString()
        var age = edt_age.text

        if (inputCheck(firstName, lastName, age)){
            val user =User(0, firstName, lastName, Integer.parseInt(age.toString()))
            viewModel.addUser(user)
            val snack = view?.let { Snackbar.make(it,"Successfully Added",Snackbar.LENGTH_LONG) }
            snack!!.show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            val snack = view?.let { Snackbar.make(it,"Please fill out all fields!",Snackbar.LENGTH_LONG) }
            snack!!.show()
        }


    }

    private fun inputCheck(first: String, last: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(first) && TextUtils.isEmpty(last) && age.isEmpty())
    }


}