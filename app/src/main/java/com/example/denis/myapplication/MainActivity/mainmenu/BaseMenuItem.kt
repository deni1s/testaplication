package com.example.denis.myapplication.MainActivity.mainmenu

abstract class BaseMenuItem {

    companion object {
        val NAV_HEADER_TYPE = 0
        val MENU_ITEM_TYPE = 1
    }

    var title: String = ""
    var type = NAV_HEADER_TYPE
}