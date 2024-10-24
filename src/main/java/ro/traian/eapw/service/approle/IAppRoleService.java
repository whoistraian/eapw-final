package ro.traian.eapw.service.approle;

import ro.traian.eapw.entity.AppRole;

public interface IAppRoleService {
    AppRole findById(Long id);

    AppRole findByName(String name);

    AppRole save(AppRole appRole);
}
