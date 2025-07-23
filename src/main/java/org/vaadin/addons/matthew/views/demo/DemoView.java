package org.vaadin.addons.matthew.views.demo;

import java.util.Random;

import org.vaadin.addons.matthew.fliplayout.FlipLayout;
import org.vaadin.addons.matthew.fliplayout.FlipLayoutVariant;
import org.vaadin.addons.taefi.component.ToggleButtonGroup;

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
import com.vaadin.flow.server.streams.DownloadHandler;

import static org.vaadin.addons.matthew.fliplayout.FlipLayout.Direction.DOWN;
import static org.vaadin.addons.matthew.fliplayout.FlipLayout.Direction.LEFT;
import static org.vaadin.addons.matthew.fliplayout.FlipLayout.Direction.RIGHT;
import static org.vaadin.addons.matthew.fliplayout.FlipLayout.Direction.UP;

@PageTitle("Flip Layout Demo")
@Route("")
public class DemoView extends VerticalLayout {

    public DemoView() {
        add(new H1("Flip Layout Demo"));
        add(new Span("Click on the card to flip it."));

        Card frontCard = new Card();
        frontCard.addThemeVariants(CardVariant.LUMO_COVER_MEDIA);
        frontCard.setWidth("400px");
        frontCard.setHeight("300px");
        Image image = new Image(DownloadHandler.forClassResource(
                DemoView.class, "/images/lapland.avif"), "Lapland");
        frontCard.setMedia(image);
        frontCard.setTitle(new Div("Lapland"));

        Card backCard = new Card();
        backCard.setWidth("400px");
        backCard.setHeight("300px");
        backCard.add("Lapland is the northern-most region of Finland and an active outdoor destination.");

        FlipLayout flipLayout = new FlipLayout(frontCard, backCard);
        flipLayout.addClickListener(event -> flipLayout.flip());

        add(flipLayout);

        RadioButtonGroup<FlipLayout.Direction> direction = new RadioButtonGroup<>("Direction", RIGHT, LEFT, DOWN, UP);
        direction.setItemLabelGenerator(item -> item.name().substring(0, 1).toUpperCase() +
                item.name().substring(1).toLowerCase());
        direction.setValue(RIGHT);
        direction.addValueChangeListener(event ->
                flipLayout.setDirection(event.getValue()));
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

        add(new H3("Flip listener"));
        add(new Span("Click on the toggle button to flip the layout."));

        FlipLayout flipLayout3 = new FlipLayout();
        flipLayout3.addThemeVariants(FlipLayoutVariant.FAST);
        Card card1 = new Card();
        card1.addThemeVariants(CardVariant.LUMO_COVER_MEDIA);
        card1.setWidth("400px");
        card1.setHeight("300px");
        Image laplandImage = new Image(DownloadHandler.forClassResource(
                DemoView.class, "/images/lapland.avif"), "Lapland");
        card1.setMedia(laplandImage);
        card1.setTitle(new Div("Lapland"));

        Card card2 = new Card();
        card2.addThemeVariants(CardVariant.LUMO_COVER_MEDIA);
        card2.setWidth("400px");
        card2.setHeight("300px");
        Image earthImage = new Image(DownloadHandler.forClassResource(
                DemoView.class, "/images/earth.avif"), "Earth");
        card2.setMedia(earthImage);
        card2.setTitle(new Div("Earth"));

        Card card3 = new Card();
        card3.addThemeVariants(CardVariant.LUMO_COVER_MEDIA);
        card3.setWidth("400px");
        card3.setHeight("300px");
        Image reindeerImage = new Image(DownloadHandler.forClassResource(
                DemoView.class, "/images/reindeer.avif"), "Reindeer");
        card3.setMedia(reindeerImage);
        card3.setTitle(new Div("Reindeer"));

        Card card4 = new Card();
        card4.addThemeVariants(CardVariant.LUMO_COVER_MEDIA);
        card4.setWidth("400px");
        card4.setHeight("300px");
        Image starrySkyImage = new Image(DownloadHandler.forClassResource(
                DemoView.class, "/images/starry-sky.avif"), "Starry Sky");
        card4.setMedia(starrySkyImage);
        card4.setTitle(new Div("Starry Sky"));

        Random random = new Random();
        flipLayout3.setFrontComponent(card1);
        flipLayout3.addFlipListener(event -> {
            Card[] cards = new Card[] { card1, card2, card3, card4 };
            if (event.isFlipped()) {
                // Pick a random card different from the current front
                Card currentFront = (Card) flipLayout3.getFrontComponent();
                Card randomCard;
                do {
                    int randomIndex = random.nextInt(cards.length);
                    randomCard = cards[randomIndex];
                } while (randomCard == currentFront);
                flipLayout3.setBackComponent(randomCard);
            } else {
                // Pick a random card different from the current back
                Card currentBack = (Card) flipLayout3.getBackComponent();
                Card randomCard;
                do {
                    int randomIndex = random.nextInt(cards.length);
                    randomCard = cards[randomIndex];
                } while (randomCard == currentBack);
                flipLayout3.setFrontComponent(randomCard);
            }
        });

        ToggleButtonGroup<Boolean> toggle = new ToggleButtonGroup<>();
        toggle.setItems(false, true);
        toggle.setValue(false);
        toggle.setToggleable(false);
        toggle.setItemLabelGenerator(value -> Boolean.FALSE.equals(value) ? "Front" : "Back");
        toggle.addValueChangeListener(event ->
                flipLayout3.setFlipped(toggle.getValue()));

        VerticalLayout example3 = new VerticalLayout(toggle, flipLayout3);
        example3.setWidth("fit-content");
        example3.setAlignItems(Alignment.CENTER);
        add(example3);
    }

}
