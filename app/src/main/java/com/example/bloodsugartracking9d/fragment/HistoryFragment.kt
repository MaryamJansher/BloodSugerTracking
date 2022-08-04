package com.example.bloodsugartracking9d.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bloodsugartracking9d.R
import com.example.bloodsugartracking9d.activities.MainActivity
import com.example.bloodsugartracking9d.adapters.CustomExpandableListAdapter
import com.example.bloodsugartracking9d.adapters.History_Adapter
import com.example.bloodsugartracking9d.all_interface.RecyclerViewclicks


import com.example.bloodsugartracking9d.koincomponents.ViewmodelKoin
import com.example.bloodsugartracking9d.room.UserDetail
import kotlinx.coroutines.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.coroutines.CoroutineContext

class HistoryFragment : Fragment(), RecyclerViewclicks, CoroutineScope {


    private val mViewModel: ViewmodelKoin by viewModel()
    lateinit var historyBinding: com.example.bloodsugartracking9d.databinding.FragmentHistoryBinding
    lateinit var history_adapter: History_Adapter
    var getAllDataList: ArrayList<UserDetail> = ArrayList<UserDetail>()
    var expandableList_dates: ArrayList<String> = ArrayList<String>()
    var expandableListDetail: HashMap<String, ArrayList<UserDetail>> = HashMap<String, ArrayList<UserDetail>>();
    var child_list: ArrayList<UserDetail> = ArrayList<UserDetail>()
    var expandableListTitle: ArrayList<String> = ArrayList<String>()
    lateinit var expandableListView: ExpandableListView
    lateinit var expandableListAdapter: ExpandableListAdapter

    lateinit var userDetail: UserDetail
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main/*+ job*/

    // private lateinit var job: Job
    //lateinit var  handler : CoroutineExceptionHandler
    override fun onAttach(context: Context) {
        super.onAttach(context)
     //   (activity as MainActivity).drawerIcon()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        historyBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false)
        expandableListView = historyBinding.expandableListView
        expandableListAdapter = CustomExpandableListAdapter(requireContext(), expandableListTitle, expandableListDetail)
        expandableListView.setAdapter(expandableListAdapter)
        expandableListView.setOnGroupExpandListener { groupPosition ->

            Toast.makeText(requireContext(), expandableListTitle[groupPosition] + " List Expanded.", Toast.LENGTH_SHORT
            ).show()
        }

        expandableListView.setOnGroupCollapseListener { groupPosition ->
            Toast.makeText(requireContext(), expandableListTitle[groupPosition] + " List Collapsed.", Toast.LENGTH_SHORT).show()
        }
        expandableListView.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->
            Toast.makeText(
                requireContext(),
                expandableListTitle[groupPosition] + " -> "
                        + expandableListDetail[expandableListTitle[groupPosition]]!![childPosition],
                Toast.LENGTH_SHORT
            ).show()
            false
        }

        return historyBinding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    fun getAllData() {

        mViewModel.getalldata().observe(requireActivity()) {
            if (isAdded or isVisible) {

                getAllDataList = it as ArrayList<UserDetail>

                for (i in 0 until getAllDataList.size) {

                    if (getAllDataList.get(i).date !in expandableList_dates) {

                        expandableList_dates.add(getAllDataList.get(i).date)
                    }

                }

                for (n in 0 until expandableList_dates.size) {

                    for (i in 0 until getAllDataList.size) {

                        if (getAllDataList.get(i).date.equals(expandableList_dates.get(n))) {

                            userDetail = getAllDataList.get(i)
                            child_list.add(userDetail)
                            expandableListDetail[expandableList_dates.get(n)] = child_list
                            child_list.clear()
                        }


                    }

                }

                expandableListTitle = ArrayList(expandableListDetail.keys)


                /*history_adapter = History_Adapter(getAllDataList, this@HistoryFragment)
                historyBinding.historyRecyclerView.layoutManager =
                    LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
                historyBinding.historyRecyclerView.adapter = history_adapter*/
            }
        }


        /* launch {

             val getlist = async(Dispatchers.Main)
             { mViewModel.getalldata().observe( viewLifecycleOwner, {
                         get_all_data_list = it as ArrayList<UserDetail>
                     })
             }

             try {
                 getlist.await()
             }  catch (e: Exception) {
                 println("Handle Exception $e")

             }


             withContext(Dispatchers.Main) {

                 history_adapter = History_Adapter(get_all_data_list, this@HistoryFragment)
                 historyBinding.historyRecyclerView.setLayoutManager(LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL, false))
                 historyBinding.historyRecyclerView.adapter = history_adapter
             }

         }

 */


    }


    override fun onResume() {
        super.onResume()

        getAllData()
        /* handler = CoroutineExceptionHandler { _, exception ->
             Log.d(ContentValues.TAG, "$exception handled !")
         }*/
    }

    override fun recyclerview_itemclick(position: Int) {

        userDetail = getAllDataList[position]

        delete_data()


    }

    fun delete_data() {

        launch {

            async(Dispatchers.IO) {
                mViewModel.delete(userDetail)

            }.await()

            withContext(Dispatchers.Main) {

                Toast.makeText(context, "Data Deleted", Toast.LENGTH_SHORT).show()

            }

        }

    }

}

