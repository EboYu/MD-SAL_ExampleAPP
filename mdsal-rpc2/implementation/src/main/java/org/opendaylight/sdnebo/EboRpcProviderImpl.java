package org.opendaylight.sdnebo;

import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.Futures;

import org.opendaylight.yang.gen.v1.urn.opendaylight.sdnebo.rpcprovider.rev160326.EboRpcproviderInput;
import org.opendaylight.yang.gen.v1.urn.opendaylight.sdnebo.rpcprovider.rev160326.EboRpcproviderOutputBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.sdnebo.rpcprovider.rev160326.EboRpcproviderOutput;
import org.opendaylight.yang.gen.v1.urn.opendaylight.sdnebo.rpcprovider.rev160326.EboRpcproviderService;
import org.opendaylight.yang.gen.v1.urn.opendaylight.sdnebo.rpcprovider.rev160326.KeywordType;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev100924.Uri;

import org.opendaylight.yangtools.yang.common.RpcResult;
import org.opendaylight.yangtools.yang.common.RpcResultBuilder;
import org.opendaylight.yangtools.yang.common.RpcError.ErrorType;

public class EboRpcProviderImpl implements EboRpcproviderService {

  //create logger.
  private static final Logger _logger = LoggerFactory
    .getLogger(EboRpcProviderImpl.class);

  //properties.
  private String _contentsSwitchFlag;

  private final Uri _sdneboUrl;
  private final String _sdneboEmail;
  private final String _sdneboCity;
  private final Long _sdneboEmployees;

  private final Uri _sdnlabUrl;
  private final String _sdnlabEmail;
  private final String _sdnlabCity;
  private final Long _sdnlabEmployees;

  //default branch control values.
  private final String URL_CONTENTS_SWITCH_FLAG = new String("url");
  private final String EMAIL_CONTENTS_SWITCH_FLAG = new String("email");
  private final String KEYWORD_sdnebo = new String("sdnebo");
  private final String KEYWORD_SDNLAB = new String("sdnlist");

  //profile values, they should be stored in DB usually.
  private final String sdnebo_URL = new String("http://eboyu.github.io/");
  private final String sdnebo_EMAIL = new String("yyb@whu.edu.cn");
  private final String sdnebo_CITY = new String("Wuhan");
  private final Long sdnebo_EMPLOYEES = new Long(1);

  private final String SDNLAB_URL = new String("http://nulistteam.github.io/");
  private final String SDNLAB_EMAIL = new String("test");
  private final String SDNLAB_CITY = new String("test");
  private final Long SDNLAB_EMPLOYEES = new Long(2);

  public EboRpcProviderImpl() {
    //set default values.
    this._contentsSwitchFlag = URL_CONTENTS_SWITCH_FLAG;

    this._sdneboUrl = new Uri(sdnebo_URL);
    this._sdneboEmail = sdnebo_EMAIL;
    this._sdneboCity = sdnebo_CITY;
    this._sdneboEmployees = sdnebo_EMPLOYEES;

    this._sdnlabUrl = new Uri(SDNLAB_URL);
    this._sdnlabEmail = SDNLAB_EMAIL;
    this._sdnlabCity = SDNLAB_CITY;
    this._sdnlabEmployees = SDNLAB_EMPLOYEES;
  }

  //_contentsSwitchFlag setter.
  public void setContentsSwitchFlag(String contentsSwitchFlag) {
    this._contentsSwitchFlag = contentsSwitchFlag;
  }

  @Override
  public Future<RpcResult<EboRpcproviderOutput>> eboRpcprovider(EboRpcproviderInput input) {

    this._logger.info("EboRpcproviderImp is closed.", this);

    //check input, if input is null throw NullPointerException.
    Preconditions.checkNotNull(input, "input can not be null, throw NullPointerException.");

    KeywordType keywordInput = null;
    RpcResultBuilder<EboRpcproviderOutput> eboRpcproviderBuilder = null;
    EboRpcproviderOutput output = null;
    EboRpcproviderOutputBuilder outputBuilder = null;

    //check keyword, if keyword is null return app error.
    if ((keywordInput = input.getKeyword()) == null) {
      eboRpcproviderBuilder = RpcResultBuilder.<EboRpcproviderOutput>failed()
        .withError(ErrorType.APPLICATION, "Invalid input value",
          "Argument can not be null.");
    //check keyword, if keyword is invalid return app error.
    }else if (!(keywordInput.getValue().equals(KEYWORD_sdnebo)) && 
        !(keywordInput.getValue().equals(KEYWORD_SDNLAB))){
      eboRpcproviderBuilder = RpcResultBuilder.<EboRpcproviderOutput>failed()
        .withError(ErrorType.APPLICATION, "Invalid input value",
          "only sdnebo or sdnlist is acceptable as a keyword.");
    //set profiles into outputBuilder.
    }else {
      outputBuilder = new EboRpcproviderOutputBuilder();

      //set sdnebo profile into outputBuilder.
      if (keywordInput.getValue().equals(KEYWORD_sdnebo)) {
        outputBuilder
          .setUrl(this._sdneboUrl)
          .setEmail(this._sdneboEmail)
          .setCity(this._sdneboCity)
          .setEmployees(this._sdneboEmployees);

      //set sdnlab profile as outputBuilder.
      }else if (keywordInput.getValue().equals(KEYWORD_SDNLAB)) {
        outputBuilder
          .setUrl(this._sdnlabUrl)
          .setEmail(this._sdnlabEmail)
          .setCity(this._sdnlabCity)
          .setEmployees(this._sdnlabEmployees);
      //set null into outputBuilder if input is mismatched.
      }else {
        outputBuilder
          .setUrl(null)
          .setEmail(null)
          .setCity(null)
          .setEmployees(null);
      }

      //set Url as null if flag is email.
      if (this._contentsSwitchFlag.equals(EMAIL_CONTENTS_SWITCH_FLAG)) {
          outputBuilder
            .setUrl(null);

      //set Email as null if flag is not email.
      }else {
          outputBuilder
            .setEmail(null);
      }

      //If output is not null, build output.
      if ((output = outputBuilder.build()) != null) {
        eboRpcproviderBuilder = RpcResultBuilder.success(output);
      //if null throw app error.
      }else {
        eboRpcproviderBuilder = RpcResultBuilder.<EboRpcproviderOutput>failed()
          .withError(ErrorType.APPLICATION, "Invalid output value",
              "Output is null.");
      }
    }
    return Futures.immediateFuture(eboRpcproviderBuilder.build());
  }
}
