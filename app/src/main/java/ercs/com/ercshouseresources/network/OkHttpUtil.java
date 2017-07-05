package ercs.com.ercshouseresources.network;

import android.support.annotation.IntDef;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.CacheControl;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/3/23.
 */

public class OkHttpUtil {
    private boolean doLog = true;//日志打印
    private final String TAG = getClass().getSimpleName();
    private static OkHttpClient httpClient;
    private Builder builder;
    private long timeStamp;

    private enum Method {
        GET, POST, POSTFILE
    }

    ;

    public HttpInfo doPostSync(HttpInfo info) {
        doRequestSync(info, Method.POST);
        return info;
    }

    public HttpInfo doPostFileSync(HttpInfo info) {
        doRequestSync(info, Method.POSTFILE);
        return info;
    }

    public HttpInfo doGetSync(HttpInfo info) {
        doRequestSync(info, Method.GET);
        return info;
    }

    private HttpInfo doRequestSync(HttpInfo info, Method method) {
        try {
            String url = info.getUrl();
            if (TextUtils.isEmpty(url)) {
                return retInfo(info, info.CheckURL);
            }
            Response res = httpClient.newCall(fetchRequest(info, method)).execute();
            if (null != res && null != res.body()) {
                if (res.isSuccessful()) {
                    return retInfo(info, info.SUCCESS, res.body().string());
                } else {
                    showLog("HttpStatus: " + res.code());
                    if (res.code() == 404)//请求页面路径错误
                        return retInfo(info, info.CheckURL);
                    if (res.code() == 500)//服务器内部错误
                        return retInfo(info, info.NoResult);
                    if (res.code() == 502)//错误网关
                        return retInfo(info, info.CheckNet);
                    if (res.code() == 504)//网关超时
                        return retInfo(info, info.CheckNet);
                }
            }
            return retInfo(info, info.CheckURL);
        } catch (IllegalArgumentException e) {
            return retInfo(info, info.ProtocolException);
        } catch (SocketTimeoutException e) {
            if (null != e && null != e.getMessage()) {
                if (e.getMessage().contains("failed to connect to"))
                    return retInfo(info, info.ConnectionTimeOut);
                if (e.getMessage().equals("timeout"))
                    return retInfo(info, info.WriteAndReadTimeOut);
            }
            return retInfo(info, info.WriteAndReadTimeOut);
        } catch (UnknownHostException e) {
            return retInfo(info, info.CheckNet);
        } catch (Exception e) {
            return retInfo(info, info.NoResult);
        }
    }

    public void doPostAsync(HttpInfo info, Callback callback) {
        doRequestAsync(info, Method.POST, callback);
    }

    public void doPostFileSync(HttpInfo info, Callback callback) {
        doRequestAsync(info, Method.POSTFILE, callback);

    }

    public void doGetAsync(HttpInfo info, Callback callback) {
        doRequestAsync(info, Method.GET, callback);
    }

    private void doRequestAsync(HttpInfo info, Method method, Callback callback) {
        httpClient.newCall(fetchRequest(info, method)).enqueue(callback);
    }


    private Request fetchRequest(HttpInfo info, Method method) {
        Request request = null;
        if (method == Method.POST) {
            FormBody.Builder builder = new FormBody.Builder();
            if (null != info.getParams() && !info.getParams().isEmpty()) {
                StringBuffer log = new StringBuffer("PostParams: ");
                for (String key : info.getParams().keySet()) {
                    builder.add(key, info.getParams().get(key));
                    log.append(key + " =" + info.getParams().get(key) + ", ");

                }
                showLog(log.toString());
            }
            request = new Request.Builder()
                    .url(info.getUrl())
                    .post(builder.build())
                    .build();
        } else if (method == Method.POSTFILE) {
            MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
            List<File> files = info.getFileParams();
            for (int i = 0; i < files.size(); i++) {
                builder.addFormDataPart("image" + i, files.get(i).getName(), RequestBody.create(MediaType.parse("img/png"), files.get(i)));//video/mp4
            }
            request = new Request.Builder()
                    .url(info.getUrl())
                    .post(builder.build())
                    .build();
        } else {
            StringBuffer params = new StringBuffer();
            params.append(info.getUrl());
            if (null != info.getParams() && !info.getParams().isEmpty())
                for (String name : info.getParams().keySet()) {
                    params.append("&" + name + "=" + info.getParams().get(name));
                }
            request = new Request.Builder()
                    .url(params.toString())
                    .get()
                    .build();
        }
        return request;
    }

