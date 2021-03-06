package com.bridgeit.ipl.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bridgeit.ipl.model.Team;
import com.bridgeit.ipl.service.TeamService;

@Controller
@RequestMapping("/")
public class TeamController {
	@Autowired
	TeamService teamService;
	
	private Logger logger = Logger.getLogger(TeamController.class);

	@RequestMapping(value="upload", method=RequestMethod.GET)
	public String init()
	{
		return "fileUpload";
	}
	
	@RequestMapping(value="upload", method=RequestMethod.POST)
	public String fileUpload(@RequestParam("file") MultipartFile file){
		String fileName=file.getOriginalFilename();
		System.out.println(fileName);
		
		Team tem;Reader reader =null;
		JSONParser parser = new JSONParser();
		try {
			reader = new InputStreamReader(file.getInputStream());
			Object ob = parser.parse(reader);
			JSONObject object = (JSONObject) ob;

			JSONArray data = (JSONArray) object.get("teaminfo");

			for (int i = 0; i < data.size(); i++) {
				tem = new Team();
				JSONObject itemObj = (JSONObject) data.get(i);

				Object nameObj = itemObj.get("team_name");
				String teamName = (String) nameObj;
				tem.setName(teamName);

				Object coachObj = itemObj.get("team_coach");
				String coachName = (String) coachObj;
				tem.setCoach(coachName);

				Object capatainObj = itemObj.get("team_captain");
				String capatainName = (String) capatainObj;
				tem.setCaptain(capatainName);

				Object venueObj = itemObj.get("team_home_venue");
				String venueName = (String) venueObj;
				tem.setHomevenue(venueName);

				Object ownerObj = itemObj.get("team_owner");
				String ownerName = (String) ownerObj;
				tem.setOwner(ownerName);

				Object logoObj = itemObj.get("team_img_url");
				String logoName = (String) logoObj;
				tem.setLogo(logoName);
				
				String nameOfTeam =teamService.getTeamName(teamName);
				if (nameOfTeam == null)
					teamService.addTeam(tem);
				
				}

		} catch (Exception e) {
			System.out.println(e);
		}
		finally {
			if(reader!=null)
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return "signin";
	}
	
	@RequestMapping(value="teamList", method=RequestMethod.GET)
	public ModelAndView displayAllTeam(){
		logger.info("this is log4j");
		List<Team> teamList = teamService.displayAllTeam();
		return new ModelAndView("teamList", "teamList", teamList);
	}
	
	@RequestMapping(value="teamDetails", method=RequestMethod.GET)
	public ModelAndView displayTeamDetails(@RequestParam("teamId")long teamId ){
		Team team = teamService.getTeamById(teamId);
		ModelAndView mav=new ModelAndView("teamDetails","team", team);
		return mav;
	}
}
