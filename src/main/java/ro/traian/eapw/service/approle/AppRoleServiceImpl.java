package ro.traian.eapw.service.approle;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ro.traian.eapw.dao.approle.AppRoleSave;
import ro.traian.eapw.entity.AppRole;
import ro.traian.eapw.repository.AppRoleRepository;

@Service
@AllArgsConstructor
public class AppRoleServiceImpl implements IAppRoleService {
    private final AppRoleRepository appRoleRepository;

    @Override
    public AppRole findById(Long id) {
        return appRoleRepository
                .findById(id)
                .orElseThrow(() -> new IllegalStateException("Role with id " + id + " not found"));
    }

    @Override
    public AppRole findByName(String name) {
        return appRoleRepository
                .findByName(name)
                .orElseThrow(() -> new IllegalStateException("Role with name " + name + " not found"));
    }

    @Override
    public AppRole save(AppRoleSave appRole) {
        try {
            this.findByName(appRole.getName());
            throw new IllegalStateException("Role already exists");
        } catch (IllegalStateException e) {
            //
        }

        AppRole newAppRole = AppRole.fromAppRoleSave(appRole);
        return appRoleRepository.save(newAppRole);
    }

}
