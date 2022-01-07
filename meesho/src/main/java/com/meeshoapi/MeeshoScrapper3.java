package com.meeshoapi;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("productsMenu")
public class MeeshoScrapper3 {

	@GetMapping(value="getMenu")
	public String mainProducts() throws IOException {
		System.out.println("hello");
		String finalJson = null;
		Document doc = Jsoup.connect("https://meesho.com")
				.userAgent("Mozilla/5.0").get();
		// Elements elements = doc.select("table.uk-table uk-table-hover
		// uk-table-striped no-footer dataTable");
//		 System.out.println(doc.select("script"));
		 Elements elements = doc.select("script");
		 for(Element element : elements) {
			 for(DataNode dataNode : element.dataNodes()) {
				 if(dataNode.getWholeData().startsWith("{\"props\"")) {
//					 System.out.println("data : "+dataNode.getWholeData());
					 finalJson = dataNode.getWholeData();
					 }
			 }
//			 if(element.text().trim().startsWith("window._PRELOADED_STATE_")) {
//				 String json = element.text().replaceAll("window._PRELOADED_STATE_ =", "");
//				 System.out.println(json);
//			 }
		 }
		 return finalJson;
	}
	
	public static void main(String[] args) throws IOException{
		System.out.println("hello");
		String finalJson = null;
		Document doc = Jsoup.connect("https://meesho.com")
				.userAgent("Mozilla/5.0").get();
		// Elements elements = doc.select("table.uk-table uk-table-hover
		// uk-table-striped no-footer dataTable");
//		 System.out.println(doc.select("script"));
		 
		 Elements elements = doc.select("script");
//		 System.out.println("doc : "+doc);
		 for(Element element : elements) {
			 for(DataNode dataNode : element.dataNodes()) {
				 if(dataNode.getWholeData().startsWith("{\"props\"")) {
				 System.out.println("data : "+dataNode.getWholeData());
				 }
//				 if(dataNode.getWholeData().startsWith("window._PRELOADED_STATE_")) {
//					 String json = dataNode.getWholeData().replaceAll("window._PRELOADED_STATE_ =", "");
//					 System.out.println(json);
//					 finalJson = json;
//					 break;
//				 }
			 }
//			 if(element.text().trim().startsWith("window._PRELOADED_STATE_")) {
//				 String json = element.text().replaceAll("window._PRELOADED_STATE_ =", "");
//				 System.out.println(json);
//			 }
		 }
	}
}
