mvn compile
mvn clean package

spark-submit \
	--class 'com.example.app.Pipe' 	\
	target/spark_py_ex-1.0.jar