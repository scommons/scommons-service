
[![CI](https://github.com/scommons/scommons-service/actions/workflows/ci.yml/badge.svg?branch=master)](https://github.com/scommons/scommons-service/actions/workflows/ci.yml?query=workflow%3Aci+branch%3Amaster)
[![scala-index](https://index.scala-lang.org/scommons/scommons-service/scommons-service-play/latest.svg)](https://index.scala-lang.org/scommons/scommons-service/scommons-service-play)
[![Coverage Status](https://coveralls.io/repos/github/scommons/scommons-service/badge.svg?branch=master)](https://coveralls.io/github/scommons/scommons-service?branch=master)

## scommons-service
Common Scala service/server layer components and utils.

### How to add it to your project

```scala
val scommonsApiVer = "1.0.0-SNAPSHOT"
val scommonsServiceVer = "1.0.0-SNAPSHOT"

libraryDependencies ++= Seq(
  // shared
  "org.scommons.api" %%% "scommons-api-core" % scommonsApiVer,
  "org.scommons.api" %%% "scommons-api-joda-time" % scommonsApiVer,

  // server/jvm only
  "org.scommons.service" %% "scommons-service-play" % scommonsServiceVer,
  "org.scommons.service" %% "scommons-service-dao" % scommonsServiceVer,
  "org.scommons.service" %% "scommons-service-util" % scommonsServiceVer,
  "org.scommons.service" %% "scommons-service-test" % scommonsServiceVer % "test",
  "org.scommons.api" %% "scommons-api-play-ws" % scommonsApiVer % "test"
)
```

Latest `SNAPSHOT` version is published to [Sonatype Repo](https://oss.sonatype.org/content/repositories/snapshots/org/scommons/), just make sure you added
the proper dependency resolver to your `build.sbt` settings:
```scala
resolvers += "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"
```

### How to Build

To build and run all the tests use the following command:
```bash
sbt clean test
```

## Documentation

//TODO
