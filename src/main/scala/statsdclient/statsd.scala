package statsdclient

import akka.actor.{Actor, ActorRef, Props}
import akka.io.{IO, UdpConnected}
import java.net.InetSocketAddress

object Statsd {
  def props(remote: InetSocketAddress, prefix: String) =
    Props(classOf[Statsd], remote, prefix)
}

class Statsd(remote: InetSocketAddress, prefix: String) extends Actor {
  import UdpConnected._
  import context.system

  IO(UdpConnected) ! UdpConnected.Connect(self, remote)

  def receive = {
    case UdpConnected.Connected =>
      context.become(ready(sender))
  }

  def ready(send: ActorRef): Receive = {
    case stat: StatsdStatistic =>
      send ! UdpConnected.Send(stat.toByteString(prefix), remote)
  }
}
