package com.example.kidsquiz.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kidsquiz.databinding.ItemQuizTypeBinding
import com.example.kidsquiz.models.QuizType

class QuizAdapter(private val listener:setOnItemClickListener):RecyclerView.Adapter<QuizAdapter.QuizViewHolder>() {

    private var binding : ItemQuizTypeBinding? = null

     inner class QuizViewHolder(itemQuizTypeBinding: ItemQuizTypeBinding):
            RecyclerView.ViewHolder(itemQuizTypeBinding.root)

     private val differCallback = object: DiffUtil.ItemCallback<QuizType>(){
         override fun areItemsTheSame(oldItem: QuizType, newItem: QuizType): Boolean {
             return oldItem.count == newItem.count
         }

         override fun areContentsTheSame(oldItem: QuizType, newItem: QuizType): Boolean {
          return oldItem == newItem
         }

     }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
              binding = ItemQuizTypeBinding.inflate(LayoutInflater.from(parent.context),
           parent,false )
        return QuizViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
             val quizType = differ.currentList[position]
        holder.itemView.apply {
            binding!!.tvQuizType.text = quizType.name
            binding!!.tvQuizCounts.text = quizType.quiz_type_score.toString()
            setOnClickListener {
                      listener.onItemClick(quizType,position)
            }
        }
    }

    override fun getItemCount(): Int {
       return differ.currentList.size
    }

    interface setOnItemClickListener{
        fun onItemClick(quizType:QuizType,position: Int)
    }
}