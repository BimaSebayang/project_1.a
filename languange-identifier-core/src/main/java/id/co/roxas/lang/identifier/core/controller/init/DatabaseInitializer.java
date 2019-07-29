package id.co.roxas.lang.identifier.core.controller.init;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import id.co.roxas.data.transfer.object.languangeIdentifierCore.core.TblCombinationWordRepositoryDto;
import id.co.roxas.lang.identifier.core.controller.BaseController;
import id.co.roxas.lang.identifier.core.dao.TblCombinationWordRepositoryDao;
import id.co.roxas.lang.identifier.core.dao.TblLangRepositoryTempDao;
import id.co.roxas.lang.identifier.core.dao.TblSynonimsWordRepositoryDao;
import id.co.roxas.lang.identifier.core.repository.TblCombinationWordRepository;
import id.co.roxas.lang.identifier.core.repository.TblLangRepositoryTemp;
import id.co.roxas.lang.identifier.core.repository.TblSynonimsWordRepositoryTemp;
import id.co.roxas.lang.identifier.core.service.generator.TblLangRepositoryTempGenerator;

@RestController
@RequestMapping("/database-init")
public class DatabaseInitializer extends BaseController{

	@Autowired
	private TblCombinationWordRepositoryDao tblCombinationWordRepositoryDao;

	@Autowired
	private TblLangRepositoryTempDao tblLangRepositoryTempDao;
	
	@Autowired
	private TblLangRepositoryTempGenerator tblLangRepositoryTempGenerator;

	@Autowired
	private TblSynonimsWordRepositoryDao tblSynonimsWordRepositoryDao;
	
	@GetMapping("/test")
	public String setInitiliazerWordAll() {
		List<String> app = new ArrayList<>();
		app.add("101");
		app.add("102");
		app.add("103");
		app.add("104");
		app.add("105");
		app.add("106");
	
		List<TblCombinationWordRepository> combinationWordRepositories = tblCombinationWordRepositoryDao
				.getAllWordsInCondition(app);

		for (TblCombinationWordRepository comb : combinationWordRepositories) {
			String input = comb.getAdditionInfo();
			Pattern p = Pattern.compile("(?<=\\bhttps://kbbi.co.id/arti-kata/\\b).*?(?=\\b\">\\b)");
			Matcher m = p.matcher(input);
			List<String> matches = new ArrayList<>();
			while (m.find()) {
//	    		if(m.group().equalsIgnoreCase("tresna")) {
//	    			System.out.println(comb.getCombWord());
//	    		}
				if (m.group().toCharArray().length <= 100) {
					TblCombinationWordRepository repository = new TblCombinationWordRepository();
					repository.setCombWord(m.group());
					repository.setCountWord(m.group().toCharArray().length);
					repository.setCreatedBy("ME");
					repository.setCreatedDate(new Date());
					tblCombinationWordRepositoryDao.save(repository);
				}
			}
			// System.out.println(comb.getCombWord() + " : " + comb.getAdditionInfo());
		}
		return "Finish";
	}

	@GetMapping("/save-meaning/{letter}")
	public String setAllWords(@PathVariable("letter") String letter) {
		List<String> getWords = tblLangRepositoryTempDao.getWords();
		List<String> getCombWords = tblCombinationWordRepositoryDao.getAllComb(getWords);
		List<TblLangRepositoryTemp> tblLangRepositoryTemps = new ArrayList<>();
		for (String comb : getCombWords) {
			if (comb.toUpperCase().startsWith(letter.toUpperCase())) {
				connectCombWord(comb);
			}
		}
		tblLangRepositoryTempDao.saveAll(tblLangRepositoryTemps);
		return "Finish";
	}
	
	@GetMapping("/save-synonimz")
	public String setAllWords() {
		connectSynonims();
		return "Finish";
	}

