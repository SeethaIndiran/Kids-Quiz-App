package com.example.kidsquiz.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kidsquiz.databinding.ItemSingleQuizPlayerBinding
import com.example.kidsquiz.models.Player
import com.example.kidsquiz.models.QuizType

class SinglePlayerAdapter( private val listener: setOnItemClickListener):RecyclerView.Adapter<SinglePlayerAdapter.PlayerViewHolder>() {


    private var binding: ItemSingleQuizPlayerBinding? = null











    inner class PlayerViewHolder(itemBinding: ItemSingleQuizPlayerBinding) :
        RecyclerView.ViewHolder(itemBinding.root){


    }


    private val differCallback = object : DiffUtil.ItemCallback<QuizType>() {
        override fun areItemsTheSame(oldItem: QuizType, newItem: QuizType): Boolean {
            return oldItem.count == newItem.count
        }

        override fun areContentsTheSame(oldItem: QuizType, newItem: QuizType): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        binding =
            ItemSingleQuizPlayerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlayerViewHolder(binding!!)

    }

    @SuppressLint("NotifyDataSetChanged", "CommitPrefEdits")
    override fun onBindViewHolder(
        holder: PlayerViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        val quiz_type = differ.currentList[position]



        holder.itemView.apply {
            binding!!.quizTypeName.text = quiz_type.name
            binding!!.starCount.text = quiz_type.quiz_type_score.toString()

            setOnClickListener {
                listener.onItemClick(quiz_type,position)
            }


        }






//        row_index_position=-1
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
    interface setOnItemClickListener{
        fun onItemClick(quizType: QuizType, position: Int)
    }






}