package ru.xenya.market.ui.components;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.IronIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import ru.xenya.market.ui.entities.PageInfo;

import java.util.ArrayList;
import java.util.List;

public class AppNavigation extends VerticalLayout implements AfterNavigationObserver {

    @Id("tabs")
    private Tabs tabs = new Tabs();

    private List<String> hrefs = new ArrayList<>();
    private String logoutHref;
    private String defaultHref;
    private String currentHref;

    public void init(List<PageInfo> pages, String defaultHref, String logoutHref) {
        this.logoutHref = logoutHref;
        this.defaultHref = defaultHref;

        for (PageInfo page : pages) {
            Tab tab = new Tab(new Icon("vaadin", page.getIcon()), new Span(page.getTitle()));
            tab.getElement().setAttribute("theme", "icon-on-top");
            hrefs.add(page.getLink());
            tabs.add(tab);
        }
        tabs.addSelectedChangeListener(e -> navigate());
        add(tabs);
    }

    private void navigate() {
        int idx = tabs.getSelectedIndex();
        if (idx >= 0 && idx < hrefs.size()) {
            String href = hrefs.get(idx);
            if (href.equals(logoutHref)) {
                UI.getCurrent().getPage().executeJavaScript("location.assign('logout')");
            } else if (!href.equals(currentHref)) {
                UI.getCurrent().navigate(href);
            }
        }
    }


    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        String href = event.getLocation().getFirstSegment().isEmpty() ? defaultHref : event.getLocation().getFirstSegment();
        currentHref = href;
        tabs.setSelectedIndex(hrefs.indexOf(href));
    }
}
