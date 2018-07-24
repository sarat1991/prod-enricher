package crawler

import com.rabbitmq.client.*;

class QueueService {

    def crawlService
    def grailsApplication

    private final String RECEIVER_QUEUE_NAME = "Product";
    private final String SENDER_QUEUE_NAME = "Details";
    Connection connection
    Channel channel
    def bootStrap() {
        ConnectionFactory factory = new ConnectionFactory()
        factory.setHost(grailsApplication.config.queue.host);
        connection = factory.newConnection()
        channel = connection.createChannel()
    }

    void receive(){
        channel.queueDeclare(RECEIVER_QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages");
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String productUrl = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + productUrl + "'");
                def response = crawlService.getProductDetails(productUrl)
                if(response == null)
                    response = [category: "invalidCategory"]
                response << [productUrl : productUrl]
                postMessage(response)
            }
        }
        channel.basicConsume(RECEIVER_QUEUE_NAME, true, consumer)
    }

    def postMessage(def message){
        channel = connection.createChannel()
        channel.queueDeclare(SENDER_QUEUE_NAME, false, false, false, null)
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(message);
        channel.basicPublish("", SENDER_QUEUE_NAME, null, byteOut.toByteArray());
        System.out.println(" [x] Sent '" + message + "'");


    }

}
