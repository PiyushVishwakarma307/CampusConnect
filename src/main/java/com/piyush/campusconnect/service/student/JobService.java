package com.piyush.campusconnect.service.student;

import com.piyush.campusconnect.dto.student.JobDTO;
import com.piyush.campusconnect.entity.Assignment;
import com.piyush.campusconnect.entity.Job;
import com.piyush.campusconnect.repository.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    @Autowired
    JobRepo jobRepository;

    public Page<JobDTO> getJobs(int page ,int size) {
        Page<Job> jobs =
                jobRepository.findAll(PageRequest.of(page, size));

        return jobs.map(this::mapToDTO);
    }

    public JobDTO mapToDTO(com.piyush.campusconnect.entity.Job j) {

        JobDTO dto = new JobDTO();
        dto.setId(j.getId());
        dto.setTitle(j.getTitle());
        dto.setType(j.getType());
        dto.setCompany(j.getCompany());
        dto.setDeadline(j.getDeadline());
        dto.setLocation(j.getLocation());

        return dto;
    }


    public List<JobDTO> getAllJobs() {
        return jobRepository.findAll().stream().map(this::mapToDTO).toList();
    }

    public void deleteJobById(long id) {
        jobRepository.deleteById(id);
    }

    public void saveJob(Job job) {
        jobRepository.save(job);
    }
}
