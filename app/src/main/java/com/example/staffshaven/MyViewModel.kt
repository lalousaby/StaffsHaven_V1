package com.example.staffshaven

import android.graphics.Bitmap
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {

    private val _checkBoxStates = mutableMapOf<String, Boolean>()
    val checkBoxStates: Map<String, Boolean> = _checkBoxStates
    val _selectedOptions = mutableListOf<String>()
    val selectedOptions: List<String> = _selectedOptions

    val _selectedOptionsJournaling = mutableListOf<String>()
    val selectedOptionsJournaling: List<String> = _selectedOptionsJournaling
    val _selectedOptionsJournalingSlide = mutableListOf<String>()
    val selectedOptionsJournalingSlide: List<String> = _selectedOptionsJournalingSlide
    val _selectedOptionsFoodSlide = mutableListOf<String>()
    val selectedOptionsFoodSlide: List<String> = _selectedOptionsFoodSlide

    val _breakfastImage = MutableLiveData<Bitmap?>()
    val breakfastImage: LiveData<Bitmap?> = _breakfastImage

    val _lunchImage = MutableLiveData<Bitmap?>()
    val lunchImage: LiveData<Bitmap?> = _lunchImage

    val _dinnerImage = MutableLiveData<Bitmap?>()
    val dinnerImage: LiveData<Bitmap?> = _dinnerImage

    val _breakfastImageClick = MutableLiveData<Bitmap?>()
    val breakfastImageClick: LiveData<Bitmap?> = _breakfastImageClick
    val _lunchImageClick = MutableLiveData<Bitmap?>()
    val lunchImageClick: LiveData<Bitmap?> = _lunchImageClick
    val _dinnerImageClick = MutableLiveData<Bitmap?>()
    val dinnerImageClick: LiveData<Bitmap?> = _dinnerImageClick

    //for swipe journaling version
    val _dayTextJournal = MutableLiveData<String>("")
    val dayTextJournal : LiveData<String> = _dayTextJournal

    // for click journaling version
    val _dayTextJournalClick = MutableLiveData<String>("")
    val dayTextJournalClick : LiveData<String> = _dayTextJournalClick

    // for slide journaling version
    val _dayTextJournalSlide = MutableLiveData<String>("")
    val dayTextJournalSlide : LiveData<String> = _dayTextJournalSlide

    val _breakfastTextFoodSlide = MutableLiveData<String>("")
    val breakfastTextFoodSlide : LiveData<String> = _breakfastTextFoodSlide

    val _lunchTextFoodSlide = MutableLiveData<String>("")
    val lunchTextFoodSlide : LiveData<String> = _lunchTextFoodSlide

    val _dinnerTextFoodSlide = MutableLiveData<String>("")
    val dinnerTextFoodSlide : LiveData<String> = _dinnerTextFoodSlide

    val _breakfastTextFoodClick = MutableLiveData<String>("")
    val breakfastTextFoodClick : LiveData<String> = _breakfastTextFoodClick
    val _lunchTextFoodClick = MutableLiveData<String>("")
    val lunchTextFoodClick : LiveData<String> = _lunchTextFoodClick
    val _dinnerTextFoodClick = MutableLiveData<String>("")
    val dinnerTextFoodClick : LiveData<String> = _dinnerTextFoodClick

    // for swipe journaling version
    val _positiveJournal = MutableLiveData<String>("")
    val positiveJournal : LiveData<String> = _positiveJournal


    // for click journaling version
    val _positive1JournalClick = MutableLiveData<String>("")
    val positive1JournalClick : LiveData<String> = _positive1JournalClick
    val _positive2JournalClick = MutableLiveData<String>("")
    val positive2JournalClick : LiveData<String> = _positive2JournalClick
    val _positive3JournalClick = MutableLiveData<String>("")
    val positive3JournalClick : LiveData<String> = _positive3JournalClick

    // for slide journaling version
    val _positive1JournalSlide = MutableLiveData<String>("")
    val positive1JournalSlide : LiveData<String> = _positive1JournalSlide
    val _positive2JournalSlide = MutableLiveData<String>("")
    val positive2JournalSlide : LiveData<String> = _positive2JournalSlide
    val _positive3JournalSlide = MutableLiveData<String>("")
    val positive3JournalSlide : LiveData<String> = _positive3JournalSlide

    //for swipe journaling version
    var selectedStudyBtnSwipe: Int? = null
    private val _selectedStudyBtnIdSwipe = MutableLiveData<Int>()
    val selectedStudyBtnIdSwipe:LiveData<Int> = _selectedStudyBtnIdSwipe
    val isStudyYesSelectedSwipe: Boolean
        get() = selectedStudyBtnIdSwipe.value == R.id.frameLayoutAnimationYes

    val isStudyNoSelectedSwipe: Boolean
        get() = selectedStudyBtnIdSwipe.value == R.id.frameLayoutAnimationNo

    // for click journaling version
    var selectedStudyBtn: Int? = null
    private val _selectedStudyBtnId = MutableLiveData<Int>()
    val selectedStudyBtnId:LiveData<Int> = _selectedStudyBtnId
    val isStudyYesSelected: Boolean
        get() = selectedStudyBtnId.value == R.id.frameLayoutClickAnimationYes

    val isStudyNoSelected: Boolean
        get() = selectedStudyBtnId.value == R.id.frameLayoutClickAnimationNo

    // for slide journaling version
    var selectedStudyBtnSlide: Int? = null
    private val _selectedStudyBtnIdSlide = MutableLiveData<Int>()
    val selectedStudyBtnIdSlide:LiveData<Int> = _selectedStudyBtnIdSlide
    val isStudyYesSelectedSlide: Boolean
        get() = selectedStudyBtnIdSlide.value == R.id.frameLayoutSlideAnimationYes
    val isStudyNoSelectedSlide: Boolean
        get() = selectedStudyBtnIdSlide.value == R.id.frameLayoutSlideAnimationNo


    var selectedVeggiesBtn: Int? = null
    var selectedVeggiesBtnSwipe: Int? = null


    // for slide journaling version
    var selectedVeggiesBtnSlide: Int? = null

    // for swipe journaling version
    private val _selectedRadioButtonIdSwipe = MutableLiveData<Int>()
    val selectedRadioButtonIdSwipe: LiveData<Int> = _selectedRadioButtonIdSwipe

    // for click journaling version
    private val _selectedRadioButtonId = MutableLiveData<Int>()
    val selectedRadioButtonId: LiveData<Int> = _selectedRadioButtonId

    // for slide journaling version
    private val _selectedRadioButtonIdSlide = MutableLiveData<Int>()
    val selectedRadioButtonIdSlide: LiveData<Int> = _selectedRadioButtonIdSlide

    // for swipe journaling version
    private val _sleepRating = MutableLiveData<Float>()
    val sleepRating: LiveData<Float> = _sleepRating

    // for click journaling version
    private val _sleepRatingClick = MutableLiveData<Float>()
    val sleepRatingClick: LiveData<Float> = _sleepRatingClick

    // for slide journaling version
    private val _sleepRatingSlide = MutableLiveData<Float>()
    val sleepRatingSlide: LiveData<Float> = _sleepRatingSlide


    fun setCheckBoxState(key: String, isChecked: Boolean) {
        _checkBoxStates[key] = isChecked
    }

    fun getCheckBoxState(key: String): Boolean {
        return _checkBoxStates[key] ?: false
    }

    fun onRadioButtonSelected(checkedId: Int) {
        _selectedRadioButtonId.value = checkedId  // for click journaling version
    }

    fun onRadioButtonSelectedSwipe(checkedId: Int) {
        _selectedRadioButtonIdSwipe.value = checkedId  // for swipe journaling version
    }

    fun onRadioButtonSelectedSlide(checkedId: Int) {
        _selectedRadioButtonIdSlide.value = checkedId  // for slide journaling version
    }

    fun onRatingChangedSwipe(rating: Float) {
        _sleepRating.value = rating
    }

    fun onRatingChanged(rating: Float) {
        _sleepRatingClick.value = rating
    }

    fun onRatingChangedSlide(rating: Float) {
        _sleepRatingSlide.value = rating
    }

    fun onTextChangedSwipe(text: String) {
        _dayTextJournal.value = text
    }

    fun onTextChanged(text: String) {
        _dayTextJournalClick.value = text
    }
    fun onDayTextChangedSlide(text: String) {
        _dayTextJournalSlide.value = text
    }

    fun onBreakfastTextChangedSlide(text: String) {
        _breakfastTextFoodSlide.value = text
    }
    fun onLunchTextChangedSlide(text: String) {
        _lunchTextFoodSlide.value = text
    }
    fun onDinnerTextChangedSlide(text: String) {
        _dinnerTextFoodSlide.value = text
    }

    fun onBreakfastTextChangedClick(text: String) {
        _breakfastTextFoodClick.value = text
    }
    fun onLunchTextChangedClick(text: String) {
        _lunchTextFoodClick.value = text
    }
    fun onDinnerTextChangedClick(text: String) {
        _dinnerTextFoodClick.value = text
    }
    fun onPositiveTextChanged(text: String) {
        _positiveJournal.value = text
    }
    fun onPositive1TextChanged(text: String) {
        _positive1JournalClick.value = text
    }
    fun onPositive1TextChangedSlide(text: String) {
        _positive1JournalSlide.value = text
    }

    fun onPositive2TextChanged(text: String) {
        _positive2JournalClick.value = text}

    fun onPositive2TextChangedSlide(text: String) {
        _positive2JournalSlide.value = text
    }

    fun onPositive3TextChanged(text: String) {
        _positive3JournalClick.value = text
    }
    fun onPositive3TextChangedSlide(text: String) {
        _positive3JournalSlide.value = text
    }


    fun onStudyYesSelectedSwipe() {
        _selectedStudyBtnIdSwipe.value = R.id.frameLayoutAnimationYes
    }
    fun onStudyYesSelected() {
        _selectedStudyBtnId.value = R.id.frameLayoutClickAnimationYes
    }
    fun onStudyYesSelectedSlide() {
        _selectedStudyBtnIdSlide.value = R.id.frameLayoutSlideAnimationYes
    }


    fun onStudyNoSelectedSwipe() {
        _selectedStudyBtnIdSwipe.value = R.id.frameLayoutAnimationNo
    }
    fun onStudyNoSelected() {
        _selectedStudyBtnId.value = R.id.frameLayoutClickAnimationNo
    }
    fun onStudyNoSelectedSlide() {
        _selectedStudyBtnIdSlide.value = R.id.frameLayoutSlideAnimationNo
    }

    fun onBreakfastImageChanged(image: Bitmap?) {
        _breakfastImage.value = image
    }
    fun onBreakfastImageChangedClick(image: Bitmap?) {
        _breakfastImageClick.value = image
    }
    fun onLunchImageChanged(image: Bitmap?) {
        _lunchImage.value = image
    }
    fun onLunchImageChangedClick(image: Bitmap?) {
        _lunchImageClick.value = image
    }
    fun onDinnerImageChanged(image: Bitmap?) {
        _dinnerImage.value = image
    }
    fun onDinnerImageChangedClick(image: Bitmap?) {
        _dinnerImageClick.value = image
    }


}