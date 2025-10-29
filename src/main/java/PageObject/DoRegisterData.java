package PageObject;

import java.util.Map;

public class DoRegisterData {

    String uniq = String.valueOf(System.currentTimeMillis());
    String email = "cu_" + uniq + "@mail.com";
    String name  = "Рест_" + uniq;
    String password = "Pass" + uniq;

    public Map<String, Object> body = Map.of(
            "email", email,
            "name",  name,
            "password", password
    );

}
