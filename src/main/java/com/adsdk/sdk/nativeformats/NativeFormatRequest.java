package com.adsdk.sdk.nativeformats;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;

import android.net.Uri;

import com.adsdk.sdk.Const;
import com.adsdk.sdk.Gender;

public class NativeFormatRequest {
	private static final String REQUEST_TYPE = "native";
	private static final String RESPONSE_TYPE = "json";
	private static final String IMAGE_TYPES = "icon"; //TODO: only "icon"?
	private static final String TEXT_TYPES = "headline"; //TODO: again, only that?
	private static final String REQUEST_TYPE_ANDROID = "api";

	private static final String USER_AGENT = "Mozilla/5.0 (iPhone; U; CPU iPhone OS 3_0 like Mac OS X; en-us) AppleWebKit/528.18 (KHTML, like Gecko) Version/4.0 Mobile/7A341 Safari/528.16";

	private String request_url;
	private List<String> adTypes;
	private String publisherId;
	private String userAgent;
	private String userAgent2;
	private String androidAdId = "";
	private boolean adDoNotTrack = false;
	private String protocolVersion;
    private String templateName;

	private double longitude = 0.0;
	private double latitude = 0.0;

    public String ip;

	private Gender gender;
	private int userAge;
	private List<String> keywords;
	
	@Override
	public String toString() {
		return this.toUri().toString();
	}

	public Uri toUri() {
		final Uri.Builder b = Uri.parse(request_url).buildUpon();
		Random r = new Random();
		int random = r.nextInt(50000);
		
		b.appendQueryParameter("rt", REQUEST_TYPE_ANDROID); //TODO: api or android_app?
		b.appendQueryParameter("r_type", REQUEST_TYPE);
		b.appendQueryParameter("r_resp", RESPONSE_TYPE);
		b.appendQueryParameter("n_img", IMAGE_TYPES);
		b.appendQueryParameter("n_txt", TEXT_TYPES);

		b.appendQueryParameter("s", this.getPublisherId());
//		b.appendQueryParameter("u", this.getUserAgent());
		b.appendQueryParameter("u", USER_AGENT);
//		b.appendQueryParameter("u2", this.getUserAgent2());
		b.appendQueryParameter("r_random", Integer.toString(random));
        if(androidAdId.length() > 0){
            b.appendQueryParameter("o_andadvid", androidAdId);
        }
        else {
            b.appendQueryParameter("o_andadvid", "xxxx");
        }
		b.appendQueryParameter("o_andadvdnt", (adDoNotTrack ? "1" : "0"));
		b.appendQueryParameter("v", this.getProtocolVersion());
        b.appendQueryParameter("i",this.ip);

//		b.appendQueryParameter("u_wv", this.getUserAgent());
//		b.appendQueryParameter("u_br", this.getUserAgent());
		if (longitude != 0 && latitude != 0) {
			b.appendQueryParameter("longitude", Double.toString(longitude));
			b.appendQueryParameter("latitude", Double.toString(latitude));
		}

        b.appendQueryParameter("template_name",this.getTemplateName());
//        WriteTemp(b.build().toString());

		return b.build();
	}

	public List<String> getAdTypes() {
		return adTypes;
	}

	public void setAdTypes(List<String> adTypes) {
		this.adTypes = adTypes;
	}

	public String getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(String publisherId) {
		this.publisherId = publisherId;
	}

	public String getUserAgent() {
		if (this.userAgent == null)
			return "";
		return this.userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getAndroidAdId() {
		return androidAdId;
	}

	public void setAndroidAdId(String androidAdId) {
		this.androidAdId = androidAdId;
	}

	public Boolean hasAdDoNotTrack() {
		return adDoNotTrack;
	}

	public void setAdDoNotTrack(boolean adDoNotTrack) {
		this.adDoNotTrack = adDoNotTrack;
	}

	public String getProtocolVersion() {
		if (this.protocolVersion == null)
			return Const.VERSION;
		else
			return this.protocolVersion;
	}

	public void setProtocolVersion(String protocolVersion) {
		this.protocolVersion = protocolVersion;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public int getUserAge() {
		return userAge;
	}

	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}

	public String getUserAgent2() {
		if (this.userAgent2 == null)
			return "";
		return this.userAgent2;
	}
	
	public void setUserAgent2(final String userAgent) {
		this.userAgent2 = userAgent;
	}

	public List<String> getKeywords() {
		return keywords;
	}
	
	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}
	
	public void setRequestUrl (String url) {
		this.request_url = url;
	}

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }
}
