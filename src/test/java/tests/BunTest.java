package tests;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import praktikum.Bun;

public class BunTest {

    @Test
    public void testBunWithEmptyNameAndZeroPrice() {
        Bun bun = new Bun("", 0f);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(bun.getName())
                .as("Имя булочки должно быть пустой строкой")
                .isEqualTo("");

        softly.assertThat(bun.getPrice())
                .as("Цена булочки должна быть 0.0")
                .isCloseTo(0f, within(0.01f));

        softly.assertAll();
    }

    @Test
    public void testBunWithNegativePrice() {
        Bun bun = new Bun("Negative Bun", -100f);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(bun.getName())
                .as("Имя булочки должно быть 'Negative Bun'")
                .isEqualTo("Negative Bun");

        softly.assertThat(bun.getPrice())
                .as("Цена булочки должна быть -100.0")
                .isCloseTo(-100f, within(0.01f));

        softly.assertAll();
    }

    private static org.assertj.core.data.Offset<Float> within(float value) {
        return org.assertj.core.data.Offset.offset(value);
    }
}
