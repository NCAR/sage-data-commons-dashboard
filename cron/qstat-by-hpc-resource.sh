#!/bin/bash
#
# sample crontab entry (run crontab -e)
# */1 * * * * /glade/u/home/<your username>/qstat/qstat-by-hpc-resource.sh 1> /glade/u/home/<your username>/qstat/qstat-by-hpc-resource.log 2>&1
#

export PATH=/glade/u/apps/opt/qstat-cache/bin:/usr/bin:/bin:/usr/sbin:/sbin:/usr/local/bin:/opt/pbs/bin

writedir=$HOME/qstat/write
storedir=$HOME/qstat/store

timestamp=$(date +%Y-%m-%d.%T)
tmp=$timestamp.tmp
err=$timestamp.err
delafterdays=1

if [ ! -d $writedir ]; then
    mkdir -p $writedir
fi

if [ ! -d $storedir ]; then
    mkdir -p $storedir
fi

# Derecho jobs
qstat -w @desched1 1> $writedir/derecho_qstat_jobs.txt.$tmp 2> $writedir/derecho_qstat_jobs.txt.$err
qstat -f -F json @desched1 1> $writedir/derecho_qstat_jobs.json.$tmp 2> $writedir/derecho_qstat_jobs.json.$err

# Derecho queue
qstat -Q -f @desched1 1> $writedir/derecho_qstat_queue.txt.$tmp 2> $writedir/derecho_qstat_queue.txt.$err
qstat -Q -f -F json @desched1 1> $writedir/derecho_qstat_queue.json.$tmp 2> $writedir/derecho_qstat_queue.json.$err

# Casper jobs
qstat -w @casper-pbs 1> $writedir/casper_qstat_jobs.txt.$tmp 2> $writedir/casper_qstat_jobs.txt.$err
qstat -f -F json @casper-pbs 1> $writedir/casper_qstat_jobs.json.$tmp 2> $writedir/casper_qstat_jobs.json.$err

# Casper queue
qstat -Q -f @casper-pbs 1> $writedir/casper_qstat_queue.txt.$tmp 2> $writedir/casper_qstat_queue.txt.$err
qstat -Q -f -F json @casper-pbs 1> $writedir/casper_qstat_queue.json.$tmp 2> $writedir/casper_qstat_queue.json.$err

# Move
mv $writedir/derecho_qstat_jobs.txt.$tmp $storedir/derecho_qstat_jobs.txt
mv $writedir/derecho_qstat_jobs.json.$tmp $storedir/derecho_qstat_jobs.json
mv $writedir/derecho_qstat_queue.txt.$tmp $storedir/derecho_qstat_queue.txt
mv $writedir/derecho_qstat_queue.json.$tmp $storedir/derecho_qstat_queue.json
mv $writedir/casper_qstat_jobs.txt.$tmp $storedir/casper_qstat_jobs.txt
mv $writedir/casper_qstat_jobs.json.$tmp $storedir/casper_qstat_jobs.json
mv $writedir/casper_qstat_queue.txt.$tmp $storedir/casper_qstat_queue.txt
mv $writedir/casper_qstat_queue.json.$tmp $storedir/casper_qstat_queue.json

# Delete empty err files and err files older than delafterdays days
find $writedir -type f -name '*.err' -empty -delete
find $writedir -type f -name '*.err' -mtime +$delafterdays -delete
