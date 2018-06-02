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

    fun getBillFromTable(tableNumber: Int): Float {
        val dishes = getDishesFromTable(tableNumber)
        var billTotal: Float = 0f
        for (dish in dishes) {
            billTotal += dish.price
        }

        return billTotal
    }

}