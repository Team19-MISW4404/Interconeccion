package model.data_structures;

import java.util.Comparator;
import java.util.Objects;

public class Country implements Comparable<Country> {
    private String countryName;
    private String capitalName;
    private double latitude;
    private double longitude;
    private String code;
    private String continentName;
    private float population;
    private double users;
    private double distlan;

    private Country(Builder builder) {
        this.countryName = builder.countryName;
        this.capitalName = builder.capitalName;
        this.latitude = builder.latitude;
        this.longitude = builder.longitude;
        this.code = builder.code;
        this.continentName = builder.continentName;
        this.population = builder.population;
        this.users = builder.users;
        this.distlan = builder.distlan;
    }

    public static class Builder {
        private String countryName;
        private String capitalName;
        private double latitude;
        private double longitude;
        private String code;
        private String continentName;
        private float population;
        private double users;
        private double distlan = 0; // Default value

        public Builder countryName(String countryName) {
            this.countryName = countryName;
            return this;
        }

        public Builder capitalName(String capitalName) {
            this.capitalName = capitalName;
            return this;
        }

        public Builder latitude(double latitude) {
            this.latitude = latitude;
            return this;
        }

        public Builder longitude(double longitude) {
            this.longitude = longitude;
            return this;
        }

        public Builder code(String code) {
            this.code = code;
            return this;
        }

        public Builder continentName(String continentName) {
            this.continentName = continentName;
            return this;
        }

        public Builder population(float population) {
            this.population = population;
            return this;
        }

        public Builder users(double users) {
            this.users = users;
            return this;
        }

        public Builder distlan(double distlan) {
            this.distlan = distlan;
            return this;
        }

        public Country build() {
            return new Country(this);
        }
    }

    // Getter and setter methods
    public String getCountryName() {
        return countryName;
    }

    public String getCapitalName() {
        return capitalName;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getCode() {
        return code;
    }

    public String getContinentName() {
        return continentName;
    }

    public float getPopulation() {
        return population;
    }

    public double getUsers() {
        return users;
    }

    public double getDistlan() {
        return distlan;
    }

    @Override
    public int compareTo(Country o) {
        if (o == null) {
            throw new NullPointerException("Comparing object is null");
        }
        return this.countryName.compareTo(o.countryName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Country country = (Country) obj;
        return Objects.equals(countryName, country.countryName) &&
               Objects.equals(capitalName, country.capitalName) &&
               Objects.equals(code, country.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryName, capitalName, code);
    }

    // Comparador por distancia
    public static class ComparadorXKm implements Comparator<Country> {
        @Override
        public int compare(Country pais1, Country pais2) {
            return Double.compare(pais1.getDistlan(), pais2.getDistlan());
        }
    }

    // Comparador por nombre
    public static class ComparadorXNombre implements Comparator<Country> {
        @Override
        public int compare(Country pais1, Country pais2) {
            return pais1.getCountryName().compareTo(pais2.getCountryName());
        }
    }
}
