package org.sample.payment.client.bill.inquiry.providerb.dto;

/*
 * Author: Atousa Mirhosseini
 * Date:   2/4/2024
 */

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@AllArgsConstructor
public enum ProviderState {
    SUCCESS("s"),
    ERROR("e"),
    INCORRECT_BILL_ID("inc_code")
    ;

    private String state;

    private static Map<String, ProviderState> stateMap = new ConcurrentHashMap<>();

    static {
        for (ProviderState providerState : ProviderState.values()) {
            stateMap.put(providerState.state, providerState);
        }
    }

    public static ProviderState getState(String state) {
        return stateMap.get(state);
    }
}
