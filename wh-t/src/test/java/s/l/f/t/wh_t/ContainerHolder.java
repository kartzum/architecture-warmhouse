package s.l.f.t.wh_t;

import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;

import java.io.File;

@SuppressWarnings("resource")
public final class ContainerHolder {
    private ContainerHolder() {
        throw new RuntimeException();
    }

    private static final DockerComposeContainer<?> environment;

    static {
        environment = new DockerComposeContainer<>(
                new File("src/test/resources/docker-compose.yaml"))
                .withExposedService("db", 5432, Wait.forListeningPort());
        environment.start();
    }

    public static void start() {
    }
}
