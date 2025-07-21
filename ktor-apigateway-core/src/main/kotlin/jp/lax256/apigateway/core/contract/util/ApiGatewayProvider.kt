package jp.lax256.apigateway.core.contract.util

import jp.lax256.apigateway.core.contract.operation.ClientTokenVerifier
import jp.lax256.apigateway.core.contract.operation.GatewayTokenVerifier
import jp.lax256.apigateway.core.contract.operation.PayloadIssuer

interface ApiGatewayProvider {
    val issuer: PayloadIssuer
    val gatewayTokenVerifier: GatewayTokenVerifier
    val clientTokenConfiguration: ClientTokenVerifier
}
