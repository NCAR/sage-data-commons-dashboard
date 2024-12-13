The scripts in this directory create all variants of the qstat program's output that the Data Commons application presents related to HPC resource queues and queued jobs.

Role of each script:

  qstat-by-hpc-resource.sh: calls the qstat program on cron.hpc.ucar.edu to generate the required files and moves them into place for the qstat-files-rsync.sh script
  qstat-files-rsync.sh:     syncs the files to sagedev.ucar.edu
  qstat-files-place.sh:     places the files in the data directory of the Data Commons application


Installation procedure:

  1. Install qstat-by-hpc-resource.sh and qstat-files-rsync.sh on cron.hpc.ucar.edu in the ~/bin directory of a sage team member (i.e. jcunning, <username> to follow).
  2. As <username> on cron.hpc.ucar.edu, create a passphraseless ssh key (to automate the authentication required by qstat-files-rsync.sh).
  3. As <username> on sagedev.ucar.edu, authorize the ssh key by adding the public part of the key to ~/.ssh/authorized_keys.
  4. As root on sagedev.ucar.edu, install qstat-files-place.sh in /root/bin.
  5. Add the following crontab entries:

  As <username> on cron.hpc.ucar.edu, add crontab entry (run crontab -e):

    */1 * * * * /glade/u/home/<username>/bin/qstat-by-hpc-resource.sh 1> /glade/u/home/<username>/bin/qstat-by-hpc-resource.log 2>&1 && /glade/u/home/<username>/bin/qstat-files-rsync.sh 1> /glade/u/home/<username>/bin/qstat-files-rsync.log 2>&1

  As root on sagedev.ucar.edu, add crontab entry (run crontab -e)

    */1 * * * * sleep 30; /root/bin/qstat-files-place.sh 1> /root/bin/qstat-files-place.log 2>&1

Note the sleep delay on the sagedev.ucar.edu job. The two machines are time synced so the cronjobs will fire at the same time. The cron job on cron.hpc.ucar.edu will take the longest of the two. I'm delaying the sagedev.ucar.edu cron job to wait for it to complete and to eliminate any real likelyhood of a collision. The collision probability is low to begin with due to moves being used post copies within the scripts.