	public void connectCombWord(String word) {
		String sw = word.replace(" ", "+");

		String nameurl = "https://jagokata.com/arti-kata/" + sw + ".html";
		System.err.println("call url : " + nameurl);
		// Set URL
		URL url = null;
		try {
			url = new URL(nameurl);
		} catch (MalformedURLException e) {
			System.err.println("404 : " + nameurl);
			return;
		}
		URLConnection spoof = null;
		try {
			spoof = url.openConnection();
		} catch (IOException e) {
			System.err.println("404 : " + nameurl);
			return;
		}

		// Spoof the connection so we look like a web browser
		spoof.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0; H010818)");
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(spoof.getInputStream()));
			String strLine = "";

			// Loop through every line in the source
			StringBuilder sb = new StringBuilder();
			while ((strLine = in.readLine()) != null) {
				sb.append(strLine);
			}
			TblLangRepositoryTemp tblLangRepositoryTemp = new TblLangRepositoryTemp();
			tblLangRepositoryTemp.setLangId(tblLangRepositoryTempGenerator.checkEssentialId
					("LANGO", 100));
			tblLangRepositoryTemp.setCreatedBy("ME");
			tblLangRepositoryTemp.setCreatedDate(new Date());
			tblLangRepositoryTemp.setLangDesc(sb.toString());
			tblLangRepositoryTemp.setLangResource(nameurl);
			tblLangRepositoryTemp.setLangName(word);
			tblLangRepositoryTempDao.save(tblLangRepositoryTemp);
			
		} catch (IOException e) {
			System.err.println("404 : " + nameurl);
			return;
		}
	}
	
	public void connectSynonims() {
        String alphabet = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z";
        String[] splitterAlphabet = alphabet.split(",");
        
        for (String sa : splitterAlphabet) {
        	String nameurl = "https://www.maknaa.com/sinonim-persamaan-kata?start="+sa.trim();
    		System.err.println("call url : " + nameurl);
    		// Set URL
    		URL url = null;
    		try {
    			url = new URL(nameurl);
    		} catch (MalformedURLException e) {
    			System.err.println("404 : " + nameurl);
    			return;
    		}
    		URLConnection spoof = null;
    		try {
    			spoof = url.openConnection();
    		} catch (IOException e) {
    			System.err.println("404 : " + nameurl);
    			return;
    		}

    		// Spoof the connection so we look like a web browser
    		spoof.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0; H010818)");
    		BufferedReader in = null;
    		try {
    			in = new BufferedReader(new InputStreamReader(spoof.getInputStream()));
    			String strLine = "";

    			// Loop through every line in the source
    			StringBuilder sb = new StringBuilder();
    			while ((strLine = in.readLine()) != null) {
    				sb.append(strLine);
    			}
    			
    			for (String pattern : findAllPatternBussiness("<tbody>", "</tbody>", sb.toString())) {
					for (String comp : findAllPatternBussiness("<tr>", "</tr>", pattern)) {
						 List<String> elements = findAllPatternBussiness("<td>", "</td>", comp);
                         String element1 = findAllPatternBussiness("\">", "</a>", elements.get(1)).get(0).replaceAll("\">", "").replaceAll("</a>", "").trim() ;
                         String element2 = elements.get(2).replaceAll("<td>", "").replaceAll("</td>", "").trim();
              //    System.err.println(element2);
					     TblSynonimsWordRepositoryTemp tblSynonimsWordRepositoryTemp = new TblSynonimsWordRepositoryTemp();
					     tblSynonimsWordRepositoryTemp.setIsActive(0);
					     tblSynonimsWordRepositoryTemp.setCreatedBy("ME");
					     tblSynonimsWordRepositoryTemp.setCreatedDate(new Date());
					     tblSynonimsWordRepositoryTemp.setLangDesc(element2);
					     tblSynonimsWordRepositoryTemp.setLangName(element1);
					     tblSynonimsWordRepositoryTemp.setLangResource(nameurl);
					     tblSynonimsWordRepositoryDao.save(tblSynonimsWordRepositoryTemp);
					}
				}
    			
    		} catch (IOException e) {
    			System.err.println("404 : " + nameurl);
    			return;
    		}
		}
	}
	
	protected List<String> findAllPatternBussiness(String regexstart, String regexEnd, String word){
		List<String> patterns = new ArrayList<>();
		String patternStr = Pattern.quote(regexstart)+"(.*?)"+Pattern.quote(regexEnd);
		//System.err.println(word);
		Pattern p = Pattern.compile(patternStr);
		Matcher m = p.matcher(word);
		while (m.find()) {
			patterns.add(m.group());
		}
		
		return patterns;
	}

}
