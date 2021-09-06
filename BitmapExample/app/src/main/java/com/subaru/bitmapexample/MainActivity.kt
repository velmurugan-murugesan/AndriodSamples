package com.subaru.bitmapexample

import android.graphics.*
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val borderSize = 2
        val bmp = findViewById<ImageView>(R.id.imageOriginal)

        val bb = addBorderToRoundedBitmap(bmp,0, 1, Color.RED)

        val imae = findViewById<ImageView>(R.id.imageUpdated)
        imae.setImageBitmap(bb)

    }

    // Custom method to add a border around rounded bitmap
    protected fun addBorderToRoundedBitmap(srcBitmap: Bitmap, cornerRadius: Int, borderWidth: Int, borderColor: Int): Bitmap? {
        // We will hide half border by bitmap
        var borderWidth = borderWidth
        borderWidth *= 2
        // Initialize a new Bitmap to make it bordered rounded bitmap
        val dstBitmap = Bitmap.createBitmap(srcBitmap.width + borderWidth,  // Width
                srcBitmap.height + borderWidth,  // Height
                Bitmap.Config.ARGB_8888 // Config
        )
        // Initialize a new Canvas instance
        val canvas = Canvas(dstBitmap)
        // Initialize a new Paint instance to draw border
        val paint = Paint()
        paint.setColor(borderColor)
        paint.setStyle(Paint.Style.STROKE)
        paint.setStrokeWidth(borderWidth.toFloat())
        paint.setAntiAlias(true)
        // Initialize a new Rect instance
        val rect = Rect(borderWidth / 2, borderWidth / 2, dstBitmap.width - borderWidth / 2, dstBitmap.height - borderWidth / 2)
        // Initialize a new instance of RectF;
        val rectF = RectF(rect)
        // Draw rounded rectangle as a border/shadow on canvas
        canvas.drawRoundRect(rectF, cornerRadius.toFloat(), cornerRadius.toFloat(), paint)
        // Draw source bitmap to canvas
        canvas.drawBitmap(srcBitmap, (borderWidth / 2).toFloat(), (borderWidth / 2).toFloat(), null)
        /*
            public void recycle ()
                Free the native object associated with this bitmap, and clear the reference to the
                pixel data. This will not free the pixel data synchronously; it simply allows it to
                be garbage collected if there are no other references. The bitmap is marked as
                "dead", meaning it will throw an exception if getPixels() or setPixels() is called,
                and will draw nothing. This operation cannot be reversed, so it should only be
                called if you are sure there are no further uses for the bitmap. This is an advanced
                call, and normally need not be called, since the normal GC process will free up this
                memory when there are no more references to this bitmap.
        */srcBitmap.recycle()
        // Return the bordered circular bitmap
        return dstBitmap
    }
}