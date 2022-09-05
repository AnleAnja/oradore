package database

import org.jetbrains.exposed.sql.Table

object ProgramEntries : Table() {
    val id = varchar("id", 100)
    val name = varchar("name", 500)
    val description = varchar("description", 10000)
    val tag = varchar("tag", 100)
    val startTime = long("startTime")
    val endTime = long("endTime")
    val roomId = varchar("roomId", 100) references Rooms.id
    val isCanceled = bool("isCanceled")
    val format = varchar("format", 100)

    override val primaryKey = PrimaryKey(id)
}

object Rooms : Table() {
    val id = varchar("id", 100)
    val name = varchar("name", 100)
    val desc = varchar("desc", 100)
    val url = varchar("url", 500).nullable()
    val buildingInfo = varchar("buildingInfo", 100).nullable()

    override val primaryKey = PrimaryKey(id)
}

object Speakers : Table() {
    val id = varchar("id", 100)
    val firstName = varchar("firstName", 100)
    val lastName = varchar("lastName", 100)
    val imgLarge = varchar("imgLarge", 500).nullable()
    val imgPreview = varchar("imgPreview", 500).nullable()
    val bio = varchar("bio", 2500)
    val company = varchar("company", 100)
    val jobTitle = varchar("jobTitle", 100)
    val location = varchar("location", 100)
    val website = varchar("website", 100)
    val linkedin = varchar("linkedin", 100)
    val xing = varchar("xing", 100)
    val twitter = varchar("twitter", 100)
    val instagram = varchar("instagram", 100)
    val facebook = varchar("facebook", 100)

    override val primaryKey = PrimaryKey(id)
}

object ProgramSpeakers : Table() {
    val id = integer("id").autoIncrement()
    val programEntryId = varchar("programEntryId", 100) references ProgramEntries.id
    val speakerId = varchar("speakerId", 100) references Speakers.id
    var role = varchar("role", 100)

    override val primaryKey = PrimaryKey(id)
}