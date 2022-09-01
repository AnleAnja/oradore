package database

import api.Room
import database.DatabaseFactory.dbQuery
import database.Rooms.id
import database.Rooms.name
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

class RoomDao {
    suspend fun insertRooms(rooms: List<Room>) {
        dbQuery {
            rooms.forEach { room ->
                Rooms.insert {
                    it[id] = room.id
                    it[name] = room.name
                }
            }
        }
    }

    suspend fun getAllRooms(): List<Room> = dbQuery {
        Rooms.selectAll().map {
            Room(
                it[id],
                it[name]
            )
        }
    }
}