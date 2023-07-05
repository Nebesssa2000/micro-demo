package ru.javabegin.micro.demo.eurekaclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javabegin.micro.demo.eurekaclient.repository.Address;
import ru.javabegin.micro.demo.eurekaclient.repository.UserDto;

@RestController
@RequestMapping("/main")
public class WebController {

    @Value("${eureka.instance.instance-id}")
    private String id;
//
//    @Value("${tmpVar}")
//    private int tmpVar;

    @Autowired
    private KafkaTemplate<Double, UserDto> kafkaTemplate;

    @GetMapping("/web")
    public String web(){
        return "webAPI" + id;
    }

    @Async
    @PostMapping
    public void sendMsg(Double msgId, UserDto msg) {
        msg.setAge(23L);
        msg.setName("Bob");
        msg.setAddress(new Address("USA", "Chg", "5Avenue", 2L, 3L));
        kafkaTemplate.send("msg1", msgId, msg)
                .handle((ms ,throwable) -> msg != null ? msg : "Hello, Exception");
        kafkaTemplate.flush();
    }
}
