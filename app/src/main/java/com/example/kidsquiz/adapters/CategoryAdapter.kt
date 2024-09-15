package com.example.kidsquiz.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kidsquiz.databinding.ItemQuizCategoryBinding
import com.example.kidsquiz.models.Category

class CategoryAdapter(private  val listener:setOnItemClickListeners):RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var binding:ItemQuizCategoryBinding? = null

    inner class CategoryViewHolder(itemBinding:ItemQuizCategoryBinding):
            RecyclerView.ViewHolder(itemBinding.root)

    private val differCallback = object: DiffUtil.ItemCallback<Category>(){
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.categoryName == newItem.categoryName
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
          return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.CategoryViewHolder {
        binding = ItemQuizCategoryBinding.inflate(LayoutInflater.from(parent.context),
            parent,false )
        return CategoryViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.CategoryViewHolder, position: Int) {
        val category = differ.currentList[position]
        holder.itemView.apply {
            binding!!.tvQuizCategory.text = category.categoryName
            binding!!.starCount.text = category.quiz_category_score.toString()
            setOnClickListener {
                listener.onItemClick(category,position)
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    interface setOnItemClickListeners{
        fun onItemClick(category: Category,position: Int)
    }
}