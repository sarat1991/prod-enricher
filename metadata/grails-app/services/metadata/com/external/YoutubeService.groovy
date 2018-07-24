package metadata.com.external

import com.google.api.client.googleapis.json.GoogleJsonResponseException
import com.google.api.client.http.HttpRequest
import com.google.api.client.http.HttpRequestInitializer
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.youtube.YouTube
import com.google.api.services.youtube.model.ResourceId
import com.google.api.services.youtube.model.SearchListResponse
import com.google.api.services.youtube.model.SearchResult
import com.traits.ExternalService

class YoutubeService implements ExternalService {

    private static final long NUMBER_OF_VIDEOS_RETURNED = 10;
    def grailsApplication

    YoutubeService(grailsApplication){
        this.grailsApplication = grailsApplication
    }

    private static YouTube youtube;

    @Override
    List get(String productIdentifier) {

        try {
            youtube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), new HttpRequestInitializer() {
                public void initialize(HttpRequest request) throws IOException {
                }
            }).setApplicationName("youtube-cmdline-search-sample").build();
            String queryTerm = productIdentifier;
            YouTube.Search.List search = youtube.search().list("id,snippet");
            String apiKey = grailsApplication.config.youtube.apikey
            search.setKey(apiKey);
            search.setQ(queryTerm);
            search.setRelevanceLanguage("en")
            search.setType("video");
            search.setFields("items(id/kind,id/videoId,snippet/title,snippet/description)");
            search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
            SearchListResponse searchResponse = search.execute();
            List<SearchResult> searchResultList = searchResponse.getItems();
            List response = null
            if (searchResultList != null) {
                response = parseResponse(searchResultList);
            }
            return response
        } catch (GoogleJsonResponseException e) {
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());
        } catch (IOException e) {
            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    List parseResponse(List<SearchResult> searchResultList) {
        def response = []
        searchResultList.each {
            def videoItem = [:]
            ResourceId rId = it.getId();
            if (rId.getKind().equals("youtube#video")) {
                videoItem.put("id", rId.getVideoId())
                videoItem.put("title", it.getSnippet().getTitle())
                videoItem.put("description", it.getSnippet().getDescription())
            }
            response << videoItem
        }
        return response
    }
}
