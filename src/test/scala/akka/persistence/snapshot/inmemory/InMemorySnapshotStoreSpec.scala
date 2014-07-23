package akka.persistence.snapshot.inmemory

import akka.persistence.snapshot.SnapshotStoreSpec
import com.typesafe.config.{Config, ConfigFactory}

class InMemorySnapshotStoreSpec extends SnapshotStoreSpec {
  override lazy val config: Config = ConfigFactory.parseString(
    """
      |akka.persistence {
      |  snapshot-store.plugin = "in-memory-snapshot-store"
      |  journal.plugin = "in-memory-journal"
      |}
    """.stripMargin)
}
