
import javax.persistence.*

@Entity
@Table(name = "topstarter")
open class TopStarter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long? = null
    open var name: String? = null
    open var age: Int? = null
    open var project: String? = null

    constructor() {}

    constructor(name: String?, age: Int?, project: String?) {
        this.name = name
        this.age = age
        this.project = project
    }

    override fun toString(): String {
        return "TopStarter(id=$id, name='$name', age=$age, project='$project')"
    }
}
