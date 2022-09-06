package database

import Environment
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Schema
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    fun init(environment: Environment) {
        val database = Database.connect(
            "jdbc:postgresql://${environment.serverName()}:5432/${environment.dbName()}",
            driver = "org.postgresql.Driver",
            user = "postgres",
            password = environment.password()
        )
        if (environment.createDb()) {
            transaction(database) {
                SchemaUtils.dropSchema(Schema("public"), cascade = true)
                SchemaUtils.createSchema(Schema("public"))
                SchemaUtils.create(
                    Speakers,
                    Rooms,
                    ProgramEntries,
                    ProgramSpeakers
                )
            }
        }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}