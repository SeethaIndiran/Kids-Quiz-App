package com.example.kidsquiz.fragments

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kidsquiz.R
import com.example.kidsquiz.adapters.CategoryAdapter
import com.example.kidsquiz.common.Constants
import com.example.kidsquiz.databinding.FragmentQuizCategoryBinding
import com.example.kidsquiz.models.Category
import com.example.kidsquiz.viewmodels.PlayerViewmodl
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class QuizCategoryFragment : Fragment(),CategoryAdapter.setOnItemClickListeners {

    private lateinit var binding:FragmentQuizCategoryBinding
    private lateinit var categoryAdapter:CategoryAdapter
    private val viewModel: PlayerViewmodl by viewModels()
    //val args:QuizCategoryFragmentArgs by navArgs()

    @Inject
    lateinit var sharedPref:SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding = FragmentQuizCategoryBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()

        val quiz_type = sharedPref.getInt(Constants.QUIZ_TYPE,0)
        //val quiz_category = sharedPref.getInt(Constants.QUIZ_CATEGORY,0)
        val quizType = Constants.provideQuizList()[quiz_type]
        //val quizCategory : ArrayList<Category> = quizType.quizCategory as ArrayList<Category>

        val new_player = sharedPref.getBoolean(Constants.NEW_PLAYER,false)

        if(new_player){
            viewModel.getAllPlayers().observe(viewLifecycleOwner) {
                if (it.isNotEmpty()) {
                    val listSize = it.size - 1
                    val player = it[listSize]


                    setUpRecyclerView()
                    categoryAdapter.differ.submitList(player.quiz_type[quiz_type].quizCategory)
                }
            }
        }else{
            viewModel.getPlayer().observe(viewLifecycleOwner) {
                if (it != null) {
                    val player = it

                    setUpRecyclerView()
                    categoryAdapter.differ.submitList(player.quiz_type[quiz_type].quizCategory)
                }
            }

        }


    }

    private fun setUpRecyclerView(){
        categoryAdapter = CategoryAdapter(this)
        binding.recyclerViewCategoryType.apply {
            adapter = categoryAdapter
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
        }
    }

    @SuppressLint("CommitPrefEdits")
    override fun onItemClick(category: Category, position: Int) {

        sharedPref.edit().putInt(Constants.QUIZ_CATEGORY,position).apply()

        val bundle = Bundle().apply {
            putSerializable("category",category)
        }
        findNavController().navigate(R.id.action_quizCategoryFragment_to_questionsFragment,bundle)
    }


}