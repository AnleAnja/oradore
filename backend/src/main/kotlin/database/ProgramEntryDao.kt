package database

import com.example.oradore.models.*
import database.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

class ProgramEntryDao(private val roomDao: RoomDao) {

    suspend fun insertProgramEntries(programEntries: List<ProgramEntryDb>) {
        dbQuery {
            programEntries.forEach { programEntry ->
                ProgramEntries.insert {
                    it[id] = programEntry.id
                    it[name] = programEntry.name
                    it[description] = programEntry.description
                    it[tag] = (programEntry.tags).abbrev
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

    suspend fun getAllProgramEntries(): List<ProgramEntry> =
        dbQuery {
            ProgramEntries
                .innerJoin(Rooms)
                .innerJoin(ProgramSpeakers)
                .innerJoin(Speakers)
                .selectAll()
                .map {
                    ProgramEntry(
                        it[ProgramEntries.name],
                        it[ProgramEntries.id],
                        it[ProgramEntries.description],
                        Category.fromAbbrev(it[ProgramEntries.tag]),
                        TimeRange(
                            it[ProgramEntries.startTime],
                            it[ProgramEntries.endTime]
                        ),
                        Room(
                            it[Rooms.id],
                            it[Rooms.name],
                            it[Rooms.desc],
                            roomDao.roomLocation(it[Rooms.url], it[Rooms.buildingInfo])
                        ),
                        listOf(
                            Pair(
                                first = Speaker(
                                    it[Speakers.id],
                                    it[Speakers.firstName],
                                    it[Speakers.lastName],
                                    it[Speakers.imgLarge],
                                    it[Speakers.imgPreview],
                                    it[Speakers.bio],
                                    it[Speakers.company],
                                    it[Speakers.jobTitle],
                                    it[Speakers.location],
                                    it[Speakers.website],
                                    it[Speakers.linkedin],
                                    it[Speakers.xing],
                                    it[Speakers.twitter],
                                    it[Speakers.instagram],
                                    it[Speakers.facebook]
                                ),
                                second = Role.fromAbbrev(it[ProgramSpeakers.role])
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