package com.example.denis.myapplication.view.menu.mainmenu

import com.example.denis.myapplication.view.BaseFragment

class MainMenuItem(title: String, val menuFragment: BaseFragment, val fragmentTag : String) : BaseMenuItem(title) {

    var tag : String = ""
    var fragment : BaseFragment

    init {
        this.fragment = menuFragment
        this.tag = fragmentTag
    }
}