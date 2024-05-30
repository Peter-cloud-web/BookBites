package com.example.bookbites.ui.uistates

import com.example.bookbites.model.user_details.UserDetailsResponse

data class UserProfileIUState(
    val isError : String = "",
    val isSuccess:UserDetailsResponse? = null,
    val isLoading:Boolean = false
)
