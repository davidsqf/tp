package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

/**
 * An UI component that displays detailed information of a {@code Person}.
 */
public class LibraryBookDetailCard extends UiPart<Region> {
    private static final String FXML = "LibraryBookDetailCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @javafx.fxml.FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label isbn;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label times;
    @FXML
    private Label author;
    @FXML
    private FlowPane categories;
    @FXML
    private FlowPane stocking;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public LibraryBookDetailCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        isbn.setText(person.getIsbn().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        times.setText(person.getTimes().value);
        person.getCategories().stream()
                .sorted(Comparator.comparing(category -> category.categoryName))
                .forEach(category -> categories.getChildren().add(new Label(category.categoryName)));
        person.getStocking().storage.forEach((location, storage) -> {
            if (storage > 0) {
                stocking.getChildren().add(new Label(location + ": " + storage + " "));
            }
        });
        author.setText(person.getAuthor().author);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof LibraryBookDetailCard)) {
            return false;
        }

        // state check
        LibraryBookDetailCard card = (LibraryBookDetailCard) other;
        return id.getText().equals(card.id.getText())
                && person.equals(card.person);
    }
}