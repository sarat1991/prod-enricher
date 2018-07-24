package crawler

class BootStrap {
    def queueService

    def init = { servletContext ->
        queueService.bootStrap()
        queueService.receive()
    }
    def destroy = {
    }
}
