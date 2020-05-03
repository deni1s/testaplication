package com.example.app.menu

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app.menu.mainmenu.BaseMenuItem
import com.example.app.menu.mainmenu.MainMenuItem
import com.example.app.menu.mainmenu.MenuClickListener
import com.example.app.menu.mainmenu.NavHeaderItem
import com.example.app.menu.viewholders.MainMenuViewHolder
import com.example.app.menu.viewholders.NavHeaderViewHolder
import com.example.presentation.R
import java.util.*

class MainMenuAdapter(
    private var menuItemList: ArrayList<BaseMenuItem>,
    val clickListener: MenuClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private val MENU_ITEM = 0
        private val NAV_HEADER = 1
    }

    var selectedIndex: Int? = 0


    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        when (viewHolder.itemViewType) {
            MENU_ITEM -> bindMenuItem(viewHolder as MainMenuViewHolder, position)
            NAV_HEADER -> bindNavHeaderItem(
                viewHolder as NavHeaderViewHolder,
                this.menuItemList[position] as NavHeaderItem
            )
        }
    }

    private fun bindMenuItem(viewHolder: MainMenuViewHolder, position: Int) {
        val mainMenuItem = this.menuItemList[position] as MainMenuItem
        viewHolder.textViewMenuItemTitle.text = mainMenuItem.title
        viewHolder.itemView.setOnClickListener { clickListener.onMenuItemClicked(position) }
        if (position == selectedIndex) {
            viewHolder.itemView.setBackgroundColor(Color.DKGRAY)
        } else {
            viewHolder.itemView.setBackgroundColor(Color.LTGRAY)
        }
    }

    private fun bindNavHeaderItem(viewHolder: NavHeaderViewHolder, navHeaderItem: NavHeaderItem) {
        viewHolder.textViewTitle.text = navHeaderItem.title
    }

    override fun getItemViewType(position: Int): Int {
        if (menuItemList.get(position) is MainMenuItem) {
            return MENU_ITEM
        } else if (menuItemList.get(position) is NavHeaderItem) {
            return NAV_HEADER
        }
        return -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View
        when (viewType) {
            MENU_ITEM -> {
                view = inflater.inflate(R.layout.main_menu_item, parent, false)
                return MainMenuViewHolder(view)
            }
            else -> {
                view = inflater.inflate(R.layout.nav_header_view, parent, false)
                return NavHeaderViewHolder(view)
            }
        }
    }

    override fun getItemCount(): Int {
        return this.menuItemList.size
    }

    fun getItem(position: Int): Any? {
        return if (position >= 0 && position <= this.menuItemList.size - 1) {
            this.menuItemList[position]
        } else null
    }

    fun setSelectedItem(selectedIndex: Int) {
        this.selectedIndex = selectedIndex
        notifyDataSetChanged()
    }
}
