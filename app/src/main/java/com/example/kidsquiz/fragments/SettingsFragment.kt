
package com.example.kidsquiz.fragments

import android.app.AlertDialog
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.kidsquiz.R
import com.example.kidsquiz.common.Constants
import com.example.kidsquiz.databinding.FragmentSettingsBinding
import com.example.kidsquiz.models.Category
import com.example.kidsquiz.models.Player
import com.example.kidsquiz.models.QuizType
import com.example.kidsquiz.viewmodels.PlayerViewmodl
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment : Fragment(),View.OnClickListener {


    private lateinit var binding:FragmentSettingsBinding

    @Inject
    lateinit var sharedPref:SharedPreferences

    private val viewModel:PlayerViewmodl by viewModels()

    private var player : Player? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         player = arguments?.getSerializable("player") as Player?




       binding.addPlayer.setOnClickListener(this)
        binding.deletePlayer.setOnClickListener(this)
        binding.resetScore.setOnClickListener(this)
        binding.emailUs.setOnClickListener(this)
        binding.shareApp.setOnClickListener(this)



    }

    override fun onClick(v: View?) {
       when(v?.id){
              R.id.add_player -> {
                  sharedPref.edit().putBoolean(Constants.KEY_FIRST_TIME_TOGGLE,true).apply()

                  val bundle = Bundle().apply {
                      putString("add_player","Add Player")
                  }
                  findNavController().navigate(R.id.action_settingsFragment_to_provideDetailsFragment,bundle)
              }
           R.id.delete_player ->{

              deleteSinglePlayer()
           }
           R.id.reset_score ->{
           updateSinglePlayer()
           }
           R.id.email_us ->{
                emailUs()
           }
           R.id.share_app ->{
            shareApp()
           }
       }
    }

    private fun navigatingToProvideDetailsFragment(){
        findNavController().navigate(SettingsFragmentDirections.actionSettingsFragmentToProvideDetailsFragment())
    }


     private fun deletePlayer(){

         val new_player = sharedPref.getBoolean(Constants.NEW_PLAYER,false)
         val delete_player = sharedPref.getBoolean(Constants.DELETE_PLAYER,false)
        // var list = ArrayList<Player>()

         if(new_player && !delete_player){
             viewModel.getAllPlayers().observe(viewLifecycleOwner) {
                 if (it.isNotEmpty()) {
                     val listSize = it.size
                     val player = it[listSize - 1]



                     viewModel.deletePlayer(player)

                     if (listSize - 1 == 0) {

                         sharedPref.edit().putBoolean(Constants.KEY_FIRST_TIME_TOGGLE, true).apply()

                         findNavController().navigate(R.id.action_settingsFragment_to_provideDetailsFragment)
                     } else {
                         findNavController().navigate(R.id.action_settingsFragment_to_multiPlayerFragment)
                     }

                 }


             }

             sharedPref.edit().putBoolean(Constants.DELETE_PLAYER,true).apply()
             sharedPref.edit().putBoolean(Constants.NEW_PLAYER,false).apply()

         }else if(!delete_player ){
             viewModel.getPlayer().observe(viewLifecycleOwner) {
                 if (it != null) {
                     val player = it
                     viewModel.deletePlayer(player)



                     viewModel.getAllPlayers().observe(viewLifecycleOwner) {
                         val listSize = it.size
                         if (listSize == 0) {
                             // navigatingToProvideDetailsFragment()
                             sharedPref.edit().putBoolean(Constants.KEY_FIRST_TIME_TOGGLE, true)
                                 .apply()

                             findNavController().navigate(R.id.action_settingsFragment_to_provideDetailsFragment)
                         } else {
                             findNavController().navigate(R.id.action_settingsFragment_to_multiPlayerFragment)
                         }
                     }
                 }


             }
             sharedPref.edit().putBoolean(Constants.DELETE_PLAYER,true).apply()
             sharedPref.edit().putBoolean(Constants.NEW_PLAYER,false).apply()


         }else if(delete_player && !new_player){
             viewModel.getAllPlayers().observe(viewLifecycleOwner) {
                 if (it.isNotEmpty()) {
                     val player = it[0]
                     viewModel.deletePlayer(player)

                     if (it.size - 1 == 0) {
                         // navigatingToProvideDetailsFragment()    // navigatingToProvideDetailsFragment()
                         sharedPref.edit().putBoolean(Constants.KEY_FIRST_TIME_TOGGLE, true).apply()

                         findNavController().navigate(R.id.action_settingsFragment_to_provideDetailsFragment)

                     } else {
                         findNavController().navigate(R.id.action_settingsFragment_to_multiPlayerFragment)
                     }

                 }

             }


             sharedPref.edit().putBoolean(Constants.DELETE_PLAYER,true).apply()
             sharedPref.edit().putBoolean(Constants.NEW_PLAYER,false).apply()

}


     }




    private fun updatePlayer(){
        val new_player = sharedPref.getBoolean(Constants.NEW_PLAYER,false)
        val delete_player = sharedPref.getBoolean(Constants.DELETE_PLAYER,false)


        if(new_player && !delete_player){
            viewModel.getAllPlayers().observe(viewLifecycleOwner) {
                if (it.isNotEmpty()) {
                    val listSize = it.size
                    val player = it[listSize - 1]

                    player.totalStarCount = 0
                    val quizList: ArrayList<QuizType> = player.quiz_type as ArrayList<QuizType>
                    for (i in quizList) {
                        i.quiz_type_score = 0
                        val cat_list: ArrayList<Category> = i.quizCategory as ArrayList<Category>
                        for (k in cat_list) {
                            k.quiz_category_score = 0
                        }
                    }

                    viewModel.updatePlayer(player)

                }
            }
        }else if(!delete_player){
            viewModel.getPlayer().observe(viewLifecycleOwner) {
                if (it != null) {
                    val player = it
                    player.totalStarCount = 0
                    val quizList: ArrayList<QuizType> = player.quiz_type as ArrayList<QuizType>
                    for (i in quizList) {
                        i.quiz_type_score = 0
                        val cat_list: ArrayList<Category> = i.quizCategory as ArrayList<Category>
                        for (k in cat_list) {
                            k.quiz_category_score = 0
                        }
                    }
                    viewModel.updatePlayer(player)


                }
            }

        }else if(delete_player && !new_player){
            viewModel.getAllPlayers().observe(viewLifecycleOwner) { it ->
                if (it.isNotEmpty()) {
                    val player = it[0]
                    player.totalStarCount = 0
                    val quizList: ArrayList<QuizType> = player.quiz_type as ArrayList<QuizType>
                    for (i in quizList) {
                        i.quiz_type_score = 0
                        val cat_list: ArrayList<Category> = i.quizCategory as ArrayList<Category>
                        for (k in cat_list) {
                            k.quiz_category_score = 0
                        }
                    }
                    viewModel.updatePlayer(player)


                }
            }
        }
    }

   private fun deleteSinglePlayer(){
        val builder = AlertDialog.Builder(requireActivity())
        builder.setTitle(resources.getString(R.string.delete_single_player))
        builder.setMessage(resources.getString(R.string.title_delete_player))
        builder.setIcon(android.R.drawable.ic_dialog_alert)
        builder.setPositiveButton(resources.getString(R.string.yes)){
                dialogInterface,_ ->
            deletePlayer()
            dialogInterface.dismiss()
        }
        builder.setNegativeButton(resources.getString(R.string.no)){
                dialogInterface,_ ->
            dialogInterface.dismiss()
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    private fun updateSinglePlayer(){
        val builder = AlertDialog.Builder(requireActivity())
        builder.setTitle(resources.getString(R.string.update_single_player))
        builder.setMessage(resources.getString(R.string.title_update_player))
        builder.setIcon(android.R.drawable.ic_dialog_alert)
        builder.setPositiveButton(resources.getString(R.string.yes)){
                dialogInterface,_ ->
        updatePlayer()
            dialogInterface.dismiss()
        }
        builder.setNegativeButton(resources.getString(R.string.no)){
                dialogInterface,_ ->
            dialogInterface.dismiss()
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }
    private fun emailUs(){
        val emailIntent = Intent(Intent.ACTION_SENDTO,
        Uri.fromParts("mailto","seethaindhiran@gmail.com",null))
        startActivity(Intent.createChooser(emailIntent,"Send email..."))
    }

    private fun shareApp(){
        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_SUBJECT,"Check out this application")
        intent.putExtra(Intent.EXTRA_TEXT,"Your application link here..")
        startActivity(Intent.createChooser(intent,"Share via"))
    }

    }


