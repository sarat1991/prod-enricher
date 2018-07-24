package metadata

class BootStrap {
   def rabbitQueueService

    def init = { servletContext ->
        rabbitQueueService.bootstrap()
        rabbitQueueService.regReceiveHandler()
    }
    def destroy = {
    }
}
