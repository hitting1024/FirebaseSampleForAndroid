package jp.hitting.firebasesampleforandroid

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference


class StorageActivity : AppCompatActivity() {

    private var storageRef: StorageReference? = null

    private var imageView1: ImageView? = null
    private var imageView2: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.initLayout()
        this.initFirebase()
    }

    private fun initLayout() {
        this.setContentView(R.layout.activity_storage)
        this.imageView1 = this.findViewById(R.id.image1) as ImageView
        this.imageView2 = this.findViewById(R.id.image2) as ImageView
    }

    private fun initFirebase() {
        this.storageRef = FirebaseStorage.getInstance().reference
    }

    private fun loadImage(name: String, completion: (Bitmap) -> Unit) {
        this.storageRef?.child("${name}.png")?.getBytes(1 * 1024 * 1024 /* 1MB */)
                ?.addOnSuccessListener {
                    val image = BitmapFactory.decodeByteArray(it, 0, it.size)
                    completion(image)
                }
    }

}
