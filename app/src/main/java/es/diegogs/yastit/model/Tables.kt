package es.diegogs.yastit.model

import es.diegogs.yastit.R


object Tables {

    private val tables: List<Table> = getTables()

    private fun getTables(): List<Table> {

        // TODO: quitar datos de prueba

        val tables: List<Table> = (1..10).map {
            Table(it)
        }
        tables[0].addDish(Dish(name = "Sartén de guisantes, trigueros y champiñones",
                description = "Quisantes frescos salteados en mantequilla junto con unos espárragos trigueros de la huerta y unos champiñones de monte. Se deja al comensal salpimentar al gusto.",
                price = 15.50f,
                photo = R.drawable.sarten_guisantes_trigueros))

        tables[0].addDish(Dish(name = "Panna cotta de limón",
                description = "La tradicional receta de la panna cotta con hierbabuena fresca.",
                price = 5f,
                photo = R.drawable.pannacotta,
                allergens = listOf(AllergenTypes.GLUTEN)))
        tables[2].addDish(Dish(name = "Caballa curada a la bilbaina",
                description = "Lomos de caballa macerados en jalapeño, vinagre de Jerez y aceite de oliva virgen extra emplatados con escamas de sal y brote o germinados.",
                price = 12.25f,
                photo = R.drawable.caballa_curada_bilbaina,
                allergens = listOf(AllergenTypes.FISH, AllergenTypes.GLUTEN)))

        tables[2].addDish(Dish(name = "Fruta de temporada: naranjas",
                description = "Naranjas del huerto del tío José, famosas por su extraordinario saber y abundante jugo.",
                price = 3f,
                photo = R.drawable.naranjas))

        return tables
    }

    /**
     * Overloading get operator
     */
    operator fun get(index: Int) = tables[index]


    fun getTablesCount() = tables.size

    fun getDishesFromTable(tableNumber: Int) = tables[tableNumber].getDishes()

    fun getBillFromTable(tableNumber: Int) =  tables[tableNumber].getBillFromTable()

    fun toArray() = tables.toTypedArray()
}