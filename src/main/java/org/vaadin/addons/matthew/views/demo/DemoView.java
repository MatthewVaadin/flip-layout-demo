package org.vaadin.addons.matthew.views.demo;

import org.vaadin.addons.matthew.fliplayout.FlipLayout;
import org.vaadin.addons.matthew.fliplayout.FlipLayoutVariant;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.card.Card;
import com.vaadin.flow.component.card.CardVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;

@PageTitle("Demo")
@Route("")
public class DemoView extends VerticalLayout {

    public DemoView() {
        add(new H1("Flip Layout Demo"));
        add(new Span("Click on the card to flip it."));

        Card frontCard = new Card();
        frontCard.addThemeVariants(CardVariant.LUMO_COVER_MEDIA);
        frontCard.setWidth("400px");
        frontCard.setHeight("300px");
        StreamResource imageResource = new StreamResource("lapland.avif",
                () -> getClass().getResourceAsStream("/images/lapland.avif"));
        Image image = new Image(imageResource, "Lapland");
        frontCard.setMedia(image);
        frontCard.setTitle(new Div("Lapland"));

        Card backCard = new Card();
        backCard.setWidth("400px");
        backCard.setHeight("300px");
        backCard.add("Lapland is the northern-most region of Finland and an active outdoor destination.");

        FlipLayout flipLayout = new FlipLayout(frontCard, backCard);
        flipLayout.addClickListener(event -> flipLayout.flip());

        add(flipLayout);

        RadioButtonGroup<String> direction = new RadioButtonGroup<>("Direction", "right", "left", "down", "up");
        direction.setItemLabelGenerator(item -> item.substring(0, 1).toUpperCase() + item.substring(1));
        direction.setValue("right");
        direction.addValueChangeListener(event -> flipLayout.setDirection(event.getValue()));
        add(direction);

        RadioButtonGroup<String> speed = new RadioButtonGroup<>("Speed", "Fast", "Medium", "Slow");
        speed.setValue("Medium");
        speed.addValueChangeListener(event -> {
            switch (event.getValue()) {
                case "Fast" -> {
                    flipLayout.removeThemeVariants(FlipLayoutVariant.SLOW);
                    flipLayout.addThemeVariants(FlipLayoutVariant.FAST);
                }
                case "Slow" -> {
                    flipLayout.removeThemeVariants(FlipLayoutVariant.FAST);
                    flipLayout.addThemeVariants(FlipLayoutVariant.SLOW);
                }
                default ->  flipLayout.removeThemeVariants(FlipLayoutVariant.FAST, FlipLayoutVariant.SLOW);
            }
        });
        add(speed);

        add(new H3("Different sized content"));
        add(new Span("Click on the button to flip the layout."));

        FlipLayout flipLayout2 = new FlipLayout();
        VerticalLayout frontForm = new VerticalLayout();
        frontForm.getStyle().setBoxShadow("0 2px 10px rgba(0, 0, 0, 0.1)");
        TextField firstNameField = new TextField("First name");
        TextField lastNameField = new TextField("Last name");
        EmailField emailField = new EmailField("Email");
        Button flipButton = new Button(VaadinIcon.ARROW_FORWARD.create());
        flipButton.addClickListener(event -> flipLayout2.flip());
        frontForm.add(firstNameField, lastNameField, emailField, flipButton);
        flipLayout2.setFrontComponent(frontForm);

        VerticalLayout backForm = new VerticalLayout();
        backForm.getStyle().setBoxShadow("0 2px 10px rgba(0, 0, 0, 0.1)");
        TextField addressField = new TextField("Address");
        addressField.setWidth("400px");
        Button unflipButton = new Button(VaadinIcon.ARROW_BACKWARD.create());
        unflipButton.addClickListener(event -> flipLayout2.flip());
        backForm.add(addressField, unflipButton);
        flipLayout2.setBackComponent(backForm);

        add(flipLayout2);
    }

}
