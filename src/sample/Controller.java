package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import javax.swing.JOptionPane;


public class Controller {

    @FXML
    private Text pressure;

    @FXML
    private TextField city;

    @FXML
    private Button getData;

    @FXML
    private Text temp_feels;

    @FXML
    private Text temp_info;

    @FXML
    private Text temp_max;

    @FXML
    private Text temp_min;

    @FXML
    void initialize() {
        getData.setOnAction(event -> {
            String getUserCityInput = city.getText().trim();
            String output = getUrlContent("https://api.openweathermap.org/data/2.5/weather?q=" + getUserCityInput + "&appid=3ca6a6ff15505238a2188b98eca6ae93&units=metric");
            // Преобразование данных из json и вывод в соответсвующих текстовых полях
            if (!output.isEmpty()) {
                JSONObject obj = new JSONObject(output);
                temp_info.setText("Температура: " + obj.getJSONObject("main").getDouble("temp"));
                temp_feels.setText("Ощущается: " + obj.getJSONObject("main").getDouble("feels_like"));
                temp_max.setText("Максимум: " + obj.getJSONObject("main").getDouble("temp"));
                temp_min.setText("Минимум: " + obj.getJSONObject("main").getDouble("temp"));
                pressure.setText("Давление: " + obj.getJSONObject("main").getDouble("pressure"));
            }
        });
    }

    //Обращение по url openweathermap.org и получение данных с сайта
    //Открывается соединение, поток на чтение данных, после чего данные записываются в отформатированную строку
    //В случае ошибочного ввода пользователя выводится сообщение
    private static String getUrlContent(String urlAddress) {
        StringBuffer content = new StringBuffer();
        try {
            URL url = new URL(urlAddress);
            URLConnection urlConn = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ошибка ввода",
                    "Ошибка",
                    JOptionPane.WARNING_MESSAGE);
        }
        return content.toString();
    }
}
