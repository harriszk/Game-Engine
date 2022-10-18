Driver.class: Driver.java
	javac Driver.java

run_driver: Driver.class
	java Driver

clean:
	rm *.class