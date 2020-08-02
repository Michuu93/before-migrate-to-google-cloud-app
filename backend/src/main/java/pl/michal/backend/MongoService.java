package pl.michal.backend;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MongoService {
    private final MongoOperations mongoOperations;

    void saveInDatabase(Data data) {
        try {
            mongoOperations.insert(data);
            log.info("Saved data in database");
        } catch (DuplicateKeyException ignore) {
            log.warn("Duplicated data from queue ignored");
        } catch (Exception e) {
            log.error("Error saving data in database", e);
            throw e;
        }
    }

    Data findByUuid(String uuid) {
        return mongoOperations.findById(uuid, Data.class);
    }

    List<Data> findAll() {
        return mongoOperations.findAll(Data.class);
    }

    void deleteAll() {
        mongoOperations.dropCollection(Data.class);
    }
}
