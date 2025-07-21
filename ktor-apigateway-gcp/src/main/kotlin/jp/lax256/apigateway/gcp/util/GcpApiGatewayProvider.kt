package jp.lax256.apigateway.gcp.util

import jp.lax256.apigateway.core.contract.operation.ClientTokenVerifier
import jp.lax256.apigateway.core.contract.operation.GatewayTokenVerifier
import jp.lax256.apigateway.core.contract.operation.PayloadIssuer
import jp.lax256.apigateway.core.contract.util.ApiGatewayProvider
import jp.lax256.apigateway.gcp.issuer.GcpPayloadIssuer
import jp.lax256.apigateway.gcp.verifier.GcpClientTokenVerifier
import jp.lax256.apigateway.gcp.verifier.GcpGatewayTokenVerifier

object GcpApiGatewayProvider: ApiGatewayProvider {
    override val issuer: PayloadIssuer
        get() = GcpPayloadIssuer()
    override val gatewayTokenVerifier: GatewayTokenVerifier
        get() = GcpGatewayTokenVerifier()
    override val clientTokenConfiguration: ClientTokenVerifier
        get() = GcpClientTokenVerifier()
}
