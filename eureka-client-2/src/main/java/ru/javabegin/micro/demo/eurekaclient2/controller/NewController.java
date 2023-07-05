package ru.javabegin.micro.demo.eurekaclient2.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javabegin.micro.demo.eurekaclient2.repository.Address;
import ru.javabegin.micro.demo.eurekaclient2.repository.UserDto;

@RestController
@RequestMapping("/new")
public class NewController {

    @Value("${eureka.instance.instance-id}")
    private String id;

    @Autowired
    private KafkaTemplate<Double, UserDto> kafkaTemplate;

    @GetMapping("/web")
    public String newWeb() {
        return "newWeb" + id;
    }

    @Async
    @PostMapping
    public void sendMsg(Double msgId, UserDto msg) {
        msg.setAge(56L);
        msg.setName("Vasya");
        msg.setAddress(new Address("RUS", "Msk", "Lenina", 1L, 1L));
        kafkaTemplate.send("msg", msgId, msg)
                .handle((ms ,throwable) -> msg != null ? msg : "Hello, Exception");
        kafkaTemplate.flush();
    }
}
