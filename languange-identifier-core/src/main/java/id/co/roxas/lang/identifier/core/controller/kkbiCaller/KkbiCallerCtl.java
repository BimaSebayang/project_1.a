package id.co.roxas.lang.identifier.core.controller.kkbiCaller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.roxas.data.transfer.object.languangeIdentifierCore.custom.KbbiMapperDto;
import id.co.roxas.lang.identifier.core.controller.BaseController;
import id.co.roxas.lang.identifier.core.dao.TblCombinationWordRepositoryDao;
import id.co.roxas.lang.identifier.core.dao.TblLangRepositoryTempDao;
import id.co.roxas.lang.identifier.core.dao.TblLangRepositoryTempDtlDao;
import id.co.roxas.lang.identifier.core.dao.TblSynonimsWordRepositoryDao;
import id.co.roxas.lang.identifier.core.repository.TblCombinationWordRepository;
import id.co.roxas.lang.identifier.core.repository.TblLangRepositoryTemp;
import id.co.roxas.lang.identifier.core.repository.TblLangRepositoryTempDtl;
import id.co.roxas.lang.identifier.core.repository.TblSynonimsWordRepositoryTemp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/lang-init")
public class KkbiCallerCtl extends BaseController{

	@Autowired
	private TblLangRepositoryTempDao tblLangRepositoryDao;

	@Autowired
	private TblCombinationWordRepositoryDao tblCombinationWordRepositoryDao;

	@Autowired
	private TblSynonimsWordRepositoryDao tblSynonimsWordRepositoryTempDao;
	
	@Autowired
	private TblLangRepositoryTempDao tblLangRepositoryTempDao;
	
	@Autowired
	private TblLangRepositoryTempDtlDao tblLangRepositoryTempDtlDao;

	@GetMapping("/word-letter/{word}")
	private List<List<String>> getWord(@PathVariable("word") String huruf) {
		List<List<String>> hs = new ArrayList<>();
		List<TblLangRepositoryTemp> list = tblLangRepositoryTempDao.getTempDataInfo(huruf);
		for (TblLangRepositoryTemp tblLangRepositoryTemp : list) {
			String[] pol = tblLangRepositoryTemp.getLangDesc().split("&lt;b&gt");
			// split("&lt;/b&gt; &lt;i&gt");
			// hs.add(Arrays.asList(pol));
			hs.add(replaceSomeWord(pol));
		}
		return hs;
	}
	
	@GetMapping("/map-word-letter/{word}")
	private List<KbbiMapperDto> getWordKbbiMapper(@PathVariable("word") String huruf) {
		List<KbbiMapperDto> hs = new ArrayList<>();
		List<TblLangRepositoryTemp> list = tblLangRepositoryTempDao.getTempDataInfo(huruf);
		for (TblLangRepositoryTemp tblLangRepositoryTemp : list) {
			String[] pol = tblLangRepositoryTemp.getLangDesc().split("&lt;b&gt");
			//KbbiMapperDto kbbiMapperDto= mapKbbi(replaceSomeWord(pol));
			hs.addAll(mapKbbi(replaceSomeWord(pol)));
		}
		return hs;
	}
	
	@GetMapping("/word-letter/synonims/save")
	private String onProgressSaveSynonims() throws Exception {
		List<String> list = tblLangRepositoryTempDtlDao.getAllWords();
		for (String string : list) {
			ConnectSynonim(string);
		}
		return "DONE";
	}
	
	@GetMapping("/word-letter/comb-word/save")
	private String onProgressWordComb() throws Exception {
	
		for (int i =1; i<=106 ; i++) {
			connectCombWord(i);
		}
		return "DONE";
	}
	
