package ru.naumow.aspects;

import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.stereotype.Component;
import ru.naumow.model.UserSessionData;

/*@Component("processor")*/
public class TestObjectPostProcessor implements ObjectPostProcessor<ProviderManager> {
    @Override
    public <O extends ProviderManager> O postProcess(O object) {
        System.out.println("sss");
        return object;
    }
}
