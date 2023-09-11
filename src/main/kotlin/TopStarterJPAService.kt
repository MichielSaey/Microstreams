// EntityManager service for TopStarter
// Necessary functions are: addTopStarter, getAllTopStarters, updateTopStarter

import javax.persistence.Persistence

class TopStarterJPAService() {

    private val persistenceUnitName = "topstarter"
    private val entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName)
    private val entityManager = entityManagerFactory.createEntityManager()

    fun addTopStarter(topStarter: TopStarter) {
        try {
            entityManager.transaction.begin()
            entityManager.persist(topStarter)
            entityManager.transaction.commit()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getAllTopStarters(): List<TopStarter> {
        val query = entityManager.createQuery("SELECT t FROM TopStarter t")
        return query.resultList as List<TopStarter>
    }

    fun close() {
        entityManager.close()
        entityManagerFactory.close()
    }
}