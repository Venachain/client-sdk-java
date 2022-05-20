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

* [Download](https://github.com/Venachain/client-sdk-java/releases) compiled .jar files to local directory and import them in your project in IDE configuration.  
* Alternatively, you can import from our maven repository. URL: http://maven.wxblockchain.org:8081/repository/maven-public/
* maven
   ```xml
   <dependency>
        <groupId>com.venachain.sdk</groupId>
        <artifactId>core</artifactId>
        <version>1.0.1-SNAPSHOT</version>
   </dependency>
  ```
* gradle
```integrationperformancetest
implementation 'com.venachain.sdk:core:1.0.1-SNAPSHOT'
```
* Import dependencies `abi`, `crypto`, `utils`, `tuples`, `rlp` same way as above when necessary. 