package com.orange.chebroker.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


@Repository
public class DockerClientImpl implements DockerClient {
	private static Logger logger=LoggerFactory.getLogger(DockerClientImpl.class.getName());
}
