package ru.xenya.market.ui;

import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.router.*;
import org.springframework.beans.factory.annotation.Autowired;
import ru.xenya.market.ui.components.AppNavigation;
import ru.xenya.market.ui.components.MarketCookieConsent;
import ru.xenya.market.ui.components.common.ConfirmationDialog;
import ru.xenya.market.ui.entities.PageInfo;
import ru.xenya.market.ui.views.HasConfirmation;

import java.util.ArrayList;
import java.util.List;

import static ru.xenya.market.ui.utils.MarketConst.*;


@PageTitle("Учет рекламы в газете \"Магнитогорский металл\"")
@Viewport(VIEWPORT)
@Route(PAGE_ROOT)
public class MainView extends VerticalLayout implements RouterLayout, BeforeEnterObserver {

    @Id("appNavigation")
    private AppNavigation appNavigation = new AppNavigation();


    //private final ConfirmationDialog confirmationDialog;
    private final ConfirmationDialog confirmationDialog;

    @Autowired
    public MainView() {
        this.confirmationDialog = new ConfirmationDialog();

        List<PageInfo> pages = new ArrayList<>();
        pages.add(new PageInfo(PAGE_STOREFRONT, ICON_STOREFRONT, TITLE_STOREFRONT));
        pages.add(new PageInfo(PAGE_CUSTOMERS, ICON_CUSTOMERS, TITLE_CUSTOMERS));
//        if (SecurityUtils.isAccessGranted(UsersView.class)){
//            pages.add(new PageInfo(PAGE_USERS, ICON_USERS, TITLE_USERS));
//        }
        pages.add(new PageInfo(PAGE_LOGOUT, ICON_LOGOUT, TITLE_LOGOUT));
        appNavigation.init(pages, PAGE_DEFAULT, PAGE_LOGOUT);
        add(appNavigation);

        getElement().appendChild(confirmationDialog.getElement());
        getElement().appendChild(new MarketCookieConsent().getElement());
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {

//        if (SecurityUtils.isAccessGranted(event.getNavigationTarget())) {
//            event.rerouteToError(AccessDeniedException.class);
//        }

    }

    @Override
    public void showRouterLayoutContent(HasElement content) {
        if (content != null) {
            getElement().appendChild(content.getElement());
        }
        this.confirmationDialog.setOpened(false);
        if (content instanceof HasConfirmation) {
            ((HasConfirmation) content).setConfirmDialog(this.confirmationDialog);
        }
    }

}