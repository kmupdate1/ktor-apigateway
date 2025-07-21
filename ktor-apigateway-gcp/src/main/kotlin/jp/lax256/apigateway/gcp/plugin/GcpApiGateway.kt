package jp.lax256.apigateway.gcp.plugin

import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationCallPipeline
import io.ktor.server.application.BaseApplicationPlugin
import io.ktor.server.application.call
import io.ktor.server.application.log
import io.ktor.util.AttributeKey
import jp.lax256.apigateway.core.CloudVendor
import jp.lax256.apigateway.core.configuration.ApiGatewayConfiguration
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
            ).apply { baseLog.error("${pluginConfig.vendor} not setting.", this) }

            GcpInitializer.run {
                initIssuer(pluginConfig.vendor)
                initGatewayTokenVerifier(pluginConfig.vendor)
                initClientTokenVerifier(pluginConfig.vendor)
            }

            baseLog.info("ApiGateway plugin installed with configuration $pluginConfig")

            val issuer = ServiceRegistry.payloadIssuer(pluginConfig.vendor)
            val gatewayTokenVerifier = ServiceRegistry.gatewayTokenVerifier(pluginConfig.vendor)
            val clientTokenVerifier = ServiceRegistry.clientTokenVerifier(pluginConfig.vendor)
            pipeline.intercept(ApplicationCallPipeline.ApplicationPhase.Plugins) {
                if (pluginConfig.verifyClientToken.verify)
                    clientTokenVerifier

                if (pluginConfig.verifyGatewayToken.verify)
                    gatewayTokenVerifier

                if (pluginConfig.issuePrincipal.issue)
                    issuer.issueFromHeader(call = call, logger = pipeline.log).let {
                        call.attributes.put(key = JwtPayloadKey, value = it)
                    }

                proceed()
            }

            return plugin
        }
    }
}
