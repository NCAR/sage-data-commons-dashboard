package edu.sage.datacommonsdashboard;

import org.springframework.stereotype.Service;

@Service
public class QueueDataServiceImpl implements QueueDataService{

    public QueueDataServiceImpl() {
    }

    @Override
    public String getCasperQstatDataText() {
        String textBlock = """
                Job id            Name             User              Time Use S Queue
                ----------------  ---------------- ----------------  -------- - -----
                2399360.casper-p* GetGFSAnalysisF* nghido                   0 H htc
                2399361.casper-p* GetGFSAnalysisF* nghido                   0 H htc
                2705953.casper-p* create_climo_an* daeunlee                 0 H htc
                2941107.casper-p* goes_gpu_1_4     csgteam                  0 H system
                2945490.casper-p* cr-login-stable  lucaso            04:10:45 R jhublogin
                2945506.casper-p* cr-login-stable  benkirk           03:54:51 R jhublogin
                2945575.casper-p* cr-login-stable  tianqi            10:20:34 R jhublogin
                2946346.casper-p* cr-login-stable  guoqiang          03:54:28 R jhublogin
                2946401.casper-p* cr-login-stable  ylesiawu          03:49:15 R jhublogin
                2946402.casper-p* cr-login-stable  jban              08:14:15 R jhublogin
                2946786.casper-p* cr-login-stable  chayan            06:24:13 R jhublogin
                2946787.casper-p* cr-login-stable  aparsells         07:41:09 R jhublogin
                2946789.casper-p* cr-login-stable  tzaman            05:35:52 R jhublogin
                2946796.casper-p* cr-login-stable  yiwenz            04:56:12 R jhublogin
                2946799.casper-p* cr-login-stable  pangulo           03:34:06 R jhublogin
                2946800.casper-p* cr-login-stable  zxhua             04:12:45 R jhublogin
                2946801.casper-p* cr-login-stable  msaif             04:52:59 R jhublogin
                2946822.casper-p* cr-login-stable  jianguan          03:44:09 R jhublogin
                2946823.casper-p* cr-login-stable  groberg           04:58:41 R jhublogin
                2946830.casper-p* cr-login-stable  ajacobs           124:30:* R jhublogin
                2946833.casper-p* cr-login-stable  chennuo           14:58:36 R jhublogin
                2946847.casper-p* cr-login-stable  liut              04:18:43 R jhublogin
                2946853.casper-p* cr-login-stable  yeddebba          28:08:40 R jhublogin
                2946865.casper-p* cr-login-stable  nikhilr           03:40:40 R jhublogin
                2946868.casper-p* cr-login-stable  dtokuda           03:30:48 R jhublogin
                2946871.casper-p* cr-login-stable  nmarkus           06:24:11 R jhublogin
                2946899.casper-p* cr-login-stable  xinchang          07:14:00 R jhublogin
                2946903.casper-p* cr-login-stable  farkian           02:44:26 R jhublogin
                2946933.casper-p* cr-login-stable  xyzhao            04:17:13 R jhublogin
                2946934.casper-p* cr-login-stable  harshah           05:23:36 R jhublogin
                2946943.casper-p* cr-login-stable  asyed             03:14:29 R jhublogin
                2946977.casper-p* cr-login-stable  arezoo            06:52:48 R jhublogin
                2947005.casper-p* cr-login-stable  masih             10:22:45 R jhublogin
                2947031.casper-p* cr-login-stable  qzou              04:28:14 R jhublogin
                2947034.casper-p* cr-login-stable  sabina            03:42:47 R jhublogin
                2947055.casper-p* cr-login-stable  xinan             04:04:27 R jhublogin
                2947064.casper-p* cr-login-stable  fafrifa           07:03:16 R jhublogin
                2947098.casper-p* cr-login-stable  abossolasco       03:51:50 R jhublogin
                2947100.casper-p* cr-login-stable  wriggles          05:06:47 R jhublogin
                2947103.casper-p* cr-login-stable  ito               08:03:35 R jhublogin
                2947105.casper-p* cr-login-stable  sofian            04:38:20 R jhublogin
                2947133.casper-p* cr-login-stable  amyliu            04:09:43 R jhublogin
                2947149.casper-p* cr-login-stable  ekim              03:42:17 R jhublogin
                2947190.casper-p* cr-login-stable  rtdatta           08:46:30 R jhublogin
                2947224.casper-p* cr-login-stable  dadelgado         03:32:08 R jhublogin
                2947391.casper-p* cr-login-stable  lianet            07:25:41 R jhublogin
                2947520.casper-p* cr-login-stable  midhun            03:37:36 R jhublogin
                2947650.casper-p* cr-login-stable  clittle           12:06:46 R jhublogin
                2947782.casper-p* cr-login-stable  wchapman          09:00:50 R jhublogin
                2947783.casper-p* cr-login-stable  miyawaki          03:54:41 R jhublogin
                2947877.casper-p* cr-login-stable  yiwenzhang        28:57:21 R jhublogin
                2947930.casper-p* cr-login-stable  bmoose            04:08:07 R jhublogin
                2947931.casper-p* cr-login-stable  jhayron           05:11:37 R jhublogin
                2947949.casper-p* cr-login-stable  tilmes            04:39:39 R jhublogin
                2947951.casper-p* cr-login-stable  sdhavale          06:09:28 R jhublogin
                2947957.casper-p* cr-login-stable  carmined          04:08:02 R jhublogin
                2947995.casper-p* cr-login-stable  wasserstein       03:50:22 R jhublogin
                2948007.casper-p* cr-login-stable  kuoyan            04:41:28 R jhublogin
                2948030.casper-p* cr-login-stable  mingzhu           03:43:36 R jhublogin
                2948045.casper-p* cr-login-stable  kinsaled          03:50:11 R jhublogin
                2948062.casper-p* cr-login-stable  emadonna          03:23:43 R jhublogin
                2948121.casper-p* cr-login-stable  mduetsch          03:52:45 R jhublogin
                2948283.casper-p* cr-login-stable  che43             03:22:04 R jhublogin
                2948328.casper-p* cr-login-stable  igomez            06:01:51 R jhublogin
                2948329.casper-p* cr-login-stable  jingyiz           04:22:14 R jhublogin
                2948343.casper-p* cr-login-stable  lsheji            03:38:56 R jhublogin
                2948356.casper-p* cr-login-stable  juliacam          04:44:43 R jhublogin
                2948376.casper-p* cr-login-stable  fengc             08:15:50 R jhublogin
                2948398.casper-p* cr-login-stable  luzheng           04:38:39 R jhublogin
                "casper_qstat.txt" 5566L, 450824B
                """;

        return textBlock;
    }

    @Override
    public String getDerechoQstatData() {
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
}
