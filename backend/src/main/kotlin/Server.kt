import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import com.example.oradore.Greeting

fun greet(): String {
    return Greeting().greeting()
}

fun main() {
    embeddedServer(Netty, 9090) {
        routing {
            get("/hello") {
                call.respondText(greet())
            }
        }
    }.start(wait = true)
}