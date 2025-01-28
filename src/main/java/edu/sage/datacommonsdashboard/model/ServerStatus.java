package edu.sage.datacommonsdashboard.model;

import java.util.List;

public class ServerStatus {

 Integer timestamp;
 List<HpcHost> hosts;

 public ServerStatus() {
 }

 public ServerStatus(Integer timestamp, List<HpcHost> hosts) {
  this.timestamp = timestamp;
  this.hosts = hosts;
 }

 public Integer getTimestamp() {
  return timestamp;
 }

 public void setTimestamp(Integer timestamp) {
  this.timestamp = timestamp;
 }

 public List<HpcHost> getHosts() {
  return hosts;
 }

 public void setHosts(List<HpcHost> hosts) {
  this.hosts = hosts;
 }
}
