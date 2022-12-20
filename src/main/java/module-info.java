module com.ode22.catnews_origins {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires com.fasterxml.jackson.databind;

    opens com.ode22.catnews_origins to javafx.fxml;
    exports com.ode22.catnews_origins;
}