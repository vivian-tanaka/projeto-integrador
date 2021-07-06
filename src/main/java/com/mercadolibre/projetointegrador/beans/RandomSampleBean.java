package com.mercadolibre.projetointegrador.beans;

import com.mercadolibre.projetointegrador.dtos.SampleDTO;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomSampleBean {
	private Random random = new Random();

	public SampleDTO random() {
		return new SampleDTO(random.nextInt(Integer.MAX_VALUE));
	}
}

