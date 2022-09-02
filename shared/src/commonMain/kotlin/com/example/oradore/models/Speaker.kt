package com.example.oradore.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Speaker(
    @SerialName("profileId")
    val id: String,
    val firstName: String,
    val lastName: String,
    @SerialName("photoMedium")
    val imgLarge: String?,
    @SerialName("photo")
    val imgPreview: String?,
    val bio: String,
    @SerialName("companyName")
    val company: String,
    val jobTitle: String,
    val location: String,
    val website: String,
    val linkedin: String,
    val xing: String,
    val twitter: String,
    val instagram: String,
    val facebook: String
)