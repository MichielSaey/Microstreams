import io.github.serpro69.kfaker.Faker


fun main(args: Array<String>) {

    val topStarters = createTopStarters(10)

    val topStarterJPAService = TopStarterJPAService()
    val topStarterMicroDDBService = TopStarterMicroDDBService()

    println("TopStarters in JPA database:")
    for (topStarter in setAndGetAllTopStartersJPA(topStarters)) {
        println(topStarter)
    }

    println("TopStarters in MicroStream database:")
    for (topStarter in setAndGetAllTopStartersMicroDDB(topStarters)) {
        println(topStarter)
    }

}

fun setAndGetAllTopStartersJPA(topStarters: List<TopStarter>): List<TopStarter> {
    val topStarterJPAService = TopStarterJPAService()
    for (topStarter in topStarters) {
        topStarterJPAService.addTopStarter(topStarter)
    }
    val topStartersJPA = topStarterJPAService.getAllTopStarters()
    topStarterJPAService.close()
    return topStartersJPA
}

fun setAndGetAllTopStartersMicroDDB(topStarters: List<TopStarter>): List<TopStarter> {
    val topStarterMicroDDBService = TopStarterMicroDDBService()
    for (topStarter in topStarters) {
        topStarterMicroDDBService.addTopStarter(topStarter)
    }
    val topStartersMicroDDB = topStarterMicroDDBService.getAllTopStarters()
    topStarterMicroDDBService.close()
    return topStartersMicroDDB
}

// Function to create a list of random TopStarter objects
fun createTopStarters(count: Int): List<TopStarter> {
    // Faker is a library to generate fake data, for nerd reasons all the names are Twin Peaks characters
    val faker = Faker()
    val topStarters = mutableListOf<TopStarter>()
    for (i in 1..count) {
        val topStarter = TopStarter(
            faker.twinPeaks.characters(),
            faker.random.nextInt(18, 100),
            faker.company.name(),
        )
        topStarters.add(topStarter)
    }
    return topStarters
}