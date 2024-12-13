#!/bin/bash

destination=sagedev.ucar.edu

if [ -d $HOME/qstat/store ]; then
  ssh $destination mkdir -p qstat/store qstat/store.rsync  
  rsync -rptv $HOME/qstat/store/ $destination:qstat/store.rsync
  ssh $destination "mv qstat/store qstat/store.old && mv qstat/store.rsync qstat/store && rm -r qstat/store.old"
fi
