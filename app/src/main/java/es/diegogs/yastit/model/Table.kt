package es.diegogs.yastit.model

data class Table (
        val tableNumber: Int,
        private var dishes: MutableList<Dish> = mutableListOf<Dish>()) {

    fun addDish(newDish: Dish) {
        dishes.add(newDish)
    }

    fun removeDish(index: Int) {
        dishes.removeAt(index)
    }

    fun getDishesCount() = dishes.size

    fun getDishes() = dishes

    fun getDish(index: Int) = dishes.get(index)

    fun getBillFromTable(): Float {
        var billTotal = 0f
        for (dish in dishes) {
            billTotal += dish.price
        }

        return billTotal
    }

    override fun toString() = tableNumber.toString()
}