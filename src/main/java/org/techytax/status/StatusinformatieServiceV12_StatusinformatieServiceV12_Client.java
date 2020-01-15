
package org.techytax.status;

import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.dom.handler.WSHandlerConstants;
import org.techytax.security.ClientPasswordCallback;
import org.techytax.security.SecureConnectionHelper;
import org.techytax.ws.IdentiteitType;
import org.techytax.ws.ObjectFactory;
import org.techytax.xbrl.DynamicWsaSignaturePartsInterceptor;

import javax.xml.namespace.QName;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * This class was generated by Apache CXF 2.7.1
 * 2013-01-16T21:54:45.200+01:00
 * Generated source version: 2.7.1
 *
 */
public final class StatusinformatieServiceV12_StatusinformatieServiceV12_Client {

    private static final QName SERVICE_NAME = new QName("http://logius.nl/digipoort/wus/2.0/statusinformatieservice/1.2/", "StatusinformatieService_V1_2");

	private static Properties keyProperties = new Properties();
	private static Properties trustProperties = new Properties();

    private StatusinformatieServiceV12_StatusinformatieServiceV12_Client() {
    }

    public static void main(String args[]) throws Exception {
        URL wsdlURL = StatusinformatieServiceV12_Service.WSDL_LOCATION;
        if (args.length > 0 && args[0] != null && !"".equals(args[0])) {
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        StatusinformatieServiceV12_Service ss = new StatusinformatieServiceV12_Service(wsdlURL, SERVICE_NAME);
        StatusinformatieServiceV12 port = ss.getStatusinformatieServiceV12();

		keyProperties.load(StatusinformatieServiceV12_StatusinformatieServiceV12_Client.class.getResourceAsStream("/client_sign.properties"));
		trustProperties.load(StatusinformatieServiceV12_StatusinformatieServiceV12_Client.class.getResourceAsStream("/client_verify.properties"));
		SecureConnectionHelper.setupTLS(port, keyProperties, trustProperties);
		String keyStorePassword = keyProperties.getProperty("org.apache.ws.security.crypto.merlin.keystore.password");
		ClientPasswordCallback.setKeyStorePassword(keyStorePassword);

		org.apache.cxf.endpoint.Client client = ClientProxy.getClient(port);
		org.apache.cxf.endpoint.Endpoint cxfEndpoint = client.getEndpoint();

		cxfEndpoint.getInInterceptors().add(new LoggingInInterceptor());
		cxfEndpoint.getOutInterceptors().add(new LoggingOutInterceptor());

		Map<String, Object> outProps = new HashMap<>();
		// how to configure the properties is outlined below;

		outProps.put(WSHandlerConstants.USER,
				WSHandlerConstants.USE_REQ_SIG_CERT);
		outProps.put(WSHandlerConstants.SIG_KEY_ID, "DirectReference");
		outProps.put(WSHandlerConstants.SIGNATURE_USER, "1");
		outProps.put(WSHandlerConstants.PW_CALLBACK_CLASS,
				ClientPasswordCallback.class.getName());
		outProps.put(
				WSHandlerConstants.SIGNATURE_PARTS,
				"{Content}{http://schemas.xmlsoap.org/soap/envelope/}Body;{Element}{http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd}Timestamp");

		outProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.SIGNATURE);

		outProps.put(WSHandlerConstants.SIG_PROP_FILE, "client_sign.properties");

		Map<String, Object> inProps = new HashMap<>();
		inProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.TIMESTAMP
				+ " " + WSHandlerConstants.SIGNATURE);
		inProps.put(WSHandlerConstants.SIG_PROP_FILE,
				"client_verify.properties");

		WSS4JInInterceptor wssIn = new WSS4JInInterceptor(inProps);
		cxfEndpoint.getInInterceptors().add(wssIn);

		WSS4JOutInterceptor wssOut = new WSS4JOutInterceptor(outProps);
		cxfEndpoint.getOutInterceptors().add(wssOut);
		cxfEndpoint.getOutInterceptors().add(
				new DynamicWsaSignaturePartsInterceptor());

		org.techytax.ws.ObjectFactory objectFactory = new ObjectFactory();

