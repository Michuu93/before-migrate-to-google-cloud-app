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
        log.info("> saveInQueue [data={}]", data);
        data.setId(UUID.randomUUID().toString());
        data.setTimestamp(LocalDateTime.now());
        rabbitService.send(data);
        log.info("< saveInQueue [data={}]", data);
        return data;
    }

    Data findByUuid(String uuid) {
        log.info("> findByUuid [uuid={}]", uuid);
        Data data = mongoService.findByUuid(uuid);
        log.info("< findByUuid [data={}]", data);
        return data;
    }

    List<Data> findAll() {
        log.info("> findAll");
        List<Data> data = mongoService.findAll();
        log.info("< findAll [data={}]", data);
        return data;
    }

    void deleteAll() {
        log.info("> deleteAll");
        mongoService.deleteAll();
        log.info("< deleteAll");
    }
}
