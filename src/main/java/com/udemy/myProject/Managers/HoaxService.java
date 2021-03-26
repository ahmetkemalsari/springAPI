package com.udemy.myProject.Managers;



import com.udemy.myProject.Models.Hoax;
import com.udemy.myProject.Models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface HoaxService {
    public Page<Hoax> getHoaxes(Pageable page);

    public Hoax save(Hoax hoax, User user);
    public Page<Hoax> getHoaxesOfUser(String username, Pageable page);
    public Page<Hoax> getOldHoaxes(long id, String username, Pageable page);
    public long getNewHoaxesCount(long id, String username);
    public List<Hoax> getNewHoaxes(long id, String username, Sort sort);

}