        {
        System.out.println("Invoking getBerichtsoorten...");
        org.techytax.ws.GetBerichtsoortenRequest _getBerichtsoorten_getBerichtsoortenRequest = null;
        try {
        	_getBerichtsoorten_getBerichtsoortenRequest = objectFactory.createGetBerichtsoortenRequest();
        	IdentiteitType identiteitType = objectFactory.createIdentiteitType();
        	identiteitType.setNummer("174006275B01");
        	identiteitType.setType("Fi");
        	_getBerichtsoorten_getBerichtsoortenRequest.setIdentiteitBelanghebbende(identiteitType);
        	_getBerichtsoorten_getBerichtsoortenRequest.setAutorisatieAdres("http://geenausp.nl");
            org.techytax.ws.GetBerichtsoortenResponse _getBerichtsoorten__return = port.getBerichtsoorten(_getBerichtsoorten_getBerichtsoortenRequest);
            System.out.println("getBerichtsoorten.result=" + _getBerichtsoorten__return);

        } catch (StatusinformatieServiceFault e) {
            System.out.println("Expected exception: StatusinformatieServiceFault has occurred.");
            System.out.println(e.toString());
        }
            }
//        {
//        System.out.println("Invoking getStatussenProces...");
//        org.techytax.ws.GetStatussenProcesRequest _getStatussenProces_getStatussenProcesRequest = null;
//        try {
//        	_getStatussenProces_getStatussenProcesRequest = objectFactory.createGetStatussenProcesRequest();
//        	_getStatussenProces_getStatussenProcesRequest.setKenmerk("");
//            org.techytax.ws.GetStatussenProcesResponse _getStatussenProces__return = port.getStatussenProces(_getStatussenProces_getStatussenProcesRequest);
//            System.out.println("getStatussenProces.result=" + _getStatussenProces__return);
//
//        } catch (StatusinformatieServiceFault e) {
//            System.out.println("Expected exception: StatusinformatieServiceFault has occurred.");
//            System.out.println(e.toString());
//        }
//            }
//        {
//        System.out.println("Invoking getProcessen...");
//        org.techytax.ws.GetProcessenRequest _getProcessen_getProcessenRequest = null;
//        try {
//        	_getProcessen_getProcessenRequest = objectFactory.createGetProcessenRequest();
//        	_getProcessen_getProcessenRequest.setBerichtsoort("Omzetbelasting");
//        	IdentiteitType identiteitType = objectFactory.createIdentiteitType();
////        	identiteitType.setNummer("30216247");
////        	identiteitType.setType("KvK");
//        	identiteitType.setNummer("174006275B01");
//        	identiteitType.setType("Fi");
//        	_getProcessen_getProcessenRequest.setIdentiteitBelanghebbende(identiteitType);
//        	_getProcessen_getProcessenRequest.setAutorisatieAdres("http://geenausp.nl");
//            org.techytax.ws.GetProcessenResponse _getProcessen__return = port.getProcessen(_getProcessen_getProcessenRequest);
//            System.out.println("getProcessen.result=" + _getProcessen__return);
//
//        } catch (StatusinformatieServiceFault e) {
//            System.out.println("Expected exception: StatusinformatieServiceFault has occurred.");
//            System.out.println(e.toString());
//        }
//            }
//        {
//        System.out.println("Invoking getNieuweStatussen...");
//        org.techytax.ws.GetNieuweStatussenRequest _getNieuweStatussen_getNieuweStatussenRequest = null;
//        try {
//            org.techytax.ws.GetNieuweStatussenResponse _getNieuweStatussen__return = port.getNieuweStatussen(_getNieuweStatussen_getNieuweStatussenRequest);
//            System.out.println("getNieuweStatussen.result=" + _getNieuweStatussen__return);
//
//        } catch (StatusinformatieServiceFault e) {
//            System.out.println("Expected exception: StatusinformatieServiceFault has occurred.");
//            System.out.println(e.toString());
//        }
//            }
//        {
//        System.out.println("Invoking getNieuweStatussenProces...");
//        org.techytax.ws.GetNieuweStatussenProcesRequest _getNieuweStatussenProces_getNieuweStatussenProcesRequest = null;
//        try {
//
//        	_getNieuweStatussenProces_getNieuweStatussenProcesRequest = objectFactory.createGetNieuweStatussenProcesRequest();
//        	_getNieuweStatussenProces_getNieuweStatussenProcesRequest.setKenmerk("63043889-ba29-417b-be1e-e20e03248b58");
//        	_getNieuweStatussenProces_getNieuweStatussenProcesRequest.setAutorisatieAdres("http://geenausp.nl");
//
//            org.techytax.ws.GetNieuweStatussenProcesResponse _getNieuweStatussenProces__return = port.getNieuweStatussenProces(_getNieuweStatussenProces_getNieuweStatussenProcesRequest);
//            System.out.println("getNieuweStatussenProces.result=" + _getNieuweStatussenProces__return);
//
//        } catch (StatusinformatieServiceFault e) {
//            System.out.println("Expected exception: StatusinformatieServiceFault has occurred.");
//            System.out.println(e.toString());
//        }
//            }

        System.exit(0);
    }

}
