# aesh-348

This replicates AESH-348: https://issues.jboss.org/browse/AESH-348

1. Clone the repo:
   $ git clone git@github.com:jmazzitelli/aesh-348.git
2. Build it:
   $ cd aesh-348 && mvn clean install
3. See the test errors for the problems
4. Build it again without tests:
   $ mvn clean install -DskipTests
5. Run the jar with your own combination of --myoption and --abc:
   $ java -jar target/aesh-test-1.jar --myoption --abc foo

That above command will fail with:

````
Exception in thread "main" org.jboss.aesh.cl.parser.OptionParserException: Option: --myoption must be given a value
````

Note if you run with --myoption at the end, it doesn't throw an exception but still isn't right:

````
$ java -jar target/aesh-test-1.jar --abc foo --myoption
hasOption(myoption)=true
abc=foo
hasOption(myoption)=false
myoption=<not set>
````

Notice "hasOption" returns false, even though I did pass in --myoption.
