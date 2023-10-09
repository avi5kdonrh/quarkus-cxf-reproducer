package org.ws.rm;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import org.apache.cxf.feature.Features;

@WebService(targetNamespace = "https://quarkiverse.github.io/quarkiverse-docs/quarkus-cxf/test/ws-rm")
@Features(classes = { RMStoreFeature.class })
public class WsrmHelloServiceImpl implements WsrmHelloService {

    @WebMethod
    @Override
    public String sayHello() {
        return "WS-ReliableMessaging Hello World! seqSize: " + RMStoreCheckInterceptor.seqSize;
    }

}
