package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class AddProblemCommand extends Command{
    public static final String COMMAND_WORD = "report";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Report  a problem you have encountered in the " +
            "library. ";

    public static final String MESSAGE_SUCCESS = "Problem reported: %1$s";

    private final String problem;

    public AddProblemCommand(String problem) {
        requireNonNull(problem);
        this.problem = problem;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.addProblem(problem);
        return new CommandResult(String.format(MESSAGE_SUCCESS, problem));
    }
}
