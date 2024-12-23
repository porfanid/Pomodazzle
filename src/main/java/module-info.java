module com.porfanid.pomodazzle {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.desktop;

    opens com.porfanid.pomodazzle to javafx.fxml;
    exports com.porfanid.pomodazzle;
    exports com.porfanid.pomodazzle.pomodoroHandler;
    opens com.porfanid.pomodazzle.pomodoroHandler to javafx.fxml;
}