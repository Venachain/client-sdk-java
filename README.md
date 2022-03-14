# Overview
> Venachain-SDK-Java is a Java development kit for interacting with Venachain.

# Build
```
git clone https://github.com/Venachain/client-sdk-java.git
cd client-sdk-java/
./gradlew clean jar            //Generate jar package
./gradlew clean distZip        //Generate code generation skeleton tool   
``` 

# Use

* import compiled .jar file into local maven cache(~/.m2) manually
* config maven or gradle in project

```
<dependency>
    <groupId>com.venachain.client</groupId>
    <artifactId>core</artifactId>
    <version>1.0.1</version>
</dependency>
```

or

```
compile "com.venachain.client:core:1.0.1"
```

* use in project

```
Web3j web3 = Web3j.build(new HttpService("https://host:port"));
```