	@GetMapping("/word-letter/save")
	private String onProgressSave(Pageable pageable) {
		//Page<TblLangRepositoryTemp> page = tblLangRepositoryTempDao.getAllTempPage(pageable);
		//List<TblLangRepositoryTemp> page = tblLangRepositoryTempDao.findAll();
		List<TblLangRepositoryTemp> page = tblLangRepositoryTempDao.getAllTempPage();
		List<TblLangRepositoryTempDtl> dtls = new ArrayList<>();
		for (TblLangRepositoryTemp tblLangRepositoryTemp : page) {
			 String[] pol = tblLangRepositoryTemp.getLangDesc().split("&lt;b&gt");
		     List<KbbiMapperDto> dtos = mapKbbi(replaceSomeWord(pol));
		     for (KbbiMapperDto tblLangRepositoryTempDtl : dtos) {
		    	 TblLangRepositoryTempDtl dtl = new TblLangRepositoryTempDtl();
		    	 dtl.setCreatedBy("ME");
		    	 dtl.setCreatedDate(new Date());
		    	 String h = new String();
                 for (Entry<String, String> lang: 
                	                    tblLangRepositoryTempDtl.getExampleDetailHeadLabel().entrySet()) {
					h = h.concat(lang.getKey().concat(" "));
				 }
                 dtl.setLangDesc(h);
                 dtl.setLangName(tblLangRepositoryTempDtl.getHeadLabel());
                // dtl.setLangIdDtl("TEST");
                 dtl.setLangResource("TBL_LANG_REPOSITORY_TEMP");
                 dtl.setRoleDetail("MEANING");
                 dtl.setTblId(tblLangRepositoryTemp);
                 System.err.println("akan save kal " + tblLangRepositoryTemp.getLangName());
                 tblLangRepositoryTempDtlDao.save(dtl);
                 //dtls.add(dtl);
			 }
		}
		//tblLangRepositoryTempDtlDao.saveAll(dtls);
		return "DONE";
	}
	
    private String setValidWord = "";
	private List<KbbiMapperDto> mapKbbi(List<String> word) {
		List<KbbiMapperDto> kbbiMapperDtos = new ArrayList<>();
		
		int i = 0;
		KbbiMapperDto kbbiMapperDto = new KbbiMapperDto();
		Map<String, String> exampleDetailHeadLabel = new HashMap<>();
		for (String w : word) {
			
			if(w.contains("/*head-label*/") )
			{
				
				String repw = changeChar(w, new String[] {"/*head-label*/"}, new String[] {"@"});
				String[] hj = repw.split("@");
				System.out.println(hj[0]);		
				kbbiMapperDto = new KbbiMapperDto();
				kbbiMapperDto.setActualRegex(w);
				exampleDetailHeadLabel = new HashMap<>();
				String temp = removalChar(hj[0], 
						new String[] {"1","2","3","4","5","6","7","8","9","0",";"}).trim();
				
				if(hj.length>1) {
					if(!hj[1].contains("/*example-label*/")&&!hj[1].contains("/*end-label*/")) {
						exampleDetailHeadLabel.put(removalChar(hj[1],new String[] {"/*end-label*/"}),null);
					}
					else {
						String repws = changeChar(hj[1], new String[] {"/*example-label*/"}, new String[] {"@"});
						String[] sjh = repws.split("@");
						if(sjh.length>1) {
						exampleDetailHeadLabel.put(removalChar(sjh[0],new String[] {"/*end-label*/"}), 
								removalChar(sjh[1],new String[] {"/*end-label*/"}));
						}
						else {
							exampleDetailHeadLabel.put(removalChar(sjh[0],new String[] {"/*end-label*/"}),null);	
						}
					}
					kbbiMapperDto.setExampleDetailHeadLabel(exampleDetailHeadLabel);
				}
				
				if(!Strings.isBlank(temp)) {
					setValidWord = temp;
				}
				String wannaDtl =  setValidWord;
				kbbiMapperDto.setHeadLabel(wannaDtl);
			}
			else {
				String repws = changeChar(w, new String[] {"/*example-label*/"}, new String[] {"@"});
				String[] hjs = repws.split("@");
				if(hjs.length>1) {
				exampleDetailHeadLabel.put(hjs[0], hjs[1]);
				}
				else {
					exampleDetailHeadLabel.put(hjs[0],null);	
				}
				kbbiMapperDto.setExampleDetailHeadLabel(exampleDetailHeadLabel);
			}
			
			if(i<word.size()-1) {
				if(word.get(i+1).contains("/*head-label*/") && kbbiMapperDto.getHeadLabel()!=null) {
					kbbiMapperDtos.add(kbbiMapperDto);
				}
			}else {
				if(i==word.size()-1 && kbbiMapperDto.getActualRegex().contains("/*head-label*/")) {
					kbbiMapperDtos.add(kbbiMapperDto);
				}
			}
			
			i++;
		}
		
		return kbbiMapperDtos;
	}


