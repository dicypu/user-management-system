package config;

/**
 * Singleton Deseni: Uygulama genelinde sistem ayarlarını tek bir merkezden yönetir.
 * Bill Pugh Singleton yaklaşımı kullanılarak Thread-Safe ve Lazy Loading yapıda kurulmuştur.
 */
public class AppConfig {

    private String applicationName;
    private String environment;
    private int maxConnections;

    // 1. Dışarıdan 'new AppConfig()' çağrısını engellemek için private constructor
    private AppConfig() {
        this.applicationName = "User Management System";
        this.environment = "DEVELOPMENT";
        this.maxConnections = 10;
        System.out.println("[AppConfig] Konfigürasyon nesnesi belleğe yüklendi (Single Instance).");
    }

    // 3. Tekil nesneye genel erişim noktası
    public static AppConfig getInstance() {
        return SingletonHelper.INSTANCE;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public String getEnvironment() {
        return environment;
    }

    public int getMaxConnections() {
        return maxConnections;
    }

    // 2. JVM sınıf yükleyicisi (ClassLoader) tarafından thread-safe başlatılan statik iç sınıf
    private static class SingletonHelper {
        private static final AppConfig INSTANCE = new AppConfig();
    }
}