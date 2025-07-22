package jp.lax256.apigateway.core.util

import jp.lax256.apigateway.core.contract.dto.Firebase
import jp.lax256.apigateway.core.contract.dto.FirebaseAppleAuth
import jp.lax256.apigateway.core.contract.dto.FirebaseFacebookAuth
import jp.lax256.apigateway.core.contract.dto.FirebaseGoogleAuth
import jp.lax256.apigateway.core.contract.dto.FirebasePasswordAuth
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

val ApiGatewayPayloadJson = Json {
    ignoreUnknownKeys = true
    prettyPrint = true
    // classDiscriminator = "sign_in_provider"
    serializersModule = SerializersModule {
        polymorphic(Firebase::class) {
            subclass(FirebasePasswordAuth::class)
            subclass(FirebaseAppleAuth::class)
            subclass(FirebaseGoogleAuth::class)
            subclass(FirebaseFacebookAuth::class)
        }
    }
}
