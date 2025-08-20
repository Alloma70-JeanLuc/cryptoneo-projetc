package org.crypto.core;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithName;

@ConfigMapping(prefix = "crypto")
public interface AppConfig {
    @WithName("name")
    String name();

    @WithName("paystack_secret_key")
    String paystackSecretKey();

    @WithName("paystack_public_key")
    String paystackPublicKey();

    @WithName("paystack_callback_url")
    String paystackCallbackUrl();

    @WithName("paystack_webhook_url")
    String paystackWebhookUrl();
}