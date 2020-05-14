<#-- @ftlvariable name="posts" type="java.util.List<ru.naumow.dto.PostDto>" -->
<div>
    <!-- Start: Post-holder -->
    <#list posts as post>
        <#if post.type = "s">
            <#include "post.ftl">
        </#if>
    </#list>
    <!-- End: Post-holder -->
</div>