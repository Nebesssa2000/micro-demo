package ru.javabegin.micro.demo.eurekaclient.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.javabegin.micro.demo.eurekaclient.config.KafkaProducerConfig;
import ru.javabegin.micro.demo.eurekaclient.repository.Address;
import ru.javabegin.micro.demo.eurekaclient.repository.UserDto;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockRestServiceServer
@AutoConfigureMockMvc
public class WebControllerTest {

    @Autowired
    private KafkaProducerConfig producerConfig;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void sendMsgTest() throws Exception {
        Double msgId = 2.0;
        UserDto msg = getUserDto();

        mockMvc.perform(
                post("/main")
                        .content(String.valueOf(producerConfig.kafkaTemplate()
                                .send("msg1",msgId, msg)))
                        .content(String.valueOf(MediaType.APPLICATION_JSON))
        )
                .andExpect(status().is(200));

    }

    private UserDto getUserDto() {
        Address address = new Address("USA2", "Ch4g", "5Ave8nue", 29L, 63L);
        UserDto userDto = new UserDto(address);
        userDto.setAge(27L);
        userDto.setName("Bobik");
        return userDto;
    }
}
