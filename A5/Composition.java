package task5.A5;

import java.util.Objects;

public class Composition {

    private Mass iron;
    private Mass silicate;
    private Mass argon;
    private Mass sulfurDioxide;
    private Mass oxygen;
    private Mass carbonDioxide;
    private Mass water;
    private Mass nitrogen;
    private Mass ammonia;
    private Mass methane;
    private Mass helium;
    private Mass hydrogen;

    public Composition(Mass iron, Mass silicate, Mass argon, Mass sulfurDioxide, Mass oxygen,
                       Mass carbonDioxide, Mass water, Mass nitrogen, Mass ammonia, Mass methane,
                       Mass helium, Mass hydrogen) {
        this.iron = iron;
        this.silicate = silicate;
        this.argon = argon;
        this.sulfurDioxide = sulfurDioxide;
        this.oxygen = oxygen;
        this.carbonDioxide = carbonDioxide;
        this.water = water;
        this.nitrogen = nitrogen;
        this.ammonia = ammonia;
        this.methane = methane;
        this.helium = helium;
        this.hydrogen = hydrogen;
    }

    public static Composition ZERO_COMPOSITION() {
        return new Composition(
                Mass.ZERO_MASS(),
                Mass.ZERO_MASS(),
                Mass.ZERO_MASS(),
                Mass.ZERO_MASS(),
                Mass.ZERO_MASS(),
                Mass.ZERO_MASS(),
                Mass.ZERO_MASS(),
                Mass.ZERO_MASS(),
                Mass.ZERO_MASS(),
                Mass.ZERO_MASS(),
                Mass.ZERO_MASS(),
                Mass.ZERO_MASS()
        );
    }

    @Override
    public String toString() {
        Mass ZERO_MASS = Mass.ZERO_MASS();
        String compositionString = "Состав{" +
                (iron.equals(ZERO_MASS) ? "" :
                        "железо: %6.2e, ".formatted(iron.getKilogram())) +
                (silicate.equals(ZERO_MASS) ? "" :
                        "кремний: %6.2e, ".formatted(silicate.getKilogram())) +
                (argon.equals(ZERO_MASS) ? "" :
                        "аргон: %6.2e, ".formatted(argon.getKilogram())) +
                (sulfurDioxide.equals(ZERO_MASS) ? "" :
                        "оксид серы(IV): %6.2e, ".formatted(sulfurDioxide.getKilogram())) +
                (oxygen.equals(Mass.ZERO_MASS()) ? "" :
                        "кислород: %6.2e, ".formatted(oxygen.getKilogram())) +
                (carbonDioxide.equals(ZERO_MASS) ? "" :
                        "оксид углерода(IV): %6.2e, ".formatted(carbonDioxide.getKilogram())) +
                (water.equals(ZERO_MASS) ? "" :
                        "вода: %6.2e, ".formatted(water.getKilogram())) +
                (nitrogen.equals(ZERO_MASS) ? "" :
                        "азот: %6.2e, ".formatted(nitrogen.getKilogram())) +
                (ammonia.equals(ZERO_MASS) ? "" :
                        "аммиак: %6.2e, ".formatted(ammonia.getKilogram())) +
                (methane.equals(ZERO_MASS) ? "" :
                        "метан: %6.2e, ".formatted(methane.getKilogram())) +
                (helium.equals(ZERO_MASS) ? "" :
                        "гелий: %6.2e, ".formatted(helium.getKilogram())) +
                (hydrogen.equals(ZERO_MASS) ? "" :
                        "водород: %6.2e".formatted(hydrogen.getKilogram())) +
                "}";
        return (compositionString.indexOf(':') == compositionString.lastIndexOf(':') ?
                compositionString.substring(0, compositionString.length() - 3) + '}' :
                compositionString);
    }

    public Mass getIron() {
        return iron;
    }

    public void setIron(Mass iron) {
        this.iron = iron;
    }

    public Mass getSilicate() {
        return silicate;
    }

    public void setSilicate(Mass silicate) {
        this.silicate = silicate;
    }

    public Mass getArgon() {
        return argon;
    }

    public void setArgon(Mass argon) {
        this.argon = argon;
    }

    public Mass getSulfurDioxide() {
        return sulfurDioxide;
    }

    public void setSulfurDioxide(Mass sulfurDioxide) {
        this.sulfurDioxide = sulfurDioxide;
    }

    public Mass getOxygen() {
        return oxygen;
    }

    public void setOxygen(Mass oxygen) {
        this.oxygen = oxygen;
    }

    public Mass getCarbonDioxide() {
        return carbonDioxide;
    }

    public void setCarbonDioxide(Mass carbonDioxide) {
        this.carbonDioxide = carbonDioxide;
    }

    public Mass getWater() {
        return water;
    }

    public void setWater(Mass water) {
        this.water = water;
    }

    public Mass getNitrogen() {
        return nitrogen;
    }

    public void setNitrogen(Mass nitrogen) {
        this.nitrogen = nitrogen;
    }

    public Mass getAmmonia() {
        return ammonia;
    }

    public void setAmmonia(Mass ammonia) {
        this.ammonia = ammonia;
    }

    public Mass getMethane() {
        return methane;
    }

    public void setMethane(Mass methane) {
        this.methane = methane;
    }

    public Mass getHelium() {
        return helium;
    }

    public void setHelium(Mass helium) {
        this.helium = helium;
    }

    public Mass getHydrogen() {
        return hydrogen;
    }

    public void setHydrogen(Mass hydrogen) {
        this.hydrogen = hydrogen;
    }


}
