package es.diegogs.yastit.model

data class Table (
        val tableNumber: Int,
        private var dishes: MutableList<Dish> = mutableListOf<Dish>()) {
    // TODO: Ver cómo metemos las variantes que pida el cliente


    fun addDish(newDish: Dish?) {
        if (newDish != null) {
            dishes.add(newDish)
        }
    }

    fun removeDish(index: Int) {
        dishes.removeAt(index)
    }

    fun getDishesCount() = dishes.size

    fun getDishes() = dishes

    fun getDish(index: Int) = dishes.get(index)

    fun getDish(id: String) = dishes.find { dish ->
        dish.dishId == id
    }

    fun setDishVariants(dishIndex: Int, variants: String) {
        getDish(dishIndex).variantes = variants
    }

    fun setDishVariants(dishId: String, variants: String) {
        getDish(dishId)?.variantes = variants
    }

    fun getBillFromTable(): Float {
        var billTotal = 0f
        for (dish in dishes) {
            billTotal += dish.price
        }

        return billTotal
    }

    override fun toString() = tableNumber.toString()
}