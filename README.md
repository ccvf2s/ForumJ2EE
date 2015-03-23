# ForumJ2EE
ForumJ2EE is a small application built with Spring mvc, SpringSecurity and Hibernate in NetBeans IDE with GlassFish server.

## Installation

```bash
$ git clone https://github.com/ccvf2s/ForumJ2EE
```

* Open the application with NetBeans IDE
* Add jar file for spring security http://mvnrepository.com/artifact/org.springframework.security
* Create a database named FORUMDB with username and password FORUMDB
* Execute command which is in the file DATABASE/BD_FORUM_DERBY.sql in Derby

When you will execute this sql, two users will be created:

First user is a simple user with status = "ROLE_USER"

username: ccvf2s
password: ccvf2s

Second user is a admin user with status= "ROLE_USER, ROLE_ADMIN"

username: ccvf2s_admin
password: ccvf2s_admin

You can now run the application and enjoy!!!


