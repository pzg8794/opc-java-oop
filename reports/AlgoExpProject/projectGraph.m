% Piter Garcia
% Dinash Domma


A = importdata('test.txt');
disp(A);
% 
rCN  = sort(A.data(:,1));
cpuT = sort(A.data(:,2));
lcsL = sort(A.data(:,3));
isL1 = sort(A.data(:,4));
isL2 = sort(A.data(:,5));

x = cpuT; 
y1 = rCN;
y2 = lcsL;

y = isL1;
z = isL2;

% graph one
figure;
subplot(2,2,1)
plot(x, y1, 'r*');

legend('recursive calls No. AND x= CPU-Time.', 0);

title('CPU-Time vs Recursive Calls Numbers');
xlabel('CPU-TIME (ms)');
ylabel('RECURSIVE CALLS NO.');

% graph two
subplot(2,2,2)
plot(x, y2, 'r*'); % lines only!

legend('LCS-Length AND x= CPU-Time.', 0);
title('CPU-Time vs LCS-Length');
xlabel('CPU-TIME (ms)');
ylabel('LCS-LENGTH');

% graph two
subplot(2,2,3)
plot(x, y, 'r*'); % lines only!

legend('ISL1-Length AND x= CPU-Time.', 0);
title('CPU-Time vs ISL1-Length');
xlabel('CPU-TIME (ms)');
ylabel('ISL1-LENGTH');

% graph two
subplot(2,2,4)
plot(x, z, 'r*'); % lines only!

legend('ISL2-Length AND x= CPU-Time.', 0);
title('CPU-Time vs ISL2-Length');
xlabel('CPU-TIME (ms)');
ylabel('ISL2-LENGTH');