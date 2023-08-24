package com.example.concepts.ProgrammingParadigms.SOLID

// lets say out of all menuitems, there is a discount applicable only on beverageitems
// the liskov principle is not followed here,as S ( Instance of baseclass) cannot be replaceable by T(instance of sub class), coz
// for MenuItem the price is calculated by getPrice() and for BeverageITem price is calculate by getPriceWithDiscount()

//------------------------ ❌❌ WRONG CODE ❌❌ -------------------------

//open class MenuItem(open var price: Int, var name: String, var Description: String) {
//
//    fun getPrice(): Double {
//        return price.toDouble()
//    }
//}
//
//class BeverageItem(var price1: Int, var name1: String, var Description1: String) :
//    MenuItem(price1, name1, Description1) {
//
//    fun getPriceWithDiscount(discountPercent: Int): Double {
//        return price1 - (discountPercent * 0.01 * price1)
//    }
//
//}
//
//fun main() {
//    var menuObj = MenuItem(20, "soap", "random description")
//    var bvgObj = BeverageItem(20, "chips", "random description")
//
//    var menuItemPrice = menuObj.getPrice()
//    var bvgItemPrice = bvgObj.getPrice()
//    println("price of menuItem is $menuItemPrice")
//    println("price of bvgItem is $bvgItemPrice")
//}


//------------------------✅✅ CORRECT CODE ✅✅ -------------------------

open class MenuItem(open var price: Int, var name: String, var Description: String) {

    open fun getPrice(): Double {
        return price.toDouble()
    }
}

class BeverageItem(var price1: Int, var name1: String, var Description1: String) :
    MenuItem(price1, name1, Description1) {

    override fun getPrice(): Double {
        return price1 - getPriceWithDiscount()
    }

    private fun getPriceWithDiscount(): Double {
        var discountPercent = 10
        return discountPercent * 0.01 * price1
    }

}

fun main() {
    var menuObj = MenuItem(20, "soap", "random description")
    var bvgObj = BeverageItem(20, "chips", "random description")

    var menuItemPrice = menuObj.getPrice()
    var bvgItemPrice = bvgObj.getPrice()
    println("price of menuItem is $menuItemPrice")
    println("price of bvgItem is $bvgItemPrice")
}