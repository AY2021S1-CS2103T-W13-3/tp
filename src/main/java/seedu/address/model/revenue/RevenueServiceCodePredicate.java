package seedu.address.model.revenue;

import java.util.function.Predicate;

import seedu.address.model.service.ServiceCode;

public class RevenueServiceCodePredicate implements Predicate<Revenue> {

    private final ServiceCode serviceCode;

    public RevenueServiceCodePredicate(ServiceCode serviceCode) {
        this.serviceCode = serviceCode;
    }

    @Override
    public boolean test(Revenue revenue) {
        return serviceCode.equals(revenue.getService().getServiceCode());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof RevenueServiceCodePredicate // instanceof handles nulls
            && serviceCode.equals(((RevenueServiceCodePredicate) other).serviceCode)); // state check
    }
}
