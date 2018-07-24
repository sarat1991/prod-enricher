package crawler

class CrawlerController {

    private static final String SERVICE_UP = "Crawler service is up and running"
    private static final String ERROR = "Internal Server Error"
    private static final String NOTFOUND = "Not found"

    def index() {
        render SERVICE_UP
    }
    def notFound(){
        render NOTFOUND
    }
    def error(){
        render ERROR
    }

}
