import api.ConferenceApi
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import com.example.oradore.Greeting
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*

fun greet(): String {
    return Greeting().greeting()
}

fun main() {
    val api = ConferenceApi("https://event.talque.com/view/v1")

    embeddedServer(Netty, 9090) {
        install(ContentNegotiation) {
            json()
        }
        install(CORS) {
            anyHost()
        }
        routing {
            get("/hello") {
                call.respondText(greet())
            }
            get("/programm") {
                val result = api.fetchProgram()
                call.respond(result)
            }
        }
    }.start(wait = true)
}