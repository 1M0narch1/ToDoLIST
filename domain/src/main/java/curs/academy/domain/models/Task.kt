package curs.academy.domain.models

data class Task (
    val title : String,
    val text : String,
    val dateOfCreation : Long,
    val dateOfFutureExecution : Long,
    val taskCompleted : Boolean,
    val userId : String,
    val id : Int = 0
)