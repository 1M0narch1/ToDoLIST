package curs.academy.domain.models

data class Note (
    val text : String,
    val dateOfCreation : Long,
    val dateOfFutureExecution : Long,
    val userId : String,
    val id : Int = 0
)