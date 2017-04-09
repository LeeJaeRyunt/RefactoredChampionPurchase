import com.riotgames.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ChampionPurchaseServiceTest {
    private ChampionPurchaseService purchaseService;

    @Before
    public void setUpRiotService() {
        purchaseService = new ChampionPurchaseService();
    }

    @Test
    public void testCheckingInvalidUser() {
        UserInfo userInfo = getUserInfo(RiotService.invalidAccountId, 0, "Garen");
        PurchaseResultStatus expectedStatus = PurchaseResultStatus.NOT_AUTHENTICATED_USER;
        PurchaseResultStatus actualStatus;

        actualStatus = purchaseService.buyChampion(userInfo, "Garen");
        assertEquals("사용자 인증 테스트에 실패했습니다.", expectedStatus, actualStatus);
    }

    @Test
    public void testCheckingValidUser() {
        UserInfo userInfo = getUserInfo("ksc1004", 1000, "Garen");
        PurchaseResultStatus expectedStatus = PurchaseResultStatus.PURCHASE_OK;
        PurchaseResultStatus actualStatus;

        actualStatus = purchaseService.buyChampion(userInfo, "Ahri");

        assertEquals("사용자 인증 테스트에 실패했습니다.", expectedStatus, actualStatus);
    }

    @Test
    public void testCheckingUserHasChampion() {
        UserInfo userInfo = getUserInfo("ksc1004", 1000, "Garen");
        PurchaseResultStatus expectedStatus = PurchaseResultStatus.PURCHASE_OK;
        PurchaseResultStatus actualStatus;

        actualStatus = purchaseService.buyChampion(userInfo, "Ahri");

        assertEquals("사용자 보유 챔피언 확인에 실패했습니다.", expectedStatus, actualStatus);
    }

    @Test
    public void testCheckingUserNotHasChampion() {
        UserInfo userInfo = getUserInfo("ksc1004", 1000, "Garen");
        PurchaseResultStatus expectedStatus = PurchaseResultStatus.HAS_ALREADY_CHAMPION;
        PurchaseResultStatus actualStatus;

        actualStatus = purchaseService.buyChampion(userInfo, "Garen");

        assertEquals("사용자 보유 챔피언 확인에 실패했습니다.", expectedStatus, actualStatus);
    }

    @Test
    public void testCheckingUserHasEnoughRP() {
        UserInfo userInfo = getUserInfo("ksc1004", 1500, "Garen");
        PurchaseResultStatus expectedStatus = PurchaseResultStatus.PURCHASE_OK;
        PurchaseResultStatus actualStatus;

        // NOTE: 모든 챔피언은 1000RP 로 설정 되어있다.
        actualStatus = purchaseService.buyChampion(userInfo, "Ahri");
        assertEquals("사용자 보유 RP 확인 테스트에 실패했습니다.", expectedStatus, actualStatus);
    }

    @Test
    public void testCheckingUserHasNotEnoughRP() {
        UserInfo userInfo = getUserInfo("ksc1004", 100, "Garen");
        PurchaseResultStatus expectedStatus = PurchaseResultStatus.NOT_ENOUGH_RP;
        PurchaseResultStatus actualStatus;

        // NOTE: 모든 챔피언은 1000RP 로 설정 되어있다.
        actualStatus = purchaseService.buyChampion(userInfo, "Ahri");
        assertEquals("사용자 보유 RP 확인 테스트에 실패했습니다.", expectedStatus, actualStatus);
    }

    private UserInfo getUserInfo(String accountId, int ownedRp, String championName) {
        UserInfo userInfo = new UserInfo();
        userInfo.setAccountId(accountId);
        userInfo.setOwnedRp(ownedRp);
        userInfo.addChampion(championName, new Champion(championName, 1000));
        return userInfo;
    }
}
