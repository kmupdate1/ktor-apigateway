package jp.lax256.apigateway.gcp.plugin

import io.ktor.server.application.*
import io.ktor.util.*
import jp.lax256.apigateway.core.CloudVendor
import jp.lax256.apigateway.core.configuration.ApiGatewayConfiguration
import jp.lax256.apigateway.core.constant.ClaimsKey
import jp.lax256.apigateway.core.constant.JwtPayloadKey
import jp.lax256.apigateway.core.exception.VendorNotMatchedException
import jp.lax256.apigateway.core.plugin.ApiGateway
import jp.lax256.apigateway.core.util.ServiceRegistry
import jp.lax256.apigateway.gcp.util.GcpInitializer

class GcpApiGateway(config: ApiGatewayConfiguration): ApiGateway {
    companion object Plugin : BaseApplicationPlugin<Application, ApiGatewayConfiguration, GcpApiGateway> {
        override val key: AttributeKey<GcpApiGateway> = AttributeKey("jp.lax256.apigateway.core.plugin.ApiGateway")

        override fun install(
            pipeline: Application,
            configure: ApiGatewayConfiguration.() -> Unit
        ): GcpApiGateway {
            val baseConfig = pipeline.environment.config
            val baseLog = pipeline.environment.log
            val pluginConfig = ApiGatewayConfiguration(baseConfig).apply(configure)

            val plugin = GcpApiGateway(pluginConfig)

            if (pluginConfig.vendor != CloudVendor.GCP) throw VendorNotMatchedException(
                "GcpApiGateway plugin requires a ${CloudVendor.GCP.name} vendor configuration. But actual vendor is ${pluginConfig.vendor.name}. Please set `vendor = CloudVendor.GCP` in the install block."
            ).apply { baseLog.error("{} not setting.", pluginConfig.vendor, this) }

            GcpInitializer.run {
                initIssuer(pluginConfig.vendor)
                initGatewayTokenVerifier(pluginConfig.vendor)
                initClientTokenVerifier(pluginConfig.vendor)
            }

            val issuer = ServiceRegistry.payloadIssuer(pluginConfig.vendor)
            val gatewayTokenVerifier = ServiceRegistry.gatewayTokenVerifier(pluginConfig.vendor)
            val clientTokenVerifier = ServiceRegistry.clientTokenVerifier(pluginConfig.vendor)
            pipeline.intercept(ApplicationCallPipeline.ApplicationPhase.Plugins) {
                if (pluginConfig.verifyClientToken.verify)
                    clientTokenVerifier

                if (pluginConfig.verifyGatewayToken.verify)
                    gatewayTokenVerifier

                if (pluginConfig.issuePrincipal.autoIssue)
                    issuer.issueFromHeader(call = call).let { payload ->
                        call.attributes.put(JwtPayloadKey, payload)
                    }

                pluginConfig.issuePrincipal.prepare?.let {
                    it(issuer.issueFromHeader(call = call))
                }?.let { call.attributes.put(ClaimsKey, it) }

                proceed()
            }

            return plugin
        }
    }
}
