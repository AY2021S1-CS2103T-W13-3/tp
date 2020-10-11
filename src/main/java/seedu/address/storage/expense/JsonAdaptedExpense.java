package seedu.address.storage.expense;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.expense.Expense;
import seedu.address.model.expense.IsFixed;
import seedu.address.model.util.attributes.Amount;
import seedu.address.model.util.attributes.Date;
import seedu.address.model.util.attributes.Description;
import seedu.address.model.util.attributes.Tag;

/**
 * Jackson-friendly version of {@link Expense}.
 */
public class JsonAdaptedExpense {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Expense's %s field is missing!";

    private final BigDecimal value;
    private final String date;
    private final String description;
    private final String isFixed;
    private String tag;

    /**
     * Constructs a {@code JsonAdaptedService} with the given Service details.
     */
    @JsonCreator
    public JsonAdaptedExpense(@JsonProperty("value") double value, @JsonProperty("date") String date,
                              @JsonProperty("description") String description,
                              @JsonProperty("isFixed") String isFixed, @JsonProperty("tag") String tag) {
        this.value = new BigDecimal(value);
        this.date = date;
        this.description = description;
        this.isFixed = isFixed;
        this.tag = tag;
    }

    /**
     * Converts a given {@code Expense} into this class for Jackson use.
     */
    public JsonAdaptedExpense(Expense source) {
        value = source.getValue().value;
        date = source.getDate().toString();
        description = source.getDescription().value;
        isFixed = source.getIsFixed().value ? "t" : "f";
        tag = source.getTag().toString();
    }

    /**
     * Converts this Jackson-friendly adapted expense object into the model's {@code Expense} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted expense.
     */
    public Expense toModelType() throws IllegalValueException {
        if (value == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Amount.class.getSimpleName()));
        }
        if (!Amount.isValidAmount(value.doubleValue())) {
            throw new IllegalValueException(Amount.MESSAGE_CONSTRAINTS);
        }
        final Amount modelValue = new Amount(value.doubleValue());

        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Date.class.getSimpleName()));
        }
        if (!Date.isValidDate(date)) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
        }
        final Date modelDate = new Date(date);

        if (isFixed == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, IsFixed.class.getSimpleName()));
        }
        if (!IsFixed.isValidIsFixed(isFixed)) {
            throw new IllegalValueException(IsFixed.MESSAGE_CONSTRAINTS);
        }
        final IsFixed modelIsFixed = new IsFixed(isFixed);

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        }
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelDescription = new Description(description);

        if (tag == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Tag.class.getSimpleName()));
        }
        if (!Tag.isValidTagName(tag)) {
            throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
        }
        final Tag modelTag = new Tag(tag);
        Expense expense = new Expense(modelDescription, modelIsFixed, modelValue, modelDate, modelTag);

        return expense;
    }
}