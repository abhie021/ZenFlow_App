package com.abhimurkute.zenflow;

public interface Blockable {
    String getIdentifier();
    void block() throws Exception;
    void unblock() throws Exception;
}