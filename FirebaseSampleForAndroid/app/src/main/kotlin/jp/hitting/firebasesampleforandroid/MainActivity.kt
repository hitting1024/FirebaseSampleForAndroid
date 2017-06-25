package jp.hitting.firebasesampleforandroid

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ListView
import com.google.firebase.database.*


class MainActivity : AppCompatActivity() {

    private var mDatabase: DatabaseReference? = null

    private val mUserList = ArrayList<DataSnapshot>()

    private var mAdapter: FirebaseAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.initLayout()
        this.initFirebase()
    }

    private fun initLayout() {
        this.setContentView(R.layout.activity_main)
        val listView = this.findViewById(R.id.listView) as ListView
        this.mAdapter = FirebaseAdapter(this, android.R.layout.simple_list_item_1, this.mUserList)
        listView.adapter = this.mAdapter
    }

    private fun initFirebase() {
        this.mDatabase = FirebaseDatabase.getInstance().getReference()

        this.mDatabase?.child("users")?.addChildEventListener(object : ChildEventListener {

            override fun onChildAdded(dataSnapshot: DataSnapshot?, previousChildName: String?) {
                if (dataSnapshot == null) {
                    return
                }
                this@MainActivity.mUserList.add(dataSnapshot)
                this@MainActivity.mAdapter?.notifyDataSetChanged()
            }

            override fun onChildMoved(dataSnapshot: DataSnapshot?, previousChildName: String?) {
                // nothing
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot?, previousChildName: String?) {
                // nothing
            }

            override fun onChildRemoved(dataSnapshot: DataSnapshot?) {
                // nothing
            }

            override fun onCancelled(databaseError: DatabaseError?) {
                // nothing
            }

        })
    }
}
