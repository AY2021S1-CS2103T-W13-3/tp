package seedu.address.logic.commands.appointment;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;

public class DoneAppointmentCommand extends Command {
    public static final String COMMAND_WORD = "done";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Marks the appointment identified by the index number used in the displayed appointment list as done.\n"
        + "Parameters: INDEX (must be a positive integer)\n"
        + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DONE_APPOINTMENT_SUCCESS = "Marked Appointment as done: %1$s";

    private final Index targetIndex;

    public DoneAppointmentCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Appointment> lastShownList = model.getFilteredAppointmentList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }

        Appointment appointmentToMarkDone = lastShownList.get(targetIndex.getZeroBased());
        appointmentToMarkDone.markDone();
        // TODO Add Revenue entry based on appointment.
        return new CommandResult(String.format(MESSAGE_DONE_APPOINTMENT_SUCCESS, appointmentToMarkDone));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof DoneAppointmentCommand // instanceof handles nulls
            && targetIndex.equals(((DoneAppointmentCommand) other).targetIndex)); // state check
    }
}
