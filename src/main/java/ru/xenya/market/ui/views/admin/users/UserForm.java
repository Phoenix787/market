package ru.xenya.market.ui.views.admin.users;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import ru.xenya.market.backend.data.entity.User;
import ru.xenya.market.ui.components.common.AbstractEditorDialog;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

@SpringComponent
@UIScope
public class UserForm extends AbstractEditorDialog {

    private H3 title;

    private TextField email;

    private TextField first;
    private TextField last;
    private PasswordField password;
    private ComboBox<String> role;

  //  private final PasswordEncoder passwordEncoder;



    public UserForm(BiConsumer<User, Operation> itemSaver, Consumer<User> itemDeleter) {
        super("Пользователь", itemSaver, itemDeleter);
    }

    @Override
    protected void confirmDelete() {
        addFields();
    }

    private void addFields() {

    }
}
