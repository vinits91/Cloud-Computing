package Hadoop_Sort.HadoopSort;

import java.io.IOException;

import org.apache.hadoop.io.Text;

public class Mapper extends org.apache.hadoop.mapreduce.Mapper<Object, Text, Text, Text> {

	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {

		Text actualValue = new Text();
		String[] inputValue = value.toString().split(" ");
		String key1 = inputValue[0];
		Text ActualKey = new Text(key1);
		actualValue.set(inputValue[1]);
		context.write(ActualKey, actualValue);

	}

}
