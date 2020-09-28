package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BIRTHDAY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.Birthday;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TypicalPersons;


class BirthdayCommandTest {
    private static final String Birthday_STUB = "some birthday";

    private Model model = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_addBirthdayUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withBirthday(Birthday_STUB).build();

        BirthdayCommand birthdayCommand = new BirthdayCommand(INDEX_FIRST_PERSON, new Birthday(editedPerson.getBirthday().value));

        String expectedMessage = String.format(BirthdayCommand.MESSAGE_ADD_BIRTHDAY_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(birthdayCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteBirthdayUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withBirthday("").build();

        BirthdayCommand birthdayCommand = new BirthdayCommand(INDEX_FIRST_PERSON,
                new Birthday(editedPerson.getBirthday().toString()));

        String expectedMessage = String.format(BirthdayCommand.MESSAGE_DELETE_BIRTHDAY_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(birthdayCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()))
                .withBirthday(BIRTHDAY_STUB).build();

        BirthdayCommand birthdayCommand = new BirthdayCommand(INDEX_FIRST_PERSON, new Birthday(editedPerson.getBirthday().value));

        String expectedMessage = String.format(BirthdayCommand.MESSAGE_ADD_BIRTHDAY_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(birthdayCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        BirthdayCommand birthdayCommand = new BirthdayCommand(outOfBoundIndex, new Birthday(VALID_BIRTHDAY_BOB));

        assertCommandFailure(birthdayCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        BirthdayCommand birthdayCommand = new BirthdayCommand(outOfBoundIndex, new Birthday(VALID_BIRTHDAY_BOB));

        assertCommandFailure(birthdayCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }
}