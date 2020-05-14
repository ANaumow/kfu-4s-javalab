package ru.naumow.components.resolvers.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ValueConstants;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.annotation.AbstractNamedValueMethodArgumentResolver;
import org.springframework.web.servlet.HandlerMapping;
import ru.naumow.annotation.CurrentBlog;
import ru.naumow.services.BlogService;

import java.util.Map;

@Component
public class CurrentBlogMethodArgumentResolver extends AbstractNamedValueMethodArgumentResolver {

    @Autowired
    private BlogService blogService;

    @Override
    protected NamedValueInfo createNamedValueInfo(MethodParameter parameter) {
        CurrentBlog ann = parameter.getParameterAnnotation(CurrentBlog.class);
        Assert.state(ann != null, "No PathVariable annotation");
        return new CurrentBlogNamedValueInfo(ann);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Object resolveName(String name, MethodParameter parameter, NativeWebRequest request) throws Exception {
        /*Map<String, String> uriTemplateVars = (Map<String, String>) request.getAttribute(
                HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE,
                RequestAttributes.SCOPE_REQUEST
        );
        if (uriTemplateVars != null)
            return blogService.getBlogByAlias(uriTemplateVars.get(name));*/
        return null;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CurrentBlog.class);
    }

    private static class CurrentBlogNamedValueInfo extends NamedValueInfo {
        public CurrentBlogNamedValueInfo(CurrentBlog annotation) {
            super(annotation.name(), true, ValueConstants.DEFAULT_NONE);
        }
    }

}
