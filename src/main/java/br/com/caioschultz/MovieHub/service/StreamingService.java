package br.com.caioschultz.MovieHub.service;

import br.com.caioschultz.MovieHub.controller.request.StreamingRequest;
import br.com.caioschultz.MovieHub.controller.response.StreamingResponse;
import br.com.caioschultz.MovieHub.dto.StreamingMapper;
import br.com.caioschultz.MovieHub.entity.Streaming;
import br.com.caioschultz.MovieHub.repository.StreamingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StreamingService {

    private final StreamingRepository repository;
    private final StreamingMapper streamingMapper;

    public StreamingService(StreamingRepository repository, StreamingMapper streamingMapper) {
        this.repository = repository;
        this.streamingMapper = streamingMapper;
    }

    public List<StreamingResponse> getAllStreamings(){
            List<Streaming> streamings = repository.findAll();
            if (streamings.isEmpty()){
                return null;
            }
            else {
                return streamings.stream()
                        .map(streaming -> streamingMapper.toResponse(streaming))
                        .collect(Collectors.toList());
            }
    }

    public StreamingResponse getStreamingById(Long id){
        Optional<Streaming> streaming = repository.findById(id);
        return streaming.map(response -> streamingMapper.toResponse(response))
                .orElse(null);
    }

    public StreamingResponse create(StreamingRequest request){
        Streaming streaming = repository.save(streamingMapper.toStreaming(request));
        StreamingResponse response = streamingMapper.toResponse(streaming);
        return response;
    }

    public StreamingResponse update(StreamingRequest request, Long id){
        if(repository.findById(id).isPresent()){
            Streaming streaming = streamingMapper.toStreaming(request);
            streaming.setId(id);
            Streaming savedStreaming = repository.save(streaming);
            StreamingResponse updatedStreaming =  streamingMapper.toResponse(savedStreaming);
            return updatedStreaming;
        }
        else {
            return null;
        }
    }

    public void delete(Long id){
        repository.deleteById(id);
    }



}
