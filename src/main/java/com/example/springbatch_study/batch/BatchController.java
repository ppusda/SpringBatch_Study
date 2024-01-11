package com.example.springbatch_study.batch;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/batch")
@RequiredArgsConstructor
public class BatchController {

    private final BatchService batchService;

    @RequestMapping("/run")
    public void runJob() {
        batchService.runBatchJob();
    }
}
