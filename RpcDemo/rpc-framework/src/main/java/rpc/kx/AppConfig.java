package rpc.kx;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 　　* @description: TODO
 * 　　* @author kx
 * 　　* @date 2020/03/16 18:26
 *
 */
@Configuration
@ComponentScan(basePackages = "rpc.kx")
public class AppConfig {

    @Bean(name="reRpcService")
    public ReRpcService reRpcService(){
        return new ReRpcService(10000);
    }
}
