/*
 * Copyright (c) 2020.
 * Created by Zhbert.
 * Licensed by GPLv3.
 */

package ru.zhbert.lorcleaner.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zhbert.lorcleaner.domain.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
