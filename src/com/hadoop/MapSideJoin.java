package com.hadoop;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

public class MapSideJoin {
    static class MapSideJoinMapper extends Mapper<LongWritable,Text,Text,NullWritable>{
        //用来保存产品信息表
        HashMap<String, String> map = new HashMap<>();
        Text k = new Text();
        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("pdts.txt")));
            String line;
            while(StringUtils.isNotEmpty(line=bufferedReader.readLine())){
                String[] field = line.split(",");
                map.put(field[0],field[1]);
            }
            bufferedReader.close();
        }

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String oldline = value.toString();
            String[] fields = oldline.split("\t");
            String pdName = map.get(fields[1]);
            k.set(oldline+"\t"+pdName);
            context.write(k,NullWritable.get());

        }
    }

    public static void main(String[] args) throws IOException, URISyntaxException, ClassNotFoundException, InterruptedException {
        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration);
        job.setJarByClass(MapSideJoin.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputKeyClass(NullWritable.class);
        FileInputFormat.setInputPaths(job,new Path("d:/work/input"));
        FileOutputFormat.setOutputPath(job,new Path("d:/work/output"));

        job.addCacheFile(new URI("file:/d:/work/cache/pdts.txt"));
        //设置job的reduce个数为0,即为不运行reduce程序
        job.setNumReduceTasks(0);
        boolean result = job.waitForCompletion(true);
        System.exit(result?0:1);

    }
}
