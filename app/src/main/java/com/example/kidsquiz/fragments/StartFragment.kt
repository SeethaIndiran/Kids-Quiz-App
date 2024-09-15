package com.example.kidsquiz.fragments

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.kidsquiz.R
import com.example.kidsquiz.common.Constants
import com.example.kidsquiz.databinding.FragmentStartBinding
import com.example.kidsquiz.viewmodels.PlayerViewmodl
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class StartFragment : Fragment() {

    private lateinit var mBinding: FragmentStartBinding
     @Inject
     lateinit var sharedPref:SharedPreferences

    val args:StartFragmentArgs by navArgs()
    private val viewModel:PlayerViewmodl by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        mBinding = FragmentStartBinding.inflate(inflater,container,false)
         return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val new_player = sharedPref.getBoolean(Constants.NEW_PLAYER,false)
        val delete_player = sharedPref.getBoolean(Constants.DELETE_PLAYER,false)

        if(new_player && !delete_player ){
            viewModel.getAllPlayers().observe(viewLifecycleOwner) {
                if (it.isNotEmpty()) {
                    val listSize = it.size - 1
                    val player = it[listSize]
                    mBinding.title.text = player.name
                    mBinding.starCount.text = player.totalStarCount.toString()
                }
            }
        }else if(!new_player && !delete_player ){
            viewModel.getPlayer().observe(viewLifecycleOwner) {
                if (it != null) {
                    val player = it
                    mBinding.title.text = player.name
                    mBinding.starCount.text = player.totalStarCount.toString()
                }
            }
        }else if(delete_player && !new_player){
            viewModel.getAllPlayers().observe(viewLifecycleOwner) {

                if (it.isNotEmpty()) {
                    val player = it[0]
                    mBinding.title.text = player.name
                    mBinding.starCount.text = player.totalStarCount.toString()
                }
            }
        }








        mBinding.startButton.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_quizTypeFragment)
        }
    }


}