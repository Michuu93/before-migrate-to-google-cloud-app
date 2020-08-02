package pl.michal.backend;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitService {
    private final RabbitTemplate rabbitTemplate;

    private final MongoService mongoService;

    @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    void listen(Data data) {
        log.info("Received data from queue [queue={}, data={}]", RabbitConfig.QUEUE_NAME, data);
        mongoService.saveInDatabase(data);
    }

    void send(Data data) {
        log.info("Send data to queue [queue={}, data={}]", RabbitConfig.QUEUE_NAME, data);
        rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_NAME, data);
    }
}
