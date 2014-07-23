package akka.persistence.snapshot.inmemory

import akka.persistence.snapshot.SnapshotStore
import akka.persistence.{SelectedSnapshot, SnapshotMetadata, SnapshotSelectionCriteria}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.stm._

/**
 * TODO: proper description
 *
 * @author Mikhail Sokolov
 */
class InMemorySnapshotStore extends SnapshotStore {
  private[this] val map = TMap.empty[SnapshotMetadata, Any]

  def loadAsync(persistenceId: String, criteria: SnapshotSelectionCriteria): Future[Option[SelectedSnapshot]] = Future {
    atomic { implicit txn =>
      val candidates = map.keys.filter(satisfies(persistenceId, criteria)).toList
      for {
        metadata <- candidates.sorted(Ordering[SnapshotMetadata].reverse).headOption
        snapshot <- map.get(metadata)
      } yield SelectedSnapshot(metadata, snapshot)
    }
  }

  def saveAsync(metadata: SnapshotMetadata, snapshot: Any): Future[Unit] = Future {
    map.single.update(metadata, snapshot)
  }

  def saved(metadata: SnapshotMetadata): Unit = {}

  def delete(metadata: SnapshotMetadata): Unit = map.single.remove(metadata)

  def delete(persistenceId: String, criteria: SnapshotSelectionCriteria): Unit =
    map.single.retain((metadata, _) => !satisfies(persistenceId, criteria)(metadata))

  private[this] def satisfies(persistenceId: String, criteria: SnapshotSelectionCriteria)
    (metadata: SnapshotMetadata): Boolean =
    metadata.persistenceId == persistenceId &&
      metadata.sequenceNr <= criteria.maxSequenceNr &&
      metadata.timestamp <= criteria.maxTimestamp
}
