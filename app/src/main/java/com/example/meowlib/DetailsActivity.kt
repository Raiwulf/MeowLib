package com.example.meowlib

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.meowlib.databinding.ActivityDetailsBinding
import com.example.meowlib.room.FavsDatabase
import com.example.meowlib.room.FavsModel

class DetailsActivity() : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private lateinit var state: String
    private lateinit var  favDB: FavsDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        favDB = FavsDatabase.getFavsDatabase(this@DetailsActivity)!!

        val name=intent.getStringExtra("catNameDetails").orEmpty()
        val desc=intent.getStringExtra("catDescDetails").orEmpty()
        val origin=intent.getStringExtra("catOriginDetails").orEmpty()
        val temper=intent.getStringExtra("catTemperamentDetails").orEmpty()

        state = intent.getStringExtra("state").orEmpty()

        val picInfo = intent.getStringExtra("catPicDetails").orEmpty()

        val favCats = favDB.favsDao.listFav()
        for(cats in favCats){
            if(name==cats.favName){
                binding.toggleFavDetails.isChecked=true
            }
        }

        binding.catNameDetails.text = name
        binding.catTemperamentDetails.text = temper
        binding.catOriginDetails.text = origin
        binding.catDescDetails.text = desc

        if(picInfo!!.contains("https")){
            Glide.with(this).load(picInfo).into(binding.catPicDetails)
        }
        else{
            Glide.with(this).load("https://cdn2.thecatapi.com/images/"+picInfo+".jpg").into(binding.catPicDetails)
        }

        binding.toggleFavDetails.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                favDB.favsDao.addFav(
                    FavsModel(favId = 0, favName = name, favOrigin = origin, favPic = picInfo, favDesc = desc, favTempa = temper)
                )
            } else {
                favDB.favsDao.delFav((name))
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (state=="fav"){
            val intent = Intent(this@DetailsActivity, FavoritesActivity::class.java)
            startActivity(intent)
        }
        else{
            val intent = Intent(this@DetailsActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}