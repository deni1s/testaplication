package com.example.app.menu

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app.menu.mainmenu.BaseMenuItem
import com.example.app.menu.mainmenu.MainMenuItem
import com.example.app.menu.mainmenu.MenuClickListener
import com.example.app.menu.mainmenu.NavHeaderItem
import com.example.presentation.view.newslist.NewsFragment
import com.example.presentation.R
import com.google.android.material.navigation.NavigationView

import java.util.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var menuAdapter: MainMenuAdapter? = null
    private var currentFragmentIndex = 1
    private lateinit var navigationView: NavigationView
    private lateinit var menuRecyclerView: RecyclerView
    private lateinit var toolbar: Toolbar
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponent()
    }

    private fun initComponent() {
        menuRecyclerView = findViewById(R.id.menuRecyclerView)
        navigationView = findViewById(R.id.navigationView)
        toolbar = findViewById(R.id.toolbar)
        drawerLayout = findViewById(R.id.drawerLayout)
        menuRecyclerView.layoutManager = LinearLayoutManager(this)
        initNavigationMenu()
        initToolbar()
        initMenuList()
    }

    private fun prepareMenuItemList(): ArrayList<BaseMenuItem> {
        val menuList = ArrayList<BaseMenuItem>()
        menuList.add(NavHeaderItem(getString(R.string.title_of_main_menu)))
        menuList.add(MainMenuItem(getString(R.string.title_news), NewsFragment.fragmentInstance, NewsFragment.FRAGMENT_TAG))
        return menuList
    }

    private fun initNavigationMenu() {
        navigationView.setNavigationItemSelectedListener(this)
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(this,drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        toolbar.setNavigationOnClickListener {
            if (supportFragmentManager.backStackEntryCount > 0) {
                toggle.isDrawerIndicatorEnabled = false
                supportActionBar!!.setDisplayHomeAsUpEnabled(true)
                supportFragmentManager.popBackStack()
            } else {
                supportActionBar!!.setDisplayHomeAsUpEnabled(false)
                toggle.isDrawerIndicatorEnabled = true
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }

        initBackStackListener(toggle)
    }

    private fun initBackStackListener(toggle: ActionBarDrawerToggle) {
        supportFragmentManager.addOnBackStackChangedListener {
            if (supportFragmentManager.backStackEntryCount > 0) {
                toggle.isDrawerIndicatorEnabled = false
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            } else {
                supportActionBar!!.setDisplayHomeAsUpEnabled(false)
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
                toggle.isDrawerIndicatorEnabled = true
                supportFragmentManager.popBackStack()
            }
        }
    }

    private fun chooseMenuItemByPosition(position: Int) {
        val menuItem = menuAdapter!!.getItem(position)
        if (menuItem is MainMenuItem) {
            val fragment = menuItem.fragment
            loadFragment(fragment, menuItem.tag)
            currentFragmentIndex = position
           menuRecyclerView.dispatchSetSelected(true)
        }
    }

    private fun loadFragment(fragment: Fragment, tag : String?) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_wrapper, fragment, tag)
        fragmentTransaction.commit()
    }

    private fun initMenuList() {
        menuAdapter = MainMenuAdapter(prepareMenuItemList(), object :
            MenuClickListener {
            override fun onMenuItemClicked(menuItem: Int) {
                chooseMenuItemByPosition(menuItem)
                closeDrawer()
            }
        })
        menuAdapter!!.setSelectedItem(currentFragmentIndex)
        menuRecyclerView.adapter = menuAdapter
        chooseMenuItemByPosition(currentFragmentIndex)
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

    private fun closeDrawer() {
        drawerLayout.closeDrawer(GravityCompat.START)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return false
    }
}
