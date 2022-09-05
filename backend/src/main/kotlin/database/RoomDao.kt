package database

import com.example.oradore.models.Room
import com.example.oradore.models.RoomLocation
import database.DatabaseFactory.dbQuery
import database.Rooms.buildingInfo
import database.Rooms.desc
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
                    it[desc] = room.desc
                    it[url] = room.roomLocation?.url
                    it[buildingInfo] = room.roomLocation?.buildingInfo
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
                    it[desc],
                    roomLocation(it[url], it[buildingInfo])
                )
            }
        }

    fun roomLocation(url: String?, buildingInfo: String?): RoomLocation? =
        if (url != null && buildingInfo != null) RoomLocation(buildingInfo, url)
        else null
}