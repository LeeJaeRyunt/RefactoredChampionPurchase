package com.riotgames;

public class ChampionPurchaseService {
    RiotService riotService = new RiotService();
    /**
     * 챔피언을 구매한다
     * @param userInfo 사용자 정보
     * @param championName 구매하는 챔피언 이름
     * @return 구매 성공시 true, 실패시 false
     */
    public PurchaseResultStatus buyChampion (UserInfo userInfo, String championName) {

        // 유저를 인증한다.
        if(riotService.isAuthenticatedUser(userInfo) == false) {
            return PurchaseResultStatus.NOT_AUTHENTICATED_USER;
        }

        // 구매를 원하는 챔피언이 현재 보유 중인지 확인한다.
        if (userInfo.hasChampion(championName)) {
            return PurchaseResultStatus.HAS_ALREADY_CHAMPION;
        }

        // 해당 champion 을 구매하기 위한 RP 가 충분한지 확인한다.
        int championPrice = riotService.getChampion(championName).getPrice();
        if (championPrice > userInfo.getRp()) {
            return PurchaseResultStatus.NOT_ENOUGH_RP;
        }
        return PurchaseResultStatus.PURCHASE_OK;
    }

}
