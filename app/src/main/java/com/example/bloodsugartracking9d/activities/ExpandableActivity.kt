/*
package com.example.bloodsugartracking9d.activities


import android.os.Bundle
import android.view.View
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bloodsugartracking9d.ExpandableListDataPump
import com.example.bloodsugartracking9d.R
import com.example.bloodsugartracking9d.adapters.CustomExpandableListAdapter


class ExpandableActivity : AppCompatActivity() {
    var expandableListView: ExpandableListView? = null
    var expandableListAdapter: ExpandableListAdapter? = null
    var expandableListTitle: ArrayList<String>? = null
    var expandableListDetail: HashMap<String, ArrayList<String>>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expandable)

        expandableListView = findViewById<View>(R.id.expandableListView) as ExpandableListView
        expandableListDetail = ExpandableListDataPump.getdata()
        expandableListTitle = ArrayList(expandableListDetail!!.keys)
        expandableListAdapter = CustomExpandableListAdapter(this, expandableListTitle!!, expandableListDetail!!)
        expandableListView!!.setAdapter(expandableListAdapter)
        expandableListView!!.setOnGroupExpandListener { groupPosition ->

            Toast.makeText(applicationContext, expandableListTitle!![groupPosition] + " List Expanded.", Toast.LENGTH_SHORT).show()
        }

        expandableListView!!.setOnGroupCollapseListener { groupPosition -> Toast.makeText(
            applicationContext, expandableListTitle!![groupPosition] + " List Collapsed.",
                Toast.LENGTH_SHORT
            ).show()
        }
        expandableListView!!.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->
            Toast.makeText(
                applicationContext,
                expandableListTitle!![groupPosition] + " -> "
                        + expandableListDetail!![expandableListTitle!![groupPosition]]!![childPosition],
                Toast.LENGTH_SHORT
            ).show()
            false
        }
    }
}
*/
