statsd-akka
===========

Simple Statsd client based on Akka IO

Statsd: https://github.com/etsy/statsd

Example
=======

```
import akka.actor.{Actor, ActorSystem, Props}
import java.net.InetSocketAddress
import java.util.Random
import statsdclient._

val address = new InetSocketAddress(<statsd server IP>, 8125)
val system = ActorSystem("example")
val client = system.actorOf(Props(new Statsd(address, "example")))

client ! Counter("mycount", 10)
client ! Gauge("mygauge", 10)
client ! ExecutionTime("mytime", 1600)
```
