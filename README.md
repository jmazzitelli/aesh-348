# aesh-348

This replicates AESH-348: https://issues.jboss.org/browse/AESH-348

1. Clone the repo and go to its directory:
   $ git clone git@github.com:jmazzitelli/aesh-348.git && cd aesh-348
2. Build it:
   $ mvn clean install -Daesh.version=0.66.5
3. See the 2 test errors for the problems
4. Build it with the latest snapshot:
   $ mvn clean install -Daesh.version=0.66.7-SNAPSHOT
5. See the 1 test error for the remaining problem
6. Build it again without tests:
   $ mvn clean install -DskipTests -Daesh.version=0.66.7-SNAPSHOT
7. Run the jar with your own combination of --myoption and --abc for manual testing:
   $ java -jar target/aesh-test-1.jar --abc foo --myoption

Note if you run with --myoption at the end, it doesn't throw an exception but still isn't right:

````
$ java -jar target/aesh-test-1.jar --abc foo --myoption
hasOption(myoption)=true
abc=foo
hasOption(myoption)=false
myoption=<not set>
````

Notice "hasOption" returns false, even though I did pass in --myoption. (see the unit test failure)

But if myoption is specified first, it works (with 0.66.7-SNAPSHOT, not with anything earlier):

````
$ java -jar target/aesh-test-1.jar --myoption --abc foo
hasOption(myoption)=true
abc=foo
hasOption(myoption)=true
myoption=
````
