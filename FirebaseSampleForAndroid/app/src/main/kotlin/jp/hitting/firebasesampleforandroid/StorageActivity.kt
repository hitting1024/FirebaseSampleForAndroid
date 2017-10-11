package jp.hitting.firebasesampleforandroid

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.ByteArrayOutputStream


class StorageActivity : AppCompatActivity() {

    private var storageRef: StorageReference? = null

    private var imageView1: ImageView? = null
    private var imageView2: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.initLayout()
        this.initFirebase()
        this.loadImage1()
        this.loadImage2()
    }

    private fun initLayout() {
        this.setContentView(R.layout.activity_storage)
        this.imageView1 = this.findViewById(R.id.image1) as ImageView
        this.imageView2 = this.findViewById(R.id.image2) as ImageView
    }

    private fun initFirebase() {
        this.storageRef = FirebaseStorage.getInstance().reference
    }

    private fun loadImage1() {
        this.loadImage("1", {
            this.imageView1?.setImageBitmap(it)
        })
    }

    private fun loadImage2() {
        this.loadImage("2", {
            this.imageView2?.setImageBitmap(it)
        })
    }

    private fun loadImage(name: String, completion: (Bitmap) -> Unit) {
        this.storageRef?.child("${name}.png")?.getBytes(1 * 1024 * 1024 /* 1MB */)
                ?.addOnSuccessListener {
                    val image = BitmapFactory.decodeByteArray(it, 0, it.size)
                    completion(image)
                }
    }

    private fun uploadImage(name: String, completion: () -> Unit) {
        val resourceId = this.resources.getIdentifier(name, "drawable", this.packageName)
        val image = BitmapFactory.decodeResource(this.resources, resourceId)
        ByteArrayOutputStream().use {
            image.compress(Bitmap.CompressFormat.PNG, 100, it)
            this.storageRef?.child("${name}.png")?.putBytes(it.toByteArray())
                    ?.addOnSuccessListener {
                        completion()
                    }
        }
    }

}
