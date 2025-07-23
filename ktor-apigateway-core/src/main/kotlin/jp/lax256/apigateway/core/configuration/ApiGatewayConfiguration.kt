package jp.lax256.apigateway.core.configuration

import io.ktor.server.config.ApplicationConfig
import jp.lax256.apigateway.core.CloudVendor
import jp.lax256.apigateway.core.dto.Claims
import jp.lax256.apigateway.core.dto.JwtPayload

class ApiGatewayConfiguration(config: ApplicationConfig) {
    val issuePrincipal: IssuePrincipalConfiguration = IssuePrincipalConfiguration(config)
    fun issuePrincipal(issuePrincipalConfiguration: IssuePrincipalConfiguration.() -> Unit) {
        issuePrincipal.apply(issuePrincipalConfiguration)
    }

    val verifyGatewayToken: VerifyGatewayTokenConfiguration = VerifyGatewayTokenConfiguration(config)
    fun verifyGatewayToken(verifyGatewayTokenConfiguration: VerifyGatewayTokenConfiguration.() -> Unit) {
        verifyGatewayToken.apply(verifyGatewayTokenConfiguration)
    }

    val verifyClientToken: VerifyClientTokenConfiguration = VerifyClientTokenConfiguration(config)
    fun verifyClientToken(verifyClientTokenConfiguration: VerifyClientTokenConfiguration.() -> Unit) {
        verifyClientToken.apply(verifyClientTokenConfiguration)
    }

    var vendor: CloudVendor = CloudVendor.Nothing

    class IssuePrincipalConfiguration(config: ApplicationConfig) {
        var autoIssue: Boolean = false
        var prepare: ((JwtPayload) -> Claims)? = null
    }

    class VerifyGatewayTokenConfiguration(config: ApplicationConfig) {
        var verify: Boolean = false
        var issuer: String = ""
        var audience: String = ""
    }

    class VerifyClientTokenConfiguration(config: ApplicationConfig) {
        var verify: Boolean = false
        var issuer: String = ""
        var audience: String = ""
    }
}
