package hochenchong.litespring.service.v4;

import hochenchong.litespring.beans.factory.annotation.Autowired;
import hochenchong.litespring.dao.v4.AccountDao;
import hochenchong.litespring.dao.v4.ItemDao;
import hochenchong.litespring.stereotype.Component;

@Component(value = "petStore")
public class PetStoreService {
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private ItemDao itemDao;

    public AccountDao getAccountDao() {
        return accountDao;
    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public ItemDao getItemDao() {
        return itemDao;
    }

    public void setItemDao(ItemDao itemDao) {
        this.itemDao = itemDao;
    }
}
