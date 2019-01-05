# pip_kp
Course project for pip

Quick start

So, we have Spring Boot in our project now. Just find Application class in packet com.pip and run its main method :)

Go to [http://localhost:9000/kp](http://localhost:9000/kp) to test connection.

### Database config

The default parameters of connection are defined in the file ```connection.properties```. 
You can create database with this credentials or use your own (from environment variables).

#### How?

In your Intellij:
```Run -> Edit Configurations -> (chose your started configuration of application) -> Configuration tab -> Environment -> Environment variables```

You go to define three variables: URL, USER and PASSWORD without any "" or '' or other brackets.

Write @PixarV if smth spits an exception in your face.
