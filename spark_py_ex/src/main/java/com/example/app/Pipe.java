package com.example.app;

import com.example.app.ExObject;
import java.util.Arrays;
import java.util.List;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkFiles;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class Pipe {

    public static void main(String[] args) {

        ExObject o1 = new ExObject("one",3.2);
        ExObject o2 = new ExObject("two",2.124);
        SparkConf conf = new SparkConf().setAppName("Pipe").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);

        String pyScript = "script.py";
        sc.addFile(pyScript);

        JavaRDD<String> lines = sc.parallelize( Arrays.asList( o1.toJSON(), o2.toJSON()));
        List<String> pyOut = lines.pipe(SparkFiles.get(pyScript)).collect();

        System.out.println(pyOut);

        sc.stop();
    }
}