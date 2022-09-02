package models

import kotlinx.serialization.Serializable

@Serializable
data class SpeakerPreview(
    val id: String,
    val firstName: String,
    val lastName: String,
    val imgPreview: String?,
    val company: String,
    val jobTitle: String
)