fun main(args: Array<String>) {
    calculateFee("Mastercard", 0, 200)
    calculateFee("Maestro", 200, 200)
    calculateFee("Mastercard", 0, 1000000)
    calculateFee(monthTransferredAmount = 0, transferAmount = 1000)
    calculateFee("Visa", 5000, 10000)
    calculateFee("Visa", 5000, 100)
    calculateFee("Amex", 5000, 10000)
}

fun calculateFee(cardType: String = "VK Pay", monthTransferredAmount: Int = 0, transferAmount: Int) {
    print("Перевод: $cardType, сумма $transferAmount, за месяц: $monthTransferredAmount: ")
    println(
        when (cardType) {
            "Mastercard", "Maestro" -> calculateMCFee(monthTransferredAmount, transferAmount)
            "Visa", "Мир" -> calculateVisaFee(monthTransferredAmount, transferAmount)
            "VK Pay" -> calculateVKFee(monthTransferredAmount, transferAmount)
            else -> "неизвестный тип карты!"
        }
    )
}

fun calculateMCFee(monthTransferredAmount: Int, transferAmount: Int): String {
    val maxTransferAmount = 150000;
    val maxMonthTransferAmount = 600000;
    val percentFee = 0.6;
    val amountFee = 20;
    val forecastMonthTransferAmount = transferAmount + monthTransferredAmount;
    val minNoFeeMonthAmount = 300;
    val maxNoFeeMonthAmount = 75000;

    return when {
        forecastMonthTransferAmount > maxMonthTransferAmount -> "превышен лимит месячного перевода!"
        transferAmount > maxTransferAmount -> "превышен лимит дневного перевода!"
        forecastMonthTransferAmount in minNoFeeMonthAmount..maxNoFeeMonthAmount -> "комиссия не взимается"
        else -> "взимается комиссия ($percentFee% + 20 руб): " + (transferAmount * percentFee / 100.0 + amountFee).toInt()
    }
}

fun calculateVisaFee(monthTransferredAmount: Int, transferAmount: Int): String {
    val minFeeAmount = 35
    val percentFee = 0.75;
    val maxTransferAmount = 150000;
    val maxMonthTransferAmount = 600000;
    val forecastMonthTransferAmount = transferAmount + monthTransferredAmount;

    return when {
        forecastMonthTransferAmount > maxMonthTransferAmount -> "превышен лимит месячного перевода!"
        transferAmount > maxTransferAmount -> "превышен лимит дневного перевода!"
        else -> {
            val fee = (transferAmount * percentFee / 100.0).toInt()
            "взимается комиссия ($percentFee%, минимум $minFeeAmount руб): " + if (fee >= minFeeAmount) fee else minFeeAmount
        }
    }
}

fun calculateVKFee(monthTransferredAmount: Int, transferAmount: Int): String {
    val maxTransferAmount = 15000;
    val maxMonthTransferAmount = 40000;

    return when {
        transferAmount + monthTransferredAmount > maxMonthTransferAmount -> "превышен лимит месячного перевода!"
        transferAmount > maxTransferAmount -> "превышен лимит дневного перевода!"
        else -> "комиссия не взимается"
    }
}