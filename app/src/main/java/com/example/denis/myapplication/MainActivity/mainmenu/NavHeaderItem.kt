package com.example.denis.myapplication.MainActivity.mainmenu

class NavHeaderItem(navTitle: String) : BaseMenuItem() {

    init {
        this.title = navTitle
        this.type = NAV_HEADER_TYPE
    }
}
