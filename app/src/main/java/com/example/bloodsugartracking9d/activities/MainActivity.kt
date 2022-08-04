package com.example.bloodsugartracking9d.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.example.bloodsugartracking9d.R
import com.example.bloodsugartracking9d.databinding.ActivityMainBinding
import com.example.bloodsugartracking9d.koincomponents.ViewmodelKoin
import com.example.bloodsugartracking9d.unit_mg_dL
import com.example.bloodsugartracking9d.unit_mmol
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {

    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    lateinit var appBarConfiguration: AppBarConfiguration
    var drawerLayout: DrawerLayout? = null
    lateinit var  toolbar : Toolbar
    private val mViewModel: ViewmodelKoin by viewModel()

    companion object {
        fun start(context: Context) {
            var intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //toolbar = binding.toolbarLayout.findViewById(R.id.toolbar)
        navController = findNavController(R.id.nav_host_fragment)
        binding.bottomNavView.setupWithNavController(navController)
        drawerLayout = binding.drawerLayout
        setSupportActionBar(binding.toolbar)
        appBarConfiguration = AppBarConfiguration(setOf(R.id.main_fragment,R.id.tracking_fragment, R.id.statistics_fragment, R.id.history_fragment, R.id.tipsFragment), drawerLayout)
        binding.navView.setupWithNavController(navController)
        setupActionBarWithNavController(navController, appBarConfiguration)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_drawer_icon)
        binding.navView.setNavigationItemSelectedListener(this)
        binding.bottomNavView.setItemIconTintList(null);

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.tracking_fragment -> showBottomNav()
                R.id.statistics_fragment -> showBottomNav()
                R.id.tipsFragment -> showBottomNav()
                R.id.history_fragment -> showBottomNav()
                else -> hideBottomNav()
            }
        }


    }

    private fun showBottomNav() {
        binding.bottomNavView.visibility = View.VISIBLE

    }

    private fun hideBottomNav() {
        binding.bottomNavView.visibility = View.GONE

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(navController) ||
                super.onOptionsItemSelected(item)
    }

    //open drawer when drawer icon clicked and back btn presse
    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp(appBarConfiguration)
    }

   /* fun drawerIcon() {
         supportActionBar?.setHomeAsUpIndicator(R.drawable.layer_13)

    }*/

    fun get_toolbar()  : Toolbar {

        return  binding.toolbar
    }





    override fun onNavigationItemSelected(@NonNull item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {


            R.id.tracking_fragment -> {

            /*    val bundle = Bundle()
                bundle.putInt("id", 1)*/
                item.setIcon(R.drawable.ic_bt_statistics_icon)
                drawerLayout?.closeDrawers()
                navController.navigate(R.id.tracking_fragment)
                return true


            }

            R.id.statistics_fragment -> {

                drawerLayout?.closeDrawers()
                navController.navigate(R.id.statistics_fragment)

            }

            R.id.history_fragment -> {


                drawerLayout?.closeDrawers()
                navController.navigate(R.id.history_fragment)

            }

            R.id.tipsFragment -> {
                drawerLayout?.closeDrawers()
                navController.navigate(R.id.tipsFragment)

            }

            R.id.unit_mmol -> {

                mViewModel.set_unit_selected(unit_mmol)
                binding.drawerLayout.closeDrawers()

            }

            R.id.unit_mg -> {

                mViewModel.set_unit_selected(unit_mg_dL)
                binding.drawerLayout.closeDrawers()
            }
        }


        return true
    }


    override fun onResume() {
        super.onResume()

    }




}