package com.example.kidsquiz.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kidsquiz.databinding.ItemPlayerLayoutBinding
import com.example.kidsquiz.models.Player
import com.example.kidsquiz.fragments.MultiPlayerFragment

class PlayerAdapter( val fragment: Fragment):RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {


    private var binding: ItemPlayerLayoutBinding? = null











    inner class PlayerViewHolder(itemBinding: ItemPlayerLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root){


        }


    private val differCallback = object : DiffUtil.ItemCallback<Player>() {
        override fun areItemsTheSame(oldItem: Player, newItem: Player): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Player, newItem: Player): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        binding =
            ItemPlayerLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlayerViewHolder(binding!!)

    }

    @SuppressLint("NotifyDataSetChanged", "CommitPrefEdits")
    override fun onBindViewHolder(
        holder: PlayerViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        val player = differ.currentList[position]



        holder.itemView.apply {
            binding!!.playerName.text = player.name
            binding!!.starCount.text = player.totalStarCount.toString()

            setOnClickListener {



        if(fragment is MultiPlayerFragment){
            //  fragment.loadFilesToSharedPref(player)
            fragment.navigateToStartFragment(player)
            fragment.itemClicked(differ.currentList,player)

}


            }


        }






//        row_index_position=-1
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }







 }