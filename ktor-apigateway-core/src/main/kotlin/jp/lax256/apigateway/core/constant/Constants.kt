package jp.lax256.apigateway.core.constant

import io.ktor.util.AttributeKey
import jp.lax256.apigateway.core.dto.Claims
import jp.lax256.apigateway.core.dto.JwtPayload

val JwtPayloadKey = AttributeKey<JwtPayload>("JwtPayloadKey")
val ClaimsKey = AttributeKey<Claims>("ClaimsKey")
