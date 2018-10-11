package ru.xenya.market.ui.views;

import ru.xenya.market.ui.components.common.ConfirmationDialog;

public interface HasConfirmation {
    void setConfirmDialog(ConfirmationDialog confirmDialog);

    ConfirmationDialog getConfirmDialog();
}
