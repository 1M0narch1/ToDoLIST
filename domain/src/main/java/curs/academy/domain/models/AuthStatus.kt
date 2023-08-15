package curs.academy.domain.models

data class AuthStatus (
    val status : Boolean,
    val error : String,
    val userId : String
    )
