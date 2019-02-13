package org.anup.programmingSearch.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.anup.programmingSearch.messenger.database.DatabaseClass;
import org.anup.programmingSearch.messenger.model.Profile;


public class ProfileService {

	
	Map<String,Profile> profiles= DatabaseClass.getProfiles();
	
	public ProfileService(){
		profiles.put("Anup", new Profile(1L,"Anup","Anup","Darekar"));
	}
	
	public List<Profile> getAllProfile(){
		return new ArrayList(profiles.values());
	}
	
	public Profile getProfile(String profileName){
		return profiles.get(profileName);
	}
	
	public Profile addProfile(Profile profile){
		profile.setId(profiles.size() + 1);
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile updateProfile(Profile profile){
		
		if(profile.getProfileName().isEmpty()){
			return null;
		}
		
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile removeProfile(String profileName){
		return profiles.remove(profileName);
	}
	
}
