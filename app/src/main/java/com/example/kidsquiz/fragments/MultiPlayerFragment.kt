
package com.example.kidsquiz.fragments

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kidsquiz.R
import com.example.kidsquiz.adapters.PlayerAdapter
import com.example.kidsquiz.common.Constants
import com.example.kidsquiz.databinding.FragmentMultiPlayerBinding
import com.example.kidsquiz.models.Player
import com.example.kidsquiz.viewmodels.PlayerViewmodl
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MultiPlayerFragment : Fragment() {

    @Inject
    lateinit var sharedPref: SharedPreferences
    private lateinit var binding:FragmentMultiPlayerBinding
    private lateinit var playerAdapter:PlayerAdapter
    private val viewModel:PlayerViewmodl by viewModels()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMultiPlayerBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()




        viewModel.getAllPlayers().observe(viewLifecycleOwner) {
            if (it != null) {
                playerAdapter.differ.submitList(it)
            } else {
                findNavController().navigate(R.id.action_multiPlayerFragment_to_provideDetailsFragment)
            }
        }
        binding.addPlayerBtn.setOnClickListener {

           sharedPref.edit().putBoolean(Constants.KEY_FIRST_TIME_TOGGLE,true).apply()


            val bundle = Bundle().apply {
                putString("add_player","Add Player")
            }

            findNavController().navigate(R.id.action_multiPlayerFragment_to_provideDetailsFragment,bundle)


        }


    }
    private fun setUpRecyclerView(){
        playerAdapter = PlayerAdapter(this@MultiPlayerFragment)
        binding.recyclerViewPlayer.apply {
            adapter = playerAdapter
            layoutManager = LinearLayoutManager(activity)

        }
    }


    fun navigateToStartFragment(player: Player){
        sharedPref.edit().putInt(Constants.QUESTION_NUM,0).apply()
        sharedPref.edit().putBoolean(Constants.REPEAT_QUIZ,false).apply()
        sharedPref.edit().putInt(Constants.SCORE,0).apply()


      findNavController().navigate(MultiPlayerFragmentDirections.actionMultiPlayerFragmentToStartFragment(player))
    }
@SuppressLint("CommitPrefEdits")
fun itemClicked(currentList: MutableList<Player>, player: Player) {

    sharedPref.edit().putBoolean(Constants.ITEM_CLICKED,true)
    sharedPref.edit().putBoolean(Constants.NEW_PLAYER,false).apply()
    sharedPref.edit().putBoolean(Constants.DELETE_PLAYER,false).apply()

    for(i in currentList){
        i.btnClicked = false
        viewModel.updatePlayer(i)
    }
    player.btnClicked = true
    viewModel.updatePlayer(player)

}

}