package com.example.kidsquiz.common

import com.example.kidsquiz.R
import com.example.kidsquiz.models.Category
import com.example.kidsquiz.models.Question
import com.example.kidsquiz.models.QuizType

object Constants {

    const val PLAYER_DATABASE = "player_db"
    const val KEY_FIRST_TIME_TOGGLE = "KEY_FIRST_TIME_TOGGLE"
    const val SHARED_PREFERENCES_NAME = "sharedPref"
    const val ITEM_CLICKED = "item"
    const val QUIZ_TYPE= "quiz_type"
    const val QUIZ_CATEGORY = "quiz_category"
    const val QUESTION_NUM = "question_number"
    const val BTN_CLICKED = "btn_clicked"
    const val QUES_COUNT = "ques_count"
    const val REPEAT_QUIZ = "repeat_quiz"
    const val NEXT_QUIZ_BTN_CLICK = "next_quiz"
    const val TRY_AGAIN = "try_again"
    const val MORE_TRY_AGAIN = "more_try"
    const val NEW_PLAYER = "new_player"
    const val SCORE = "score"
    const val DELETE_PLAYER = "delete_player"
    const val ADD_PLAYER = "add_player"





    fun provideQuizList():ArrayList<QuizType>{
        val quizList = ArrayList<QuizType>()

        val quizOne = QuizType(1,"Birds and Animals",0, arrayListOf(Category("Amazing birds",0,
            arrayListOf
                (Question("Which bird does not make its own nest?", R.drawable.clear_sky,"Sparrow","Cuckoo","Parrot","Crow",2),
                 Question("Which bird can run faster than a horse and roar like alion?",R.drawable.clear_sky,"Duck",
               "Peacock","Ostrich","Puffin",3),
                 Question("Which bird cannot fly but swim and dive?",R.drawable.clear_sky,"Ostrich",
                "Duck","Penguin","Swan",3),
            Question("Which bird can fly backwards and sideways?",R.drawable.clear_sky,"Humming Bird",
                "Ostrich","Peacock ","Sparrow",1),
            Question("Which word can learn and speak upto 100 words?",R.drawable.clear_sky,"Flamingo",
                "Parrot","Cuckoo","Sparrow",2),
            Question("What is the maximum heartbeat of HummingBird per minute?",R.drawable.clear_sky,"72",
                "1200","400","1000",2),
            Question("Which living thing tastes using its feet?",R.drawable.clear_sky,"HoneyBee",
                "LadyBug","DragonFly","ButterFly",4),
            Question("Which bird does lay the world's largest egg?",R.drawable.clear_sky,"Ostrich",
                "Eagle","Peacock","Swan",1),
            Question("Which one is the smallest bird?",R.drawable.clear_sky,"Sparrow",
                "Cuckoo","LoveBirds","HummingBird",4),
            Question("Which bird does not have wings at all?",R.drawable.clear_sky,"Kiwi",
                "Cuckoo","Eagle","Owl",1),
                Question("The only mammal who can fly?",R.drawable.clear_sky,"Tiger",
                    "Cheetah","Bat","Monkey",3),
                Question
                    ("What is the name of the animal who smells water upto 3 miles away?",R.drawable.clear_sky,"Cat",
                    "Elephant","Dog","Deer",2),
                Question("An animal having four stomach inside its body",R.drawable.clear_sky,"Cow",
                    "Giraffe","Lion","Elephant",1),
                Question("The tallest living animal?",R.drawable.clear_sky,"Elephant",
                    "Giraffe","Horse","Cow",2),
                Question("The only animal that does sleep on its back",R.drawable.clear_sky,"Kangaroo",
                    "Human","Cat","Dog",2),
                Question("An animal who is dumb?",R.drawable.clear_sky,"Deer",
                    "Rhinoceros","Hippopotamus","Giraffe",4),
                Question("An animal who drinks upto 100 liters of water in a single time?",R.drawable.clear_sky,"Giraffe",
                    "Elephant","Camel","Lion",3),
                Question("An animal have longest life span?",R.drawable.clear_sky,"Tortoise",
                    "Hippopotamus","Rhinocerus","Zebra",1),
                Question("The animal keeps his baby in its pocket?",R.drawable.clear_sky,"Dog",
                    "Cat","Zebra","Kangaroo",4),
                Question("The living thing who can see both infrared and ultraviolet light",R.drawable.clear_sky,"Snake",
                    "GoldFish","Cat","Dog",2),
                Question("Which does not make its own nest?", R.drawable.clear_sky,"Sparrow","Cuckoo","Parrot","Crow",2),
                Question("Which can run faster than a horse and roar like alion?",R.drawable.clear_sky,"Duck",
                    "Peacock","Ostrich","Puffin",3),
                Question("Which cannot fly but swim and dive?",R.drawable.clear_sky,"Ostrich",
                    "Duck","Penguin","Swan",3),
                Question("Which can fly backwards and sideways?",R.drawable.clear_sky,"Humming Bird",
                    "Ostrich","Peacock ","Sparrow",1),
                Question("Which  can learn and speak upto 100 words?",R.drawable.clear_sky,"Flamingo",
                    "Parrot","Cuckoo","Sparrow",2),
                Question("What is the maximum heartbeat of HummingBird per minute?",R.drawable.clear_sky,"72",
                    "1200","400","1000",2),
                Question("Which living thing tastes using its feet?",R.drawable.clear_sky,"HoneyBee",
                    "LadyBug","DragonFly","ButterFly",4),
                Question("Which bird does lay the world's largest egg?",R.drawable.clear_sky,"Ostrich",
                    "Eagle","Peacock","Swan",1),
                Question("Which one is the smallest bird?",R.drawable.clear_sky,"Sparrow",
                    "Cuckoo","LoveBirds","HummingBird",4),
                Question("Which bird does not have wings at all?",R.drawable.clear_sky,"Kiwi",
                    "Cuckoo","Eagle","Owl",1),)),
            Category("Amazing animals",0,
            arrayListOf(
                Question("The only mammal who can fly?",R.drawable.clear_sky,"Tiger",
                    "Cheetah","Bat","Monkey",3),
            Question
            ("What is the name of the animal who smells water upto 3 miles away?",R.drawable.clear_sky,"Cat",
                "Elephant","Dog","Deer",2),
            Question("An animal having four stomach inside its body",R.drawable.clear_sky,"Cow",
                "Giraffe","Lion","Elephant",1),
            Question("The tallest living animal?",R.drawable.clear_sky,"Elephant",
                "Giraffe","Horse","Cow",2),
            Question("The only animal that does sleep on its back",R.drawable.clear_sky,"Kangaroo",
                "Human","Cat","Dog",2),
            Question("An animal who is dumb?",R.drawable.clear_sky,"Deer",
                "Rhinoceros","Hippopotamus","Giraffe",4),
            Question("An animal who drinks upto 100 liters of water in a single time?",R.drawable.clear_sky,"Giraffe",
                "Elephant","Camel","Lion",3),
            Question("An animal have longest life span?",R.drawable.clear_sky,"Tortoise",
                "Hippopotamus","Rhinocerus","Zebra",1),
            Question("The animal keeps his baby in its pocket?",R.drawable.clear_sky,"Dog",
                "Cat","Zebra","Kangaroo",4),
            Question("The living thing who can see both infrared and ultraviolet light",R.drawable.clear_sky,"Snake",
                "GoldFish","Cat","Dog",2),
                Question("Which does not make its own nest?", R.drawable.clear_sky,"Sparrow","Cuckoo","Parrot","Crow",2),
                Question("Which can run faster than a horse and roar like alion?",R.drawable.clear_sky,"Duck",
                    "Peacock","Ostrich","Puffin",3),
                Question("Which cannot fly but swim and dive?",R.drawable.clear_sky,"Ostrich",
                    "Duck","Penguin","Swan",3),
                Question("Which can fly backwards and sideways?",R.drawable.clear_sky,"Humming Bird",
                    "Ostrich","Peacock ","Sparrow",1),
                Question("Which  can learn and speak upto 100 words?",R.drawable.clear_sky,"Flamingo",
                    "Parrot","Cuckoo","Sparrow",2),
                Question("What is the maximum heartbeat of HummingBird per minute?",R.drawable.clear_sky,"72",
                    "1200","400","1000",2),
                Question("Which living thing tastes using its feet?",R.drawable.clear_sky,"HoneyBee",
                    "LadyBug","DragonFly","ButterFly",4),
                Question("Which bird does lay the world's largest egg?",R.drawable.clear_sky,"Ostrich",
                    "Eagle","Peacock","Swan",1),
                Question("Which one is the smallest bird?",R.drawable.clear_sky,"Sparrow",
                    "Cuckoo","LoveBirds","HummingBird",4),
                Question("Which bird does not have wings at all?",R.drawable.clear_sky,"Kiwi",
                    "Cuckoo","Eagle","Owl",1)))))

        quizList.add(quizOne)

        val quizTwo = QuizType(2,"Solar Systems",0, arrayListOf(Category("Planets",0, arrayListOf(
            Question("How many planets are there in our solor system?", R.drawable.clear_sky,"9",
                "10","11","7",1),
        Question("Which star is at the centre of our solar system?",R.drawable.clear_sky,"Earth","Sun","Moon","Venus",2),
            Question("How many planets are there in our solor system?", R.drawable.clear_sky,"9",
                "10","11","7",1),
            Question("Which star is at the centre of our solar system?",R.drawable.clear_sky,"Earth","Sun","Moon","Venus",2),
            Question("How many planets are there in our solor system?", R.drawable.clear_sky,"9",
                "10","11","7",1),
            Question("Which star is at the centre of our solar system?",R.drawable.clear_sky,"Earth","Sun","Moon","Venus",2),
            Question("How many planets are there in our solor system?", R.drawable.clear_sky,"9",
                "10","11","7",1),
            Question("Which star is at the centre of our solar system?",R.drawable.clear_sky,"Earth","Sun","Moon","Venus",2),
            Question("How many planets are there in our solor system?", R.drawable.clear_sky,"9",
                "10","11","7",1),
            Question("Which star is at the centre of our solar system?",R.drawable.clear_sky,"Earth","Sun","Moon","Venus",2),
            Question("Which  solar does not make its own nest?", R.drawable.clear_sky,"Sparrow","Cuckoo","Parrot","Crow",2),
            Question("Which can run faster than a horse and roar like alion?",R.drawable.clear_sky,"Duck",
                "Peacock","Ostrich","Puffin",3),
            Question("Which cannot fly but swim and dive?",R.drawable.clear_sky,"Ostrich",
                "Duck","Penguin","Swan",3),
            Question("Which can fly backwards and sideways?",R.drawable.clear_sky,"Humming Bird",
                "Ostrich","Peacock ","Sparrow",1),
            Question("Which  can learn and speak upto 100 words?",R.drawable.clear_sky,"Flamingo",
                "Parrot","Cuckoo","Sparrow",2),
            Question("What is the maximum heartbeat of HummingBird per minute?",R.drawable.clear_sky,"72",
                "1200","400","1000",2),
            Question("Which living thing tastes using its feet?",R.drawable.clear_sky,"HoneyBee",
                "LadyBug","DragonFly","ButterFly",4),
            Question("Which bird does lay the world's largest egg?",R.drawable.clear_sky,"Ostrich",
                "Eagle","Peacock","Swan",1),
            Question("Which one is the smallest bird?",R.drawable.clear_sky,"Sparrow",
                "Cuckoo","LoveBirds","HummingBird",4),
            Question("Which bird does not have wings at all?",R.drawable.clear_sky,"Kiwi",
                "Cuckoo","Eagle","Owl",1))),

            Category("Earth", 0,arrayListOf(
                Question("Which planet is third from the Sun?",R.drawable.clear_sky,"Mercury",
                    "Moon","Earth","Mars",3),
            Question("Which is the natural satellite of Earth?",R.drawable.clear_sky,"Mars",
                "Moon","Mercury","Jupiter",2),
                Question("How many planets are there in our solor system?", R.drawable.clear_sky,"9",
                    "10","11","7",1),
                Question("Which star is at the centre of our solar system?",R.drawable.clear_sky,"Earth","Sun","Moon","Venus",2),
                Question("How many planets are there in our solor system?", R.drawable.clear_sky,"9",
                    "10","11","7",1),
                Question("Which star is at the centre of our solar system?",R.drawable.clear_sky,"Earth","Sun","Moon","Venus",2),
                Question("How many planets are there in our solor system?", R.drawable.clear_sky,"9",
                    "10","11","7",1),
                Question("Which star is at the centre of our solar system?",R.drawable.clear_sky,"Earth","Sun","Moon","Venus",2),
                Question("How many planets are there in our solor system?", R.drawable.clear_sky,"9",
                    "10","11","7",1),
                Question("Which star is at the centre of our solar system?",R.drawable.clear_sky,"Earth","Sun","Moon","Venus",2),
                Question("Which earth does not make its own nest?", R.drawable.clear_sky,"Sparrow","Cuckoo","Parrot","Crow",2),
                Question("Which can run faster than a horse and roar like alion?",R.drawable.clear_sky,"Duck",
                    "Peacock","Ostrich","Puffin",3),
                Question("Which cannot fly but swim and dive?",R.drawable.clear_sky,"Ostrich",
                    "Duck","Penguin","Swan",3),
                Question("Which can fly backwards and sideways?",R.drawable.clear_sky,"Humming Bird",
                    "Ostrich","Peacock ","Sparrow",1),
                Question("Which  can learn and speak upto 100 words?",R.drawable.clear_sky,"Flamingo",
                    "Parrot","Cuckoo","Sparrow",2),
                Question("What is the maximum heartbeat of HummingBird per minute?",R.drawable.clear_sky,"72",
                    "1200","400","1000",2),
                Question("Which living thing tastes using its feet?",R.drawable.clear_sky,"HoneyBee",
                    "LadyBug","DragonFly","ButterFly",4),
                Question("Which bird does lay the world's largest egg?",R.drawable.clear_sky,"Ostrich",
                    "Eagle","Peacock","Swan",1),
                Question("Which one is the smallest bird?",R.drawable.clear_sky,"Sparrow",
                    "Cuckoo","LoveBirds","HummingBird",4),
                Question("Which bird does not have wings at all?",R.drawable.clear_sky,"Kiwi",
                    "Cuckoo","Eagle","Owl",1)))
        ))

        quizList.add(quizTwo)


        return quizList
    }

}