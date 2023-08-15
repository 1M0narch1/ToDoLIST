package curs.academy.domain.models

import java.net.IDN

data class User(
    val userId : String,
    val login : String,
    val password : String,
    val id : Int = 0
)