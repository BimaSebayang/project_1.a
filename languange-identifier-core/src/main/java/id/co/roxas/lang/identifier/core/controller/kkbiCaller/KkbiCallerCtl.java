package id.co.roxas.lang.identifier.core.controller.kkbiCaller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.roxas.lang.identifier.core.dao.TblCombinationWordRepositoryDao;
import id.co.roxas.lang.identifier.core.dao.TblLangRepositoryTempDao;
import id.co.roxas.lang.identifier.core.repository.TblCombinationWordRepository;
import id.co.roxas.lang.identifier.core.repository.TblLangRepositoryTemp;

@RestController
@RequestMapping("/lang-init")
public class KkbiCallerCtl {

	@Autowired
	private TblLangRepositoryTempDao tblLangRepositoryDao;

	@Autowired
	private TblCombinationWordRepositoryDao tblCombinationWordRepositoryDao;

	@RequestMapping("/comb-word/{jumlahHuruf}")
	public String helloCombWord(@PathVariable("jumlahHuruf") int jumlahHuruf) {
		
	//	List<String> arrs = tblCombinationWordRepositoryDao.getAllWords(jumlahHuruf-1);
		
		//System.err.println(new Gson().toJson(arrs));
			List<TblCombinationWordRepository>combinationWordRepositories = new ArrayList<>();
	
				for (String huruf : buatHuruf(SemuaAbjad(),jumlahHuruf)) {			
					TblCombinationWordRepository repository = new TblCombinationWordRepository();
					repository.setCombWord(huruf);
					repository.setCountWord(huruf.toCharArray().length);
					repository.setCreatedBy("ME");
					repository.setCreatedDate(new Date());
					tblCombinationWordRepositoryDao.save(repository);
					//combinationWordRepositories.add(repository);
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

	@RequestMapping("/call/{hurufMin}/{hurufMax}")
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
		}
		else {
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
