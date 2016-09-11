package Hadoop_Sort.HadoopSort;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * Hello world!
 *
 */
public class App extends Configured implements Tool{
    public static void main( String[] args )
    {
    	int res;
		try {
			res = ToolRunner.run(new Configuration(), new App(), args);
			 System.exit(res);  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          
    }

	public int run(String[] arg0) throws Exception {
		Job job = Job.getInstance(new Configuration());
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        job.setMapperClass(Mapper.class);
        job.setReducerClass(Reducer.class);

        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        FileInputFormat.setInputPaths(job, new Path("/src"));
        FileOutputFormat.setOutputPath(job, new Path("output"));

        job.setJarByClass(App.class);

        job.submit();
        return 0;
		
	}
}
