# ioTester
Java Application to test a device IO performance.
It creates a new file to a given directory, writes some data inside it and deletes the file. At each step it records the duration of the operation and writes into a log file.


The tool runs at a given frequency and logs the results into an output file in this format:


```
 [12-01-2018 11:48:48.626] create a file... 
 [12-01-2018 11:48:48.652] file created. 
 [12-01-2018 11:48:48.653] file deleted. 
 [12-01-2018 11:48:48.653] Time duration : 27 ms
 [12-01-2018 11:48:48.653] !!! ALERT !!! 
 ----------------------------------------------------------
 [12-01-2018 11:48:53.653] create a file... 
 [12-01-2018 11:48:53.661] file created. 
 [12-01-2018 11:48:53.662] file deleted. 
 [12-01-2018 11:48:53.662] Time duration : 9 ms
 ----------------------------------------------------------
```

This tools helps testing a shared filesystem performance





