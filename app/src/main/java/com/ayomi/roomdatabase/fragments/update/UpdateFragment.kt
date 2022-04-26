package com.ayomi.roomdatabase.fragments.update

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ayomi.roomdatabase.R
import com.ayomi.roomdatabase.data.User
import com.ayomi.roomdatabase.viewmodel.UserViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*


class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this)[UserViewModel::class.java]
        view.update_firstname.setText(args.currentUser.firstName)
        view.update_lastname.setText(args.currentUser.lastName)
        view.update_age.setText(args.currentUser.age.toString())

        view.button_Update.setOnClickListener {
            updateItem()
        }

        setHasOptionsMenu(true)
    }

    private fun updateItem() {

        var firstName = update_firstname.text.toString()
        var lastName = update_lastname.text.toString()
        var age = Integer.parseInt(update_age.text.toString())

        if (inputCheck(firstName, lastName, age)) {
            val updateUser =
                User(args.currentUser.id, firstName, lastName, Integer.parseInt(age.toString()))
            viewModel.updateUsers(updateUser)
            val snack =
                view?.let { Snackbar.make(it, "Successfully Updated", Snackbar.LENGTH_LONG) }
            snack!!.show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            val snack =
                view?.let { Snackbar.make(it, "Please fill out all fields!", Snackbar.LENGTH_LONG) }
            snack!!.show()
        }


    }

    private fun inputCheck(first: String, last: String, age: Int): Boolean {
        return !(TextUtils.isEmpty(first) && TextUtils.isEmpty(last) && TextUtils.isEmpty(age.toString()))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete) {
            deleteItem()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteItem() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Delete User Data")
            .setMessage("Are you sure you want to delete this user?")
            .setNegativeButton("No", null)
            .setPositiveButton("Yes") { _, _ ->
                viewModel.deleteUser(args.currentUser)
                val snack =
                    view?.let { Snackbar.make(it, "Successfully Deleted User", Snackbar.LENGTH_LONG) }
                snack!!.show()
                findNavController().navigate(R.id.action_updateFragment_to_listFragment)
            }.create().show()
    }
}