package com.example.workoutapp

class ExerciseModel(
    private var id: Int,
    private var name: String,
    private var image: Int,
    private var isCompleted: Boolean,
    private var isSelected: Boolean
){
    fun getId():Int{return id}
    fun getName():String{return name}
    fun getImage():Int{return image}
    fun getIsCompleted():Boolean{return isCompleted}
    fun getIsSelected():Boolean {return isSelected}

    fun setId(id:Int){
        this.id = id
    }

    fun setName(name: String){
        this.name = name
    }

    fun setImage(image:Int){
        this.image = image
    }

    fun setCompleted(isCompleted: Boolean){
        this.isCompleted = isCompleted
    }

    fun setSelected(isSelected: Boolean){
        this.isSelected = isSelected
    }



}