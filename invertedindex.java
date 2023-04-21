package invertedindex ;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class Invertedindex {

    public static class TokenizerMapper extends Mapper<Object, Text, Text, Text> {

        private Text word = new Text();
        private Text docId = new Text();

        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {

            String fileName = ((org.apache.hadoop.mapreduce.lib.input.FileSplit) context.getInputSplit()).getPath().getName();
            docId.set(fileName);

            StringTokenizer itr = new StringTokenizer(value.toString());
            while (itr.hasMoreTokens()) {
                String token = itr.nextToken().toLowerCase();
                word.set(token);
                context.write(word, docId);
            }
        }
    }

    public static class InvertedIndexReducer extends Reducer<Text, Text, Text, Text> {

        public void reduce(Text key, Iterable<Text> values, Context context)
                throws IOException, InterruptedException {

            Map<String, Integer> docIdToCountMap = new HashMap<>();
            for (Text value : values) {
                String docId = value.toString();
                if (docIdToCountMap.containsKey(docId)) {
                    docIdToCountMap.put(docId, docIdToCountMap.get(docId) + 1);
                } else {
                    docIdToCountMap.put(docId, 1);
                }
            }

            StringBuilder sb = new StringBuilder();
            for (String docId : docIdToCountMap.keySet()) {
                sb.append(docId).append(":").append(docIdToCountMap.get(docId)).append("\t");
            }
            context.write(key, new Text(sb.toString()));
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "inverted index");

        job.setJarByClass(Invertedindex.class);
        job.setMapperClass(TokenizerMapper.class);
        job.setReducerClass(InvertedIndexReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
ackage invertedindex ;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class Invertedindex {

    public static class TokenizerMapper extends Mapper<Object, Text, Text, Text> {

        private Text word = new Text();
        private Text docId = new Text();

        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {

            String fileName = ((org.apache.hadoop.mapreduce.lib.input.FileSplit) context.getInputSplit()).getPath().getName();
            docId.set(fileName);

            StringTokenizer itr = new StringTokenizer(value.toString());
            while (itr.hasMoreTokens()) {
                String token = itr.nextToken().toLowerCase();
                word.set(token);
                context.write(word, docId);
            }
        }
    }

    public static class InvertedIndexReducer extends Reducer<Text, Text, Text, Text> {

        public void reduce(Text key, Iterable<Text> values, Context context)
                throws IOException, InterruptedException {

            Map<String, Integer> docIdToCountMap = new HashMap<>();
            for (Text value : values) {
                String docId = value.toString();
                if (docIdToCountMap.containsKey(docId)) {
                    docIdToCountMap.put(docId, docIdToCountMap.get(docId) + 1);
                } else {
                    docIdToCountMap.put(docId, 1);
                }
            }

            StringBuilder sb = new StringBuilder();
            for (String docId : docIdToCountMap.keySet()) {
                sb.append(docId).append(":").append(docIdToCountMap.get(docId)).append("\t");
            }
            context.write(key, new Text(sb.toString()));
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "inverted index");

        job.setJarByClass(Invertedindex.class);
        job.setMapperClass(TokenizerMapper.class);
        job.setReducerClass(InvertedIndexReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
