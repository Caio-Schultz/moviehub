package br.com.caioschultz.MovieHub.mapper;

import br.com.caioschultz.MovieHub.controller.request.StreamingRequest;
import br.com.caioschultz.MovieHub.controller.response.StreamingResponse;
import br.com.caioschultz.MovieHub.entity.Streaming;
import org.springframework.stereotype.Component;

@Component
public class StreamingMapper {


    public Streaming toStreaming(StreamingRequest streamingRequest){
        Streaming streaming = new Streaming();
        streaming.setName(streamingRequest.name());

        return streaming;
    }

    public StreamingResponse toResponse(Streaming streaming){

        StreamingResponse response = new StreamingResponse(streaming.getId(), streaming.getName());

        return response;
    }
}
