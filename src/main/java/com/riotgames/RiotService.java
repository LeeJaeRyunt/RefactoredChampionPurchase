package com.riotgames;

public class RiotService {
    public static final String invalidAccountId = "invalid";
    private final int FIXED_CHAMPION_PRICE = 1000;

    /**
     * User 가 인증된 사용지인지를 확인한다.
     * @param userInfo
     * @return 인증된 사용자이면 true, 그렇지 않으면 false
     */
    public boolean isAuthenticatedUser(UserInfo userInfo) {
        if (userInfo.getAccountId().equals(invalidAccountId)) {
            return false;
        }
        return true;
    }

    /**
     * 챔피언 정보를 리턴한다.
     * (모든 챔피언의 가격은 1000 RP 로 가정한다.)
     * @param championName
     * @return
     */
    public Champion getChampion(String championName) {
        return new Champion(championName, FIXED_CHAMPION_PRICE);
    }
}
