package statsdclient

import akka.actor.{Actor, ActorRef, Props}
import akka.io.{IO, Udp}
import java.net.InetSocketAddress

object Statsd {
  def props(remote: InetSocketAddress, prefix: String) =
    Props(classOf[Statsd], remote, prefix)
}

class Statsd(remote: InetSocketAddress, prefix: String) extends Actor {
  import Udp._
  import context.system

  IO(Udp) ! Udp.SimpleSender

  def receive = {
    case Udp.SimpleSenderReady =>
      context.become(ready(sender))
  }

  def ready(send: ActorRef): Receive = {
    case stat: StatsdStatistic =>
      send ! Udp.Send(stat.toByteString(prefix), remote)
  }
}
