# PushApi

A sample project to enable push notifications for resource updates.

# Tools
- Java
- Apache CXF
- Jetty
- Java 7
- Apache derby
- RabbitMQ
 
# Design
The Push server is designed as a publish subscribe model implementataion. THe implemntation is backed by a persistent message queue and an exchange for routing.

Each resource that needs to be exposed a  service will create a new Queue with the resource name as the queue name. The exchange in RabbitMq is set in direct mode and based on the resource updates the messages are routed to the specific queue.

The resource updates are pushed as a HTTP post.

The susbscription for notification  requires a valid http endpoint with no security.

One thread listener will be created per resource exposed in the PUSH API.

High Level model:






