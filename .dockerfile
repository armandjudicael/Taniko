FROM oraclelinux:8-slim
# Use a slim base image for efficiency

# Install required packages
RUN yum install -y oracle-database-preinstall-19c oracle-database-ee-19c

# Configure Oracle environment variables
ENV ORACLE_SID=mysid
# Replace with your desired SID
ENV ORACLE_PDB=mypdb
# Replace with your desired PDB name (optional)
ENV ORACLE_HOME=/opt/oracle/product/19c/dbhome_1

# Set password for the SYS and SYSTEM users (replace with your secure passwords)
ENV ORACLE_PWD=Aj!30071999

# Create Oracle user and group for security
RUN groupadd -g 54321 oinstall && \
    useradd -u 54321 -g oinstall -G dba oracle

# Expose Oracle listener port
EXPOSE 1521

# Volumes for database files and configuration
VOLUME ["/opt/oracle/oradata", "/opt/oracle/product/19c/dbhome_1/dbs"]

# Entrypoint script to start Oracle database
COPY entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh
ENTRYPOINT ["/entrypoint.sh"]
