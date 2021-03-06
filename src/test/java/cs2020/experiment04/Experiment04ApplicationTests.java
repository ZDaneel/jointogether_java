package cs2020.experiment04;

import com.github.pagehelper.PageInfo;
import cs2020.experiment04.entity.Partyinfo;
import cs2020.experiment04.service.IPartybillService;
import cs2020.experiment04.service.IPartyinfoService;
import cs2020.experiment04.service.IPartymessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Experiment04ApplicationTests {

    @Autowired
    private IPartyinfoService partyinfoService;

    @Autowired
    private IPartybillService partybillService;

    @Autowired
    private IPartymessageService partymessageService;

    @Test
    void contextLoads() {
    }

    @Test
    void testPartyPage(){
        PageInfo<Partyinfo> allByPage = partyinfoService.findAllByPage(1, 5, 1);
        List<Partyinfo> list = allByPage.getList();
        long total = allByPage.getTotal();
    }

    @Test
    void testSaveUserParty(){
        Partyinfo partyinfo = new Partyinfo();
        partyinfo.setId(16);
        partyinfo.setUsername("admin");
        partyinfoService.saveUserParty(partyinfo);
    }

    @Test
    void testSaveBillFirst(){
        Partyinfo partyinfo = new Partyinfo();
        partyinfo.setId(1);
        partyinfo.setUsername("admin");
        partyinfo.setCharge(100.0);
        partybillService.savePartyBillFirst(partyinfo);
    }

    @Test
    void testFindAllUserId() {
        partyinfoService.toGroup(1);
    }
}
