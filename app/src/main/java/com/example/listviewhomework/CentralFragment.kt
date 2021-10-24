package com.example.listviewhomework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listviewhomework.driver.Driver
import com.example.listviewhomework.fragments.NewItemFragment
import com.example.listviewhomework.fragments.UpdateItemFragment
import com.example.listviewhomework.myAdapters.MyDynamicListAdapter
import com.example.listviewhomework.myAdapters.MyRecyclerViewAdapter


const val RECYCLER_VIEW_KEY = "recycler_view_key"

class CentralFragment : Fragment() {
    lateinit var recyclerview: RecyclerView
    var temp = -1


    companion object {
        fun newInstance() = CentralFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // val listview = view.findViewById<ListView>(R.id.listview1)

        showHideDeleteUpdate(false)
        var objects = listOf(
            Driver("Ani Karapetyan", "+123457000", "18LU789"),
            Driver("Artashes Gevorgyan", "+123789000", "36LU789"),
            Driver("Tatev Petrosyan", "+123457000", "85LU787"),
            Driver("Mariam Galstyan", "+123457010", "88TM868"),
            Driver("Petros Petrosyan", "+123457000", "28LU889"),
            Driver("Ani Karapetyan", "+123457000", "38LU789"),
            Driver("Artashes Gevorgyan", "+123789000", "36LU789"),
            Driver("Tatev Petrosyan", "+123457000", "35LU787"),
            Driver("Mariam Galstyan", "+123457010", "88TM768"),
            Driver("Petros Petrosyan", "+123457000", "18LU869"),
            Driver("Ani Karapetyan", "+123457000", "18LU689"),
            Driver("Artashes Gevorgyan", "+123789000", "12LU789"),
            Driver("Tatev Petrosyan", "+123457000", "15LU787"),
            Driver("Mariam Galstyan", "+123457010", "75TM868"),
            Driver("Petros Petrosyan", "+123457000", "18OU889"),
            Driver("Ani Karapetyan", "+123457000", "58LP789"),
            Driver("Artashes Gevorgyan", "+123789000", "36LC789"),
            Driver("Tatev Petrosyan", "+123457000", "35AU787"),
            Driver("Mariam Galstyan", "+123457010", "88QM868"),
            Driver("Petros Petrosyan", "+123457000", "17FU889")
        )

        // ListView
        /* listview.adapter = ListViewAdapter1(
             context = context ?: return,
             objects = objects
         )

         listview.setOnItemClickListener{_,_,position,_ ->
            val rnd : Random = Random(256)
           listview.getChildAt(position).setBackgroundColor(Color.argb(255,rnd.nextInt(256),rnd.nextInt(256),rnd.nextInt()))
              }*/

        //RecyclerView
        val rvAdapter = MyRecyclerViewAdapter(objects) { show -> showHideDeleteUpdate(show) }
        recyclerview = view.findViewById<RecyclerView>(R.id.recyclerview)
        recyclerview.adapter = rvAdapter

        recyclerview.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL, false
        )


        /* val btn1Change= view.findViewById<Button>(R.id.btn1)
         btn1Change.setOnClickListener{
             rvAdapter.addToList(Driver("New Driver","+3745789456","89GF572"))
             // edit text-ov ban
             rvAdapter.notifyDataSetChanged()

         }

         val btn2Change= view.findViewById<Button>(R.id.btn2)
         btn2Change.setOnClickListener{
             rvAdapter.removeFromList(1)  // need to be the selected one
             rvAdapter.notifyItemRemoved(1)
         }

         val btn3Change= view.findViewById<Button>(R.id.btn3)
         btn3Change.setOnClickListener{
             rvAdapter.setItemTo(5,Driver("Updated Driver", "+7854 00 1","54GF782"))
             //selected one
             rvAdapter.notifyDataSetChanged()
         }
             */

        //with DiffUtils
        val btn1Change = view.findViewById<Button>(R.id.btn1)
        btn1Change.setOnClickListener {

            parentFragmentManager.beginTransaction()
                .replace(R.id.container, NewItemFragment.newInstance("Fragment2"))
                .addToBackStack(null)
                .commit()

        }

        parentFragmentManager.setFragmentResultListener(
            "NEWITEMFRESULT",
            viewLifecycleOwner
        ) { _, bundle ->
            if (bundle.containsKey("newDriverNameKEY") &&
                bundle.containsKey("newDriverPhoneKEY") &&
                bundle.containsKey("newDriverCarKEY")
            ) {
                val newDriver = Driver(
                    bundle.get("newDriverNameKEY").toString(),
                    bundle.get("newDriverPhoneKEY").toString(),
                    bundle.get("newDriverCarKEY").toString()
                )
                val driverDiffUtilsCallback = DriverDiffUtilsCallback(
                    oldList = objects,
                    newList = rvAdapter.addToList(newDriver)
                )

                val diffResult = DiffUtil.calculateDiff(driverDiffUtilsCallback)
                diffResult.dispatchUpdatesTo(rvAdapter)
            }
        }

        val btn2Change = view.findViewById<Button>(R.id.btn2)
        btn2Change.setOnClickListener {
            val DriverDiffUtilsCallback = DriverDiffUtilsCallback(
                oldList = objects,
                newList = rvAdapter.removeFromList()
            )

            val diffResult = DiffUtil.calculateDiff(DriverDiffUtilsCallback)
            diffResult.dispatchUpdatesTo(rvAdapter)
        }


        val btn3Change = view.findViewById<Button>(R.id.btn3)
        btn3Change.setOnClickListener {
            //stegh save instance ov ban a petq mtatsel
            temp = rvAdapter.currentSelectedIndex
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, UpdateItemFragment.newInstance())
                .addToBackStack(null)
                .commit()
        }

        parentFragmentManager.setFragmentResultListener(
            "UPDATEITEMFRESULT",
            viewLifecycleOwner
        ) { _, bundle ->
            if (bundle.containsKey("updateDriverNameKEY") &&
                bundle.containsKey("updateDriverPhoneKEY")
            ) {
                val name = bundle.get("updateDriverNameKEY").toString()
                val phone = bundle.get("updateDriverPhoneKEY").toString()
                val DriverDiffUtilsCallback = DriverDiffUtilsCallback(
                    oldList = objects,
                    newList = rvAdapter.setItemTo(name, phone, temp)
                )
                temp = -1
                val diffResult = DiffUtil.calculateDiff(DriverDiffUtilsCallback)
                diffResult.dispatchUpdatesTo(rvAdapter)
            }
        }


        //DYNAMICLISTADAPTER PART
        /*
          val rvListAdapter = MyDynamicListAdapter()
          rvListAdapter.submitList(objects)

          val btn1ChangeWithListAdapter = view.findViewById<Button>(R.id.btn1)
          btn1ChangeWithListAdapter .setOnClickListener{
             val newList = objects.
                 toMutableList()
                 .apply { add(Driver("New Name", "New Number", "New Car Number"))
                 }.also {
                     objects= it
                 }
                 rvListAdapter.submitList(newList)
             }
*/

        //how to save the instance ?
        savedInstanceState?.let { data ->

        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(RECYCLER_VIEW_KEY, recyclerview.layoutManager?.onSaveInstanceState())

    }

    private fun showHideDeleteUpdate(show: Boolean) {
        view?.findViewById<Button>(R.id.btn2)?.isVisible = show
        view?.findViewById<Button>(R.id.btn3)?.isVisible = show
    }
}
