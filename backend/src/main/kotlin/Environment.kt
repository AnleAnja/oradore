enum class Environment {
    DEV,
    PROD;

    fun createDb(): Boolean =
        when (this) {
            DEV -> true
            PROD -> true
        }

    fun dbName(): String =
        when (this) {
            DEV -> "oradoredb"
            PROD -> "postgres"
        }

    fun serverName(): String =
        when (this) {
            DEV -> "localhost"
            PROD -> "oradore-db"
        }

    fun password(): String =
        when (this) {
            DEV -> ""
            PROD -> "postgres"
        }
}