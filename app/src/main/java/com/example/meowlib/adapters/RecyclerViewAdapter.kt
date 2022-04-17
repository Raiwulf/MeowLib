package com.example.meowlib.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.meowlib.DetailsActivity
import com.example.meowlib.databinding.RowItemsBinding
import com.example.meowlib.model.CatsAPI

class RecyclerViewAdapter(val context: Context) :
    RecyclerView.Adapter<RecyclerViewAdapter.catViewHolder>() {

    var catList: MutableList<CatsAPI> = mutableListOf()

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

        holder.binding.catName.text = catItem.name
        holder.binding.catOrigin.text= catItem.origin
        if(catItem.image!==null){
            Glide.with(context).load(catItem.image?.url).into(holder.binding.catPic)
        }
        else{
            Glide.with(context).load("https://cdn2.thecatapi.com/images/"+catItem.reference_image_id+".jpg").into(holder.binding.catPic)
        }

        holder.itemView.setOnClickListener{
            onUserClick(catItem)
        }
    }

    override fun getItemCount(): Int {
        return catList.size
    }

    fun setItems(catsList: MutableList<CatsAPI>) {
        catList.clear()
        catList.addAll(catsList)
        notifyDataSetChanged()
    }

    fun onUserClick(catItem: CatsAPI){

        val intent = Intent(context, DetailsActivity::class.java)
        intent.putExtra("catNameDetails",catItem.name)
        intent.putExtra("catDescDetails",catItem.description)
        intent.putExtra("catOriginDetails", catItem.origin)
        intent.putExtra("catTemperamentDetails", catItem.temperament)
        intent.putExtra("state", "main")

        if(catItem.image!==null){
            intent.putExtra("catPicDetails",catItem.image.url)

        }
        else{
            intent.putExtra("catPicDetails",catItem.reference_image_id)
        }

        context.startActivity(intent)
    }
}