package com.example.app.menu.mainmenu

import com.example.presentation.view.BaseFragment

class MainMenuItem(title: String, menuFragment: BaseFragment, fragmentTag : String) : BaseMenuItem(title) {

    var tag : String = ""
    var fragment : BaseFragment

    init {
        this.fragment = menuFragment
        this.tag = fragmentTag
    }
}
