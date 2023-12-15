package ttps.java.grupo1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ttps.java.grupo1.model.GroupCategory;
import ttps.java.grupo1.repository.GroupCategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GroupCategoryService {

    @Autowired
    GroupCategoryRepository groupCategoryRepository;

    @Transactional
    public GroupCategory save(GroupCategory groupCategory) {
        return this.groupCategoryRepository.save(groupCategory);
    }

    @Transactional(readOnly = true)
    public Optional<GroupCategory> findById(Long id) {
        return this.groupCategoryRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<GroupCategory> findAll() {
        return this.groupCategoryRepository.findAll();
    }
}
