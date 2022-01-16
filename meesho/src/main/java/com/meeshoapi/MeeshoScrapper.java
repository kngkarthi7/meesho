package com.meeshoapi;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("products")
public class MeeshoScrapper {

	@GetMapping(value = "getProducts")
	public String getProductsInfo(HttpServletRequest request, HttpServletResponse response, Model model)
			throws IOException, JsonProcessingException {
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
//		for (Element element : doc.getAllElements()) {
//			if (element.hasClass("plp")) {
//				for (Element subElement1 : element.getAllElements()) {
//					if (subElement1.hasClass("col-6")) {
//						System.out.println("==============================================");
//						product = new ProductView();
//					}
//					if (subElement1.hasClass("plp-card-title")) {
//						System.out.println("Name : " + subElement1.text());
//						product.setProductName(subElement1.text());
//					}
//					if (subElement1.hasClass("actual-cost")) {
//						System.out.println("Cost : " + subElement1.text().substring(1));
//						product.setProductCost(Integer.parseInt(subElement1.text().substring(1)));
//					}
//					if (subElement1.hasClass("plp-card")) {
//						System.out.println("Product Link : " + subElement1.attr("href"));
//						product.setProductLink(subElement1.attr("href"));
//					}
//					if (subElement1.hasClass("plp-img")) {
//						System.out.println("Image  Link : " + subElement1.attr("data-src"));
//						product.setProductImageLink(subElement1.attr("data-src"));
//						pdView.getProducts().add(product);
//					}
//				}
//
//			}
//		}
		for (Element element : doc.getAllElements()) {
//			System.out.println("Sub ELement Data : "+element.className());
			if (element.className().contains("sc-dkPtRN")) {
				ProductView productView = new ProductView();
//				System.out.println("========================================================");
				for (Node childNode : element.childNodes()) {
					if (!childNode.attr("href").trim().equals("")) {
//						System.out.println("href : " + childNode.attr("href"));
						productView.setProductLink(childNode.attr("href"));
					}
				}
				
				Elements imgelement = element.children().select("img");
				imgelement.forEach(imgele -> {
					if (imgele.attr("src").startsWith("https")) {
//						System.out.println("img link : "+imgele.attr("src"));
						productView.setProductImageLink(imgele.attr("src"));
//						System.out.println("Name : " + imgele.attr("alt"));
						productView.setProductName(imgele.attr("alt"));
					}
				});
				
				Elements priceElements = element.children().select("h5");
				priceElements.forEach(priceElement-> {
//					System.out.println("priceElement : "+priceElement.wholeText());
					productView.setProductCost(Integer.parseInt(priceElement.wholeText().substring(1)));
				});
				if(productView.isValid()) {
					pdView.getProducts().add(productView);
				}
				
//				System.out.println("========================================================");
				
			}
//			Elements subElements = element.getElementsContainingText("NewProductCard");
//			for(Element subElement : subElements) {
//				
//				
//				System.out.println("Sub ELement Data : "+subElement.data());
//			}
		}
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(pdView);
		System.out.println(json);
		System.out.println(pdView.getProducts().size());
		return json;
	}

	public static void main(String[] args) throws IOException {
		String url = "https://meesho.com/ethnicwear-women/pl/4dxfc";
		StringBuilder finalText = new StringBuilder();
//		Document doc = Jsoup.connect("https://meesho.com/topwear-men/pl/zcm6l").userAgent("Mozilla/5.0").get();
		Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0").get();
//		System.out.println("doc : " + doc);
		// Elements elements = doc.select("table.uk-table uk-table-hover
		// uk-table-striped no-footer dataTable");
//		 System.out.println(doc.html());
		// Element element = doc.getElementById("buyorder");
		// Elements elements = doc.getElementsByClass("plp");
		ProductsDisplayView pdView = new ProductsDisplayView();
		ProductView product = null;
//		for (Element element : doc.getAllElements()) {
//			if(element.hasClass("plp")) {		
//				for(Element subElement1 : element.getAllElements()) {
//					if(subElement1.hasClass("col-6")) {
//						System.out.println("==============================================");
//						product = new ProductView();
//					}
//					if(subElement1.hasClass("plp-card-title")) {
//						System.out.println("Name : "+subElement1.text());
//						product.setProductName(subElement1.text());
//					}
//					if(subElement1.hasClass("actual-cost")) {
//						System.out.println("Cost : "+subElement1.text().substring(1));
//						product.setProductCost(Integer.parseInt(subElement1.text().substring(1)));
//					}	
//					if (subElement1.hasClass("plp-card")) {
//						System.out.println("Product Link : "+subElement1.attr("href"));
//						product.setProductLink(subElement1.attr("href"));
//					}
//					if(subElement1.hasClass("plp-img")) {
//						System.out.println("Image  Link : "+ subElement1.attr("data-src"));
//						product.setProductImageLink(subElement1.attr("data-src"));
//						pdView.getProducts().add(product);
//					}
//				}
//				
//			}
//		}
		for (Element element : doc.getAllElements()) {
//			System.out.println("Sub ELement Data : "+element.className());
			if (element.className().contains("sc-dkPtRN")) {
				ProductView productView = new ProductView();
//				System.out.println("========================================================");
				for (Node childNode : element.childNodes()) {
					if (!childNode.attr("href").trim().equals("")) {
//						System.out.println("href : " + childNode.attr("href"));
						productView.setProductLink(childNode.attr("href"));
					}
				}
				
				Elements imgelement = element.children().select("img");
				imgelement.forEach(imgele -> {
					if (imgele.attr("src").startsWith("https")) {
//						System.out.println("img link : "+imgele.attr("src"));
						productView.setProductImageLink(imgele.attr("src"));
//						System.out.println("Name : " + imgele.attr("alt"));
						productView.setProductName(imgele.attr("alt"));
					}
				});
				
				Elements priceElements = element.children().select("h5");
				priceElements.forEach(priceElement-> {
//					System.out.println("priceElement : "+priceElement.wholeText());
					productView.setProductCost(Integer.parseInt(priceElement.wholeText().substring(1)));
				});
				if(productView.isValid()) {
					pdView.getProducts().add(productView);
				}
				
//				System.out.println("========================================================");
				
			}
//			Elements subElements = element.getElementsContainingText("NewProductCard");
//			for(Element subElement : subElements) {
//				
//				
//				System.out.println("Sub ELement Data : "+subElement.data());
//			}
		}
//		pdView.getProducts().forEach(pdv -> {
//			System.out.println("***********"+pdv+"*************");
//		});
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(pdView);
		System.out.println(json);
		System.out.println(pdView.getProducts().size());
	}
}
