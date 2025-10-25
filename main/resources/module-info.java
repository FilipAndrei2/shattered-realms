module com.shr.shr {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;  // Adaugă aceasta linie pentru javafx.media

    requires org.controlsfx.controls;
    requires com.almasb.fxgl.all;

    opens com.shr.shr to javafx.fxml;
    exports com.shr.shr;
}
