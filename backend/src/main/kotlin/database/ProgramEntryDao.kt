package database

import com.example.oradore.models.Category
import com.example.oradore.models.Format
import com.example.oradore.models.ProgramEntry
import com.example.oradore.models.TimeRange
import database.DatabaseFactory.dbQuery
import com.example.oradore.models.ProgramEntryPreview
import com.example.oradore.models.Room
import com.example.oradore.models.SpeakerPreview
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

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

    suspend fun getAllProgramEntries(): List<ProgramEntryPreview> =
        dbQuery {
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
                            it[Rooms.description],
                            it[Rooms.url],
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
                    it.value.first().copy(
                        speakers = it.value.map { it.speakers.first() }
                    )
                }
        }
}