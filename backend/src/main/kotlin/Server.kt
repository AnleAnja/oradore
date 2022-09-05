import api.ConferenceApi
import database.DatabaseFactory
import database.ProgramEntryDao
import database.RoomDao
import database.SpeakerDao
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main() {
    DatabaseFactory.init(true)
    val roomDao = RoomDao()
    val programEntryDao = ProgramEntryDao(roomDao)
    val speakerDao = SpeakerDao()
    val api = ConferenceApi(
        "https://event.talque.com/view/v1",
        programEntryDao,
        roomDao,
        speakerDao
    )

    embeddedServer(Netty, 9090) {
        install(ContentNegotiation) {
            json()
        }
        install(CORS) {
            anyHost()
        }
        routing {
            post("/program") {
                api.fetchAndSaveProgram()
                call.respond(HttpStatusCode(200, "ok"))
            }
            get("/program") {
                val result = programEntryDao.getAllProgramEntries()
                call.respond(result)
            }
            get("/rooms") {
                val rooms = roomDao.getAllRooms()
                call.respond(rooms)
            }
            get("/speakers") {
                val speakers = speakerDao.getAllSpeakers()
                call.respond(speakers)
            }
        }
    }.start(wait = true)
}