	private List<String> replaceSomeWord(String[] old) {
		List<String> newword = new ArrayList<>();
		List<String> oldList = Arrays.asList(old);
		for (String ol : oldList) {
			String changCh = changeChar(ol, new String[] { "&lt;/b&gt; &lt;i&gt;","&lt;i&gt;","&lt;br&gt;" },
					                        new String[] {" /*head-label*/ "," /*example-label*/ ",
					                        		      " /*end-label*/ "});
			newword.add(removalChar(changCh, new String[] { "&lt;/b&gt;", "&lt;/i&gt;","·",
					                                        "&lt;sup&gt;","&lt;/sup&gt" }));
		}
		return newword;
	}

	private String changeChar(String words, String[] chs, String[] changes) {
		if (chs.length == changes.length) {
			for (int i = 0; i<chs.length; i++) {
				//System.err.println("replace " + words + " regex " + ch + " dengan _/partition/_ ");
				words = words.replace(chs[i], changes[i]);
			}

		}
		return words;
	}

	private String removalChar(String words, String[] chs) {
		for (String ch : chs) {
			//System.err.println("replace " + words + " regex " + ch + " dengan nullspace ");
			words = words.replace(ch, "");
		}

		return words;
	}

	@GetMapping("/comb-word/{jumlahHuruf}")
	public String helloCombWord(@PathVariable("jumlahHuruf") int jumlahHuruf) {

		// List<String> arrs =
		// tblCombinationWordRepositoryDao.getAllWords(jumlahHuruf-1);

		// System.err.println(new Gson().toJson(arrs));
		List<TblCombinationWordRepository> combinationWordRepositories = new ArrayList<>();

		for (String huruf : buatHuruf(SemuaAbjad(), jumlahHuruf)) {
			TblCombinationWordRepository repository = new TblCombinationWordRepository();
			repository.setCombWord(huruf);
			repository.setCountWord(huruf.toCharArray().length);
			repository.setCreatedBy("ME");
			repository.setCreatedDate(new Date());
			tblCombinationWordRepositoryDao.save(repository);
			// combinationWordRepositories.add(repository);
		}

//			for (String huruf : buatHuruf(SemuaAbjad(), arrs, jumlahHuruf)) {
//				TblCombinationWordRepository repository = new TblCombinationWordRepository();
//				repository.setCombWord(huruf);
//				repository.setCountWord(huruf.toCharArray().length);
//				repository.setCreatedBy("ME");
//				repository.setCreatedDate(new Date());
//				combinationWordRepositories.add(repository);
//				//combinationWordRepositories.add(repository);
//			}
		tblCombinationWordRepositoryDao.saveAll(combinationWordRepositories);

		return "DONE";
	}

