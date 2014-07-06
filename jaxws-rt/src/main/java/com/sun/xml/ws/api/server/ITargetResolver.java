package com.sun.xml.ws.api.server;

import java.util.Map;

/**
 * Created by Somebody on 22.06.14.
 */
public interface ITargetResolver {

    public <T> T resolve(Class<T> clazz) throws InstantiationException, IllegalAccessException;

}
