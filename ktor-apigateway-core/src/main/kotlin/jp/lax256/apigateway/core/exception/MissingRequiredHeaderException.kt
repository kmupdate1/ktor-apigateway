package jp.lax256.apigateway.core.exception

class MissingRequiredHeaderException(
    headerName: String,
): RuntimeException("Missing header: $headerName.")
