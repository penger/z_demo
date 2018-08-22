package com.hadoop.wordcount;

import org.apache.commons.io.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Iterator;

/**
 * Created by gongp on 2018/8/22.
 */
public class WordCountDemo {

    static {
        try {
            File output = new File("d://work/output");
            FileUtils.deleteDirectory(output);
            System.load("F:\\opensoft\\hadoop-2.6.0-cdh5.5.0\\bin\\hadoop.dll");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    static class CountMpper extends Mapper<LongWritable,Text,Text,IntWritable>{
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String[] words = line.split(" ");
            for (String word : words) {
                context.write(new Text(word),new IntWritable(1));
            }
        }
    }


    static class CountReducer extends Reducer<Text,IntWritable,Text,IntWritable>{
        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int count = 0;
            Iterator<IntWritable> iterator = values.iterator();
            while (iterator.hasNext()){
                IntWritable next = iterator.next();
                count += next.get();
            }
            context.write(key,new IntWritable(count));
        }
    }


    public static void main(String[] args) throws IOException, URISyntaxException, ClassNotFoundException, InterruptedException {
        System.setProperty("hadoop.home.dir","F:\\opensoft\\hadoop-2.6.0-cdh5.5.0");
        Configuration configuration = new Configuration();
        configuration.set("hadoop.tmp.dir","file:///d:/work/tmp");
        Job job = Job.getInstance(configuration);
        job.setJarByClass(WordCountDemo.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        job.setMapperClass(CountMpper.class);
        job.setReducerClass(CountReducer.class);
        FileInputFormat.setInputPaths(job,new Path("file:///d:/work/input"));
        FileOutputFormat.setOutputPath(job,new Path("file:///d:/work/output"));

//        job.addCacheFile(new URI("file:/d:/work/cache/pdts.txt"));
        //设置job的reduce个数为0,即为不运行reduce程序
//        job.setNumReduceTasks(0);
        boolean result = job.waitForCompletion(true);
        System.exit(result?0:1);
    }

}
