package com.example.almaziskhakov.conductortest.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by almaziskhakov on 17/03/2017.
 */

public class Provider {
    private String  id;
    private String  name;
    private int     priority;
    private boolean subscriber;
    @SerializedName("first_popup_message")
    private String  subscriptionExpiringSoonMessage;
    @SerializedName("second_popup_message")
    private String  subscriptionExpiredMessage;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    public boolean isSubscriber() {
        return subscriber;
    }

    public boolean isNotSubscriber() {
        return !"subscriber".equals(name);
    }

    public String getSubscriptionExpiringSoonMessage() {
        return subscriptionExpiringSoonMessage;
    }

    public String getSubscriptionExpiredMessage() {
        return subscriptionExpiredMessage;
    }

}
