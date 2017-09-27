import org.scalameter.api._

object MicroBench extends Bench.OfflineReport {


  val ranges = for {
    size <- Gen.range("size")(300000, 1500000, 300000)
  } yield 0 until size

  measure method "map" in {
    using(ranges) config(
      reports.resultDir -> "target/bench",
      exec.benchRuns -> 2,
      exec.maxWarmupRuns -> 2,
      exec.minWarmupRuns -> 2,
      exec.independentSamples -> 2
    ) curve("Range") in {
      _.map(_ + 1)
    }
  }
}
