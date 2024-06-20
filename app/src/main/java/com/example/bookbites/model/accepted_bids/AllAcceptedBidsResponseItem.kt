package com.example.bookbites.model.accepted_bids


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AllAcceptedBidsResponseItem(
    @SerialName("bidAcceptorId")
    val bidAcceptorId: String,
    @SerialName("bidSenderId")
    val bidSenderId: String,
    @SerialName("biddedBookId")
    val biddedBookId: Int,
    @SerialName("bookAuthor")
    val bookAuthor: String,
    @SerialName("bookPage")
    val bookPage: Int,
    @SerialName("bookSummary")
    val bookSummary: String,
    @SerialName("bookTitle")
    val bookTitle: String,
    @SerialName("createdAt")
    val createdAt: Long,
    @SerialName("isBidAccepted")
    val isBidAccepted: Boolean
)