    private HttpInfo retInfo(HttpInfo info, int code) {
        retInfo(info, code, null);
        return info;
    }

    private HttpInfo retInfo(HttpInfo info, int code, String resDetail) {
        info.packInfo(code, resDetail);
        showLog("Response: " + info.getRetDetail());
        return info;
    }

    public Interceptor CACHE_CONTROL_NETWORK_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response res = chain.proceed(chain.request());
            Response.Builder resBuilder = res.newBuilder();
            switch (builder.cacheType) {
                case CacheType.CACHE_THEN_NETWORK:
                    resBuilder.removeHeader("Pragma")
                            .header("Cache-Control", String.format("max-age=%d", builder.cacheSurvivalTime));
            }
            return resBuilder.build();
        }
    };

    public Interceptor CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            switch (builder.cacheType) {
                case CacheType.FORCE_CACHE:
                    request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
                    break;
                case CacheType.FORCE_NETWORK:
                    request = request.newBuilder().cacheControl(CacheControl.FORCE_NETWORK).build();
                    break;
                case CacheType.NETWORK_THEN_CACHE:
                case CacheType.CACHE_THEN_NETWORK:
//                    if(!NetWorkUtil.isNetworkAvailable(BaseApplication.getApplication())){
//                        request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
//                    }
                    break;
            }
            return chain.proceed(request);
        }
    };

    public Interceptor LOG_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            long startTime = System.currentTimeMillis();
            showLog(String.format("%s-URL: %s %n", chain.request().method(),
                    chain.request().url()));
            Response res = chain.proceed(chain.request());
            long endTime = System.currentTimeMillis();
            showLog(String.format("CostTime: %.1fs", (endTime - startTime) / 1000.0));
            return res;
        }
    };

    public Interceptor EXT_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request original = chain.request();
            Request.Builder requestBuilder = original.newBuilder();
            if ("POST".equals(original.method())) {
                if (original.body() instanceof FormBody) {
                    FormBody.Builder newFormBody = new FormBody.Builder();
                    FormBody originalFormBody = (FormBody) original.body();
                    for (int i = 0; i < originalFormBody.size(); i++) {
                        newFormBody.addEncoded(originalFormBody.encodedName(i), originalFormBody.encodedValue(i));
                    }
                    newFormBody.add("userId", "25025298");
                    requestBuilder.method(original.method(), newFormBody.build());
                }
            } else if ("GET".equals(original.method())) {
                requestBuilder.url(original.url() + "&userId=25025298");
            }
            Request request = requestBuilder.build();
            return chain.proceed(request);
        }
    };

    private OkHttpUtil(Builder builder) {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                .connectTimeout(builder.connectTimeout, TimeUnit.SECONDS)
                .readTimeout(builder.readTimeout, TimeUnit.SECONDS)
                .writeTimeout(builder.writeTimeout, TimeUnit.SECONDS)
                // .cache(new Cache(builder.cachedDir,builder.maxCacheSize))
                .retryOnConnectionFailure(builder.retryOnConnectionFailure)
                .addInterceptor(EXT_INTERCEPTOR)
                .addInterceptor(LOG_INTERCEPTOR)
                .addInterceptor(CACHE_CONTROL_INTERCEPTOR)
                .addNetworkInterceptor(CACHE_CONTROL_NETWORK_INTERCEPTOR);
        if (null != builder.networkInterceptors && !builder.networkInterceptors.isEmpty())
            clientBuilder.networkInterceptors().addAll(builder.networkInterceptors);
        if (null != builder.interceptors && !builder.interceptors.isEmpty())
            clientBuilder.interceptors().addAll(builder.interceptors);
        httpClient = clientBuilder.build();
        timeStamp = System.currentTimeMillis();
        this.builder = builder;
        switch (this.builder.cacheLevel) {
            case CacheLevel.FIRST_LEVEL:
                this.builder.cacheSurvivalTime = 0;
                break;
            case CacheLevel.SECOND_LEVEL:
                this.builder.cacheSurvivalTime = 15;
                break;
            case CacheLevel.THIRD_LEVEL:
                this.builder.cacheSurvivalTime = 30;
                break;
            case CacheLevel.FOURTH_LEVEL:
                this.builder.cacheSurvivalTime = 60;
                break;
            default:
                this.builder.cacheSurvivalTime = 0;
                break;
        }
        if (this.builder.cacheSurvivalTime > 0)
            this.builder.cacheType = CacheType.CACHE_THEN_NETWORK;
    }

    public static Builder Builder() {
        return new Builder();
    }

    public static final class Builder {

        private int maxCacheSize = 10 * 1024 * 1024;
        //  private File cachedDir = BaseApplication.getApplication().getExternalCacheDir();
        private int connectTimeout = 20;
        private int readTimeout = 20;
        private int writeTimeout = 20;
        private boolean retryOnConnectionFailure = false;
        private List<Interceptor> networkInterceptors;
        private List<Interceptor> interceptors;
        private int cacheSurvivalTime = 0;//缓存存活时间（秒）
        private int cacheType = CacheType.NETWORK_THEN_CACHE;//缓存类型
        private int cacheLevel = CacheLevel.SECOND_LEVEL;//缓存级别

        public Builder() {
        }

        public OkHttpUtil build() {
            return new OkHttpUtil(this);
        }

        public Builder setMaxCacheSize(int maxCacheSize) {
            this.maxCacheSize = maxCacheSize;
            return this;
        }

        public Builder setCachedDir(File cachedDir) {
            // this.cachedDir = cachedDir;
            return this;
        }

        public Builder setConnectTimeout(int connectTimeout) {
            if (connectTimeout <= 0)
                throw new IllegalArgumentException("connectTimeout must be > 0");
            this.connectTimeout = connectTimeout;
            return this;
        }

        public Builder setReadTimeout(int readTimeout) {
            if (readTimeout <= 0)
                throw new IllegalArgumentException("readTimeout must be > 0");
            this.readTimeout = readTimeout;
            return this;
        }

        public Builder setWriteTimeout(int writeTimeout) {
            if (writeTimeout <= 0)
                throw new IllegalArgumentException("writeTimeout must be > 0");
            this.writeTimeout = writeTimeout;
            return this;
        }

        public Builder setRetryOnConnectionFailure(boolean retryOnConnectionFailure) {
            this.retryOnConnectionFailure = retryOnConnectionFailure;
            return this;
        }

        public Builder setNetworkInterceptors(List<Interceptor> networkInterceptors) {
            this.networkInterceptors = networkInterceptors;
            return this;
        }

        public Builder setInterceptors(List<Interceptor> interceptors) {
            this.interceptors = interceptors;
            return this;
        }

        public Builder setCacheSurvivalTime(int cacheSurvivalTime) {
            if (cacheSurvivalTime < 0)
                throw new IllegalArgumentException("cacheSurvivalTime must be >= 0");
            this.cacheSurvivalTime = cacheSurvivalTime;
            return this;
        }

        public Builder setCacheType(@CacheType int cacheType) {
            this.cacheType = cacheType;
            return this;
        }

        public Builder setCacheLevel(@CacheLevel int cacheLevel) {
            this.cacheLevel = cacheLevel;
            return this;
        }
    }

    @IntDef({CacheType.FORCE_NETWORK, CacheType.FORCE_CACHE, CacheType.NETWORK_THEN_CACHE, CacheType.CACHE_THEN_NETWORK})
    public @interface CacheType {
        int FORCE_NETWORK = 1;
        int FORCE_CACHE = 2;
        int NETWORK_THEN_CACHE = 3;
        int CACHE_THEN_NETWORK = 4;
    }

    @IntDef({CacheLevel.FIRST_LEVEL, CacheLevel.SECOND_LEVEL, CacheLevel.THIRD_LEVEL, CacheLevel.FOURTH_LEVEL})
    public @interface CacheLevel {
        int FIRST_LEVEL = 1; //默认无缓存
        int SECOND_LEVEL = 2; //缓存存活时间为15秒
        int THIRD_LEVEL = 3; //30秒
        int FOURTH_LEVEL = 4; //60秒
    }

    private void showLog(String msg) {
        if (this.doLog)
            Log.d(TAG + "[" + timeStamp + "]", msg);
    }

}
