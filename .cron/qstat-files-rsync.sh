#!/bin/bash

destination=sagedev.ucar.edu

if [ -d $HOME/qstat/store ]; then
  ssh $destination mkdir -p qstat/store.rsync
  rsync -rptv $HOME/qstat/store/ $destination:qstat/store.rsync
  ssh $destination "rm -rf qstat/store && mv qstat/store.rsync qstat/store"
fi
