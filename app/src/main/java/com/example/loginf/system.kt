package com.example.loginf

import android.os.Bundle
import android.provider.AlarmClock
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.loginf.databinding.ActivitySystemBinding

class system : templateMain() {
    lateinit var contentView: ActivitySystemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        contentView= ActivitySystemBinding.inflate(layoutInflater)

        var user=intent.getStringExtra(AlarmClock.EXTRA_MESSAGE)
        var imgRes=intent.getStringExtra("avatar").toString()



        var res: ImageView?=contentView.imageView2 as ImageView?
        var resImage="https://geniomaticrodas.edu.pe/resources/${imgRes}"
        res?.setImage(resImage)

        contentView.lblUser.text=user

        val lista=contentView.myRec
        val miAdaptador=myAdapterRecycler()

        lista.layoutManager=GridLayoutManager(this,2)
        lista.adapter=miAdaptador
        lista.itemAnimator

        contentView.grid.setOnClickListener {
            v-> lista.layoutManager=GridLayoutManager(this,2)
        }

        contentView.line.setOnClickListener {
                v-> lista.layoutManager=LinearLayoutManager(this)
        }

        setContentView(contentView.root)

    }

    fun ImageView.setImage(url: String){
        Glide.with(context)
            .load(url)
            .placeholder(R.drawable.ic_baseline_account_circle_24)
            .error(R.drawable.ic_baseline_error_24)
            .circleCrop()
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(this)
        this.setBackgroundResource(R.drawable.backavatar)
    }
}