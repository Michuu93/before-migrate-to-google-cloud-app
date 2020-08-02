package pl.michal.backend;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DataService {

    private final RabbitService rabbitService;

    private final MongoService mongoService;

    Data saveInQueue(Data data) {
        data.setId(UUID.randomUUID().toString());
        data.setTimestamp(LocalDateTime.now());
        rabbitService.send(data);
        return data;
    }

    Data findByUuid(String uuid) {
        return mongoService.findByUuid(uuid);
    }

    List<Data> findAll() {
        return mongoService.findAll();
    }

    void deleteAll() {
        mongoService.deleteAll();
    }
}
