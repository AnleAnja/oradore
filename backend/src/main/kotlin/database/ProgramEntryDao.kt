package database

import api.*
import database.DatabaseFactory.dbQuery
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

@Serializable
data class ProgramEntryPreview(
    val name: String,
    val id: String,
    val tags: Category,
    val timeRange: TimeRange,
    val room: Room,
    val speakers: List<SpeakerPreview>,
    val isCanceled: Boolean,
    val format: Format
)

@Serializable
data class SpeakerPreview(
    val id: String,
    val firstName: String,
    val lastName: String,
    val imgPreview: String?,
    val company: String,
    val jobTitle: String
)

class ProgramEntryDao {

    suspend fun insertProgramEntries(programEntries: List<ProgramEntry>) {
        dbQuery {
            programEntries.forEach { programEntry ->
                ProgramEntries.insert {
                    it[id] = programEntry.id
                    it[name] = programEntry.name
                    it[description] = programEntry.description
                    it[tag] = (programEntry.tags.firstOrNull() ?: Category.OTHER).abbrev
                    it[startTime] = programEntry.timeRange.start
                    it[endTime] = programEntry.timeRange.end
                    it[roomId] = programEntry.roomId
                    it[isCanceled] = programEntry.isCanceled
                    it[format] = programEntry.format.abbrev
                }

                programEntry.speakers.forEach { speakerRef ->
                    ProgramSpeakers.insert {
                        it[programEntryId] = programEntry.id
                        it[speakerId] = speakerRef.id
                        it[role] = speakerRef.role.label
                    }
                }
            }
        }
    }

    suspend fun getAllProgramEntries(): List<ProgramEntryPreview> = dbQuery {
        ProgramEntries
            .innerJoin(Rooms)
            .innerJoin(ProgramSpeakers)
            .innerJoin(Speakers)
            .selectAll()
            .map {
                ProgramEntryPreview(
                    it[ProgramEntries.name],
                    it[ProgramEntries.id],
                    Category.fromAbbrev(it[ProgramEntries.tag]),
                    TimeRange(
                        it[ProgramEntries.startTime],
                        it[ProgramEntries.endTime]
                    ),
                    Room(
                        it[Rooms.id],
                        it[Rooms.name],
                    ),
                    listOf(
                        SpeakerPreview(
                            it[Speakers.id],
                            it[Speakers.firstName],
                            it[Speakers.lastName],
                            it[Speakers.imgPreview],
                            it[Speakers.company],
                            it[Speakers.jobTitle]
                        )
                    ),
                    it[ProgramEntries.isCanceled],
                    Format.fromAbbrev(it[ProgramEntries.format])
                )
            }
            .groupBy { it.id }
            .map {
                it.value.first().copy(speakers = it.value.map {
                    it.speakers.first()
                })
            }
    }
}