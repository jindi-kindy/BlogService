package cn.withzz.blog;


import java.util.HashSet;
import java.util.Set;

import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;



public class ExamplesConfig implements ServerApplicationConfig {

    @Override
    public Set<ServerEndpointConfig> getEndpointConfigs(
            Set<Class<? extends Endpoint>> scanned) {

        Set<ServerEndpointConfig> result = new HashSet<>();
//
//        if (scanned.contains(EchoEndpoint.class)) {
//            result.add(ServerEndpointConfig.Builder.create(
//                    EchoEndpoint.class,
//                    "/websocket/echoProgrammatic").build());
//        }

        if (scanned.contains(DrawboardEndpoint.class)) {
            result.add(ServerEndpointConfig.Builder.create(
                    DrawboardEndpoint.class,
                    "/drawboard").build());
        }

        return result;
    }


    @Override
    public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> scanned) {
        // Deploy all WebSocket endpoints defined by annotations in the examples
        // web application. Filter out all others to avoid issues when running
        // tests on Gump
        Set<Class<?>> results = new HashSet<>();
        for (Class<?> clazz : scanned) {
            if (clazz.getPackage().getName().startsWith("cn.withzz.blog")) {
                results.add(clazz);
            }
        }
        return results;
    }
}
