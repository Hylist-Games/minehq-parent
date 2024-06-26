package dev.lugami.bridge.global.disguise;

import dev.lugami.bridge.global.ranks.Rank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class DisguisePlayer {

    private final String name;

    private Rank disguiseRank;
    private String disguiseName;
    private String disguiseSkin;

    private DisguisePlayerSkin realSkin;
    private DisguisePlayerSkin fakeSkin;
}
