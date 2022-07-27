package com.example.workoutapp

object Constants {
    fun defaultExerciseList(): ArrayList<ExerciseModel>{
        val exerciseList=ArrayList<ExerciseModel>()

        val jumping_jacks = ExerciseModel(1, "Jumping Jacks",
            R.drawable.ic_jumping_jacks, false,false)
        exerciseList.add(jumping_jacks)

        val abdominal_crunch = ExerciseModel(2, "Crunches", R.drawable.ic_abdominal_crunch,
            false, false)
        exerciseList.add(abdominal_crunch)

        val high_kness = ExerciseModel(3, "High Knees", R.drawable.ic_high_knees_running_in_place,
            false, false)
        exerciseList.add(high_kness)

        val lunge = ExerciseModel(4, "Lunges", R.drawable.ic_lunge,
            false, false)
        exerciseList.add(lunge)

        val plank = ExerciseModel(5, "Plank", R.drawable.ic_plank,
            false, false)
        exerciseList.add(plank)

        val push_up = ExerciseModel(6, "Push Ups", R.drawable.ic_push_up,
            false, false)
        exerciseList.add(push_up)

        val push_up_with_rotation = ExerciseModel(7, "Push Ups with Rotation",
            R.drawable.ic_push_up_and_rotation, false, false)
        exerciseList.add(push_up_with_rotation)

        val side_plank = ExerciseModel(8, "Side Plank", R.drawable.ic_side_plank,
            false, false)

        val squat = ExerciseModel(9, "Squat", R.drawable.ic_squat,
            false,false)
        exerciseList.add(squat)

        val step_up = ExerciseModel(10, "Step Up", R.drawable.ic_step_up_onto_chair,
            false, false)
        exerciseList.add(step_up)

        val tricep_dips = ExerciseModel(11, "Dips", R.drawable.ic_triceps_dip_on_chair,
            false, false)
        exerciseList.add(tricep_dips)

        val wall_sit= ExerciseModel(12, "Wall Sits", R.drawable.ic_wall_sit,
            false, false)
        exerciseList.add(wall_sit)

        return exerciseList
    }
}