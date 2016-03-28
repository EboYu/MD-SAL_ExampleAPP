package org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.exampleapp.api.rev160328;
import java.util.concurrent.Future;
import org.opendaylight.yangtools.yang.binding.RpcService;
import org.opendaylight.yangtools.yang.common.RpcResult;


/**
 * Interface for implementing the following YANG RPCs defined in module <b>exampleapp</b>
 * <br>(Source path: <i>META-INF/yang/exampleapp.yang</i>):
 * <pre>
 * rpc example-app {
 *     input {
 *         leaf bandwidth {
 *             type uint32;
 *         }
 *     }
 *     
 *     output {
 *         leaf bandwidth {
 *             type uint32;
 *         }
 *     }
 * }
 * </pre>
 *
 */
public interface ExampleappService
    extends
    RpcService
{




    Future<RpcResult<ExampleAppOutput>> exampleApp(ExampleAppInput input);

}

