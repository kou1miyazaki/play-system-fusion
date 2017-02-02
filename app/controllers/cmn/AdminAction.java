package controllers.cmn;

import play.libs.F;
import play.mvc.Action.Simple;
import play.mvc.Http;
import play.mvc.Result;

public class AdminAction extends Simple {

	   @Override
	    public F.Promise<Result> call(Http.Context ctx) throws Throwable {
	        System.out.println("事前処理を行います。");
	        System.out.println(ctx.session().toString());
	        //System.out.println(Context.current().request().toString());

	        String r = ctx.request().remoteAddress();
	        String x = ctx.request().username();
	        String p = ctx.request().getHeader("X-Forwarded-Proto");
	        String ua = ctx.request().getHeader("User-Agent");
	        System.out.println("x-forwarded-for" + r );
	        System.out.println("x-forwarded-for" + x );
	        System.out.println("x-forwarded-prot" + p );
	        System.out.println("user-agent" + ua );

	        return delegate.call(ctx);
	    }

}