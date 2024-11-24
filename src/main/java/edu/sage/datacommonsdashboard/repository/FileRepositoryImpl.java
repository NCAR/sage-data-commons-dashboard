package edu.sage.datacommonsdashboard.repository;

//import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import edu.sage.datacommonsdashboard.model.QueueData;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FileRepositoryImpl implements FileRepository {

    private final ResourceLoader resourceLoader;

    public FileRepositoryImpl(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Value("${dashboard.queue.file.path}")
    private String filePath;


    @Override
    public String getCasperQstatJobsText() throws IOException {

        return this.readFileFromResources("casper_qstat_jobs.txt");
    }

    @Override
    public String getCasperQstatJobsJson() throws IOException {

        return this.readFileFromResources("casper_qstat_jobs.json");
    }

    @Override
    public String getCasperQstatQueueText() throws IOException {

        return this.readFileFromResources("casper_qstat_queue.txt");
    }

    @Override
    public String getCasperQstatQueueJson() throws IOException {

        return this.readFileFromResources("casper_qstat_queue.json");
    }

    @Override
    public String getDerechoQstatJobsText() throws IOException {

        return this.readFileFromResources("derecho_qstat_jobs.txt");
    }

    @Override
    public String getDerechoQstatJobsJson() throws IOException {

        return this.readFileFromResources("derecho_qstat_jobs.json");
    }

    @Override
    public String getDerechoQstatQueueText() throws IOException {

        return this.readFileFromResources("derecho_qstat_queue.txt");
    }

    public static boolean verifyFilePath(String filePath, String fileName) throws IOException {

        Path path = Paths.get(filePath);
        Path fullPath = path.resolve(fileName);

        // Check whether the normalized full path contains the expected separation
        String expectedSeparator = System.getProperty("file.separator");
        String fullPathStr = fullPath.toString();

        // Ensure the file exists (or can be accessed)
        if (Files.exists(fullPath) ) {
            return fullPathStr.contains(expectedSeparator + fileName);
        }
        return false;
    }


    private String readFileFromResources(String fileName) throws IOException {

        Resource resource = resourceLoader.getResource("classpath:" + fileName);

        if (resource.exists()) {
            return new String(Files.readAllBytes(Paths.get(resource.getURI())));
        } else {
            throw new IOException("File not found: " + filePath + fileName);
        }
    }

    private String readFileWithPath(String fileName) throws IOException {

//        System.out.println("===FileRepositoryImpl data filePath: " + filePath + ", fileName: " + fileName);

        if (verifyFilePath(filePath, fileName)) {

            Resource resource = resourceLoader.getResource("file:" + filePath + fileName);

            if (resource.exists()) {
                return new String(Files.readAllBytes(Paths.get(resource.getURI())));
            } else {
                throw new IOException("File not found: " + filePath + fileName);
            }

        } else {
            throw new IOException("File path error: "+ filePath + fileName);
        }
    }





    @Override
    public String getDerechoQstatQueueJson() {

        String textBlock = """
                {
                    "timestamp":1731528276,
                    "pbs_version":"2022.1.5.20240213134632",
                    "pbs_server":"desched1.hsn.de.hpc.ucar.edu",
                    "Queue":{
                        "cpu":{
                            "queue_type":"Execution",
                            "Priority":100,
                            "total_jobs":1041,
                            "state_count":"Transit:0 Queued:165 Held:70 Waiting:0 Running:805 Exiting:1 Begun:0 ",
                            "from_route_only":"True",
                            "resources_max":{
                                "ngpus":0
                            },
                            "resources_default":{
                                "preempt_targets":"QUEUE=pcpu"
                            },
                            "default_chunk":{
                                "mem":"235gb",
                                "ncpus":128,
                                "Qlist":"cpu"
                            },
                            "resources_assigned":{
                                "mem":"542201gb",
                                "mpiprocs":268406,
                                "ncpus":289715,
                                "nodect":2439
                            },
                            "backfill_depth":5,
                            "enabled":"True",
                            "started":"True"
                        },
                        "gpu":{
                            "queue_type":"Execution",
                            "Priority":100,
                            "total_jobs":41,
                            "state_count":"Transit:0 Queued:0 Held:29 Waiting:0 Running:12 Exiting:0 Begun:0 ",
                            "from_route_only":"True",
                            "resources_max":{
                                "gpu_type":"a100"
                            },
                            "resources_min":{
                                "gpu_type":"a100",
                                "ngpus":1
                            },
                            "resources_default":{
                                "ncpus":64,
                                "ngpus":4,
                                "preempt_targets":"QUEUE=pgpu"
                            },
                            "default_chunk":{
                                "mem":"487gb",
                                "ncpus":64,
                                "ngpus":4,
                                "Qlist":"a100"
                            },
                            "resources_assigned":{
                                "mem":"26911gb",
                                "mpiprocs":9,
                                "ncpus":3521,
                                "nodect":56
                            },
                            "backfill_depth":5,
                            "enabled":"True",
                            "started":"True"
                        },
                        "system":{
                            "queue_type":"Execution",
                            "total_jobs":0,
                            "state_count":"Transit:0 Queued:0 Held:0 Waiting:0 Running:0 Exiting:0 Begun:0 ",
                            "default_chunk":{
                                "Qlist":"system"
                            },
                            "resources_assigned":{
                                "mem":"0gb",
                                "mpiprocs":0,
                                "ncpus":0,
                                "nodect":0
                            },
                            "enabled":"True",
                            "started":"True"
                        },
                        "main":{
                            "queue_type":"Route",
                            "total_jobs":2,
                            "state_count":"Transit:0 Queued:0 Held:0 Waiting:2 Running:0 Exiting:0 Begun:0 ",
                            "resources_max":{
                                "mem":"692tb",
                                "ncpus":323712,
                                "ngpus":328
                            },
                            "route_destinations":"cpu,gpu,hybrid",
                            "route_held_jobs":"False",
                            "route_retry_time":60,
                            "enabled":"True",
                            "started":"True"
                        },
                        "hybrid":{
                            "queue_type":"Execution",
                            "Priority":100,
                            "total_jobs":0,
                            "state_count":"Transit:0 Queued:0 Held:0 Waiting:0 Running:0 Exiting:0 Begun:0 ",
                            "from_route_only":"True",
                            "resources_max":{
                                "gpu_type":"hybrid"
                            },
                            "resources_min":{
                                "gpu_type":"hybrid",
                                "ncpus":1,
                                "ngpus":1
                            },
                            "default_chunk":{
                                "mem":"235gb",
                                "Qlist":"hybrid"
                            },
                            "enabled":"True",
                            "started":"True"
                        },
                        "preempt":{
                            "queue_type":"Route",
                            "total_jobs":0,
                            "state_count":"Transit:0 Queued:0 Held:0 Waiting:0 Running:0 Exiting:0 Begun:0 ",
                            "route_destinations":"pcpu,pgpu",
                            "route_held_jobs":"False",
                            "route_retry_time":60,
                            "enabled":"True",
                            "started":"True"
                        },
                        "pcpu":{
                            "queue_type":"Execution",
                            "Priority":50,
                            "total_jobs":1,
                            "state_count":"Transit:0 Queued:1 Held:0 Waiting:0 Running:0 Exiting:0 Begun:0 ",
                            "from_route_only":"True",
                            "resources_max":{
                                "ngpus":0
                            },
                            "resources_min":{
                                "ngpus":0
                            },
                            "default_chunk":{
                                "mem":"235gb",
                                "ncpus":128,
                                "Qlist":"cpu"
                            },
                            "resources_assigned":{
                                "mem":"0gb",
                                "mpiprocs":0,
                                "ncpus":0,
                                "nodect":0
                            },
                            "kill_delay":600,
                            "enabled":"True",
                            "started":"True"
                        },
                        "pgpu":{
                            "queue_type":"Execution",
                            "Priority":50,
                            "total_jobs":0,
                            "state_count":"Transit:0 Queued:0 Held:0 Waiting:0 Running:0 Exiting:0 Begun:0 ",
                            "from_route_only":"True",
                            "resources_max":{
                                "gpu_type":"a100"
                            },
                            "resources_min":{
                                "gpu_type":"a100",
                                "ngpus":1
                            },
                            "default_chunk":{
                                "mem":"487gb",
                                "Qlist":"a100"
                            },
                            "resources_assigned":{
                                "mem":"0gb",
                                "ncpus":0,
                                "nodect":0
                            },
                            "kill_delay":600,
                            "enabled":"True",
                            "started":"True"
                        },
                        "gpudev":{
                            "queue_type":"Execution",
                            "Priority":200,
                            "total_jobs":0,
                            "state_count":"Transit:0 Queued:0 Held:0 Waiting:0 Running:0 Exiting:0 Begun:0 ",
                            "from_route_only":"True",
                            "resources_max":{
                                "gpu_type":"a100",
                                "ncpus":128,
                                "ngpus":8
                            },
                            "resources_min":{
                                "gpu_type":"a100",
                                "ngpus":1
                            },
                            "resources_default":{
                                "place":"pack:shared",
                                "preempt_targets":"None"
                            },
                            "default_chunk":{
                                "mem":"120gb",
                                "ncpus":1,
                                "ngpus":4,
                                "Qlist":"a100"
                            },
                            "resources_assigned":{
                                "mem":"0gb",
                                "mpiprocs":0,
                                "ncpus":0,
                                "nodect":0
                            },
                            "enabled":"True",
                            "started":"True"
                        },
                        "cpudev":{
                            "queue_type":"Execution",
                            "Priority":200,
                            "total_jobs":801,
                            "state_count":"Transit:0 Queued:173 Held:616 Waiting:0 Running:12 Exiting:0 Begun:0 ",
                            "from_route_only":"True",
                            "resources_max":{
                                "ncpus":3200,
                                "ngpus":0
                            },
                            "resources_default":{
                                "place":"pack:shared",
                                "preempt_targets":"None"
                            },
                            "default_chunk":{
                                "mem":"10gb",
                                "ncpus":1,
                                "Qlist":"cpu"
                            },
                            "resources_assigned":{
                                "mem":"1640gb",
                                "mpiprocs":69,
                                "ncpus":293,
                                "nodect":12
                            },
                            "max_run_res":{
                                "mem":"[u:PBS_GENERIC=470gb]",
                                "ncpus":"[u:PBS_GENERIC=256]"
                            },
                            "backfill_depth":50,
                            "enabled":"True",
                            "started":"True"
                        },
                        "develop":{
                            "queue_type":"Route",
                            "total_jobs":0,
                            "state_count":"Transit:0 Queued:0 Held:0 Waiting:0 Running:0 Exiting:0 Begun:0 ",
                            "resources_max":{
                                "ncpus":3200,
                                "ngpus":8
                            },
                            "resources_default":{
                                "place":"pack:shared"
                            },
                            "route_destinations":"cpudev,gpudev",
                            "route_held_jobs":"False",
                            "route_retry_time":60,
                            "enabled":"True",
                            "started":"True"
                        },
                        "repair":{
                            "queue_type":"Execution",
                            "total_jobs":0,
                            "state_count":"Transit:0 Queued:0 Held:0 Waiting:0 Running:0 Exiting:0 Begun:0 ",
                            "acl_user_enable":"True",
                            "acl_users":"andersnb,aricw,csgteam,emma,jam,jbaker,jblaas,jtillots,matthews,robertsj,shanks,stormyk,vanderwb",
                            "from_route_only":"False",
                            "resources_default":{
                                "walltime":"01:00:00"
                            },
                            "default_chunk":{
                                "Qlist":"repair"
                            },
                            "enabled":"True",
                            "started":"True"
                        },
                        "jhub":{
                            "queue_type":"Execution",
                            "total_jobs":0,
                            "state_count":"Transit:0 Queued:0 Held:0 Waiting:0 Running:0 Exiting:0 Begun:0 ",
                            "from_route_only":"False",
                            "default_chunk":{
                                "Qlist":"jhub"
                            },
                            "backfill_depth":1000,
                            "enabled":"True",
                            "started":"True"
                        },
                        "tutorial":{
                            "queue_type":"Route",
                            "total_jobs":0,
                            "state_count":"Transit:0 Queued:0 Held:0 Waiting:0 Running:0 Exiting:0 Begun:0 ",
                            "route_destinations":"R5488474,R5488492,R5488493,R5488494,R5591177",
                            "enabled":"True",
                            "started":"True"
                        }
                    }
                }""";

        return textBlock;
    }



    @Override
    public QueueData createQueueRow() {

        QueueData queueData = new QueueData();

        // 2945490.casper-p* cr-login-stable  lucaso            04:10:45 R jhublogin
        queueData.setJobId("2945490.casper-p*");
        queueData.setName("cr-login-stable");
        queueData.setUser("lucaso");
        queueData.setTimeUse("04:10:45");
        queueData.setStatus("R");
        queueData.setQueue("jhublogin");

        return queueData;
    }

    @Override
    public List<String> convertTextToJson() {

        // TODO: hardcoded file
        String textFilePath = "/Users/cgrant/derecho_trim.txt";

        try {
            List<String> lines = readLinesFromTextFile(textFilePath);
            return lines;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static List<String> readLinesFromTextFile(String filePath) throws IOException {

        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }

private static ArrayNode convertLinesToJson(List<String> lines) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode jsonArray = objectMapper.createArrayNode();

        for (String line : lines) {
            // Assuming each line represents a simple JSON object
            //ObjectNode jsonNode = objectMapper.readValue(line, ObjectNode.class);
            ObjectNode jsonObject = convertToJson(line);
            jsonArray.add(String.valueOf(jsonObject));
        }

        return jsonArray;
    }

    private static ObjectNode convertToJson(String spaceSeparatedString) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();

        ObjectNode jsonObject = mapper.createObjectNode();

        // String[] elements = spaceSeparatedString.split(" ");

        // Temporary
        jsonObject.put("key", spaceSeparatedString);

        return jsonObject;
    }

}