val start = System.nanoTime()
System.setProperty("spark.hadoop.dfs.replication","1")
val file=sc.textFile("hdfs:///src")
val keyVal=file.map(_.splitAt(" ")_.trim)
val sorted=keyVal.sortByKey()
val parsedOutput=sorted.map{case (key,value) => s"$key $value" }
parsedOutput.saveAsTextFile("/vol0/keyValoutput")
val end = System.nanoTime()
println("Elapsed time: " + (end - start) + "ns")

