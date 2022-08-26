package database

import api.Category
import api.ProgramEntry
import api.Room
import api.Speaker
import org.jetbrains.exposed.sql.insert

class ProgramEntryDao {
    fun insert(room: Room, speakers: List<Speaker>, programEntry: ProgramEntry) {
        Rooms.insert {
            it[id] = room.id
            it[name] = room.name
        }

        speakers.forEach { speaker ->
            Speakers.insert {
                it[id] = speaker.id
                it[firstName] = speaker.firstName
                it[lastName] = speaker.lastName
                it[imgLarge] = speaker.imgLarge
                it[imgPreview] = speaker.imgPreview
                it[bio] = speaker.bio
                it[company] = speaker.company
                it[jobTitle] = speaker.jobTitle
                it[location] = speaker.location
                it[website] = speaker.website
                it[linkedin] = speaker.linkedin
                it[xing] = speaker.xing
                it[twitter] = speaker.twitter
                it[instagram] = speaker.instagram
                it[facebook] = speaker.facebook
            }
        }

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