	@GetMapping("/call/{hurufMin}/{hurufMax}")
	public String helloKkbi(@PathVariable("hurufMin") int hurufMin, @PathVariable("hurufMax") int hurufMax) {
//-XX:PermSize=64 -XX:MaxPermSize=128m 
		try {
			for (int i = hurufMin; i <= hurufMax; i++) {
				Connect(i);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "Done";
	}

	public void Connect(int huruf) throws Exception {

		for (String bh : buatHuruf(SemuaAbjad(), huruf)) {
			// Set URL
			URL url = new URL("https://kbbi.web.id/" + bh);
			URLConnection spoof = url.openConnection();

			// Spoof the connection so we look like a web browser
			spoof.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0; H010818)");
			BufferedReader in = new BufferedReader(new InputStreamReader(spoof.getInputStream()));
			String strLine = "";

			// Loop through every line in the source
			StringBuilder sb = new StringBuilder();
			while ((strLine = in.readLine()) != null) {
				sb.append(strLine);
			}

			if (sb.toString().toLowerCase().contains("</div><b>")) {
				TblLangRepositoryTemp langRepository = new TblLangRepositoryTemp();
				langRepository.setLangName(bh);
				langRepository.setCreatedBy("ME");
				langRepository.setCreatedDate(new Date());
				langRepository.setLangResource("https://kbbi.web.id/" + bh);
				langRepository.setLangDesc(sb.toString());
				tblLangRepositoryDao.save(langRepository);
			}
		}
		System.out.println("End of page.");
	}
	
	public void ConnectSynonim(String huruf)  {

			// Set URL
			URL url = null;
			try {
				url = new URL("https://kamuslengkap.com/kamus/sinonim/arti-kata/" + huruf);
			} catch (MalformedURLException e) {
				System.err.println("404 : " +"https://kamuslengkap.com/kamus/sinonim/arti-kata/" + huruf );
				return;
			}
			URLConnection spoof = null;
			try {
				spoof = url.openConnection();
			} catch (IOException e) {
				System.err.println("404 : " +"https://kamuslengkap.com/kamus/sinonim/arti-kata/" + huruf );
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

					TblSynonimsWordRepositoryTemp  langRepository = new TblSynonimsWordRepositoryTemp();
					langRepository.setLangName(huruf);
					langRepository.setCreatedBy("ME");
					langRepository.setCreatedDate(new Date());
					langRepository.setLangResource("https://kamuslengkap.com/kamus/sinonim/arti-kata/" + huruf);
					langRepository.setLangDesc(sb.toString());
					tblSynonimsWordRepositoryTempDao.save(langRepository);
			} catch (IOException e) {
				System.err.println("404 : " +"https://kamuslengkap.com/kamus/sinonim/arti-kata/" + huruf );
				return;
			}
			
			
		System.out.println("End of page.");
	}
	
	
	public void connectCombWord(int angka)  {
        String nameurl = "https://kbbi.co.id/daftar-kata?page=" + angka;
		// Set URL
		URL url = null;
		try {
			url = new URL(nameurl);
		} catch (MalformedURLException e) {
			System.err.println("404 : " +nameurl);
			return;
		}
		URLConnection spoof = null;
		try {
			spoof = url.openConnection();
		} catch (IOException e) {
			System.err.println("404 : " +nameurl);
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

				TblCombinationWordRepository  langRepository = new TblCombinationWordRepository();
				langRepository.setCombWord(Integer.toString(angka));
				langRepository.setCreatedBy("ME");
				langRepository.setCreatedDate(new Date());
				langRepository.setAdditionInfo(sb.toString());
				tblCombinationWordRepositoryDao.save(langRepository);
		} catch (IOException e) {
			System.err.println("404 : " +"https://kamuslengkap.com/kamus/sinonim/arti-kata/" + nameurl );
			return;
		}
		
		
	System.out.println("End of page.");
}

	public List<String> SemuaAbjad() {
		List<String> arrays = new ArrayList<>();
		arrays.add("A");
		arrays.add("B");
		arrays.add("C");
		arrays.add("D");
		arrays.add("E");
		arrays.add("F");
		arrays.add("G");
		arrays.add("H");
		arrays.add("I");
		arrays.add("J");
		arrays.add("K");
		arrays.add("L");
		arrays.add("M");
		arrays.add("N");
		arrays.add("O");
		arrays.add("P");
		arrays.add("Q");
		arrays.add("R");
		arrays.add("S");
		arrays.add("T");
		arrays.add("U");
		arrays.add("V");
		arrays.add("W");
		arrays.add("X");
		arrays.add("Y");
		arrays.add("Z");
		arrays.add(" ");
		return arrays;
	}

	public List<String> buatHuruf(List<String> arrays, int jumlahHuruf) {
		List<String> lastArr = new ArrayList<>();
		lastArr.addAll(arrays);
		int count = 1;
		while (count <= jumlahHuruf) {
			List<String> newArr = new ArrayList<>();
			for (String la : lastArr) {
				for (String ar : arrays) {
					newArr.add(la.concat(ar));
				}
			}
			lastArr.clear();
			lastArr.addAll(newArr);
			count++;
		}
		return lastArr;
	}

	public List<String> buatHuruf(List<String> arrays, List<String> lastArray, int jumlahHuruf) {

		List<String> lastArr = new ArrayList<>();
		lastArr.addAll(lastArray);
		List<String> newArr = new ArrayList<>();
		if (jumlahHuruf > 1) {
			for (String la : lastArr) {
				for (String ar : arrays) {
					newArr.add(la.concat(ar));
				}
			}
		} else {
			return arrays;
		}
		lastArr.clear();
		lastArr.addAll(newArr);
		return lastArr;
	}

	public List<String> buatHuruf(String arrays, List<String> lastArray) {

		List<String> lastArr = new ArrayList<>();
		lastArr.addAll(lastArray);
		List<String> newArr = new ArrayList<>();
		for (String la : lastArr) {
			newArr.add(la.concat(arrays));
		}
		lastArr.clear();
		lastArr.addAll(newArr);
		return lastArr;
	}

	public List<String> buatHuruf(List<String> arrays, String lastArray) {

		List<String> newArr = new ArrayList<>();
		for (String la : arrays) {
			newArr.add(lastArray.concat(la));
		}
		return newArr;
	}
}
