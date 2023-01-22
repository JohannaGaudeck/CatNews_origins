module com.ode22.catnews_origins {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires com.fasterxml.jackson.databind;
    requires java.desktop;

    opens com.ode22.catnews_origins to javafx.fxml;
    opens com.ode22.catnews_origins.Dto to javafx.fxml;
    exports com.ode22.catnews_origins;
    exports com.ode22.catnews_origins.Dto;
    exports com.ode22.catnews_origins.Client;
    exports com.ode22.catnews_origins.Handler;
    opens com.ode22.catnews_origins.Handler to javafx.fxml;
}