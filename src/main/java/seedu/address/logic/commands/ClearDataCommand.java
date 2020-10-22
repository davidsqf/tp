package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.book.Book;

public class ClearDataCommand extends Command{
    public static final String COMMAND_WORD = "purge";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Purges all sample data pre-stored in the app.\n";

    public static final String MESSAGE_CLEAR_SUCCESS = "Purged all sample data.";

    /**
     * Purges all sample data.
     */
    public ClearDataCommand() {

    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Book> lastShownList = model.getFilteredBookList();
        List<Book> deleteList = new ArrayList<>();

        for (Book book: lastShownList) {
            if (book.getName().fullName.equals("Pride and Prejudice")
                || book.getName().fullName.equals("A Brief History Of Time From Big Bang To Black Holes")) {
                    deleteList.add(book);
                }
        }

        if (deleteList.size() == 0) {
            throw new CommandException(Messages.MESSAGE_NO_DATA_TO_CLEAR);
        }

        for (Book book : deleteList) {
            model.deleteBook(book);
        }

        return new CommandResult(String.format(MESSAGE_CLEAR_SUCCESS, deleteList.toString()));
    }
}
