package servicio;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import static com.google.gson.JsonParser.parseReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.System.Logger.Level;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.model;
import servicio.traductor;

/**
 *
 * @author EDGARD
 */
public class emociones {

    private static String api_key = "CRlRJjkHF8Lo97r5f00tr0OGbBQRM7gB7EGfgvmHkm4";
    private static String host = "https://apis.paralleldots.com/v4/";

    public static String emotion(String text) throws Exception {
        if (api_key != null) {
            String url = host + "emotion";
            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("api_key", api_key)
                    .addFormDataPart("text", text)
                    .addFormDataPart("lang_code", "en")
                    .build();
            Request request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .addHeader("cache-control", "no-cache")
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } else {
            return "{ \"Error\": \"API key does not exist\" }";
        }
    }

    public static void emoci(model mol) throws Exception {
        try {
            traductor.traducir(mol);
            String texto = mol.getTextIn();
            if (api_key != null) {
                String url = host + "emotion";
                OkHttpClient client = new OkHttpClient();
                RequestBody requestBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("api_key", api_key)
                        .addFormDataPart("text", texto)
                        .addFormDataPart("lang_code", "en")
                        .build();
                Request request = new Request.Builder()
                        .url(url)
                        .post(requestBody)
                        .addHeader("cache-control", "no-cache")
                        .build();
                Response response = client.newCall(request).execute();

                JsonObject jsonObject = JsonParser.parseString​(response.body().string()).getAsJsonObject();
                if (jsonObject.isJsonObject()) {
                    JsonObject rootobj = jsonObject.getAsJsonObject();
                    JsonObject emotion = rootobj.getAsJsonObject("emotion");
                    String Happy = emotion.get("Happy").getAsString();
                    String Fear = emotion.get("Fear").getAsString();
                    String Sad = emotion.get("Sad").getAsString();
                    String Excited = emotion.get("Excited").getAsString();
                    String Angry = emotion.get("Angry").getAsString();
                    String Bored = emotion.get("Bored").getAsString();
                    System.out.println("Resultado\n");
                    System.out.println(Happy + "\n" + Fear + "\n" + Sad + "\n" + Excited + "\n" + Angry + "\n" + Bored + "\n");
                    Float.parseFloat(Sad);
                    float Happy1 = Float.parseFloat(Happy);
                    float Fear1 = Float.parseFloat(Fear);
                    float Sad1 = Float.parseFloat(Sad);
                    float Excited1 = Float.parseFloat(Excited);
                    float Angry1 = Float.parseFloat(Angry);
                    float Bored1 = Float.parseFloat(Bored);
                    mol.setHappy(Happy1);
                    mol.setFear(Fear1);
                    mol.setSad(Sad1);
                    mol.setExcited(Excited1);
                    mol.setAngry(Angry1);
                    mol.setBored(Bored1);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Busqueda", "RUC no encontrado"));
        }

    }

    public static void main(String[] args) throws Exception {
        String texto = "soy victor la vida es triste, me quiero morir";
        String resultado = "";
        resultado = emotion(texto);
        System.out.println("resultado" + resultado);

        model mol = new model();
        mol.setTexto(texto);
        emoci(mol);
    }
}
