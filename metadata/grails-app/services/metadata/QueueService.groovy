package metadata

import com.rabbitmq.client.*;

class QueueService {

    Channel channel
    Connection connection
    def productInfoService
    private final String SENDER_QUEUE_NAME = "Product"
    private final String RECEIVER_QUEUE_NAME = "Details"

    def bootstrap() {
        ConnectionFactory factory = new ConnectionFactory()
        factory.setHost("localhost")
        connection = factory.newConnection()
        channel = connection.createChannel()
        receive()
    }

    def postMessage(String message){
        channel = connection.createChannel()
        channel.queueDeclare(SENDER_QUEUE_NAME, false, false, false, null)
        channel.basicPublish("", SENDER_QUEUE_NAME, null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

    }

    private void receive(){
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
