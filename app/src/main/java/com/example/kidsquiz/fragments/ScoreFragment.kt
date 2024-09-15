
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
import androidx.navigation.fragment.navArgs
import com.example.kidsquiz.R
import com.example.kidsquiz.common.Constants
import com.example.kidsquiz.databinding.FragmentScoreBinding
import com.example.kidsquiz.models.Player
import com.example.kidsquiz.models.Category
import com.example.kidsquiz.models.QuizType
import com.example.kidsquiz.viewmodels.PlayerViewmodl
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ScoreFragment : Fragment() {

    @Inject
    lateinit var sharedPref: SharedPreferences
    private lateinit var binding:FragmentScoreBinding


    private val viewModel:PlayerViewmodl by viewModels()

    val args:ScoreFragmentArgs by navArgs()

     var current_player: Player? = null






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentScoreBinding.inflate(inflater,container,false)
        return binding.root


    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       // sharedPref.edit().putInt(Constants.QUESTION_NUM,0).apply()

        val quiz_type = sharedPref.getInt(Constants.QUIZ_TYPE,0)
        val quiz_category = sharedPref.getInt(Constants.QUIZ_CATEGORY,0)
        val quizType = Constants.provideQuizList()[quiz_type]
        val quizCategory : ArrayList<Category> = quizType.quizCategory as ArrayList<Category>
        val category: Category = quizCategory[quiz_category]
        val num = sharedPref.getInt(Constants.QUESTION_NUM,0)
        val try_again = sharedPref.getInt(Constants.TRY_AGAIN,0)
       val player_score =  sharedPref.getInt(Constants.SCORE,0)


        val score  = args.score
        binding.score.text = score.toString()
        binding.scoreCard.text = "$score" + "/" + "10"

        val two_score  = score + player_score

        val new_player = sharedPref.getBoolean(Constants.NEW_PLAYER,false)
        val delete_player = sharedPref.getBoolean(Constants.DELETE_PLAYER,false)

        if(new_player && !delete_player){

            viewModel.getAllPlayers().observe(viewLifecycleOwner) {
                if (it.isNotEmpty()) {
                    val listSize = it.size - 1
                    val player = it[listSize]
                    current_player = player

                    // current_player!!.quiz_type[quiz_type].name = Constants.provideQuizList()[quiz_type].name
                    current_player!!.quiz_type[quiz_type].quizCategory[quiz_category].quiz_category_score =
                        two_score

                    val list: ArrayList<Category> =
                        current_player!!.quiz_type[quiz_type].quizCategory as ArrayList<Category>
                    var scores = 0

                    scores = list.sumOf {
                        it.quiz_category_score
                    }
                    current_player!!.quiz_type[quiz_type].quiz_type_score = scores

                    val quizList: ArrayList<QuizType> =
                        current_player!!.quiz_type as ArrayList<QuizType>
                    var t_scores = 0

                    t_scores = quizList.sumOf {
                        it.quiz_type_score
                    }
                    current_player!!.totalStarCount = t_scores
                    viewModel.updatePlayer(current_player!!)
                }
            }
        }else if(!new_player && !delete_player) {
        viewModel.getPlayer().observe(viewLifecycleOwner) {

            if (it != null) {
                current_player = it


                // current_player!!.quiz_type[quiz_type].quiz_type_score = score
                current_player!!.quiz_type[quiz_type].quizCategory[quiz_category].quiz_category_score =
                    two_score


                val list: ArrayList<Category> =
                    current_player!!.quiz_type[quiz_type].quizCategory as ArrayList<Category>
                var scores = 0

                scores = list.sumOf {
                    it.quiz_category_score
                }
                current_player!!.quiz_type[quiz_type].quiz_type_score = scores

                val quizList: ArrayList<QuizType> =
                    current_player!!.quiz_type as ArrayList<QuizType>
                var t_scores = 0

                t_scores = quizList.sumOf {
                    it.quiz_type_score
                }
                current_player!!.totalStarCount = t_scores
                viewModel.updatePlayer(current_player!!)

            }
        }
        }else if(delete_player){
        viewModel.getAllPlayers().observe(viewLifecycleOwner) {
            val player = it[0]
            current_player = player
            current_player!!.quiz_type[quiz_type].quizCategory[quiz_category].quiz_category_score =
                two_score


            val list: ArrayList<Category> =
                current_player!!.quiz_type[quiz_type].quizCategory as ArrayList<Category>
            var scores = 0

            scores = list.sumOf {
                it.quiz_category_score
            }
            current_player!!.quiz_type[quiz_type].quiz_type_score = scores

            val quizList: ArrayList<QuizType> = current_player!!.quiz_type as ArrayList<QuizType>
            var t_scores = 0

            t_scores = quizList.sumOf {
                it.quiz_type_score
            }
            current_player!!.totalStarCount = t_scores
            viewModel.updatePlayer(current_player!!)
        }
        }


        if(num < category.question.size-10 ){
            binding.moreQuestionBtn.text = "More Questions"
        }else{
            binding.moreQuestionBtn.text  = "Repeat Quiz"












            }



        if(quiz_type== Constants.provideQuizList().size-1 && quiz_category==quizCategory.size-1){
            binding.nextQuizBtn.text= "Go back to QuizType"
        }else{
            binding.nextQuizBtn.text= "Next Quiz"
        }





             binding.tryAgainBtn.setOnClickListener {

                 //sharedPref.edit().putInt(Constants.TRY_AGAIN,quiz_finish).apply()


                 val bundle = Bundle().apply {
                     putString("clickedBtn","TryAgain")

                 }
                 findNavController().navigate(R.id.action_scoreFragment_to_questionsFragment,bundle)
             }

        binding.moreQuestionBtn.setOnClickListener {

             sharedPref.edit().putInt(Constants.QUES_COUNT,num).apply()
            sharedPref.edit().putBoolean(Constants.BTN_CLICKED,true).apply()

            sharedPref.edit().putBoolean(Constants.NEXT_QUIZ_BTN_CLICK,false).apply()
            sharedPref.edit().putInt(Constants.SCORE,two_score).apply()




            if(num <category.question.size-10  ){
                sharedPref.edit().putInt(Constants.MORE_TRY_AGAIN,try_again).apply()
                sharedPref.edit().putBoolean(Constants.REPEAT_QUIZ,false).apply()


                val bundle = Bundle().apply {
                    putString("clickedBtn","MoreQuestions")
                }

                findNavController().navigate(R.id.action_scoreFragment_to_questionsFragment,bundle)


            }else{
                sharedPref.edit().putInt(Constants.TRY_AGAIN,0).apply()
               // sharedPref.edit().putBoolean(Constants.BTN_CLICKED,false).apply()
                sharedPref.edit().putBoolean(Constants.REPEAT_QUIZ,true).apply()
                sharedPref.edit().putInt(Constants.QUESTION_NUM,0).apply()
                sharedPref.edit().putInt(Constants.SCORE,0).apply()



                findNavController().navigate(R.id.action_scoreFragment_to_quizCategoryFragment)

            }




        }

        binding.nextQuizBtn.setOnClickListener {

            sharedPref.edit().putBoolean(Constants.NEXT_QUIZ_BTN_CLICK,true).apply()
            sharedPref.edit().putBoolean(Constants.BTN_CLICKED,false).apply()
           // sharedPref.edit().putInt(Constants.TRY_AGAIN,0).apply()
            if(quiz_type== Constants.provideQuizList().size-1 && quiz_category==quizCategory.size-1){

                sharedPref.edit().putBoolean(Constants.REPEAT_QUIZ,true).apply()
                sharedPref.edit().putInt(Constants.QUESTION_NUM,0).apply()
                sharedPref.edit().putInt(Constants.SCORE,0).apply()

                findNavController().navigate(R.id.action_scoreFragment_to_quizTypeFragment)
            }else{
                sharedPref.edit().putInt(Constants.QUESTION_NUM,0).apply()
                sharedPref.edit().putInt(Constants.SCORE,0).apply()
                val bundle = Bundle().apply {
                    putString("clickedBtn","NextQuiz")}

                findNavController().navigate(R.id.action_scoreFragment_to_questionsFragment,bundle)
                }



            }



}}








