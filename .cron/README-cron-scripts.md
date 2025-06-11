The scripts in this directory create all variants of the qstat program's output that the Data Commons application presents related to HPC resource queues and queued jobs.

Role of each script:

  qstat-by-hpc-resource.sh: calls the qstat program on cron.hpc.ucar.edu to generate the required files and moves them into place for the qstat-files-glade-campaign.sh script
  qstat-files-glade-campaign.sh: copies the files to a Glade Campaign location--configured as the data directory of the Data Commons application

Installation procedure:

  * Install qstat-by-hpc-resource.sh and qstat-files-glade-campaign.sh on cron.hpc.ucar.edu in the ~/bin directory of a sage team member (i.e. jcunning, <username> to follow).
  * As <username> on cron.hpc.ucar.edu, add crontab entry (run crontab -e):

      */1 * * * * /glade/u/home/<username>/bin/qstat-by-hpc-resource.sh 1> /glade/u/home/<username>/bin/qstat-by-hpc-resource.log 2>&1 && /glade/u/home/<username>/bin/qstat-files-glade-campaign.sh 1> /glade/u/home/<username>/bin/qstat-files-glade-campaign.log 2>&1
