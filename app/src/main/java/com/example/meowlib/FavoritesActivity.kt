package com.example.meowlib

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.meowlib.adapters.FavsAdapter
import com.example.meowlib.databinding.ActivityFavoritesBinding
import com.example.meowlib.room.FavsDatabase
import com.example.meowlib.room.FavsModel

class FavoritesActivity : AppCompatActivity() {
    private lateinit var  favCats: List<FavsModel>
    private lateinit var  favDB: FavsDatabase
    private lateinit var binding: ActivityFavoritesBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        favDB = FavsDatabase.getFavsDatabase(this@FavoritesActivity)!!
        val favCats = favDB.favsDao.listFav()

        val linearLayoutManager= LinearLayoutManager(this)
        val recyclerViewAdapter= FavsAdapter(this)
        binding.favRecylerView.layoutManager = linearLayoutManager
        binding.favRecylerView.adapter = recyclerViewAdapter

        recyclerViewAdapter.setItems(favCats)

    }
}