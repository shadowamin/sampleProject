package com.hannibalprojects.sampleproject.data

import androidx.room.Entity

@Entity
data class User(val id : Int, val email: String, val firstName : String, val lastName : String, val avatar : String)