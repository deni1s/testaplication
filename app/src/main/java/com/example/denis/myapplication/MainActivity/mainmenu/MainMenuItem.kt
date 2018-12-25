package com.example.denis.myapplication.MainActivity.mainmenu

import com.example.denis.myapplication.MainActivity.fragments.BaseFragment
import com.example.denis.myapplication.MainActivity.fragments.news.NewsFragment

class MainMenuItem(menuTitle: String, menuFragment: BaseFragment, fragmentTag : String) : BaseMenuItem() {
    var tag : String = ""
    var fragment : BaseFragment

    init {
        this.title = menuTitle
        this.fragment = menuFragment
        this.tag = fragmentTag
        this.type = MENU_ITEM_TYPE
    }
}