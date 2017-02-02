package forms.cmn;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import consts.cmn.CommonConstraint;
import play.data.validation.ValidationError;

/**
 *
 * SystemFusionフォーム.
 *
 * @since	2016/11/21
 * @author	kou1miyazaki
 * @version 1.0
 *
 */
public abstract class SystemFusionForm {

	/** ノート. */
	public String note;

	/** 作成者. */
	public String createUser;

	/** 作成日. */
	public String createDate;

	/** 最終更新者. */
	public String updateUser;

	/** 最終更新日. */
	public String updateDate;

	/** コンストラクタ. */
	public SystemFusionForm() {
	}

	/**
	 *
	 * 値チェック.
	 *
	 * @return List
	 *
	 */
	public abstract List<ValidationError> validate();

	/**
	 *
	 * 詳細アイテムを取得する.
	 *
	 * @return List
	 *
	 */
	public final List<ItemForm> getItemRecord() {

	    // リスト作成.
		List<ItemForm> item = new ArrayList<ItemForm>();
		IntStream.range(0, CommonConstraint.C_ITEM_DETAIL.size())
			.forEach(i -> {
				try {
					item.add(
						new ItemForm(CommonConstraint.C_ITEM_DETAIL.get(i),
								CommonConstraint.C_ITEM_TYPE.get(i),
								CommonConstraint.C_ITEM_DISPLAY.get(i),
								CommonConstraint.C_ITEM_LABEL.get(i) //,
								//(String) this.getClass().getDeclaredField(CommonConstraint.C_ITEM_DETAIL.get(i)).get(this)
							)
						);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
//				} catch (IllegalAccessException e) {
//					e.printStackTrace();
//				} catch (NoSuchFieldException e) {
//					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				}
			}
		);

		item.get(0).value = this.note;
		item.get(1).value = this.createUser;
		item.get(2).value = this.createDate;
		item.get(3).value = this.updateUser;
		item.get(4).value = this.updateDate;
		return item;
	}

	/**
	 *
	 * 文字列へ変換.
	 *
	 * @return String
	 *
	 */
	@Override
	public final String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Class: " + this.getClass().getCanonicalName() + "\n");
		sb.append("Settings:\n");
		for (Field field : this.getClass().getDeclaredFields()) {
			try {
				field.setAccessible(true);
				sb.append(field.getName() + " = " + field.get(this) + "\n");
			} catch (IllegalAccessException e) {
				sb.append(field.getName() + " = " + "access denied\n");
			}
		}
		return sb.toString();
	}
}

