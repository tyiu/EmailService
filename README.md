EmailService
============

RESTful backend service that sends emails via an arbitrary email service provider from a set of providers.

Tech Stack
==========

Java 8

Dropwizard - https://dropwizard.github.io/dropwizard/

Dropwizard includes the following:
* Jetty for HTTP
* Jersey for REST
* Jackson for JSON

Maven for packaging

Why Dropwizard?
===============

Dropwizard compiles powerful and widely used Java libraries used for developing RESTful web services.

They are well documented and I have some experience with them from working on previous Java web services.

How to Deploy
=============

* Checkout source code
* Type: mvn package
* Type: java -jar target/emailservice-0.0.1-SNAPSHOT.jar server email-config.yml

/email/send Endpoint
====================

* HTTP POST
* Expects application/json payload
* Example: {"subject":"<subject string>", "message":"<plain-text-message string>", "from":{"name":"<name string>", "emailAddress":"<email@address.com>"}, "to":[{"name":"<name string>", "emailAddress":"<email@address.com>"}, {"name":"<name string>", "emailAddress":"<email@address.com>"}], "cc":[{"name":"<name string>", "emailAddress":"<email@address.com>"}, {"name":"<name string>", "emailAddress":"<email@address.com>"}], "bcc":[{"name":"<name string>", "emailAddress":"<email@address.com>"}, {"name":"<name string>", "emailAddress":"<email@address.com>"}]}
* Note: You can have multiple to, cc, and bcc recipients
* Note: cc and bcc are optional. Everything else is required.

What Works And What Doesn't
===========================

This service can receive HTTP POST requests to the /email/send endpoint and will attempt to forward it
to an actual email service provider.

However, none of the forwarded requests work at this current time due to either incorrect configured
account settings on their sites or incorrect requests sent to their services.

There are no health checks to determine the availability of the service.

There is also a lack of unit tests and validation tests. 

Contact
=======

https://www.linkedin.com/in/terryyiu
