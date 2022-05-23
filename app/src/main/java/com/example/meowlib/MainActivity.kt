package com.example.meowlib

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.lottie.LottieAnimationView
import com.example.meowlib.adapters.RecyclerViewAdapter
import com.example.meowlib.databinding.ActivityMainBinding
import com.example.meowlib.model.CatsAPI
import com.example.meowlib.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        linearLayoutManager= LinearLayoutManager(this)
        recyclerViewAdapter= RecyclerViewAdapter(this)
        binding.fetchedList.layoutManager = linearLayoutManager
        binding.fetchedList.adapter = recyclerViewAdapter

        supportActionBar?.hide()
        val anim2 = findViewById<LottieAnimationView>(R.id.catSearchAnim)

        Handler(Looper.getMainLooper()).postDelayed({
            anim2.visibility= View.VISIBLE
            anim2.playAnimation()
        }, 128)

        binding.favsBtn.setOnClickListener {
            if (binding.favsBtn.isPressed){
                val intent = Intent(this, FavoritesActivity::class.java)
                startActivity( intent )
            }
        }

        val apiInterface = ApiInterface.create().getData()

        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                val apiInterface = ApiInterface.create().searchData(p0.toString())
                apiInterface.enqueue( object : Callback<List<CatsAPI>> {
                    override fun onResponse(
                        call: Call<List<CatsAPI>>,
                        response: Response<List<CatsAPI>>
                    ) {
                            recyclerViewAdapter.setItems(response.body()!! as MutableList<CatsAPI> )
                    }

                    override fun onFailure(call: Call<List<CatsAPI>>, t: Throwable) {

                    }
                })
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }


        })

        apiInterface.enqueue( object : Callback<List<CatsAPI>> {
            override fun onResponse(
                call: Call<List<CatsAPI>>,
                response: Response<List<CatsAPI>>
            ) {


                if(response.body() != null)
                    recyclerViewAdapter.setItems(response.body()!! as MutableList<CatsAPI>)
            }

            override fun onFailure(call: Call<List<CatsAPI>>, t: Throwable) {
            val msg = Toast.makeText(applicationContext,"Failed", Toast.LENGTH_SHORT)
                msg.setGravity(Gravity.CENTER, 200, 200)
                msg.show()
            }
        })

        val savedData = this.getPreferences(Context.MODE_PRIVATE)

    }
}
