package com.example.kidsquiz.fragments

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kidsquiz.R
import com.example.kidsquiz.adapters.QuizAdapter
import com.example.kidsquiz.common.Constants
import com.example.kidsquiz.databinding.FragmentQuizTypeBinding
import com.example.kidsquiz.models.Category
import com.example.kidsquiz.models.QuizType
import com.example.kidsquiz.viewmodels.PlayerViewmodl
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class QuizTypeFragment : Fragment(),QuizAdapter.setOnItemClickListener {

    private lateinit var mBinding: FragmentQuizTypeBinding

    private lateinit var quizAdapter:QuizAdapter

    private val viewModel: PlayerViewmodl by viewModels()

    @Inject
    lateinit var sharedPref:SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       mBinding = FragmentQuizTypeBinding.inflate(inflater,container,false)
       return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        val quiz_type = sharedPref.getInt(Constants.QUIZ_TYPE,0)
       // val quiz_category = sharedPref.getInt(Constants.QUIZ_CATEGORY,0)
        val quizType = Constants.provideQuizList()[quiz_type]
       // val quizCategory : ArrayList<Category> = quizType.quizCategory as ArrayList<Category>






        val new_player = sharedPref.getBoolean(Constants.NEW_PLAYER,false)

        if(new_player){
            viewModel.getAllPlayers().observe(viewLifecycleOwner) {
                if (it.isNotEmpty()) {
                    val listSize = it.size - 1
                    val player = it[listSize]
                    setUpRecyclerView()
                    quizAdapter.differ.submitList(player.quiz_type)
                }
            }
        }else{
            viewModel.getPlayer().observe(viewLifecycleOwner) {
                if (it != null) {
                    val player = it
                    setUpRecyclerView()
                    quizAdapter.differ.submitList(player.quiz_type)
                }
            }

        }

    }
    private fun setUpRecyclerView(){
        quizAdapter = QuizAdapter(this)
        mBinding.recyclerViewQuizType.apply {
            adapter = quizAdapter
            layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
            setHasFixedSize(true)
        }
    }

    @SuppressLint("CommitPrefEdits")
    override fun onItemClick(quizType: QuizType, position: Int) {

        sharedPref.edit().putInt(Constants.QUIZ_TYPE,position).apply()

        val bundle = Bundle().apply {
            putSerializable("quizType",quizType)
        }
        findNavController().navigate(R.id.action_quizTypeFragment_to_quizCategoryFragment,bundle)
    }
}