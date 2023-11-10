import org.junit.Test

import org.junit.Assert.*

class MainKtTest {

    @Test
    fun calculateMCFeeGreaterThenMaxMonthAmount() {
        val monthTransferredAmount: Int = 1000000
        val transferAmount: Int = 100

        val result = calculateMCFee(monthTransferredAmount, transferAmount)

        assertEquals("превышен лимит месячного перевода!", result)
    }

    @Test
    fun calculateMCFeeGreaterThenMaxTransferAmount() {
        val monthTransferredAmount: Int = 100
        val transferAmount: Int = 200000

        val result = calculateMCFee(monthTransferredAmount, transferAmount)

        assertEquals("превышен лимит дневного перевода!", result)
    }

    @Test
    fun calculateMCFeeNoFeeAmount() {
        val monthTransferredAmount: Int = 0
        val transferAmount: Int = 100

        val result = calculateMCFee(monthTransferredAmount, transferAmount)

        assertEquals("взимается комиссия (0.6% + 20 руб): 20", result)
    }

    @Test
    fun calculateMCNoFeeAmount() {
        val monthTransferredAmount: Int = 0
        val transferAmount: Int = 1000

        val result = calculateMCFee(monthTransferredAmount, transferAmount)

        assertEquals("комиссия не взимается", result)
    }

    @Test
    fun calculateVisaFeeGreaterThenMaxMonthAmount() {
        val monthTransferredAmount: Int = 1000000
        val transferAmount: Int = 100

        val result = calculateVisaFee(monthTransferredAmount, transferAmount)

        assertEquals("превышен лимит месячного перевода!", result)
    }

    @Test
    fun calculateVisaFeeGreaterThenMaxTransferAmount() {
        val monthTransferredAmount: Int = 100
        val transferAmount: Int = 500000

        val result = calculateVisaFee(monthTransferredAmount, transferAmount)

        assertEquals("превышен лимит дневного перевода!", result)
    }

    @Test
    fun calculateVisaMinFeeAmount() {
        val monthTransferredAmount: Int = 100
        val transferAmount: Int = 500

        val result = calculateVisaFee(monthTransferredAmount, transferAmount)

        assertEquals("взимается комиссия (0.75%, минимум 35 руб): 35", result)
    }

    @Test
    fun calculateVisaFeeAmount() {
        val monthTransferredAmount: Int = 100
        val transferAmount: Int = 5000

        val result = calculateVisaFee(monthTransferredAmount, transferAmount)

        assertEquals("взимается комиссия (0.75%, минимум 35 руб): 37", result)
    }

    @Test
    fun calculateVKFeeGreaterThenMaxMonthAmount() {
        val monthTransferredAmount: Int = 100000
        val transferAmount: Int = 50

        val result = calculateVKFee(monthTransferredAmount, transferAmount)

        assertEquals("превышен лимит месячного перевода!", result)
    }

    @Test
    fun calculateVKFeeGreaterThenMaxTransferAmount() {
        val monthTransferredAmount: Int = 100
        val transferAmount: Int = 20000

        val result = calculateVKFee(monthTransferredAmount, transferAmount)

        assertEquals("превышен лимит дневного перевода!", result)
    }

    @Test
    fun calculateVKFeeAmount() {
        val monthTransferredAmount: Int = 100
        val transferAmount: Int = 500

        val result = calculateVKFee(monthTransferredAmount, transferAmount)

        assertEquals("комиссия не взимается", result)
    }

    @Test
    fun calculateFeeTest() {
        val result = calculateFee(transferAmount = 100)

        assertEquals(Unit, result)
    }

    @Test
    fun mainTest() {
        val result = main(arrayOf<String>())

        assertEquals(Unit, result)
    }
}
