package com.example.test4;

class SelectedAnswer {
    String word;
    int answerPositionIndex;    //Answers position in string
    int IDForCheckingAnswer = -1;    //The id used when checking if the answer is correct
    SelectedAnswer()
    {}

    SelectedAnswer (String word)
    {
        this.word = word;
    }


}
