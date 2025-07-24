package jp.lax256.apigateway.gcp.issuer

import io.ktor.server.application.*
import jp.lax256.apigateway.core.dto.JwtPayload
import jp.lax256.apigateway.core.contract.operation.PayloadIssuer
import jp.lax256.apigateway.core.util.ApiGatewayPayloadJson
import jp.lax256.apigateway.gcp.http.GatewayHttpHeaders
import org.slf4j.LoggerFactory
import java.util.*

class GcpPayloadIssuer: PayloadIssuer {
    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun issueFromHeader(call: ApplicationCall): JwtPayload {
        val xApigatewayApiUserinfo = call.request.headers[GatewayHttpHeaders.XApigatewayApiUserinfo]
            ?: throw IllegalStateException("No '${GatewayHttpHeaders.XApigatewayApiUserinfo}' header found.").apply {
                logger.error(this.message, this)
            }

        return runCatching {
            Base64.getUrlDecoder().decode(xApigatewayApiUserinfo)
                .let { String(it, Charsets.UTF_8) }
                .run {
                    logger.info(
                        "$SUCCESS: Decoded '{}' header value.",
                        GatewayHttpHeaders.XApigatewayApiUserinfo,
                    )
                    ApiGatewayPayloadJson.decodeFromString<JwtPayload>(this)
                }
        }.fold(
            onSuccess = { jwtPayload ->
                logger.info(
                    "$SUCCESS: Decoding and parsing '{}' header value.",
                    GatewayHttpHeaders.XApigatewayApiUserinfo,
                )
                jwtPayload
            },
            onFailure = { throwable ->
                logger.error(
                    "$FAILED: Decoding or parsing '{}' header value. - {}",
                    GatewayHttpHeaders.XApigatewayApiUserinfo,
                    throwable.message,
                    throwable,
                )
                throw throwable
            }
        )
    }
}
