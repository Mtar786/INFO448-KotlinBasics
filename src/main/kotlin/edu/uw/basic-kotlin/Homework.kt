package edu.uw.basickotlin

// write a "whenFn" that takes an arg of type "Any" and returns a String
fun whenFn(arg: Any): String {
    return when (arg) {
        is String -> when (arg) {
            "Hello" -> "world"
            else -> "Say what?"
        }
        is Int -> when (arg) {
            0 -> "zero"
            1 -> "one"
            in 2..10 -> "low number"
            else -> "a number"
        }
        else -> "I don't understand"
    }
}

// write an "add" function that takes two Ints, returns an Int, and adds the values
fun add(lhs: Int, rhs: Int):  Int = lhs + rhs

// write a "sub" function that takes two Ints, returns an Int, and subtracts the values
fun sub(lhs: Int, rhs: Int): Int { return lhs - rhs }

// write a "mathOp" function that takes two Ints and a function (that takes two Ints and returns an Int), returns an Int, and applies the passed-in-function to the arguments
fun mathOp(lhs: Int, rhs: Int, op: (Int, Int) -> Int): Int {
    return op(lhs, rhs)
}

// write a class "Person" with first name, last name and age
class Person(val firstName: String, val lastName:String, var age: Int) {
    val debugString: String
        get() = "[Person firstName:$firstName lastName:$lastName age:$age]"
}


// write a class "Money"
class Money(val amount: Int, val currency: String) {
    init {
        require(amount >= 0) { "Amount must not be less than zero" }
        require(currency in setOf("USD", "EUR", "CAN", "GBP")) { "Invalid currency" }
    }


    fun convert(targetCurrency: String): Money {
        val conversionRates = mapOf(
            "USD" to 1.0,
            "GBP" to 0.5,
            "EUR" to 1.5,
            "CAN" to 1.25
        )

        if (targetCurrency == currency) {
            return Money(amount, targetCurrency)
        }

        if (!conversionRates.containsKey(currency) || !conversionRates.containsKey(targetCurrency)) {
            throw IllegalArgumentException("Invalid currency")
        }

        val conversionRate = conversionRates[targetCurrency]!! / conversionRates[currency]!!
        val convertedAmount = (amount * conversionRate).toInt()

        return Money(convertedAmount, targetCurrency)
    }


    operator fun plus(other: Money): Money {
        if (this.currency != other.currency) {
            val convertedAmount = other.convert(currency).amount
            return Money(amount + convertedAmount, currency)
        } else {
            return Money(amount + other.amount, currency)
        }
    }
}
