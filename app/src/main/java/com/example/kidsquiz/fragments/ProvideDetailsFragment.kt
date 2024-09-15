package com.example.kidsquiz.fragments

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.kidsquiz.R
import com.example.kidsquiz.common.Constants
import com.example.kidsquiz.databinding.FragmentProvideDetailsBinding
import com.example.kidsquiz.models.Category
import com.example.kidsquiz.models.Player
import com.example.kidsquiz.models.Question
import com.example.kidsquiz.models.QuizType
import com.example.kidsquiz.viewmodels.PlayerViewmodl
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProvideDetailsFragment : Fragment() {

    private lateinit var binding:FragmentProvideDetailsBinding

    private val viewModel:PlayerViewmodl by viewModels()



    @Inject
    lateinit var sharedPref: SharedPreferences

    @set:Inject
    var isFirstAppOpen = true

    val args:ProvideDetailsFragmentArgs by navArgs()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentProvideDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



       val firstOpen =  sharedPref.getBoolean(Constants.KEY_FIRST_TIME_TOGGLE,true)
       if(!firstOpen ){
           findNavController().navigate(R.id.action_provideDetailsFragment_to_startFragment)
       }





        binding.addBtn.setOnClickListener {

             sharedPref.edit().putBoolean(Constants.NEW_PLAYER,true).apply()
            sharedPref.edit().putBoolean(Constants.DELETE_PLAYER,false).apply()
            insertPlayer()

        }

if(args.addPlayer != null){
    sharedPref.edit().putBoolean(Constants.KEY_FIRST_TIME_TOGGLE,false).apply()
}



    }

  @SuppressLint("SuspiciousIndentation")
  private  fun insertPlayer(){

        val player_name = binding.etName.text.toString()
      val email = binding.etEmail.text.toString()
        val player_score  = 0
      val questionsList = ArrayList<Question>()
      val quiz_type = ArrayList<QuizType>()
      val one = QuizType(1,"Birds and Animals",0,arrayListOf(
          Category("Amazing Birds",0,
              questionsList),
      Category("Amazing Animals",0,questionsList)))
      quiz_type.add(one)
      val two = QuizType(2,"Solars and Systems",0,arrayListOf(
              Category("SolarSystem and Planets",0,
                  questionsList),
              Category("Earth and Planets",0,questionsList)))
      quiz_type.add(two)


        if(player_name.isEmpty() || email.isEmpty()){
            Snackbar.make(requireView(),"Please fill all the fields", Snackbar.LENGTH_LONG).show()
        }else{
            val player = Player(player_name,player_score,quiz_type,false,0)

            viewModel.insertPlayer(player)

            sharedPref.edit().putBoolean(Constants.KEY_FIRST_TIME_TOGGLE,false)
                .putBoolean(Constants.ITEM_CLICKED,false)
                .apply()

            val bundle = Bundle().apply {
                putString("firstTimeUser","FirstTimeUser")
            }

            findNavController().navigate(R.id.action_provideDetailsFragment_to_startFragment,bundle)

        }



    }

    override fun onDestroy() {
        super.onDestroy()
        if(args.addPlayer != null){
            sharedPref.edit().putBoolean(Constants.KEY_FIRST_TIME_TOGGLE,false).apply()
        }
    }


}