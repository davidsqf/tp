package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;


import java.util.HashMap;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.category.Category;
import seedu.address.model.book.*;
import seedu.address.model.book.Book;


/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ISBN, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_CATEGORY, PREFIX_STOCKING, PREFIX_TIMES, PREFIX_AUTHOR, PREFIX_PUBLISHER);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_ISBN, PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        System.out.println("debug");
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Isbn isbn = ParserUtil.parseIsbn(argMultimap.getValue(PREFIX_ISBN).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Times times = ParserUtil.parseTime(argMultimap.getValue(PREFIX_TIMES).get()); // add command does not allow adding remarks straight away
        Set<Category> categoryList = ParserUtil.parseCategories(argMultimap.getAllValues(PREFIX_CATEGORY));

        Stocking stocking = ParserUtil.parseStocking(argMultimap.getValue(PREFIX_STOCKING).get());

        Author author = ParserUtil.parseAuthor(argMultimap.getValue(PREFIX_AUTHOR).get()); // to be implemented

        Publisher publisher = ParserUtil.parsePublisher(argMultimap.getValue(PREFIX_PUBLISHER).get());

        Book book = new Book(name, isbn, email, address, times, categoryList, stocking,
                author, publisher);

        return new AddCommand(book);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
