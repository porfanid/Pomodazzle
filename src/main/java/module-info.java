module com.porfanid.pomodazzle {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires javafx.swing;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.desktop;
    requires javafx.media;

    opens com.porfanid.pomodazzle to javafx.fxml;
    exports com.porfanid.pomodazzle;
    opens com.porfanid.pomodazzle.pomodoroHandler to javafx.fxml;
    exports com.porfanid.pomodazzle.pomodoroHandler;

}