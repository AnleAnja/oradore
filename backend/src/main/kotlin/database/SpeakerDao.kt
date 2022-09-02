package database

import database.DatabaseFactory.dbQuery
import models.Speaker
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

class SpeakerDao {
    suspend fun insertSpeakers(speakers: List<Speaker>) {
        dbQuery {
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
        }
    }

    suspend fun getAllSpeakers(): List<Speaker> =
        dbQuery {
            Speakers.selectAll().map {
                Speaker(
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
                )
            }
        }
}