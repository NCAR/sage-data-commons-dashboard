package edu.sage.datacommonsdashboard.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import edu.sage.datacommonsdashboard.model.QueueData;
import org.json.JSONException;
import org.json.JSONObject;
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
    public String readFileFromResources(String fileName) throws IOException {

        Resource resource = resourceLoader.getResource("classpath:" + fileName);

        if (resource.exists()) {
            return new String(Files.readAllBytes(Paths.get(resource.getURI())));
        } else {
            throw new IOException("File not found: " + filePath + fileName);
        }
    }

    @Override
    public String readFileWithPath(String fileName) throws IOException {

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


        @Override
        public String getCasperQstatDataText() throws IOException {

            return this.readFileFromResources("casper_qstat_jobs.txt");
        }

        @Override
        public String getDerechoQstatDataText() {
            String textBlock = """
                Job id            Name             User              Time Use S Queue
                ----------------  ---------------- ----------------  -------- - -----
                6567439.desched1  st_archive.CLM5* akhtert                  0 H cpudev
                6572108.desched1  icepack_test     tcraig                   0 W main
                6572197.desched1  cice_test        tcraig                   0 W main
                6584419.desched1  CONUS404         chliu             28204:1* R cpu
                6584651.desched1  PACE_mesh        duda              295:42:* R cpu
                6584706.desched1  w04303           chengw            749:17:* R cpu
                6584707.desched1  gsi              chengw                   0 H cpu
                6584708.desched1  gsi              chengw                   0 H cpu
                6584709.desched1  gsi              chengw                   0 H cpu
                6584710.desched1  gsi              chengw                   0 H cpu
                6584711.desched1  w04304           chengw                   0 H cpu
                6584712.desched1  gsi              chengw                   0 H cpu
                6584713.desched1  gsi              chengw                   0 H cpu
                6584714.desched1  w04305           chengw                   0 H cpu
                6585938.desched1  wx_6h            akn7              133:22:* R gpu
                6585939.desched1  wx_6h            akn7                     0 H gpu
                6585940.desched1  wx_6h            akn7                     0 H gpu
                6585941.desched1  wx_6h            akn7                     0 H gpu
                6585942.desched1  wx_6h            akn7                     0 H gpu
                6585943.desched1  wx_6h            akn7                     0 H gpu
                6585944.desched1  wx_6h            akn7                     0 H gpu
                6585945.desched1  wx_6h            akn7                     0 H gpu
                6585978.desched1  fuxi_6h          akn7              249:50:* R gpu
                6585979.desched1  fuxi_6h          akn7                     0 H gpu
                6585980.desched1  fuxi_6h          akn7                     0 H gpu
                6585981.desched1  fuxi_6h          akn7                     0 H gpu
                6585982.desched1  fuxi_6h          akn7                     0 H gpu
                6585983.desched1  fuxi_6h          akn7                     0 H gpu
                6585984.desched1  fuxi_6h          akn7                     0 H gpu
                6585985.desched1  fuxi_6h          akn7                     0 H gpu
                6586245.desched1  fuxi_dry         ksha              17:21:24 R gpu
                6586246.desched1  fuxi_dry         ksha                     0 H gpu
                6586247.desched1  fuxi_dry         ksha                     0 H gpu
                6586248.desched1  fuxi_dry         ksha                     0 H gpu
                6586249.desched1  fuxi_dry         ksha                     0 H gpu
                6586250.desched1  fuxi_dry         ksha                     0 H gpu
                6586251.desched1  fuxi_dry         ksha                     0 H gpu
                6586252.desched1  fuxi_dry         ksha                     0 H gpu
                6586253.desched1  fuxi_dry         ksha                     0 H gpu
                6586755.desched1  CONUS404         chliu                    0 H cpu
                6587466.desched1  F.MMIOx0.25_C5_* pacosta           17406:0* R cpu
                6587467.desched1  F.MMIOx0.25_C5_* pacosta                  0 H cpu
                6587483.desched1  job_2024-11-11_* marcbecker        756:31:* R cpu
                6587488.desched1  job_2024-11-11_* marcbecker        952:32:* R cpu
                6587490.desched1  job_2024-11-11_* marcbecker        931:02:* R cpu
                6587503.desched1  job_2024-11-11_* marcbecker        806:35:* R cpu
                6587505.desched1  job_2024-11-11_* marcbecker        872:33:* R cpu
                6587507.desched1  job_2024-11-11_* marcbecker        864:57:* R cpu
                6587508.desched1  job_2024-11-11_* marcbecker        836:43:* R cpu
                6587509.desched1  job_2024-11-11_* marcbecker        914:05:* R cpu
                6587510.desched1  job_2024-11-11_* marcbecker        863:11:* R cpu
                6587511.desched1  job_2024-11-11_* marcbecker        762:26:* R cpu
                6587514.desched1  job_2024-11-11_* marcbecker        853:19:* R cpu
                6587516.desched1  job_2024-11-11_* marcbecker        642:18:* R cpu
                6587517.desched1  job_2024-11-11_* marcbecker        836:45:* R cpu
                6587519.desched1  job_2024-11-11_* marcbecker        701:12:* R cpu
                6587521.desched1  job_2024-11-11_* marcbecker        770:53:* R cpu
                6587522.desched1  job_2024-11-11_* marcbecker        771:19:* R cpu
                6587524.desched1  job_2024-11-11_* marcbecker        901:20:* R cpu
                6587525.desched1  job_2024-11-11_* marcbecker        874:24:* R cpu
                6587526.desched1  job_2024-11-11_* marcbecker        701:23:* R cpu
                6587527.desched1  job_2024-11-11_* marcbecker        809:36:* R cpu
                6587528.desched1  job_2024-11-11_* marcbecker        914:28:* R cpu
                6587529.desched1  job_2024-11-11_* marcbecker        855:16:* R cpu
                6587530.desched1  job_2024-11-11_* marcbecker        748:18:* R cpu
                6587531.desched1  job_2024-11-11_* marcbecker        783:18:* R cpu
                6587532.desched1  job_2024-11-11_* marcbecker        881:37:* R cpu
                6587533.desched1  job_2024-11-11_* marcbecker        895:09:* R cpu
                6587534.desched1  job_2024-11-11_* marcbecker        967:55:* R cpu
                """;

            return textBlock;
        }

        @Override
        public String getDerechoQstatQueueDataJson() {

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
        public String getCasperQstatDataJson() {

            String textBlock = """
    TBD
    """;

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

        private static ArrayNode convertLinesToJson(List<String> lines) throws JSONException, JsonProcessingException {

            ObjectMapper objectMapper = new ObjectMapper();
            ArrayNode jsonArray = objectMapper.createArrayNode();

            for (String line : lines) {
                // Assuming each line represents a simple JSON object
                //ObjectNode jsonNode = objectMapper.readValue(line, ObjectNode.class);
                JSONObject jsonObject = convertToJson(line);
                jsonArray.add(String.valueOf(jsonObject));
            }

            return jsonArray;
        }

        private static JSONObject convertToJson(String spaceSeparatedString) throws JSONException {

            JSONObject jsonObject = new JSONObject();

            // String[] elements = spaceSeparatedString.split(" ");

            // Temporary
            jsonObject.put("key", spaceSeparatedString);

            return jsonObject;
        }

}