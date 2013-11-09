import akka.actor.{Actor, ActorSystem, Props}
import java.net.InetSocketAddress
import java.util.Random
import statsdclient._

object StatsdExample {
  def main(args: Array[String]) {
    val address = new InetSocketAddress("127.0.0.1", 8125)
    val system = ActorSystem("example")
    val client = system.actorOf(Props(new Statsd(address, "example")))
    val random = new Random

    while(true) {
      client ! Gauge("mygauge", Math.abs(random.nextInt % 128))
      Thread.sleep(500)
    }
  }
}
