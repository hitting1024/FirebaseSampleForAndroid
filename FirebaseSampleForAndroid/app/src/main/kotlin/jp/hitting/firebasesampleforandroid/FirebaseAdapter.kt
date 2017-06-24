package jp.hitting.firebasesampleforandroid

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import java.util.*

class FirebaseAdapter(context: Context, textViewResourceId: Int, private val items: MutableList<DataSnapshot>) : ArrayAdapter<DataSnapshot>(context, textViewResourceId) {

    private val inflater: LayoutInflater

    init {
        this.inflater = LayoutInflater.from(context)
    }

    override fun getCount(): Int {
        return this.items.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val user = this.items[position].value as Dictionary<String, String>
        val view = this.inflater.inflate(android.R.layout.simple_list_item_1, null)
        val text1 = view.findViewById(android.R.id.text1) as TextView
        text1.text = user["name"] + ": " + user["address"]
        return view
    }

}