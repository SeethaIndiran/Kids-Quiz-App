package com.example.kidsquiz.fragments

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.kidsquiz.R
import com.example.kidsquiz.common.Constants
import com.example.kidsquiz.databinding.FragmentQuestionsBinding
import com.example.kidsquiz.models.Category
import com.example.kidsquiz.models.Question
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_questions.*
import javax.inject.Inject

@AndroidEntryPoint
class QuestionsFragment : Fragment(),View.OnClickListener {

    @Inject
    lateinit var sharedPref:SharedPreferences

    private  lateinit var binding:FragmentQuestionsBinding
    val args:QuestionsFragmentArgs by navArgs()
    private var mCurrentPosition:Int = 1
    private var mArrayList:List<Question> ? = null
    private var mSelectedOption:Int = 0
    private var mWrongAnswer:Int = 1
    private var mRightAnswer:Int = 1
    private var mCorrectAnswers:Int = 0
    private var questionNumber:Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
      binding = FragmentQuestionsBinding.inflate(inflater,container,false)
        return binding.root
    }

    @SuppressLint("CommitPrefEdits")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val quiz_type = sharedPref.getInt(Constants.QUIZ_TYPE,0)
        val quiz_category = sharedPref.getInt(Constants.QUIZ_CATEGORY,0)
        val quizType = Constants.provideQuizList()[quiz_type]
        var quizCategory : ArrayList<Category> = quizType.quizCategory as ArrayList<Category>
        val category:Category = quizCategory[quiz_category]
        val btnClicked = sharedPref.getBoolean(Constants.BTN_CLICKED,false)
        val ques_number = sharedPref.getInt(Constants.QUES_COUNT,0)
        val repeat = sharedPref.getBoolean(Constants.REPEAT_QUIZ,false)
        val next_btn_clicked = sharedPref.getBoolean(Constants.NEXT_QUIZ_BTN_CLICK,false)
       // val more_try_again = sharedPref.getInt(Constants.MORE_TRY_AGAIN,0)



         if(args.category != null){
           // val questions  = args.category
          mArrayList = category.question.slice(questionNumber until questionNumber+9)
        }else if(args.clickedBtn == "MoreQuestions"){
            questionNumber = 10
             val ques = questionNumber + ques_number
             sharedPref.edit().putInt(Constants.QUESTION_NUM,ques).apply()
             //sharedPref.edit().putInt(Constants.TRY_AGAIN,ques).apply()
            mArrayList = category.question.slice(ques until ques+9)
        }else if(args.clickedBtn == "TryAgain"){
             if(! btnClicked && !next_btn_clicked|| repeat ){
                 mArrayList  = category.question.slice(questionNumber until questionNumber+9)
             }
             else if(next_btn_clicked && !btnClicked){
                 mArrayList = category.question.slice(questionNumber until questionNumber+9)

              }else if(btnClicked && !next_btn_clicked ){
                //  questionNumber = 10
               //  val quest  =questionNumber + more_try_again
                // sharedPref.edit().putInt(Constants.TRY_AGAIN,quest).apply()
                      val quest = sharedPref.getInt(Constants.QUESTION_NUM,0)

                 mArrayList = category.question.slice(quest until quest + 9)



              }


             }else if(args.clickedBtn == "NextQuiz") {

                 if (quiz_category < quizCategory.size - 1) {

                 val quisCategory: Category = quizCategory[quiz_category+1]
                 sharedPref.edit().putInt(Constants.QUIZ_CATEGORY, quiz_category + 1).apply()
                 mArrayList = quisCategory.question.slice(questionNumber until questionNumber + 9)
             } else {
                 val quis_type = Constants.provideQuizList()[quiz_type + 1]
                 sharedPref.edit().putInt(Constants.QUIZ_TYPE, quiz_type + 1).apply()
                 sharedPref.edit().putInt(Constants.QUIZ_CATEGORY, 0).apply()

                 quizCategory = quis_type.quizCategory as ArrayList<Category>

                 val cateGory = quizCategory[0]



                 mArrayList = cateGory.question.slice(questionNumber until questionNumber + 9)


             }



         }


        setUpUi()


        binding.btnOptionOne.setOnClickListener(this)
        binding.btnOptionTwo.setOnClickListener(this)
        binding.btnOptionThree.setOnClickListener(this)
        binding.btnOptionFour.setOnClickListener(this)
        binding.btnNext.setOnClickListener(this)


    }
    @SuppressLint("SetTextI18n")
    private fun setUpUi(){
        val question = mArrayList?.get(mCurrentPosition-1)
        defaultOptionsView()

        if(mCurrentPosition == mArrayList?.size){
            btn_next.text = "FINISH"
        }else{
         //   btn_next.text = "SUBMIT"
        }

        binding.tvQuestion.text = question?.title
        if (question != null) {
            binding.ivQuestion.setImageResource(question.image)
        }
        binding.btnOptionOne.text = question?.optionOne
        binding.btnOptionTwo.text = question?.optionTwo
        binding.btnOptionThree.text = question?.optionThree
        binding.btnOptionFour.text = question?.optionFour
        binding.btnNext.text = "SUBMIT"
        binding.tvQuestionCount.text = "$mCurrentPosition" + "/" + "10"

         mSelectedOption = 0

    }


    @SuppressLint("SetTextI18n")
    override fun onClick(v: View?) {

        when(v?.id){
            R.id.btn_option_one ->{
                selectedOptionView(btn_option_one,1)
            }
            R.id.btn_option_two ->{
                selectedOptionView(btn_option_two,2)
            }
            R.id.btn_option_three ->{
                selectedOptionView(btn_option_three,3)
            }
            R.id.btn_option_four ->{
                selectedOptionView(btn_option_four,4)
            }
            R.id.btn_next ->{
                if(mSelectedOption == 0){
                   mCurrentPosition++
                    if(mCurrentPosition <= mArrayList?.size!!){
                        setUpUi()
                    }else{


                        val bundle = Bundle().apply {
                            putInt("score",mCorrectAnswers)
                        }
                        findNavController().navigate(R.id.action_questionsFragment_to_scoreFragment,bundle)
                    }



                }else{
                       val question = mArrayList!!.get(mCurrentPosition-1)
                    if(question.correctOption != mSelectedOption){
                        answerView(mSelectedOption, R.drawable.wrong_selected)
                        binding.tvQuestionWrong.text = mWrongAnswer++.toString()
                    }else{
                        mCorrectAnswers++
                        binding.tvQuestionRight.text = mRightAnswer++.toString()
                    }
                    answerView(question.correctOption,R.drawable.right_selected)
                    if(mCurrentPosition == mArrayList!!.size){
                        binding.btnNext.text = "FINISH"
                    }else{
                        binding.btnNext.text = "GO TO NEXT"
                    }
                    mSelectedOption = 0

                }
            }
        }
    }
