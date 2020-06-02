package ru.naumow.components.stomp;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import ru.naumow.events.PostCreatedEvent;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class BlogUpdateStompHandler implements ApplicationListener<PostCreatedEvent> {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private Configuration configuration;

    @Override
    public void onApplicationEvent(PostCreatedEvent event) {
        try {
            Template template = configuration.getTemplate("/resources/ftl/post.ftl");
            Map<String, Object> data = new HashMap<>();
            data.put("post", event.getPostDto());
            StringWriter stringWriter = new StringWriter();
            template.process(data, stringWriter);
            messagingTemplate.convertAndSend("/blog/update/" + event.getBlogAlias(), stringWriter);
        } catch (IOException | TemplateException ioException) {
            throw new IllegalStateException(ioException);
        }
    }
}
