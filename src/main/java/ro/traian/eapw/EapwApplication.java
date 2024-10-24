package ro.traian.eapw;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lombok.AllArgsConstructor;
import ro.traian.eapw.constant.AppRoleConstant;
import ro.traian.eapw.entity.AppRole;
import ro.traian.eapw.service.approle.IAppRoleService;

@SpringBootApplication
@AllArgsConstructor
public class EapwApplication {
	public static void main(String[] args) {
		SpringApplication.run(EapwApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(IAppRoleService appRoleService) {
		return args -> {
			for (AppRoleConstant appRoleConstant : AppRoleConstant.values()) {
				try {
					appRoleService.findByName(appRoleConstant.name());
					continue;
				} catch (IllegalStateException e) {
					//
				}

				AppRole appRole = new AppRole();
				appRole.setName(appRoleConstant.name());

				appRoleService.save(appRole);
			}
		};
	}

}
