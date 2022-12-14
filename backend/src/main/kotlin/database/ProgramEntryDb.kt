package database

import com.example.oradore.models.Category
import com.example.oradore.models.Format
import com.example.oradore.models.SpeakerRef
import com.example.oradore.models.TimeRange
import kotlinx.serialization.Serializable

@Serializable
data class ProgramEntryDb(
    val name: String,
    val id: String,
    val description: String,
    val tags: Category,
    val timeRange: TimeRange,
    val roomId: String,
    val speakers: List<SpeakerRef>,
    val isCanceled: Boolean,
    val format: Format
)