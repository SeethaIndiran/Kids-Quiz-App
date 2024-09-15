
package com.example.kidsquiz.fragments

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
import com.example.kidsquiz.adapters.SinglePlayerAdapter
import com.example.kidsquiz.common.Constants
import com.example.kidsquiz.databinding.FragmentSinglePlayerBinding
import com.example.kidsquiz.models.QuizType
import com.example.kidsquiz.viewmodels.PlayerViewmodl
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SinglePlayerFragment : Fragment(),SinglePlayerAdapter.setOnItemClickListener {


    private lateinit var binding:FragmentSinglePlayerBinding

    private lateinit var quizAdapter:SinglePlayerAdapter

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
        binding = FragmentSinglePlayerBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        val new_player = sharedPref.getBoolean(Constants.NEW_PLAYER,false)
        val delete_player = sharedPref.getBoolean(Constants.DELETE_PLAYER,false)

        if(new_player && !delete_player){
            viewModel.getAllPlayers().observe(viewLifecycleOwner) {
                if (it.isNotEmpty()) {
                    val listSize = it.size - 1
                    val player = it[listSize]
                    binding.helloPlayer.text = player.name
                    binding.playerScore.text = player.totalStarCount.toString()
                    setUpRecyclerView()
                    quizAdapter.differ.submitList(player.quiz_type)
                }
            }
        }else if(!new_player && !delete_player){
            viewModel.getPlayer().observe(viewLifecycleOwner) {
                if (it != null) {
                    val player = it
                    binding.helloPlayer.text = player.name
                    binding.playerScore.text = player.totalStarCount.toString()
                    setUpRecyclerView()
                    quizAdapter.differ.submitList(player.quiz_type)
                }
            }

        }else if(delete_player && !new_player){


viewModel.getAllPlayers().observe(viewLifecycleOwner) {
    val player = it[0]
    binding.helloPlayer.text = player.name
    binding.playerScore.text = player.totalStarCount.toString()
    setUpRecyclerView()
    quizAdapter.differ.submitList(player.quiz_type)
}


        }}




    private fun setUpRecyclerView(){
        quizAdapter = SinglePlayerAdapter(this)
        binding.recyclerViewPlayerProfile.apply {
            adapter = quizAdapter
            layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
            setHasFixedSize(true)
        }
    }

    override fun onItemClick(quizType: QuizType, position: Int) {
        sharedPref.edit().putInt(Constants.QUIZ_TYPE,position).apply()
        sharedPref.edit().putInt(Constants.QUESTION_NUM,0).apply()
        sharedPref.edit().putBoolean(Constants.REPEAT_QUIZ,false).apply()
        sharedPref.edit().putInt(Constants.SCORE,0).apply()

        val bundle = Bundle().apply {
            putSerializable("singlePlayerQuizType",quizType)
        }
        findNavController().navigate(R.id.action_singlePlayerFragment_to_quizCategoryFragment,bundle)
    }
    }
