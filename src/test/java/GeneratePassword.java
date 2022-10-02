import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeneratePassword {

    public static void main(String[] args) {
        String mekari123 = new BCryptPasswordEncoder().encode("admin123");
        System.out.println(mekari123);
    }

}
