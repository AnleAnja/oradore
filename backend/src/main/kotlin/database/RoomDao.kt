package database

import com.example.oradore.models.Room
import database.DatabaseFactory.dbQuery
import database.Rooms.id
import database.Rooms.name
import database.Rooms.url
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

class RoomDao {
    suspend fun insertRooms(rooms: List<Room>) {
        dbQuery {
            rooms.forEach { room ->
                Rooms.insert {
                    it[id] = room.id
                    it[name] = room.name
                    it[url] = room.url
                }
            }
        }
    }

    suspend fun getAllRooms(): List<Room> =
        dbQuery {
            Rooms.selectAll().map {
                Room(
                    it[id],
                    it[name],
                    it[url]
                )
            }
        }
}