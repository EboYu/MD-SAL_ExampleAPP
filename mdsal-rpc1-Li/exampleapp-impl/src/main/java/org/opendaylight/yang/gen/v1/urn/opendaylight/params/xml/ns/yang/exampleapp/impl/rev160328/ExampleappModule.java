package org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.exampleapp.impl.rev160328;

import org.opendaylight.controller.sal.binding.api.BindingAwareBroker;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.exampleapp.api.rev160328.ExampleappService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.opendaylight.sdn.impl.ExampleappImpl;
public class ExampleappModule extends org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.exampleapp.impl.rev160328.AbstractExampleappModule {

    private static final Logger LOG = LoggerFactory.getLogger(ExampleappModule.class);
    public ExampleappModule(org.opendaylight.controller.config.api.ModuleIdentifier identifier, org.opendaylight.controller.config.api.DependencyResolver dependencyResolver) {
        super(identifier, dependencyResolver);
    }

    public ExampleappModule(org.opendaylight.controller.config.api.ModuleIdentifier identifier, org.opendaylight.controller.config.api.DependencyResolver dependencyResolver, org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.exampleapp.impl.rev160328.ExampleappModule oldModule, java.lang.AutoCloseable oldInstance) {
        super(identifier, dependencyResolver, oldModule, oldInstance);
    }

    @Override
    public void customValidation() {
        // add custom validation form module attributes here.
    }

    @Override
    public java.lang.AutoCloseable createInstance() {
        // TODO:implement
        LOG.info("ExampleappModule Create Instance.");
        final ExampleappImpl exampleApp = new ExampleappImpl();

        LOG.info("ExampleappModule Regiser RPC.");
        @SuppressWarnings("unused")
        final BindingAwareBroker.RpcRegistration<ExampleappService> rpcRegistration = getRpcRegistryDependency()
                .addRpcImplementation(ExampleappService.class, exampleApp);

        getBrokerDependency().registerConsumer(exampleApp, null);

        // Wrap exampleApp as AutoCloseable and close registrations to md-sal at
        // close(). The close method is where you would generally clean up
        // thread pools
        // etc.
        final class AutoCloseableExampleApp implements AutoCloseable {

            @Override
            public void close() throws Exception {
                exampleApp.close();
            }
        }
        return new AutoCloseableExampleApp();
    }

}
