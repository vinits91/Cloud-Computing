package Hadoop_Sort.HadoopSort;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.apache.hadoop.io.Text;

public class Reducer<Key> extends org.apache.hadoop.mapreduce.Reducer<Key, Text, Key, Text> implements Comparator<Key> {

	private Text result = new Text();

	public void reduce(Key key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		ArrayList<Text> sortValue = new ArrayList<Text>();
		Collections.sort(sortValue);
		for (Text val : sortValue) {
			result.set(val);
		}

		context.write(key, result);
	}

	public int compare(Key arg0, Key arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

}
