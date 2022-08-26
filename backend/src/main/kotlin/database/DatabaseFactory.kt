package database

import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    fun init(create: Boolean) {
        val database = Database.connect(
            "jdbc:postgresql://localhost:5432/oradoredb",
            driver = "org.postgresql.Driver",
            user = "postgres",
            password = ""
        )
        if (create) {
            transaction(database) {
                SchemaUtils.create(
                    Speakers,
                    Rooms,
                    ProgramEntries,
                    ProgramSpeakers
                )
            }
        }
        suspend fun <T> dbQuery(block: suspend () -> T): T =
            newSuspendedTransaction(Dispatchers.IO) { block() }
    }
}