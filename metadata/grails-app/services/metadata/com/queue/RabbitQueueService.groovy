package metadata.com.queue

import com.rabbitmq.client.*
import com.traits.MessageQueue

class RabbitQueueService implements MessageQueue {

    Channel channel
    Connection connection
    def productInfoService
    def grailsApplication
    private final String SENDER_QUEUE_NAME = "Product"
    private final String RECEIVER_QUEUE_NAME = "Details"


    def bootstrap() {
        String host = grailsApplication.config.queue.host
        ConnectionFactory factory = new ConnectionFactory()
        factory.setHost(host)
        connection = factory.newConnection()
        channel = connection.createChannel()
    }

    def postMessage(String message){
        channel = connection.createChannel()
        channel.queueDeclare(SENDER_QUEUE_NAME, false, false, false, null)
        channel.basicPublish("", SENDER_QUEUE_NAME, null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

    }

    void regReceiveHandler(){
        channel.queueDeclare(RECEIVER_QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages");
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                ByteArrayInputStream byteIn = new ByteArrayInputStream(body);
                ObjectInputStream inputStream = new ObjectInputStream(byteIn)
                Map productDetails = (Map)inputStream.readObject()
                System.out.println(" [x] Received '" + productDetails + "'");
                productInfoService.productDetailHandler(productDetails)
            }
        }
        channel.basicConsume(RECEIVER_QUEUE_NAME, true, consumer)
    }

}
