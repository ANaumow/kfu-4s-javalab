package ru.naumow.components.awares;

public interface BlogAliasAware {

    /**
     * Method will be invoked after AuthenticationManager.authenticate()
     * @param alias is user's blog alias
     */
    void setBlogAlias(String alias);

}
