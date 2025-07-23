package jp.lax256.apigateway.gcp.issuer

import io.ktor.server.application.*
import jp.lax256.apigateway.core.dto.JwtPayload
import jp.lax256.apigateway.core.contract.operation.PayloadIssuer
import jp.lax256.apigateway.core.util.ApiGatewayPayloadJson
import jp.lax256.apigateway.gcp.http.GatewayHttpHeaders
import java.util.*

class GcpPayloadIssuer: PayloadIssuer {
    override fun issueFromHeader(call: ApplicationCall): JwtPayload {
        val xApigatewayApiUserinfo = call.request.headers[GatewayHttpHeaders.XApigatewayApiUserinfo]
            ?: throw IllegalStateException("${GatewayHttpHeaders.XApigatewayApiUserinfo} header not found.").apply {
                call.application.log.error(this.message, this)
            }

        return runCatching {
            Base64.getUrlDecoder().decode(xApigatewayApiUserinfo)
                .let { String(it, Charsets.UTF_8) }
                .run {
                    call.application.log.info("Decoded ${GatewayHttpHeaders.XApigatewayApiUserinfo} Payload: $this")
                    ApiGatewayPayloadJson.decodeFromString<JwtPayload>(this)
                }
        }.fold(
            onSuccess = { jwtPayload ->
                call.application.log.info("SUCCESS decoding and parsing ${GatewayHttpHeaders.XApigatewayApiUserinfo}: user_id - ${jwtPayload.userId}, email - ${jwtPayload.email}")
                jwtPayload
            },
            onFailure = { throwable ->
                call.application.log.error(
                    "FAILED to decoding or parsing ${GatewayHttpHeaders.XApigatewayApiUserinfo}: ${throwable.message}",
                    throwable,
                )
                throw throwable
            }
        )
    }
}
