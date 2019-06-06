package pjp.akidamjaffar.config;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * @author JeiMchfundi
 */
public class GEP_Util<T> {
	private StandardServiceRegistry registry;
	private SessionFactory sessionFactory;
	private final Class<T> classObj;

	public GEP_Util(Class<T> classObj) {
		// TODO Auto-generated constructor stub
		this.classObj = classObj;
	}

	public SessionFactory getSessionFactory() {
		if (sessionFactory == null)
			try {
				final StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();

				final Map<String, String> settings = new HashMap<>();
				settings.put("hibernate.connection.driver_class", "org.apache.derby.jdbc.ClientDriver");
				settings.put("hibernate.connection.url", "jdbc:derby://localhost/root;create=true");
				settings.put("hibernate.connection.username", "SYS");
				settings.put("hibernate.connection.password", "Password@2906");
				settings.put("hibernate.show_sql", "true");
				settings.put("hibernate.hbm2ddl.auto", "update");
				settings.put("hibernate.default_schema", "ABS");
				settings.put("hibernate.connection.pool_size", "2");

				registryBuilder.applySettings(settings);

				registry = registryBuilder.build();

				final MetadataSources sources = new MetadataSources(registry).addAnnotatedClass(classObj);

				final Metadata metadata = sources.getMetadataBuilder().build();

				sessionFactory = metadata.getSessionFactoryBuilder().build();

			} catch (final Exception e) {
				e.printStackTrace();
				if (registry != null)
					StandardServiceRegistryBuilder.destroy(registry);
			}
		return sessionFactory;
	}

	public void shutdown() {
		if (registry != null)
			StandardServiceRegistryBuilder.destroy(registry);
	}
}