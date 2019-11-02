package com.movieswatch.services;

import com.movieswatch.entities.Codepostaux;

public interface CodePostauxService {
	Codepostaux getByNumber(String number);
}
