package com.meeshoapi;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("products")
public class MeeshoScrapper {

	@PostMapping(value="getProducts")
	public String getProductsInfo(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException, JsonProcessingException {
		String url = (String) request.getParameter("url");
		StringBuilder finalText = new StringBuilder();
//		Document doc = Jsoup.connect("https://meesho.com/topwear-men/pl/zcm6l").userAgent("Mozilla/5.0").get();
		Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0").get();
		// Elements elements = doc.select("table.uk-table uk-table-hover
		// uk-table-striped no-footer dataTable");
//		 System.out.println(doc.html());
		// Element element = doc.getElementById("buyorder");
		// Elements elements = doc.getElementsByClass("plp");
		ProductsDisplayView pdView = new ProductsDisplayView();
		ProductView product = null;
		for (Element element : doc.getAllElements()) {
			if(element.hasClass("plp")) {		
				for(Element subElement1 : element.getAllElements()) {
					if(subElement1.hasClass("col-6")) {
						System.out.println("==============================================");
						product = new ProductView();
					}
					if(subElement1.hasClass("plp-card-title")) {
						System.out.println("Name : "+subElement1.text());
						product.setProductName(subElement1.text());
					}
					if(subElement1.hasClass("actual-cost")) {
						System.out.println("Cost : "+subElement1.text().substring(1));
						product.setProductCost(Integer.parseInt(subElement1.text().substring(1)));
					}	
					if (subElement1.hasClass("plp-card")) {
						System.out.println("Product Link : "+subElement1.attr("href"));
						product.setProductLink(subElement1.attr("href"));
					}
					if(subElement1.hasClass("plp-img")) {
						System.out.println("Image  Link : "+ subElement1.attr("data-src"));
						product.setProductImageLink(subElement1.attr("data-src"));
						pdView.getProducts().add(product);
					}
				}
				
			}
		}
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(pdView);
		System.out.println(json);
		System.out.println(pdView.getProducts().size());
		return json;
	}
}
