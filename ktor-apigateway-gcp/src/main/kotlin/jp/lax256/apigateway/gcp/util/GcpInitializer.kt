package jp.lax256.apigateway.gcp.util

import jp.lax256.apigateway.core.CloudVendor
import jp.lax256.apigateway.core.util.ServiceRegistry

object GcpInitializer {
    fun initIssuer(vendor: CloudVendor) =
        ServiceRegistry.register(vendor = vendor, issuer = GcpApiGatewayProvider.issuer)

    fun initGatewayTokenVerifier(vendor: CloudVendor) =
        ServiceRegistry.register(vendor = vendor, verifier = GcpApiGatewayProvider.gatewayTokenVerifier)

    fun initClientTokenVerifier(vendor: CloudVendor) =
        ServiceRegistry.register(vendor = vendor, verifier = GcpApiGatewayProvider.clientTokenConfiguration)
}
