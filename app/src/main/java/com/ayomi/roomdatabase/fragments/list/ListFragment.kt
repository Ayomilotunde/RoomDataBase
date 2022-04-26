package com.ayomi.roomdatabase.fragments.list

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayomi.roomdatabase.adapter.ListAdapter
import com.ayomi.roomdatabase.R
import com.ayomi.roomdatabase.viewmodel.UserViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment() {
    private lateinit var viewModel: UserViewModel
    val listAdapter = ListAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerViewList.adapter = listAdapter
        recyclerViewList.layoutManager = LinearLayoutManager(requireContext())
         viewModel = ViewModelProvider(this)[UserViewModel::class.java]
        viewModel.readAllData.observe(viewLifecycleOwner, Observer {
            listAdapter.setData(it)
        })

        setHasOptionsMenu(true)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete) {
            deleteAllUsers()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllUsers() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Delete All Users Data")
            .setMessage("Are you sure you want to delete all user?")
            .setNegativeButton("No", null)
            .setPositiveButton("Yes") { _, _ ->
                viewModel.deleteAllUsers()
                val snack =
                    view?.let { Snackbar.make(it, "Successfully Deleted All User", Snackbar.LENGTH_LONG) }
                snack!!.show()
            }.create().show()
    }


}