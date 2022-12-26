package com.taycon.mark.chat.config;

import com.taycon.mark.chat.service.ChatService;
import com.taycon.mark.chat.service.ChatServiceWebSocketHandler;
import com.taycon.mark.chat.service.DefaultChatService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class ChatServerConfig extends SpringBootServletInitializer {


    @Bean
    public ChatService chatService() {
        return new DefaultChatService();
    }

    @Bean(name="/chat")
    public WebSocketHandler chatServiceWebSocketHandler() {
        return new ChatServiceWebSocketHandler(chatService());
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ChatServerConfig.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ChatServerConfig.class);
    }

}
