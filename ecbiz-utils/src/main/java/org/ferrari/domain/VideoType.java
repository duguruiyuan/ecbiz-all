
package org.ferrari.domain;
import org.ferrari.utils.Code;
import org.ferrari.utils.CodeType;
/**
 * @author sindy
 *
 */
public class VideoType extends CodeType {
	private static final long serialVersionUID = -3444959180957116288L;
	public final static VideoType type = new VideoType();
	public final static Code DEFAULT  = new Code(type, "2", "车型视频");
	public final static Code ONE  = new Code(type, "1", "车展视频");
	public final static Code THREE  = new Code(type, "3", "视听娱乐");
}
