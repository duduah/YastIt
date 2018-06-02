package es.diegogs.yastit.model

object Tables {

    private val tables: List<Table> = getTables()

    private fun getTables(): List<Table> {

        return (1..10).map {
            Table(it)
        }
    }

    fun getTablesCount() = tables.size

    fun getDishesFromTable(tableNumber: Int) = tables[tableNumber].getDishes()

    fun getBillFromTable(tableNumber: Int) =  tables[tableNumber].getBillFromTable()

    fun toArray() = tables.toTypedArray()
}