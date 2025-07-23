package jp.lax256.apigateway.core.contract.operation

import io.ktor.server.application.ApplicationCall
import jp.lax256.apigateway.core.dto.JwtPayload

interface PayloadIssuer {
    fun issueFromHeader(call: ApplicationCall): JwtPayload
}
