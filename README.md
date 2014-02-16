Friend stalker app
==============

#Getting started

##How to run application

<pre><code>git clone https://github.com/iTomkinas/friend-stalker.git
cd friend-stalker</code></pre>

### Pre configuration
#### Database connection configuration files
<pre><code>gedit friend-stalker-domain/src/main/resources/application.properties
gedit friend-stalker-domain/src/main/resources/liquibase.properties</code></pre>

#### Facebook app settings
<pre><code>gedit friend-stalker-ui/src/main/java/com/itomkinas/friendStalker/ui/utils/FacebookOAuth.java</code></pre>

Set your facebook APP_ID and APP_SECRET. You can create your app at [Facebook developers](https://developers.facebook.com/)

### Starting application
<pre><code>mvn clean install
cd friend-stalker-ui
mvn jetty:run
</code></pre>

