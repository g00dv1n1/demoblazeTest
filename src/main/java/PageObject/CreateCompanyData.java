package PageObject;

import java.util.Map;

public class CreateCompanyData {

    String uniq = String.valueOf(System.currentTimeMillis());
    String email_owner = "vladislav.shiller@gmail.com";
    String company_name  = "Рест_" + uniq;
    enum type {ООО, ИП, ОАО};
    String company_type = type.values()[new java.util.Random().nextInt(type.values().length)].name();
    String [] company_users = {"cu_1761492935447@mail.com", "cu_1761492327947@mail.com"	};

    public Map<String, Object> body = Map.of(
            "company_name", company_name,
            "company_type",  company_type,
            "company_users", company_users,
            "email_owner", email_owner
    );

}
