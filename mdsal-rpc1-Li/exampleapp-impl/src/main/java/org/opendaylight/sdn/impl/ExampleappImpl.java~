package org.opendaylight.sdn.impl;

import java.util.concurrent.Future;

import org.opendaylight.controller.sal.binding.api.BindingAwareBroker.ConsumerContext;
import org.opendaylight.controller.sal.binding.api.BindingAwareConsumer;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.exampleapp.api.rev160328.ExampleAppInput;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.exampleapp.api.rev160328.ExampleAppOutput;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.exampleapp.api.rev160328.ExampleAppOutputBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.exampleapp.api.rev160328.ExampleappService;
import org.opendaylight.yangtools.yang.common.RpcResult;
import org.opendaylight.yangtools.yang.common.RpcResultBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.util.concurrent.Futures;

public class ExampleappImpl implements BindingAwareConsumer, ExampleappService {
private static final Logger LOG = LoggerFactory.getLogger(ExampleappImpl.class);
private static long DEFAULT_BANDWIDTH = 50;

private Long bandwidth;

public ExampleappImpl() {
    LOG.info("ExampleAppImpl contructor");
    this.bandwidth = DEFAULT_BANDWIDTH;
}

@Override
public Future<RpcResult<ExampleAppOutput>> exampleApp(ExampleAppInput input) {
    LOG.info("ExampleAppImpl exampleRpc");
    // Build output message
    ExampleAppOutputBuilder outBuilder = new ExampleAppOutputBuilder();
    outBuilder.setBandwidth(input.getBandwidth());
    // Modify bandwidth
    this.bandwidth = input.getBandwidth();
    return Futures.immediateFuture(RpcResultBuilder.success(outBuilder.build()).build());
}

public void close() {
    LOG.info("ExampleAppImpl close");
}

@Override
public void onSessionInitialized(ConsumerContext context) {
    LOG.info("ExampleAppImpl onSessionInitialized");
}

}
