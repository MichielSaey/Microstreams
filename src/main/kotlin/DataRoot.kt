// The root of a MicroStream database can be any type, but it is recommended to define a root type specific for your application.
// This is because the root object is the entry point to the database, and therefore the entry point to your application.
// In this case, the root object is a list of TopStarter objects, but it could also be a single TopStarter object,
// or any other type that is relevant for your application.
class DataRoot {

    // The root object is a list of TopStarter objects
    private var topStarters: MutableList<TopStarter> = mutableListOf<TopStarter>()

    // There is a function to add a TopStarter object to the list
    fun add(topStarter: TopStarter) {
        topStarters.add(topStarter)
    }

    // And a function to get the list of TopStarter objects
    fun getTopStarters(): List<TopStarter> {
        return topStarters
    }

}