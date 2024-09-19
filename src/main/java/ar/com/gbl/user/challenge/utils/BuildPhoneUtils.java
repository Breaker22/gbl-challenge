package ar.com.gbl.user.challenge.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.util.CollectionUtils;

import ar.com.gbl.user.challenge.model.entity.Phone;
import ar.com.gbl.user.challenge.model.entity.User;
import ar.com.gbl.user.challenge.model.request.PhoneRequest;

public final class BuildPhoneUtils {
	
	public static List<Phone> buildPhoneEntity(List<PhoneRequest> listPhoneRequest, User user) {
		ArrayList<Phone> listPhones = new ArrayList<>();

		if (!CollectionUtils.isEmpty(listPhoneRequest)) {
			for (PhoneRequest phRequest : listPhoneRequest) {
				Phone phone = new Phone();

				phone.setId(UUID.randomUUID().toString());
				phone.setNumber(phRequest.getNumber());
				phone.setCityCode(phRequest.getCityCode());
				phone.setCountryCode(phRequest.getCountryCode());
				phone.setUser(user);

				listPhones.add(phone);
			}
		}

		return listPhones;
	}

	public static List<PhoneRequest> buildPhoneRequest(User user) {
		ArrayList<PhoneRequest> listPhones = new ArrayList<>();

		if (!CollectionUtils.isEmpty(user.getPhones())) {
			for (Phone phone : user.getPhones()) {
				PhoneRequest phoneRequest = new PhoneRequest();

				phoneRequest.setCityCode(phone.getCityCode());
				phoneRequest.setCountryCode(phone.getCountryCode());
				phoneRequest.setNumber(phone.getNumber());

				listPhones.add(phoneRequest);
			}
		}

		return listPhones;
	}
}