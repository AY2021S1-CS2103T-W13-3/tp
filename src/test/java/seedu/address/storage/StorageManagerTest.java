package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.TypicalClients.getTypicalClientManager;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.UserPrefs;
import seedu.address.model.manager.ClientManager;
import seedu.address.model.manager.ReadOnlyClientManager;
import seedu.address.storage.appointment.AppointmentStorage;
import seedu.address.storage.appointment.JsonAppointmentStorage;
import seedu.address.storage.client.JsonClientStorage;
import seedu.address.storage.expense.ExpenseStorage;
import seedu.address.storage.expense.JsonExpenseStorage;
import seedu.address.storage.revenue.JsonRevenueStorage;
import seedu.address.storage.revenue.RevenueStorage;
import seedu.address.storage.service.JsonServiceStorage;
import seedu.address.storage.service.ServiceStorage;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonClientStorage clientManagerStorage = new JsonClientStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        ServiceStorage serviceStorage = new JsonServiceStorage(getTempFilePath("services"));
        RevenueStorage revenueStorage = new JsonRevenueStorage(getTempFilePath("revenues"));
        ExpenseStorage expenseStorage = new JsonExpenseStorage(getTempFilePath("expenses"));
        AppointmentStorage appointmentStorage = new JsonAppointmentStorage(getTempFilePath("appointments"));

        storageManager = new StorageManager(userPrefsStorage, clientManagerStorage, serviceStorage,
                revenueStorage, expenseStorage, appointmentStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void clientManagerReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonClientStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonClientStorageTest} class.
         */
        ClientManager original = getTypicalClientManager();
        storageManager.saveClientManager(original);
        ReadOnlyClientManager retrieved = storageManager.readClientManager().get();
        assertEquals(original, new ClientManager(retrieved));
    }

    @Test
    public void getAddressBookFilePath() {
        assertNotNull(storageManager.getClientManagerFilePath());
    }

}
