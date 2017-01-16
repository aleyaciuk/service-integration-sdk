package com.appdirect.sdk.appmarket.events;

import java.util.Map;

import lombok.EqualsAndHashCode;

/**
 * Represents one of the subtypes of the Subscription Notice event sent by the App Market.
 * See the documentation at the link below for more detailed information regarding the significance of the event.
 * <p>
 * Note that when {@link SubscriptionDeactivated} is handled in a connector, the data for the deactivated account
 * SHOULD NOT be deleted, because it can be reactivated later (see {@link SubscriptionReactivated}).
 *
 * @see <a href="https://docs.appdirect.com/developer/distribution/event-notifications/subscription-events#notice-types">SUBSCRIPTION_NOTICE types</a>
 */
@EqualsAndHashCode(callSuper = true)
public class SubscriptionDeactivated extends EventWithConsumerKeyQueryParametersAndEventFlag {
	private final AccountInfo accountInfo;

	public SubscriptionDeactivated(String consumerKeyUsedByTheRequest, AccountInfo accountInfo, Map<String, String[]> queryParameters, EventFlag flag) {
		super(consumerKeyUsedByTheRequest, queryParameters, flag);
		this.accountInfo = accountInfo;
	}

	public AccountInfo getAccountInfo() {
		return accountInfo;
	}
}