import org.apache.spark.{SparkConf,SparkContext}
import org.apache.spark.SparkContext._

object OlaMundoSpark {
	def main(args: Array[String]) {
		val conf = new SparkConf().setMaster("local").setAppName("Spark Lab01")
		val sc = new SparkContext(conf)
		val input = sc.textFile("README.md")
		val words = input.flatMap(line => line.split(" "))
		val counts = words.map(word => (word, 1)).reduceByKey{case (x, y) => x + y}
		counts.foreach{case (word,count) => println(f"$word%20s \t $count")}
		sc.stop()
	}
}
