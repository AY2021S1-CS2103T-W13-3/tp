package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.client.Client;
import seedu.address.model.client.Email;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;
import seedu.address.model.expense.Expense;
import seedu.address.model.expense.IsFixed;
import seedu.address.model.manager.ExpenseTracker;
import seedu.address.model.manager.ReadOnlyExpenseTracker;
import seedu.address.model.manager.ReadOnlyServiceManager;
import seedu.address.model.manager.ServiceManager;
import seedu.address.model.service.Duration;
import seedu.address.model.service.Service;
import seedu.address.model.util.attributes.Amount;
import seedu.address.model.util.attributes.Date;
import seedu.address.model.util.attributes.Description;
import seedu.address.model.util.attributes.Tag;
import seedu.address.model.util.attributes.Title;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Client[] getSampleClients() {
        return new Client[]{
            new Client(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                getTagSet("friends")),
            new Client(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                getTagSet("colleagues", "friends")),
            new Client(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                getTagSet("neighbours")),
            new Client(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                getTagSet("family")),
            new Client(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                getTagSet("classmates")),
            new Client(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                getTagSet("colleagues"))
        };
    }

    public static Service[] getSampleServices() {
        return new Service[]{
            new Service(new Title("Lash Lift"), new Duration(0.5), new Amount(38.0)).addServiceCode("SC000"),
            new Service(new Title("Nail Extension"), new Duration(0.5), new Amount(28.9)).addServiceCode("SC001"),
            new Service(new Title("Pedicure"), new Duration(0.5), new Amount(18.5)).addServiceCode("SC003"),
            new Service(new Title("Hair Treatment"), new Duration(1.5), new Amount(88.9)).addServiceCode("SC007"),
            new Service(new Title("Manicure"), new Duration(1.0), new Amount(21.5)).addServiceCode("SC005"),
        };
    }

    public static Expense[] getSampleExpenses() {
        return new Expense[]{
            new Expense(new Description("Conditioner"), new IsFixed("f"), new Amount(15.0),
                    new Date("10-10-2020"), new Tag("HairSupplies")),
            new Expense(new Description("Lash Tint"), new IsFixed("f"), new Amount(20.0),
                    new Date("10-12-2020"), new Tag("LashSupplies")),
            new Expense(new Description("Nail Polish"), new IsFixed("f"), new Amount(10.0),
                    new Date("09-12-2020"), new Tag("NailSupplies")),
            new Expense(new Description("Chair"), new IsFixed("t"), new Amount(25.0),
                    new Date("10-10-2020"), new Tag("Equipment")),
            new Expense(new Description("Lash Extension Glue"), new IsFixed("f"), new Amount(45.0),
                    new Date("01-12-2020"), new Tag("LashSupplies")),
        };
    }

    public static ReadOnlyServiceManager getSampleServiceManager() {
        ServiceManager sampleServiceManager = new ServiceManager();
        for (Service sampleService : getSampleServices()) {
            sampleServiceManager.addService(sampleService);
        }
        return sampleServiceManager;
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Client sampleClient : getSampleClients()) {
            sampleAb.addClient(sampleClient);
        }
        return sampleAb;
    }

    public static ReadOnlyExpenseTracker getSampleExpenseTracker() {
        ExpenseTracker sampleExpenseTracker = new ExpenseTracker();
        for (Expense sampleExpense : getSampleExpenses()) {
            sampleExpenseTracker.addExpense(sampleExpense);
        }
        return sampleExpenseTracker;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
            .map(Tag::new)
            .collect(Collectors.toSet());
    }
}
