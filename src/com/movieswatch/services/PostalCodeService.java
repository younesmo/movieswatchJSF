package com.movieswatch.services;

import com.movieswatch.entities.Postalcode;

public interface PostalCodeService {
	Postalcode getByNumber(String number);
}
