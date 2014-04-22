name := "Motari"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache
)     

play.Project.playJavaSettings

ebeanEnabled := true
  
compile in Test <<= PostCompile(Test)

