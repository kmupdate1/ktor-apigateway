package jp.lax256.apigateway.gcp.http

import io.ktor.http.HttpHeaders

object GatewayHttpHeaders {
    val UserAgent: String = HttpHeaders.UserAgent
    val Authorization: String = HttpHeaders.Authorization
    val AcceptEncoding: String = HttpHeaders.AcceptEncoding
    val Forwarded: String = HttpHeaders.Forwarded
    val Host: String = HttpHeaders.Host
    val XForwardedProto: String = HttpHeaders.XForwardedProto
    const val Traceparent: String = "Traceparent"
    const val XCloudTraceContext: String = "X-Cloud-Trace-Content"
    const val XForwardedAuthorization: String = "X-Forwarded-Authorization"
    val ContentLength: String = HttpHeaders.ContentLength
    val XRequestId: String = HttpHeaders.XRequestId
    val XForwardedFor: String = HttpHeaders.XForwardedFor
    val Accept: String = HttpHeaders.Accept
    const val XApigatewayApiUserinfo: String = "X-Apigateway-Api-Userinfo"
}
