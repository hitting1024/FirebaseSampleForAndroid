package jp.hitting.firebasesampleforandroid

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.firebase.database.*


class MainActivity : AppCompatActivity() {

    private var mDatabase: DatabaseReference? = null

    private val mUserList = ArrayList<DataSnapshot>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.initFirebase()
    }

    private fun initFirebase() {
        this.mDatabase = FirebaseDatabase.getInstance().getReference()

        this.mDatabase?.child("users")?.addChildEventListener(object : ChildEventListener {

            override fun onChildAdded(dataSnapshot: DataSnapshot?, previousChildName: String?) {
                if (dataSnapshot == null) {
                    return
                }
                this@MainActivity.mUserList.add(dataSnapshot)
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
