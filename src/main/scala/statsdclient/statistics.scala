package statsdclient

import akka.util.ByteString

sealed trait StatsdStatistic {
  def toByteString(prefix: String): ByteString
}

case class Counter(aspect: String, delta: Int) extends StatsdStatistic {
  def toByteString(prefix: String): ByteString =
    ByteString("%s.%s:%d|c".format(prefix, aspect, delta))
}

case class Gauge(aspect: String, value: Int) extends StatsdStatistic {
  def toByteString(prefix: String): ByteString =
    ByteString("%s.%s:%d|g".format(prefix, aspect, value))
}

case class ExecutionTime(aspect: String, timeMs: Int) extends StatsdStatistic {
  def toByteString(prefix: String): ByteString =
    ByteString("%s.%s:%d|ms".format(prefix, aspect, timeMs))
}
