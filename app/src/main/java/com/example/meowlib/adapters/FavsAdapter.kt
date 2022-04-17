package com.example.meowlib.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.meowlib.DetailsActivity
import com.example.meowlib.databinding.RowItemsBinding
import com.example.meowlib.room.FavsModel

class FavsAdapter(val context: Context) :
    RecyclerView.Adapter<FavsAdapter.catViewHolder>() {

    var catList: MutableList<FavsModel> = mutableListOf()

    class catViewHolder(val binding: RowItemsBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): catViewHolder {

        return catViewHolder(
            RowItemsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: catViewHolder, position: Int) {
        val catItem = catList[position]

        holder.binding.catName.text = catItem.favName
        holder.binding.catOrigin.text= catItem.favOrigin
        if(catItem.favPic!=null){
            Glide.with(context).load(catItem.favPic).into(holder.binding.catPic)
        }
        else{
            Glide.with(context).load(catItem.favPic).into(holder.binding.catPic)
        }

        holder.itemView.setOnClickListener{
            onUserClick(catItem)
        }
    }

    override fun getItemCount(): Int {
        return catList.size
    }

    fun setItems(favsList: List<FavsModel>) {
        catList.clear()
        catList.addAll(favsList)
        notifyDataSetChanged()
    }

    fun onUserClick(catItem: FavsModel){

        val intent = Intent(context, DetailsActivity::class.java)
        intent.putExtra("catNameDetails",catItem.favName)
        intent.putExtra("catDescDetails", catItem.favDesc)
        intent.putExtra("catOriginDetails", catItem.favOrigin)
        intent.putExtra("catTemperamentDetails", catItem.favTempa)
        intent.putExtra("state", "fav")

        if(catItem.favPic!==null){
            intent.putExtra("catPicDetails",catItem.favPic)

        }
        else{
            intent.putExtra("catPicDetails",catItem.favPic)
        }

        context.startActivity(intent)
    }
}