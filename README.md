# PushApi

A sample project to enable push notifications for resource updates.

### Tools
- Java
- Apache CXF
- Jetty
- Java 7
- Apache derby
- RabbitMQ
 
### Design
The Push server is designed as a publish subscribe model implementataion. THe implemntation is backed by a persistent message queue and an exchange for routing.

Each resource that needs to be exposed a  service will create a new Queue with the resource name as the queue name. The exchange in RabbitMq is set in direct mode and based on the resource updates the messages are routed to the specific queue.

The resource updates are pushed as a HTTP post.

The susbscription for notification  requires a valid http endpoint with no security.

One thread listener will be created per resource exposed in the PUSH API.

### High Level model:

![Design high level](https://github.com/pradeepvemulakonda/PushApi/blob/master/doc/Push_API_Design.jpg "High Level design")

#### Registration Sequence

![Register Sequence](https://github.com/pradeepvemulakonda/PushApi/blob/master/doc/Register.jpg "Registration flow")

#### Push Server Sequence

![Push Server Sequence](https://github.com/pradeepvemulakonda/PushApi/blob/master/doc/Push-Server.jpg "Push message flow")

##### Description

- Client Registers with Registry server with a HTTP endpoint creating a subscriber for resource.
- Once the Push Server is started then queues are created for each resource supported in the Resources enum.
- There will be one observer thread on each queue waiting for messages.
- Once a message is placed in the queue the  observer thread gets the subscriber from the registry and passes on the message by triggering a POST message onto the subscriber endpoint.

### Not Covered in the sample
- Error handling



