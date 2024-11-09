import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Layout extends VBox {
    private Label realTimeLabel;
    private Label securityStatusLabel;
    private Button optimizeButton;
    private TextArea optimizationStatusArea;
    private LineChart<Number, Number> energyChart;
    private XYChart.Series<Number, Number> energyDataSeries;
    private Button securityAlert;
    private int currentDateIndex;

    public Layout() {
        realTimeLabel = new Label("Real-Time energy Monitoring: 11-09-2024");
        securityStatusLabel = new Label("Security Status: OK");
        optimizeButton = new Button("Optimize Power");
        optimizationStatusArea = new TextArea();
        optimizationStatusArea.setEditable(false);
        optimizationStatusArea.setPrefHeight(90);
        securityAlert = new Button("Trigger Security Alert");
        energyDataSeries = new XYChart.Series<>();
        energyDataSeries.setName("Energy Data");

        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();

        xAxis.setLabel("Time (mins)");
        yAxis.setLabel("Energy consumption (kWh)");

        energyChart = new LineChart<>(xAxis, yAxis);
        energyChart.setTitle("Energy-consumption over time");
        energyChart.getData().add(energyDataSeries);

        realTimeDate();
        energyDataUpdate();

        setPadding(new javafx.geometry.Insets(10));
        setSpacing(15);
        getChildren().addAll(energyChart, realTimeLabel, securityStatusLabel, optimizeButton, optimizationStatusArea, securityAlert);

    }

    public void realTimeDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String currentDate = sdf.format(new Date());
        realTimeLabel.setText(currentDate);

    }

    public void energyDataUpdate() {
        Thread update = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(3000);
                    currentDateIndex++;
                    double usage = Math.random() * 5 + 1;
                    javafx.application.Platform.runLater(() -> {
                        energyDataSeries.getData().add(new XYChart.Data<>(currentDateIndex, usage));
                        realTimeDate();
                    });


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        update.setDaemon(true);
        update.start();
    }

    public void energyOptimization() {
        optimizationStatusArea.setText("");
    }

}
