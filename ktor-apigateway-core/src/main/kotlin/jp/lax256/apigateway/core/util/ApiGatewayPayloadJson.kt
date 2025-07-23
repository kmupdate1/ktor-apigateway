package jp.lax256.apigateway.core.util

import jp.lax256.apigateway.core.dto.Firebase
import jp.lax256.apigateway.core.dto.FirebaseAppleAuth
import jp.lax256.apigateway.core.dto.FirebaseFacebookAuth
import jp.lax256.apigateway.core.dto.FirebaseGoogleAuth
import jp.lax256.apigateway.core.dto.FirebasePasswordAuth
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

val ApiGatewayPayloadJson = Json {
    ignoreUnknownKeys = true
    prettyPrint = true
    isLenient = true

    serializersModule = SerializersModule {
        polymorphic(Firebase::class) {
            subclass(FirebasePasswordAuth::class)
            subclass(FirebaseAppleAuth::class)
            subclass(FirebaseGoogleAuth::class)
            subclass(FirebaseFacebookAuth::class)
        }
    }
}
