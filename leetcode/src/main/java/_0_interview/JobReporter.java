package _0_interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Reads a daily job log from STDIN and prints a summary of each job chain.
 */
public class JobReporter {
    private static final String HEADER = "job_id,runtime_in_seconds,next_job_id";

    record Job(long id, long runtime, long nextId) {
    }

    record Chain(long startJobId, long lastJobId, int count, long totalRuntime) {
        long getAverageRuntime() {
            return totalRuntime / count;
        }
    }

    static void main() {
        try {
            // 1. read CSV input from STDIN
            List<String> lines = readInputMock();

            // 2. build chain of jobs from raw input
            Map<Long, Job> jobs = parse(lines);
            List<Chain> chains = buildChains(jobs);

            // 3. print report to STDOUT
            printReport(chains);
        } catch (Exception ex) {
            System.out.println("Invalid Input");
        }
    }

    /**
     * Reads input from STDIN and returns a list of lines.
     */
    private static List<String> readInput() throws IOException {
        List<String> lines = new ArrayList<>();

        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }
        return lines;
    }

    private static List<String> readInputMock() {
        return Arrays.asList(
                "job_id,runtime_in_seconds,next_job_id",
                "1,10,2",
                "2,20,3",
                "3,30,0",
                "4,125,5",
                "5,25,0",
                "6,5000,0"
        );
    }

    /**
     * Parses the CSV input lines into a map of jobs.
     */
    private static Map<Long, Job> parse(List<String> lines) {
        if (lines.isEmpty() || !lines.getFirst().trim().equals(HEADER)) {
            throw new IllegalArgumentException("Invalid header");
        }

        Map<Long, Job> jobs = new HashMap<>();
        for (int i = 1; i < lines.size(); i++) {
            String row = lines.get(i).trim();
            if (row.isEmpty()) {
                throw new IllegalArgumentException("Invalid row");
            }

            String[] parts = row.split(",", -1);
            if (parts.length != 3) {
                throw new IllegalArgumentException("Invalid fields length");
            }

            long id = Long.parseLong(parts[0].trim());
            long runtime = Long.parseLong(parts[1].trim());
            long nextJobId = Long.parseLong(parts[2].trim());

            if (id <= 0 || runtime < 0 || nextJobId < 0 || jobs.containsKey(id)) {
                throw new IllegalArgumentException("Invalid job id");
            }

            jobs.put(id, new Job(id, runtime, nextJobId));
        }
        return jobs;
    }

    private static List<Chain> buildChains(Map<Long, Job> jobs) {
        // build inDegree mapping
        Set<Long> inDegree = new HashSet<>();
        for (Job job : jobs.values()) {
            if (job.nextId() == 0) continue;

            // if nextId not exist in jobs -> invalid
            if (!jobs.containsKey(job.nextId())) {
                throw new IllegalArgumentException("Invalid next job id");
            }

            // 2 jobs point to the same job id -> invalid
            if (inDegree.contains(job.nextId())) {
                throw new IllegalArgumentException("Duplicate next job id");
            }

            inDegree.add(job.nextId());
        }

        // build chains from jobs with 0 indegree
        List<Chain> chains = new ArrayList<>();
        int totalJobs = 0;

        for (Job job : jobs.values()) {
            if (inDegree.contains(job.id())) continue;

            Job curJob = job;
            long totalRuntime = job.runtime();
            int count = 1;

            while (curJob.nextId() != 0) {
                curJob = jobs.get(curJob.nextId());
                totalRuntime += curJob.runtime();
                count++;
            }
            totalJobs += count;

            chains.add(new Chain(job.id(), curJob.id(), count, totalRuntime));
        }

        // if visited jobs count != total jobs -> chain contains cycle -> invalid
        if (totalJobs != jobs.size()) {
            throw new IllegalArgumentException("Invalid job count");
        }

        // sort chains by totalRuntime DESC and startJobId ASC
        chains.sort((a, b) -> a.totalRuntime() == b.totalRuntime() ?
                Long.compare(a.startJobId(), b.startJobId()) :
                Long.compare(b.totalRuntime(), a.totalRuntime()));

        return chains;
    }

    private static void printReport(List<Chain> chains) {
        if (chains.isEmpty()) return;

        StringBuilder sb = new StringBuilder();
        sb.append("-\n");
        for (Chain c : chains) {
            sb.append("start_job: ").append(c.startJobId()).append('\n')
                    .append("last_job: ").append(c.lastJobId()).append('\n')
                    .append("number_of_jobs: ").append(c.count()).append('\n')
                    .append("job_chain_runtime: ").append(formatTime(c.totalRuntime())).append('\n')
                    .append("average_job_time: ").append(formatTime(c.getAverageRuntime())).append('\n')
                    .append("-\n");
        }
        System.out.println(sb);
    }

    private static String formatTime(long seconds) {
        return String.format("%02d:%02d:%02d", seconds / 3600, (seconds % 3600) / 60, seconds % 60);
    }
}
