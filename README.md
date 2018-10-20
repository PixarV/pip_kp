# pip_kp
Course project for pip

Quick start

### Set up JBoss Server

1. Get it [here](http://wildfly.org/downloads/), you need **14.0.1.Final** version of **Java EE Full & Web Distribution**
2. Unzip | tar this archive into your working directory
3. Open our best project in Intellij IDEA and do this staff:
    * **Add app server**: File -> Settings -> Build, Execution... -> Application Servers -> + JBoss -> JBoss Home: insert path
    to your unpacked archive
    * **Create war**: View -> Tool Windows -> Gradle -> Tasks -> build -> war
    * **Configure run**: Run -> Edit Configuration -> + JBoss Server -> Local -> customize your settings if you want
    * **Deploy war**: ^ -> Deployment -> + Artifact -> choose not exploded war
    
    **Important note** : if IDEA is trying to say / offer smth to you: say 'Yes'.

### Configure database connection
0. If you want to run server only without any database staff: comment line 
    `<jta-data-source>java:jboss/datasources/PostgresDS</jta-data-source>` in `persistence.xml` 
    and goto **Run** part
1. We use and love PostgreSQL, so if you have linux [this guide](https://wiki.archlinux.org/index.php/PostgreSQL) 
for you
2. After installing you need to create some example of table // but i think this things change so fast. 
We use userr (look at `UserEntity.java`)
3. Some JBoss magic:
    * download jar JDBC driver for postgres [here](https://jdbc.postgresql.org/download.html), version **42.2.5** sounds good, 
    JDBC **4.2**
    * open home directory of your JBoss and go to path: `wildfly-14.0.1.Final/modules/system/layers/base`
    and create `org/postgresql/main` here
    * move driver jar into main
    * create: `module.xml` here with:  
    ```
    <?xml version="1.0" encoding="UTF-8"?>    
     <module xmlns="urn:jboss:module:1.1" name="org.postgresql">
         <resources> 
             <resource-root path="postgresql-42.2.5.jar"/>
         </resources>
         <dependencies>
             <module name="javax.api"/>
             <module name="javax.transaction.api"/>
         </dependencies>
     </module>
     ```
    * go to `wildfly-14.0.1.Final/standalone/configuration/`
    * add this to `standalone.xml` between `<datasources>` tags:
    ```
    <datasource jndi-name="java:jboss/datasources/PostgresDS" pool-name="PostgresDS" enabled="true" use-java-context="true">
        <connection-url>jdbc:postgresql://localhost:5432/pip</connection-url>
        <driver>postgresql</driver>
        <security>
            <user-name>USER</user-name>
            <password>PASSWORD</password>
        </security>
    </datasource>
    <drivers>
        <driver name="h2" module="com.h2database.h2">
            <xa-datasource-class>org.h2.jdbcx.JdbcDataSource</xa-datasource-class>
        </driver>
        <driver name="postgresql" module="org.postgresql">
            <xa-datasource-class>org.postgresql.xa.PGXADataSource</xa-datasource-class>
        </driver>
    </drivers>
    ```
    `<drivers>` part should rewrite earlier version with only h2.
    
    * put your credentials between tags `<user-name>` and `<password>`
    
### Run

Go to your IDEA and click on Run button. Write @PixarV if smth spits an exception in your face.

Good luck!

P.S. 
1. You should install Lombok plugin in your IDEA and enable annotation processing by this way:
File -> Settings -> Build, Execution... -> Compile -> Annotation Processors -> Enable...
2. If you want to have an access to root of JBoss server:
    * run `wildfly-14.0.1.Final/bin/add-user.sh`
    * go to http://localhost:9990/console/index.html with your credentials