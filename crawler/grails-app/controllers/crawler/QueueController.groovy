package crawler

class QueueController {

    def queueService

    def index() {

        queueService.bootStrap()
        render "bootstrap"
    }
}
