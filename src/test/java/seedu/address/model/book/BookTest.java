package seedu.address.model.book;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ISBN_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LANGUAGE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalBooks.BOOK1;
import static seedu.address.testutil.TypicalBooks.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.BookBuilder;

public class BookTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Book book = new BookBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> book.getCategories().remove(0));
    }

    @Test
    public void isSameBook() {
        // same object -> returns true
        assertTrue(BOOK1.isSameBook(BOOK1));

        // null -> returns false
        assertFalse(BOOK1.isSameBook(null));

        // different isbn and email -> returns false
        Book editedAlice = new BookBuilder(BOOK1).withIsbn(VALID_ISBN_BOB).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(BOOK1.isSameBook(editedAlice));

        // different name -> returns false
        editedAlice = new BookBuilder(BOOK1).withName(VALID_NAME_BOB).build();
        assertFalse(BOOK1.isSameBook(editedAlice));

        // same name, same isbn, different attributes -> returns true
        editedAlice = new BookBuilder(BOOK1).withEmail(VALID_EMAIL_BOB).withLanguage(VALID_LANGUAGE_BOB)
                .withCategories(VALID_CATEGORY_HUSBAND).build();
        assertTrue(BOOK1.isSameBook(editedAlice));

        // same name, same email, different attributes -> returns true
        editedAlice = new BookBuilder(BOOK1).withIsbn(VALID_ISBN_BOB).withLanguage(VALID_LANGUAGE_BOB)
                .withCategories(VALID_CATEGORY_HUSBAND).build();
        assertTrue(BOOK1.isSameBook(editedAlice));

        // same name, same isbn, same email, different attributes -> returns true
        editedAlice = new BookBuilder(BOOK1).withLanguage(VALID_LANGUAGE_BOB)
                .withCategories(VALID_CATEGORY_HUSBAND).build();
        assertTrue(BOOK1.isSameBook(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Book aliceCopy = new BookBuilder(BOOK1).build();
        assertTrue(BOOK1.equals(aliceCopy));

        // same object -> returns true
        assertTrue(BOOK1.equals(BOOK1));

        // null -> returns false
        assertFalse(BOOK1.equals(null));

        // different type -> returns false
        assertFalse(BOOK1.equals(5));

        // different book -> returns false
        assertFalse(BOOK1.equals(BOB));

        // different name -> returns false
        Book editedAlice = new BookBuilder(BOOK1).withName(VALID_NAME_BOB).build();
        assertFalse(BOOK1.equals(editedAlice));

        // different isbn -> returns false
        editedAlice = new BookBuilder(BOOK1).withIsbn(VALID_ISBN_BOB).build();
        assertFalse(BOOK1.equals(editedAlice));

        // different email -> returns false
        editedAlice = new BookBuilder(BOOK1).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(BOOK1.equals(editedAlice));

        // different language -> returns false
        editedAlice = new BookBuilder(BOOK1).withLanguage(VALID_LANGUAGE_BOB).build();
        assertFalse(BOOK1.equals(editedAlice));

        // different categories -> returns false
        editedAlice = new BookBuilder(BOOK1).withCategories(VALID_CATEGORY_HUSBAND).build();
        assertFalse(BOOK1.equals(editedAlice));
    }
}
