package org.ws.rm;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import org.apache.cxf.feature.Features;

@WebService(portName = "WsrmHelloServicePort", serviceName = "org.ws.rm.WsrmHelloService", targetNamespace = "https://quarkiverse.github.io/quarkiverse-docs/quarkus-cxf/test/ws-rm", endpointInterface = "org.ws.rm.WsrmHelloService")
@Features(classes = { RMStoreFeature.class })
public class WsrmHelloServiceImpl implements WsrmHelloService {

    @WebMethod
    @Override
    public String sayHello() {
        return "WS-ReliableMessaging Hello World! seqSize: " + RMStoreCheckInterceptor.seqSize;
    }

}
