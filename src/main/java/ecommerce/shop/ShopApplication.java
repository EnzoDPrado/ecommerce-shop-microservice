package ecommerce.shop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class ShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);

        log.info("""
                ___           ___           ___           ___    \s
                                      /\\  \\         /\\__\\         /\\  \\         /\\  \\   \s
                                     /::\\  \\       /:/  /        /::\\  \\       /::\\  \\  \s
                                    /:/\\ \\  \\     /:/__/        /:/\\:\\  \\     /:/\\:\\  \\ \s
                                   _\\:\\~\\ \\  \\   /::\\  \\ ___   /:/  \\:\\  \\   /::\\~\\:\\  \\\s
                                  /\\ \\:\\ \\ \\__\\ /:/\\:\\  /\\__\\ /:/__/ \\:\\__\\ /:/\\:\\ \\:\\__\\
                                  \\:\\ \\:\\ \\/__/ \\/__\\:\\/:/  / \\:\\  \\ /:/  / \\/__\\:\\/:/  /
                                   \\:\\ \\:\\__\\        \\::/  /   \\:\\  /:/  /       \\::/  /\s
                                    \\:\\/:/  /        /:/  /     \\:\\/:/  /         \\/__/ \s
                                     \\::/  /        /:/  /       \\::/  /                \s
                                      \\/__/         \\/__/         \\/__/                 \s
                              SHOP-MICROSERVICE :: 0.1
                            \s""");
    }
}
