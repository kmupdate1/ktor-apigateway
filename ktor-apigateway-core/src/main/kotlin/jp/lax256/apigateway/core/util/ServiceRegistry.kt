package jp.lax256.apigateway.core.util

import jp.lax256.apigateway.core.CloudVendor
import jp.lax256.apigateway.core.contract.operation.ClientTokenVerifier
import jp.lax256.apigateway.core.contract.operation.PayloadIssuer
import jp.lax256.apigateway.core.contract.operation.GatewayTokenVerifier

object ServiceRegistry {
    private val issuers = mutableMapOf<CloudVendor, PayloadIssuer>()
    private val gatewayTokenVerifiers = mutableMapOf<CloudVendor, GatewayTokenVerifier>()
    private val clientTokenVerifiers = mutableMapOf<CloudVendor, ClientTokenVerifier>()

    fun register(vendor: CloudVendor, issuer: PayloadIssuer) { issuers[vendor] = issuer }
    fun register(vendor: CloudVendor, verifier: GatewayTokenVerifier) { gatewayTokenVerifiers[vendor] = verifier }
    fun register(vendor: CloudVendor, verifier: ClientTokenVerifier) { clientTokenVerifiers[vendor] = verifier }

    fun payloadIssuer(vendor: CloudVendor): PayloadIssuer =
        issuers[vendor] ?: error("Issuer not registered for vendor: $vendor")
    fun gatewayTokenVerifier(vendor: CloudVendor): GatewayTokenVerifier =
        gatewayTokenVerifiers[vendor] ?: error("Gateway token verifier not registered for vendor: $vendor")
    fun clientTokenVerifier(vendor: CloudVendor): ClientTokenVerifier =
        clientTokenVerifiers[vendor] ?: error("Client token verifier not registered for vendor: $vendor")
}
