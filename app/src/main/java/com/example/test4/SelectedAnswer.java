package com.example.test4;

class SelectedAnswer {
    int answerSlotIndex;        //Which "____" this answer is using
    int answerPositionIndex;    //What is the position of the answer within the bigger string
    String word;

    SelectedAnswer()
    {}

    SelectedAnswer (int slot, int index, String word)
    {
        answerSlotIndex = slot;
        answerPositionIndex = index;
        this.word = word;
    }


}
