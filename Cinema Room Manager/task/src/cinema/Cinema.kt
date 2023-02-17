package cinema

fun showSeat(seat: MutableList<MutableList<Char>>, nbrOfSeatsForEachRow: Int) {
    print("Cinema:\n  ")
    for (i in 1..nbrOfSeatsForEachRow) print("$i ")
    for (i in seat.indices) print("\n${i + 1} ${seat[i].joinToString(" ")}")
    println()
}

fun main() {
    // var declaration
    println("Enter the number of rows:")
    val nbrRows = readln().toInt()
    println("Enter the number of seats in each row:")
    val nbrOfSeatsForEachRow = readln().toInt()
    val seat = MutableList(nbrRows) { MutableList(nbrOfSeatsForEachRow) { 'S' } }
    val totalIncome = if (nbrRows * nbrOfSeatsForEachRow <= 60) {
        nbrRows * nbrOfSeatsForEachRow * 10
    } else {
        (nbrRows / 2) * nbrOfSeatsForEachRow * 10 + (nbrRows - (nbrRows / 2)) * nbrOfSeatsForEachRow * 8
    }
    var purchasedTicket = 0
    var currentIncome = 0


    while (true) {
        //menu
        println()
        println("1. Show the seats")
        println("2. Buy a ticket")
        println("3. Statistics")
        println("0. Exit")
        when (readln().toInt()) {
            0 -> {
                break
            }

            1 -> {//showing the seat
                showSeat(seat, nbrOfSeatsForEachRow)
            }

            2 -> {
                var rowNbr: Int
                var seatNbr: Int
                while (true) {
                    println("Enter a row number:")
                    rowNbr = readln().toInt()
                    println("Enter a seat number in that row:")
                    seatNbr = readln().toInt()
                    try {
                        if (seat[rowNbr - 1][seatNbr - 1] != 'B') {
                            seat[rowNbr - 1][seatNbr - 1] = 'B'
                            break
                        } else {
                            println("That ticket has already been purchased!")
                        }
                    } catch (e: Exception) {
                        println("Wrong input!")
                    }
                    println()
                }
                seat[rowNbr - 1][seatNbr - 1] = 'B'
                purchasedTicket++
                currentIncome += if (nbrRows * nbrOfSeatsForEachRow <= 60 || nbrRows / 2 >= rowNbr) {
                    println("Ticket price: \$10")
                    10
                } else {
                    println("Ticket price: \$8")
                    8
                }
            }

            3 -> {//statistic
                println("Number of purchased tickets: $purchasedTicket")
                println("Percentage: ${"%.2f".format(purchasedTicket * 100.0 / (nbrRows * nbrOfSeatsForEachRow))}%")
                println("Current income: \$$currentIncome")
                println("Total income: \$$totalIncome")
            }

            else -> {//default
                println("Invalid option")
            }
        }

    }
}