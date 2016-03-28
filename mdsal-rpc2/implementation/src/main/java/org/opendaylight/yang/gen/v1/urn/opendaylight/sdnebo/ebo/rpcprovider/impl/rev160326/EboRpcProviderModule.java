package org.opendaylight.yang.gen.v1.urn.opendaylight.sdnebo.ebo.rpcprovider.impl.rev160326;
import org.opendaylight.controller.sal.binding.api.BindingAwareBroker.RpcRegistration;
import org.opendaylight.sdnebo.EboRpcProviderImpl;
import org.opendaylight.yang.gen.v1.urn.opendaylight.sdnebo.rpcprovider.rev160326.EboRpcproviderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EboRpcProviderModule extends org.opendaylight.yang.gen.v1.urn.opendaylight.sdnebo.ebo.rpcprovider.impl.rev160326.AbstractEboRpcProviderModule {

  //create logger.
  private final static Logger _logger = LoggerFactory.getLogger(EboRpcProviderModule.class);

  public EboRpcProviderModule(org.opendaylight.controller.config.api.ModuleIdentifier identifier, org.opendaylight.controller.config.api.DependencyResolver dependencyResolver) {
    super(identifier, dependencyResolver);
  }

  public EboRpcProviderModule(org.opendaylight.controller.config.api.ModuleIdentifier identifier, org.opendaylight.controller.config.api.DependencyResolver dependencyResolver, org.opendaylight.yang.gen.v1.urn.opendaylight.sdnebo.ebo.rpcprovider.impl.rev160326.EboRpcProviderModule oldModule, java.lang.AutoCloseable oldInstance) {
    super(identifier, dependencyResolver, oldModule, oldInstance);
  }

  @Override
  public void customValidation() {
    // add custom validation form module attributes here.
  }

  @Override
  public java.lang.AutoCloseable createInstance() {
    _logger.info("EboRcpProvider is called.");

    EboRpcProviderImpl eboRpcProviderImpl = new EboRpcProviderImpl();
    eboRpcProviderImpl.setContentsSwitchFlag(getContentsSwitchFlag());
    final RpcRegistration<EboRpcproviderService> eboRpcproviderService = getRpcRegistryDependency().addRpcImplementation(EboRpcproviderService.class, eboRpcProviderImpl);

    final class CloseResources implements AutoCloseable {
    @Override
      public void close() throws Exception {
        eboRpcproviderService.close();
        _logger.info("EboRpcProvider was closed.", this);
      }
    }

    AutoCloseable ret = new CloseResources();
    return ret;
  }

}
