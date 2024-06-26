package dev.lugami.qlib.uuid;

import java.util.UUID;

public interface UUIDCache {

    UUID uuid(String var1);

    String name(UUID var1);

    void ensure(UUID var1);

    void update(UUID var1, String var2);
}

