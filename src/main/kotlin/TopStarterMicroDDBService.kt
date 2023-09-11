// Author: Michiel Saey

import one.microstream.afs.aws.dynamodb.types.DynamoDbConnector
import one.microstream.afs.blobstore.types.BlobStoreFileSystem
import one.microstream.storage.embedded.types.EmbeddedStorage
import one.microstream.storage.embedded.types.EmbeddedStorageManager
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import java.net.URI

class TopStarterMicroDDBService {

    // StorageManager is the class that is used to access the database, and therrefore needs to a
    private var storageManager: EmbeddedStorageManager;

    constructor() {

        // DynamoDB is a NoSQL database developed by Amazon and can be used for blob storage.
        // The DynamoDB client is used to connect to the DynamoDB database, that in this case is running locally inside the docker container
        val dynamoDBClient: DynamoDbClient = DynamoDbClient.builder()
            .endpointOverride(URI("http://localhost:8000"))
            .region(Region.US_EAST_1)
            .credentialsProvider(
                StaticCredentialsProvider.create(AwsBasicCredentials.create("root", "root"))
            )
            .build()


        // The BlobStoreFileSystem is a MicroStream class that is used to store data in a blob storage
        val fileSystem: BlobStoreFileSystem = BlobStoreFileSystem.New(DynamoDbConnector.Caching(dynamoDBClient))

        // The EmbeddedStorageManager is a MicroStream class that is used to access the database
        this.storageManager = EmbeddedStorage.start(
            fileSystem.ensureDirectoryPath("storage")
        )

        // In MicroStream, the root object is the entry point to the database. If the root object is null, it means that
        // the database is empty, so we create a new root object and store it in the database to start the database.
        if (storageManager.root() == null) {
            println("No root object found, creating new root object")

            // The root is defined in the DataRoot class
            val root = DataRoot()

            storageManager.setRoot(root)
            storageManager.storeRoot()
        }
        storageManager.root()
    }

    private fun getRoot(): DataRoot {
        return this.storageManager.root() as DataRoot
    }

    fun addTopStarter(topStarter: TopStarter) {
        this.getRoot().add(topStarter)
        this.storageManager.storeRoot()
    }

    fun getAllTopStarters(): List<TopStarter> {
        return this.getRoot().getTopStarters()
    }

    fun close() {
        this.storageManager.shutdown()
    }

}