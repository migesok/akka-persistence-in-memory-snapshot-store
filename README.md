In-memory Snapshot Store Plugin for Akka Persistence
=========================================

[![Build Status](https://travis-ci.org/migesok/akka-persistence-in-memory-snapshot-store.svg?branch=master)](https://travis-ci.org/migesok/akka-persistence-in-memory-snapshot-store)

Snapshot store backed by scala-stm's TMap.

It can be used in unit tests to avoid side-effects and unwanted dependencies between test runs.

## Requirements
- Scala 2.10.x or 2.11.x
- Akka Persistence 2.3.6

## Dependency
To include the In-memory Snapshot Store Plugin into your sbt project, add the following lines to your `build.sbt` file:

    resolvers += "migesok at bintray" at "http://dl.bintray.com/migesok/maven"

    libraryDependencies +=
      "com.migesok" %% "akka-persistence-in-memory-snapshot-store" % "0.1.1"

## Configuration
To activate the plugin, add the following line to your Akka `application.conf`:

    akka.persistence.snapshot-store.plugin = "in-memory-snapshot-store"
