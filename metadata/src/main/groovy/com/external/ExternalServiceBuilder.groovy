package com.external

import metadata.com.external.YoutubeService

class ExternalServiceBuilder {

    public static String YOUTUBE = "youtube"

     static def create(String type, grailApplication){
        switch (type){
            case YOUTUBE:
                return new YoutubeService(grailApplication);
        }

    }
}
