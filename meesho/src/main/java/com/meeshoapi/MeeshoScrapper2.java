package com.meeshoapi;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("productsInfo")
public class MeeshoScrapper2 {
	private static final String CLIENT_ID = "FREE_TRIAL_ACCOUNT";
	private static final String CLIENT_SECRET = "PUBLIC_SECRET";
	private static final String ENDPOINT = "http://api.whatsmate.net/v1/translation/translate";
	public static int count = 0;

	@GetMapping(value = "getProductInfo")
	public String getProductInfo(HttpServletRequest request, HttpServletResponse response, Model model)
			throws IOException, JsonProcessingException {
		String finalData = null;
		System.out.println("hello");
		String finalJson = null;
		String url = request.getParameter("url");
		Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0").get();
		// Elements elements = doc.select("table.uk-table uk-table-hover
		// uk-table-striped no-footer dataTable");
//		 System.out.println(doc.select("script"));

		Elements elements = doc.select("script");
//		 System.out.println("doc : "+doc);
		for (Element element : elements) {
			for (DataNode dataNode : element.dataNodes()) {
				if (dataNode.getWholeData().startsWith("{\"props\"")) {
					finalData = dataNode.getWholeData();
					System.out.println("data : " + dataNode.getWholeData());
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
		return finalData;
	}

//	public static void main(String[] args) throws Exception{
//		Document doc = Jsoup.connect("https://meesho.com/aadab-fashionable-men-sports-shoes/p/16yfb9")
//						.userAgent("Mozilla/5.0").get();
//		System.out.println(doc);
//		
//	}

	public static void main(String[] args) throws IOException {
		System.out.println("hello");
		String finalJson = null;
		Document doc = Jsoup.connect("https://meesho.com/aadab-fashionable-men-sports-shoes/p/16yfb9")
				.userAgent("Mozilla/5.0").get();
		// Elements elements = doc.select("table.uk-table uk-table-hover
		// uk-table-striped no-footer dataTable");
//		 System.out.println(doc.select("script"));

		Elements elements = doc.select("script");
//		 System.out.println("doc : "+doc);
		for (Element element : elements) {
			for (DataNode dataNode : element.dataNodes()) {
				if (dataNode.getWholeData().startsWith("{\"props\"")) {
					System.out.println("data : " + dataNode.getWholeData());
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

//String url = (String) request.getParameter("url");
//StringBuilder finalText = new StringBuilder();
////Document doc = Jsoup.connect("https://meesho.com/stylish-casual-cotton-solid-t-shirt/p/k5so")
//Document doc = Jsoup.connect(url)
//		.userAgent("Mozilla/5.0").get();
//// Elements elements = doc.select("table.uk-table uk-table-hover
//// uk-table-striped no-footer dataTable");
//// System.out.println(doc.html());
//// Element element = doc.getElementById("buyorder");
//// Elements elements = doc.getElementsByClass("plp");
//ProductInfoView productInfoView = new ProductInfoView();
//for (Element element : doc.getAllElements()) {
//	if (element.hasClass("container Mobile")) {
//		for (Element subElement : element.getAllElements()) {
//			if (subElement.hasClass("pdp-img")) {
//				productInfoView.setProductImageLink(subElement.attr("data-src"));
//				System.out.println("productImage : " + subElement.attr("data-src"));
//			} else if (subElement.hasClass("actual-cost")) {
//				productInfoView.setProductCost(Integer.parseInt(subElement.text().substring(1)));
//				System.out.println("Cost : " + subElement.text().substring(1));
//			} else if (subElement.hasClass("similar-product-list")) {
//				Elements aTagElements = subElement.select("a");
//				Elements imgTagElements = subElement.select("img");
//				System.out.println(aTagElements.size());
//				System.out.println(imgTagElements.size());
//				for (int i = 0; i < aTagElements.size(); i++) {
//					SimilarProductsView spView = new SimilarProductsView();
//					Element aTagElement = aTagElements.get(i);
//					Element imgTagElement = imgTagElements.get(i);
//					System.out.println("Url : " + aTagElement.attr("href"));
//					System.out.println("ImageUrl : " + imgTagElement.attr("data-src"));
//					spView.setProductUrl(aTagElement.attr("href"));
//					spView.setImageUrl(imgTagElement.attr("data-src"));
//					productInfoView.getSimilarProducts().add(spView);
//				}
//			} else if (subElement.hasClass("chip-button")) {
//				for(Element sizeElement : subElement.getAllElements()) {
//					productInfoView.getSizesAvailable().add(sizeElement.text());					
//				}
//			} else if (subElement.hasClass("product-description")) {
//				System.out.println("productdescription");
//				Elements preElements = subElement.select("li");
//				StringBuilder productDesc = new StringBuilder();
//				for(Element descElement : preElements) {
//					productDesc.append(descElement.text());
//					System.out.println(descElement.text());
//				}
//				productInfoView.setProductDescription(productDesc.toString());
//			}
//		}
//	}
//}
//ObjectMapper mapper = new ObjectMapper();
//String json = mapper.writeValueAsString(productInfoView);
//System.out.println(json);
//return json;
