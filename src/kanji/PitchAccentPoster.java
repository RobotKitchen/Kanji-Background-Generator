package kanji;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class PitchAccentPoster {
	
	Document doc = null;
	
	public void sendPost(String kanjiSentence) throws Exception {
		Document doc = Jsoup.connect("http://www.gavo.t.u-tokyo.ac.jp/ojad/phrasing/index").userAgent(
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36")
				.data("_method", "POST").data("data[Phrasing][text]", kanjiSentence)
				.data("data[Phrasing][curve]", "advanced").data("data[Phrasing][accent]", "advanced")
				.data("data[Phrasing][accent_mark]", "all").data("data[Phrasing][estimation]", "crf")
				.data("data[Phrasing][analyze]", "true").data("data[Phrasing][phrase_component]", "invisible")
				.data("data[Phrasing][param]", "invisible").data("data[Phrasing][subscript]", "visible")
				.data("data[Phrasing][jeita]", "invisible").timeout(10000).post();
		
		this.doc = doc;
	}
	
	public String getKana() {
		System.out.println("starting");
		for(Element e:doc.getElementsByClass("phrasing_text")) {
				return e.text();
		}
		return null;
	}
	
	public List<Integer> getPitch(){
		System.out.println("starting");
		for(Element e:doc.getElementsByClass("phrasing_text")) {
			for(Element d:e.getElementsByTag("span")) {
				for(String k: d.classNames()) {
					System.out.println(k);
				}
				System.out.println(d.text());
			}
		}
		return null;
	}
}
