package pojoClasses;

import java.util.List;

public class CommerceOrder {

    private List<CommerceOrderDetail> orders;

    public List<CommerceOrderDetail> getOrders() {
        return orders;
    }
    public void setOrders(List<CommerceOrderDetail> orders) {
        this.orders = orders;
    }
}
