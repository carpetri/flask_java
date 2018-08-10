

#Start flask in the background
python flask_example.py &

# Clean class files before compiling
if [ -a  *.class ] 
then
	rm -f *class
fi

# Compile
javac -classpath json-simple-1.1.1.jar Example.java

# Execute example
java -classpath json-simple-1.1.1.jar:. Example

# End the server
kill %%
