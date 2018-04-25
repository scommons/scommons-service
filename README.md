
[![Build Status](https://travis-ci.org/scommons/scommons-service.svg?branch=master)](https://travis-ci.org/scommons/scommons-service)
[![Coverage Status](https://coveralls.io/repos/github/scommons/scommons-service/badge.svg?branch=master)](https://coveralls.io/github/scommons/scommons-service?branch=master)

## scommons-service
Common service/server layer Scala components

### How to add it to your project

Current version is under active development, but you already can try it:
```scala
val scommonsVer = "0.1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  // shared
  "org.scommons.api" %%% "scommons-api-core" % scommonsVer,
  "org.scommons.api" %%% "scommons-api-joda-time" % scommonsVer,

  // server/jvm only
  "org.scommons.api" %% "scommons-api-play-ws" % scommonsVer,
  "org.scommons.service" %% "scommons-service-play" % scommonsVer
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

### How to Run Showcase/Demo server locally

Please, see the README.md in [scommons-showcase](https://github.com/scommons/scommons-showcase) project page.


## Documentation

//TODO