private fun defaultOptionsView(){
    val options = ArrayList<TextView>()
    options.add(0,binding.btnOptionOne)
    options.add(1,binding.btnOptionTwo)
    options.add(2,binding.btnOptionThree)
    options.add(3,binding.btnOptionFour)

    for(option in options){
        option.setTextColor(Color.parseColor("#89fffd"))
        option.background = ContextCompat.getDrawable(requireActivity(),R.drawable.gradient_quiz_type)
    }
}
    private fun selectedOptionView(tv:TextView,selectedOption:Int){
        defaultOptionsView()
        mSelectedOption = selectedOption
        tv.setTextColor(Color.parseColor("#FFFFFF"))
        tv.background = ContextCompat.getDrawable(requireActivity(),R.drawable.selected_back)
    }

    private fun answerView(answer:Int,drawable:Int){
        when(answer){
            1->{
                btn_option_one.background = ContextCompat.getDrawable(requireActivity(),drawable)
            }
            2->{
                btn_option_two.background = ContextCompat.getDrawable(requireActivity(),drawable)
            }
            3->{
                btn_option_three.background = ContextCompat.getDrawable(requireActivity(),drawable)
            }
            4->{
                btn_option_four.background = ContextCompat.getDrawable(requireActivity(),drawable)
            }
        }
    }

}