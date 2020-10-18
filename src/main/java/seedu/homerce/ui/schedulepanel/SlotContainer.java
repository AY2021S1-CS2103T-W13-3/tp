package seedu.homerce.ui.schedulepanel;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.homerce.ui.UiPart;

/**
 * A UI Component that that constitutes a region of a calendar view.
 */
public abstract class SlotContainer extends UiPart<Region> {

    public static final double WIDTH_SCALING_FACTOR = 2;

    @FXML
    protected HBox slotPane;

    public SlotContainer(String fxml, double minutes) {
        super(fxml);
        slotPane.setPrefWidth(minutes * WIDTH_SCALING_FACTOR);
    }
}